package com.projek.gerak;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Belanja extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataRef;
    private String userKey;
    private EditText edtLokasi;
    private EditText edtDeskripsiLokasi;
    private EditText edtDeskripsiBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.belanja);
    }

    public void insertBelanjaClick(View view) {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDataRef = mDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userKey = user.getUid();
        edtLokasi = (EditText) findViewById(R.id.tbLokasi_belanja);
        edtDeskripsiLokasi = (EditText) findViewById(R.id.tbDeskripsiSupermarket_belanja);
        edtDeskripsiBarang = (EditText) findViewById(R.id.tbDeskripsiBarang_belanja);

        mDataRef.child("user").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue(String.class);
                Log.d(TAG, "Name: " + username);
                String lokasi = edtLokasi.getText().toString();
                String deskripsiLokasi = edtDeskripsiLokasi.getText().toString();
                String deskripsiBarang = edtDeskripsiBarang.getText().toString();

                insertBelanja(userKey, username, lokasi, deskripsiLokasi, deskripsiBarang);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void insertBelanja(String userId, String username, String lokasi, String deskripsiLokasi, String deskripsiBarang) {
        com.projek.gerak.Table.Belanja belanja = new com.projek.gerak.Table.Belanja(username,
                lokasi, deskripsiLokasi, deskripsiBarang);
        mDataRef = mDatabase.getReference().child("belanja").child(userId).push();
        mDataRef.setValue(belanja);

        Toast.makeText(Belanja.this, "Berhasil input belanja",
                Toast.LENGTH_SHORT).show();

        Intent i = new Intent(Belanja.this, Home.class);
        i.putExtra("menu", "home");
        startActivity(i);
        finish();
    }

    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("menu", "home");
        startActivity(intent);
    }
}
