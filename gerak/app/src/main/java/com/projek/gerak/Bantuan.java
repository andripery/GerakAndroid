package com.projek.gerak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Bantuan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }

    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
    }

    public void pesan(View view){
        Intent intent = new Intent(getApplicationContext(), Pesan.class);
        startActivity(intent);
    }
}
