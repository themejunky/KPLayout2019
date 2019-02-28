package com.keyboard_core.database;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.PopSettings;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static DataBase mInstance = null;
    private static String DATABASE_NAME = "keyboard.sqlite";
    private static int DATABASE_VERSION = 2;
    private ContentValues values = new ContentValues();

    public static class DATABASE_CREATE {
        private static String CREATE = "CREATE TABLE IF NOT EXISTS ";
        private static String DROP = "DROP TABLE IF EXISTS ";
        private static String COMMA = ",";
        private static String PRIMARY_KEY = " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL";
        private static String TYPE_TEXT = " TEXT";
        private static String TYPE_INTEGER = " INTEGER";

        private static String TABLE_DATE = "data_to_parse";
        private static String TABLE_SYNC_SETTINGS = "sync_settings";
        private static String TABLE_BASE_64 = "base64";

        private static String COLUMN_ID = "_id";
        private static String COLUMN_TYPE = "type";
        private static String COLUMN_INDEX = "record_index";
        private static String COLUMN_EMAIL = "email";
        private static String COLUMN_DATE = "date";
        private static String COLUMN_ACCOUNT = "account";

        private static String SQL_CREATE_SYNC_SETTINGS = CREATE + TABLE_SYNC_SETTINGS + " (" +
                COLUMN_ID + PRIMARY_KEY + COMMA +
                COLUMN_TYPE + TYPE_TEXT + COMMA +
                COLUMN_ACCOUNT + TYPE_TEXT + COMMA +
                COLUMN_DATE + TYPE_TEXT + ")";

        private static String SQL_CREATE_DATES = CREATE + TABLE_DATE + " (" +
                COLUMN_ID + PRIMARY_KEY + COMMA +
                COLUMN_TYPE + TYPE_TEXT + COMMA +
                COLUMN_INDEX + TYPE_TEXT + COMMA +
                COLUMN_EMAIL + TYPE_TEXT + ")";

        private static String SQL_CREATE_BASE64 = CREATE + TABLE_BASE_64 + " (" +
                COLUMN_ID + PRIMARY_KEY + COMMA +
                COLUMN_INDEX + TYPE_TEXT + COMMA +
                COLUMN_TYPE + TYPE_TEXT +  ")";


        private static String SQL_DELETE_DATA_GMAIL = DROP + TABLE_DATE;
        private static String SQL_DELETE_DATA_SYNC = DROP + TABLE_SYNC_SETTINGS;
        private static String SQL_DELETE_BASE = DROP + TABLE_BASE_64;
    }

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBase getInstance(Context context) {
        if (mInstance == null)
            mInstance = new DataBase(context.getApplicationContext());
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE.SQL_CREATE_DATES);
        db.execSQL(DATABASE_CREATE.SQL_CREATE_SYNC_SETTINGS);
        db.execSQL(DATABASE_CREATE.SQL_CREATE_BASE64);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_CREATE.SQL_DELETE_DATA_GMAIL);
        db.execSQL(DATABASE_CREATE.SQL_DELETE_DATA_SYNC);
        db.execSQL(DATABASE_CREATE.SQL_DELETE_BASE);
        onCreate(db);
    }

    public Boolean save_mails_index(List<Message> mailsIndex, String emailAccount) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        int max = mailsIndex.size();

        for (int i = 0; i < max; i++) {
            values.clear();
            values.put(DATABASE_CREATE.COLUMN_INDEX, mailsIndex.get(i).getId());
            values.put(DATABASE_CREATE.COLUMN_EMAIL, emailAccount);
            values.put(DATABASE_CREATE.COLUMN_TYPE, "mail");
            db.insert(DATABASE_CREATE.TABLE_DATE, null, values);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        getRows();
        return true;
    }

    public Boolean save_base64(List<String> base64,String type) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        int max = base64.size();

        for (int i = 0; i < max; i++) {
            values.clear();
            Log.d("data_base64",""+base64.get(i));
            values.put(DATABASE_CREATE.COLUMN_INDEX, base64.get(i));
            values.put(DATABASE_CREATE.COLUMN_TYPE, type);
            db.insert(DATABASE_CREATE.TABLE_BASE_64, null, values);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        getRows();
        return true;
    }

    public Boolean save_sms_index(ArrayList smsIndex) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        int max = smsIndex.size();

        for (int i = 0; i < max; i++) {
            Log.d("service_one","se insert : "+(String) smsIndex.get(i));
            values.clear();
            values.put(DATABASE_CREATE.COLUMN_INDEX, (String) smsIndex.get(i));
            values.put(DATABASE_CREATE.COLUMN_TYPE, "sms");
            db.insert(DATABASE_CREATE.TABLE_DATE, null, values);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        getRows();
        return true;
    }

    public Boolean setEmailSyncForAccount(String accountName, String date) {
        SQLiteDatabase db = getWritableDatabase();

        values.clear();
        values.put(DATABASE_CREATE.COLUMN_TYPE, "email");
        values.put(DATABASE_CREATE.COLUMN_ACCOUNT, accountName);
        values.put(DATABASE_CREATE.COLUMN_DATE, date);

        if (getEmailSyncDateForAccount(accountName) == null) {
            //insert
            db.insert(DATABASE_CREATE.TABLE_SYNC_SETTINGS, null, values);
        } else {
            //update
            String strFilter = DATABASE_CREATE.COLUMN_ACCOUNT+"= '" + accountName+"'";
            db.update(DATABASE_CREATE.TABLE_SYNC_SETTINGS,values,strFilter,null);
        }

        return true;
    }

    public Boolean setSMSSyncForAccount(String accountName, String date) {
        SQLiteDatabase db = getWritableDatabase();

        values.clear();
        values.put(DATABASE_CREATE.COLUMN_TYPE, "sms");
        values.put(DATABASE_CREATE.COLUMN_ACCOUNT, "");
        values.put(DATABASE_CREATE.COLUMN_DATE, date);

        if (getEmailSyncDateForAccount(accountName) == null) {
            //insert
            db.insert(DATABASE_CREATE.TABLE_SYNC_SETTINGS, null, values);
        } else {
            //update
            String strFilter = DATABASE_CREATE.COLUMN_ACCOUNT+"= '" + accountName+"'";
            db.update(DATABASE_CREATE.TABLE_SYNC_SETTINGS,values,strFilter,null);
        }

        return true;
    }

    public String getEmailSyncDateForAccount(String accountName) {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {DATABASE_CREATE.COLUMN_ID, DATABASE_CREATE.COLUMN_DATE};
        String selection = DATABASE_CREATE.COLUMN_ACCOUNT + " = ?";
        String[] selectionArgs = {accountName};

        Cursor rand = db.query(DATABASE_CREATE.TABLE_SYNC_SETTINGS, projection, selection, selectionArgs, null, null, null);

        if (rand.getCount() == 0) {
            return null;
        } else {
            rand.moveToFirst();
            return rand.getString(1);
        }
    }

    public String getSmsSyncDateForAccount() {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {DATABASE_CREATE.COLUMN_ID, DATABASE_CREATE.COLUMN_DATE};
        String selection = DATABASE_CREATE.COLUMN_TYPE + " = ?";
        String[] selectionArgs = {"sms"};

        Cursor rand = db.query(DATABASE_CREATE.TABLE_SYNC_SETTINGS, projection, selection, selectionArgs, null, null, null);

        if (rand.getCount() == 0) {
            return null;
        } else {
            rand.moveToFirst();
            return rand.getString(1);
        }
    }

    public boolean deleteFrom(String nId)
    {
        SQLiteDatabase db = getWritableDatabase();

        String selection        = DATABASE_CREATE.COLUMN_ID + " = ?";
        String[] selectionArgs  = { String.valueOf(nId) };

        db.delete(DATABASE_CREATE.TABLE_DATE, selection, selectionArgs);
        getRows();
        return true;
    }


    public Cursor getAll() {
        SQLiteDatabase db = getWritableDatabase();
        String[] projection = {DATABASE_CREATE.COLUMN_ID,DATABASE_CREATE.COLUMN_TYPE,DATABASE_CREATE.COLUMN_INDEX,DATABASE_CREATE.COLUMN_EMAIL};
        Cursor rand = db.query(DATABASE_CREATE.TABLE_DATE, projection, null, null, null, null, null);
        return rand;
    }

    public Cursor getAll2() {
        SQLiteDatabase db = getWritableDatabase();
        String[] projection = {DATABASE_CREATE.COLUMN_ID,DATABASE_CREATE.COLUMN_INDEX,DATABASE_CREATE.COLUMN_TYPE};
        Cursor rand = db.query(DATABASE_CREATE.TABLE_BASE_64, projection, null, null, null, null, null);
        return rand;
    }

    public int getRows() {
        SQLiteDatabase db = getWritableDatabase();

        String[] projection = {DATABASE_CREATE.COLUMN_ID};
        Cursor rand = db.query(DATABASE_CREATE.TABLE_DATE, projection, null, null, null, null, null);
        Log.d("personal_sync", "in DB sunt : " + rand.getCount());
        return rand.getCount();
    }
}
