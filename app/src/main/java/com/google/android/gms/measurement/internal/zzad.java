package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzbt;
import com.google.android.gms.internal.measurement.zzcb;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzlm;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzad extends zzkg {
    private static final String[] zzb = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] zzc = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzd = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;"};
    private static final String[] zze = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zzf = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzkc zzk = new zzkc(zzm());
    private final zzae zzj = new zzae(this, zzn(), "google_app_measurement.db");

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzad(zzkj zzkjVar) {
        super(zzkjVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkg
    protected final boolean zze() {
        return false;
    }

    public final void zzf() {
        zzak();
        c_().beginTransaction();
    }

    public final void b_() {
        zzak();
        c_().setTransactionSuccessful();
    }

    public final void zzh() {
        zzak();
        c_().endTransaction();
    }

    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                cursor = c_().rawQuery(str, strArr);
                if (cursor.moveToFirst()) {
                    return cursor.getLong(0);
                }
                throw new SQLiteException("Database returned empty set");
            } catch (SQLiteException e) {
                zzr().zzf().zza("Database error", str, e);
                throw e;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            try {
                cursor = c_().rawQuery(str, strArr);
                if (cursor.moveToFirst()) {
                    return cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            } catch (SQLiteException e) {
                zzr().zzf().zza("Database error", str, e);
                throw e;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SQLiteDatabase c_() {
        zzd();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e) {
            zzr().zzi().zza("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x016f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzak zza(java.lang.String r28, java.lang.String r29) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzak");
    }

    public final void zza(zzak zzakVar) {
        Long l;
        Preconditions.checkNotNull(zzakVar);
        zzd();
        zzak();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzakVar.zza);
        contentValues.put("name", zzakVar.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzakVar.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzakVar.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzakVar.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzakVar.zzg));
        contentValues.put("last_bundled_day", zzakVar.zzh);
        contentValues.put("last_sampled_complex_event_id", zzakVar.zzi);
        contentValues.put("last_sampling_rate", zzakVar.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzakVar.zze));
        if (zzakVar.zzk == null || !zzakVar.zzk.booleanValue()) {
            l = null;
        } else {
            l = 1L;
        }
        contentValues.put("last_exempt_from_sampling", l);
        try {
            if (c_().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update event aggregates (got -1). appId", zzeu.zza(zzakVar.zza));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing event aggregates. appId", zzeu.zza(zzakVar.zza), e);
        }
    }

    public final void zzb(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        try {
            c_().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error deleting user property. appId", zzeu.zza(str), zzo().zzc(str2), e);
        }
    }

    public final boolean zza(zzks zzksVar) {
        Preconditions.checkNotNull(zzksVar);
        zzd();
        zzak();
        if (zzc(zzksVar.zza, zzksVar.zzc) == null) {
            if (zzkr.zza(zzksVar.zzc)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzksVar.zza}) >= zzt().zzd(zzksVar.zza)) {
                    return false;
                }
            } else if (!"_npa".equals(zzksVar.zzc) && zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzksVar.zza, zzksVar.zzb}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzksVar.zza);
        contentValues.put("origin", zzksVar.zzb);
        contentValues.put("name", zzksVar.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzksVar.zzd));
        zza(contentValues, "value", zzksVar.zze);
        try {
            if (c_().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update user property (got -1). appId", zzeu.zza(zzksVar.zza));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing user property. appId", zzeu.zza(zzksVar.zza), e);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzks zzc(java.lang.String r19, java.lang.String r20) {
        /*
            r18 = this;
            r8 = r20
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r19)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            r18.zzd()
            r18.zzak()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r18.c_()     // Catch: all -> 0x0086, SQLiteException -> 0x008a
            java.lang.String r11 = "user_attributes"
            r0 = 3
            java.lang.String[] r12 = new java.lang.String[r0]     // Catch: all -> 0x0086, SQLiteException -> 0x008a
            java.lang.String r0 = "set_timestamp"
            r1 = 0
            r12[r1] = r0     // Catch: all -> 0x0086, SQLiteException -> 0x008a
            java.lang.String r0 = "value"
            r2 = 1
            r12[r2] = r0     // Catch: all -> 0x0086, SQLiteException -> 0x008a
            java.lang.String r0 = "origin"
            r3 = 2
            r12[r3] = r0     // Catch: all -> 0x0086, SQLiteException -> 0x008a
            java.lang.String r13 = "app_id=? and name=?"
            java.lang.String[] r14 = new java.lang.String[r3]     // Catch: all -> 0x0086, SQLiteException -> 0x008a
            r14[r1] = r19     // Catch: all -> 0x0086, SQLiteException -> 0x008a
            r14[r2] = r8     // Catch: all -> 0x0086, SQLiteException -> 0x008a
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch: all -> 0x0086, SQLiteException -> 0x008a
            boolean r0 = r10.moveToFirst()     // Catch: all -> 0x007e, SQLiteException -> 0x0082
            if (r0 != 0) goto L_0x0046
            if (r10 == 0) goto L_0x0045
            r10.close()
        L_0x0045:
            return r9
        L_0x0046:
            long r5 = r10.getLong(r1)     // Catch: all -> 0x007e, SQLiteException -> 0x0082
            r11 = r18
            java.lang.Object r7 = r11.zza(r10, r2)     // Catch: SQLiteException -> 0x007c, all -> 0x00ad
            java.lang.String r3 = r10.getString(r3)     // Catch: SQLiteException -> 0x007c, all -> 0x00ad
            com.google.android.gms.measurement.internal.zzks r0 = new com.google.android.gms.measurement.internal.zzks     // Catch: SQLiteException -> 0x007c, all -> 0x00ad
            r1 = r0
            r2 = r19
            r4 = r20
            r1.<init>(r2, r3, r4, r5, r7)     // Catch: SQLiteException -> 0x007c, all -> 0x00ad
            boolean r1 = r10.moveToNext()     // Catch: SQLiteException -> 0x007c, all -> 0x00ad
            if (r1 == 0) goto L_0x0075
            com.google.android.gms.measurement.internal.zzeu r1 = r18.zzr()     // Catch: SQLiteException -> 0x007c, all -> 0x00ad
            com.google.android.gms.measurement.internal.zzew r1 = r1.zzf()     // Catch: SQLiteException -> 0x007c, all -> 0x00ad
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzeu.zza(r19)     // Catch: SQLiteException -> 0x007c, all -> 0x00ad
            r1.zza(r2, r3)     // Catch: SQLiteException -> 0x007c, all -> 0x00ad
        L_0x0075:
            if (r10 == 0) goto L_0x007b
            r10.close()
        L_0x007b:
            return r0
        L_0x007c:
            r0 = move-exception
            goto L_0x008e
        L_0x007e:
            r0 = move-exception
            r11 = r18
            goto L_0x00ae
        L_0x0082:
            r0 = move-exception
            r11 = r18
            goto L_0x008e
        L_0x0086:
            r0 = move-exception
            r11 = r18
            goto L_0x00af
        L_0x008a:
            r0 = move-exception
            r11 = r18
            r10 = r9
        L_0x008e:
            com.google.android.gms.measurement.internal.zzeu r1 = r18.zzr()     // Catch: all -> 0x00ad
            com.google.android.gms.measurement.internal.zzew r1 = r1.zzf()     // Catch: all -> 0x00ad
            java.lang.String r2 = "Error querying user property. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzeu.zza(r19)     // Catch: all -> 0x00ad
            com.google.android.gms.measurement.internal.zzes r4 = r18.zzo()     // Catch: all -> 0x00ad
            java.lang.String r4 = r4.zzc(r8)     // Catch: all -> 0x00ad
            r1.zza(r2, r3, r4, r0)     // Catch: all -> 0x00ad
            if (r10 == 0) goto L_0x00ac
            r10.close()
        L_0x00ac:
            return r9
        L_0x00ad:
            r0 = move-exception
        L_0x00ae:
            r9 = r10
        L_0x00af:
            if (r9 == 0) goto L_0x00b4
            r9.close()
        L_0x00b4:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzc(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzks");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzks> zza(java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x009b, code lost:
        zzr().zzf().zza("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x013e A[Catch: all -> 0x015b, TryCatch #5 {all -> 0x015b, blocks: (B:22:0x00b9, B:24:0x00c3, B:26:0x00de, B:28:0x00ed, B:29:0x00f3, B:51:0x0127, B:53:0x013e, B:55:0x014a), top: B:71:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x015f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzks> zza(java.lang.String r21, java.lang.String r22, java.lang.String r23) {
        /*
            Method dump skipped, instructions count: 357
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public final boolean zza(zzw zzwVar) {
        Preconditions.checkNotNull(zzwVar);
        zzd();
        zzak();
        if (zzc(zzwVar.zza, zzwVar.zzc.zza) == null && zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzwVar.zza}) >= 1000) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzwVar.zza);
        contentValues.put("origin", zzwVar.zzb);
        contentValues.put("name", zzwVar.zzc.zza);
        zza(contentValues, "value", zzwVar.zzc.zza());
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzwVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzwVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzwVar.zzh));
        zzp();
        contentValues.put("timed_out_event", zzkr.zza((Parcelable) zzwVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzwVar.zzd));
        zzp();
        contentValues.put("triggered_event", zzkr.zza((Parcelable) zzwVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzwVar.zzc.zzb));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzwVar.zzj));
        zzp();
        contentValues.put("expired_event", zzkr.zza((Parcelable) zzwVar.zzk));
        try {
            if (c_().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update conditional user property (got -1)", zzeu.zza(zzwVar.zza));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing conditional user property", zzeu.zza(zzwVar.zza), e);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x015b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzw zzd(java.lang.String r33, java.lang.String r34) {
        /*
            Method dump skipped, instructions count: 351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzd(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzw");
    }

    public final int zze(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        try {
            return c_().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error deleting conditional property", zzeu.zza(str), zzo().zzc(str2), e);
            return 0;
        }
    }

    public final List<zzw> zzb(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzd();
        zzak();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zza(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0090, code lost:
        zzr().zzf().zza("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x017b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzw> zza(java.lang.String r40, java.lang.String[] r41) {
        /*
            Method dump skipped, instructions count: 385
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, java.lang.String[]):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0198 A[Catch: SQLiteException -> 0x027b, all -> 0x02a4, TryCatch #1 {all -> 0x02a4, blocks: (B:11:0x00ec, B:13:0x0149, B:18:0x0153, B:21:0x0198, B:22:0x019d, B:24:0x01d0, B:27:0x01db, B:28:0x01df, B:29:0x01e2, B:31:0x01ea, B:36:0x01f4, B:38:0x01ff, B:41:0x0206, B:44:0x021d, B:45:0x0221, B:47:0x022d, B:48:0x023f, B:50:0x0245, B:52:0x0251, B:53:0x025a, B:55:0x0263, B:68:0x028d), top: B:77:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01d0 A[Catch: SQLiteException -> 0x027b, all -> 0x02a4, TryCatch #1 {all -> 0x02a4, blocks: (B:11:0x00ec, B:13:0x0149, B:18:0x0153, B:21:0x0198, B:22:0x019d, B:24:0x01d0, B:27:0x01db, B:28:0x01df, B:29:0x01e2, B:31:0x01ea, B:36:0x01f4, B:38:0x01ff, B:41:0x0206, B:44:0x021d, B:45:0x0221, B:47:0x022d, B:48:0x023f, B:50:0x0245, B:52:0x0251, B:53:0x025a, B:55:0x0263, B:68:0x028d), top: B:77:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01ff A[Catch: SQLiteException -> 0x027b, all -> 0x02a4, TryCatch #1 {all -> 0x02a4, blocks: (B:11:0x00ec, B:13:0x0149, B:18:0x0153, B:21:0x0198, B:22:0x019d, B:24:0x01d0, B:27:0x01db, B:28:0x01df, B:29:0x01e2, B:31:0x01ea, B:36:0x01f4, B:38:0x01ff, B:41:0x0206, B:44:0x021d, B:45:0x0221, B:47:0x022d, B:48:0x023f, B:50:0x0245, B:52:0x0251, B:53:0x025a, B:55:0x0263, B:68:0x028d), top: B:77:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x021d A[Catch: SQLiteException -> 0x027b, all -> 0x02a4, TryCatch #1 {all -> 0x02a4, blocks: (B:11:0x00ec, B:13:0x0149, B:18:0x0153, B:21:0x0198, B:22:0x019d, B:24:0x01d0, B:27:0x01db, B:28:0x01df, B:29:0x01e2, B:31:0x01ea, B:36:0x01f4, B:38:0x01ff, B:41:0x0206, B:44:0x021d, B:45:0x0221, B:47:0x022d, B:48:0x023f, B:50:0x0245, B:52:0x0251, B:53:0x025a, B:55:0x0263, B:68:0x028d), top: B:77:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x022d A[Catch: SQLiteException -> 0x027b, all -> 0x02a4, TryCatch #1 {all -> 0x02a4, blocks: (B:11:0x00ec, B:13:0x0149, B:18:0x0153, B:21:0x0198, B:22:0x019d, B:24:0x01d0, B:27:0x01db, B:28:0x01df, B:29:0x01e2, B:31:0x01ea, B:36:0x01f4, B:38:0x01ff, B:41:0x0206, B:44:0x021d, B:45:0x0221, B:47:0x022d, B:48:0x023f, B:50:0x0245, B:52:0x0251, B:53:0x025a, B:55:0x0263, B:68:0x028d), top: B:77:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0245 A[Catch: SQLiteException -> 0x027b, all -> 0x02a4, TryCatch #1 {all -> 0x02a4, blocks: (B:11:0x00ec, B:13:0x0149, B:18:0x0153, B:21:0x0198, B:22:0x019d, B:24:0x01d0, B:27:0x01db, B:28:0x01df, B:29:0x01e2, B:31:0x01ea, B:36:0x01f4, B:38:0x01ff, B:41:0x0206, B:44:0x021d, B:45:0x0221, B:47:0x022d, B:48:0x023f, B:50:0x0245, B:52:0x0251, B:53:0x025a, B:55:0x0263, B:68:0x028d), top: B:77:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0263 A[Catch: SQLiteException -> 0x027b, all -> 0x02a4, TRY_LEAVE, TryCatch #1 {all -> 0x02a4, blocks: (B:11:0x00ec, B:13:0x0149, B:18:0x0153, B:21:0x0198, B:22:0x019d, B:24:0x01d0, B:27:0x01db, B:28:0x01df, B:29:0x01e2, B:31:0x01ea, B:36:0x01f4, B:38:0x01ff, B:41:0x0206, B:44:0x021d, B:45:0x0221, B:47:0x022d, B:48:0x023f, B:50:0x0245, B:52:0x0251, B:53:0x025a, B:55:0x0263, B:68:0x028d), top: B:77:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzf zzb(java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 684
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzb(java.lang.String):com.google.android.gms.measurement.internal.zzf");
    }

    public final void zza(zzf zzfVar) {
        Preconditions.checkNotNull(zzfVar);
        zzd();
        zzak();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzfVar.zzc());
        contentValues.put("app_instance_id", zzfVar.zzd());
        contentValues.put("gmp_app_id", zzfVar.zze());
        contentValues.put("resettable_device_id_hash", zzfVar.zzh());
        contentValues.put("last_bundle_index", Long.valueOf(zzfVar.zzs()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzfVar.zzj()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzfVar.zzk()));
        contentValues.put("app_version", zzfVar.zzl());
        contentValues.put("app_store", zzfVar.zzn());
        contentValues.put("gmp_version", Long.valueOf(zzfVar.zzo()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzfVar.zzp()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzfVar.zzr()));
        contentValues.put("day", Long.valueOf(zzfVar.zzw()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzfVar.zzx()));
        contentValues.put("daily_events_count", Long.valueOf(zzfVar.zzy()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzfVar.zzz()));
        contentValues.put("config_fetched_time", Long.valueOf(zzfVar.zzt()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzfVar.zzu()));
        contentValues.put("app_version_int", Long.valueOf(zzfVar.zzm()));
        contentValues.put("firebase_instance_id", zzfVar.zzi());
        contentValues.put("daily_error_events_count", Long.valueOf(zzfVar.zzab()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzfVar.zzaa()));
        contentValues.put("health_monitor_sample", zzfVar.zzac());
        contentValues.put("android_id", Long.valueOf(zzfVar.zzae()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzfVar.zzaf()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzfVar.zzag()));
        contentValues.put("admob_app_id", zzfVar.zzf());
        contentValues.put("dynamite_version", Long.valueOf(zzfVar.zzq()));
        if (zzfVar.zzai() != null) {
            if (zzfVar.zzai().size() == 0) {
                zzr().zzi().zza("Safelisted events should not be an empty list. appId", zzfVar.zzc());
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzfVar.zzai()));
            }
        }
        if (zzlm.zzb() && zzt().zze(zzfVar.zzc(), zzaq.zzbn)) {
            contentValues.put("ga_app_id", zzfVar.zzg());
        }
        try {
            SQLiteDatabase c_ = c_();
            if (c_.update("apps", contentValues, "app_id = ?", new String[]{zzfVar.zzc()}) == 0 && c_.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update app (got -1). appId", zzeu.zza(zzfVar.zzc()));
            }
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing app. appId", zzeu.zza(zzfVar.zzc()), e);
        }
    }

    public final long zzc(String str) {
        Preconditions.checkNotEmpty(str);
        zzd();
        zzak();
        try {
            return c_().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzt().zzb(str, zzaq.zzo))))});
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error deleting over the limit events. appId", zzeu.zza(str), e);
            return 0L;
        }
    }

    public final zzac zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return zza(j, str, 1L, false, false, z3, false, z5);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0131  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzac zza(long r22, java.lang.String r24, long r25, boolean r27, boolean r28, boolean r29, boolean r30, boolean r31) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(long, java.lang.String, long, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.measurement.internal.zzac");
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0079: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:24:0x0079 */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] zzd(java.lang.String r12) {
        /*
            r11 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r11.zzd()
            r11.zzak()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r11.c_()     // Catch: all -> 0x005d, SQLiteException -> 0x005f
            java.lang.String r2 = "apps"
            r3 = 1
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch: all -> 0x005d, SQLiteException -> 0x005f
            java.lang.String r5 = "remote_config"
            r9 = 0
            r4[r9] = r5     // Catch: all -> 0x005d, SQLiteException -> 0x005f
            java.lang.String r5 = "app_id=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: all -> 0x005d, SQLiteException -> 0x005f
            r6[r9] = r12     // Catch: all -> 0x005d, SQLiteException -> 0x005f
            r7 = 0
            r8 = 0
            r10 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r10
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: all -> 0x005d, SQLiteException -> 0x005f
            boolean r2 = r1.moveToFirst()     // Catch: SQLiteException -> 0x005b, all -> 0x0078
            if (r2 != 0) goto L_0x0039
            if (r1 == 0) goto L_0x0038
            r1.close()
        L_0x0038:
            return r0
        L_0x0039:
            byte[] r2 = r1.getBlob(r9)     // Catch: SQLiteException -> 0x005b, all -> 0x0078
            boolean r3 = r1.moveToNext()     // Catch: SQLiteException -> 0x005b, all -> 0x0078
            if (r3 == 0) goto L_0x0054
            com.google.android.gms.measurement.internal.zzeu r3 = r11.zzr()     // Catch: SQLiteException -> 0x005b, all -> 0x0078
            com.google.android.gms.measurement.internal.zzew r3 = r3.zzf()     // Catch: SQLiteException -> 0x005b, all -> 0x0078
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzeu.zza(r12)     // Catch: SQLiteException -> 0x005b, all -> 0x0078
            r3.zza(r4, r5)     // Catch: SQLiteException -> 0x005b, all -> 0x0078
        L_0x0054:
            if (r1 == 0) goto L_0x005a
            r1.close()
        L_0x005a:
            return r2
        L_0x005b:
            r2 = move-exception
            goto L_0x0061
        L_0x005d:
            r12 = move-exception
            goto L_0x007a
        L_0x005f:
            r2 = move-exception
            r1 = r0
        L_0x0061:
            com.google.android.gms.measurement.internal.zzeu r3 = r11.zzr()     // Catch: all -> 0x0078
            com.google.android.gms.measurement.internal.zzew r3 = r3.zzf()     // Catch: all -> 0x0078
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzeu.zza(r12)     // Catch: all -> 0x0078
            r3.zza(r4, r12, r2)     // Catch: all -> 0x0078
            if (r1 == 0) goto L_0x0077
            r1.close()
        L_0x0077:
            return r0
        L_0x0078:
            r12 = move-exception
            r0 = r1
        L_0x007a:
            if (r0 == 0) goto L_0x007f
            r0.close()
        L_0x007f:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzd(java.lang.String):byte[]");
    }

    public final boolean zza(zzcb.zzg zzgVar, boolean z) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzgVar);
        Preconditions.checkNotEmpty(zzgVar.zzx());
        Preconditions.checkState(zzgVar.zzk());
        zzv();
        long currentTimeMillis = zzm().currentTimeMillis();
        if (zzgVar.zzl() < currentTimeMillis - zzy.zzk() || zzgVar.zzl() > zzy.zzk() + currentTimeMillis) {
            zzr().zzi().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzeu.zza(zzgVar.zzx()), Long.valueOf(currentTimeMillis), Long.valueOf(zzgVar.zzl()));
        }
        try {
            byte[] zzc2 = zzg().zzc(zzgVar.zzbi());
            zzr().zzx().zza("Saving bundle, size", Integer.valueOf(zzc2.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzgVar.zzx());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzgVar.zzl()));
            contentValues.put("data", zzc2);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzgVar.zzaz()) {
                contentValues.put("retry_count", Integer.valueOf(zzgVar.zzba()));
            }
            try {
                if (c_().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzr().zzf().zza("Failed to insert bundle (got -1). appId", zzeu.zza(zzgVar.zzx()));
                return false;
            } catch (SQLiteException e) {
                zzr().zzf().zza("Error storing bundle. appId", zzeu.zza(zzgVar.zzx()), e);
                return false;
            }
        } catch (IOException e2) {
            zzr().zzf().zza("Data loss. Failed to serialize bundle. appId", zzeu.zza(zzgVar.zzx()), e2);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String d_() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.c_()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch: all -> 0x0026, SQLiteException -> 0x002b
            boolean r2 = r0.moveToFirst()     // Catch: SQLiteException -> 0x0024, all -> 0x0040
            if (r2 == 0) goto L_0x001d
            r2 = 0
            java.lang.String r1 = r0.getString(r2)     // Catch: SQLiteException -> 0x0024, all -> 0x0040
            if (r0 == 0) goto L_0x001c
            r0.close()
        L_0x001c:
            return r1
        L_0x001d:
            if (r0 == 0) goto L_0x0023
            r0.close()
        L_0x0023:
            return r1
        L_0x0024:
            r2 = move-exception
            goto L_0x002d
        L_0x0026:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0041
        L_0x002b:
            r2 = move-exception
            r0 = r1
        L_0x002d:
            com.google.android.gms.measurement.internal.zzeu r3 = r6.zzr()     // Catch: all -> 0x0040
            com.google.android.gms.measurement.internal.zzew r3 = r3.zzf()     // Catch: all -> 0x0040
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zza(r4, r2)     // Catch: all -> 0x0040
            if (r0 == 0) goto L_0x003f
            r0.close()
        L_0x003f:
            return r1
        L_0x0040:
            r1 = move-exception
        L_0x0041:
            if (r0 == 0) goto L_0x0046
            r0.close()
        L_0x0046:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.d_():java.lang.String");
    }

    public final boolean zzk() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    public final List<Pair<zzcb.zzg, Long>> zza(String str, int i, int i2) {
        byte[] zzb2;
        zzd();
        zzak();
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i2 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            try {
                Cursor query = c_().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
                if (!query.moveToFirst()) {
                    List<Pair<zzcb.zzg, Long>> emptyList = Collections.emptyList();
                    if (query != null) {
                        query.close();
                    }
                    return emptyList;
                }
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                do {
                    long j = query.getLong(0);
                    try {
                        zzb2 = zzg().zzb(query.getBlob(1));
                    } catch (IOException e) {
                        zzr().zzf().zza("Failed to unzip queued bundle. appId", zzeu.zza(str), e);
                    }
                    if (!arrayList.isEmpty() && zzb2.length + i3 > i2) {
                        break;
                    }
                    try {
                        zzcb.zzg.zza zzaVar = (zzcb.zzg.zza) zzkn.zza(zzcb.zzg.zzbf(), zzb2);
                        if (!query.isNull(2)) {
                            zzaVar.zzi(query.getInt(2));
                        }
                        i3 += zzb2.length;
                        arrayList.add(Pair.create((zzcb.zzg) ((zzfo) zzaVar.zzv()), Long.valueOf(j)));
                    } catch (IOException e2) {
                        zzr().zzf().zza("Failed to merge queued bundle. appId", zzeu.zza(str), e2);
                    }
                    if (!query.moveToNext()) {
                        break;
                    }
                } while (i3 <= i2);
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e3) {
                zzr().zzf().zza("Error querying bundles. appId", zzeu.zza(str), e3);
                List<Pair<zzcb.zzg, Long>> emptyList2 = Collections.emptyList();
                if (0 != 0) {
                    cursor.close();
                }
                return emptyList2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzv() {
        int delete;
        zzd();
        zzak();
        if (zzam()) {
            long zza = zzs().zzf.zza();
            long elapsedRealtime = zzm().elapsedRealtime();
            if (Math.abs(elapsedRealtime - zza) > zzaq.zzx.zza(null).longValue()) {
                zzs().zzf.zza(elapsedRealtime);
                zzd();
                zzak();
                if (zzam() && (delete = c_().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzm().currentTimeMillis()), String.valueOf(zzy.zzk())})) > 0) {
                    zzr().zzx().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(List<Long> list) {
        zzd();
        zzak();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzam()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zzb(sb3.toString(), (String[]) null) > 0) {
                zzr().zzi().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase c_ = c_();
                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + 127);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                c_.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                zzr().zzf().zza("Error incrementing retry count. error", e);
            }
        }
    }

    private final boolean zza(String str, int i, zzbt.zzb zzbVar) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbVar);
        Integer num = null;
        if (TextUtils.isEmpty(zzbVar.zzc())) {
            zzew zzi2 = zzr().zzi();
            Object zza = zzeu.zza(str);
            Integer valueOf = Integer.valueOf(i);
            if (zzbVar.zza()) {
                num = Integer.valueOf(zzbVar.zzb());
            }
            zzi2.zza("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zza, valueOf, String.valueOf(num));
            return false;
        }
        byte[] zzbi = zzbVar.zzbi();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzbVar.zza() ? Integer.valueOf(zzbVar.zzb()) : null);
        contentValues.put("event_name", zzbVar.zzc());
        contentValues.put("session_scoped", zzbVar.zzj() ? Boolean.valueOf(zzbVar.zzk()) : null);
        contentValues.put("data", zzbi);
        try {
            if (c_().insertWithOnConflict("event_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert event filter (got -1). appId", zzeu.zza(str));
            return true;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing event filter. appId", zzeu.zza(str), e);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzbt.zze zzeVar) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzeVar);
        Integer num = null;
        if (TextUtils.isEmpty(zzeVar.zzc())) {
            zzew zzi2 = zzr().zzi();
            Object zza = zzeu.zza(str);
            Integer valueOf = Integer.valueOf(i);
            if (zzeVar.zza()) {
                num = Integer.valueOf(zzeVar.zzb());
            }
            zzi2.zza("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zza, valueOf, String.valueOf(num));
            return false;
        }
        byte[] zzbi = zzeVar.zzbi();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzeVar.zza() ? Integer.valueOf(zzeVar.zzb()) : null);
        contentValues.put("property_name", zzeVar.zzc());
        contentValues.put("session_scoped", zzeVar.zzg() ? Boolean.valueOf(zzeVar.zzh()) : null);
        contentValues.put("data", zzbi);
        try {
            if (c_().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert property filter (got -1). appId", zzeu.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing property filter. appId", zzeu.zza(str), e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00dd  */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbt.zzb>> zzf(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzak()
            r12.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.c_()
            r9 = 0
            java.lang.String r2 = "event_filters"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            java.lang.String r5 = "audience_id"
            r10 = 0
            r4[r10] = r5     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            java.lang.String r5 = "data"
            r11 = 1
            r4[r11] = r5     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            java.lang.String r5 = "app_id=? AND event_name=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            r6[r10] = r13     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            r6[r11] = r14     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            r14 = 0
            r7 = 0
            r8 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r14
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            boolean r1 = r14.moveToFirst()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            if (r1 != 0) goto L_0x0049
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            if (r14 == 0) goto L_0x0048
            r14.close()
        L_0x0048:
            return r13
        L_0x0049:
            byte[] r1 = r14.getBlob(r11)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzbt$zzb$zza r2 = com.google.android.gms.internal.measurement.zzbt.zzb.zzl()     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzgz r1 = com.google.android.gms.measurement.internal.zzkn.zza(r2, r1)     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzbt$zzb$zza r1 = (com.google.android.gms.internal.measurement.zzbt.zzb.zza) r1     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzgw r1 = r1.zzv()     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzfo r1 = (com.google.android.gms.internal.measurement.zzfo) r1     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzbt$zzb r1 = (com.google.android.gms.internal.measurement.zzbt.zzb) r1     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            int r2 = r14.getInt(r10)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            java.lang.Object r3 = r0.get(r3)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            java.util.List r3 = (java.util.List) r3     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            if (r3 != 0) goto L_0x007c
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            r3.<init>()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            r0.put(r2, r3)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
        L_0x007c:
            r3.add(r1)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            goto L_0x0093
        L_0x0080:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzeu r2 = r12.zzr()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.measurement.internal.zzew r2 = r2.zzf()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzeu.zza(r13)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            r2.zza(r3, r4, r1)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
        L_0x0093:
            boolean r1 = r14.moveToNext()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            if (r1 != 0) goto L_0x0049
            if (r14 == 0) goto L_0x009e
            r14.close()
        L_0x009e:
            return r0
        L_0x009f:
            r0 = move-exception
            goto L_0x00a5
        L_0x00a1:
            r13 = move-exception
            goto L_0x00db
        L_0x00a3:
            r0 = move-exception
            r14 = r9
        L_0x00a5:
            com.google.android.gms.measurement.internal.zzeu r1 = r12.zzr()     // Catch: all -> 0x00d9
            com.google.android.gms.measurement.internal.zzew r1 = r1.zzf()     // Catch: all -> 0x00d9
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzeu.zza(r13)     // Catch: all -> 0x00d9
            r1.zza(r2, r3, r0)     // Catch: all -> 0x00d9
            boolean r0 = com.google.android.gms.internal.measurement.zzku.zzb()     // Catch: all -> 0x00d9
            if (r0 == 0) goto L_0x00d2
            com.google.android.gms.measurement.internal.zzy r0 = r12.zzt()     // Catch: all -> 0x00d9
            com.google.android.gms.measurement.internal.zzen<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzaq.zzcs     // Catch: all -> 0x00d9
            boolean r13 = r0.zze(r13, r1)     // Catch: all -> 0x00d9
            if (r13 == 0) goto L_0x00d2
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch: all -> 0x00d9
            if (r14 == 0) goto L_0x00d1
            r14.close()
        L_0x00d1:
            return r13
        L_0x00d2:
            if (r14 == 0) goto L_0x00d8
            r14.close()
        L_0x00d8:
            return r9
        L_0x00d9:
            r13 = move-exception
            r9 = r14
        L_0x00db:
            if (r9 == 0) goto L_0x00e0
            r9.close()
        L_0x00e0:
            goto L_0x00e2
        L_0x00e1:
            throw r13
        L_0x00e2:
            goto L_0x00e1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzf(java.lang.String, java.lang.String):java.util.Map");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map<Integer, List<zzbt.zzb>> zze(String str) {
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            try {
                Cursor query = c_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str}, null, null, null);
                if (!query.moveToFirst()) {
                    Map<Integer, List<zzbt.zzb>> emptyMap = Collections.emptyMap();
                    if (query != null) {
                        query.close();
                    }
                    return emptyMap;
                }
                do {
                    try {
                        zzbt.zzb zzbVar = (zzbt.zzb) ((zzfo) ((zzbt.zzb.zza) zzkn.zza(zzbt.zzb.zzl(), query.getBlob(1))).zzv());
                        if (zzbVar.zzf()) {
                            int i = query.getInt(0);
                            List list = (List) arrayMap.get(Integer.valueOf(i));
                            if (list == null) {
                                list = new ArrayList();
                                arrayMap.put(Integer.valueOf(i), list);
                            }
                            list.add(zzbVar);
                        }
                    } catch (IOException e) {
                        zzr().zzf().zza("Failed to merge filter. appId", zzeu.zza(str), e);
                    }
                } while (query.moveToNext());
                if (query != null) {
                    query.close();
                }
                return arrayMap;
            } catch (SQLiteException e2) {
                zzr().zzf().zza("Database error querying filters. appId", zzeu.zza(str), e2);
                Map<Integer, List<zzbt.zzb>> emptyMap2 = Collections.emptyMap();
                if (0 != 0) {
                    cursor.close();
                }
                return emptyMap2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00dd  */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbt.zze>> zzg(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzak()
            r12.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.c_()
            r9 = 0
            java.lang.String r2 = "property_filters"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            java.lang.String r5 = "audience_id"
            r10 = 0
            r4[r10] = r5     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            java.lang.String r5 = "data"
            r11 = 1
            r4[r11] = r5     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            java.lang.String r5 = "app_id=? AND property_name=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            r6[r10] = r13     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            r6[r11] = r14     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            r14 = 0
            r7 = 0
            r8 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r14
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: all -> 0x00a1, SQLiteException -> 0x00a3
            boolean r1 = r14.moveToFirst()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            if (r1 != 0) goto L_0x0049
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            if (r14 == 0) goto L_0x0048
            r14.close()
        L_0x0048:
            return r13
        L_0x0049:
            byte[] r1 = r14.getBlob(r11)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzbt$zze$zza r2 = com.google.android.gms.internal.measurement.zzbt.zze.zzi()     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzgz r1 = com.google.android.gms.measurement.internal.zzkn.zza(r2, r1)     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzbt$zze$zza r1 = (com.google.android.gms.internal.measurement.zzbt.zze.zza) r1     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzgw r1 = r1.zzv()     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzfo r1 = (com.google.android.gms.internal.measurement.zzfo) r1     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzbt$zze r1 = (com.google.android.gms.internal.measurement.zzbt.zze) r1     // Catch: IOException -> 0x0080, SQLiteException -> 0x009f, all -> 0x00d9
            int r2 = r14.getInt(r10)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            java.lang.Object r3 = r0.get(r3)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            java.util.List r3 = (java.util.List) r3     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            if (r3 != 0) goto L_0x007c
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            r3.<init>()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            r0.put(r2, r3)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
        L_0x007c:
            r3.add(r1)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            goto L_0x0093
        L_0x0080:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzeu r2 = r12.zzr()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            com.google.android.gms.measurement.internal.zzew r2 = r2.zzf()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzeu.zza(r13)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            r2.zza(r3, r4, r1)     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
        L_0x0093:
            boolean r1 = r14.moveToNext()     // Catch: SQLiteException -> 0x009f, all -> 0x00d9
            if (r1 != 0) goto L_0x0049
            if (r14 == 0) goto L_0x009e
            r14.close()
        L_0x009e:
            return r0
        L_0x009f:
            r0 = move-exception
            goto L_0x00a5
        L_0x00a1:
            r13 = move-exception
            goto L_0x00db
        L_0x00a3:
            r0 = move-exception
            r14 = r9
        L_0x00a5:
            com.google.android.gms.measurement.internal.zzeu r1 = r12.zzr()     // Catch: all -> 0x00d9
            com.google.android.gms.measurement.internal.zzew r1 = r1.zzf()     // Catch: all -> 0x00d9
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzeu.zza(r13)     // Catch: all -> 0x00d9
            r1.zza(r2, r3, r0)     // Catch: all -> 0x00d9
            boolean r0 = com.google.android.gms.internal.measurement.zzku.zzb()     // Catch: all -> 0x00d9
            if (r0 == 0) goto L_0x00d2
            com.google.android.gms.measurement.internal.zzy r0 = r12.zzt()     // Catch: all -> 0x00d9
            com.google.android.gms.measurement.internal.zzen<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzaq.zzcs     // Catch: all -> 0x00d9
            boolean r13 = r0.zze(r13, r1)     // Catch: all -> 0x00d9
            if (r13 == 0) goto L_0x00d2
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch: all -> 0x00d9
            if (r14 == 0) goto L_0x00d1
            r14.close()
        L_0x00d1:
            return r13
        L_0x00d2:
            if (r14 == 0) goto L_0x00d8
            r14.close()
        L_0x00d8:
            return r9
        L_0x00d9:
            r13 = move-exception
            r9 = r14
        L_0x00db:
            if (r9 == 0) goto L_0x00e0
            r9.close()
        L_0x00e0:
            goto L_0x00e2
        L_0x00e1:
            throw r13
        L_0x00e2:
            goto L_0x00e1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzg(java.lang.String, java.lang.String):java.util.Map");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a4  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Map<java.lang.Integer, java.util.List<java.lang.Integer>> zzf(java.lang.String r8) {
        /*
            r7 = this;
            r7.zzak()
            r7.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r7.c_()
            r2 = 0
            java.lang.String r3 = "select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: all -> 0x0068, SQLiteException -> 0x006a
            r5 = 0
            r4[r5] = r8     // Catch: all -> 0x0068, SQLiteException -> 0x006a
            r6 = 1
            r4[r6] = r8     // Catch: all -> 0x0068, SQLiteException -> 0x006a
            android.database.Cursor r1 = r1.rawQuery(r3, r4)     // Catch: all -> 0x0068, SQLiteException -> 0x006a
            boolean r3 = r1.moveToFirst()     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            if (r3 != 0) goto L_0x0033
            java.util.Map r8 = java.util.Collections.emptyMap()     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            if (r1 == 0) goto L_0x0032
            r1.close()
        L_0x0032:
            return r8
        L_0x0033:
            int r3 = r1.getInt(r5)     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            java.lang.Object r4 = r0.get(r4)     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            java.util.List r4 = (java.util.List) r4     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            if (r4 != 0) goto L_0x004f
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            r4.<init>()     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            r0.put(r3, r4)     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
        L_0x004f:
            int r3 = r1.getInt(r6)     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            r4.add(r3)     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            boolean r3 = r1.moveToNext()     // Catch: SQLiteException -> 0x0066, all -> 0x00a0
            if (r3 != 0) goto L_0x0033
            if (r1 == 0) goto L_0x0065
            r1.close()
        L_0x0065:
            return r0
        L_0x0066:
            r0 = move-exception
            goto L_0x006c
        L_0x0068:
            r8 = move-exception
            goto L_0x00a2
        L_0x006a:
            r0 = move-exception
            r1 = r2
        L_0x006c:
            com.google.android.gms.measurement.internal.zzeu r3 = r7.zzr()     // Catch: all -> 0x00a0
            com.google.android.gms.measurement.internal.zzew r3 = r3.zzf()     // Catch: all -> 0x00a0
            java.lang.String r4 = "Database error querying scoped filters. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzeu.zza(r8)     // Catch: all -> 0x00a0
            r3.zza(r4, r5, r0)     // Catch: all -> 0x00a0
            boolean r0 = com.google.android.gms.internal.measurement.zzku.zzb()     // Catch: all -> 0x00a0
            if (r0 == 0) goto L_0x0099
            com.google.android.gms.measurement.internal.zzy r0 = r7.zzt()     // Catch: all -> 0x00a0
            com.google.android.gms.measurement.internal.zzen<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzaq.zzcs     // Catch: all -> 0x00a0
            boolean r8 = r0.zze(r8, r3)     // Catch: all -> 0x00a0
            if (r8 == 0) goto L_0x0099
            java.util.Map r8 = java.util.Collections.emptyMap()     // Catch: all -> 0x00a0
            if (r1 == 0) goto L_0x0098
            r1.close()
        L_0x0098:
            return r8
        L_0x0099:
            if (r1 == 0) goto L_0x009f
            r1.close()
        L_0x009f:
            return r2
        L_0x00a0:
            r8 = move-exception
            r2 = r1
        L_0x00a2:
            if (r2 == 0) goto L_0x00a7
            r2.close()
        L_0x00a7:
            goto L_0x00a9
        L_0x00a8:
            throw r8
        L_0x00a9:
            goto L_0x00a8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzf(java.lang.String):java.util.Map");
    }

    private final boolean zzb(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzak();
        zzd();
        SQLiteDatabase c_ = c_();
        try {
            long zzb2 = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min((int) CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE, zzt().zzb(str, zzaq.zzae)));
            if (zzb2 <= max) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 140);
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return c_.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Database error querying filters. appId", zzeu.zza(str), e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00de  */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzcb.zzi> zzg(java.lang.String r12) {
        /*
            r11 = this;
            r11.zzak()
            r11.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.c_()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: all -> 0x00a2, SQLiteException -> 0x00a4
            java.lang.String r3 = "audience_id"
            r9 = 0
            r2[r9] = r3     // Catch: all -> 0x00a2, SQLiteException -> 0x00a4
            java.lang.String r3 = "current_results"
            r10 = 1
            r2[r10] = r3     // Catch: all -> 0x00a2, SQLiteException -> 0x00a4
            java.lang.String r3 = "app_id=?"
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch: all -> 0x00a2, SQLiteException -> 0x00a4
            r4[r9] = r12     // Catch: all -> 0x00a2, SQLiteException -> 0x00a4
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: all -> 0x00a2, SQLiteException -> 0x00a4
            boolean r1 = r0.moveToFirst()     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            if (r1 != 0) goto L_0x0054
            boolean r1 = com.google.android.gms.internal.measurement.zzku.zzb()     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            if (r1 == 0) goto L_0x004d
            com.google.android.gms.measurement.internal.zzy r1 = r11.zzt()     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            com.google.android.gms.measurement.internal.zzen<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzaq.zzcs     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            boolean r1 = r1.zze(r12, r2)     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            if (r1 == 0) goto L_0x004d
            java.util.Map r12 = java.util.Collections.emptyMap()     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            if (r0 == 0) goto L_0x004c
            r0.close()
        L_0x004c:
            return r12
        L_0x004d:
            if (r0 == 0) goto L_0x0053
            r0.close()
        L_0x0053:
            return r8
        L_0x0054:
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            r1.<init>()     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
        L_0x0059:
            int r2 = r0.getInt(r9)     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            byte[] r3 = r0.getBlob(r10)     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            com.google.android.gms.internal.measurement.zzcb$zzi$zza r4 = com.google.android.gms.internal.measurement.zzcb.zzi.zzi()     // Catch: IOException -> 0x007c, SQLiteException -> 0x00a0, all -> 0x00da
            com.google.android.gms.internal.measurement.zzgz r3 = com.google.android.gms.measurement.internal.zzkn.zza(r4, r3)     // Catch: IOException -> 0x007c, SQLiteException -> 0x00a0, all -> 0x00da
            com.google.android.gms.internal.measurement.zzcb$zzi$zza r3 = (com.google.android.gms.internal.measurement.zzcb.zzi.zza) r3     // Catch: IOException -> 0x007c, SQLiteException -> 0x00a0, all -> 0x00da
            com.google.android.gms.internal.measurement.zzgw r3 = r3.zzv()     // Catch: IOException -> 0x007c, SQLiteException -> 0x00a0, all -> 0x00da
            com.google.android.gms.internal.measurement.zzfo r3 = (com.google.android.gms.internal.measurement.zzfo) r3     // Catch: IOException -> 0x007c, SQLiteException -> 0x00a0, all -> 0x00da
            com.google.android.gms.internal.measurement.zzcb$zzi r3 = (com.google.android.gms.internal.measurement.zzcb.zzi) r3     // Catch: IOException -> 0x007c, SQLiteException -> 0x00a0, all -> 0x00da
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            r1.put(r2, r3)     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            goto L_0x0093
        L_0x007c:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzeu r4 = r11.zzr()     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            com.google.android.gms.measurement.internal.zzew r4 = r4.zzf()     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzeu.zza(r12)     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            r4.zza(r5, r6, r2, r3)     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
        L_0x0093:
            boolean r2 = r0.moveToNext()     // Catch: SQLiteException -> 0x00a0, all -> 0x00da
            if (r2 != 0) goto L_0x0059
            if (r0 == 0) goto L_0x009f
            r0.close()
        L_0x009f:
            return r1
        L_0x00a0:
            r1 = move-exception
            goto L_0x00a6
        L_0x00a2:
            r12 = move-exception
            goto L_0x00dc
        L_0x00a4:
            r1 = move-exception
            r0 = r8
        L_0x00a6:
            com.google.android.gms.measurement.internal.zzeu r2 = r11.zzr()     // Catch: all -> 0x00da
            com.google.android.gms.measurement.internal.zzew r2 = r2.zzf()     // Catch: all -> 0x00da
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzeu.zza(r12)     // Catch: all -> 0x00da
            r2.zza(r3, r4, r1)     // Catch: all -> 0x00da
            boolean r1 = com.google.android.gms.internal.measurement.zzku.zzb()     // Catch: all -> 0x00da
            if (r1 == 0) goto L_0x00d3
            com.google.android.gms.measurement.internal.zzy r1 = r11.zzt()     // Catch: all -> 0x00da
            com.google.android.gms.measurement.internal.zzen<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzaq.zzcs     // Catch: all -> 0x00da
            boolean r12 = r1.zze(r12, r2)     // Catch: all -> 0x00da
            if (r12 == 0) goto L_0x00d3
            java.util.Map r12 = java.util.Collections.emptyMap()     // Catch: all -> 0x00da
            if (r0 == 0) goto L_0x00d2
            r0.close()
        L_0x00d2:
            return r12
        L_0x00d3:
            if (r0 == 0) goto L_0x00d9
            r0.close()
        L_0x00d9:
            return r8
        L_0x00da:
            r12 = move-exception
            r8 = r0
        L_0x00dc:
            if (r8 == 0) goto L_0x00e1
            r8.close()
        L_0x00e1:
            goto L_0x00e3
        L_0x00e2:
            throw r12
        L_0x00e3:
            goto L_0x00e2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzg(java.lang.String):java.util.Map");
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            zzr().zzf().zza("Loaded invalid null value from database");
            return null;
        } else if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        } else {
            if (type == 2) {
                return Double.valueOf(cursor.getDouble(i));
            }
            if (type == 3) {
                return cursor.getString(i);
            }
            if (type != 4) {
                zzr().zzf().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
            }
            zzr().zzf().zza("Loaded invalid blob type value, ignoring it");
            return null;
        }
    }

    public final long zzw() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long zzh(String str, String str2) {
        Throwable th;
        SQLiteException e;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        SQLiteDatabase c_ = c_();
        c_.beginTransaction();
        long j = 0;
        try {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
            sb.append("select ");
            sb.append(str2);
            sb.append(" from app2 where app_id=?");
            try {
                try {
                    long zza = zza(sb.toString(), new String[]{str}, -1L);
                    if (zza == -1) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("app_id", str);
                        contentValues.put("first_open_count", (Integer) 0);
                        contentValues.put("previous_install_count", (Integer) 0);
                        if (c_.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                            zzr().zzf().zza("Failed to insert column (got -1). appId", zzeu.zza(str), str2);
                            c_.endTransaction();
                            return -1L;
                        }
                        zza = 0;
                    }
                    try {
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("app_id", str);
                        contentValues2.put(str2, Long.valueOf(1 + zza));
                        if (c_.update("app2", contentValues2, "app_id = ?", new String[]{str}) == 0) {
                            zzr().zzf().zza("Failed to update column (got 0). appId", zzeu.zza(str), str2);
                            c_.endTransaction();
                            return -1L;
                        }
                        c_.setTransactionSuccessful();
                        c_.endTransaction();
                        return zza;
                    } catch (SQLiteException e2) {
                        e = e2;
                        j = zza;
                        zzr().zzf().zza("Error inserting column. appId", zzeu.zza(str), str2, e);
                        c_.endTransaction();
                        return j;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    c_.endTransaction();
                    throw th;
                }
            } catch (SQLiteException e3) {
                e = e3;
            }
        } catch (SQLiteException e4) {
            e = e4;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public final long zzx() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0L);
    }

    public final long zza(zzcb.zzg zzgVar) throws IOException {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzgVar);
        Preconditions.checkNotEmpty(zzgVar.zzx());
        byte[] zzbi = zzgVar.zzbi();
        long zza = zzg().zza(zzbi);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzgVar.zzx());
        contentValues.put("metadata_fingerprint", Long.valueOf(zza));
        contentValues.put("metadata", zzbi);
        try {
            c_().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
            return zza;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing raw event metadata. appId", zzeu.zza(zzgVar.zzx()), e);
            throw e;
        }
    }

    public final boolean zzy() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzz() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzh(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String zza(long r5) {
        /*
            r4 = this;
            r4.zzd()
            r4.zzak()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.c_()     // Catch: all -> 0x0041, SQLiteException -> 0x0043
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: all -> 0x0041, SQLiteException -> 0x0043
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: all -> 0x0041, SQLiteException -> 0x0043
            r6 = 0
            r3[r6] = r5     // Catch: all -> 0x0041, SQLiteException -> 0x0043
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch: all -> 0x0041, SQLiteException -> 0x0043
            boolean r1 = r5.moveToFirst()     // Catch: SQLiteException -> 0x003f, all -> 0x0058
            if (r1 != 0) goto L_0x0035
            com.google.android.gms.measurement.internal.zzeu r6 = r4.zzr()     // Catch: SQLiteException -> 0x003f, all -> 0x0058
            com.google.android.gms.measurement.internal.zzew r6 = r6.zzx()     // Catch: SQLiteException -> 0x003f, all -> 0x0058
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.zza(r1)     // Catch: SQLiteException -> 0x003f, all -> 0x0058
            if (r5 == 0) goto L_0x0034
            r5.close()
        L_0x0034:
            return r0
        L_0x0035:
            java.lang.String r6 = r5.getString(r6)     // Catch: SQLiteException -> 0x003f, all -> 0x0058
            if (r5 == 0) goto L_0x003e
            r5.close()
        L_0x003e:
            return r6
        L_0x003f:
            r6 = move-exception
            goto L_0x0045
        L_0x0041:
            r6 = move-exception
            goto L_0x005a
        L_0x0043:
            r6 = move-exception
            r5 = r0
        L_0x0045:
            com.google.android.gms.measurement.internal.zzeu r1 = r4.zzr()     // Catch: all -> 0x0058
            com.google.android.gms.measurement.internal.zzew r1 = r1.zzf()     // Catch: all -> 0x0058
            java.lang.String r2 = "Error selecting expired configs"
            r1.zza(r2, r6)     // Catch: all -> 0x0058
            if (r5 == 0) goto L_0x0057
            r5.close()
        L_0x0057:
            return r0
        L_0x0058:
            r6 = move-exception
            r0 = r5
        L_0x005a:
            if (r0 == 0) goto L_0x005f
            r0.close()
        L_0x005f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(long):java.lang.String");
    }

    public final long zzaa() {
        Cursor cursor = null;
        try {
            try {
                cursor = c_().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return -1L;
                }
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            } catch (SQLiteException e) {
                zzr().zzf().zza("Error querying raw events", e);
                if (cursor != null) {
                    cursor.close();
                }
                return -1L;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0093: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:30:0x0093 */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzcb.zzc, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.zzd()
            r7.zzak()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.c_()     // Catch: all -> 0x007b, SQLiteException -> 0x007d
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: all -> 0x007b, SQLiteException -> 0x007d
            r4 = 0
            r3[r4] = r8     // Catch: all -> 0x007b, SQLiteException -> 0x007d
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch: all -> 0x007b, SQLiteException -> 0x007d
            r6 = 1
            r3[r6] = r5     // Catch: all -> 0x007b, SQLiteException -> 0x007d
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch: all -> 0x007b, SQLiteException -> 0x007d
            boolean r2 = r1.moveToFirst()     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            if (r2 != 0) goto L_0x0038
            com.google.android.gms.measurement.internal.zzeu r8 = r7.zzr()     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            com.google.android.gms.measurement.internal.zzew r8 = r8.zzx()     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            java.lang.String r9 = "Main event not found"
            r8.zza(r9)     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            if (r1 == 0) goto L_0x0037
            r1.close()
        L_0x0037:
            return r0
        L_0x0038:
            byte[] r2 = r1.getBlob(r4)     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            long r3 = r1.getLong(r6)     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            com.google.android.gms.internal.measurement.zzcb$zzc$zza r4 = com.google.android.gms.internal.measurement.zzcb.zzc.zzj()     // Catch: IOException -> 0x0061, SQLiteException -> 0x0079, all -> 0x0092
            com.google.android.gms.internal.measurement.zzgz r2 = com.google.android.gms.measurement.internal.zzkn.zza(r4, r2)     // Catch: IOException -> 0x0061, SQLiteException -> 0x0079, all -> 0x0092
            com.google.android.gms.internal.measurement.zzcb$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzcb.zzc.zza) r2     // Catch: IOException -> 0x0061, SQLiteException -> 0x0079, all -> 0x0092
            com.google.android.gms.internal.measurement.zzgw r2 = r2.zzv()     // Catch: IOException -> 0x0061, SQLiteException -> 0x0079, all -> 0x0092
            com.google.android.gms.internal.measurement.zzfo r2 = (com.google.android.gms.internal.measurement.zzfo) r2     // Catch: IOException -> 0x0061, SQLiteException -> 0x0079, all -> 0x0092
            com.google.android.gms.internal.measurement.zzcb$zzc r2 = (com.google.android.gms.internal.measurement.zzcb.zzc) r2     // Catch: IOException -> 0x0061, SQLiteException -> 0x0079, all -> 0x0092
            android.util.Pair r8 = android.util.Pair.create(r2, r3)     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            if (r1 == 0) goto L_0x0060
            r1.close()
        L_0x0060:
            return r8
        L_0x0061:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzeu r3 = r7.zzr()     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            com.google.android.gms.measurement.internal.zzew r3 = r3.zzf()     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzeu.zza(r8)     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            r3.zza(r4, r8, r9, r2)     // Catch: SQLiteException -> 0x0079, all -> 0x0092
            if (r1 == 0) goto L_0x0078
            r1.close()
        L_0x0078:
            return r0
        L_0x0079:
            r8 = move-exception
            goto L_0x007f
        L_0x007b:
            r8 = move-exception
            goto L_0x0094
        L_0x007d:
            r8 = move-exception
            r1 = r0
        L_0x007f:
            com.google.android.gms.measurement.internal.zzeu r9 = r7.zzr()     // Catch: all -> 0x0092
            com.google.android.gms.measurement.internal.zzew r9 = r9.zzf()     // Catch: all -> 0x0092
            java.lang.String r2 = "Error selecting main event"
            r9.zza(r2, r8)     // Catch: all -> 0x0092
            if (r1 == 0) goto L_0x0091
            r1.close()
        L_0x0091:
            return r0
        L_0x0092:
            r8 = move-exception
            r0 = r1
        L_0x0094:
            if (r0 == 0) goto L_0x0099
            r0.close()
        L_0x0099:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    public final boolean zza(String str, Long l, long j, zzcb.zzc zzcVar) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzcVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] zzbi = zzcVar.zzbi();
        zzr().zzx().zza("Saving complex main event, appId, data size", zzo().zza(str), Integer.valueOf(zzbi.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzbi);
        try {
            if (c_().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert complex main event (got -1). appId", zzeu.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing complex main event. appId", zzeu.zza(str), e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(String str, Bundle bundle) {
        zzd();
        zzak();
        byte[] zzbi = zzg().zza(new zzal(this.zzy, "", str, "dep", 0L, 0L, bundle)).zzbi();
        zzr().zzx().zza("Saving default event parameters, appId, data size", zzo().zza(str), Integer.valueOf(zzbi.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("parameters", zzbi);
        try {
            if (c_().insertWithOnConflict("default_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert default event parameters (got -1). appId", zzeu.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing default event parameters. appId", zzeu.zza(str), e);
            return false;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x00da: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:44:0x00da */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.os.Bundle zzi(java.lang.String r8) {
        /*
            r7 = this;
            r7.zzd()
            r7.zzak()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.c_()     // Catch: all -> 0x00c2, SQLiteException -> 0x00c4
            java.lang.String r2 = "select parameters from default_event_params where app_id=?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: all -> 0x00c2, SQLiteException -> 0x00c4
            r4 = 0
            r3[r4] = r8     // Catch: all -> 0x00c2, SQLiteException -> 0x00c4
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch: all -> 0x00c2, SQLiteException -> 0x00c4
            boolean r2 = r1.moveToFirst()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            if (r2 != 0) goto L_0x0031
            com.google.android.gms.measurement.internal.zzeu r8 = r7.zzr()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            com.google.android.gms.measurement.internal.zzew r8 = r8.zzx()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            java.lang.String r2 = "Default event parameters not found"
            r8.zza(r2)     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            if (r1 == 0) goto L_0x0030
            r1.close()
        L_0x0030:
            return r0
        L_0x0031:
            byte[] r2 = r1.getBlob(r4)     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzcb$zzc$zza r3 = com.google.android.gms.internal.measurement.zzcb.zzc.zzj()     // Catch: IOException -> 0x00a8, SQLiteException -> 0x00c0, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzgz r2 = com.google.android.gms.measurement.internal.zzkn.zza(r3, r2)     // Catch: IOException -> 0x00a8, SQLiteException -> 0x00c0, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzcb$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzcb.zzc.zza) r2     // Catch: IOException -> 0x00a8, SQLiteException -> 0x00c0, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzgw r2 = r2.zzv()     // Catch: IOException -> 0x00a8, SQLiteException -> 0x00c0, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzfo r2 = (com.google.android.gms.internal.measurement.zzfo) r2     // Catch: IOException -> 0x00a8, SQLiteException -> 0x00c0, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzcb$zzc r2 = (com.google.android.gms.internal.measurement.zzcb.zzc) r2     // Catch: IOException -> 0x00a8, SQLiteException -> 0x00c0, all -> 0x00d9
            r7.zzg()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            java.util.List r8 = r2.zza()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            android.os.Bundle r2 = new android.os.Bundle     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            r2.<init>()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            java.util.Iterator r8 = r8.iterator()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
        L_0x0058:
            boolean r3 = r8.hasNext()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            if (r3 == 0) goto L_0x00a0
            java.lang.Object r3 = r8.next()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            com.google.android.gms.internal.measurement.zzcb$zze r3 = (com.google.android.gms.internal.measurement.zzcb.zze) r3     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            java.lang.String r4 = r3.zzb()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            boolean r5 = r3.zzi()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            if (r5 == 0) goto L_0x0076
            double r5 = r3.zzj()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            r2.putDouble(r4, r5)     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            goto L_0x0058
        L_0x0076:
            boolean r5 = r3.zzg()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            if (r5 == 0) goto L_0x0084
            float r3 = r3.zzh()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            r2.putFloat(r4, r3)     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            goto L_0x0058
        L_0x0084:
            boolean r5 = r3.zzc()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            if (r5 == 0) goto L_0x0092
            java.lang.String r3 = r3.zzd()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            r2.putString(r4, r3)     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            goto L_0x0058
        L_0x0092:
            boolean r5 = r3.zze()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            if (r5 == 0) goto L_0x009f
            long r5 = r3.zzf()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            r2.putLong(r4, r5)     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
        L_0x009f:
            goto L_0x0058
        L_0x00a0:
            if (r1 == 0) goto L_0x00a7
            r1.close()
        L_0x00a7:
            return r2
        L_0x00a8:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzeu r3 = r7.zzr()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            com.google.android.gms.measurement.internal.zzew r3 = r3.zzf()     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            java.lang.String r4 = "Failed to retrieve default event parameters. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzeu.zza(r8)     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            r3.zza(r4, r8, r2)     // Catch: SQLiteException -> 0x00c0, all -> 0x00d9
            if (r1 == 0) goto L_0x00bf
            r1.close()
        L_0x00bf:
            return r0
        L_0x00c0:
            r8 = move-exception
            goto L_0x00c6
        L_0x00c2:
            r8 = move-exception
            goto L_0x00db
        L_0x00c4:
            r8 = move-exception
            r1 = r0
        L_0x00c6:
            com.google.android.gms.measurement.internal.zzeu r2 = r7.zzr()     // Catch: all -> 0x00d9
            com.google.android.gms.measurement.internal.zzew r2 = r2.zzf()     // Catch: all -> 0x00d9
            java.lang.String r3 = "Error selecting default event parameters"
            r2.zza(r3, r8)     // Catch: all -> 0x00d9
            if (r1 == 0) goto L_0x00d8
            r1.close()
        L_0x00d8:
            return r0
        L_0x00d9:
            r8 = move-exception
            r0 = r1
        L_0x00db:
            if (r0 == 0) goto L_0x00e0
            r0.close()
        L_0x00e0:
            goto L_0x00e2
        L_0x00e1:
            throw r8
        L_0x00e2:
            goto L_0x00e1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.zzi(java.lang.String):android.os.Bundle");
    }

    public final boolean zza(zzal zzalVar, long j, boolean z) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzalVar);
        Preconditions.checkNotEmpty(zzalVar.zza);
        byte[] zzbi = zzg().zza(zzalVar).zzbi();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzalVar.zza);
        contentValues.put("name", zzalVar.zzb);
        contentValues.put("timestamp", Long.valueOf(zzalVar.zzc));
        contentValues.put("metadata_fingerprint", Long.valueOf(j));
        contentValues.put("data", zzbi);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            if (c_().insert("raw_events", null, contentValues) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert raw event (got -1). appId", zzeu.zza(zzalVar.zza));
            return false;
        } catch (SQLiteException e) {
            zzr().zzf().zza("Error storing raw event. appId", zzeu.zza(zzalVar.zza), e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(String str, List<zzbt.zza> list) {
        boolean z;
        boolean z2;
        Preconditions.checkNotNull(list);
        for (int i = 0; i < list.size(); i++) {
            zzbt.zza.C0037zza zzbl = list.get(i).zzbl();
            if (zzbl.zzb() != 0) {
                for (int i2 = 0; i2 < zzbl.zzb(); i2++) {
                    zzbt.zzb.zza zzbl2 = zzbl.zzb(i2).zzbl();
                    zzbt.zzb.zza zzaVar = (zzbt.zzb.zza) ((zzfo.zza) zzbl2.clone());
                    String zzb2 = zzgw.zzb(zzbl2.zza());
                    if (zzb2 != null) {
                        zzaVar.zza(zzb2);
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    for (int i3 = 0; i3 < zzbl2.zzb(); i3++) {
                        zzbt.zzc zza = zzbl2.zza(i3);
                        String zza2 = zzgz.zza(zza.zzh());
                        if (zza2 != null) {
                            zzaVar.zza(i3, (zzbt.zzc) ((zzfo) zza.zzbl().zza(zza2).zzv()));
                            z2 = true;
                        }
                    }
                    if (z2) {
                        zzbl = zzbl.zza(i2, zzaVar);
                        list.set(i, (zzbt.zza) ((zzfo) zzbl.zzv()));
                    }
                }
            }
            if (zzbl.zza() != 0) {
                for (int i4 = 0; i4 < zzbl.zza(); i4++) {
                    zzbt.zze zza3 = zzbl.zza(i4);
                    String zza4 = zzgy.zza(zza3.zzc());
                    if (zza4 != null) {
                        zzbl = zzbl.zza(i4, zza3.zzbl().zza(zza4));
                        list.set(i, (zzbt.zza) ((zzfo) zzbl.zzv()));
                    }
                }
            }
        }
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase c_ = c_();
        c_.beginTransaction();
        try {
            zzak();
            zzd();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase c_2 = c_();
            c_2.delete("property_filters", "app_id=?", new String[]{str});
            c_2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzbt.zza zzaVar2 : list) {
                zzak();
                zzd();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzaVar2);
                if (!zzaVar2.zza()) {
                    zzr().zzi().zza("Audience with no ID. appId", zzeu.zza(str));
                } else {
                    int zzb3 = zzaVar2.zzb();
                    Iterator<zzbt.zzb> it = zzaVar2.zze().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            Iterator<zzbt.zze> it2 = zzaVar2.zzc().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    Iterator<zzbt.zzb> it3 = zzaVar2.zze().iterator();
                                    while (true) {
                                        if (!it3.hasNext()) {
                                            z = true;
                                            break;
                                        } else if (!zza(str, zzb3, it3.next())) {
                                            z = false;
                                            break;
                                        }
                                    }
                                    if (z) {
                                        Iterator<zzbt.zze> it4 = zzaVar2.zzc().iterator();
                                        while (true) {
                                            if (!it4.hasNext()) {
                                                break;
                                            } else if (!zza(str, zzb3, it4.next())) {
                                                z = false;
                                                break;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        zzak();
                                        zzd();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase c_3 = c_();
                                        c_3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(zzb3)});
                                        c_3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(zzb3)});
                                    }
                                } else if (!it2.next().zza()) {
                                    zzr().zzi().zza("Property filter with no ID. Audience definition ignored. appId, audienceId", zzeu.zza(str), Integer.valueOf(zzb3));
                                    break;
                                }
                            }
                        } else if (!it.next().zza()) {
                            zzr().zzi().zza("Event filter with no ID. Audience definition ignored. appId, audienceId", zzeu.zza(str), Integer.valueOf(zzb3));
                            break;
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzbt.zza zzaVar3 : list) {
                arrayList.add(zzaVar3.zza() ? Integer.valueOf(zzaVar3.zzb()) : null);
            }
            zzb(str, arrayList);
            c_.setTransactionSuccessful();
        } finally {
            c_.endTransaction();
        }
    }

    private final boolean zzam() {
        return zzn().getDatabasePath("google_app_measurement.db").exists();
    }
}
