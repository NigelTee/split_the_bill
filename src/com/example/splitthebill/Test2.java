package com.example.splitthebill;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Test2 extends ActionBarActivity{

EditText item_name, price, user, tax;
static TextView total;
static Float total1 = 0f;

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
	DBAdapter_T2 myDb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test2_1);
		item_name = (EditText) findViewById(R.id.et_test2_itemname);
		price = (EditText) findViewById(R.id.et_test2_price);
		tax = (EditText) findViewById(R.id.et_test2_tsc);
		user = (EditText) findViewById(R.id.et_test2_user);
		total = (TextView) findViewById(R.id.tv_test2_totaldisplay);
		openDB();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.action_settings) {
				
			return true;
		}
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
	
	public void onClick_AddDetails(View v){
		int imageId;
		imageId = imageIDs[nextImageIndex];
		nextImageIndex = (nextImageIndex + 1)% imageIDs.length;
		
		String getitemname = "";
		String getprice = "";
		String gettotalprice = "";
		String status = null;
		String getuser = "";
		String gettax = "";
		
		String itemname = item_name.getText().toString();
		String usercheck = user.getText().toString();
		String pricecheck = price.getText().toString();
		String taxcheck = tax.getText().toString();
		
		getitemname = getitemname + itemname;
		getuser = getuser + usercheck;
		getprice = getprice + pricecheck;
		gettax = gettax + taxcheck;
		
		if (getuser == "" && getprice == "" && getitemname == "" && gettax == ""){
			Toast.makeText(getBaseContext(), "Item Name, User And Amount Not Entered", Toast.LENGTH_LONG).show();
		}else if(getitemname == ""){
			Toast.makeText(getBaseContext(), "ItemName Not Entered", Toast.LENGTH_LONG).show();
		}else if(getprice == ""){
			Toast.makeText(getBaseContext(), "Amount Not Entered", Toast.LENGTH_LONG).show();
		}else if(getuser == ""){
			Toast.makeText(getBaseContext(), "Name Not Entered", Toast.LENGTH_LONG).show();
		}else if(gettax == ""){
			Toast.makeText(getBaseContext(), "Tax Not Entered", Toast.LENGTH_LONG).show();
		}else{
			
		Float amount = Float.parseFloat(getprice);
		Float tamount = Float.parseFloat(gettax);
		Float famount = amount*tamount/100 + amount;
		getprice = Float.toString(famount);
		gettotalprice = getprice;
		
		if (famount == 0f){
		status = "Cleared";
		}else{
		status = "due";	
		}
		
		total1 = total1 + famount;
		total.setText(Float.toString(total1));
		
		//check name if it is the same as keyed in
		String ID = myDb.getData(getuser);
		if (ID == ""){
			//take in data
			myDb.insertRow(getuser, getitemname, getprice, gettotalprice, imageId, status);
			Toast.makeText(getBaseContext(), "Details Entered", Toast.LENGTH_SHORT).show();
		}else{
			//get old data
			//Toast.makeText(getBaseContext(), ID, Toast.LENGTH_SHORT).show();
			try {
			String returnedName = myDb.getSameUserData(ID);
			String returnedItemName = myDb.getItemname(returnedName);
			String returnedPrice = myDb.getPrice(returnedName);
			String returnedTotalPrice = myDb.getTotalPrice(returnedName);
			//add with old data
			
			getitemname = getitemname + "\n" + returnedItemName;
			getprice = getprice + "\n" + returnedPrice;
			getuser = returnedName;
			Float newamount = Float.parseFloat(returnedTotalPrice);
			Float updatedamount = newamount + famount;
			gettotalprice = Float.toString(updatedamount);
			
			Toast.makeText(getBaseContext(), getitemname + "\n" + getuser + "\n" +  getprice + "\n" +  gettotalprice, Toast.LENGTH_SHORT).show();
			//update database
			myDb.updateRow(ID, getuser, getitemname, getprice, gettotalprice, imageId, status);
			
			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No Such User");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
		}
		
		
		item_name.setText(null);
		user.setText(null);
		price.setText(null);
		tax.setText(null);
		
		}
	}
	
	public void onClick_Display(MenuItem m){
		Intent i = new Intent("com.example.splitthebill.TEST2_2");
		startActivity(i);
	}
	
}

