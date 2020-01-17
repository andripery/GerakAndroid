package com.projek.gerak.Table;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Masukan {

    public String username;
    public String masukan;

    public Masukan() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Masukan(String username, String masukan) {
        this.username = username;
        this.masukan = masukan;
    }

}