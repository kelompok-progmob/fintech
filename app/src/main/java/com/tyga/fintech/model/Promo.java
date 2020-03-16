package com.tyga.fintech.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Promo implements Parcelable {

    private String id_promo, id_merchant, nama,alamat,deskripsi, syarat_penggunaan, tanggal_mulai, tanggal_berakhir, active;
    private double nominal;

    public Promo() {
    }

    public Promo(String id_promo, String id_merchant, String nama, String alamat, String deskripsi, String syarat_penggunaan, String tanggal_mulai, String tanggal_berakhir, String active, double nominal) {
        this.id_promo = id_promo;
        this.id_merchant = id_merchant;
        this.nama = nama;
        this.alamat = alamat;
        this.deskripsi = deskripsi;
        this.syarat_penggunaan = syarat_penggunaan;
        this.tanggal_mulai = tanggal_mulai;
        this.tanggal_berakhir = tanggal_berakhir;
        this.active = active;
        this.nominal = nominal;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId_promo() {
        return id_promo;
    }

    public void setId_promo(String id_promo) {
        this.id_promo = id_promo;
    }

    public String getId_merchant() {
        return id_merchant;
    }

    public void setId_merchant(String id_merchant) {
        this.id_merchant = id_merchant;
    }

    public String getSyarat_penggunaan() {
        return syarat_penggunaan;
    }

    public void setSyarat_penggunaan(String syarat_penggunaan) {
        this.syarat_penggunaan = syarat_penggunaan;
    }

    public String getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(String tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public String getTanggal_berakhir() {
        return tanggal_berakhir;
    }

    public void setTanggal_berakhir(String tanggal_berakhir) {
        this.tanggal_berakhir = tanggal_berakhir;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_promo) ;
        dest.writeString(this.id_merchant) ;
        dest.writeString(this.nama) ;
        dest.writeString(this.alamat) ;
        dest.writeString(this.deskripsi) ;
        dest.writeString(this.syarat_penggunaan) ;
        dest.writeString(this.tanggal_mulai) ;
        dest.writeString(this.tanggal_berakhir) ;
    }

    protected Promo(Parcel in) {

        this.id_promo = in.readString();
        this.id_merchant = in.readString();
        this.nama = in.readString();
        this.alamat = in.readString();
        this.deskripsi = in.readString();
        this.syarat_penggunaan = in.readString();
        this.tanggal_mulai = in.readString();
        this.tanggal_berakhir = in.readString();

    }
    public static final Creator<Promo> CREATOR = new Creator<Promo>() {
        @Override
        public Promo createFromParcel(Parcel source) {
            return new Promo(source);
        }
        @Override
        public Promo[] newArray(int size) {
            return new Promo[size];
        }
    };
}
