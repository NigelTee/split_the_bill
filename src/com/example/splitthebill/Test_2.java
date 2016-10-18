package com.example.splitthebill;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

public class Test_2 extends Activity{

	DBAdapter myDb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_2);
		openDB();
		populateListViewFromDB();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		closeDB();
	}

	private void openDB() {
		// TODO Auto-generated method stub
		myDb = new DBAdapter(this);
		myDb.open();
	}
	private void closeDB(){
		myDb.close();
	}
	
	private void populateListViewFromDB() {
		Cursor cursor = myDb.getAllRows();
		
		// Allow activity to manage lifetime of the cursor.
		// DEPRECATED! Runs on the UI thread, OK for small/short queries.
		startManagingCursor(cursor);
		
		// Setup mapping from cursor to view fields:
		String[] fromFieldNames = new String[] 
				{DBAdapter.KEY_NAME, DBAdapter.KEY_STUDENTNUM, DBAdapter.KEY_STATUS, DBAdapter.KEY_FAVCOLOUR};
		int[] toViewIDs = new int[]
				{R.id.tvName,     R.id.ivPhoto,           R.id.tvStatus,     R.id.tvAmount};
		
		// Create adapter to may columns of the DB onto element in the UI.
		SimpleCursorAdapter myCursorAdapter = 
				new SimpleCursorAdapter(
						this,		// Context
						R.layout.user_view_demo2,	// Row layout template
						cursor,					// cursor (set of DB records to map)
						fromFieldNames,			// DB Column names
						toViewIDs				// View IDs to put information in
						);
		
		// Set the adapter for the list view
		ListView myList = (ListView) findViewById(R.id.lv_testlist);
		myList.setAdapter(myCursorAdapter);
	}
	
}
