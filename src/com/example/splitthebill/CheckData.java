package com.example.splitthebill;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckData extends Activity {

	EditText name, pass, checkname;
	CheckDataBaseAdapter database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkdata);
		
		name = (EditText) findViewById(R.id.et_checkdata_name);
		pass = (EditText) findViewById(R.id.et_checkdata_pass);
		checkname = (EditText) findViewById(R.id.et_checkdata_getdetails);
		database = new CheckDataBaseAdapter(this);
		
	}
public void addUser(View view){
	
	String user = name.getText().toString();
	String password = pass.getText().toString();
	
	long id = database.inserData(user, password);
	if (id<0){
		String message = "bad";
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
	}else{
		String message = "ok";
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
		}
	}

public void viewDetails(View view){
	String data = database.getAllData();
	String message = data;
	Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
}

public void Checkdetails(View view){
	try{
	String s1 = checkname.getText().toString();
	String sub1 = s1.substring(0,s1.indexOf(" "));//from start till a space is found
	String sub2 = s1.substring(s1.indexOf(" ")+1);//from after space
	String s2 = database.getData(sub1,sub2);
	Toast.makeText(getBaseContext(), s2, Toast.LENGTH_LONG).show();
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

public void update(View view)
{
	String upname = name.getText().toString();
	String up = checkname.getText().toString();
	database.updateName(up, upname);
}

public void delete(View view)
{
	String del = checkname.getText().toString();
	database.deleteRow(del);
}

}
