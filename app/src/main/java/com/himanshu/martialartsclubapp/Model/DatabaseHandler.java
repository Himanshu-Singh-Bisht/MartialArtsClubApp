package com.himanshu.martialartsclubapp.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

// inheriting  SQliteOpenHelper
public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Martial Art Database";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "MartialArt";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String PRICE_KEY = "price";
    private static final String COLOR_KEY = "color";

    public DatabaseHandler(Context context)
    {
        super(context , DATABASE_NAME , null , DATABASE_VERSION);       // constructor for super class
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createDatabaseSQL = " CREATE TABLE " + MARTIAL_ARTS_TABLE +
                "(" + ID_KEY  + " INTEGER primary key autoincrement," +
                NAME_KEY + " TEXT,"  +
                PRICE_KEY + " REAL," +
                COLOR_KEY + " TEXT" +  ");";

        db.execSQL(createDatabaseSQL);           // to execute the query
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(" drop table if exists " + MARTIAL_ARTS_TABLE);
        onCreate(db);
    }

    public void addMartialArt(MartialArt martialArtObject)
    {
        SQLiteDatabase database = getWritableDatabase();
        String addMartialArtSQLCommand = "Insert Into " + MARTIAL_ARTS_TABLE + " values ( null , ' " +
                        martialArtObject.getMartialArtName() + "' , " + " '" +
                        martialArtObject.getMartialArtPrice() + "' , " + " '" +
                        martialArtObject.getMartialArtColor() + "' )" ;
        database.execSQL(addMartialArtSQLCommand);
        database.close();
    }

    public void deleteMartialArtObjectFromDatabaseById(int id)
    {
        SQLiteDatabase database = getWritableDatabase();
        String deleteMartialArtSQLCommand = "delete from " + MARTIAL_ARTS_TABLE +
                " where " + ID_KEY + " = " + id;
        database.execSQL(deleteMartialArtSQLCommand);
        database.close();
    }

    public void modifyMartialArtObject(int martialArtId , String martialArtName , double martialArtPrice , String martialArtColor)
    {
        SQLiteDatabase database = getWritableDatabase();
        String modifyMartialArtCommand = "update " + MARTIAL_ARTS_TABLE + " set " + NAME_KEY + " = '" +
                        martialArtName + "' , " + PRICE_KEY + " = '" + martialArtPrice +
                        "' , " + COLOR_KEY + " = '" + martialArtColor + ", " +
                        " where " + ID_KEY + " = " + martialArtId;

        database.execSQL(modifyMartialArtCommand);
        database.close();
    }

    public ArrayList<MartialArt> returnAllMartialArtObjects()
    {
        SQLiteDatabase database = getWritableDatabase();
        String SQLQueryCommand = " select * from " + MARTIAL_ARTS_TABLE;
        Cursor cursor = database.rawQuery(SQLQueryCommand , null);

        ArrayList<MartialArt> martialArts = new ArrayList<>();
        while(cursor.moveToNext())
        {
            MartialArt currentMartialArtObject = new MartialArt(Integer.parseInt(cursor.getString(0)),
                                                cursor.getString(1) , cursor.getDouble(2) ,
                                                    cursor.getString(3));
            martialArts.add(currentMartialArtObject);
        }
        database.close();

        return martialArts;
    }

    public MartialArt returnMartialArtObjectById(int id)
    {
        SQLiteDatabase database = getWritableDatabase();
        String SQLQueryCommand = "select * from " + MARTIAL_ARTS_TABLE +
                                " where " + ID_KEY + " = " + id;
        Cursor cursor = database.rawQuery(SQLQueryCommand , null);
        MartialArt martialArtObject = null;
        if(cursor.moveToFirst())        // as there will only one object with that id.
        {
            martialArtObject = new MartialArt(Integer.parseInt(cursor.getString(0)) ,
                    cursor.getString(1) , cursor.getDouble(2) ,
                    cursor.getString(3));
        }
        database.close();
        return martialArtObject;
    }


}
