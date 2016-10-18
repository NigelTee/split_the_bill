package com.example.splitthebill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Test extends Activity{

EditText name, amount;
	
	int[] imageIDs = {
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
	};
	int nextImageIndex = 0;
	DBAdapter myDb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_1);
		name = (EditText) findViewById(R.id.et_testname);
		amount = (EditText) findViewById(R.id.et_testamount);
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
		myDb = new DBAdapter(this);
		myDb.open();
	}
	private void closeDB(){
		myDb.close();
	}
	
	public void onClick_Add(View v){
		int imageId = imageIDs[nextImageIndex];
		nextImageIndex = (nextImageIndex + 1)% imageIDs.length;
	
		String getamount = "";
		String status = null;
		String getname = "";
		
		String getnamecheck = name.getText().toString();
		String getamountcheck = amount.getText().toString();
		
		getname = getname + getnamecheck;
		getamount = getamount + getamountcheck;
		
		if (getname == "" && getamount == ""){
			Toast.makeText(getBaseContext(), "Name And Amount Not Entered", Toast.LENGTH_LONG).show();
		}else if(getname == ""){
			Toast.makeText(getBaseContext(), "Name Not Entered", Toast.LENGTH_LONG).show();
		}else if(getamount == ""){
			Toast.makeText(getBaseContext(), "Amount Not Entered", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(getBaseContext(), "Details Entered", Toast.LENGTH_LONG).show();
			
			
		Float famount = Float.parseFloat(getamount);
		if (famount == 0f){
		status = "Cleared";
		}else{
		status = "due";	
		}
		
		myDb.insertRow(getname, imageId, getamount, status);}
	}
	
	public void onClick_Display(View v){
		Intent i = new Intent("com.example.splitthebill.TEST_2");
		startActivity(i);
		
	}
	
	public void onCLick_ClearAll(View v){
		myDb.deleteAll();
	}	
	

}
