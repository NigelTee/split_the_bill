package com.example.splitthebill;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DataBaseDemo1 extends Activity implements OnClickListener{

	Button Add, Clear, Display;
	TextView textview;
	EditText entername;
	DBAdapter myDB;
	int id;
	static String name;
	int studentNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.databasedemo1);
		Add = (Button) findViewById(R.id.bADD);
		Clear = (Button) findViewById(R.id.bCLEAR);
		Display = (Button) findViewById(R.id.bDisplay);
		textview = (TextView) findViewById(R.id.tvText);
		entername = (EditText) findViewById(R.id.etentername);
		
		Add.setOnClickListener(this);
		Clear.setOnClickListener(this);
		Display.setOnClickListener(this);
		
		openDB();
	}


	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		closeDB();
	}

	private void openDB() {
		// TODO Auto-generated method stub
		myDB = new DBAdapter(this);
		myDB.open();
	}

	private void closeDB() {
		// TODO Auto-generated method stub
		myDB.close();
	}

	private void displayText(String message){
		
		textview.setText(message);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		
		case R.id.bADD:
			displayText("User Added");
			String name = entername.getText().toString();
			long newId = myDB.insertRow(name, 123, "favcolor", "not paid");
			
			
		break;
		
		case R.id.bCLEAR:
			displayText("User Cleared");
			myDB.deleteAll();
		break;
		
		case R.id.bDisplay:
			displayText("Display Users");
			
			Cursor cursor = myDB.getAllRows();
			displayRecordSet(cursor);
			
		break;
		}
	}


	public void displayRecordSet(Cursor cursor) {
		// TODO Auto-generated method stub
		String message = "";
		
		if(cursor.moveToFirst()){
			//process data
			do{
			id = cursor.getInt(DBAdapter.COL_ROWID);
			name = cursor.getString(DBAdapter.COL_NAME);
			studentNumber = cursor.getInt(DBAdapter.COL_STUDENTNUM);
		
		message += "id" + id +" , " +
				   "name = "+name+" " +
				   "studentNumber "+studentNumber+
				   "\n";
			}while(cursor.moveToNext());
			
			cursor.close();
			
		}
		displayText(message);
	}

}
