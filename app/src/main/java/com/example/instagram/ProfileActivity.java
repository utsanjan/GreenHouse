package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.blog01.greenhouse.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.Objects;

/* loaded from: classes.dex */
public class ProfileActivity extends AppCompatActivity {
    DatabaseReference PostsRef;
    private int countPosts = 0;
    String currentUserId;
    FirebaseAuth mAuth;
    private Button myPosts;
    DatabaseReference profileUserRef;
    private TextView userCountry;
    private TextView userDOB;
    private TextView userGender;
    private TextView userName;
    private TextView userProfName;
    private CircleImageView userProfileImage;
    private TextView userRelation;
    private TextView userStatus;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        this.currentUserId = ((FirebaseUser) Objects.requireNonNull(instance.getCurrentUser())).getUid();
        this.profileUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(this.currentUserId);
        this.PostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        this.userName = (TextView) findViewById(R.id.my_username);
        this.userProfName = (TextView) findViewById(R.id.my_profile_full_name);
        this.userStatus = (TextView) findViewById(R.id.my_profile_status);
        this.userCountry = (TextView) findViewById(R.id.my_country);
        this.userGender = (TextView) findViewById(R.id.my_gender);
        this.userRelation = (TextView) findViewById(R.id.my_relationship_status);
        this.userDOB = (TextView) findViewById(R.id.my_dob);
        this.userProfileImage = (CircleImageView) findViewById(R.id.my_profile_pic);
        Button button = (Button) findViewById(R.id.my_post_button);
        this.myPosts = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.ProfileActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ProfileActivity.this.SendUserToMyPostsActivity();
            }
        });
        Query startAt = this.PostsRef.orderByChild("uid").startAt(this.currentUserId);
        startAt.endAt(this.currentUserId + "\uf8ff").addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.ProfileActivity.2
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ProfileActivity.this.countPosts = (int) dataSnapshot.getChildrenCount();
                    Button button2 = ProfileActivity.this.myPosts;
                    button2.setText(ProfileActivity.this.countPosts + "  Posts");
                    return;
                }
                ProfileActivity.this.myPosts.setText("0 Posts");
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        this.profileUserRef.addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.ProfileActivity.3
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String myProfileImage = Objects.requireNonNull(dataSnapshot.child("profileimage").getValue()).toString();
                    String myUserName = Objects.requireNonNull(dataSnapshot.child("username").getValue()).toString();
                    String myProfileName = Objects.requireNonNull(dataSnapshot.child("fullname").getValue()).toString();
                    String myProfileStatus = Objects.requireNonNull(dataSnapshot.child(NotificationCompat.CATEGORY_STATUS).getValue()).toString();
                    String myDOB = Objects.requireNonNull(dataSnapshot.child("dob").getValue()).toString();
                    String myCountry = Objects.requireNonNull(dataSnapshot.child("country").getValue()).toString();
                    String myGender = Objects.requireNonNull(dataSnapshot.child("gender").getValue()).toString();
                    String myRelationStatus = Objects.requireNonNull(dataSnapshot.child("relationshipstatus").getValue()).toString();
                    Picasso.with(ProfileActivity.this).load(myProfileImage).placeholder(R.drawable.profile).into(ProfileActivity.this.userProfileImage);
                    TextView textView = ProfileActivity.this.userName;
                    textView.setText("@" + myUserName);
                    ProfileActivity.this.userProfName.setText(myProfileName);
                    ProfileActivity.this.userStatus.setText(myProfileStatus);
                    TextView textView2 = ProfileActivity.this.userDOB;
                    textView2.setText("DOB:" + myDOB);
                    TextView textView3 = ProfileActivity.this.userCountry;
                    textView3.setText("Country:" + myCountry);
                    TextView textView4 = ProfileActivity.this.userGender;
                    textView4.setText("Gender:" + myGender);
                    TextView textView5 = ProfileActivity.this.userRelation;
                    textView5.setText("Relationship:" + myRelationStatus);
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendUserToMyPostsActivity() {
        Intent myPostsIntent = new Intent(this, MyPostsActivity.class);
        startActivity(myPostsIntent);
    }
}
