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
import androidx.core.app.NotificationCompat;
import com.blog01.greenhouse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
public class SetupActivity extends AppCompatActivity {
    static final int Gallery_Pick = 1;
    private EditText CountryName;
    private EditText FullName;
    private CircleImageView ProfileImage;
    private Button SaveInformationbuttion;
    private EditText UserName;
    private StorageReference UserProfileImageRef;
    private DatabaseReference UsersRef;
    String currentUserID;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;
    private int res;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        this.currentUserID = ((FirebaseUser) Objects.requireNonNull(instance.getCurrentUser())).getUid();
        this.UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(this.currentUserID);
        this.UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
        this.UserName = (EditText) findViewById(R.id.setup_username);
        this.FullName = (EditText) findViewById(R.id.setup_full_name);
        this.CountryName = (EditText) findViewById(R.id.setup_country_name);
        this.SaveInformationbuttion = (Button) findViewById(R.id.setup_button);
        this.ProfileImage = (CircleImageView) findViewById(R.id.setup_profile_image);
        this.loadingBar = new ProgressDialog(this);
        this.SaveInformationbuttion.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.SetupActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SetupActivity.this.SaveAccountSetupInformation();
            }
        });
        this.ProfileImage.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.SetupActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction("android.intent.action.GET_CONTENT");
                galleryIntent.setType("image/*");
                SetupActivity.this.startActivityForResult(galleryIntent, 1);
            }
        });
        this.UsersRef.addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.SetupActivity.3
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    return;
                }
                if (dataSnapshot.hasChild("profileimage")) {
                    String image = dataSnapshot.child("profileimage").getValue().toString();
                    Picasso.with(SetupActivity.this).load(image).placeholder(R.drawable.profile).into(SetupActivity.this.ProfileImage);
                    return;
                }
                Toast.makeText(SetupActivity.this, "Please select profile image first.", 0).show();
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
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
            this.res = resultCode;
            if (resultCode == -1) {
                this.loadingBar.setTitle("Profile Image");
                this.loadingBar.setMessage("Please wait, while we updating your profile image...");
                this.loadingBar.setCanceledOnTouchOutside(true);
                this.loadingBar.show();
                Uri resultUri = result.getUri();
                StorageReference storageReference = this.UserProfileImageRef;
                StorageReference filePath = storageReference.child(this.currentUserID + ".jpg");
                filePath.putFile(resultUri).addOnCompleteListener((OnCompleteListener) new AnonymousClass4());
                return;
            }
            Toast.makeText(this, "Error Occurred: Image can not be cropped. Try Again.", 0).show();
            this.loadingBar.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.instagram.SetupActivity$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass4 implements OnCompleteListener<UploadTask.TaskSnapshot> {
        AnonymousClass4() {
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public void onComplete(Task<UploadTask.TaskSnapshot> task) {
            if (task.isSuccessful()) {
                Toast.makeText(SetupActivity.this, "Profile Image stored successfully...", 0).show();
                Task<Uri> result = task.getResult().getMetadata().getReference().getDownloadUrl();
                result.addOnSuccessListener(new OnSuccessListener<Uri>() { // from class: com.example.instagram.SetupActivity.4.1
                    public void onSuccess(Uri uri) {
                        String downloadUrl = uri.toString();
                        SetupActivity.this.UsersRef.child("profileimage").setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.example.instagram.SetupActivity.4.1.1
                            @Override // com.google.android.gms.tasks.OnCompleteListener
                            public void onComplete(Task<Void> task2) {
                                if (task2.isSuccessful()) {
                                    Toast.makeText(SetupActivity.this, "Profile Image stored Successfully...", 0).show();
                                    SetupActivity.this.loadingBar.dismiss();
                                    return;
                                }
                                String message = task2.getException().getMessage();
                                SetupActivity setupActivity = SetupActivity.this;
                                Toast.makeText(setupActivity, "Error: " + message, 0).show();
                                SetupActivity.this.loadingBar.dismiss();
                            }
                        });
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SaveAccountSetupInformation() {
        String username = this.UserName.getText().toString();
        String fullname = this.FullName.getText().toString();
        String country = this.CountryName.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please write your username...", 0).show();
        }
        if (TextUtils.isEmpty(fullname)) {
            Toast.makeText(this, "Please write your full name...", 0).show();
        }
        if (TextUtils.isEmpty(country)) {
            Toast.makeText(this, "Please write your country...", 0).show();
        }
        if (this.res != -1) {
            Toast.makeText(this, "Profile pic is Compulsory due to security reasons!!", 0).show();
            return;
        }
        this.loadingBar.setTitle("Saving Information");
        this.loadingBar.setMessage("Please wait, while we are creating your new Account...");
        this.loadingBar.show();
        this.loadingBar.setCanceledOnTouchOutside(true);
        HashMap userMap = new HashMap();
        userMap.put("username", username);
        userMap.put("fullname", fullname);
        userMap.put("country", country);
        userMap.put(NotificationCompat.CATEGORY_STATUS, "none");
        userMap.put("gender", "none");
        userMap.put("dob", "none");
        userMap.put("relationshipstatus", "none");
        this.UsersRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() { // from class: com.example.instagram.SetupActivity.5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task task) {
                if (task.isSuccessful()) {
                    SetupActivity.this.SendUserToMainActivity();
                    Toast.makeText(SetupActivity.this, "your Account is created Successfully.", 1).show();
                    SetupActivity.this.loadingBar.dismiss();
                    return;
                }
                String message = task.getException().getMessage();
                SetupActivity setupActivity = SetupActivity.this;
                Toast.makeText(setupActivity, "Error Occured: " + message, 0).show();
                SetupActivity.this.loadingBar.dismiss();
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
