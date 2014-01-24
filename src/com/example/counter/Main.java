package com.example.counter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

	List<String> noteList = new ArrayList<String>();
	String selected = null;
	TextView Intro;
	TextView Yourcounter;
	
	
	private static final String FILENAME = "file4.sav";
	private ListView myListview;
	private EditText myEditText;
	private ArrayAdapter<String> aa = null; 
	
	
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
				
				setResult(RESULT_OK);
				String text = myEditText.getText().toString().trim();

;
				System.out.println(noteList);
				
				
				if(text.equals("")){
					Toast.makeText(getApplicationContext(), "Please Enter a name",
							   Toast.LENGTH_LONG).show();
					return;
				}
				
				if(noteList.contains(text)){
					//http://stackoverflow.com/questions/3500197/how-to-display-toast-in-android
					Toast.makeText(getApplicationContext(), "Counter already exists",
							   Toast.LENGTH_LONG).show();
					return;
					
				}
			
				else{
					
				saveInFile(text+"\n");
				noteList.add(text);
				aa.notifyDataSetChanged();
				//passedView.setText(passedVar);
				myEditText.setText("");
				
			
				}
				
		   }
	   });
			
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		List<String> tweets = loadFromFile();
		noteList = tweets;
		
		//System.out.println(tweets);
		
		 aa = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);
		myListview.setAdapter(aa);
		
	}

	
	
	//Used from Lonely Twitter and modified accordingly
	private List<String> loadFromFile() {
		
		List<String> tweets = new ArrayList<String>();
		//System.out.println(tweets);
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				if (!line.equals("")) // don't write out blank lines
			    {
				tweets.add(line);
			    }
				line = in.readLine();
			
				//System.out.println(line);
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
	
