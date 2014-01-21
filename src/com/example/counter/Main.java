package com.example.counter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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

	ArrayList<String> noteList = new ArrayList<String>();
	String selected = null;
	TextView Intro;
	TextView Yourcounter;
	
	
	private static final String FILENAME = "file3.sav";
	private ListView myListview;
	private EditText myEditText;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//set message introduction
		Intro = (TextView) findViewById(R.id.introText);
		Intro.setText("Enter a name, press + to add counter");
		
		Yourcounter = (TextView) findViewById(R.id.yourcounters);
		Yourcounter.setText("Your Counters");
		
		//  List view element
	    myListview = (ListView)findViewById(R.id.counterlist);
		myEditText = (EditText)findViewById(R.id.countertext);

		//final ArrayAdapter<String> aa;

		//aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,noteList);
		
		//myListView.setAdapter(aa);
	

		
		//http://android.okhelp.cz/start-activity-from-listview-item-click-android-example/
		 //list item click 
	     myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    		 
	    		   
	    		 
	    		 
	               // When clicked, show a toast with the TextView text Game, Help, Home
	               Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();  
	              
	               
	              // http://stackoverflow.com/questions/801193/modify-view-static-variables-while-debugging-in-eclipse
	               String selectedFromList =(String) (myListview.getItemAtPosition(position));

	               
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
				//noteList.add(0, myEditText.getText().toString());
				//aa.notifyDataSetChanged();
				//myEditText.setText("");
				
				setResult(RESULT_OK);
				String text = myEditText.getText().toString();
				saveInFile(text+"\n");
				
		   }
	   });
			
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		List<String> tweets = loadFromFile();
		
		System.out.println(tweets);
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);
		myListview.setAdapter(aa);
		
	}

	private List<String> loadFromFile() {
		
		List<String> tweets = new ArrayList<String>();
		//System.out.println(tweets);
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				//System.out.println(tweets);
				line = in.readLine();
			}
			//System.out.println(tweets);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets;
		//return tweets.toArray(new String[tweets.size()]);
	}
	
	
	
	
	private void saveInFile(String text) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(new String(text)
					.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
