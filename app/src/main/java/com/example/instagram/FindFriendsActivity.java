package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blog01.greenhouse.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

/* loaded from: classes.dex */
public class FindFriendsActivity extends AppCompatActivity {
    private ImageButton SearchButton;
    private EditText SearchInputText;
    private RecyclerView SearchResultList;
    private DatabaseReference allUsersDatabaseRef;
    private Toolbar mToolbar;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);
        this.allUsersDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");
        Toolbar toolbar = (Toolbar) findViewById(R.id.find_friends_appbar_layout);
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Peoples");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_result_list);
        this.SearchResultList = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.SearchResultList.setLayoutManager(new LinearLayoutManager(this));
        this.SearchButton = (ImageButton) findViewById(R.id.search_people_friends_button);
        this.SearchInputText = (EditText) findViewById(R.id.search_box_input);
        this.SearchButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.FindFriendsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String searchBoxInput = FindFriendsActivity.this.SearchInputText.getText().toString();
                FindFriendsActivity.this.SearchPeopleAndFriends(searchBoxInput);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SearchPeopleAndFriends(String searchBoxInput) {
        Toast.makeText(this, "Searching..", 1).show();
        Query startAt = this.allUsersDatabaseRef.orderByChild("fullname").startAt(searchBoxInput);
        Query searchPeopleAndFriendsQuery = startAt.endAt(searchBoxInput + "\uf8ff");
        FirebaseRecyclerAdapter<FindFriends, FindFriendsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FindFriends, FindFriendsViewHolder>(FindFriends.class, R.layout.all_users_display_layout, FindFriendsViewHolder.class, searchPeopleAndFriendsQuery) { // from class: com.example.instagram.FindFriendsActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            public void populateViewHolder(FindFriendsViewHolder findFriendsViewHolder, FindFriends findFriends, final int i) {
                findFriendsViewHolder.setFullname(findFriends.getFullname());
                findFriendsViewHolder.setStatus(findFriends.getStatus());
                findFriendsViewHolder.setProfileimage(FindFriendsActivity.this.getApplicationContext(), findFriends.getProfileimage());
                findFriendsViewHolder.mView.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.FindFriendsActivity.2.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        String visit_user_id = getRef(i).getKey();
                        Intent profileIntent = new Intent(FindFriendsActivity.this, PersonProfileActivity.class);
                        profileIntent.putExtra("visit_user_id", visit_user_id);
                        FindFriendsActivity.this.startActivity(profileIntent);
                    }
                });
            }
        };
        this.SearchResultList.setAdapter(firebaseRecyclerAdapter);
    }

    /* loaded from: classes.dex */
    public static class FindFriendsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public FindFriendsViewHolder(View itemView) {
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

        public void setStatus(String status) {
            TextView myStatus = (TextView) this.mView.findViewById(R.id.all_users_status);
            myStatus.setText(status);
        }
    }
}
