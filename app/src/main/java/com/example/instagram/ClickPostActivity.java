package com.example.instagram;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.blog01.greenhouse.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/* loaded from: classes.dex */
public class ClickPostActivity extends AppCompatActivity {
    private Button DeletePostButton;
    private Button EditPostButton;
    private TextView PostDescription;
    private ImageView PostImage;
    private String Postkey;
    private DatabaseReference clickPostRef;
    private String currentUserID;
    private String databaseUserID;
    private String description;
    private String image;
    private FirebaseAuth mAuth;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_post);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.mAuth = instance;
        this.currentUserID = instance.getCurrentUser().getUid();
        this.Postkey = getIntent().getExtras().get("PostKey").toString();
        this.clickPostRef = FirebaseDatabase.getInstance().getReference().child("Posts").child(this.Postkey);
        this.PostImage = (ImageView) findViewById(R.id.click_post_image);
        this.PostDescription = (TextView) findViewById(R.id.click_post_description);
        this.DeletePostButton = (Button) findViewById(R.id.delete_post_button);
        this.EditPostButton = (Button) findViewById(R.id.edit_post_button);
        this.DeletePostButton.setVisibility(4);
        this.EditPostButton.setVisibility(4);
        this.clickPostRef.addValueEventListener(new ValueEventListener() { // from class: com.example.instagram.ClickPostActivity.1
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ClickPostActivity.this.description = dataSnapshot.child("description").getValue().toString();
                    ClickPostActivity.this.image = dataSnapshot.child("postimage").getValue().toString();
                    ClickPostActivity.this.databaseUserID = dataSnapshot.child("uid").getValue().toString();
                    ClickPostActivity.this.PostDescription.setText(ClickPostActivity.this.description);
                    Picasso.with(ClickPostActivity.this).load(ClickPostActivity.this.image).into(ClickPostActivity.this.PostImage);
                    if (ClickPostActivity.this.currentUserID.equals(ClickPostActivity.this.databaseUserID)) {
                        ClickPostActivity.this.DeletePostButton.setVisibility(0);
                        ClickPostActivity.this.EditPostButton.setVisibility(0);
                    }
                    ClickPostActivity.this.EditPostButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.ClickPostActivity.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            ClickPostActivity.this.EditCurrentPost(ClickPostActivity.this.description);
                        }
                    });
                }
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        this.DeletePostButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.instagram.ClickPostActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ClickPostActivity.this.DeleteCurrentPost();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void EditCurrentPost(String description) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Post");
        final EditText inputField = new EditText(this);
        inputField.setText(description);
        builder.setView(inputField);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() { // from class: com.example.instagram.ClickPostActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                ClickPostActivity.this.clickPostRef.child("description").setValue(inputField.getText().toString());
                Toast.makeText(ClickPostActivity.this, "Post Updated successfully", 0).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.example.instagram.ClickPostActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(17170450);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void DeleteCurrentPost() {
        this.clickPostRef.removeValue();
        SendUserToMainActivity();
        Toast.makeText(this, "Post has been deleted", 0).show();
    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(268468224);
        startActivity(mainIntent);
        finish();
    }
}
