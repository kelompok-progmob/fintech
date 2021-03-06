package com.tyga.fintech.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Merchant implements Parcelable {

    private String id_merchant, id_lpd, id_mapping_merchant,nama,image,alamat,deskripsi,no_rek_merchant,nama_lpd,no_hp;
    private double total_saldo;

    public Merchant() {
    }

    public Merchant(String id_merchant, String id_lpd, String id_mapping_merchant, String nama, String image, String alamat, String deskripsi, String no_rek_merchant, String nama_lpd, String no_hp, double total_saldo) {
        this.id_merchant = id_merchant;
        this.id_lpd = id_lpd;
        this.id_mapping_merchant = id_mapping_merchant;
        this.nama = nama;
        this.image = image;
        this.alamat = alamat;
        this.deskripsi = deskripsi;
        this.no_rek_merchant = no_rek_merchant;
        this.nama_lpd = nama_lpd;
        this.no_hp = no_hp;
        this.total_saldo = total_saldo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNama_lpd() {
        return nama_lpd;
    }

    public void setNama_lpd(String nama_lpd) {
        this.nama_lpd = nama_lpd;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getId_mapping_merchant() {
        return id_mapping_merchant;
    }

    public void setId_mapping_merchant(String id_mapping_merchant) {
        this.id_mapping_merchant = id_mapping_merchant;
    }

    public String getId_merchant() {
        return id_merchant;
    }

    public void setId_merchant(String id_merchant) {
        this.id_merchant = id_merchant;
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

    public String getNo_rek_merchant() {
        return no_rek_merchant;
    }

    public void setNo_rek_merchant(String no_rek_merchant) {
        this.no_rek_merchant = no_rek_merchant;
    }

    public double getTotal_saldo() {
        return total_saldo;
    }

    public void setTotal_saldo(double total_saldo) {
        this.total_saldo = total_saldo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_merchant) ;
        dest.writeString(this.id_lpd) ;
        dest.writeString(this.id_mapping_merchant) ;
        dest.writeString(this.nama) ;
        dest.writeString(this.image) ;
        dest.writeString(this.alamat) ;
        dest.writeString(this.deskripsi) ;
        dest.writeString(this.no_rek_merchant) ;
        dest.writeString(this.nama_lpd) ;
        dest.writeString(this.no_hp) ;
        dest.writeDouble(this.total_saldo);

    }

    protected Merchant(Parcel in) {

        this.id_merchant = in.readString();
        this.id_lpd = in.readString();
        this.id_mapping_merchant = in.readString();
        this.nama = in.readString();
        this.image = in.readString();
        this.alamat = in.readString();
        this.deskripsi= in.readString();
        this.no_rek_merchant = in.readString();
        this.nama_lpd = in.readString();
        this.no_hp = in.readString();
        this.total_saldo = in.readDouble();


    }
    public static final Creator<Merchant> CREATOR = new Creator<Merchant>() {
        @Override
        public Merchant createFromParcel(Parcel source) {
            return new Merchant(source);
        }
        @Override
        public Merchant[] newArray(int size) {
            return new Merchant[size];
        }
    };
}
