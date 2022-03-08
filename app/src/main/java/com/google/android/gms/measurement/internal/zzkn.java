package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzbt;
import com.google.android.gms.internal.measurement.zzcb;
import com.google.android.gms.internal.measurement.zzfb;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzgz;
import com.google.android.gms.internal.measurement.zzjw;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkn extends zzkg {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkn(zzkj zzkjVar) {
        super(zzkjVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkg
    protected final boolean zze() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzcb.zzk.zza zzaVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzaVar.zza().zzb().zzc();
        if (obj instanceof String) {
            zzaVar.zzb((String) obj);
        } else if (obj instanceof Long) {
            zzaVar.zzb(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzaVar.zza(((Double) obj).doubleValue());
        } else {
            zzr().zzf().zza("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzcb.zze.zza zzaVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzaVar.zza().zzb().zzc().zze();
        if (obj instanceof String) {
            zzaVar.zzb((String) obj);
        } else if (obj instanceof Long) {
            zzaVar.zza(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzaVar.zza(((Double) obj).doubleValue());
        } else if (!zzjw.zzb() || !zzt().zza(zzaq.zzcf) || !(obj instanceof Bundle[])) {
            zzr().zzf().zza("Ignoring invalid (type) event param value", obj);
        } else {
            zzaVar.zza(zza((Bundle[]) obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzcb.zze zza(zzcb.zzc zzcVar, String str) {
        for (zzcb.zze zzeVar : zzcVar.zza()) {
            if (zzeVar.zzb().equals(str)) {
                return zzeVar;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object zzb(zzcb.zzc zzcVar, String str) {
        zzcb.zze zza = zza(zzcVar, str);
        if (zza == null) {
            return null;
        }
        if (zza.zzc()) {
            return zza.zzd();
        }
        if (zza.zze()) {
            return Long.valueOf(zza.zzf());
        }
        if (zza.zzi()) {
            return Double.valueOf(zza.zzj());
        }
        if (!zzjw.zzb() || !zzt().zza(zzaq.zzcf) || zza.zzl() <= 0) {
            return null;
        }
        List<zzcb.zze> zzk = zza.zzk();
        ArrayList arrayList = new ArrayList();
        for (zzcb.zze zzeVar : zzk) {
            if (zzeVar != null) {
                Bundle bundle = new Bundle();
                for (zzcb.zze zzeVar2 : zzeVar.zzk()) {
                    if (zzeVar2.zzc()) {
                        bundle.putString(zzeVar2.zzb(), zzeVar2.zzd());
                    } else if (zzeVar2.zze()) {
                        bundle.putLong(zzeVar2.zzb(), zzeVar2.zzf());
                    } else if (zzeVar2.zzi()) {
                        bundle.putDouble(zzeVar2.zzb(), zzeVar2.zzj());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzcb.zzc zza(zzal zzalVar) {
        zzcb.zzc.zza zzb = zzcb.zzc.zzj().zzb(zzalVar.zzd);
        Iterator<String> it = zzalVar.zze.iterator();
        while (it.hasNext()) {
            String next = it.next();
            zzcb.zze.zza zza = zzcb.zze.zzm().zza(next);
            zza(zza, zzalVar.zze.zza(next));
            zzb.zza(zza);
        }
        return (zzcb.zzc) ((zzfo) zzb.zzv());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzcb.zzc.zza zzaVar, String str, Object obj) {
        List<zzcb.zze> zza = zzaVar.zza();
        int i = 0;
        while (true) {
            if (i >= zza.size()) {
                i = -1;
                break;
            } else if (str.equals(zza.get(i).zzb())) {
                break;
            } else {
                i++;
            }
        }
        zzcb.zze.zza zza2 = zzcb.zze.zzm().zza(str);
        if (obj instanceof Long) {
            zza2.zza(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zza2.zzb((String) obj);
        } else if (obj instanceof Double) {
            zza2.zza(((Double) obj).doubleValue());
        } else if (zzjw.zzb() && zzt().zza(zzaq.zzcf) && (obj instanceof Bundle[])) {
            zza2.zza(zza((Bundle[]) obj));
        }
        if (i >= 0) {
            zzaVar.zza(i, zza2);
        } else {
            zzaVar.zza(zza2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zza(zzcb.zzf zzfVar) {
        if (zzfVar == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        for (zzcb.zzg zzgVar : zzfVar.zza()) {
            if (zzgVar != null) {
                zza(sb, 1);
                sb.append("bundle {\n");
                if (zzgVar.zza()) {
                    zza(sb, 1, "protocol_version", Integer.valueOf(zzgVar.zzb()));
                }
                zza(sb, 1, "platform", zzgVar.zzq());
                if (zzgVar.zzz()) {
                    zza(sb, 1, "gmp_version", Long.valueOf(zzgVar.zzaa()));
                }
                if (zzgVar.zzab()) {
                    zza(sb, 1, "uploading_gmp_version", Long.valueOf(zzgVar.zzac()));
                }
                if (zzgVar.zzbc()) {
                    zza(sb, 1, "dynamite_version", Long.valueOf(zzgVar.zzbd()));
                }
                if (zzgVar.zzau()) {
                    zza(sb, 1, "config_version", Long.valueOf(zzgVar.zzav()));
                }
                zza(sb, 1, "gmp_app_id", zzgVar.zzam());
                zza(sb, 1, "admob_app_id", zzgVar.zzbb());
                zza(sb, 1, "app_id", zzgVar.zzx());
                zza(sb, 1, "app_version", zzgVar.zzy());
                if (zzgVar.zzar()) {
                    zza(sb, 1, "app_version_major", Integer.valueOf(zzgVar.zzas()));
                }
                zza(sb, 1, "firebase_instance_id", zzgVar.zzaq());
                if (zzgVar.zzah()) {
                    zza(sb, 1, "dev_cert_hash", Long.valueOf(zzgVar.zzai()));
                }
                zza(sb, 1, "app_store", zzgVar.zzw());
                if (zzgVar.zzg()) {
                    zza(sb, 1, "upload_timestamp_millis", Long.valueOf(zzgVar.zzh()));
                }
                if (zzgVar.zzi()) {
                    zza(sb, 1, "start_timestamp_millis", Long.valueOf(zzgVar.zzj()));
                }
                if (zzgVar.zzk()) {
                    zza(sb, 1, "end_timestamp_millis", Long.valueOf(zzgVar.zzl()));
                }
                if (zzgVar.zzm()) {
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(zzgVar.zzn()));
                }
                if (zzgVar.zzo()) {
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(zzgVar.zzp()));
                }
                zza(sb, 1, "app_instance_id", zzgVar.zzag());
                zza(sb, 1, "resettable_device_id", zzgVar.zzad());
                zza(sb, 1, "device_id", zzgVar.zzat());
                zza(sb, 1, "ds_id", zzgVar.zzay());
                if (zzgVar.zzae()) {
                    zza(sb, 1, "limited_ad_tracking", Boolean.valueOf(zzgVar.zzaf()));
                }
                zza(sb, 1, "os_version", zzgVar.zzr());
                zza(sb, 1, "device_model", zzgVar.zzs());
                zza(sb, 1, "user_default_language", zzgVar.zzt());
                if (zzgVar.zzu()) {
                    zza(sb, 1, "time_zone_offset_minutes", Integer.valueOf(zzgVar.zzv()));
                }
                if (zzgVar.zzaj()) {
                    zza(sb, 1, "bundle_sequential_index", Integer.valueOf(zzgVar.zzak()));
                }
                if (zzgVar.zzan()) {
                    zza(sb, 1, "service_upload", Boolean.valueOf(zzgVar.zzao()));
                }
                zza(sb, 1, "health_monitor", zzgVar.zzal());
                if (!zzt().zza(zzaq.zzcl) && zzgVar.zzaw() && zzgVar.zzax() != 0) {
                    zza(sb, 1, "android_id", Long.valueOf(zzgVar.zzax()));
                }
                if (zzgVar.zzaz()) {
                    zza(sb, 1, "retry_counter", Integer.valueOf(zzgVar.zzba()));
                }
                List<zzcb.zzk> zze = zzgVar.zze();
                if (zze != null) {
                    for (zzcb.zzk zzkVar : zze) {
                        if (zzkVar != null) {
                            zza(sb, 2);
                            sb.append("user_property {\n");
                            Double d = null;
                            zza(sb, 2, "set_timestamp_millis", zzkVar.zza() ? Long.valueOf(zzkVar.zzb()) : null);
                            zza(sb, 2, "name", zzo().zzc(zzkVar.zzc()));
                            zza(sb, 2, "string_value", zzkVar.zze());
                            zza(sb, 2, "int_value", zzkVar.zzf() ? Long.valueOf(zzkVar.zzg()) : null);
                            if (zzkVar.zzh()) {
                                d = Double.valueOf(zzkVar.zzi());
                            }
                            zza(sb, 2, "double_value", d);
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzcb.zza> zzap = zzgVar.zzap();
                String zzx = zzgVar.zzx();
                if (zzap != null) {
                    for (zzcb.zza zzaVar : zzap) {
                        if (zzaVar != null) {
                            zza(sb, 2);
                            sb.append("audience_membership {\n");
                            if (zzaVar.zza()) {
                                zza(sb, 2, "audience_id", Integer.valueOf(zzaVar.zzb()));
                            }
                            if (zzaVar.zzf()) {
                                zza(sb, 2, "new_audience", Boolean.valueOf(zzaVar.zzg()));
                            }
                            zza(sb, 2, "current_data", zzaVar.zzc(), zzx);
                            if (zzaVar.zzd()) {
                                zza(sb, 2, "previous_data", zzaVar.zze(), zzx);
                            }
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzcb.zzc> zzc = zzgVar.zzc();
                if (zzc != null) {
                    for (zzcb.zzc zzcVar : zzc) {
                        if (zzcVar != null) {
                            zza(sb, 2);
                            sb.append("event {\n");
                            zza(sb, 2, "name", zzo().zza(zzcVar.zzc()));
                            if (zzcVar.zzd()) {
                                zza(sb, 2, "timestamp_millis", Long.valueOf(zzcVar.zze()));
                            }
                            if (zzcVar.zzf()) {
                                zza(sb, 2, "previous_timestamp_millis", Long.valueOf(zzcVar.zzg()));
                            }
                            if (zzcVar.zzh()) {
                                zza(sb, 2, "count", Integer.valueOf(zzcVar.zzi()));
                            }
                            if (zzcVar.zzb() != 0) {
                                zza(sb, 2, zzcVar.zza());
                            }
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zza(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zza(zzbt.zzb zzbVar) {
        if (zzbVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzbVar.zza()) {
            zza(sb, 0, "filter_id", Integer.valueOf(zzbVar.zzb()));
        }
        zza(sb, 0, "event_name", zzo().zza(zzbVar.zzc()));
        String zza = zza(zzbVar.zzh(), zzbVar.zzi(), zzbVar.zzk());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", zza);
        }
        if (zzbVar.zzf()) {
            zza(sb, 1, "event_count_filter", zzbVar.zzg());
        }
        if (zzbVar.zze() > 0) {
            sb.append("  filters {\n");
            for (zzbt.zzc zzcVar : zzbVar.zzd()) {
                zza(sb, 2, zzcVar);
            }
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zza(zzbt.zze zzeVar) {
        if (zzeVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zzeVar.zza()) {
            zza(sb, 0, "filter_id", Integer.valueOf(zzeVar.zzb()));
        }
        zza(sb, 0, "property_name", zzo().zzc(zzeVar.zzc()));
        String zza = zza(zzeVar.zze(), zzeVar.zzf(), zzeVar.zzh());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", zza);
        }
        zza(sb, 1, zzeVar.zzd());
        sb.append("}\n");
        return sb.toString();
    }

    private static String zza(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private final void zza(StringBuilder sb, int i, List<zzcb.zze> list) {
        if (list != null) {
            int i2 = i + 1;
            for (zzcb.zze zzeVar : list) {
                if (zzeVar != null) {
                    zza(sb, i2);
                    sb.append("param {\n");
                    Double d = null;
                    if (!zzjw.zzb() || !zzt().zza(zzaq.zzcd)) {
                        zza(sb, i2, "name", zzo().zzb(zzeVar.zzb()));
                        zza(sb, i2, "string_value", zzeVar.zzd());
                        zza(sb, i2, "int_value", zzeVar.zze() ? Long.valueOf(zzeVar.zzf()) : null);
                        if (zzeVar.zzi()) {
                            d = Double.valueOf(zzeVar.zzj());
                        }
                        zza(sb, i2, "double_value", d);
                    } else {
                        zza(sb, i2, "name", zzeVar.zza() ? zzo().zzb(zzeVar.zzb()) : null);
                        zza(sb, i2, "string_value", zzeVar.zzc() ? zzeVar.zzd() : null);
                        zza(sb, i2, "int_value", zzeVar.zze() ? Long.valueOf(zzeVar.zzf()) : null);
                        if (zzeVar.zzi()) {
                            d = Double.valueOf(zzeVar.zzj());
                        }
                        zza(sb, i2, "double_value", d);
                        if (zzeVar.zzl() > 0) {
                            zza(sb, i2, zzeVar.zzk());
                        }
                    }
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzcb.zzi zziVar, String str2) {
        if (zziVar != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            if (zziVar.zzd() != 0) {
                zza(sb, 4);
                sb.append("results: ");
                int i2 = 0;
                for (Long l : zziVar.zzc()) {
                    int i3 = i2 + 1;
                    if (i2 != 0) {
                        sb.append(", ");
                    }
                    sb.append(l);
                    i2 = i3;
                }
                sb.append('\n');
            }
            if (zziVar.zzb() != 0) {
                zza(sb, 4);
                sb.append("status: ");
                int i4 = 0;
                for (Long l2 : zziVar.zza()) {
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(", ");
                    }
                    sb.append(l2);
                    i4 = i5;
                }
                sb.append('\n');
            }
            if (zziVar.zzf() != 0) {
                zza(sb, 4);
                sb.append("dynamic_filter_timestamps: {");
                int i6 = 0;
                for (zzcb.zzb zzbVar : zziVar.zze()) {
                    int i7 = i6 + 1;
                    if (i6 != 0) {
                        sb.append(", ");
                    }
                    sb.append(zzbVar.zza() ? Integer.valueOf(zzbVar.zzb()) : null);
                    sb.append(":");
                    sb.append(zzbVar.zzc() ? Long.valueOf(zzbVar.zzd()) : null);
                    i6 = i7;
                }
                sb.append("}\n");
            }
            if (zziVar.zzh() != 0) {
                zza(sb, 4);
                sb.append("sequence_filter_timestamps: {");
                int i8 = 0;
                for (zzcb.zzj zzjVar : zziVar.zzg()) {
                    int i9 = i8 + 1;
                    if (i8 != 0) {
                        sb.append(", ");
                    }
                    sb.append(zzjVar.zza() ? Integer.valueOf(zzjVar.zzb()) : null);
                    sb.append(": [");
                    int i10 = 0;
                    for (Long l3 : zzjVar.zzc()) {
                        long longValue = l3.longValue();
                        int i11 = i10 + 1;
                        if (i10 != 0) {
                            sb.append(", ");
                        }
                        sb.append(longValue);
                        i10 = i11;
                    }
                    sb.append("]");
                    i8 = i9;
                }
                sb.append("}\n");
            }
            zza(sb, 3);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzbt.zzd zzdVar) {
        if (zzdVar != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzdVar.zza()) {
                zza(sb, i, "comparison_type", zzdVar.zzb().name());
            }
            if (zzdVar.zzc()) {
                zza(sb, i, "match_as_float", Boolean.valueOf(zzdVar.zzd()));
            }
            if (zzdVar.zze()) {
                zza(sb, i, "comparison_value", zzdVar.zzf());
            }
            if (zzdVar.zzg()) {
                zza(sb, i, "min_comparison_value", zzdVar.zzh());
            }
            if (zzdVar.zzi()) {
                zza(sb, i, "max_comparison_value", zzdVar.zzj());
            }
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, zzbt.zzc zzcVar) {
        if (zzcVar != null) {
            zza(sb, i);
            sb.append("filter {\n");
            if (zzcVar.zze()) {
                zza(sb, i, "complement", Boolean.valueOf(zzcVar.zzf()));
            }
            if (zzcVar.zzg()) {
                zza(sb, i, "param_name", zzo().zzb(zzcVar.zzh()));
            }
            if (zzcVar.zza()) {
                int i2 = i + 1;
                zzbt.zzf zzb = zzcVar.zzb();
                if (zzb != null) {
                    zza(sb, i2);
                    sb.append("string_filter");
                    sb.append(" {\n");
                    if (zzb.zza()) {
                        zza(sb, i2, "match_type", zzb.zzb().name());
                    }
                    if (zzb.zzc()) {
                        zza(sb, i2, "expression", zzb.zzd());
                    }
                    if (zzb.zze()) {
                        zza(sb, i2, "case_sensitive", Boolean.valueOf(zzb.zzf()));
                    }
                    if (zzb.zzh() > 0) {
                        zza(sb, i2 + 1);
                        sb.append("expression_list {\n");
                        for (String str : zzb.zzg()) {
                            zza(sb, i2 + 2);
                            sb.append(str);
                            sb.append("\n");
                        }
                        sb.append("}\n");
                    }
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
            if (zzcVar.zzc()) {
                zza(sb, i + 1, "number_filter", zzcVar.zzd());
            }
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append('\n');
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final <T extends Parcelable> T zza(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            return creator.createFromParcel(obtain);
        } catch (SafeParcelReader.ParseException e) {
            zzr().zzf().zza("Failed to load parcelable from buffer");
            return null;
        } finally {
            obtain.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza(zzao zzaoVar, zzn zznVar) {
        Preconditions.checkNotNull(zzaoVar);
        Preconditions.checkNotNull(zznVar);
        if (!TextUtils.isEmpty(zznVar.zzb) || !TextUtils.isEmpty(zznVar.zzr)) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza(List<Long> list, int i) {
        if (i >= (list.size() << 6)) {
            return false;
        }
        return ((1 << (i % 64)) & list.get(i / 64).longValue()) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Long> zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 < bitSet.length()) {
                    if (bitSet.get(i3)) {
                        j |= 1 << i2;
                    }
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<Long> zza(List<Long> list, List<Integer> list2) {
        ArrayList arrayList = new ArrayList(list);
        for (Integer num : list2) {
            if (num.intValue() < 0) {
                zzr().zzi().zza("Ignoring negative bit index to be cleared", num);
            } else {
                int intValue = num.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    zzr().zzi().zza("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & ((1 << (num.intValue() % 64)) ^ (-1))));
                }
            }
        }
        int size = arrayList.size();
        int i = size;
        for (int size2 = arrayList.size() - 1; size2 >= 0 && ((Long) arrayList.get(size2)).longValue() == 0; size2--) {
            i = size2;
        }
        return arrayList.subList(0, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzm().currentTimeMillis() - j) > j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzp().zzd();
        MessageDigest zzi = zzkr.zzi();
        if (zzi != null) {
            return zzkr.zza(zzi.digest(bArr));
        }
        zzr().zzf().zza("Failed to get MD5");
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final byte[] zzb(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzr().zzf().zza("Failed to ungzip content", e);
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final byte[] zzc(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzr().zzf().zza("Failed to gzip content", e);
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<Integer> zzf() {
        Map<String, String> zza = zzaq.zza(this.zza.zzn());
        if (zza == null || zza.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int intValue = zzaq.zzao.zza(null).intValue();
        for (Map.Entry<String, String> entry : zza.entrySet()) {
            if (entry.getKey().startsWith("measurement.id.")) {
                try {
                    int parseInt = Integer.parseInt(entry.getValue());
                    if (parseInt != 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                        if (arrayList.size() >= intValue) {
                            zzr().zzi().zza("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                        continue;
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    zzr().zzi().zza("Experiment ID NumberFormatException", e);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <Builder extends zzgz> Builder zza(Builder builder, byte[] bArr) throws zzfw {
        zzfb zzb = zzfb.zzb();
        if (zzb != null) {
            return (Builder) builder.zza(bArr, zzb);
        }
        return (Builder) builder.zza(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzcb.zzg.zza zzaVar, String str) {
        if (zzaVar == null) {
            return -1;
        }
        for (int i = 0; i < zzaVar.zze(); i++) {
            if (str.equals(zzaVar.zzd(i).zzc())) {
                return i;
            }
        }
        return -1;
    }

    private static List<zzcb.zze> zza(Bundle[] bundleArr) {
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : bundleArr) {
            if (bundle != null) {
                zzcb.zze.zza zzm = zzcb.zze.zzm();
                for (String str : bundle.keySet()) {
                    zzcb.zze.zza zza = zzcb.zze.zzm().zza(str);
                    Object obj = bundle.get(str);
                    if (obj instanceof Long) {
                        zza.zza(((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        zza.zzb((String) obj);
                    } else if (obj instanceof Double) {
                        zza.zza(((Double) obj).doubleValue());
                    }
                    zzm.zza(zza);
                }
                if (zzm.zzd() > 0) {
                    arrayList.add((zzcb.zze) ((zzfo) zzm.zzv()));
                }
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzkn zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzo e_() {
        return super.e_();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzad zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzfs zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzai zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzes zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzkr zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfv zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzeu zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzfg zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzy zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzx zzu() {
        return super.zzu();
    }
}
