package com.tyga.fintech.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Promo implements Parcelable {

    private String id_promo, id_merchant, nama_promo,image_promo;
    private double nominal;
    private String syarat_penggunaan, tanggal_mulai, tanggal_berakhir, active, nama,alamat,deskripsi, nama_lpd;


    public Promo() {
    }

    public Promo(String id_promo, String id_merchant, String nama_promo, String image_promo, double nominal, String syarat_penggunaan, String tanggal_mulai, String tanggal_berakhir, String active, String nama, String alamat, String deskripsi, String nama_lpd) {
        this.id_promo = id_promo;
        this.id_merchant = id_merchant;
        this.nama_promo = nama_promo;
        this.image_promo = image_promo;
        this.nominal = nominal;
        this.syarat_penggunaan = syarat_penggunaan;
        this.tanggal_mulai = tanggal_mulai;
        this.tanggal_berakhir = tanggal_berakhir;
        this.active = active;
        this.nama = nama;
        this.alamat = alamat;
        this.deskripsi = deskripsi;
        this.nama_lpd = nama_lpd;
    }

    public String getNama_lpd() {
        return nama_lpd;
    }

    public void setNama_lpd(String nama_lpd) {
        this.nama_lpd = nama_lpd;
    }

    public String getNama_promo() {
        return nama_promo;
    }

    public void setNama_promo(String nama_promo) {
        this.nama_promo = nama_promo;
    }

    public String getImage_promo() {
        return image_promo;
    }

    public void setImage_promo(String image_promo) {
        this.image_promo = image_promo;
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
        dest.writeString(this.nama_promo) ;
        dest.writeString(this.image_promo) ;
        dest.writeDouble(this.nominal);
        dest.writeString(this.syarat_penggunaan) ;
        dest.writeString(this.tanggal_mulai) ;
        dest.writeString(this.tanggal_berakhir) ;
        dest.writeString(this.active) ;
        dest.writeString(this.nama) ;
        dest.writeString(this.alamat) ;
        dest.writeString(this.deskripsi) ;
        dest.writeString(this.nama_lpd) ;
    }

    protected Promo(Parcel in) {

        this.id_promo = in.readString();
        this.id_merchant = in.readString();
        this.image_promo = in.readString();
        this.nama_promo = in.readString();
        this.nominal = in.readDouble();
        this.syarat_penggunaan = in.readString();
        this.tanggal_mulai = in.readString();
        this.tanggal_berakhir = in.readString();
        this.active = in.readString();
        this.nama = in.readString();
        this.alamat = in.readString();
        this.deskripsi = in.readString();
        this.nama_lpd = in.readString();

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
