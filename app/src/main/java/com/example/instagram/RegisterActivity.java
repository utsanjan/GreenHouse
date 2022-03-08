package com.example.instagram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.blog01.greenhouse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* loaded from: classes.dex */
public class RegisterActivity extends AppCompatActivity {
    private Button CreateAccountButton;
    private EditText UserConfirmPassword;
    private EditText UserEmail;
    private EditText UserPassword;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.mAuth = FirebaseAuth.getInstance();
        this.UserEmail = (EditText) findViewById(R.id.register_email);
        this.UserPassword = (EditText) findViewById(R.id.register_password);
        this.UserConfirmPassword = (EditText) findViewById(R.id.register_conform_password);
        this.CreateAccountButton = (Button) findViewById(R.id.register_create_account);
        this.loadingBar = new ProgressDialog(this);
        this.CreateAccountButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.RegisterActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegisterActivity.this.CreateNewAccount();
            }
        });
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

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(268468224);
        startActivity(mainIntent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void CreateNewAccount() {
        String email = this.UserEmail.getText().toString();
        String password = this.UserPassword.getText().toString();
        String confirmPassword = this.UserConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please write your email...", 0).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", 0).show();
        } else if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please confirm your password...", 0).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "your password do not match with your confirm password...", 0).show();
        } else {
            this.loadingBar.setTitle("Creating New Account");
            this.loadingBar.setMessage("Please wait, while we are creating your new Account...");
            this.loadingBar.show();
            this.loadingBar.setCanceledOnTouchOutside(true);
            this.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() { // from class: com.example.instagram.RegisterActivity.2
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        RegisterActivity.this.SendEmailVerificationMsg();
                        RegisterActivity.this.loadingBar.dismiss();
                        return;
                    }
                    String message = task.getException().getMessage();
                    RegisterActivity registerActivity = RegisterActivity.this;
                    Toast.makeText(registerActivity, "Error Occured: " + message, 0).show();
                    RegisterActivity.this.loadingBar.dismiss();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendEmailVerificationMsg() {
        FirebaseUser user = this.mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.example.instagram.RegisterActivity.3
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registration is Successful, we've sent you a mail. please check and verify your account ", 0).show();
                        RegisterActivity.this.SendUserToLoginActivity();
                        RegisterActivity.this.mAuth.signOut();
                        return;
                    }
                    String error = task.getException().getMessage();
                    RegisterActivity registerActivity = RegisterActivity.this;
                    Toast.makeText(registerActivity, "Error Occurred: " + error, 0).show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.addFlags(268468224);
        startActivity(loginIntent);
        finish();
    }
}
