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
	String selected = null;
	
	TextView Intro;
	TextView Yourcounter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//set intro message
		Intro = (TextView) findViewById(R.id.introText);
		Intro.setText("Enter a name, press + to add counter");
		
		Yourcounter = (TextView) findViewById(R.id.yourcounters);
		Yourcounter.setText("Your Counters");
		
		//  List view element
		final ListView myListView = (ListView)findViewById(R.id.counterlist);
		
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
	              
	               
	              // http://stackoverflow.com/questions/801193/modify-view-static-variables-while-debugging-in-eclipse
	               String selectedFromList =(String) (myListView.getItemAtPosition(position));

	               
	               Intent intent = new Intent(Main.this,MainCounter.class);
	               
	               intent.putExtra("selected1", selectedFromList);
	               
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
