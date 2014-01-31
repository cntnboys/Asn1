/*
The SummaryActivity class represents the Activity that is responsible for retrieving data for counters and 
displaying it in a specific manner. The data is retrieved from within a CounterModel object and processed using 
the DataParse class which does the data work. 

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
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
*The SummaryActivity class represents the Activity that is responsible for retrieving data for counters and 
*displaying it in a specific manner. The data is retrieved from within a CounterModel object and processed using 
*the DataParse class which does the data work. 
*
* @author Cameron Alexander
*
*/

public class SummaryActivity extends Activity {
	
	//declare variables needed
	List<CounterModel> List2 = new ArrayList<CounterModel>();
	final String FILENAME2 = "counter2.sav";
    List<CounterModel> objList = new ArrayList<CounterModel>();
    protected String passedVar = null;
    protected List<Date> datelist2 = new ArrayList<Date>();
    private ListView myListview2;
    private ArrayAdapter<String> aa2 = null; 

    
    
    //OnCreate retrieve data sent from MainCounter and initialise button
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		
		//http://www.youtube.com/watch?v=XPKb_JqeTp8
		passedVar = getIntent().getStringExtra("selected2");
		
		//Button Back button. Return to Main menu
	     Button btnSimple45 = (Button) findViewById(R.id.back2);
		 btnSimple45.setOnClickListener(new View.OnClickListener() {
					
		@Override
		public void onClick(View v) {
		     // TODO Auto-generated method stub
			 Intent intent2 = new Intent(SummaryActivity.this,Main.class);
			 startActivity(intent2);  
			 finish();
					
		   }
     });		
}
	
	   //On Pause 
	   @Override
	   public void onPause() {
			super.onPause(); 
			finish();
		        
	}
	
	//Onstart retreive list of CounterModel objects from file, find the right object and retrieve the list of Dates from object
	//Process the data retrieved and display appropriately.
	@Override
    protected void onStart() {
            // TODO Auto-generated method stub
            super.onStart();
           
            //get list of counter models from file
            Context context2 = getApplication();
            LoadSave ld = new LoadSave();
            List2 = ld.loadFromFile(FILENAME2,objList,context2);
           
            
            //loop through counter objects
            for(int i=0;i<List2.size();i++){
            	
            	CounterModel cam2 = List2.get(i);
            	String strincheck = cam2.getText();
            	
            	
            	if(strincheck.equals(passedVar)){
            		
            		datelist2 = cam2.getDatelist();
            		System.out.println(datelist2);
            		
            		//get year stats
            		Context context3 = getApplication();	
				    DateParse ld3 = new DateParse();
				    List<String> listyear = ld3.year(context3, datelist2);
				    
				    //get month stats
				    DateParse ld4 = new DateParse();
				    List<String> listmonth =ld4.month(context3, datelist2);
				    
				    //get week stats
				    DateParse ld5 = new DateParse();
				    List<String> listweek =ld5.week(context3, datelist2);
					
				    //get day stats
				    DateParse ld6 = new DateParse();
				    List<String> listday =ld6.day(context3, datelist2);
				    
				    
				    //get hour stats
				    DateParse ld7 = new DateParse();
				    List<String> listhour =ld7.hour(context3, datelist2);
				    
				    //get min stats
				    DateParse ld8 = new DateParse();
				    List<String> listmin =ld8.minute(context3, datelist2);
				    
				    
				    //add lists together for display
				    List<String> FinalList = new ArrayList<String>();
				    String year = "---YEAR---";
				    FinalList.add(year);
				    FinalList.addAll(listyear);
				    String month = "---MONTH---";
				    FinalList.add(month);
				    FinalList.addAll(listmonth);
				    String week = "---WEEK---";
				    FinalList.add(week);
				    FinalList.addAll(listweek);
				    String day = "---DAY---";
				    FinalList.add(day);
				    FinalList.addAll(listday);
				    String hour = "---HOUR---";
				    FinalList.add(hour);
				    FinalList.addAll(listhour);
				    String mins = "---MIN---";
				    FinalList.add(mins);
				    FinalList.addAll(listmin);
				    
		
				    //set list adapter with information and display
				    myListview2 = (ListView)findViewById(R.id.counterlist);
				    aa2 = new ArrayAdapter<String>(this,
    						R.layout.list_item, FinalList);
    				 myListview2.setAdapter(aa2);		

            		
            		
            	}
            
           
            }
            
            
      }

	
}
