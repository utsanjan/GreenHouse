package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blog01.greenhouse.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

/* loaded from: classes.dex */
public class UsersPostActivity extends AppCompatActivity {
    Boolean LikeChecker;
    private DatabaseReference LikesRef;
    private DatabaseReference PostsRef;
    private String currentUserId;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private RecyclerView myPostLists;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        this.currentUserId = instance.getCurrentUser().getUid();
        this.PostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_posts_bar_layout);
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Posts");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.all_post_list);
        this.myPostLists = recyclerView;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        this.myPostLists.setLayoutManager(linearLayoutManager);
        DisplayAllPosts();
    }

    private void DisplayAllPosts() {
        FirebaseRecyclerAdapter<Posts, MyPostViewHolder> firebaseRecyclerAdapter = new AnonymousClass1(Posts.class, R.layout.all_post_layout, MyPostViewHolder.class, this.PostsRef);
        this.myPostLists.setAdapter(firebaseRecyclerAdapter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.instagram.UsersPostActivity$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends FirebaseRecyclerAdapter<Posts, MyPostViewHolder> {
        AnonymousClass1(Class cls, int x1, Class cls2, Query x3) {
            super(cls, x1, cls2, x3);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void populateViewHolder(MyPostViewHolder viewHolder, Posts model, final int i) {
            final String PostKey = getRef(i).getKey();
            viewHolder.setFullname(model.getFullname());
            viewHolder.setTime(model.getTime());
            viewHolder.setDate(model.getDate());
            viewHolder.setDescription(model.getDescription());
            viewHolder.setProfileimage(UsersPostActivity.this.getApplicationContext(), model.getProfileimage());
            viewHolder.setPostimage(UsersPostActivity.this.getApplicationContext(), model.getPostimage());
            viewHolder.setLikeButtonStatus(PostKey);
            viewHolder.mView.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.UsersPostActivity.1.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    String visit_user_id = AnonymousClass1.this.getRef(i).getKey();
                    Intent profileIntent = new Intent(UsersPostActivity.this, MyPostsActivity.class);
                    profileIntent.putExtra("visit_user_id", visit_user_id);
                    UsersPostActivity.this.startActivity(profileIntent);
                }
            });
            viewHolder.CommentPostButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.UsersPostActivity.1.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Intent commentsIntent = new Intent(UsersPostActivity.this, CommentsActivity.class);
                    commentsIntent.putExtra("PostKey", PostKey);
                    UsersPostActivity.this.startActivity(commentsIntent);
                }
            });
            viewHolder.LikePostButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.UsersPostActivity.1.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    UsersPostActivity.this.LikeChecker = true;
                    UsersPostActivity.this.LikesRef.addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.UsersPostActivity.1.3.1
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!UsersPostActivity.this.LikeChecker.equals(true)) {
                                return;
                            }
                            if (dataSnapshot.child(PostKey).hasChild(UsersPostActivity.this.currentUserId)) {
                                UsersPostActivity.this.LikesRef.child(PostKey).child(UsersPostActivity.this.currentUserId).removeValue();
                                UsersPostActivity.this.LikeChecker = false;
                                return;
                            }
                            UsersPostActivity.this.LikesRef.child(PostKey).child(UsersPostActivity.this.currentUserId).setValue(true);
                            UsersPostActivity.this.LikeChecker = false;
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
    public static class MyPostViewHolder extends RecyclerView.ViewHolder {
        ImageButton CommentPostButton;
        TextView DisplayNoOfLikes;
        ImageButton LikePostButton;
        int countLikes;
        View mView;
        DatabaseReference LikesRef = FirebaseDatabase.getInstance().getReference().child("Likes");
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        public MyPostViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.LikePostButton = (ImageButton) itemView.findViewById(R.id.like_button);
            this.CommentPostButton = (ImageButton) this.mView.findViewById(R.id.comment_button);
            this.DisplayNoOfLikes = (TextView) this.mView.findViewById(R.id.display_no_of_likes);
        }

        public void setLikeButtonStatus(final String PostKey) {
            this.LikesRef.addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.UsersPostActivity.MyPostViewHolder.1
                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(PostKey).hasChild(MyPostViewHolder.this.currentUserId)) {
                        MyPostViewHolder.this.countLikes = (int) dataSnapshot.child(PostKey).getChildrenCount();
                        MyPostViewHolder.this.LikePostButton.setImageResource(R.drawable.like);
                        TextView textView = MyPostViewHolder.this.DisplayNoOfLikes;
                        textView.setText(Integer.toString(MyPostViewHolder.this.countLikes) + "Likes");
                        return;
                    }
                    MyPostViewHolder.this.countLikes = (int) dataSnapshot.child(PostKey).getChildrenCount();
                    MyPostViewHolder.this.LikePostButton.setImageResource(R.drawable.dislike);
                    TextView textView2 = MyPostViewHolder.this.DisplayNoOfLikes;
                    textView2.setText(Integer.toString(MyPostViewHolder.this.countLikes) + "Likes");
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }

        public void setFullname(String fullname) {
            TextView username = (TextView) this.mView.findViewById(R.id.post_user_name);
            username.setText(fullname);
        }

        public void setProfileimage(Context ctx, String profileimage) {
            CircleImageView image = (CircleImageView) this.mView.findViewById(R.id.post_profile_image);
            Picasso.with(ctx).load(profileimage).into(image);
        }

        public void setTime(String time) {
            TextView PostTime = (TextView) this.mView.findViewById(R.id.post_time);
            PostTime.setText("    " + time);
        }

        public void setDate(String date) {
            TextView PostDate = (TextView) this.mView.findViewById(R.id.post_date);
            PostDate.setText("    " + date);
        }

        public void setDescription(String description) {
            TextView PostDescription = (TextView) this.mView.findViewById(R.id.post_description);
            PostDescription.setText(description);
        }

        public void setPostimage(Context ctx1, String postimage) {
            ImageView PostImage = (ImageView) this.mView.findViewById(R.id.post_image);
            Picasso.with(ctx1).load(postimage).into(PostImage);
        }
    }
}
