package com.projek.gerak;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataRef;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private String userKey;

    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    private CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance();
//        mDataRef = mDatabase.getReference();
//        FirebaseUser user = mAuth.getCurrentUser();
//        userKey = user.getUid();
//
//        mDataRef.child("user").child(userKey).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String username = dataSnapshot.child("username").getValue(String.class);
//                Log.d(TAG, "Name: " + username);
//                TextView nama = (TextView) findViewById(R.id.txtNama_Home);
//                nama.setText("Hi, "+ username);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {}
//        });

        final ImageButton home = (ImageButton) findViewById(R.id.btnHome);
        final ImageButton pesan = (ImageButton) findViewById(R.id.btnChat);
        final ImageButton bantuan = (ImageButton) findViewById(R.id.btnHelp);
        final ImageButton akun = (ImageButton) findViewById(R.id.btnAkun);
        final TextView tvHome = (TextView) findViewById(R.id.txtHome);
        final TextView tvPesan = (TextView) findViewById(R.id.txtChat);
        final TextView tvBantuan = (TextView) findViewById(R.id.txtHelp);
        final TextView tvAkun = (TextView) findViewById(R.id.txtAkun);

        loadFragment(new FragmentHome());

        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("menu");
        if (s.equalsIgnoreCase("akun")) {
            loadFragment(new FragmentAkun());
            tvHome.setTextColor(Color.parseColor("#A5A5A5"));
            home.setBackgroundResource(R.drawable.ic_home_mute);
            tvPesan.setTextColor(Color.parseColor("#A5A5A5"));
            pesan.setBackgroundResource(R.drawable.ic_chat);
            tvBantuan.setTextColor(Color.parseColor("#A5A5A5"));
            bantuan.setBackgroundResource(R.drawable.ic_help);
            tvAkun.setTextColor(Color.parseColor("#374E4E"));
            akun.setBackgroundResource(R.drawable.ic_akun_active);
        } else if (s.equalsIgnoreCase("home")) {
            loadFragment(new FragmentHome());
        }


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new FragmentHome());
                tvHome.setTextColor(Color.parseColor("#374E4E"));
                home.setBackgroundResource(R.drawable.ic_home);
                tvPesan.setTextColor(Color.parseColor("#A5A5A5"));
                pesan.setBackgroundResource(R.drawable.ic_chat);
                tvBantuan.setTextColor(Color.parseColor("#A5A5A5"));
                bantuan.setBackgroundResource(R.drawable.ic_help);
                tvAkun.setTextColor(Color.parseColor("#A5A5A5"));
                akun.setBackgroundResource(R.drawable.ic_akun);
            }
        });

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new FragmentPesan());
                tvHome.setTextColor(Color.parseColor("#A5A5A5"));
                home.setBackgroundResource(R.drawable.ic_home_mute);
                tvPesan.setTextColor(Color.parseColor("#374E4E"));
                pesan.setBackgroundResource(R.drawable.ic_chat_active);
                tvBantuan.setTextColor(Color.parseColor("#A5A5A5"));
                bantuan.setBackgroundResource(R.drawable.ic_help);
                tvAkun.setTextColor(Color.parseColor("#A5A5A5"));
                akun.setBackgroundResource(R.drawable.ic_akun);
            }
        });

        bantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new FragmentBantuan());
                tvHome.setTextColor(Color.parseColor("#A5A5A5"));
                home.setBackgroundResource(R.drawable.ic_home_mute);
                tvPesan.setTextColor(Color.parseColor("#A5A5A5"));
                pesan.setBackgroundResource(R.drawable.ic_chat);
                tvBantuan.setTextColor(Color.parseColor("#374E4E"));
                bantuan.setBackgroundResource(R.drawable.ic_help_active);
                tvAkun.setTextColor(Color.parseColor("#A5A5A5"));
                akun.setBackgroundResource(R.drawable.ic_akun);
            }
        });

        akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new FragmentAkun());
                tvHome.setTextColor(Color.parseColor("#A5A5A5"));
                home.setBackgroundResource(R.drawable.ic_home_mute);
                tvPesan.setTextColor(Color.parseColor("#A5A5A5"));
                pesan.setBackgroundResource(R.drawable.ic_chat);
                tvBantuan.setTextColor(Color.parseColor("#A5A5A5"));
                bantuan.setBackgroundResource(R.drawable.ic_help);
                tvAkun.setTextColor(Color.parseColor("#374E4E"));
                akun.setBackgroundResource(R.drawable.ic_akun_active);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout, fragment);
        fragmentTransaction.commit();
    }

    public void berimasukan(View view) {
        Intent intent = new Intent(getApplicationContext(), BeriMasukan.class);
        startActivity(intent);
    }

    public void logoutAlert(View view) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.logout, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(subView);
        builder.create();
        builder.show();

    }

    public void keluar(View view) {
        FirebaseAuth.getInstance().signOut();
        finishAffinity();
        System.exit(0);
    }

    public void keahlian(View view) {
        Intent intent = new Intent(getApplicationContext(), Keahlian.class);
        startActivity(intent);
    }

    public void titip(View view) {
        Intent intent = new Intent(getApplicationContext(), Titip.class);
        startActivity(intent);
    }

    public void belanja(View view) {
        Intent intent = new Intent(getApplicationContext(), Belanja.class);
        startActivity(intent);
    }

    public void promo(View view) {
        Intent intent = new Intent(getApplicationContext(), Promo.class);
        startActivity(intent);
    }

    public void pesan(View view) {
        Intent intent = new Intent(getApplicationContext(), Pesan.class);
        startActivity(intent);
    }

    public void bantuan(View view) {
        Intent intent = new Intent(getApplicationContext(), Bantuan.class);
        startActivity(intent);
    }

    public void akun(View view) {
        Intent intent = new Intent(getApplicationContext(), Akun.class);
        startActivity(intent);
    }

    public void chooseImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                Bitmap bitmap = BitmapFactory.decodeFile(filePath.toString());
                Log.d("Profile Picture", "" + bitmap);
                Log.d("Path", "" + filePath);
                profileImage = findViewById(R.id.profile_image);
                profileImage.setImageBitmap(bitmap);
                uploadImage(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadImage(Uri filePath) {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userKey = firebaseUser.getUid();

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + userKey);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Home.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Home.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }

    }

}
