package com.projek.gerak_freelancer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projek.gerak_freelancer.Model.User;

public class Daftar extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SighUpActivity";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText edtNama, edtKota, edtAlamat, edtNope, edtEmail, edtPass;
    private Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        widget();

        btnDaftar = findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(this);
    }

    public void widget(){
        edtNama = findViewById(R.id.edtNama);
        edtKota = findViewById(R.id.edtKota);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtNope = findViewById(R.id.edtNope);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
    }

    @Override
    public void onClick(View v){
        signUp();
    }

    public void signUp(){
        Log.d(TAG, "signIn");
        if (!validateForm()) {
            return;
        }

        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        //hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(Daftar.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user){
        String username = edtNama.getText().toString();
        String kota = edtKota.getText().toString();
        String alamat = edtAlamat.getText().toString();
        String nope = edtNope.getText().toString();

        User dataUser = new User(username, kota, alamat, nope, user.getEmail());

        mDatabase.child("user").child(user.getUid()).setValue(dataUser);

        Toast.makeText(Daftar.this, "Daftar Berhasil, Silahkan Login",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Daftar.this, MainActivity.class);
        startActivity(intent);
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(edtNama.getText().toString())) {
            edtNama.setError("Required");
            result = false;
        } else {
            edtNama.setError(null);
        }

        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            edtEmail.setError("Required");
            result = false;
        } else {
            edtEmail.setError(null);
        }

        if (TextUtils.isEmpty(edtPass.getText().toString())) {
            edtPass.setError("Required");
            result = false;
        } else {
            edtPass.setError(null);
        }
        return result;
    }

    public void masuk(View view){
        Intent intent = new Intent(Daftar.this, MainActivity.class);
        startActivity(intent);
    }
}
