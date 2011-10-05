package com.cricket;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseAccess 
{
	public static final String DATABASE = "mydb";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_PORTFOLIO = "portfolio";
	public static final String KEY_ID = "Id";
	public static final String KEY_SNAME = "ShareName";
	public static final String KEY_NOS = "NoOfShares";
	public static final String CREATE_USERS = "create table " 
												+TABLE_PORTFOLIO + "(" + KEY_ID + " text,"
																   + KEY_SNAME + " text,"
																   + KEY_NOS + " text );";
	Databasehelper dh;
	SQLiteDatabase db;
	
	public DatabaseAccess(Context ctx)
	{
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
			db.execSQL(CREATE_USERS);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			// TODO Auto-generated method stub
			
		}
	}
	
	public void open()
	{
		db = dh.getWritableDatabase();
		
	}
	public void close()
	{
		dh.close();
	}
	
	public ArrayList<Portfolio> getData()
	{
		String cols[]={KEY_ID,KEY_SNAME,KEY_NOS};
		ArrayList<Portfolio> alt=new ArrayList<Portfolio>();
		Cursor c = db.query(TABLE_PORTFOLIO, cols, null, null, null, null, null);
		if(c.moveToNext())
		{
			do
			{
				Portfolio temp=new Portfolio();
				String name = c.getString(c.getColumnIndex(DatabaseAccess.KEY_SNAME));
				temp.setShareName(name);
				int id = c.getInt(c.getColumnIndex(DatabaseAccess.KEY_ID));
				temp.setId(id);
				int nos = c.getInt(c.getColumnIndex(DatabaseAccess.KEY_NOS));
				temp.setId(nos);
				alt.add(temp);
			}while(c.moveToNext());
		}
		return alt;
	}
	public boolean addEntry(int id,String sname,int nos)
	{
		ContentValues cv = new ContentValues();
		cv.put(KEY_ID, id);
		cv.put(KEY_SNAME, sname);
		cv.put(KEY_NOS,nos);
		long rid = db.insert(TABLE_PORTFOLIO, null, cv);
		if(rid!=-1)
			return true;	
		else
			return false;
		
	}

}
