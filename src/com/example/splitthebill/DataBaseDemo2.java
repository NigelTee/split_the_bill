package com.example.splitthebill;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DataBaseDemo2 extends Activity{

	private List<UsersDemo2> myUsers = new ArrayList<UsersDemo2>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.databasedemo2);
		
		populateUserList();
		populateListView();
	}



	private void populateUserList() {
		// TODO Auto-generated method stub

			myUsers.add(new UsersDemo2("Nigel", 1, R.drawable.ic_launcher, "notcleared"));

	}
	
	private void populateListView() {
		// TODO Auto-generated method stub
		ArrayAdapter<UsersDemo2> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.lvUsersListView);
		list.setAdapter(adapter);
	}
	
	private class MyListAdapter extends ArrayAdapter<UsersDemo2> {
		public MyListAdapter() {
			super(DataBaseDemo2.this, R.layout.user_view_demo2, myUsers);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View itemView = convertView;
			if (itemView == null){
				itemView = getLayoutInflater().inflate(R.layout.user_view_demo2, parent, false);
			}
			
			UsersDemo2 CurrentUser = myUsers.get(position);
			
			ImageView imageView = (ImageView) itemView.findViewById(R.id.ivPhoto);
			imageView.setImageResource(CurrentUser.getId());
			
			TextView Name = (TextView) itemView.findViewById(R.id.tvName);
			Name.setText(CurrentUser.getName());
			
			TextView Amount = (TextView) itemView.findViewById(R.id.tvAmount);
			Amount.setText(""+ CurrentUser.getAmount());
			
			TextView Condition = (TextView) itemView.findViewById(R.id.tvStatus);
			Condition.setText(CurrentUser.getCondition());
			
			return itemView;
		}
	
	
	}

}
