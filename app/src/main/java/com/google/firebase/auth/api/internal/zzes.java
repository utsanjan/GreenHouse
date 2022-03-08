package com.google.firebase.auth.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.firebase_auth.zza;
import com.google.android.gms.internal.firebase_auth.zzbw;
import com.google.android.gms.internal.firebase_auth.zzbx;
import com.google.android.gms.internal.firebase_auth.zzbz;
import com.google.android.gms.internal.firebase_auth.zzcb;
import com.google.android.gms.internal.firebase_auth.zzcd;
import com.google.android.gms.internal.firebase_auth.zzcf;
import com.google.android.gms.internal.firebase_auth.zzch;
import com.google.android.gms.internal.firebase_auth.zzcj;
import com.google.android.gms.internal.firebase_auth.zzcl;
import com.google.android.gms.internal.firebase_auth.zzcn;
import com.google.android.gms.internal.firebase_auth.zzcp;
import com.google.android.gms.internal.firebase_auth.zzcr;
import com.google.android.gms.internal.firebase_auth.zzct;
import com.google.android.gms.internal.firebase_auth.zzcv;
import com.google.android.gms.internal.firebase_auth.zzcx;
import com.google.android.gms.internal.firebase_auth.zzcz;
import com.google.android.gms.internal.firebase_auth.zzd;
import com.google.android.gms.internal.firebase_auth.zzdb;
import com.google.android.gms.internal.firebase_auth.zzdd;
import com.google.android.gms.internal.firebase_auth.zzdf;
import com.google.android.gms.internal.firebase_auth.zzdh;
import com.google.android.gms.internal.firebase_auth.zzdj;
import com.google.android.gms.internal.firebase_auth.zzdl;
import com.google.android.gms.internal.firebase_auth.zzdn;
import com.google.android.gms.internal.firebase_auth.zzdp;
import com.google.android.gms.internal.firebase_auth.zzdr;
import com.google.android.gms.internal.firebase_auth.zzdt;
import com.google.android.gms.internal.firebase_auth.zzdv;
import com.google.android.gms.internal.firebase_auth.zzdx;
import com.google.android.gms.internal.firebase_auth.zzdz;
import com.google.android.gms.internal.firebase_auth.zzeb;
import com.google.android.gms.internal.firebase_auth.zzed;
import com.google.android.gms.internal.firebase_auth.zzef;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.android.gms.internal.firebase_auth.zzfy;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class zzes extends zza implements zzep {
    public zzes() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthService");
    }

    @Override // com.google.android.gms.internal.firebase_auth.zza
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzeo zzeoVar = null;
        switch (i) {
            case 1:
                String readString = parcel.readString();
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder);
                    }
                }
                zza(readString, zzeoVar);
                break;
            case 2:
                String readString2 = parcel.readString();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface2 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface2;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder2);
                    }
                }
                zzb(readString2, zzeoVar);
                break;
            case 3:
                zzfy zzfyVar = (zzfy) zzd.zza(parcel, zzfy.CREATOR);
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface3 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface3;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder3);
                    }
                }
                zza(zzfyVar, zzeoVar);
                break;
            case 4:
                String readString3 = parcel.readString();
                UserProfileChangeRequest userProfileChangeRequest = (UserProfileChangeRequest) zzd.zza(parcel, UserProfileChangeRequest.CREATOR);
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 != null) {
                    IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface4 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface4;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder4);
                    }
                }
                zza(readString3, userProfileChangeRequest, zzeoVar);
                break;
            case 5:
                String readString4 = parcel.readString();
                String readString5 = parcel.readString();
                IBinder readStrongBinder5 = parcel.readStrongBinder();
                if (readStrongBinder5 != null) {
                    IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface5 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface5;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder5);
                    }
                }
                zza(readString4, readString5, zzeoVar);
                break;
            case 6:
                String readString6 = parcel.readString();
                String readString7 = parcel.readString();
                IBinder readStrongBinder6 = parcel.readStrongBinder();
                if (readStrongBinder6 != null) {
                    IInterface queryLocalInterface6 = readStrongBinder6.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface6 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface6;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder6);
                    }
                }
                zzb(readString6, readString7, zzeoVar);
                break;
            case 7:
                String readString8 = parcel.readString();
                String readString9 = parcel.readString();
                IBinder readStrongBinder7 = parcel.readStrongBinder();
                if (readStrongBinder7 != null) {
                    IInterface queryLocalInterface7 = readStrongBinder7.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface7 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface7;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder7);
                    }
                }
                zzc(readString8, readString9, zzeoVar);
                break;
            case 8:
                String readString10 = parcel.readString();
                String readString11 = parcel.readString();
                IBinder readStrongBinder8 = parcel.readStrongBinder();
                if (readStrongBinder8 != null) {
                    IInterface queryLocalInterface8 = readStrongBinder8.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface8 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface8;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder8);
                    }
                }
                zzd(readString10, readString11, zzeoVar);
                break;
            case 9:
                String readString12 = parcel.readString();
                IBinder readStrongBinder9 = parcel.readStrongBinder();
                if (readStrongBinder9 != null) {
                    IInterface queryLocalInterface9 = readStrongBinder9.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface9 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface9;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder9);
                    }
                }
                zzc(readString12, zzeoVar);
                break;
            case 10:
                String readString13 = parcel.readString();
                IBinder readStrongBinder10 = parcel.readStrongBinder();
                if (readStrongBinder10 != null) {
                    IInterface queryLocalInterface10 = readStrongBinder10.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface10 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface10;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder10);
                    }
                }
                zzd(readString13, zzeoVar);
                break;
            case 11:
                String readString14 = parcel.readString();
                String readString15 = parcel.readString();
                String readString16 = parcel.readString();
                IBinder readStrongBinder11 = parcel.readStrongBinder();
                if (readStrongBinder11 != null) {
                    IInterface queryLocalInterface11 = readStrongBinder11.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface11 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface11;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder11);
                    }
                }
                zza(readString14, readString15, readString16, zzeoVar);
                break;
            case 12:
                String readString17 = parcel.readString();
                zzfy zzfyVar2 = (zzfy) zzd.zza(parcel, zzfy.CREATOR);
                IBinder readStrongBinder12 = parcel.readStrongBinder();
                if (readStrongBinder12 != null) {
                    IInterface queryLocalInterface12 = readStrongBinder12.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface12 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface12;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder12);
                    }
                }
                zza(readString17, zzfyVar2, zzeoVar);
                break;
            case 13:
                String readString18 = parcel.readString();
                IBinder readStrongBinder13 = parcel.readStrongBinder();
                if (readStrongBinder13 != null) {
                    IInterface queryLocalInterface13 = readStrongBinder13.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface13 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface13;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder13);
                    }
                }
                zze(readString18, zzeoVar);
                break;
            case 14:
                String readString19 = parcel.readString();
                String readString20 = parcel.readString();
                IBinder readStrongBinder14 = parcel.readStrongBinder();
                if (readStrongBinder14 != null) {
                    IInterface queryLocalInterface14 = readStrongBinder14.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface14 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface14;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder14);
                    }
                }
                zze(readString19, readString20, zzeoVar);
                break;
            case 15:
                String readString21 = parcel.readString();
                IBinder readStrongBinder15 = parcel.readStrongBinder();
                if (readStrongBinder15 != null) {
                    IInterface queryLocalInterface15 = readStrongBinder15.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface15 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface15;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder15);
                    }
                }
                zzf(readString21, zzeoVar);
                break;
            case 16:
                IBinder readStrongBinder16 = parcel.readStrongBinder();
                if (readStrongBinder16 != null) {
                    IInterface queryLocalInterface16 = readStrongBinder16.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface16 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface16;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder16);
                    }
                }
                zza(zzeoVar);
                break;
            case 17:
                String readString22 = parcel.readString();
                IBinder readStrongBinder17 = parcel.readStrongBinder();
                if (readStrongBinder17 != null) {
                    IInterface queryLocalInterface17 = readStrongBinder17.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface17 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface17;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder17);
                    }
                }
                zzg(readString22, zzeoVar);
                break;
            case 18:
                String readString23 = parcel.readString();
                IBinder readStrongBinder18 = parcel.readStrongBinder();
                if (readStrongBinder18 != null) {
                    IInterface queryLocalInterface18 = readStrongBinder18.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface18 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface18;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder18);
                    }
                }
                zzh(readString23, zzeoVar);
                break;
            case 19:
                String readString24 = parcel.readString();
                IBinder readStrongBinder19 = parcel.readStrongBinder();
                if (readStrongBinder19 != null) {
                    IInterface queryLocalInterface19 = readStrongBinder19.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface19 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface19;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder19);
                    }
                }
                zzi(readString24, zzeoVar);
                break;
            case 20:
                String readString25 = parcel.readString();
                IBinder readStrongBinder20 = parcel.readStrongBinder();
                if (readStrongBinder20 != null) {
                    IInterface queryLocalInterface20 = readStrongBinder20.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface20 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface20;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder20);
                    }
                }
                zzj(readString25, zzeoVar);
                break;
            case 21:
                String readString26 = parcel.readString();
                String readString27 = parcel.readString();
                IBinder readStrongBinder21 = parcel.readStrongBinder();
                if (readStrongBinder21 != null) {
                    IInterface queryLocalInterface21 = readStrongBinder21.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface21 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface21;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder21);
                    }
                }
                zzf(readString26, readString27, zzeoVar);
                break;
            case 22:
                zzfr zzfrVar = (zzfr) zzd.zza(parcel, zzfr.CREATOR);
                IBinder readStrongBinder22 = parcel.readStrongBinder();
                if (readStrongBinder22 != null) {
                    IInterface queryLocalInterface22 = readStrongBinder22.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface22 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface22;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder22);
                    }
                }
                zza(zzfrVar, zzeoVar);
                break;
            case 23:
                PhoneAuthCredential phoneAuthCredential = (PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR);
                IBinder readStrongBinder23 = parcel.readStrongBinder();
                if (readStrongBinder23 != null) {
                    IInterface queryLocalInterface23 = readStrongBinder23.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface23 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface23;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder23);
                    }
                }
                zza(phoneAuthCredential, zzeoVar);
                break;
            case 24:
                String readString28 = parcel.readString();
                PhoneAuthCredential phoneAuthCredential2 = (PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR);
                IBinder readStrongBinder24 = parcel.readStrongBinder();
                if (readStrongBinder24 != null) {
                    IInterface queryLocalInterface24 = readStrongBinder24.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface24 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface24;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder24);
                    }
                }
                zza(readString28, phoneAuthCredential2, zzeoVar);
                break;
            case 25:
                String readString29 = parcel.readString();
                ActionCodeSettings actionCodeSettings = (ActionCodeSettings) zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder readStrongBinder25 = parcel.readStrongBinder();
                if (readStrongBinder25 != null) {
                    IInterface queryLocalInterface25 = readStrongBinder25.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface25 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface25;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder25);
                    }
                }
                zza(readString29, actionCodeSettings, zzeoVar);
                break;
            case 26:
                String readString30 = parcel.readString();
                ActionCodeSettings actionCodeSettings2 = (ActionCodeSettings) zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder readStrongBinder26 = parcel.readStrongBinder();
                if (readStrongBinder26 != null) {
                    IInterface queryLocalInterface26 = readStrongBinder26.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface26 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface26;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder26);
                    }
                }
                zzb(readString30, actionCodeSettings2, zzeoVar);
                break;
            case 27:
                String readString31 = parcel.readString();
                IBinder readStrongBinder27 = parcel.readStrongBinder();
                if (readStrongBinder27 != null) {
                    IInterface queryLocalInterface27 = readStrongBinder27.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface27 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface27;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder27);
                    }
                }
                zzk(readString31, zzeoVar);
                break;
            case 28:
                String readString32 = parcel.readString();
                ActionCodeSettings actionCodeSettings3 = (ActionCodeSettings) zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder readStrongBinder28 = parcel.readStrongBinder();
                if (readStrongBinder28 != null) {
                    IInterface queryLocalInterface28 = readStrongBinder28.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface28 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface28;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder28);
                    }
                }
                zzc(readString32, actionCodeSettings3, zzeoVar);
                break;
            case 29:
                EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zzd.zza(parcel, EmailAuthCredential.CREATOR);
                IBinder readStrongBinder29 = parcel.readStrongBinder();
                if (readStrongBinder29 != null) {
                    IInterface queryLocalInterface29 = readStrongBinder29.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface29 instanceof zzeo) {
                        zzeoVar = (zzeo) queryLocalInterface29;
                    } else {
                        zzeoVar = new zzeq(readStrongBinder29);
                    }
                }
                zza(emailAuthCredential, zzeoVar);
                break;
            default:
                switch (i) {
                    case 101:
                        zzcn zzcnVar = (zzcn) zzd.zza(parcel, zzcn.CREATOR);
                        IBinder readStrongBinder30 = parcel.readStrongBinder();
                        if (readStrongBinder30 != null) {
                            IInterface queryLocalInterface30 = readStrongBinder30.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (queryLocalInterface30 instanceof zzeo) {
                                zzeoVar = (zzeo) queryLocalInterface30;
                            } else {
                                zzeoVar = new zzeq(readStrongBinder30);
                            }
                        }
                        zza(zzcnVar, zzeoVar);
                        break;
                    case 102:
                        zzdl zzdlVar = (zzdl) zzd.zza(parcel, zzdl.CREATOR);
                        IBinder readStrongBinder31 = parcel.readStrongBinder();
                        if (readStrongBinder31 != null) {
                            IInterface queryLocalInterface31 = readStrongBinder31.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (queryLocalInterface31 instanceof zzeo) {
                                zzeoVar = (zzeo) queryLocalInterface31;
                            } else {
                                zzeoVar = new zzeq(readStrongBinder31);
                            }
                        }
                        zza(zzdlVar, zzeoVar);
                        break;
                    case 103:
                        zzdj zzdjVar = (zzdj) zzd.zza(parcel, zzdj.CREATOR);
                        IBinder readStrongBinder32 = parcel.readStrongBinder();
                        if (readStrongBinder32 != null) {
                            IInterface queryLocalInterface32 = readStrongBinder32.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (queryLocalInterface32 instanceof zzeo) {
                                zzeoVar = (zzeo) queryLocalInterface32;
                            } else {
                                zzeoVar = new zzeq(readStrongBinder32);
                            }
                        }
                        zza(zzdjVar, zzeoVar);
                        break;
                    case 104:
                        zzed zzedVar = (zzed) zzd.zza(parcel, zzed.CREATOR);
                        IBinder readStrongBinder33 = parcel.readStrongBinder();
                        if (readStrongBinder33 != null) {
                            IInterface queryLocalInterface33 = readStrongBinder33.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (queryLocalInterface33 instanceof zzeo) {
                                zzeoVar = (zzeo) queryLocalInterface33;
                            } else {
                                zzeoVar = new zzeq(readStrongBinder33);
                            }
                        }
                        zza(zzedVar, zzeoVar);
                        break;
                    case 105:
                        zzbx zzbxVar = (zzbx) zzd.zza(parcel, zzbx.CREATOR);
                        IBinder readStrongBinder34 = parcel.readStrongBinder();
                        if (readStrongBinder34 != null) {
                            IInterface queryLocalInterface34 = readStrongBinder34.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (queryLocalInterface34 instanceof zzeo) {
                                zzeoVar = (zzeo) queryLocalInterface34;
                            } else {
                                zzeoVar = new zzeq(readStrongBinder34);
                            }
                        }
                        zza(zzbxVar, zzeoVar);
                        break;
                    case 106:
                        zzbz zzbzVar = (zzbz) zzd.zza(parcel, zzbz.CREATOR);
                        IBinder readStrongBinder35 = parcel.readStrongBinder();
                        if (readStrongBinder35 != null) {
                            IInterface queryLocalInterface35 = readStrongBinder35.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (queryLocalInterface35 instanceof zzeo) {
                                zzeoVar = (zzeo) queryLocalInterface35;
                            } else {
                                zzeoVar = new zzeq(readStrongBinder35);
                            }
                        }
                        zza(zzbzVar, zzeoVar);
                        break;
                    case 107:
                        zzcf zzcfVar = (zzcf) zzd.zza(parcel, zzcf.CREATOR);
                        IBinder readStrongBinder36 = parcel.readStrongBinder();
                        if (readStrongBinder36 != null) {
                            IInterface queryLocalInterface36 = readStrongBinder36.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (queryLocalInterface36 instanceof zzeo) {
                                zzeoVar = (zzeo) queryLocalInterface36;
                            } else {
                                zzeoVar = new zzeq(readStrongBinder36);
                            }
                        }
                        zza(zzcfVar, zzeoVar);
                        break;
                    case 108:
                        zzdn zzdnVar = (zzdn) zzd.zza(parcel, zzdn.CREATOR);
                        IBinder readStrongBinder37 = parcel.readStrongBinder();
                        if (readStrongBinder37 != null) {
                            IInterface queryLocalInterface37 = readStrongBinder37.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (queryLocalInterface37 instanceof zzeo) {
                                zzeoVar = (zzeo) queryLocalInterface37;
                            } else {
                                zzeoVar = new zzeq(readStrongBinder37);
                            }
                        }
                        zza(zzdnVar, zzeoVar);
                        break;
                    case 109:
                        zzcp zzcpVar = (zzcp) zzd.zza(parcel, zzcp.CREATOR);
                        IBinder readStrongBinder38 = parcel.readStrongBinder();
                        if (readStrongBinder38 != null) {
                            IInterface queryLocalInterface38 = readStrongBinder38.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (queryLocalInterface38 instanceof zzeo) {
                                zzeoVar = (zzeo) queryLocalInterface38;
                            } else {
                                zzeoVar = new zzeq(readStrongBinder38);
                            }
                        }
                        zza(zzcpVar, zzeoVar);
                        break;
                    default:
                        switch (i) {
                            case 111:
                                zzcr zzcrVar = (zzcr) zzd.zza(parcel, zzcr.CREATOR);
                                IBinder readStrongBinder39 = parcel.readStrongBinder();
                                if (readStrongBinder39 != null) {
                                    IInterface queryLocalInterface39 = readStrongBinder39.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (queryLocalInterface39 instanceof zzeo) {
                                        zzeoVar = (zzeo) queryLocalInterface39;
                                    } else {
                                        zzeoVar = new zzeq(readStrongBinder39);
                                    }
                                }
                                zza(zzcrVar, zzeoVar);
                                break;
                            case 112:
                                zzct zzctVar = (zzct) zzd.zza(parcel, zzct.CREATOR);
                                IBinder readStrongBinder40 = parcel.readStrongBinder();
                                if (readStrongBinder40 != null) {
                                    IInterface queryLocalInterface40 = readStrongBinder40.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (queryLocalInterface40 instanceof zzeo) {
                                        zzeoVar = (zzeo) queryLocalInterface40;
                                    } else {
                                        zzeoVar = new zzeq(readStrongBinder40);
                                    }
                                }
                                zza(zzctVar, zzeoVar);
                                break;
                            case 113:
                                zzdz zzdzVar = (zzdz) zzd.zza(parcel, zzdz.CREATOR);
                                IBinder readStrongBinder41 = parcel.readStrongBinder();
                                if (readStrongBinder41 != null) {
                                    IInterface queryLocalInterface41 = readStrongBinder41.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (queryLocalInterface41 instanceof zzeo) {
                                        zzeoVar = (zzeo) queryLocalInterface41;
                                    } else {
                                        zzeoVar = new zzeq(readStrongBinder41);
                                    }
                                }
                                zza(zzdzVar, zzeoVar);
                                break;
                            case 114:
                                zzeb zzebVar = (zzeb) zzd.zza(parcel, zzeb.CREATOR);
                                IBinder readStrongBinder42 = parcel.readStrongBinder();
                                if (readStrongBinder42 != null) {
                                    IInterface queryLocalInterface42 = readStrongBinder42.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (queryLocalInterface42 instanceof zzeo) {
                                        zzeoVar = (zzeo) queryLocalInterface42;
                                    } else {
                                        zzeoVar = new zzeq(readStrongBinder42);
                                    }
                                }
                                zza(zzebVar, zzeoVar);
                                break;
                            case 115:
                                zzcx zzcxVar = (zzcx) zzd.zza(parcel, zzcx.CREATOR);
                                IBinder readStrongBinder43 = parcel.readStrongBinder();
                                if (readStrongBinder43 != null) {
                                    IInterface queryLocalInterface43 = readStrongBinder43.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (queryLocalInterface43 instanceof zzeo) {
                                        zzeoVar = (zzeo) queryLocalInterface43;
                                    } else {
                                        zzeoVar = new zzeq(readStrongBinder43);
                                    }
                                }
                                zza(zzcxVar, zzeoVar);
                                break;
                            case 116:
                                zzdh zzdhVar = (zzdh) zzd.zza(parcel, zzdh.CREATOR);
                                IBinder readStrongBinder44 = parcel.readStrongBinder();
                                if (readStrongBinder44 != null) {
                                    IInterface queryLocalInterface44 = readStrongBinder44.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (queryLocalInterface44 instanceof zzeo) {
                                        zzeoVar = (zzeo) queryLocalInterface44;
                                    } else {
                                        zzeoVar = new zzeq(readStrongBinder44);
                                    }
                                }
                                zza(zzdhVar, zzeoVar);
                                break;
                            case 117:
                                zzch zzchVar = (zzch) zzd.zza(parcel, zzch.CREATOR);
                                IBinder readStrongBinder45 = parcel.readStrongBinder();
                                if (readStrongBinder45 != null) {
                                    IInterface queryLocalInterface45 = readStrongBinder45.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (queryLocalInterface45 instanceof zzeo) {
                                        zzeoVar = (zzeo) queryLocalInterface45;
                                    } else {
                                        zzeoVar = new zzeq(readStrongBinder45);
                                    }
                                }
                                zza(zzchVar, zzeoVar);
                                break;
                            default:
                                switch (i) {
                                    case 119:
                                        zzcb zzcbVar = (zzcb) zzd.zza(parcel, zzcb.CREATOR);
                                        IBinder readStrongBinder46 = parcel.readStrongBinder();
                                        if (readStrongBinder46 != null) {
                                            IInterface queryLocalInterface46 = readStrongBinder46.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (queryLocalInterface46 instanceof zzeo) {
                                                zzeoVar = (zzeo) queryLocalInterface46;
                                            } else {
                                                zzeoVar = new zzeq(readStrongBinder46);
                                            }
                                        }
                                        zza(zzcbVar, zzeoVar);
                                        break;
                                    case 120:
                                        zzbw zzbwVar = (zzbw) zzd.zza(parcel, zzbw.CREATOR);
                                        IBinder readStrongBinder47 = parcel.readStrongBinder();
                                        if (readStrongBinder47 != null) {
                                            IInterface queryLocalInterface47 = readStrongBinder47.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (queryLocalInterface47 instanceof zzeo) {
                                                zzeoVar = (zzeo) queryLocalInterface47;
                                            } else {
                                                zzeoVar = new zzeq(readStrongBinder47);
                                            }
                                        }
                                        zza(zzbwVar, zzeoVar);
                                        break;
                                    case 121:
                                        zzcd zzcdVar = (zzcd) zzd.zza(parcel, zzcd.CREATOR);
                                        IBinder readStrongBinder48 = parcel.readStrongBinder();
                                        if (readStrongBinder48 != null) {
                                            IInterface queryLocalInterface48 = readStrongBinder48.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (queryLocalInterface48 instanceof zzeo) {
                                                zzeoVar = (zzeo) queryLocalInterface48;
                                            } else {
                                                zzeoVar = new zzeq(readStrongBinder48);
                                            }
                                        }
                                        zza(zzcdVar, zzeoVar);
                                        break;
                                    case 122:
                                        zzdd zzddVar = (zzdd) zzd.zza(parcel, zzdd.CREATOR);
                                        IBinder readStrongBinder49 = parcel.readStrongBinder();
                                        if (readStrongBinder49 != null) {
                                            IInterface queryLocalInterface49 = readStrongBinder49.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (queryLocalInterface49 instanceof zzeo) {
                                                zzeoVar = (zzeo) queryLocalInterface49;
                                            } else {
                                                zzeoVar = new zzeq(readStrongBinder49);
                                            }
                                        }
                                        zza(zzddVar, zzeoVar);
                                        break;
                                    case 123:
                                        zzdr zzdrVar = (zzdr) zzd.zza(parcel, zzdr.CREATOR);
                                        IBinder readStrongBinder50 = parcel.readStrongBinder();
                                        if (readStrongBinder50 != null) {
                                            IInterface queryLocalInterface50 = readStrongBinder50.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (queryLocalInterface50 instanceof zzeo) {
                                                zzeoVar = (zzeo) queryLocalInterface50;
                                            } else {
                                                zzeoVar = new zzeq(readStrongBinder50);
                                            }
                                        }
                                        zza(zzdrVar, zzeoVar);
                                        break;
                                    case 124:
                                        zzcv zzcvVar = (zzcv) zzd.zza(parcel, zzcv.CREATOR);
                                        IBinder readStrongBinder51 = parcel.readStrongBinder();
                                        if (readStrongBinder51 != null) {
                                            IInterface queryLocalInterface51 = readStrongBinder51.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (queryLocalInterface51 instanceof zzeo) {
                                                zzeoVar = (zzeo) queryLocalInterface51;
                                            } else {
                                                zzeoVar = new zzeq(readStrongBinder51);
                                            }
                                        }
                                        zza(zzcvVar, zzeoVar);
                                        break;
                                    default:
                                        switch (i) {
                                            case 126:
                                                zzcz zzczVar = (zzcz) zzd.zza(parcel, zzcz.CREATOR);
                                                IBinder readStrongBinder52 = parcel.readStrongBinder();
                                                if (readStrongBinder52 != null) {
                                                    IInterface queryLocalInterface52 = readStrongBinder52.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (queryLocalInterface52 instanceof zzeo) {
                                                        zzeoVar = (zzeo) queryLocalInterface52;
                                                    } else {
                                                        zzeoVar = new zzeq(readStrongBinder52);
                                                    }
                                                }
                                                zza(zzczVar, zzeoVar);
                                                break;
                                            case 127:
                                                zzdf zzdfVar = (zzdf) zzd.zza(parcel, zzdf.CREATOR);
                                                IBinder readStrongBinder53 = parcel.readStrongBinder();
                                                if (readStrongBinder53 != null) {
                                                    IInterface queryLocalInterface53 = readStrongBinder53.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (queryLocalInterface53 instanceof zzeo) {
                                                        zzeoVar = (zzeo) queryLocalInterface53;
                                                    } else {
                                                        zzeoVar = new zzeq(readStrongBinder53);
                                                    }
                                                }
                                                zza(zzdfVar, zzeoVar);
                                                break;
                                            case 128:
                                                zzdb zzdbVar = (zzdb) zzd.zza(parcel, zzdb.CREATOR);
                                                IBinder readStrongBinder54 = parcel.readStrongBinder();
                                                if (readStrongBinder54 != null) {
                                                    IInterface queryLocalInterface54 = readStrongBinder54.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (queryLocalInterface54 instanceof zzeo) {
                                                        zzeoVar = (zzeo) queryLocalInterface54;
                                                    } else {
                                                        zzeoVar = new zzeq(readStrongBinder54);
                                                    }
                                                }
                                                zza(zzdbVar, zzeoVar);
                                                break;
                                            case 129:
                                                zzdp zzdpVar = (zzdp) zzd.zza(parcel, zzdp.CREATOR);
                                                IBinder readStrongBinder55 = parcel.readStrongBinder();
                                                if (readStrongBinder55 != null) {
                                                    IInterface queryLocalInterface55 = readStrongBinder55.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (queryLocalInterface55 instanceof zzeo) {
                                                        zzeoVar = (zzeo) queryLocalInterface55;
                                                    } else {
                                                        zzeoVar = new zzeq(readStrongBinder55);
                                                    }
                                                }
                                                zza(zzdpVar, zzeoVar);
                                                break;
                                            case 130:
                                                zzdt zzdtVar = (zzdt) zzd.zza(parcel, zzdt.CREATOR);
                                                IBinder readStrongBinder56 = parcel.readStrongBinder();
                                                if (readStrongBinder56 != null) {
                                                    IInterface queryLocalInterface56 = readStrongBinder56.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (queryLocalInterface56 instanceof zzeo) {
                                                        zzeoVar = (zzeo) queryLocalInterface56;
                                                    } else {
                                                        zzeoVar = new zzeq(readStrongBinder56);
                                                    }
                                                }
                                                zza(zzdtVar, zzeoVar);
                                                break;
                                            case 131:
                                                zzdx zzdxVar = (zzdx) zzd.zza(parcel, zzdx.CREATOR);
                                                IBinder readStrongBinder57 = parcel.readStrongBinder();
                                                if (readStrongBinder57 != null) {
                                                    IInterface queryLocalInterface57 = readStrongBinder57.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (queryLocalInterface57 instanceof zzeo) {
                                                        zzeoVar = (zzeo) queryLocalInterface57;
                                                    } else {
                                                        zzeoVar = new zzeq(readStrongBinder57);
                                                    }
                                                }
                                                zza(zzdxVar, zzeoVar);
                                                break;
                                            case 132:
                                                zzcj zzcjVar = (zzcj) zzd.zza(parcel, zzcj.CREATOR);
                                                IBinder readStrongBinder58 = parcel.readStrongBinder();
                                                if (readStrongBinder58 != null) {
                                                    IInterface queryLocalInterface58 = readStrongBinder58.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (queryLocalInterface58 instanceof zzeo) {
                                                        zzeoVar = (zzeo) queryLocalInterface58;
                                                    } else {
                                                        zzeoVar = new zzeq(readStrongBinder58);
                                                    }
                                                }
                                                zza(zzcjVar, zzeoVar);
                                                break;
                                            case 133:
                                                zzdv zzdvVar = (zzdv) zzd.zza(parcel, zzdv.CREATOR);
                                                IBinder readStrongBinder59 = parcel.readStrongBinder();
                                                if (readStrongBinder59 != null) {
                                                    IInterface queryLocalInterface59 = readStrongBinder59.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (queryLocalInterface59 instanceof zzeo) {
                                                        zzeoVar = (zzeo) queryLocalInterface59;
                                                    } else {
                                                        zzeoVar = new zzeq(readStrongBinder59);
                                                    }
                                                }
                                                zza(zzdvVar, zzeoVar);
                                                break;
                                            case 134:
                                                zzcl zzclVar = (zzcl) zzd.zza(parcel, zzcl.CREATOR);
                                                IBinder readStrongBinder60 = parcel.readStrongBinder();
                                                if (readStrongBinder60 != null) {
                                                    IInterface queryLocalInterface60 = readStrongBinder60.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (queryLocalInterface60 instanceof zzeo) {
                                                        zzeoVar = (zzeo) queryLocalInterface60;
                                                    } else {
                                                        zzeoVar = new zzeq(readStrongBinder60);
                                                    }
                                                }
                                                zza(zzclVar, zzeoVar);
                                                break;
                                            case 135:
                                                zzef zzefVar = (zzef) zzd.zza(parcel, zzef.CREATOR);
                                                IBinder readStrongBinder61 = parcel.readStrongBinder();
                                                if (readStrongBinder61 != null) {
                                                    IInterface queryLocalInterface61 = readStrongBinder61.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (queryLocalInterface61 instanceof zzeo) {
                                                        zzeoVar = (zzeo) queryLocalInterface61;
                                                    } else {
                                                        zzeoVar = new zzeq(readStrongBinder61);
                                                    }
                                                }
                                                zza(zzefVar, zzeoVar);
                                                break;
                                            default:
                                                return false;
                                        }
                                }
                        }
                }
        }
        parcel2.writeNoException();
        return true;
    }
}
