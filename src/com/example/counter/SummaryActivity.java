package com.example.counter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends Activity {
	
	
	List<CounterModel> List2 = new ArrayList<CounterModel>();
	final String FILENAME2 = "counter2.sav";
    List<CounterModel> objList = new ArrayList<CounterModel>();
    protected String passedVar = null;
    protected List<Date> datelist2 = new ArrayList<Date>();

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
            		
            		Context context3 = getApplication();	
				    DateParse ld3 = new DateParse();
				    ld3.year(context3, datelist2);
					
            		
            		
            		
            		
            	}
            
           
            }
            
            
      }

	
}
