//------------------------------------ DBADapter.java ---------------------------------------------

//TODO: Change the package to match your project.
package com.example.splitthebill;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


//TO USE:
//Change the package (at top) to match your project.
//Search for "TODO", and make the appropriate changes.
public class DBAdapter_T2 extends Activity{

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	/////////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter_T2";
	
	// DB Fields
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	public static final String KEY_USER = "user";
	public static final String KEY_ITEMNAME = "itemname";
	public static final String KEY_AMOUNT = "amount";
	public static final String KEY_TOTALAMOUNT = "totalamount";
	public static final String KEY_IMAGE = "image";
	public static final String KEY_STATUS = "status";
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_USER = 1;
	public static final int COL_ITEMNAME = 2;
	public static final int COL_AMOUNT = 3;
	public static final int COL_TOTALAMOUNT = 4;
	public static final int COL_IMAGE = 5;
	public static final int COL_STATUS = 6;
	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_USER, KEY_ITEMNAME, KEY_AMOUNT, KEY_TOTALAMOUNT, KEY_IMAGE, KEY_STATUS};
	
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "MyDb2";
	public static final String DATABASE_TABLE = "mainTable";
	// Track DB version if a new version of your app changes the format.
	public static final int DATABASE_VERSION = 1;	
	
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_USER + " text not null, "
			+ KEY_ITEMNAME + " integer not null, "
			+ KEY_AMOUNT + " string not null, "
			+ KEY_TOTALAMOUNT + " string not null, "
			+ KEY_IMAGE + " text not null, "
			+ KEY_STATUS + " text not null"
			
			// Rest  of creation:
			+ ");";
	
	// Context of application who uses us.
	private final Context context;
	
	private DatabaseHelper myDBHelper_T2;
	private SQLiteDatabase db2;

	/////////////////////////////////////////////////////////////////////
	//	Public methods:
	/////////////////////////////////////////////////////////////////////
	
	public DBAdapter_T2(Context ctx) {
		this.context = ctx;
		myDBHelper_T2 = new DatabaseHelper(context);
	}
	
	// Open the database connection.
	public DBAdapter_T2 open() {
		db2 = myDBHelper_T2.getWritableDatabase();
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper_T2.close();
	}
	
	// Add a new set of values to the database.
	public long insertRow(String user, String itemname, String amount, String totalamount, int image, String status) {
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_USER, user);
		initialValues.put(KEY_ITEMNAME, itemname);
		initialValues.put(KEY_AMOUNT, amount);
		initialValues.put(KEY_TOTALAMOUNT, totalamount);
		initialValues.put(KEY_IMAGE, image);
		initialValues.put(KEY_STATUS, status);
		
		// Insert it into the database.
		return db2.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db2.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	// Return all data in the database.
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db2.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db2.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	
	// Change an existing row to be equal to new data.
	public int updateRow(String rowId, String user, String itemname, String amount, String totalamount, int image, String status) {
		String[] where = {rowId};
		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_USER, user);
		newValues.put(KEY_ITEMNAME, itemname);
		newValues.put(KEY_AMOUNT, amount);
		newValues.put(KEY_TOTALAMOUNT, totalamount);
		newValues.put(KEY_IMAGE, image);
		newValues.put(KEY_STATUS, status);
		
		// Insert it into the database.
		int id = db2.update(DATABASE_TABLE, newValues, KEY_ROWID+" =? ", where);
		
		return id;
	}
	
	public int updateName(String oldName, String newName)
	{
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_USER, newName);
		String[] whereArgs={oldName};
		int count = db2.update(DATABASE_TABLE, newValues, KEY_USER+" =? ", whereArgs);
	
		return count;
	}
	
	public int updateItemName(String oldName, String newName)
	{
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_ITEMNAME, newName);
		String[] whereArgs={oldName};
		int count = db2.update(DATABASE_TABLE, newValues, KEY_ITEMNAME+" =? ", whereArgs);
	
		return count;
	}
	
	public int updatePrice(String oldName, String newName)
	{
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_AMOUNT, newName);
		String[] whereArgs={oldName};
		int count = db2.update(DATABASE_TABLE, newValues, KEY_AMOUNT+" =? ", whereArgs);
	
		return count;
	}
	
	public String getData(String name){
		SQLiteDatabase db = myDBHelper_T2.getWritableDatabase();
		String[] columns = {DBAdapter_T2.KEY_ROWID};
		String[] selectionArgs={name};
		Cursor cursor = db.query(DBAdapter_T2.DATABASE_TABLE, columns, 
				DBAdapter_T2.KEY_USER+" =?",
				selectionArgs, null, null, null, null);
		
		StringBuffer buffer = new StringBuffer();
		while(cursor.moveToNext()){
			int index0 = cursor.getColumnIndex(DBAdapter_T2.KEY_ROWID);
			int personId = cursor.getInt(index0);
			buffer.append(personId + "\n");
		}
		return buffer.toString();
	}
	
	public String getItemname(String name) {
		SQLiteDatabase db = myDBHelper_T2.getWritableDatabase();
		String[] columns = {DBAdapter_T2.KEY_ITEMNAME};
		String[] selectionArgs={name};
		Cursor cursor = db.query(DBAdapter_T2.DATABASE_TABLE, columns, 
				DBAdapter_T2.KEY_USER+" =?",
				selectionArgs, null, null, null, null);
		
		StringBuffer buffer = new StringBuffer();
		while(cursor.moveToNext()){
			int index1 = cursor.getColumnIndex(DBAdapter_T2.KEY_ITEMNAME);
			String itemname = cursor.getString(index1);
			buffer.append(itemname);
		}
		return buffer.toString();
	}
	
	public String getPrice(String name) {
		SQLiteDatabase db = myDBHelper_T2.getWritableDatabase();
		String[] columns = {DBAdapter_T2.KEY_AMOUNT};
		String[] selectionArgs={name};
		Cursor cursor = db.query(DBAdapter_T2.DATABASE_TABLE, columns, 
				DBAdapter_T2.KEY_USER+" =?",
				selectionArgs, null, null, null, null);
		
		StringBuffer buffer = new StringBuffer();
		while(cursor.moveToNext()){
			int index2 = cursor.getColumnIndex(DBAdapter_T2.KEY_AMOUNT);
			String amount = cursor.getString(index2);
			buffer.append(amount);
		}
		return buffer.toString();
	}
	
	public String getTotalPrice(String name) {
		SQLiteDatabase db = myDBHelper_T2.getWritableDatabase();
		String[] columns = {DBAdapter_T2.KEY_TOTALAMOUNT};
		String[] selectionArgs={name};
		Cursor cursor = db.query(DBAdapter_T2.DATABASE_TABLE, columns, 
				DBAdapter_T2.KEY_USER+" =?",
				selectionArgs, null, null, null, null);
		
		StringBuffer buffer = new StringBuffer();
		while(cursor.moveToNext()){
			int index2 = cursor.getColumnIndex(DBAdapter_T2.KEY_TOTALAMOUNT);
			String amount = cursor.getString(index2);
			buffer.append(amount);
		}
		return buffer.toString();
	}
	
	public String getSameUserData(String ID) {
		// TODO Auto-generated method stub
		String[] selectionArgs={ID};
		Cursor c = db2.query(DATABASE_TABLE, ALL_KEYS, KEY_ROWID + " =?", 
				selectionArgs, null, null, null, null);// get row id and equals to
												// whatever is passed in
		StringBuffer buffer = new StringBuffer();
		while(c.moveToNext()){// as long as c is not null, move to that part
			int index0 = c.getColumnIndex(DBAdapter_T2.KEY_USER);		
			String user = c.getString(index0);
			buffer.append(user);
		}
		return buffer.toString();
	}
	
	/////////////////////////////////////////////////////////////////////
	//	Private Helper Classes:
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}

}
