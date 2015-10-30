package com.example.fxb.note.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
    private static final String DATABASE_NAME = "cycares.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseManager sInstance;
    private static SQLiteDatabase sDatabase;

    public static DatabaseManager initialize(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseManager(context.getApplicationContext());
        }
        return sInstance;
    }

    public static void close() {
        if (sInstance != null && sDatabase.isOpen()) {
            sDatabase.close();
        }
        sInstance = null;
    }

    public synchronized static SQLiteDatabase getDatabase(Context context) {
        if (sDatabase == null) {
            sInstance = new DatabaseManager(context.getApplicationContext());
        }
        return sDatabase;
    }

    private DatabaseManager(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context,
                DATABASE_NAME);
        sDatabase = databaseHelper.getWritableDatabase();
    }

    /**
     * Sqlite insert query
     *
     * @param table
     * @param values
     * @return boolean result
     */
    public static long insert(String table, ContentValues values) {
        return sDatabase.insert(table, null, values);
    }

    /**
     * Sqlite update query
     *
     * @param table
     * @param values
     * @param whereClause
     * @return boolean result
     */
    public static boolean update(String table, ContentValues values,
                                 String whereClause) {
        return sDatabase.update(table, values, whereClause, null) > 0;
    }

    /**
     * Sqlite delete query
     *
     * @param table
     * @param whereClause
     * @return boolean result
     */
    public synchronized static boolean delete(String table, String whereClause) {
        return sDatabase.delete(table, whereClause, null) > 0;
    }

    /**
     * Sqlite select query
     *
     * @param table
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @return cursor
     */
    public static Cursor select(String table, String[] columns,
                                String selection, String[] selectionArgs, String groupBy,
                                String having, String orderBy, String limit) {
        return sDatabase.query(table, columns, selection, selectionArgs,
                groupBy, having, orderBy, limit);
    }

    /**
     * Sqlite select query
     *
     * @param table
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @return cursor
     */
    public static Cursor select(String table, String[] columns,
                                String selection, String[] selectionArgs, String groupBy,
                                String having, String orderBy) {
        return sDatabase.query(table, columns, selection, selectionArgs,
                groupBy, having, orderBy);
    }

    /**
     * Sqlite select query
     *
     * @param sql
     * @param selectionArgs
     * @return cursor
     */
    public static Cursor select(String sql, String[] selectionArgs) {
        return sDatabase.rawQuery(sql, selectionArgs);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String MY_SUPPLY_DEMAND = "CREATE TABLE [my_supply_demand] ('id' INTEGER NOT NULL PRIMARY KEY , 'u_id' INTEGER,'title' TEXT, 'desc' TEXT, 'photo' TEXT,'mp3' TEXT, 'longitude' REAL DEFAULT '0', 'latitude' REAL DEFAULT '0','category_id' INTEGER DEFAULT '0', 'type' INTEGER, 'is_verify' INTEGER, 'status' INTEGER DEFAULT '0', 'address' TEXT,'datetime' INTEGER,'distance' INTEGER);";

        public DatabaseHelper(Context context, String databaseName) {
            // calls the super constructor, requesting the default cursor
            // factory.
            super(context, databaseName, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //TODO create
            //db.execSQL(MY_SUPPLY_DEMAND);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // apply necessary change of database

        }

    }

    public static void clear() {
        //TODO clear
    }
}
