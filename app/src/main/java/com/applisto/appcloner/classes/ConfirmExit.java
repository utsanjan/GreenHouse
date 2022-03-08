package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

/* loaded from: classes2.dex */
public class ConfirmExit extends BackKeyHandler {
    private static final String TAG = ConfirmExit.class.getSimpleName();
    private boolean mConfirmExit;

    public ConfirmExit(CloneSettings cloneSettings) {
        this.mConfirmExit = cloneSettings.getBoolean("confirmExit", false).booleanValue();
        String str = TAG;
        Log.i(str, "ConfirmExit; confirmExit: " + this.mConfirmExit);
    }

    public void init(Context context) {
        if (this.mConfirmExit) {
            onCreate();
        }
    }

    @Override // com.applisto.appcloner.classes.BackKeyHandler
    protected boolean handleBackPressed(final Activity activity, Object token) {
        if (activity == null) {
            return true;
        }
        String appName = Utils.getAppName(activity);
        AlertDialog.Builder dialogBuilder = Utils.getDialogBuilder(activity);
        dialogBuilder.setMessage("Are you sure you want to exit " + appName + "?").setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.applisto.appcloner.classes.ConfirmExit.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
        return false;
    }
}
