package com.projek.gerak.Table;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public String nope;
    public String alamat;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, String nope, String alamat) {
        this.username = username;
        this.email = email;
        this.nope = nope;
        this.alamat = alamat;
    }

}