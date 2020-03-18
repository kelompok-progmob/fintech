package com.tyga.fintech.model;

public class ResponseMessage {

    private String success,berhasil;

    public ResponseMessage(String success, String berhasil) {
        this.success = success;
        this.berhasil = berhasil;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getBerhasil() {
        return berhasil;
    }

    public void setBerhasil(String berhasil) {
        this.berhasil = berhasil;
    }
}
