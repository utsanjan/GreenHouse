package com.example.instagram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.blog01.greenhouse.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* loaded from: classes.dex */
public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    private TextView ForgetPasswordLink;
    private Button LoginButton;
    private TextView NeedNewAccountLink;
    private EditText UserEmail;
    private EditText UserPassword;
    private Boolean emailAddressChecker;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.mAuth = FirebaseAuth.getInstance();
        this.NeedNewAccountLink = (TextView) findViewById(R.id.dont_hav_acc);
        this.ForgetPasswordLink = (TextView) findViewById(R.id.forget_password_link);
        this.UserEmail = (EditText) findViewById(R.id.login_email);
        this.UserPassword = (EditText) findViewById(R.id.login_password);
        this.LoginButton = (Button) findViewById(R.id.login_button);
        this.loadingBar = new ProgressDialog(this);
        this.NeedNewAccountLink.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.LoginActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.SendUserToRegisterActivity();
            }
        });
        this.ForgetPasswordLink.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.LoginActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, ResetPassordActivity.class));
            }
        });
        this.LoginButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.LoginActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.AllowingUserToLogin();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            this.loadingBar.setTitle("google Sign In");
            this.loadingBar.setMessage("Please wait, while we are allowing you to login using your Google Account...");
            this.loadingBar.setCanceledOnTouchOutside(true);
            this.loadingBar.show();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                Toast.makeText(this, "Please wait, while we are getting your auth result...", 0).show();
                return;
            }
            Toast.makeText(this, "Can't get Auth result.", 0).show();
            this.loadingBar.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        if (currentUser != null) {
            SendUserToMainActivity();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void AllowingUserToLogin() {
        String email = this.UserEmail.getText().toString();
        String password = this.UserPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please write your email...", 0).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", 0).show();
        } else {
            this.loadingBar.setTitle("Login");
            this.loadingBar.setMessage("Please wait, while we are allowing you to login into your Account...");
            this.loadingBar.setCanceledOnTouchOutside(true);
            this.loadingBar.show();
            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() { // from class: com.example.instagram.LoginActivity.4
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        LoginActivity.this.VerifyEmailAddress();
                        LoginActivity.this.loadingBar.dismiss();
                        return;
                    }
                    String message = task.getException().getMessage();
                    LoginActivity loginActivity = LoginActivity.this;
                    Toast.makeText(loginActivity, "Error occured: " + message, 0).show();
                    LoginActivity.this.loadingBar.dismiss();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void VerifyEmailAddress() {
        FirebaseUser user = this.mAuth.getCurrentUser();
        Boolean valueOf = Boolean.valueOf(user.isEmailVerified());
        this.emailAddressChecker = valueOf;
        if (valueOf.booleanValue()) {
            SendUserToMainActivity();
            return;
        }
        Toast.makeText(this, "Please verify your account", 0).show();
        this.mAuth.signOut();
    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(268468224);
        startActivity(mainIntent);
        finish();
    }

    private void SendUserToLoginActivity() {
        Intent mainIntent = new Intent(this, LoginActivity.class);
        mainIntent.addFlags(268468224);
        startActivity(mainIntent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendUserToRegisterActivity() {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}
