package com.tyga.fintech.model;

public class User {
    private String noHP;
    private String role;
    private String password;
    private long idUser;

    public String getNoHP() { return noHP; }
    public void setNoHP(String value) { this.noHP = value; }

    public String getRole() { return role; }
    public void setRole(String value) { this.role = value; }

    public String getPassword() { return password; }
    public void setPassword(String value) { this.password = value; }

    public long getIDUser() { return idUser; }
    public void setIDUser(long value) { this.idUser = value; }
}
