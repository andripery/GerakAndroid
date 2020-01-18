package com.projek.gerak;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
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

public class Keahlian extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataRef;
    private String userKey;
    private EditText edtJudul;
    private EditText edtDeskripsiKeahlian;
    private EditText edtTanggal;
    private EditText edtJam;
    private Spinner spinnerSkillKeahlian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keahlian);

    }

    public void insertKeahlianClick(View view) {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDataRef = mDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userKey = user.getUid();
        edtJudul = (EditText) findViewById(R.id.tbJudul_keahlian);
        edtDeskripsiKeahlian = (EditText) findViewById(R.id.tbDeskripsiKeahlian);
        spinnerSkillKeahlian = (Spinner) findViewById(R.id.spinner_keahlian);
        edtTanggal = (EditText) findViewById(R.id.tbTanggal_keahlian);
        edtJam = (EditText) findViewById(R.id.tbJam_keahlian);

        mDataRef.child("user").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue(String.class);
                Log.d(TAG, "Name: " + username);
                String judul = edtJudul.getText().toString();
                String deskripsi = edtDeskripsiKeahlian.getText().toString();
                String tanggal = edtTanggal.getText().toString();
                String jam = edtJam.getText().toString();
                String skill = spinnerSkillKeahlian.getSelectedItem().toString();

                insertKeahlian(userKey, username, judul, deskripsi, skill, tanggal, jam);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void insertKeahlian(String userId, String username, String judul, String deskripsi, String skill, String tanggal, String jam) {
        com.projek.gerak.Table.Keahlian keahlian = new com.projek.gerak.Table.Keahlian(username,
                judul, deskripsi, skill, tanggal, jam);
        mDataRef = mDatabase.getReference().child("keahlian").child(userId).push();
        mDataRef.setValue(keahlian);

        Toast.makeText(Keahlian.this, "Berhasil input keahlian",
                Toast.LENGTH_SHORT).show();

        Intent i = new Intent(Keahlian.this, Home.class);
        i.putExtra("menu", "home");
        startActivity(i);
        finish();
    }

    public void home(View view) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("menu", "home");
        startActivity(intent);
    }
}
