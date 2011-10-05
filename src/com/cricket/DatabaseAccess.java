package com.cricket;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAccess {
	public static final String DATABASE = "cricsedb";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_MARKET = "market";
	public static final String KEY_ID = "ID";
	public static final String KEY_NAME = "name";
	public static final String KEY_PRICE = "price";
	public static final String KEY_SHARE = "share";
	public static final String CREATE_MARKET = "CREATE TABLE "+ TABLE_MARKET +"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME+" VARCHAR( 50 ) NOT NULL ,"+KEY_PRICE+" INTEGER NOT NULL ,"+KEY_SHARE+" INTEGER NOT NULL ,UNIQUE ("+KEY_NAME+"))";
	
	Databasehelper dh;
	SQLiteDatabase db;
	
	public DatabaseAccess(Context ctx){
		dh = new Databasehelper(ctx);
	}
	
	public class Databasehelper extends SQLiteOpenHelper
	{

		public Databasehelper(Context ctx) 
		{
			super(ctx,DATABASE , null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			// TODO Auto-generated method stub
			db.execSQL(CREATE_MARKET);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			// TODO Auto-generated method stub
		}
	}
	
	public void open(){
		db = dh.getWritableDatabase();
	}
	public void close(){
		dh.close();
	}
	public boolean insertData(String name,int price, int shares)
	{
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_PRICE, price);
		cv.put(KEY_SHARE, shares);
		long rid=-1;
		rid = db.insert(TABLE_MARKET, null, cv);
		if(rid!=-1){
			Log.i("DATABASE INSERTED", name);
			return true;	
		}
		else{
			Log.i("DATABASE ---NOT--- INSERTED", name);
			return false;
		}
	}
	
	public ArrayList<DataClass> getData(String arg)
	{
		String where=null;
		if(arg!=null){
			where=KEY_NAME+" LIKE '"+arg+"%'";
		}
		Cursor c=db.query(TABLE_MARKET, new String[]{KEY_ID,KEY_NAME,KEY_PRICE,KEY_SHARE}, where, null, null, null, null);
		ArrayList<DataClass> dc=new ArrayList<DataClass>();
		while(c.moveToNext()){
			DataClass d=new DataClass();
			d.setName(c.getString(c.getColumnIndex(KEY_NAME)));
			d.setPrice(c.getInt(c.getColumnIndex(KEY_PRICE)));
			d.setShares(c.getInt(c.getColumnIndex(KEY_SHARE)));
			dc.add(d);
		}
		return dc;
	}
	
}

