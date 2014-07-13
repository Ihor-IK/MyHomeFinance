package com.home.homefinance.activity.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.home.HomeFinance.R;

public class SqlAdapter extends BaseAdapter {

    private static final String DB_NAME = "costs_names2.sqlite";
    private static final String TABLE_NAME = "names";
    private static final int DB_VESION = 1;

    private static final String KEY_ID = "_id";
    private static final int ID_COLUMN = 0;
    private static final String KEY_NAME = "name";
    private static final int NAME_COLUMN = 1;

    private Cursor cursor;
    private SQLiteDatabase database;
    private DbOpenHelper dbOpenHelper;
    private Context context;


    public SqlAdapter(Context context) {
        super();
        this.context = context;
        init();
    }

    @Override
    public long getItemId(int position) {
        Name nameOnPosition = getItem(position);
        return nameOnPosition.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parrent) {
        TextView textView;
        if (null == convertView) {
            textView = (TextView) View.inflate(context, R.layout.costs_list_layout,
                    null);
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(getItem(position).getName());
        return textView;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Name getItem(int position) {
        if (cursor.moveToPosition(position)) {
            long id = cursor.getLong(ID_COLUMN);
            String name = cursor.getString(NAME_COLUMN);
            Name nameOnPositon = new Name(id, name);
            return nameOnPositon;
        } else {
            throw new CursorIndexOutOfBoundsException(
                    "Cant move cursor on postion");
        }
    }


    public Cursor getAllEntries() {
        String[] columnsToTake = { KEY_ID, KEY_NAME };
        return database.query(TABLE_NAME, columnsToTake,
                null, null, null, null, KEY_ID);
    }

    public long addItem(Name name) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name.getName());
        long id = database.insert(TABLE_NAME, null, values);
        refresh();
        return id;
    }

    public boolean removeItem(Name nameToRemove) {
        boolean isDeleted = (database.delete(TABLE_NAME, KEY_NAME + "=?",
                new String[] { nameToRemove.getName() })) > 0;
        refresh();
        return isDeleted;
    }

    public boolean updateItem(long id, String newName) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newName);
        boolean isUpdated = (database.update(TABLE_NAME, values, KEY_ID + "=?",
                new String[] {id+""})) > 0;
        return isUpdated;
    }


    public void onDestroy() {
        dbOpenHelper.close();
    }

    private void refresh() {
        cursor = getAllEntries();
        notifyDataSetChanged();
    }

    private void init() {
        dbOpenHelper = new DbOpenHelper(context, DB_NAME, null, DB_VESION);
        try {
            database = dbOpenHelper.getWritableDatabase();
        } catch (SQLException e) {
            Log.e(this.toString(), "Error while getting database");
            throw new Error("The end");
        }
        cursor = getAllEntries();
    }


    private static class DbOpenHelper extends SQLiteOpenHelper {

        public DbOpenHelper(Context context, String name,
                            CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override

        public void onCreate(SQLiteDatabase db) {
            final String CREATE_DB = "CREATE TABLE " + TABLE_NAME + " ("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_NAME + " TEXT NOT NULL);";
            db.execSQL(CREATE_DB);
        }

        @Override

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
