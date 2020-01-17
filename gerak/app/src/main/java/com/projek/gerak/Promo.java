package com.projek.gerak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Promo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promo);
    }

    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("menu", "home");
        startActivity(intent);
    }
}
