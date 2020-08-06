package com.himanshu.martialartsclubapp.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// inheriting  SQliteOpenHelper
public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Martial Art Database";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "Martial Arts";
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
        String createDatabseSQL = "create table " + MARTIAL_ARTS_TABLE +
                " ( " + ID_KEY  + " integer primary key autoincrementation " +
                " , " + NAME_KEY + " text " + " , " +
                PRICE_KEY + " real " + " , " +
                COLOR_KEY + " text " +  " ) ";

        db.execSQL(createDatabseSQL);           // to execute the query
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
}
