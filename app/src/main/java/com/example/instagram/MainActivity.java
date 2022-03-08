package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blog01.greenhouse.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;
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
public class MainActivity extends AppCompatActivity {
    Boolean LikeChecker;
    private DatabaseReference LikesRef;
    private CircleImageView NavProfileImage;
    private TextView NavProfileUserName;
    private DatabaseReference PostsRef;
    private DatabaseReference UsersRef;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    String currentUserID;
    DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;
    Toolbar mToolbar;
    NavigationView navigationView;
    private RecyclerView postList;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mAuth = FirebaseAuth.getInstance();
        if (Build.VERSION.SDK_INT >= 19) {
            this.currentUserID = ((FirebaseUser) Objects.requireNonNull(this.mAuth.getCurrentUser())).getUid();
        }
        this.UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        this.PostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        this.LikesRef = FirebaseDatabase.getInstance().getReference().child("Likes");
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= 19) {
            ((ActionBar) Objects.requireNonNull(getSupportActionBar())).setTitle("Home");
        }
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawable_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.drawerLayout, R.string.drawer_open, R.string.drawer_close);
        this.actionBarDrawerToggle = actionBarDrawerToggle;
        this.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        this.actionBarDrawerToggle.syncState();
        if (Build.VERSION.SDK_INT >= 19) {
            ((ActionBar) Objects.requireNonNull(getSupportActionBar())).setDisplayHomeAsUpEnabled(true);
        }
        this.navigationView = (NavigationView) findViewById(R.id.navigation_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.all_user_post_list);
        this.postList = recyclerView;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        this.postList.setLayoutManager(linearLayoutManager);
        View navView = this.navigationView.inflateHeaderView(R.layout.navigation_header);
        this.NavProfileImage = (CircleImageView) navView.findViewById(R.id.nav_profile_image);
        this.NavProfileUserName = (TextView) navView.findViewById(R.id.nav_user_full_name);
        this.UsersRef.child(this.currentUserID).addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.MainActivity.1
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("fullname")) {
                        String fullname = dataSnapshot.child("fullname").getValue().toString();
                        MainActivity.this.NavProfileUserName.setText(fullname);
                    }
                    if (dataSnapshot.hasChild("profileimage")) {
                        String image = dataSnapshot.child("profileimage").getValue().toString();
                        Picasso.with(MainActivity.this).load(image).placeholder(R.drawable.profile).into(MainActivity.this.NavProfileImage);
                        return;
                    }
                    Toast.makeText(MainActivity.this, "Profile name do not exists...", 0).show();
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() { // from class: com.example.instagram.MainActivity.2
            @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
            public boolean onNavigationItemSelected(MenuItem item) {
                MainActivity.this.UserMenuSelector(item);
                return false;
            }
        });
        DisplayAllUsersPosts();
    }

    private void DisplayAllUsersPosts() {
        Query SortPostsInDescendingOrder = this.PostsRef.orderByChild("counter");
        FirebaseRecyclerAdapter<Posts, PostsViewHolder> firebaseRecyclerAdapter = new AnonymousClass3(Posts.class, R.layout.all_post_layout, PostsViewHolder.class, SortPostsInDescendingOrder);
        this.postList.setAdapter(firebaseRecyclerAdapter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.instagram.MainActivity$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass3 extends FirebaseRecyclerAdapter<Posts, PostsViewHolder> {
        AnonymousClass3(Class cls, int x1, Class cls2, Query x3) {
            super(cls, x1, cls2, x3);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void populateViewHolder(PostsViewHolder viewHolder, Posts model, int position) {
            final String PostKey = getRef(position).getKey();
            viewHolder.setFullname(model.getFullname());
            viewHolder.setTime(model.getTime());
            viewHolder.setDate(model.getDate());
            viewHolder.setDescription(model.getDescription());
            viewHolder.setProfileimage(MainActivity.this.getApplicationContext(), model.getProfileimage());
            viewHolder.setPostimage(MainActivity.this.getApplicationContext(), model.getPostimage());
            viewHolder.setLikeButtonStatus(PostKey);
            viewHolder.mView.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.MainActivity.3.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Intent clickPostIntent = new Intent(MainActivity.this, ClickPostActivity.class);
                    clickPostIntent.putExtra("PostKey", PostKey);
                    MainActivity.this.startActivity(clickPostIntent);
                }
            });
            viewHolder.CommentPostButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.MainActivity.3.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Intent commentsIntent = new Intent(MainActivity.this, CommentsActivity.class);
                    commentsIntent.putExtra("PostKey", PostKey);
                    MainActivity.this.startActivity(commentsIntent);
                }
            });
            viewHolder.LikePostButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.MainActivity.3.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    MainActivity.this.LikeChecker = true;
                    MainActivity.this.LikesRef.addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.MainActivity.3.3.1
                        static final /* synthetic */ boolean $assertionsDisabled = false;

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!MainActivity.this.LikeChecker.equals(true)) {
                                return;
                            }
                            if (dataSnapshot.child(PostKey).hasChild(MainActivity.this.currentUserID)) {
                                MainActivity.this.LikesRef.child(PostKey).child(MainActivity.this.currentUserID).removeValue();
                                MainActivity.this.LikeChecker = false;
                                return;
                            }
                            MainActivity.this.LikesRef.child(PostKey).child(MainActivity.this.currentUserID).setValue(true);
                            MainActivity.this.LikeChecker = false;
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class PostsViewHolder extends RecyclerView.ViewHolder {
        ImageButton CommentPostButton;
        TextView DisplayNoOfLikes;
        ImageButton LikePostButton;
        int countLikes;
        View mView;
        DatabaseReference LikesRef = FirebaseDatabase.getInstance().getReference().child("Likes");
        String currentUserId = ((FirebaseUser) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())).getUid();

        public PostsViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.LikePostButton = (ImageButton) itemView.findViewById(R.id.like_button);
            this.CommentPostButton = (ImageButton) this.mView.findViewById(R.id.comment_button);
            this.DisplayNoOfLikes = (TextView) this.mView.findViewById(R.id.display_no_of_likes);
        }

        void setLikeButtonStatus(final String PostKey) {
            this.LikesRef.addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.MainActivity.PostsViewHolder.1
                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(PostKey).hasChild(PostsViewHolder.this.currentUserId)) {
                        PostsViewHolder.this.countLikes = (int) dataSnapshot.child(PostKey).getChildrenCount();
                        PostsViewHolder.this.LikePostButton.setImageResource(R.drawable.like);
                        TextView textView = PostsViewHolder.this.DisplayNoOfLikes;
                        textView.setText(PostsViewHolder.this.countLikes + "Likes");
                        return;
                    }
                    PostsViewHolder.this.countLikes = (int) dataSnapshot.child(PostKey).getChildrenCount();
                    PostsViewHolder.this.LikePostButton.setImageResource(R.drawable.dislike);
                    TextView textView2 = PostsViewHolder.this.DisplayNoOfLikes;
                    textView2.setText(PostsViewHolder.this.countLikes + "Likes");
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }

        void setFullname(String fullname) {
            TextView username = (TextView) this.mView.findViewById(R.id.post_user_name);
            username.setText(fullname);
        }

        void setProfileimage(Context ctx, String profileimage) {
            CircleImageView image = (CircleImageView) this.mView.findViewById(R.id.post_profile_image);
            Picasso.with(ctx).load(profileimage).into(image);
        }

        public void setTime(String time) {
            TextView PostTime = (TextView) this.mView.findViewById(R.id.post_time);
            PostTime.setText("    " + time);
        }

        void setDate(String date) {
            TextView PostDate = (TextView) this.mView.findViewById(R.id.post_date);
            PostDate.setText("    " + date);
        }

        void setDescription(String description) {
            TextView PostDescription = (TextView) this.mView.findViewById(R.id.post_description);
            PostDescription.setText(description);
        }

        void setPostimage(Context ctx1, String postimage) {
            ImageView PostImage = (ImageView) this.mView.findViewById(R.id.post_image);
            Picasso.with(ctx1).load(postimage).into(PostImage);
        }
    }

    private void SendUserToPostActivity() {
        Intent addNewPostIntent = new Intent(this, PostActivity.class);
        startActivity(addNewPostIntent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        if (currentUser == null) {
            SendUserToLoginActivity();
        } else {
            CheckUserExistence();
        }
    }

    private void CheckUserExistence() {
        final String current_user_id = ((FirebaseUser) Objects.requireNonNull(this.mAuth.getCurrentUser())).getUid();
        this.UsersRef.addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.MainActivity.4
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(current_user_id)) {
                    MainActivity.this.SendUserToSetupActivity();
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void SendUserToFriendsActivity() {
        Intent friendsIntent = new Intent(this, FriendsActivity.class);
        startActivity(friendsIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendUserToSetupActivity() {
        Intent setupIntent = new Intent(this, SetupActivity.class);
        startActivity(setupIntent);
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void UserMenuSelector(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_Logout /* 2131230967 */:
                this.mAuth.signOut();
                SendUserToLoginActivity();
                return;
            case R.id.nav_home /* 2131230968 */:
                SendUserToMainActivity();
                Toast.makeText(this, "Home", 0).show();
                return;
            case R.id.nav_peoples /* 2131230969 */:
                SendUserToFindFriendsActivity();
                Toast.makeText(this, "Home", 0).show();
                return;
            case R.id.nav_post /* 2131230970 */:
                SendUserToPostActivity();
                return;
            case R.id.nav_profile /* 2131230971 */:
                SendUserToProfileActivity();
                Toast.makeText(this, "Profile", 0).show();
                return;
            case R.id.nav_profile_image /* 2131230972 */:
            default:
                return;
            case R.id.nav_settings /* 2131230973 */:
                SendUserToSettingsActivity();
                Toast.makeText(this, "Settings", 0).show();
                return;
        }
    }

    private void SendUserToMainActivity() {
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);
    }

    private void SendUserToSettingsActivity() {
        Intent loginIntent = new Intent(this, SettingsActivity.class);
        startActivity(loginIntent);
    }

    private void SendUserToProfileActivity() {
        Intent loginIntent = new Intent(this, ProfileActivity.class);
        startActivity(loginIntent);
    }

    private void SendUserToFindFriendsActivity() {
        Intent loginIntent = new Intent(this, FindFriendsActivity.class);
        startActivity(loginIntent);
    }
}
