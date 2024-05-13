package com.example.kalorihesabi;

public class Yemek {
    String ad;
    String kalori;

    String image;

    public Yemek(String ad, String kalori, String image) {
        this.ad = ad;
        this.kalori = kalori;
        this.image = image;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getKalori() {
        return kalori;
    }

    public void setKalori(String kalori) {
        this.kalori = kalori;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}