package com.projek.gerak;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentAkun extends Fragment {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataRef;
    private String userKey;
    private CircleImageView profileImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        final View view = lf.inflate(R.layout.akun, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDataRef = mDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userKey = user.getUid();


        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images").child(userKey);
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                profileImage = view.findViewById(R.id.profile_image);
                Glide.with(FragmentAkun.this /* context */)
                        .load(uri)
                        .into(profileImage);
            }
        });

        mDataRef.child("user").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue(String.class);
                if (dataSnapshot.hasChild("image")) {
//                    nope = dataSnapshot.child("nope").getValue(String.class);
//                    edtNope.setText(nope);
                }
                Log.d(TAG, "Name: " + username);
                EditText nama = (EditText) view.findViewById(R.id.tbNama);
                nama.setText(username);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return view;
    }
}
