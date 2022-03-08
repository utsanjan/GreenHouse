package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzav;
import com.google.android.gms.internal.firebase_auth.zzej;
import com.google.firebase.FirebaseError;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.zzt;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzeh {
    private static final SparseArray<Pair<String, String>> zza;

    /* JADX WARN: Removed duplicated region for block: B:42:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.firebase.FirebaseException zza(com.google.android.gms.common.api.Status r3) {
        /*
            Method dump skipped, instructions count: 478
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.api.internal.zzeh.zza(com.google.android.gms.common.api.Status):com.google.firebase.FirebaseException");
    }

    public static FirebaseException zza(Status status, AuthCredential authCredential, String str, String str2) {
        int statusCode = status.getStatusCode();
        if (!(statusCode == 17012 || statusCode == 17007 || statusCode == 17025)) {
            return zza(status);
        }
        return new FirebaseAuthUserCollisionException(zza(statusCode), zza(zzb(statusCode), status)).zza(authCredential).zza(str).zzb(str2);
    }

    public static FirebaseAuthMultiFactorException zza(FirebaseAuth firebaseAuth, zzej zzejVar, FirebaseUser firebaseUser) {
        zzav.zza(firebaseAuth);
        zzav.zza(zzejVar);
        Pair<String, String> pair = zza.get(17078);
        return new FirebaseAuthMultiFactorException((String) pair.first, (String) pair.second, zzt.zza(zzejVar, firebaseAuth, firebaseUser));
    }

    private static String zza(String str, Status status) {
        return TextUtils.isEmpty(status.getStatusMessage()) ? str : String.format(String.valueOf(str).concat(" [ %s ]"), status.getStatusMessage());
    }

    private static String zza(int i) {
        Pair<String, String> pair = zza.get(i);
        return pair != null ? (String) pair.first : "INTERNAL_ERROR";
    }

    private static String zzb(int i) {
        Pair<String, String> pair = zza.get(i);
        return pair != null ? (String) pair.second : "An internal error has occurred.";
    }

    static {
        SparseArray<Pair<String, String>> sparseArray = new SparseArray<>();
        zza = sparseArray;
        sparseArray.put(FirebaseError.ERROR_INVALID_CUSTOM_TOKEN, new Pair<>("ERROR_INVALID_CUSTOM_TOKEN", "The custom token format is incorrect. Please check the documentation."));
        zza.put(FirebaseError.ERROR_CUSTOM_TOKEN_MISMATCH, new Pair<>("ERROR_CUSTOM_TOKEN_MISMATCH", "The custom token corresponds to a different audience."));
        zza.put(FirebaseError.ERROR_INVALID_CREDENTIAL, new Pair<>("ERROR_INVALID_CREDENTIAL", "The supplied auth credential is malformed or has expired."));
        zza.put(FirebaseError.ERROR_INVALID_EMAIL, new Pair<>("ERROR_INVALID_EMAIL", "The email address is badly formatted."));
        zza.put(FirebaseError.ERROR_WRONG_PASSWORD, new Pair<>("ERROR_WRONG_PASSWORD", "The password is invalid or the user does not have a password."));
        zza.put(FirebaseError.ERROR_USER_MISMATCH, new Pair<>("ERROR_USER_MISMATCH", "The supplied credentials do not correspond to the previously signed in user."));
        zza.put(FirebaseError.ERROR_REQUIRES_RECENT_LOGIN, new Pair<>("ERROR_REQUIRES_RECENT_LOGIN", "This operation is sensitive and requires recent authentication. Log in again before retrying this request."));
        zza.put(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL, new Pair<>("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL", "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address."));
        zza.put(FirebaseError.ERROR_EMAIL_ALREADY_IN_USE, new Pair<>("ERROR_EMAIL_ALREADY_IN_USE", "The email address is already in use by another account."));
        zza.put(FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE, new Pair<>("ERROR_CREDENTIAL_ALREADY_IN_USE", "This credential is already associated with a different user account."));
        zza.put(FirebaseError.ERROR_USER_DISABLED, new Pair<>("ERROR_USER_DISABLED", "The user account has been disabled by an administrator."));
        zza.put(FirebaseError.ERROR_USER_TOKEN_EXPIRED, new Pair<>("ERROR_USER_TOKEN_EXPIRED", "The user's credential is no longer valid. The user must sign in again."));
        zza.put(FirebaseError.ERROR_USER_NOT_FOUND, new Pair<>("ERROR_USER_NOT_FOUND", "There is no user record corresponding to this identifier. The user may have been deleted."));
        zza.put(FirebaseError.ERROR_INVALID_USER_TOKEN, new Pair<>("ERROR_INVALID_USER_TOKEN", "This user's credential isn't valid for this project. This can happen if the user's token has been tampered with, or if the user isn't for the project associated with this API key."));
        zza.put(FirebaseError.ERROR_OPERATION_NOT_ALLOWED, new Pair<>("ERROR_OPERATION_NOT_ALLOWED", "The given sign-in provider is disabled for this Firebase project. Enable it in the Firebase console, under the sign-in method tab of the Auth section."));
        zza.put(FirebaseError.ERROR_WEAK_PASSWORD, new Pair<>("ERROR_WEAK_PASSWORD", "The given password is invalid."));
        zza.put(17029, new Pair<>("ERROR_EXPIRED_ACTION_CODE", "The out of band code has expired."));
        zza.put(17030, new Pair<>("ERROR_INVALID_ACTION_CODE", "The out of band code is invalid. This can happen if the code is malformed, expired, or has already been used."));
        zza.put(17031, new Pair<>("ERROR_INVALID_MESSAGE_PAYLOAD", "The email template corresponding to this action contains invalid characters in its message. Please fix by going to the Auth email templates section in the Firebase Console."));
        zza.put(17033, new Pair<>("ERROR_INVALID_RECIPIENT_EMAIL", "The email corresponding to this action failed to send as the provided recipient email address is invalid."));
        zza.put(17032, new Pair<>("ERROR_INVALID_SENDER", "The email template corresponding to this action contains an invalid sender email or name. Please fix by going to the Auth email templates section in the Firebase Console."));
        zza.put(17034, new Pair<>("ERROR_MISSING_EMAIL", "An email address must be provided."));
        zza.put(17035, new Pair<>("ERROR_MISSING_PASSWORD", "A password must be provided."));
        zza.put(17041, new Pair<>("ERROR_MISSING_PHONE_NUMBER", "To send verification codes, provide a phone number for the recipient."));
        zza.put(17042, new Pair<>("ERROR_INVALID_PHONE_NUMBER", "The format of the phone number provided is incorrect. Please enter the phone number in a format that can be parsed into E.164 format. E.164 phone numbers are written in the format [+][country code][subscriber number including area code]."));
        zza.put(17043, new Pair<>("ERROR_MISSING_VERIFICATION_CODE", "The Phone Auth Credential was created with an empty sms verification Code"));
        zza.put(17044, new Pair<>("ERROR_INVALID_VERIFICATION_CODE", "The sms verification code used to create the phone auth credential is invalid. Please resend the verification code sms and be sure use the verification code provided by the user."));
        zza.put(17045, new Pair<>("ERROR_MISSING_VERIFICATION_ID", "The Phone Auth Credential was created with an empty verification ID"));
        zza.put(17046, new Pair<>("ERROR_INVALID_VERIFICATION_ID", "The verification ID used to create the phone auth credential is invalid."));
        zza.put(17049, new Pair<>("ERROR_RETRY_PHONE_AUTH", "An error occurred during authentication using the PhoneAuthCredential. Please retry authentication."));
        zza.put(17051, new Pair<>("ERROR_SESSION_EXPIRED", "The sms code has expired. Please re-send the verification code to try again."));
        zza.put(17052, new Pair<>("ERROR_QUOTA_EXCEEDED", "The sms quota for this project has been exceeded."));
        zza.put(FirebaseError.ERROR_APP_NOT_AUTHORIZED, new Pair<>("ERROR_APP_NOT_AUTHORIZED", "This app is not authorized to use Firebase Authentication. Please verify that the correct package name and SHA-1 are configured in the Firebase Console."));
        zza.put(17063, new Pair<>("ERROR_API_NOT_AVAILABLE_WITHOUT_GOOGLE_PLAY", "The API that you are calling is not available on devices without Google Play services."));
        zza.put(17062, new Pair<>("ERROR_WEB_INTERNAL_ERROR", "There was an internal error in the web widget."));
        zza.put(17064, new Pair<>("ERROR_INVALID_CERT_HASH", "There was an error while trying to get your package certificate hash."));
        zza.put(17065, new Pair<>("ERROR_WEB_STORAGE_UNSUPPORTED", "This browser is not supported or 3rd party cookies and data may be disabled."));
        zza.put(17040, new Pair<>("ERROR_MISSING_CONTINUE_URI", "A continue URL must be provided in the request."));
        zza.put(17068, new Pair<>("ERROR_DYNAMIC_LINK_NOT_ACTIVATED", "Please activate Dynamic Links in the Firebase Console and agree to the terms and conditions."));
        zza.put(17071, new Pair<>("ERROR_INVALID_PROVIDER_ID", "The provider ID provided for the attempted web operation is invalid."));
        zza.put(17057, new Pair<>("ERROR_WEB_CONTEXT_ALREADY_PRESENTED", "A headful operation is already in progress. Please wait for that to finish."));
        zza.put(17058, new Pair<>("ERROR_WEB_CONTEXT_CANCELED", "The web operation was canceled by the user."));
        zza.put(17072, new Pair<>("ERROR_TENANT_ID_MISMATCH", "The provided tenant ID does not match the Auth instance's tenant ID."));
        zza.put(17073, new Pair<>("ERROR_UNSUPPORTED_TENANT_OPERATION", "This operation is not supported in a multi-tenant context."));
        zza.put(17074, new Pair<>("ERROR_INVALID_DYNAMIC_LINK_DOMAIN", "The provided dynamic link domain is not configured or authorized for the current project."));
        zza.put(17075, new Pair<>("ERROR_REJECTED_CREDENTIAL", "The request contains malformed or mismatching credentials"));
        zza.put(17077, new Pair<>("ERROR_PHONE_NUMBER_NOT_FOUND", "The provided phone number does not match any of the second factor phone numbers associated with this user."));
        zza.put(17079, new Pair<>("ERROR_INVALID_TENANT_ID", "The Auth instance's tenant ID is invalid."));
        zza.put(17078, new Pair<>("ERROR_SECOND_FACTOR_REQUIRED", "Please complete a second factor challenge to finish signing into this account."));
        zza.put(17080, new Pair<>("ERROR_API_NOT_AVAILABLE", "The API that you are calling is not available."));
        zza.put(17081, new Pair<>("ERROR_MISSING_MULTI_FACTOR_SESSION", "The request is missing proof of first factor successful sign-in."));
        zza.put(17082, new Pair<>("ERROR_MISSING_MULTI_FACTOR_INFO", "No second factor identifier is provided."));
        zza.put(17083, new Pair<>("ERROR_INVALID_MULTI_FACTOR_SESSION", "The request does not contain a valid proof of first factor successful sign-in."));
        zza.put(17084, new Pair<>("ERROR_MULTI_FACTOR_INFO_NOT_FOUND", "The user does not have a second factor matching the identifier provided."));
        zza.put(17085, new Pair<>("ERROR_ADMIN_RESTRICTED_OPERATION", "This operation is restricted to administrators only."));
        zza.put(17086, new Pair<>("ERROR_UNVERIFIED_EMAIL", "This operation requires a verified email."));
        zza.put(17087, new Pair<>("ERROR_SECOND_FACTOR_ALREADY_ENROLLED", "The second factor is already enrolled on this account."));
        zza.put(17088, new Pair<>("ERROR_MAXIMUM_SECOND_FACTOR_COUNT_EXCEEDED", "The maximum allowed number of second factors on a user has been exceeded."));
        zza.put(17089, new Pair<>("ERROR_UNSUPPORTED_FIRST_FACTOR", "Enrolling a second factor or signing in with a multi-factor account requires sign-in with a supported first factor."));
        zza.put(17090, new Pair<>("ERROR_EMAIL_CHANGE_NEEDS_VERIFICATION", "Multi-factor users must always have a verified email."));
        zza.put(17091, new Pair<>("ERROR_INTERNAL_SUCCESS_SIGN_OUT", "This is an internal error code indicating that the operation was successful but the user needs to be signed out."));
        zza.put(17093, new Pair<>("ERROR_MISSING_CLIENT_IDENTIFIER", "This request is missing a reCAPTCHA token."));
        zza.put(17094, new Pair<>("ERROR_MISSING_OR_INVALID_NONCE", "The request does not contain a valid nonce. This can occur if the SHA-256 hash of the provided raw nonce does not match the hashed nonce in the ID token payload."));
        zza.put(18001, new Pair<>("ERROR_USER_CANCELLED", "The user did not grant your application the permissions it requested."));
    }
}
