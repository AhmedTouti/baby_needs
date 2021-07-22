package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.BabyNeeds;
import Util.Util;

public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(Context context) {
        super(context, Util.DataBaseName, null, Util.DataBaseVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="CREATE TABLE "+Util.column_name+" ("+Util.Key_ID+" INTEGER PRIMARY KEY "+
        ","+Util.Key_itemName+" TEXT,"+Util.Key_itemQuantity+" DOUBLE,"+Util.Key_itemColor+" TEXT,"+Util.Key_itemSize+
        " DOUBLE)";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop="DROP TABLE IF EXISTS";
        sqLiteDatabase.execSQL(drop,new String[]{Util.column_name});
        onCreate(sqLiteDatabase);

    }
    /**CRUD**/
public void AddBabyN(BabyNeeds babyNeeds){
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues values=new ContentValues();
    values.put(Util.Key_itemName,babyNeeds.getItem_name());
    values.put(Util.Key_itemQuantity,babyNeeds.getQuantity());
    values.put(Util.Key_itemColor,babyNeeds.getColor());
    values.put(Util.Key_itemSize,babyNeeds.getSize());
    db.insert(Util.column_name,null,values);
    db.close();

}

//Cursor cursor =db.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_PHONE_NUMBER},Util.KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null);
//
    public BabyNeeds getBabyN(int Id){
    SQLiteDatabase db=this.getReadableDatabase();
        BabyNeeds babyNeeds=new BabyNeeds();
        Cursor cursor=db.query(Util.column_name,new String[]{Util.Key_ID,Util.Key_itemName,Util.Key_itemQuantity,
                Util.Key_itemColor,Util.Key_itemSize},
                Util.Key_ID+ "=?",
                new String[]{String.valueOf(Id)},
                null,null,null);

        if (cursor!=null){
            cursor.moveToFirst();

            babyNeeds=new BabyNeeds(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3),
                    cursor.getDouble(4));
            db.close();
            return  babyNeeds;
        }else{
           return babyNeeds;
        }


    }
    public ArrayList<BabyNeeds> getAll(){
        ArrayList<BabyNeeds> LIST=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String get="SELECT * FROM "+Util.column_name;
        Cursor cursor=db.rawQuery(get,null);
        if (cursor.moveToNext()){
            do {
                BabyNeeds babyNeeds=new BabyNeeds(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getString(3),
                        cursor.getDouble(4));
                LIST.add(babyNeeds);

            }while (cursor.moveToNext()) ;
        }
        return LIST;
    }
    public int UpdateB(BabyNeeds babyNeeds){
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues values =new ContentValues();
        values.put(Util.Key_itemName,babyNeeds.getItem_name());
        values.put(Util.Key_itemQuantity,babyNeeds.getQuantity());
        values.put(Util.Key_itemColor,babyNeeds.getColor());
        values.put(Util.Key_itemSize,babyNeeds.getSize());
    int x= db.update(Util.column_name,values,Util.Key_ID+"=?",new String[]{ String.valueOf(babyNeeds.getItem_Id())});
    db.close();
    return x; }
    public int DeleteB(BabyNeeds babyNeeds){
        SQLiteDatabase db=this.getWritableDatabase();

        int x= db.delete(Util.column_name,Util.Key_ID+"=?",new String[]{String.valueOf(babyNeeds.getItem_Id())});

        db.close();
        return x; }
    public int getCountB(){
        SQLiteDatabase db=this.getReadableDatabase();
        String get="SELECT * FROM "+Util.column_name;
        Cursor cursor=db.rawQuery(get,null);
      int x= cursor.getCount();
        db.close();
        return x; }



}
