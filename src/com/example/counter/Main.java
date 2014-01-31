/*
The Main class is the Activity that is responsible for generating the main page of the counter.
It handles creating the Main page which contains resources to create, view and choose which counter
you want to use. This class generates the Main selection page which contains text, listview, and buttons
which have Onclicklisteners to carry out there assigned tasks.

Copyright 2014 Cameron Alexander
<Contact: cpalexan@ualberta.ca>

License GPLv3: GNU GPL Version 3
<http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package com.example.counter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.google.gson.Gson;

/**
*The Main class is the Activity that is responsible for generating the main page of the counter.
*It handles creating the Main page which contains resources to create, view and choose which counter
*you want to use. This class generates the Main selection page which contains text, listview, and buttons
*which have Onclicklisteners to carry out there assigned tasks.
*
**
* @author Cameron Alexander
*
*
**/

public class Main extends Activity {

	//declarted needed variables
	List<String> noteList = new ArrayList<String>();
	String selected = null;
	TextView Intro;
	TextView Yourcounter;
	final String FILENAME2 = "counter2.sav";
	private ListView myListview;
	private EditText myEditText;
	private ArrayAdapter<String> aa = null; 
	List<CounterModel> objList = new ArrayList<CounterModel>();
	List<CounterModel> List2 = new ArrayList<CounterModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//set message introduction
		Intro = (TextView) findViewById(R.id.introText);
		Intro.setText("Enter a name, press + to add counter");
		
		//set your counter text
		Yourcounter = (TextView) findViewById(R.id.yourcounters);
		Yourcounter.setText("Your Counters");
		
		//List view element
	    myListview = (ListView)findViewById(R.id.counterlist);
		myEditText = (EditText)findViewById(R.id.countertext);
	
		 //http://android.okhelp.cz/start-activity-from-listview-item-click-android-example/
		 //list view item click 
	     myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    		 
	               // When clicked, show a toast with the TextView text Game, Help, Home
	               Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();  
	              
	               
	               //http://stackoverflow.com/questions/801193/modify-view-static-variables-while-debugging-in-eclipse
	               String selectedFromList =(String) (myListview.getItemAtPosition(position));
	               
	               //startMainCounter class once listview clicked
	               Intent intent = new Intent(Main.this,MainCounter.class);
	               intent.putExtra("selected1", selectedFromList);
	               startActivity(intent);   
	    	 }

	     });
	     
		//My first button was learned from this online tutorial
	    //http://www.youtube.com/watch?v=q6-4E1JGT_k
		//Button plus sign adds a new counter object, creates new CounterModel object
		Button btnSimple = (Button) findViewById(R.id.summary);
		btnSimple.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			setResult(RESULT_OK);
				
			//input text for new counter
			String text = myEditText.getText().toString().trim();
			text = text.replaceAll("\\s", "");
				
			//checking input
			if(text.equals("")){
				Toast.makeText(getApplicationContext(), "Please Enter a name",Toast.LENGTH_LONG).show();
					return;
				}
			
			if(noteList.contains(text)){
					
			   //http://stackoverflow.com/questions/3500197/how-to-display-toast-in-android
				Toast.makeText(getApplicationContext(), "Counter already exists", Toast.LENGTH_LONG).show();
					return;
					
				}
			
				else{
				
				//if input name is ok, create a new CounterModel and add to list of CounterModels	
				setResult(RESULT_OK);
			    List2.add(new CounterModel(text));
			       
			    //Create new Gson and save object list into file
			    Gson gson = new Gson();
			    String json = gson.toJson(List2);
			    Context context1 = getApplication();
			    LoadSave ls = new LoadSave();
			    ls.saveInFile(json,context1,FILENAME2);	
			    
			    //pack created new text and pass it to MainCounter and start MainCounter activity
			    text = text+" 0";
			    Intent intent = new Intent(Main.this,MainCounter.class);
	            intent.putExtra("selected1", text);
	            startActivity(intent);
	            finish();

				}
		    }
	    });		
	}
	
	//On Pause finish activity
	@Override
	public void onPause() {
	   super.onPause(); 
	   finish();
		        
	}
	
	@Override
	protected void onStart() {
		 super.onStart();
		
		 //Onstart load list of CounterModelObjects from file
		 //get Context off activity 
		 //http://stackoverflow.com/questions/8262279/technique-for-get-the-applicationcontext-anywhere
		 Context context2 = getApplication();
	     LoadSave ld = new LoadSave();
	     List2 = ld.loadFromFile(FILENAME2,objList,context2);
	            
	     //Send List 2 to be sorted
	     //http://stackoverflow.com/questions/4066538/sort-an-arraylist-based-on-an-object-field
	     Collections.sort(List2, new Comparator<CounterModel>(){
	         public int compare(CounterModel o1, CounterModel o2){
	                if(o1.getCount() == o2.getCount())
	                    return 0;
	                return o1.getCount() > o2.getCount() ? -1 : 1;
	                }
	           });
	            
	            
	     //loop through list of Counter Objects and add to listview for display name and count
	     for(int i=0;i<List2.size();i++){
	            	
	         CounterModel cam2 = List2.get(i);
	         String nameofcount = cam2.getText().toString();	
	         int countofcounter = cam2.getCount();
	         String countstring = Integer.toString(countofcounter);
	         
	         //add string + count of object to String list
	         noteList.add(nameofcount+" "+countstring);
	         
	         //set adapter to display String list to listview
	         aa = new ArrayAdapter<String>(this,R.layout.list_item, noteList);
	         myListview.setAdapter(aa);		
		
	    }
	
	}
	
}