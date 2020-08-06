package com.himanshu.martialartsclubapp.Model;

public class MartialArt
{
    private String martialArtId;
    private String martialArtName;
    private double martialArtPrice;
    private String martialArtColor;

    // constructor
    public MartialArt(String martialArtId, String martialArtName, double martialArtPrice, String martialArtColor)
    {
        this.martialArtId = martialArtId;
        this.martialArtName = martialArtName;
        this.martialArtPrice = martialArtPrice;
        this.martialArtColor = martialArtColor;
    }

    // GETTERS
    public String getMartialArtId() {
        return martialArtId;
    }

    public String getMartialArtName() {
        return martialArtName;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    // SETTERS

    public void setMartialArtId(String martialArtId) {
        this.martialArtId = martialArtId;
    }

    public void setMartialArtName(String martialArtName) {
        this.martialArtName = martialArtName;
    }

    public void setMartialArtPrice(double martialArtPrice) {
        this.martialArtPrice = martialArtPrice;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }
}
