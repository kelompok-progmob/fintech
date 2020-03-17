package com.tyga.fintech.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaksi implements Parcelable {

    private String id_transaksi, id_user, id_merchant, tanggal, link_qrcode,nama;
    private double nominal;

    public Transaksi(String id_transaksi, String id_user, String id_merchant, String tanggal, String link_qrcode, String nama, double nominal) {
        this.id_transaksi = id_transaksi;
        this.id_user = id_user;
        this.id_merchant = id_merchant;
        this.tanggal = tanggal;
        this.link_qrcode = link_qrcode;
        this.nama = nama;
        this.nominal = nominal;
    }

    public Transaksi() {
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_merchant() {
        return id_merchant;
    }

    public void setId_merchant(String id_merchant) {
        this.id_merchant = id_merchant;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLink_qrcode() {
        return link_qrcode;
    }

    public void setLink_qrcode(String link_qrcode) {
        this.link_qrcode = link_qrcode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
        dest.writeString(this.id_transaksi) ;
        dest.writeString(this.id_user) ;
        dest.writeString(this.id_merchant) ;
        dest.writeString(this.nama) ;
        dest.writeString(this.tanggal) ;
        dest.writeString(this.link_qrcode) ;
        dest.writeDouble(this.nominal);

    }

    protected Transaksi(Parcel in) {

        this.id_transaksi = in.readString();
        this.id_merchant = in.readString();
        this.nama = in.readString();
        this.id_user = in.readString();
        this.tanggal = in.readString();
        this.link_qrcode = in.readString();
        this.nominal = in.readDouble();

    }
    public static final Creator<Transaksi> CREATOR = new Creator<Transaksi>() {
        @Override
        public Transaksi createFromParcel(Parcel source) {
            return new Transaksi(source);
        }
        @Override
        public Transaksi[] newArray(int size) {
            return new Transaksi[size];
        }
    };
}
