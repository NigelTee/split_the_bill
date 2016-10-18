package com.example.splitthebill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CheckDataBaseAdapter {

	CheckDataBase helper;
	public CheckDataBaseAdapter(Context context) {
		helper = new CheckDataBase(context);
	}
	public long inserData(String name, String password)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(CheckDataBase.KEY_USER, name);
		contentValues.put(CheckDataBase.KEY_ITEMNAME, password);	
		long id =db.insert(CheckDataBase.DATABASE_TABLE, null, contentValues);
		return id;
	}
		
	public String getAllData()
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		String[] columns = {CheckDataBase.KEY_ROWID, CheckDataBase.KEY_USER, CheckDataBase.KEY_ITEMNAME};
		Cursor cursor = db.query(CheckDataBase.DATABASE_TABLE, columns, null, null, null, null, null);
		StringBuffer buffer = new StringBuffer();
		while(cursor.moveToNext()){
			
			int cid = cursor.getInt(0);
			String name = cursor.getString(1);
			String password = cursor.getString(2);
			buffer.append(cid+" "+name+" "+password+"\n");
		}
		return buffer.toString();
		
	}
	
	public String getData(String name, String password){
		SQLiteDatabase db = helper.getWritableDatabase();
		String[] columns = {CheckDataBase.KEY_ROWID};
		String[] selectionArgs={name, password};
		Cursor cursor = db.query(CheckDataBase.DATABASE_TABLE, columns, 
				CheckDataBase.KEY_USER+" =? AND "+CheckDataBase.KEY_ITEMNAME+" =?",
				selectionArgs, null, null, null, null);
		
		StringBuffer buffer = new StringBuffer();
		while(cursor.moveToNext()){
			int index0 = cursor.getColumnIndex(CheckDataBase.KEY_ROWID);
			//int index1 = cursor.getColumnIndex(CheckDataBase.KEY_USER);
			//int index2 = cursor.getColumnIndex(CheckDataBase.KEY_ITEMNAME);
			int personId = cursor.getInt(index0);
			//String personName = cursor.getString(index1);
			//String personPassword = cursor.getString(index2);
			buffer.append(personId + /*" "+personName+" "+personPassword+"*/ "\n");
		}
		return buffer.toString();
	}
	public int updateName(String oldName, String newName)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(CheckDataBase.KEY_USER, newName);
		String[] whereArgs={oldName};
		int count = db.update(CheckDataBase.DATABASE_TABLE, contentValues, CheckDataBase.KEY_USER+" =? ", whereArgs);
	
		return count;
	}
	public int deleteRow(String deleteName)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		String[] whereArgs={deleteName};
		int delete = db.delete(CheckDataBase.DATABASE_TABLE, CheckDataBase.KEY_USER+" =? ", whereArgs);
	
		return delete;
	}
	
	static class CheckDataBase extends SQLiteOpenHelper{

		// DB info: it's name, and the table we are using (just one).
			
		public static final String DATABASE_NAME = "MyDb2";
			
			private static final String DATABASE_TABLE = "mainTable";
			private static final int DATABASE_VERSION = 1;	
			// TODO: Setup your fields here:
			private static final String KEY_ROWID = "_id";
			private static final String KEY_USER = "user";
			private static final String KEY_ITEMNAME = "itemname";
			
			private static final String DATABASE_CREATE_SQL = 
					"create table " + DATABASE_TABLE 
					+ " (" + KEY_ROWID + " integer primary key autoincrement, " + KEY_USER
					+ " VARCHAR(255), " + KEY_ITEMNAME + " VARCHAR(255));";
					
			private static final String DROP_TABLE = "DROP TABLE IF EXISTS "
					+ DATABASE_TABLE;
			private Context context;

			public CheckDataBase (Context context){
				super (context, DATABASE_TABLE, null, DATABASE_VERSION);
				this.context = context;
				
			}
			
			
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
		try {
			db.execSQL(DATABASE_CREATE_SQL);
		
		}catch (SQLException e){
			
		}
		
	}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			try{
			db.execSQL(DROP_TABLE);
			onCreate(db);
		}catch (SQLException e){
			
			
		}
		
	}
	}
}	