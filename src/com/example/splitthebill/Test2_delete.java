package com.example.splitthebill;


import com.example.splitthebill.R;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class Test2_delete extends ActionBarActivity{

	DBAdapter_T2 myDb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_2);
		openDB();
		populateListViewFromDB();
		registerListCallBack();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		return super.onOptionsItemSelected(item);
	}
	
		@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		closeDB();
	}

	private void openDB() {
		// TODO Auto-generated method stub
		myDb = new DBAdapter_T2(this);
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
				{DBAdapter_T2.KEY_USER, DBAdapter_T2.KEY_AMOUNT, DBAdapter_T2.KEY_IMAGE, DBAdapter_T2.KEY_STATUS};
		int[] toViewIDs = new int[]
				{R.id.tvName, R.id.tvAmount, R.id.ivPhoto, R.id.tvStatus};
		
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

	
	private void registerListCallBack() {
		// TODO Auto-generated method stub
		ListView myList = (ListView) findViewById(R.id.lv_testlist);
		myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewCLicked, 
					int position, long idinDb) {
				// TODO Auto-generated method stub
				
				Cursor cursor = myDb.getRow(idinDb);
				if (cursor.moveToFirst()) {
					long idDB = cursor.getLong(DBAdapter_T2.COL_ROWID);
					String user = cursor.getString(DBAdapter_T2.COL_USER);
					String amount = cursor.getString(DBAdapter_T2.COL_AMOUNT);
					String status = cursor.getString(DBAdapter_T2.COL_STATUS);
				
					myDb.deleteRow(idDB);
					populateListViewFromDB();
					
					String message = "User: "+ user + " Amount: "+ amount + " Status: "+ status + " is deleted.";
					Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
				}
				cursor.close();
			}
		});
	}

	
}