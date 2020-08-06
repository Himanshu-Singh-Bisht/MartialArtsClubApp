package com.himanshu.martialartsclubapp.Model;

public class MartialArt
{
    private int martialArtId;
    private String martialArtName;
    private double martialArtPrice;
    private String martialArtColor;

    // constructor
    public MartialArt(int martialArtId, String martialArtName, double martialArtPrice, String martialArtColor)
    {
        this.martialArtId = martialArtId;
        this.martialArtName = martialArtName;
        this.martialArtPrice = martialArtPrice;
        this.martialArtColor = martialArtColor;
    }

    // GETTERS
    public int getMartialArtId() {
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

    public void setMartialArtId(int martialArtId) {
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

    @Override
    public String toString()
    {
        return (getMartialArtId() + "\n"+
                getMartialArtName() + "\n" +
                getMartialArtPrice() + "\n" +
                getMartialArtColor() );
    }
}
