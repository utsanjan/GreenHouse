package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.blog01.greenhouse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/* loaded from: classes.dex */
public class ResetPassordActivity extends AppCompatActivity {
    private EditText ResetEmailInput;
    private Button ResetPasswordEmailButton;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_passord);
        this.mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.forget_password_toolbar);
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Reset Password");
        this.ResetPasswordEmailButton = (Button) findViewById(R.id.send_email);
        this.ResetEmailInput = (EditText) findViewById(R.id.reset_email);
        this.ResetPasswordEmailButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.ResetPassordActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String userEmail = ResetPassordActivity.this.ResetEmailInput.getText().toString();
                if (TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(ResetPassordActivity.this, "Please write your valid address...", 0).show();
                } else {
                    ResetPassordActivity.this.mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.example.instagram.ResetPassordActivity.1.1
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ResetPassordActivity.this, "Please check your email Account, If you want to Reset your Password", 0).show();
                                ResetPassordActivity.this.startActivity(new Intent(ResetPassordActivity.this, LoginActivity.class));
                                return;
                            }
                            String message = task.getException().getMessage();
                            ResetPassordActivity resetPassordActivity = ResetPassordActivity.this;
                            Toast.makeText(resetPassordActivity, "Error Occurred" + message, 0).show();
                        }
                    });
                }
            }
        });
    }
}
