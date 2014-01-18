package com.example.counter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;




public class Main extends Activity {

	final ArrayList<String> noteList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//  List view element
		ListView myListView = (ListView)findViewById(R.id.counterlist);
		
		final EditText myEditText = (EditText)findViewById(R.id.countertext);

		final ArrayAdapter<String> aa;
		
		
		aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,noteList);
		
		myListView.setAdapter(aa);
	

		
		//http://android.okhelp.cz/start-activity-from-listview-item-click-android-example/
		 //list item click 
	     myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	               // When clicked, show a toast with the TextView text Game, Help, Home
	               Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();          
	               Intent intent = null;
	               intent = new Intent(getBaseContext(),MainCounter.class);
	               startActivity(intent);   
	    	 }

	     });

		
		//Button
		Button btnSimple = (Button) findViewById(R.id.button1);
		
		btnSimple.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				noteList.add(0, myEditText.getText().toString());
				aa.notifyDataSetChanged();
				myEditText.setText("");
		      }
	   });
		
			
	}

	
	
}
