package com.projek.gerak;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Akun extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

//        ImageView logout = (ImageView) findViewById(R.id.btnExit2_profile);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logOut();
//            }
//        });
    }

    private void logOut(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.logout,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(subView);
        builder.create();
        builder.show();
    }

    public void home(View view){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("menu", "akun");
        startActivity(intent);
    }
}
