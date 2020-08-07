package com.himanshu.martialartsclubapp;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

import com.himanshu.martialartsclubapp.Model.MartialArt;

public class MartialArtButton extends AppCompatButton
{
    private MartialArt martialArtObject;

    public MartialArtButton(Context context ,MartialArt martialArtObject)
    {
        super(context);
        this.martialArtObject = martialArtObject;
    }

    // GETTERS      (as we are gonna need these getters in MainActivity
    public String getMartialArtColor()
    {
        return martialArtObject.getMartialArtColor();
    }

    public double getMartialArtPrice()
    {
        return martialArtObject.getMartialArtPrice();
    }
}
