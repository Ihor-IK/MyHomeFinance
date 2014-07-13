package com.home.homefinance.activity.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.*;

public class AssetDatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "";

    private Context context;



    public AssetDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 30);
        this.context = context;

        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = "/data/data/" + context.getPackageName() + "/databases/" + DATABASE_NAME;
        new File("/data/data/" + context.getPackageName() + "/databases/").mkdir();
        OutputStream myOutput = new FileOutputStream(new File(outFileName));
        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}