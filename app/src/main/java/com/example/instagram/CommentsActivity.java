package com.example.instagram;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blog01.greenhouse.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CommentsActivity extends AppCompatActivity {
    private EditText CommentInputText;
    private RecyclerView CommentsList;
    private String Post_Key;
    private DatabaseReference PostsRef;
    private DatabaseReference UsersRef;
    private String current_user_id;
    private FirebaseAuth mAuth;
    private ImageButton postCommentButton;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        this.Post_Key = getIntent().getExtras().get("PostKey").toString();
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        this.current_user_id = instance.getCurrentUser().getUid();
        this.UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        this.PostsRef = FirebaseDatabase.getInstance().getReference().child("Posts").child(this.Post_Key).child("comments");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.comments_list);
        this.CommentsList = recyclerView;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        this.CommentsList.setLayoutManager(linearLayoutManager);
        this.CommentInputText = (EditText) findViewById(R.id.comment_input);
        ImageButton imageButton = (ImageButton) findViewById(R.id.comment_post_button);
        this.postCommentButton = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.CommentsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                CommentsActivity.this.UsersRef.child(CommentsActivity.this.current_user_id).addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.CommentsActivity.1.1
                    private DataSnapshot dataSnapshot;

                    @Override // com.google.firebase.database.ValueEventListener
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        this.dataSnapshot = dataSnapshot;
                        if (dataSnapshot.exists()) {
                            String userName = dataSnapshot.child("username").getValue().toString();
                            CommentsActivity.this.ValidateComment(userName);
                            CommentsActivity.this.CommentInputText.setText("");
                        }
                    }

                    @Override // com.google.firebase.database.ValueEventListener
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Comments, CommentsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Comments, CommentsViewHolder>(Comments.class, R.layout.all_comments_layout, CommentsViewHolder.class, this.PostsRef) { // from class: com.example.instagram.CommentsActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            public void populateViewHolder(CommentsViewHolder commentsViewHolder, Comments comments, int i) {
                commentsViewHolder.setUsername(comments.getUsername());
                commentsViewHolder.setComment(comments.getComment());
                commentsViewHolder.setDate(comments.getDate());
                commentsViewHolder.setTime(comments.getTime());
            }
        };
        this.CommentsList.setAdapter(firebaseRecyclerAdapter);
    }

    /* loaded from: classes.dex */
    public static class CommentsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
        }

        public void setUsername(String username) {
            TextView myUserName = (TextView) this.mView.findViewById(R.id.comment_username);
            myUserName.setText("@" + username + " ");
        }

        public void setComment(String comment) {
            TextView myComment = (TextView) this.mView.findViewById(R.id.comment_text);
            myComment.setText(comment);
        }

        public void setDate(String date) {
            TextView myDate = (TextView) this.mView.findViewById(R.id.comment_date);
            myDate.setText(date);
        }

        public void setTime(String time) {
            TextView myTime = (TextView) this.mView.findViewById(R.id.comment_time);
            myTime.setText(time);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ValidateComment(String userName) {
        String commentText = this.CommentInputText.getText().toString();
        if (TextUtils.isEmpty(commentText)) {
            Toast.makeText(this, "please write text to comment...", 0).show();
            return;
        }
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        String saveCurrentDate = currentDate.format(calFordDate.getTime());
        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        String saveCurrentTime = currentTime.format(calFordTime.getTime());
        String RandomKey = this.current_user_id + saveCurrentDate + saveCurrentTime;
        HashMap commentsMap = new HashMap();
        commentsMap.put("uid", this.current_user_id);
        commentsMap.put("comment", commentText);
        commentsMap.put("date", saveCurrentDate);
        commentsMap.put("time", saveCurrentTime);
        commentsMap.put("username", userName);
        this.PostsRef.child(RandomKey).updateChildren(commentsMap).addOnCompleteListener(new OnCompleteListener() { // from class: com.example.instagram.CommentsActivity.3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CommentsActivity.this, "you have commented successfully", 0).show();
                } else {
                    Toast.makeText(CommentsActivity.this, "Error Occurred, try again...", 0).show();
                }
            }
        });
    }
}
