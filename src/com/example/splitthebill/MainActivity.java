package com.example.splitthebill;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	EditText ItemName, Price, Overall;
	ListView Display;
	Button Add, Total, Clear;
	Float counter = 0f;
	ArrayList<String> addArray= new ArrayList<String>();
	String item;
	String input;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ItemName = (EditText) findViewById(R.id.etitemname);
		Price = (EditText) findViewById(R.id.etitemprice);
		Overall = (EditText) findViewById(R.id.ettotal);
		Add = (Button) findViewById(R.id.Add);
		Total = (Button) findViewById(R.id.Total);
		Clear = (Button) findViewById(R.id.bclear);
		Display = (ListView) findViewById(R.id.lvdisplay);
		Add.setOnClickListener(this);
		Total.setOnClickListener(this);
		Clear.setOnClickListener(this);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		
		case R.id.Add:
			input = Price.getText().toString();
			item = ItemName.getText().toString();

			if(addArray.contains(item) && addArray.contains(input)){
				Toast.makeText(getBaseContext(), "Item Entered", Toast.LENGTH_LONG).show();
			}else if (item == null || item.trim().equals("")){
				Toast.makeText(getBaseContext(), "Input is Empty", Toast.LENGTH_LONG).show();
			}else if (input == null || input.trim().equals("")){
				Toast.makeText(getBaseContext(), "Input is Empty", Toast.LENGTH_LONG).show();
			}else{
				Float value = Float.parseFloat(input);
				counter = counter + value;
				String total = item + "\t" + input;
				addArray.add(total);// set what to be displayed
				ArrayAdapter<String> adapter = new ArrayAdapter<String> (MainActivity.this, android.R.layout.simple_list_item_1, addArray);
				Display.setAdapter(adapter);
				ItemName.setText("");// clear ItemName
				Price.setText(""); // clear Price
			}
			break;
		
		case R.id.Total:
			Overall.setText("Your Total Is "+counter);
			break;
			
		case R.id.bclear:
			counter = 0f;
			Overall.setText("Your Total Is "+counter);
		}
	}
}
