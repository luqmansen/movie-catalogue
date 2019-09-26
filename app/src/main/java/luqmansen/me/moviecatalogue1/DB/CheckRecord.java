package luqmansen.me.moviecatalogue1.DB;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import luqmansen.me.moviecatalogue1.Activity.DetailActivity;

public class CheckRecord {

    private Context context;

    public CheckRecord(Context context) {
        this.context = context;
    }

    public boolean CheckRecordInDB(String tablename, String fieldname, String id){
        DBHandler db = new DBHandler(context);


        String query = "SELECT * FROM " + tablename + " WHERE " + fieldname + " = " + id;
        Cursor cursor = db.doQuery(query);

        if (cursor.getCount()<=0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
