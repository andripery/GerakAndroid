package com.projek.gerak;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Daftar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_1);

        Button button = (Button) findViewById(R.id.btnContinue_daftar1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                congrats();
            }
        });
    }

    private void congrats(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.daftar_2,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(subView);
        builder.create();
        builder.show();
    }

    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
    }
}
