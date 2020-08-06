package com.himanshu.martialartsclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.himanshu.martialartsclubapp.Model.DatabaseHandler;
import com.himanshu.martialartsclubapp.Model.MartialArt;

public class AddMartialArtActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtPrice, edtColor;
    private Button btnAddMartialArt, btnBack;

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtColor = findViewById(R.id.edtColor);

        btnAddMartialArt = findViewById(R.id.btnAddMartialArt);
        btnBack = findViewById(R.id.btnBack);

        databaseHandler = new DatabaseHandler(AddMartialArtActivity.this);

        btnAddMartialArt.setOnClickListener(AddMartialArtActivity.this);
        btnBack.setOnClickListener(AddMartialArtActivity.this);
    }

    public void addMartialArtObjectToDatabse() {
        String nameValue = edtName.getText().toString();
        String priceValue = edtPrice.getText().toString();
        String colorValue = edtColor.getText().toString();

        try {
            double priceDoubleValue = Double.parseDouble(priceValue);
            MartialArt martialArtObject = new MartialArt(0, nameValue, priceDoubleValue, colorValue);

            databaseHandler.addMartialArt(martialArtObject);
            Toast.makeText(AddMartialArtActivity.this, "Martial Art Object Added to the Database", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnAddMartialArt :
                addMartialArtObjectToDatabse();
                break;

            case R.id.btnBack :
                finish();
                break;
        }
    }
}