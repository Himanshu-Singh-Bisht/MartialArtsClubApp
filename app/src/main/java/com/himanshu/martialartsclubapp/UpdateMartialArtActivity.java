package com.himanshu.martialartsclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.himanshu.martialartsclubapp.Model.DatabaseHandler;
import com.himanshu.martialartsclubapp.Model.MartialArt;

import java.util.ArrayList;

public class UpdateMartialArtActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_art);

        databaseHandler = new DatabaseHandler(UpdateMartialArtActivity.this);
        modifyUserInterface();
    }

    public void modifyUserInterface()
    {
        ArrayList<MartialArt> martialArtObjects = databaseHandler.returnAllMartialArtObjects();

        if(martialArtObjects.size() > 0)
        {
            ScrollView scrollView = new ScrollView(UpdateMartialArtActivity.this);
            GridLayout gridLayout = new GridLayout(UpdateMartialArtActivity.this);

            gridLayout.setRowCount(martialArtObjects.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextView = new TextView[martialArtObjects.size()];
            EditText[][] edtNamePriceAndColor = new EditText[martialArtObjects.size()][3];
            Button[] modifyButtons = new Button[martialArtObjects.size()];

            Point screenSize = new Point();     // to find the width of screen
            getWindowManager().getDefaultDisplay().getSize(screenSize);

            int screenWidth = screenSize.x;     // width of screen
            int index = 0;

            for(MartialArt martialArtObject : martialArtObjects)
            {
                idTextView[index] = new TextView(UpdateMartialArtActivity.this);
                idTextView[index].setGravity(Gravity.CENTER);
                idTextView[index].setText(martialArtObject.getMartialArtId() +"");


                edtNamePriceAndColor[index][0] = new EditText(UpdateMartialArtActivity.this);
                edtNamePriceAndColor[index][1] = new EditText(UpdateMartialArtActivity.this);
                edtNamePriceAndColor[index][2] = new EditText(UpdateMartialArtActivity.this);

                edtNamePriceAndColor[index][0].setText(martialArtObject.getMartialArtName());
                edtNamePriceAndColor[index][1].setText(martialArtObject.getMartialArtPrice() + "");
                edtNamePriceAndColor[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);       // setting input type for price to number
                edtNamePriceAndColor[index][2].setText(martialArtObject.getMartialArtColor());

                edtNamePriceAndColor[index][0].setId(martialArtObject.getMartialArtId() + 100);
                edtNamePriceAndColor[index][1].setId(martialArtObject.getMartialArtId() + 200);
                edtNamePriceAndColor[index][2].setId(martialArtObject.getMartialArtId() + 300);


                modifyButtons[index] = new Button(UpdateMartialArtActivity.this);
                modifyButtons[index].setText("Modify");
                modifyButtons[index].setId(martialArtObject.getMartialArtId());

                modifyButtons[index].setOnClickListener(UpdateMartialArtActivity.this);


                gridLayout.addView(idTextView[index] ,(int) (screenWidth * 0.05) ,
                        ViewGroup.LayoutParams.WRAP_CONTENT);   // setting width and height ,    id
                gridLayout.addView(edtNamePriceAndColor[index][0] , (int) (screenWidth * 0.25) ,
                        ViewGroup.LayoutParams.WRAP_CONTENT);           // name
                gridLayout.addView(edtNamePriceAndColor[index][1] , (int) (screenWidth * 0.20) ,
                        ViewGroup.LayoutParams.WRAP_CONTENT);           // price
                gridLayout.addView(edtNamePriceAndColor[index][2] , (int) (screenWidth * 0.25) ,
                        ViewGroup.LayoutParams.WRAP_CONTENT);           // color

                gridLayout.addView(modifyButtons[index] , (int) (screenWidth * 0.30) ,
                        ViewGroup.LayoutParams.WRAP_CONTENT);


                index++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);
        }
    }

    @Override
    public void onClick(View view)
    {
        int martialArtObjectId = view.getId();      // as buttom id is same as object id

        EditText edtMartialArtName = findViewById(martialArtObjectId + 100);
        EditText edtMartialArtPrice = findViewById(martialArtObjectId + 200);
        EditText edtMartialArtColor = findViewById(martialArtObjectId + 300);

        String martialArtNameStringValue = edtMartialArtName.getText().toString();
        String martialArtPriceStringValue = edtMartialArtPrice.getText().toString();
        String martialArtColorStringValue = edtMartialArtColor.getText().toString();

        try {
            double martialArtPriceDoubleValue = Double.parseDouble(martialArtPriceStringValue);
            databaseHandler.modifyMartialArtObject(martialArtObjectId ,
                    martialArtNameStringValue ,
                    martialArtPriceDoubleValue ,
                     martialArtColorStringValue);

            Toast.makeText(UpdateMartialArtActivity.this , "The Martial Art Object is modified" , Toast.LENGTH_SHORT).show();
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }
}