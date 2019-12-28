package com.projek.gerak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getstarted);
    }

    public void createAccount(View view){
        Intent intent = new Intent(getApplicationContext(), Daftar.class);
        startActivity(intent);
    }

}
