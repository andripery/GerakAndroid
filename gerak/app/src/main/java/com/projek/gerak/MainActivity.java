package com.projek.gerak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getstarted);

//        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//        ImageButton navToggle = (ImageButton) findViewById(R.id.btnProfile_home);
//        navToggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(navigationView);
//            }
//        });
//
//        loadFragment(new FragmentNavView());

    }

//    private void loadFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.nav_layout, fragment);
//        fragmentTransaction.commit();
//    }

    public void createAccount(View view){
        Intent intent = new Intent(getApplicationContext(), Daftar.class);
        startActivity(intent);
    }

}
