package luqmansen.me.moviecatalogue1.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CancellationSignal;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler  extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "favoritedb";
    private static final String TABLE_FAVORITE = "favoritetable";
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE= "type";
    private static final String KEY_TITLE= "title";
    private static final String KEY_OVERVIEW = "overview";
    private static final String KEY_DATE = "date";
    private static final String KEY_BACKDROP = "backdrop";
    private static final String KEY_POSTER = "poster";
    private static final String KEY_TRAILER = "trailer";

    public DBHandler( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_FAVORITE + "("
            + KEY_ID + " INTEGER PRIMARY KEY ,"
            + KEY_TYPE + " TEXT,"
            + KEY_TITLE + " TEXT,"
            + KEY_DATE + " TEXT,"
            + KEY_OVERVIEW + " TEXT,"
            + KEY_BACKDROP + " TEXT,"
            + KEY_POSTER + " TEXT,"
            + KEY_TRAILER + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
        onCreate(db);
    }

    public Cursor doQuery(String query){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }



    public void insertFavorites(String id, String type, String title, String date, String overview, String backdrop, String poster, String trailer){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_TYPE, type);
        contentValues.put(KEY_TITLE, title);
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_OVERVIEW, overview);
        contentValues.put(KEY_BACKDROP, backdrop);
        contentValues.put(KEY_POSTER, poster);
        contentValues.put(KEY_TRAILER, trailer);

        long newRowID = db.insert(TABLE_FAVORITE, null, contentValues);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAll(){

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> favoritelist = new ArrayList<>();
        String query = "SELECT id, type, title, date, overview, backdrop, poster, trailer FROM " + TABLE_FAVORITE;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            HashMap<String, String> favorite = new HashMap<>();
            favorite.put("id", cursor.getString(cursor.getColumnIndex(KEY_ID)));
            favorite.put("type", cursor.getString(cursor.getColumnIndex(KEY_TYPE)));
            favorite.put("title", cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            favorite.put("date", cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            favorite.put("overview", cursor.getString(cursor.getColumnIndex(KEY_OVERVIEW)));
            favorite.put("backdrop", cursor.getString(cursor.getColumnIndex(KEY_BACKDROP)));
            favorite.put("poster", cursor.getString(cursor.getColumnIndex(KEY_POSTER)));
            favorite.put("trailer", cursor.getString(cursor.getColumnIndex(KEY_TRAILER)));

        }
        return favoritelist;
    }

    public void deleteFavorite(int movieId){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_FAVORITE, KEY_ID+" = ?", new String[]{String.valueOf(movieId)});
            db.close();
    }

}
