package com.tyga.fintech.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ValidasiTransaksi implements Parcelable {
    String nama, alamat, nohp;
    double nominal;
    int points;

    public ValidasiTransaksi() {
    }

    public ValidasiTransaksi(String nama, String alamat, String nohp, double nominal, int points) {
        this.nama = nama;
        this.alamat = alamat;
        this.nohp = nohp;
        this.nominal = nominal;
        this.points = points;
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

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama) ;
        dest.writeString(this.alamat) ;
        dest.writeString(this.nohp) ;
        dest.writeDouble(this.nominal); ;
        dest.writeInt(this.points) ;

    }

    protected ValidasiTransaksi(Parcel in) {

        this.nama = in.readString();
        this.alamat = in.readString();
        this.nohp = in.readString();
        this.nominal = in.readDouble();
        this.points = in.readInt();


    }
    public static final Creator<ValidasiTransaksi> CREATOR = new Creator<ValidasiTransaksi>() {
        @Override
        public ValidasiTransaksi createFromParcel(Parcel source) {
            return new ValidasiTransaksi(source);
        }
        @Override
        public ValidasiTransaksi[] newArray(int size) {
            return new ValidasiTransaksi[size];
        }
    };
}
