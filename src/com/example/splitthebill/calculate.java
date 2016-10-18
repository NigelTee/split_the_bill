package com.example.splitthebill;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class calculate extends ListActivity {

	String classes[] = {"MainActivity", "DataBaseDemo1", "DataBaseDemo2", "DataBaseDemo3", "Test", "Test2", "CheckData", "Test2ViewAndUpdate"};

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String cheese = classes[position];
		try {
			Class ourClass = Class.forName("com.example.splitthebill." + cheese);
			Intent ourIntent = new Intent(calculate.this, ourClass);
			startActivity(ourIntent);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setListAdapter(new ArrayAdapter<String>(calculate.this,
				android.R.layout.simple_list_item_1, classes));
	}
}
