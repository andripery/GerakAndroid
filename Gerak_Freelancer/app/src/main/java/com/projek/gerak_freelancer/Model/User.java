package com.projek.gerak_freelancer.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String kota;
    public String alamat;
    public String nope;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String nama, String email) {
        this.username = nama;
        this.email = email;
    }

    public User(String username, String kota, String alamat, String nope, String email) {
        this.username = username;
        this.kota = kota;
        this.alamat = alamat;
        this.nope = nope;
        this.email = email;
    }

}