package com.example.kalorihesabi;

public class HelperClass {

    String isim, email, kAdi, sifre;

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getkAdi() {
        return kAdi;
    }

    public void setkAdi(String kAdi) {
        this.kAdi = kAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public HelperClass(String isim, String email, String kAdi, String sifre) {
        this.isim = isim;
        this.email = email;
        this.kAdi = kAdi;
        this.sifre = sifre;
    }

    public HelperClass() {
    }
}
