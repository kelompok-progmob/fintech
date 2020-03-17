package com.tyga.fintech.model;

public class MappingUser {

    private String id_mapping_user, id_nasabah, id_lpd, no_rek_user, active;
    private double total_saldo_rekening, total_saldo_topup;

    public MappingUser(String id_mapping_user, String id_nasabah, String id_lpd, String no_rek_user, String active, double total_saldo_rekening, double total_saldo_topup) {
        this.id_mapping_user = id_mapping_user;
        this.id_nasabah = id_nasabah;
        this.id_lpd = id_lpd;
        this.no_rek_user = no_rek_user;
        this.active = active;
        this.total_saldo_rekening = total_saldo_rekening;
        this.total_saldo_topup = total_saldo_topup;
    }

    public MappingUser() {
    }

    public String getId_mapping_user() {
        return id_mapping_user;
    }

    public void setId_mapping_user(String id_mapping_user) {
        this.id_mapping_user = id_mapping_user;
    }

    public String getId_nasabah() {
        return id_nasabah;
    }

    public void setId_nasabah(String id_nasabah) {
        this.id_nasabah = id_nasabah;
    }

    public String getId_lpd() {
        return id_lpd;
    }

    public void setId_lpd(String id_lpd) {
        this.id_lpd = id_lpd;
    }

    public String getNo_rek_user() {
        return no_rek_user;
    }

    public void setNo_rek_user(String no_rek_user) {
        this.no_rek_user = no_rek_user;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public double getTotal_saldo_rekening() {
        return total_saldo_rekening;
    }

    public void setTotal_saldo_rekening(double total_saldo_rekening) {
        this.total_saldo_rekening = total_saldo_rekening;
    }

    public double getTotal_saldo_topup() {
        return total_saldo_topup;
    }

    public void setTotal_saldo_topup(double total_saldo_topup) {
        this.total_saldo_topup = total_saldo_topup;
    }
}
