package com.himanshu.martialartsclubapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.himanshu.martialartsclubapp.Model.DatabaseHandler;
import com.himanshu.martialartsclubapp.Model.MartialArt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHandler databaseHandler;
    private Double totalMartialArtPrice ;
    private ScrollView scrollView;
    private int martialArtButtonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHandler = new DatabaseHandler(MainActivity.this);
        totalMartialArtPrice = 0.0;
        scrollView = findViewById(R.id.scrollViewMainActivity);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);

        martialArtButtonWidth = screenSize.x / 2;       // half of the screen width is occupied by the button

        modifyUserInterface();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        modifyUserInterface();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.add_martial_art :
                Intent addMartialArtIntent = new Intent(MainActivity.this , AddMartialArtActivity.class);
                startActivity(addMartialArtIntent);

                break;

            case R.id.delete_martial_art :
                Intent deleteMartialArtIntent = new Intent(MainActivity.this , DeleteMartialArtActivity.class);
                startActivity(deleteMartialArtIntent);

                break;

            case R.id.update_martial_art :
                Intent updateMartialArtIntent = new Intent(MainActivity.this , UpdateMartialArtActivity.class);
                startActivity(updateMartialArtIntent);

                break;

            case R.id.reset_martial_art_price :
                totalMartialArtPrice = 0.0;
                Toast.makeText(MainActivity.this , totalMartialArtPrice + "" , Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void modifyUserInterface()
    {
        ArrayList<MartialArt> allMartialArtObjects = databaseHandler.returnAllMartialArtObjects();

        scrollView.removeAllViewsInLayout();

        if(allMartialArtObjects.size() > 0)
        {
            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allMartialArtObjects.size() + 1) / 2);
            gridLayout.setColumnCount(2);

            MartialArtButton[] martialArtButtons = new MartialArtButton[allMartialArtObjects.size()];
            int index = 0;
            for(MartialArt martialArtObject : allMartialArtObjects)
            {
                martialArtButtons[index] = new MartialArtButton(MainActivity.this , martialArtObject);
                martialArtButtons[index].setText(martialArtObject.getMartialArtId() + "\n" +
                        martialArtObject.getMartialArtName() + "\n" +
                        martialArtObject.getMartialArtPrice() + "\n");

                switch(martialArtObject.getMartialArtColor())
                {
                    case "Red" : martialArtButtons[index].setBackgroundColor(Color.RED);
                        break;

                    case "Blue" : martialArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;

                    case "Yellow" : martialArtButtons[index].setBackgroundColor(Color.YELLOW);
                        break;

                    case "Green" : martialArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;

                    case "Cyan" : martialArtButtons[index].setBackgroundColor(Color.CYAN);
                        break;

                    default:  martialArtButtons[index].setBackgroundColor(Color.GRAY);
                }

                martialArtButtons[index].setOnClickListener(MainActivity.this);

                gridLayout.addView(martialArtButtons[index] , martialArtButtonWidth , ViewGroup.LayoutParams.WRAP_CONTENT);
                index++;
            }

            scrollView.addView(gridLayout);
        }
    }

    @Override
    public void onClick(View view)
    {
        MartialArtButton martialArtButton = (MartialArtButton) view;
        totalMartialArtPrice = totalMartialArtPrice + martialArtButton.getMartialArtPrice();

        // to show total price of the Martial Arts Club with currency Instance
        String martialArtsPriceFormatted = NumberFormat.getCurrencyInstance().format(totalMartialArtPrice);
        Toast.makeText(MainActivity.this , martialArtsPriceFormatted , Toast.LENGTH_SHORT).show();
    }
}