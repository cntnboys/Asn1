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
import android.widget.TextView;

public class SummaryActivity extends Activity {
	
	
	List<CounterModel> List2 = new ArrayList<CounterModel>();
	final String FILENAME2 = "counter2.sav";
    List<CounterModel> objList = new ArrayList<CounterModel>();
    protected String passedVar = null;
    protected List<Date> datelist2 = new ArrayList<Date>();
    private ListView myListview2;
    private ArrayAdapter<String> aa2 = null; 

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
	
	
	
	
	@Override
    protected void onStart() {
            // TODO Auto-generated method stub
            super.onStart();
            
            System.out.println("SummaryPassed "+passedVar);
            //list of counter models
            Context context2 = getApplication();
            LoadSave ld = new LoadSave();
            List2 = ld.loadFromFile(FILENAME2,objList,context2);
           // System.out.println("List2"+List2); 
            
            //loop through counter objects
            for(int i=0;i<List2.size();i++){
            	
            	CounterModel cam2 = List2.get(i);
            	String strincheck = cam2.getText();
            	
            	
            	if(strincheck.equals(passedVar)){
            		
            		datelist2 = cam2.getDatelist();
            		System.out.println(datelist2);
            		
            		//year stats
            		Context context3 = getApplication();	
				    DateParse ld3 = new DateParse();
				    List<String> listyear = ld3.year(context3, datelist2);
				    
				    //month stats
				    DateParse ld4 = new DateParse();
				    List<String> listmonth =ld4.month(context3, datelist2);
				    
				    //week stats
				    DateParse ld5 = new DateParse();
				    List<String> listweek =ld5.week(context3, datelist2);
					
				    //day stats
				    DateParse ld6 = new DateParse();
				    List<String> listday =ld6.day(context3, datelist2);
				    
				    
				    //hour stats
				    DateParse ld7 = new DateParse();
				    List<String> listhour =ld7.hour(context3, datelist2);
				    
				    
				    //add lists together for display
				    List<String> FinalList = new ArrayList<String>();
				    String year = "---YEAR---";
				    FinalList.add(year);
				    FinalList.addAll(listyear);
				    String month = "---Month---";
				    FinalList.add(month);
				    FinalList.addAll(listmonth);
				    String week = "---Week---";
				    FinalList.add(week);
				    FinalList.addAll(listweek);
				    String day = "---Day---";
				    FinalList.add(day);
				    FinalList.addAll(listday);
				    String hour = "---Hour---";
				    FinalList.add(hour);
				    FinalList.addAll(listhour);
				    
				    System.out.println("FInallist"+FinalList);
				    
				    myListview2 = (ListView)findViewById(R.id.counterlist);
				    aa2 = new ArrayAdapter<String>(this,
    						R.layout.list_item, FinalList);
    				 myListview2.setAdapter(aa2);		

            		
            		
            	}
            
           
            }
            
            
      }

	
}
