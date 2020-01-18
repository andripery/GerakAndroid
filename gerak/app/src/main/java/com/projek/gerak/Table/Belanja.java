package com.projek.gerak.Table;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Belanja {

    public String username;
    public String lokasi;
    public String deskripsiLokasi;
    public String deskripsiBarang;

    public Belanja() {}

    public Belanja(String username, String lokasi, String deskripsiLokasi, String deskripsiBarang) {
        this.username = username;
        this.lokasi = lokasi;
        this.deskripsiLokasi = deskripsiLokasi;
        this.deskripsiBarang = deskripsiBarang;
    }
}
