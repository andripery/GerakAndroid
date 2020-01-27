package com.projek.gerak;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projek.gerak.Table.Masukan;
import com.projek.gerak.Table.User;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Akun extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDataRef = mDatabase.getReference();
    private FirebaseUser firebaseUser = mAuth.getCurrentUser();
    private String userKey = firebaseUser.getUid();
    private EditText edtNama;
    private EditText edtNope;
    private EditText edtPass;
    private EditText edtAlamat;
    private String nama = "";
    private String nope = "";
    private String password = "";
    private String alamat = "";
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        edtNama = (EditText) findViewById(R.id.tbNama);
        edtNope = (EditText) findViewById(R.id.tbNope);
        edtAlamat = (EditText) findViewById(R.id.tbAlamat);
        edtPass = (EditText) findViewById(R.id.tbPassword);

        mDataRef.child("user").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                nama = dataSnapshot.child("username").getValue(String.class);
                email = dataSnapshot.child("email").getValue(String.class);
                if (dataSnapshot.hasChild("nope")) {
                    nope = dataSnapshot.child("nope").getValue(String.class);
                    edtNope.setText(nope);
                }
                if (dataSnapshot.hasChild("alamat")) {
                    alamat = dataSnapshot.child("alamat").getValue(String.class);
                    edtAlamat.setText(alamat);
                }
                Log.d(TAG, "Name: " + nama);

                edtNama.setText(nama);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void updateUser(View view) {

        edtNama = (EditText) findViewById(R.id.tbNama);
        edtNope = (EditText) findViewById(R.id.tbNope);
        edtPass = (EditText) findViewById(R.id.tbPassword);
        edtAlamat = (EditText) findViewById(R.id.tbAlamat);

        mDataRef.child("user").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                Log.d(TAG, "Name: " + username);

                String nama = edtNama.getText().toString();
                String nope = edtNope.getText().toString();
                String password = edtPass.getText().toString();
                String alamat = edtAlamat.getText().toString();

                updateUser(userKey, nama, email, nope, password, alamat);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void updateUser(String userId, String username, final String email, String nope, final String password, String alamat) {
        User user = new User(username, email, nope, alamat);
        mDataRef.child("user").child(userId).setValue(user);

        mAuth = FirebaseAuth.getInstance();

//        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
//                .setDisplayName(username)
//                .build();
//
//        firebaseUser.updateProfile(userProfileChangeRequest)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "User profile updated.");
//                        }
//                    }
//                });

        final String userEmail = email;
        final String userPass = password;

        // Get auth credentials from the user for re-authentication
        AuthCredential credential = EmailAuthProvider
                .getCredential(email, password); // Current Login Credentials \\
        // Prompt the user to re-provide their sign-in credentials
//        firebaseUser.reauthenticate(credential)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Log.d(TAG, "User re-authenticated.");
//                        //Now change your email address \\
//                        //----------------Code for Changing Email Address----------\\
//                        firebaseUser.updateEmail(userEmail)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            Log.d(TAG, "User email address updated.");
//                                        }
//                                    }
//                                });
//                        //----------------------------------------------------------\\
//                    }
//                });

        if (userPass != null) {
            firebaseUser.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d(TAG, "User re-authenticated.");
                            //Now change your email address \\
                            //----------------Code for Changing Email Address----------\\
                            firebaseUser.updatePassword(userPass)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User password updated.");
                                            }
                                        }
                                    });
                            //----------------------------------------------------------\\
                        }
                    });
        }

//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(Akun.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

        Toast.makeText(Akun.this, "Berhasil update akun",
                Toast.LENGTH_SHORT).show();

        Intent i = new Intent(Akun.this, Home.class);
        i.putExtra("menu", "akun");
        startActivity(i);
        finish();
    }

    public void home(View view) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("menu", "akun");
        startActivity(intent);
    }
}
