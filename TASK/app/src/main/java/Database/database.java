package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Item;
import Util.Constant;

/**
 * Created by SUYASH on 10-02-2018.
 */

public class database extends SQLiteOpenHelper {

    public database(Context context) {
        super(context, Constant.DB_NAME, null, Constant.DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "create table "+Constant.DB_Table+"( "+Constant.Keyid+" Integer Primary key, "+Constant.Task+
                " text, "+Constant.Description+" text);";

        db.execSQL(statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add(Item item)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(Constant.Task,item.getTask());
        content.put(Constant.Description,item.getDescription());

        db.insert(Constant.DB_Table,null,content);
        db.close();
    }
    public List<Item> getlist(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constant.DB_Table,new String[]{Constant.Keyid,Constant.Task,Constant.Description},null,null,null,null,Constant.Keyid,null);
        List<Item> itemList = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Item item = new Item(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
                itemList.add(item);
            }while(cursor.moveToNext());
        }
        return itemList;
    }
    public void delete(int pos)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constant.DB_Table,Constant.Keyid+" =?",new String[]{String.valueOf(pos)});
        db.close();
    }
    public void deleteall()
    {
        SQLiteDatabase db = getWritableDatabase();
       // Cursor cursor = db.query(Constant.DB_Table,new String[]{Constant.Keyid,Constant.Task,Constant.Description},null,null,null,null,Constant.Keyid,null);
        //if(cursor.moveToFirst())
       // {
         //   do{
           //     db.delete(Constant.DB_Table,Constant.Keyid+" =?",new String[]{String.valueOf(cursor.getPosition())});
            //}while(cursor.moveToNext());
       // }
        db.delete(Constant.DB_Table,null,null);

    }
}
