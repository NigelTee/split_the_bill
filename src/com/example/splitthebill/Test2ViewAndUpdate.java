package com.example.splitthebill;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Test2ViewAndUpdate extends ActionBarActivity{

	EditText tv, tv2, tvname;
	String data, data2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test2_viewandupdate);
		tv = (EditText) findViewById(R.id.et_test2viewandupdate_itemnames);
		tv2 = (EditText) findViewById(R.id.et_test2viewandupdate_price);
		tvname = (EditText) findViewById(R.id.et_test2viewandupdate_name);
		DBAdapter_T2 info = new DBAdapter_T2(this);
		DBAdapter_T2 level = new DBAdapter_T2(this);
		
		info.open();
		level.open();
		data = info.getItemname(Test2_2.user);
		data2 = level.getPrice(Test2_2.user);
		info.close();
		level.close();
		tv.setText(data);
		tv2.setText(data2);
		tvname.setText(Test2_2.user);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main3, menu);
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
	
	public void onClick_Update(MenuItem m){
		
		DBAdapter_T2 update = new DBAdapter_T2(this);
		update.open();
		String newitemname = tv.getText().toString();
		String newprice = tv2.getText().toString();
		String newname = tvname.getText().toString();
		update.updateItemName(data, newitemname);
		update.updatePrice(data2, newprice);
		update.updateName(Test2_2.user, newname);
		Toast.makeText(getBaseContext(), "updated", Toast.LENGTH_SHORT).show();
		update.close();
		
	}

	
}
