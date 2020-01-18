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

public class Titip extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataRef;
    private String userKey;
    private EditText edtLokasiPenerima;
    private EditText edtDetailPenerima;
    private EditText edtNamaPenerima;
    private EditText edtNopePenerima;
    private EditText edtDeskripsiBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titip);
    }

    public void insertTitipClick(View view) {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDataRef = mDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userKey = user.getUid();
        edtLokasiPenerima = (EditText) findViewById(R.id.tbLokasi_titip);
        edtDetailPenerima = (EditText) findViewById(R.id.tbLokasiDetail_titip);
        edtNamaPenerima = (EditText) findViewById(R.id.tbPenerima_titip);
        edtNopePenerima = (EditText) findViewById(R.id.tbPenerimaTelepon_titip);
        edtDeskripsiBarang = (EditText) findViewById(R.id.tbDeskripsiBarang_titip);

        mDataRef.child("user").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue(String.class);
                Log.d(TAG, "Name: " + username);
                String lokasiPenerima = edtLokasiPenerima.getText().toString();
                String detailPenerima = edtDetailPenerima.getText().toString();
                String namaPenerima = edtNamaPenerima.getText().toString();
                String nopePenerima = edtNopePenerima.getText().toString();
                String deskripsiBarang = edtDeskripsiBarang.getText().toString();

                insertTitip(userKey, username, lokasiPenerima, detailPenerima, namaPenerima, nopePenerima, deskripsiBarang);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void insertTitip(String userId, String username, String lokasiPenerima,String detailPenerima, String namaPenerima,
                               String nopePenerima, String deskripsiBarang) {
        com.projek.gerak.Table.Titip titip = new com.projek.gerak.Table.Titip(username,
                lokasiPenerima, detailPenerima, namaPenerima, nopePenerima, deskripsiBarang);
        mDataRef = mDatabase.getReference().child("titip").child(userId).push();
        mDataRef.setValue(titip);

        Toast.makeText(Titip.this, "Berhasil input titip barang",
                Toast.LENGTH_SHORT).show();

        Intent i = new Intent(Titip.this, Home.class);
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
