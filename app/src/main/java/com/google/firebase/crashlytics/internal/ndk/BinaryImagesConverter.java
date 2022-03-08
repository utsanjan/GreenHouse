package com.google.firebase.crashlytics.internal.ndk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class BinaryImagesConverter {
    private static final String DATA_DIR = "/data";
    private final Context context;
    private final FileIdStrategy fileIdStrategy;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface FileIdStrategy {
        String createId(File file) throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinaryImagesConverter(Context context, FileIdStrategy fileIdStrategy) {
        this.context = context;
        this.fileIdStrategy = fileIdStrategy;
    }

    byte[] convert(String raw) throws IOException {
        JSONArray binaryImagesJson = parseProcMapsJsonFromString(raw);
        return generateBinaryImagesJsonString(binaryImagesJson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] convert(BufferedReader reader) throws IOException {
        JSONArray binaryImagesJson = parseProcMapsJsonFromStream(reader);
        return generateBinaryImagesJsonString(binaryImagesJson);
    }

    private JSONArray parseProcMapsJsonFromStream(BufferedReader reader) throws IOException {
        JSONArray binaryImagesJson = new JSONArray();
        while (true) {
            String mapEntryString = reader.readLine();
            if (mapEntryString == null) {
                return binaryImagesJson;
            }
            JSONObject mapJson = jsonFromMapEntryString(mapEntryString);
            if (mapJson != null) {
                binaryImagesJson.put(mapJson);
            }
        }
    }

    private JSONArray parseProcMapsJsonFromString(String rawProcMapsString) {
        JSONArray binaryImagesJson = new JSONArray();
        try {
            JSONObject rawObj = new JSONObject(rawProcMapsString);
            JSONArray maps = rawObj.getJSONArray("maps");
            String mapsString = joinMapsEntries(maps);
            String[] mapsEntries = mapsString.split("\\|");
            for (String mapEntryString : mapsEntries) {
                JSONObject mapJson = jsonFromMapEntryString(mapEntryString);
                if (mapJson != null) {
                    binaryImagesJson.put(mapJson);
                }
            }
            return binaryImagesJson;
        } catch (JSONException e) {
            Logger.getLogger().w("Unable to parse proc maps string", e);
            return binaryImagesJson;
        }
    }

    private JSONObject jsonFromMapEntryString(String mapEntryString) {
        ProcMapEntry mapInfo = ProcMapEntryParser.parse(mapEntryString);
        if (mapInfo == null || !isRelevant(mapInfo)) {
            return null;
        }
        String path = mapInfo.path;
        File binFile = getLibraryFile(path);
        try {
            String uuid = this.fileIdStrategy.createId(binFile);
            try {
                return createBinaryImageJson(uuid, mapInfo);
            } catch (JSONException e) {
                Logger.getLogger().d("Could not create a binary image json string", e);
                return null;
            }
        } catch (IOException e2) {
            Logger logger = Logger.getLogger();
            logger.d("Could not generate ID for file " + mapInfo.path, e2);
            return null;
        }
    }

    private File getLibraryFile(String path) {
        File libFile = new File(path);
        if (!libFile.exists()) {
            return correctDataPath(libFile);
        }
        return libFile;
    }

    private File correctDataPath(File missingFile) {
        if (Build.VERSION.SDK_INT < 9) {
            return missingFile;
        }
        if (!missingFile.getAbsolutePath().startsWith(DATA_DIR)) {
            return missingFile;
        }
        try {
            ApplicationInfo ai = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 0);
            return new File(ai.nativeLibraryDir, missingFile.getName());
        } catch (PackageManager.NameNotFoundException e) {
            Logger.getLogger().e("Error getting ApplicationInfo", e);
            return missingFile;
        }
    }

    private static byte[] generateBinaryImagesJsonString(JSONArray binaryImages) {
        JSONObject binaryImagesObject = new JSONObject();
        try {
            binaryImagesObject.put("binary_images", binaryImages);
            return binaryImagesObject.toString().getBytes(Charset.forName("UTF-8"));
        } catch (JSONException e) {
            Logger.getLogger().w("Binary images string is null", e);
            return new byte[0];
        }
    }

    private static JSONObject createBinaryImageJson(String uuid, ProcMapEntry mapEntry) throws JSONException {
        JSONObject binaryImage = new JSONObject();
        binaryImage.put("base_address", mapEntry.address);
        binaryImage.put("size", mapEntry.size);
        binaryImage.put("name", mapEntry.path);
        binaryImage.put("uuid", uuid);
        return binaryImage;
    }

    private static String joinMapsEntries(JSONArray array) throws JSONException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length(); i++) {
            sb.append(array.getString(i));
        }
        return sb.toString();
    }

    private static boolean isRelevant(ProcMapEntry mapEntry) {
        return (mapEntry.perms.indexOf(120) == -1 || mapEntry.path.indexOf(47) == -1) ? false : true;
    }
}
