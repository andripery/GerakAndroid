package com.projek.gerak.Table;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Titip {

    public String username;
    public String lokasiPenerima;
    public String detailPenerima;
    public String namaPenerima;
    public String nopePenerima;
    public String deskripsiBarang;

    public Titip() {
    }

    public Titip(String username,String lokasiPenerima,String detailPenerima, String namaPenerima,
    String nopePenerima, String deskripsiBarang) {
        this.username = username;
        this.lokasiPenerima = lokasiPenerima;
        this.detailPenerima = detailPenerima;
        this.namaPenerima = namaPenerima;
        this.nopePenerima = nopePenerima;
        this.deskripsiBarang = deskripsiBarang;
    }
}
