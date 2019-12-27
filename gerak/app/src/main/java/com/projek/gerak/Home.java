package com.projek.gerak;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ImageButton imageButton = (ImageButton) findViewById(R.id.btnProfile_home);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile();
            }
        });
    }

    private void profile(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.profile,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(subView);
        builder.create();
        builder.show();
    }

    public void keahlian(View view){
        Intent intent = new Intent(getApplicationContext(), Keahlian.class);
        startActivity(intent);
    }

    public void pesan(View view){
        Intent intent = new Intent(getApplicationContext(), Pesan.class);
        startActivity(intent);
    }

    public void bantuan(View view){
        Intent intent = new Intent(getApplicationContext(), Bantuan.class);
        startActivity(intent);
    }

    public void akun(View view){
        Intent intent = new Intent(getApplicationContext(), Akun.class);
        startActivity(intent);
    }

}
