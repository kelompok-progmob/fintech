package com.tyga.fintech.model;

public class Lpd {

    private String id_lpd,nama,alamat;

    public Lpd(String id_lpd, String nama, String alamat) {
        this.id_lpd = id_lpd;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getId_lpd() {
        return id_lpd;
    }

    public void setId_lpd(String id_lpd) {
        this.id_lpd = id_lpd;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
