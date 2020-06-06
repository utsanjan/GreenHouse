package com.cemkolaghat.collegeblog.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.database.DatabaseUtils;
import android.os.Bundle;

import com.cemkolaghat.collegeblog.R;
import com.cemkolaghat.collegeblog.databinding.ActivitySignupBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignupBinding activitySignupBinding;
    SignUpViewModel signUpViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup);
        signUpViewModel =new ViewModelProvider(this).get(SignUpViewModel.class);
    }
}