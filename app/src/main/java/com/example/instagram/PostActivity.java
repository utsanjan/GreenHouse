package com.example.instagram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.blog01.greenhouse.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

/* loaded from: classes.dex */
public class PostActivity extends AppCompatActivity {
    static final int Gallery_Pick = 1;
    private String Description;
    private EditText PostDescription;
    private StorageReference PostsImagesReference;
    private DatabaseReference PostsRef;
    private ImageButton SelectPostImage;
    private Button UpdatePostButton;
    private DatabaseReference UsersRef;
    private long countPosts = 0;
    private String current_user_id;
    private String downloadUrl;
    private Uri imageUri;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private String postRandomName;
    private String saveCurrentDate;
    private String saveCurrentTime;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        this.PostsImagesReference = FirebaseStorage.getInstance().getReference();
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        this.current_user_id = instance.getCurrentUser().getUid();
        this.UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        this.PostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        this.SelectPostImage = (ImageButton) findViewById(R.id.select_post_image);
        this.UpdatePostButton = (Button) findViewById(R.id.update_post_button);
        this.PostDescription = (EditText) findViewById(R.id.post_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.update_post_page_toolbar);
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        this.loadingBar = new ProgressDialog(this);
        if (Build.VERSION.SDK_INT >= 21) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } else {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("Update Post");
        this.SelectPostImage.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.PostActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                PostActivity.this.OpenGallery();
            }
        });
        this.UpdatePostButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.PostActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                PostActivity.this.ValidatePostInfo();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ValidatePostInfo() {
        String obj = this.PostDescription.getText().toString();
        this.Description = obj;
        if (this.imageUri == null) {
            Toast.makeText(getApplicationContext(), "Please Select A Post Image", 0).show();
        } else if (TextUtils.isEmpty(obj)) {
            Toast.makeText(getApplicationContext(), "Please Write Post Description", 0).show();
        } else {
            this.loadingBar.setTitle("Add New Post");
            this.loadingBar.setMessage("Please wait, while we are updating your new post...");
            this.loadingBar.show();
            this.loadingBar.setCanceledOnTouchOutside(true);
            StoringImageToFirebaseStorage();
        }
    }

    private void StoringImageToFirebaseStorage() {
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        this.saveCurrentDate = currentDate.format(calFordDate.getTime());
        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        this.saveCurrentTime = currentTime.format(calFordTime.getTime());
        this.postRandomName = "" + this.saveCurrentDate + this.saveCurrentTime;
        StorageReference child = this.PostsImagesReference.child("Post Images");
        final StorageReference filePath = child.child(this.imageUri.getLastPathSegment() + this.postRandomName + ".jpg");
        filePath.putFile(this.imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() { // from class: com.example.instagram.PostActivity.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.android.gms.tasks.Continuation
            public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (task.isSuccessful()) {
                    return filePath.getDownloadUrl();
                }
                throw task.getException();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() { // from class: com.example.instagram.PostActivity.3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downUri = task.getResult();
                    Toast.makeText(PostActivity.this, "Image stored successfully...", 0).show();
                    PostActivity.this.downloadUrl = downUri.toString();
                    PostActivity.this.SavingPostInformationToDatabase();
                    return;
                }
                String message = task.getException().getMessage();
                PostActivity postActivity = PostActivity.this;
                Toast.makeText(postActivity, "Error occured: " + message, 0).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SavingPostInformationToDatabase() {
        this.PostsRef.addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.PostActivity.5
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    PostActivity.this.countPosts = dataSnapshot.getChildrenCount();
                    return;
                }
                PostActivity.this.countPosts = 0L;
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        this.UsersRef.child(this.current_user_id).addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.PostActivity.6
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userFullName = Objects.requireNonNull(dataSnapshot.child("fullname").getValue()).toString();
                    String userProfileImage = Objects.requireNonNull(dataSnapshot.child("profileimage").getValue()).toString();
                    HashMap postsMap = new HashMap();
                    postsMap.put("uid", PostActivity.this.current_user_id);
                    postsMap.put("date", PostActivity.this.saveCurrentDate);
                    postsMap.put("time", PostActivity.this.saveCurrentTime);
                    postsMap.put("description", PostActivity.this.Description);
                    postsMap.put("postimage", PostActivity.this.downloadUrl);
                    postsMap.put("profileimage", userProfileImage);
                    postsMap.put("fullname", userFullName);
                    postsMap.put("counter", Long.valueOf(PostActivity.this.countPosts));
                    DatabaseReference databaseReference = PostActivity.this.PostsRef;
                    databaseReference.child(PostActivity.this.current_user_id + PostActivity.this.postRandomName).updateChildren(postsMap).addOnCompleteListener(new OnCompleteListener() { // from class: com.example.instagram.PostActivity.6.1
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public void onComplete(Task task) {
                            if (task.isSuccessful()) {
                                PostActivity.this.SendUserToMainActivity();
                                Toast.makeText(PostActivity.this, "New Post is updated successfully.", 0).show();
                                PostActivity.this.loadingBar.dismiss();
                                return;
                            }
                            Toast.makeText(PostActivity.this, "Error Occured while updating your post.", 0).show();
                            PostActivity.this.loadingBar.dismiss();
                        }
                    });
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction("android.intent.action.GET_CONTENT");
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1 && data != null) {
            Uri data2 = data.getData();
            this.imageUri = data2;
            this.SelectPostImage.setImageURI(data2);
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == 16908332) {
            SendUserToMainActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendUserToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
