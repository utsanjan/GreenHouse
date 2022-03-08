package com.theartofdev.edmodo.cropper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public class CropImageActivity extends AppCompatActivity implements CropImageView.OnSetImageUriCompleteListener, CropImageView.OnCropImageCompleteListener {
    private Uri mCropImageUri;
    private CropImageView mCropImageView;
    private CropImageOptions mOptions;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_image_activity);
        this.mCropImageView = (CropImageView) findViewById(R.id.cropImageView);
        Bundle bundle = getIntent().getBundleExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE);
        this.mCropImageUri = (Uri) bundle.getParcelable(CropImage.CROP_IMAGE_EXTRA_SOURCE);
        this.mOptions = (CropImageOptions) bundle.getParcelable(CropImage.CROP_IMAGE_EXTRA_OPTIONS);
        if (savedInstanceState == null) {
            Uri uri = this.mCropImageUri;
            if (uri == null || uri.equals(Uri.EMPTY)) {
                if (CropImage.isExplicitCameraPermissionRequired(this)) {
                    requestPermissions(new String[]{"android.permission.CAMERA"}, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
                } else {
                    CropImage.startPickImageActivity(this);
                }
            } else if (CropImage.isReadExternalStoragePermissionsRequired(this, this.mCropImageUri)) {
                requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                this.mCropImageView.setImageUriAsync(this.mCropImageUri);
            }
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            CropImageOptions cropImageOptions = this.mOptions;
            CharSequence title = (cropImageOptions == null || cropImageOptions.activityTitle == null || this.mOptions.activityTitle.length() <= 0) ? getResources().getString(R.string.crop_image_activity_title) : this.mOptions.activityTitle;
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.mCropImageView.setOnSetImageUriCompleteListener(this);
        this.mCropImageView.setOnCropImageCompleteListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.mCropImageView.setOnSetImageUriCompleteListener(null);
        this.mCropImageView.setOnCropImageCompleteListener(null);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.crop_image_menu, menu);
        if (!this.mOptions.allowRotation) {
            menu.removeItem(R.id.crop_image_menu_rotate_left);
            menu.removeItem(R.id.crop_image_menu_rotate_right);
        } else if (this.mOptions.allowCounterRotation) {
            menu.findItem(R.id.crop_image_menu_rotate_left).setVisible(true);
        }
        if (!this.mOptions.allowFlipping) {
            menu.removeItem(R.id.crop_image_menu_flip);
        }
        if (this.mOptions.cropMenuCropButtonTitle != null) {
            menu.findItem(R.id.crop_image_menu_crop).setTitle(this.mOptions.cropMenuCropButtonTitle);
        }
        Drawable cropIcon = null;
        try {
            if (this.mOptions.cropMenuCropButtonIcon != 0) {
                cropIcon = ContextCompat.getDrawable(this, this.mOptions.cropMenuCropButtonIcon);
                menu.findItem(R.id.crop_image_menu_crop).setIcon(cropIcon);
            }
        } catch (Exception e) {
            Log.w("AIC", "Failed to read menu crop drawable", e);
        }
        if (this.mOptions.activityMenuIconColor != 0) {
            updateMenuItemIconColor(menu, R.id.crop_image_menu_rotate_left, this.mOptions.activityMenuIconColor);
            updateMenuItemIconColor(menu, R.id.crop_image_menu_rotate_right, this.mOptions.activityMenuIconColor);
            updateMenuItemIconColor(menu, R.id.crop_image_menu_flip, this.mOptions.activityMenuIconColor);
            if (cropIcon != null) {
                updateMenuItemIconColor(menu, R.id.crop_image_menu_crop, this.mOptions.activityMenuIconColor);
            }
        }
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.crop_image_menu_crop) {
            cropImage();
            return true;
        } else if (item.getItemId() == R.id.crop_image_menu_rotate_left) {
            rotateImage(-this.mOptions.rotationDegrees);
            return true;
        } else if (item.getItemId() == R.id.crop_image_menu_rotate_right) {
            rotateImage(this.mOptions.rotationDegrees);
            return true;
        } else if (item.getItemId() == R.id.crop_image_menu_flip_horizontally) {
            this.mCropImageView.flipImageHorizontally();
            return true;
        } else if (item.getItemId() == R.id.crop_image_menu_flip_vertically) {
            this.mCropImageView.flipImageVertically();
            return true;
        } else if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        } else {
            setResultCancel();
            return true;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        setResultCancel();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200) {
            if (resultCode == 0) {
                setResultCancel();
            }
            if (resultCode == -1) {
                Uri pickImageResultUri = CropImage.getPickImageResultUri(this, data);
                this.mCropImageUri = pickImageResultUri;
                if (CropImage.isReadExternalStoragePermissionsRequired(this, pickImageResultUri)) {
                    requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
                } else {
                    this.mCropImageView.setImageUriAsync(this.mCropImageUri);
                }
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 201) {
            Uri uri = this.mCropImageUri;
            if (uri == null || grantResults.length <= 0 || grantResults[0] != 0) {
                Toast.makeText(this, R.string.crop_image_activity_no_permissions, 1).show();
                setResultCancel();
            } else {
                this.mCropImageView.setImageUriAsync(uri);
            }
        }
        if (requestCode == 2011) {
            CropImage.startPickImageActivity(this);
        }
    }

    @Override // com.theartofdev.edmodo.cropper.CropImageView.OnSetImageUriCompleteListener
    public void onSetImageUriComplete(CropImageView view, Uri uri, Exception error) {
        if (error == null) {
            if (this.mOptions.initialCropWindowRectangle != null) {
                this.mCropImageView.setCropRect(this.mOptions.initialCropWindowRectangle);
            }
            if (this.mOptions.initialRotation > -1) {
                this.mCropImageView.setRotatedDegrees(this.mOptions.initialRotation);
                return;
            }
            return;
        }
        setResult(null, error, 1);
    }

    @Override // com.theartofdev.edmodo.cropper.CropImageView.OnCropImageCompleteListener
    public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {
        setResult(result.getUri(), result.getError(), result.getSampleSize());
    }

    protected void cropImage() {
        if (this.mOptions.noOutputImage) {
            setResult(null, null, 1);
            return;
        }
        Uri outputUri = getOutputUri();
        this.mCropImageView.saveCroppedImageAsync(outputUri, this.mOptions.outputCompressFormat, this.mOptions.outputCompressQuality, this.mOptions.outputRequestWidth, this.mOptions.outputRequestHeight, this.mOptions.outputRequestSizeOptions);
    }

    protected void rotateImage(int degrees) {
        this.mCropImageView.rotateImage(degrees);
    }

    protected Uri getOutputUri() {
        Uri outputUri = this.mOptions.outputUri;
        if (outputUri != null && !outputUri.equals(Uri.EMPTY)) {
            return outputUri;
        }
        try {
            String ext = this.mOptions.outputCompressFormat == Bitmap.CompressFormat.JPEG ? ".jpg" : this.mOptions.outputCompressFormat == Bitmap.CompressFormat.PNG ? ".png" : ".webp";
            return Uri.fromFile(File.createTempFile("cropped", ext, getCacheDir()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create temp file for output image", e);
        }
    }

    protected void setResult(Uri uri, Exception error, int sampleSize) {
        int resultCode = error == null ? -1 : CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE;
        setResult(resultCode, getResultIntent(uri, error, sampleSize));
        finish();
    }

    protected void setResultCancel() {
        setResult(0);
        finish();
    }

    protected Intent getResultIntent(Uri uri, Exception error, int sampleSize) {
        CropImage.ActivityResult result = new CropImage.ActivityResult(this.mCropImageView.getImageUri(), uri, error, this.mCropImageView.getCropPoints(), this.mCropImageView.getCropRect(), this.mCropImageView.getRotatedDegrees(), this.mCropImageView.getWholeImageRect(), sampleSize);
        Intent intent = new Intent();
        intent.putExtras(getIntent());
        intent.putExtra(CropImage.CROP_IMAGE_EXTRA_RESULT, result);
        return intent;
    }

    private void updateMenuItemIconColor(Menu menu, int itemId, int color) {
        Drawable menuItemIcon;
        MenuItem menuItem = menu.findItem(itemId);
        if (menuItem != null && (menuItemIcon = menuItem.getIcon()) != null) {
            try {
                menuItemIcon.mutate();
                menuItemIcon.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                menuItem.setIcon(menuItemIcon);
            } catch (Exception e) {
                Log.w("AIC", "Failed to update menu item color", e);
            }
        }
    }
}
