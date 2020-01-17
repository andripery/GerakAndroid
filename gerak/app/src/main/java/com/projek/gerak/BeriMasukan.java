package com.projek.gerak;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projek.gerak.Table.Masukan;

public class BeriMasukan extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataRef;
    private String userKey;
    private EditText edtmasukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.berimasukan);

    }

    public void insertMasukanClick(View view){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDataRef = mDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userKey = user.getUid();
        edtmasukan = (EditText) findViewById(R.id.tbBeriMasukan);

        mDataRef.child("user").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue(String.class);
                Log.d(TAG, "Name: " + username);
                String masukan = edtmasukan.getText().toString();
                insertMasukan(userKey, username, masukan);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void insertMasukan(String userId, String username, String saran){
        Masukan masukan = new Masukan(username, saran);
        mDataRef = mDatabase.getReference().child("masukan").child(userId).push();
        mDataRef.setValue(masukan);

        Toast.makeText(BeriMasukan.this, "Berhasil beri masukan",
                Toast.LENGTH_SHORT).show();

        Intent i = new Intent(BeriMasukan.this,Home.class);
        i.putExtra("menu", "akun");
        startActivity(i);
        finish();
    }

    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("menu", "akun");
        startActivity(intent);
    }
}