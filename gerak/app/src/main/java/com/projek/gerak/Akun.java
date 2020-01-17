package com.projek.gerak;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projek.gerak.Table.Masukan;
import com.projek.gerak.Table.User;

public class Akun extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataRef;
    private String userKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

    }

    public void updateUser(View view){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDataRef = mDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userKey = user.getUid();

        mDataRef.child("user").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue(String.class);
                Log.d(TAG, "Name: " + username);
                EditText edtnama = (EditText) findViewById(R.id.tbNama);
                EditText edtemail = (EditText) findViewById(R.id.tbEmail);
                EditText edtnope = (EditText) findViewById(R.id.tbNope);
                EditText edtpassword = (EditText) findViewById(R.id.tbPassword);
                EditText edtalamat = (EditText) findViewById(R.id.tbAlamat);

                String nama = edtnama.getText().toString();
                String email = edtemail.getText().toString();
                String nope = edtnope.getText().toString();
                String password = edtpassword.getText().toString();
                String alamat = edtalamat.getText().toString();

                edtnama.setText(username);
                edtemail.setText(email);
                edtnope.setText(nope);
                edtalamat.setText(alamat);

                updateUser(userKey, username, email, nope, password, alamat);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void updateUser(String userId, String username, String email, String nope, String password, String alamat) {
        User user = new User(username, email, nope, alamat);
        mDataRef = mDatabase.getReference().child("user").child(userId);
        mDataRef.setValue(user);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        firebaseUser.updatePassword(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User password updated.");
                        }
                    }
                });

        Toast.makeText(Akun.this, "Berhasil beri masukan",
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
