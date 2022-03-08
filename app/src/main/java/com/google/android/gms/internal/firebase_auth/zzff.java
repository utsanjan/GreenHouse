package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzlv;
import com.google.firebase.auth.api.internal.zzel;
import com.google.firebase.auth.api.zza;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzff extends AbstractSafeParcelable implements zzel<zzff, zzlv.zzb> {
    public static final Parcelable.Creator<zzff> CREATOR = new zzfe();
    private String zza;
    private String zzb;
    private Long zzc;
    private String zzd;
    private Long zze;

    public zzff() {
        this.zze = Long.valueOf(System.currentTimeMillis());
    }

    public zzff(String str, String str2, Long l, String str3) {
        this(str, str2, l, str3, Long.valueOf(System.currentTimeMillis()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzff(String str, String str2, Long l, String str3, Long l2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = l;
        this.zzd = str3;
        this.zze = l2;
    }

    public final boolean zzb() {
        return DefaultClock.getInstance().currentTimeMillis() + 300000 < this.zze.longValue() + (this.zzc.longValue() * 1000);
    }

    public final void zza(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
    }

    public final String zzc() {
        return this.zza;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final long zze() {
        Long l = this.zzc;
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public final String zzf() {
        return this.zzd;
    }

    public final long zzg() {
        return this.zze.longValue();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeLongObject(parcel, 4, Long.valueOf(zze()), false);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeLongObject(parcel, 6, Long.valueOf(this.zze.longValue()), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final zzjx<zzlv.zzb> zza() {
        return zzlv.zzb.zzf();
    }

    public final String zzh() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("refresh_token", this.zza);
            jSONObject.put("access_token", this.zzb);
            jSONObject.put("expires_in", this.zzc);
            jSONObject.put("token_type", this.zzd);
            jSONObject.put("issued_at", this.zze);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.d("GetTokenResponse", "Failed to convert GetTokenResponse to JSON");
            throw new zza(e);
        }
    }

    public static zzff zzb(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            zzff zzffVar = new zzff();
            zzffVar.zza = jSONObject.optString("refresh_token", null);
            zzffVar.zzb = jSONObject.optString("access_token", null);
            zzffVar.zzc = Long.valueOf(jSONObject.optLong("expires_in"));
            zzffVar.zzd = jSONObject.optString("token_type", null);
            zzffVar.zze = Long.valueOf(jSONObject.optLong("issued_at"));
            return zzffVar;
        } catch (JSONException e) {
            Log.d("GetTokenResponse", "Failed to read GetTokenResponse from JSONObject");
            throw new zza(e);
        }
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final /* synthetic */ zzff zza(zzjn zzjnVar) {
        if (zzjnVar instanceof zzlv.zzb) {
            zzlv.zzb zzbVar = (zzlv.zzb) zzjnVar;
            this.zza = Strings.emptyToNull(zzbVar.zze());
            this.zzb = Strings.emptyToNull(zzbVar.zza());
            this.zzc = Long.valueOf(zzbVar.zzb());
            this.zzd = Strings.emptyToNull(zzbVar.zzd());
            this.zze = Long.valueOf(System.currentTimeMillis());
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of GrantTokenResponse.");
    }
}
