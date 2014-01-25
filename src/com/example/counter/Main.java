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




public class Main extends Activity {

	List<String> noteList = new ArrayList<String>();
	String selected = null;
	TextView Intro;
	TextView Yourcounter;
	
	
	private static final String FILENAME = "file4.sav";
	final String FILENAME2 = "counter2.sav";
	private ListView myListview;
	private EditText myEditText;
	private ArrayAdapter<String> aa = null; 
	
	List<CounterModel> objList = new ArrayList<CounterModel>();
	List<CounterModel> List2 = new ArrayList<CounterModel>();
	    
	
	
	//On Pause 
		@Override
		public void onPause() {
		    super.onPause(); 
		    finish();
	        
		}
	
	
	
	
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
		
		//Button plus sign
		Button btnSimple = (Button) findViewById(R.id.summary);
		btnSimple.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				setResult(RESULT_OK);
				String text = myEditText.getText().toString().trim();


				//System.out.println(noteList);
				
				
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
					
				//saveInFile(text+"\n");
				//noteList.add(text);
				
				//passedView.setText(passedVar);
				//myEditText.setText("");
					
				setResult(RESULT_OK);
			    List2.add(new CounterModel(text));
			        
			    //System.out.println("here"+objList);
			       
			    Gson gson = new Gson();
			    String json = gson.toJson(List2);
			        
			    Context context1 = getApplication();
			    LoadSave ls = new LoadSave();
			    ls.saveInFile(json,context1,FILENAME2);	
			    
			    text = text+" 0";
			    
			    Intent intent = new Intent(Main.this,MainCounter.class);
	            intent.putExtra("selected1", text);
	            startActivity(intent);
	            finish();
			
				}
				
		   }
	   });
			
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//List<String> tweets = loadFromFile();
		//noteList = tweets;
		
		//System.out.println(tweets);
		
		
				
				Context context2 = getApplication();
	            LoadSave ld = new LoadSave();
	            List2 = ld.loadFromFile(FILENAME2,objList,context2);
	            
	            
	           // http://stackoverflow.com/questions/4066538/sort-an-arraylist-based-on-an-object-field
	            // send List 2 to be sorted
	            Collections.sort(List2, new Comparator<CounterModel>(){
	                public int compare(CounterModel o1, CounterModel o2){
	                    if(o1.getCount() == o2.getCount())
	                        return 0;
	                    return o1.getCount() > o2.getCount() ? -1 : 1;
	                }
	           });
	            
	            
	            
	            
	            //loop through counter objects
	            for(int i=0;i<List2.size();i++){
	            	
	            	CounterModel cam2 = List2.get(i);
	            	String nameofcount = cam2.getText().toString();
	            	
	            	int countofcounter = cam2.getCount();
	            	String countstring = Integer.toString(countofcounter);
	            	
	            	noteList.add(nameofcount+" "+countstring);
	            	//System.out.println(nameofcount+countstring);
	            	
	            	 aa = new ArrayAdapter<String>(this,
	            						R.layout.list_item, noteList);
	            				 myListview.setAdapter(aa);		
		
	            }

	
	}
	
}