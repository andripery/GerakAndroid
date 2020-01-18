package com.projek.gerak.Table;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Keahlian {

    public String username;
    public String judul;
    public String deskripsi;
    public String skill;
    public String tanggal;
    public String jam;

    public Keahlian() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Keahlian(String username, String judul, String deskripsi, String skill, String tanggal, String jam) {
        this.username = username;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.skill = skill;
        this.tanggal = tanggal;
        this.jam = jam;
    }

}