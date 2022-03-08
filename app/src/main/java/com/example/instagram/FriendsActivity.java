package com.example.instagram;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
public class FriendsActivity extends AppCompatActivity {
    private DatabaseReference FriendsRef;
    private DatabaseReference UsersRef;
    private FirebaseAuth mAuth;
    private RecyclerView myFriendList;
    private String online_user_id;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        this.online_user_id = instance.getCurrentUser().getUid();
        this.FriendsRef = FirebaseDatabase.getInstance().getReference().child("Friends").child(this.online_user_id);
        this.UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.friend_list);
        this.myFriendList = recyclerView;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        this.myFriendList.setLayoutManager(linearLayoutManager);
        DisplayAllFriends();
    }

    private void DisplayAllFriends() {
        FirebaseRecyclerAdapter<Friends, FriendsViewHolder> firebaseRecyclerAdapter = new AnonymousClass1(Friends.class, R.layout.all_users_display_layout, FriendsViewHolder.class, this.FriendsRef);
        this.myFriendList.setAdapter(firebaseRecyclerAdapter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.instagram.FriendsActivity$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends FirebaseRecyclerAdapter<Friends, FriendsViewHolder> {
        AnonymousClass1(Class cls, int x1, Class cls2, Query x3) {
            super(cls, x1, cls2, x3);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void populateViewHolder(FriendsViewHolder friendsViewHolder, Friends friends, int i) {
            friendsViewHolder.setDate(friends.getDate());
            String usersIDs = getRef(i).getKey();
            FriendsActivity.this.UsersRef.child(usersIDs).addValueEventListener(new C00181(friendsViewHolder, usersIDs));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.example.instagram.FriendsActivity$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public class C00181 implements ValueEventListener {
            final /* synthetic */ FriendsViewHolder val$friendsViewHolder;
            final /* synthetic */ String val$usersIDs;

            C00181(FriendsViewHolder friendsViewHolder, String str) {
                this.val$friendsViewHolder = friendsViewHolder;
                this.val$usersIDs = str;
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    final String userName = dataSnapshot.child("fullname").getValue().toString();
                    String profileImage = dataSnapshot.child("profileimage").getValue().toString();
                    this.val$friendsViewHolder.setFullname(userName);
                    this.val$friendsViewHolder.setProfileimage(FriendsActivity.this.getApplicationContext(), profileImage);
                    this.val$friendsViewHolder.mView.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.FriendsActivity.1.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            CharSequence[] options = {userName + "'s Profile", "Send Messages"};
                            AlertDialog.Builder builder = new AlertDialog.Builder(FriendsActivity.this);
                            builder.setTitle("Select Options");
                            builder.setItems(options, new DialogInterface.OnClickListener() { // from class: com.example.instagram.FriendsActivity.1.1.1.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        Intent profileIntent = new Intent(FriendsActivity.this, PersonProfileActivity.class);
                                        profileIntent.putExtra("visit_user_id", C00181.this.val$usersIDs);
                                        FriendsActivity.this.startActivity(profileIntent);
                                    }
                                    if (which == 1) {
                                        Intent chatIntent = new Intent(FriendsActivity.this, PersonProfileActivity.class);
                                        chatIntent.putExtra("visit_user_id", C00181.this.val$usersIDs);
                                        FriendsActivity.this.startActivity(chatIntent);
                                    }
                                }
                            });
                            builder.show();
                        }
                    });
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class FriendsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public FriendsViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
        }

        public void setProfileimage(Context ctx, String profileimage) {
            CircleImageView myImage = (CircleImageView) this.mView.findViewById(R.id.all_user_profile_image);
            Picasso.with(ctx).load(profileimage).placeholder(R.drawable.profile).into(myImage);
        }

        public void setFullname(String fullname) {
            TextView myName = (TextView) this.mView.findViewById(R.id.all_users_profile_full_name);
            myName.setText(fullname);
        }

        public void setDate(String date) {
            TextView friendsDate = (TextView) this.mView.findViewById(R.id.all_users_status);
            friendsDate.setText("Friends @" + date);
        }
    }
}
