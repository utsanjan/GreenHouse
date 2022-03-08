package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.blog01.greenhouse.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.Objects;

/* loaded from: classes.dex */
public class PersonProfileActivity extends AppCompatActivity {
    private String CURRENT_STATE;
    private Button CancelFriendReqButton;
    private DatabaseReference FriendRequestRef;
    private DatabaseReference FriendsRef;
    private DatabaseReference PostsRef;
    private Button SendFriendReqButton;
    private DatabaseReference UserRef;
    private int countPosts = 0;
    private String currentUserId;
    private FirebaseAuth mAuth;
    private Button myPosts;
    private DatabaseReference profileUserRef;
    private String receiverUserId;
    private String saveCurrentDate;
    private String senderUserId;
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
        setContentView(R.layout.activity_person_profile);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        this.senderUserId = instance.getCurrentUser().getUid();
        this.receiverUserId = getIntent().getExtras().get("visit_user_id").toString();
        this.UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        this.FriendRequestRef = FirebaseDatabase.getInstance().getReference().child("FriendRequests");
        this.FriendsRef = FirebaseDatabase.getInstance().getReference().child("Friends");
        this.PostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        IntializeFields();
        this.UserRef.child(this.receiverUserId).addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.PersonProfileActivity.1
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
                    Picasso.with(PersonProfileActivity.this).load(myProfileImage).placeholder(R.drawable.profile).into(PersonProfileActivity.this.userProfileImage);
                    TextView textView = PersonProfileActivity.this.userName;
                    textView.setText("@" + myUserName);
                    PersonProfileActivity.this.userProfName.setText(myProfileName);
                    PersonProfileActivity.this.userStatus.setText(myProfileStatus);
                    TextView textView2 = PersonProfileActivity.this.userDOB;
                    textView2.setText("DOB:" + myDOB);
                    TextView textView3 = PersonProfileActivity.this.userCountry;
                    textView3.setText("Country:" + myCountry);
                    TextView textView4 = PersonProfileActivity.this.userGender;
                    textView4.setText("Gender:" + myGender);
                    TextView textView5 = PersonProfileActivity.this.userRelation;
                    textView5.setText("Relationship:" + myRelationStatus);
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void IntializeFields() {
        this.userName = (TextView) findViewById(R.id.person_username);
        this.userProfName = (TextView) findViewById(R.id.person_profile_full_name);
        this.userStatus = (TextView) findViewById(R.id.person_profile_status);
        this.userCountry = (TextView) findViewById(R.id.person_country);
        this.userGender = (TextView) findViewById(R.id.person_gender);
        this.userRelation = (TextView) findViewById(R.id.person_relationship_status);
        this.userDOB = (TextView) findViewById(R.id.person_dob);
        this.userProfileImage = (CircleImageView) findViewById(R.id.person_profile_pic);
    }

    private void SendUserToMyPostsActivity() {
        Intent myPostsIntent = new Intent(this, UsersPostActivity.class);
        startActivity(myPostsIntent);
    }
}
