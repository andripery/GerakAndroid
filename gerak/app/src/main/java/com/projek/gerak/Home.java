package com.projek.gerak;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton home = (ImageButton) findViewById(R.id.btnHome);
        final ImageButton pesan = (ImageButton) findViewById(R.id.btnChat);
        final ImageButton bantuan = (ImageButton) findViewById(R.id.btnHelp);
        final ImageButton akun = (ImageButton) findViewById(R.id.btnAkun);
        final TextView tvHome = (TextView) findViewById(R.id.txtHome);
        final TextView tvPesan = (TextView) findViewById(R.id.txtChat);
        final TextView tvBantuan = (TextView) findViewById(R.id.txtHelp);
        final TextView tvAkun = (TextView) findViewById(R.id.txtAkun);

        loadFragment(new FragmentHome());

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

}
