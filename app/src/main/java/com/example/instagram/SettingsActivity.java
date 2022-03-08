package com.example.instagram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import com.blog01.greenhouse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.HashMap;
import java.util.Objects;

/* loaded from: classes.dex */
public class SettingsActivity extends AppCompatActivity {
    static final int Gallery_Pick = 1;
    private DatabaseReference SettingsUserRef;
    private Button UpdateAccountSettingsButton;
    private StorageReference UserProfileImageRef;
    private String currentUserId;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private EditText userCountry;
    private EditText userDOB;
    private EditText userGender;
    private EditText userName;
    private CircleImageView userProfImage;
    private EditText userProfName;
    private EditText userRelation;
    private EditText userStatus;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        this.currentUserId = instance.getCurrentUser().getUid();
        this.SettingsUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(this.currentUserId);
        this.UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Account Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.userName = (EditText) findViewById(R.id.settings_username);
        this.userProfName = (EditText) findViewById(R.id.settings_profile_full_name);
        this.userStatus = (EditText) findViewById(R.id.settings_status);
        this.userCountry = (EditText) findViewById(R.id.settings_country);
        this.userGender = (EditText) findViewById(R.id.settings_gender);
        this.userRelation = (EditText) findViewById(R.id.settings_relationship_status);
        this.userDOB = (EditText) findViewById(R.id.settings_dob);
        this.userProfImage = (CircleImageView) findViewById(R.id.settings_profile_image);
        this.UpdateAccountSettingsButton = (Button) findViewById(R.id.update_acc_settings_buttons);
        this.loadingBar = new ProgressDialog(this);
        this.SettingsUserRef.addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.SettingsActivity.1
            private DataSnapshot dataSnapshot;

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                this.dataSnapshot = dataSnapshot;
                if (dataSnapshot.exists()) {
                    String myProfileImage = Objects.requireNonNull(dataSnapshot.child("profileimage").getValue()).toString();
                    String myUserName = dataSnapshot.child("username").getValue().toString();
                    String myProfileName = dataSnapshot.child("fullname").getValue().toString();
                    String myProfileStatus = dataSnapshot.child(NotificationCompat.CATEGORY_STATUS).getValue().toString();
                    String myDOB = dataSnapshot.child("dob").getValue().toString();
                    String myCountry = dataSnapshot.child("country").getValue().toString();
                    String myGender = dataSnapshot.child("gender").getValue().toString();
                    String myRelationStatus = dataSnapshot.child("relationshipstatus").getValue().toString();
                    Picasso.with(SettingsActivity.this).load(myProfileImage).placeholder(R.drawable.profile).into(SettingsActivity.this.userProfImage);
                    SettingsActivity.this.userName.setText(myUserName);
                    SettingsActivity.this.userProfName.setText(myProfileName);
                    SettingsActivity.this.userStatus.setText(myProfileStatus);
                    SettingsActivity.this.userDOB.setText(myDOB);
                    SettingsActivity.this.userCountry.setText(myCountry);
                    SettingsActivity.this.userGender.setText(myGender);
                    SettingsActivity.this.userRelation.setText(myRelationStatus);
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        this.UpdateAccountSettingsButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.SettingsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SettingsActivity.this.ValidateAccountInfo();
            }
        });
        this.userProfImage.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.SettingsActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction("android.intent.action.GET_CONTENT");
                galleryIntent.setType("image/*");
                SettingsActivity.this.startActivityForResult(galleryIntent, 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1 && data != null) {
            data.getData();
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1, 1).start(this);
        }
        if (requestCode == 203) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == -1) {
                this.loadingBar.setTitle("Profile Image");
                this.loadingBar.setMessage("Please wait, while we updating your profile image...");
                this.loadingBar.setCanceledOnTouchOutside(true);
                this.loadingBar.show();
                Uri resultUri = result.getUri();
                StorageReference storageReference = this.UserProfileImageRef;
                StorageReference filePath = storageReference.child(this.currentUserId + ".jpg");
                filePath.putFile(resultUri).addOnCompleteListener((OnCompleteListener) new AnonymousClass4());
                return;
            }
            Toast.makeText(this, "Error Occurred: Image can not be cropped. Try Again.", 0).show();
            this.loadingBar.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.instagram.SettingsActivity$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass4 implements OnCompleteListener<UploadTask.TaskSnapshot> {
        AnonymousClass4() {
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public void onComplete(Task<UploadTask.TaskSnapshot> task) {
            if (task.isSuccessful()) {
                Toast.makeText(SettingsActivity.this, "Profile Image stored successfully to Firebase storage...", 0).show();
                Task<Uri> result = task.getResult().getMetadata().getReference().getDownloadUrl();
                result.addOnSuccessListener(new OnSuccessListener<Uri>() { // from class: com.example.instagram.SettingsActivity.4.1
                    public void onSuccess(Uri uri) {
                        String downloadUrl = uri.toString();
                        SettingsActivity.this.SettingsUserRef.child("profileimage").setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.example.instagram.SettingsActivity.4.1.1
                            @Override // com.google.android.gms.tasks.OnCompleteListener
                            public void onComplete(Task<Void> task2) {
                                if (task2.isSuccessful()) {
                                    Intent selfIntent = new Intent(SettingsActivity.this, SettingsActivity.class);
                                    SettingsActivity.this.startActivity(selfIntent);
                                    Toast.makeText(SettingsActivity.this, "Profile Image stored to Firebase Database Successfully...", 0).show();
                                    SettingsActivity.this.loadingBar.dismiss();
                                    return;
                                }
                                String message = task2.getException().getMessage();
                                SettingsActivity settingsActivity = SettingsActivity.this;
                                Toast.makeText(settingsActivity, "Error: " + message, 0).show();
                                SettingsActivity.this.loadingBar.dismiss();
                            }
                        });
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ValidateAccountInfo() {
        String username = this.userName.getText().toString();
        String profileName = this.userProfName.getText().toString();
        String status = this.userStatus.getText().toString();
        String dob = this.userDOB.getText().toString();
        String country = this.userCountry.getText().toString();
        String gender = this.userGender.getText().toString();
        String relation = this.userRelation.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please write your username...", 0).show();
        } else if (TextUtils.isEmpty(profileName)) {
            Toast.makeText(this, "Please write your profile name...", 0).show();
        } else if (TextUtils.isEmpty(status)) {
            Toast.makeText(this, "Please write your status...", 0).show();
        } else if (TextUtils.isEmpty(dob)) {
            Toast.makeText(this, "Please write your date of birth...", 0).show();
        } else if (TextUtils.isEmpty(country)) {
            Toast.makeText(this, "Please write your country...", 0).show();
        } else if (TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Please write your gender...", 0).show();
        } else if (TextUtils.isEmpty(relation)) {
            Toast.makeText(this, "Please write your relationship status...", 0).show();
        } else {
            this.loadingBar.setTitle("Profile Image");
            this.loadingBar.setMessage("Please wait, while we updating your profile image...");
            this.loadingBar.setCanceledOnTouchOutside(true);
            this.loadingBar.show();
            UpdateAccountInfo(username, profileName, status, dob, country, gender, relation);
        }
    }

    private void UpdateAccountInfo(String username, String profileName, String status, String dob, String country, String gender, String relation) {
        HashMap userMap = new HashMap();
        userMap.put("username", username);
        userMap.put("fullname", profileName);
        userMap.put(NotificationCompat.CATEGORY_STATUS, status);
        userMap.put("dob", dob);
        userMap.put("country", country);
        userMap.put("gender", gender);
        userMap.put("relationshipstatus", relation);
        this.SettingsUserRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() { // from class: com.example.instagram.SettingsActivity.5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task task) {
                if (task.isSuccessful()) {
                    SettingsActivity.this.SendUserToMainActivity();
                    Toast.makeText(SettingsActivity.this, "Account Settings Updated Successfully", 0).show();
                    SettingsActivity.this.loadingBar.dismiss();
                    return;
                }
                Toast.makeText(SettingsActivity.this, "Error Occurred, while updating account setting info...", 0).show();
                SettingsActivity.this.loadingBar.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendUserToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(268468224);
        startActivity(mainIntent);
        finish();
    }
}
