/*
The MainCounter class represents the Activity that is responsible for displaying the information from
counter selected from Main. It is where the CounterModel Objects can be unpacked and displayed and modified.
It allows the user to increment the counter, reset, delete , rename or go to a statistics summary page.
It is composed of some text fields and some buttons with listeners with a dialog box for renaming. 

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
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
*The MainCounter class is the Activity that is responsible for displaying the information from the
*counter selected from Main ACtivity. It is where CounterModel Objects can be unpacked and displayed and modified.
*It allows the user to increment the counter, reset, delete , rename or go to a statistics summary page.
*It is composed of some text fields and some buttons with listeners with a dialog box for renaming.
*
* @author Cameron Alexander
*
*/

public class MainCounter extends Activity {
	
	//declared all needed variables
	Context context = this;
	String result;
	int count = 0;
	TextView text;
    final String FILENAME2 = "counter2.sav";
    List<CounterModel> objList = new ArrayList<CounterModel>();
    List<CounterModel> List2 = new ArrayList<CounterModel>();
    
	//passed variable from Main
	protected String passedVar = null;
	private TextView passedView=null;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_counter);
		getActionBar().setTitle("Cpalexan-Counter");
		
		//get variable from Main which is equal to the text of counter the user clicked on
		//http://www.youtube.com/watch?v=XPKb_JqeTp8
		passedVar = getIntent().getStringExtra("selected1");
	    passedVar = passedVar.substring(0, passedVar.indexOf(" ")); 

		//Button increments count, Click me
		Button btnSimple2 = (Button) findViewById(R.id.button2);
		text = (TextView) findViewById(R.id.text1);
		btnSimple2.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
				
			count = count + 1;
			text.setText(""+count);
				
			//after button is clicked save updated count information, save everything
			Context context3 = getApplication();	
			UpdateCount ld = new UpdateCount();
			ld.update(count, context3, FILENAME2, List2, passedVar);
			 
		}
});
		
		
		
		//Button 3 reset, resets CountModel object when button is pressed, calls reset
	    Button btnSimple3 = (Button) findViewById(R.id.button3);
	    btnSimple3.setOnClickListener(new View.OnClickListener() {
					
		@Override
		public void onClick(View v) {
		
			count = 0;
			text.setText(""+count);
			Context context3 = getApplication();	
			UpdateCount ld = new UpdateCount();
		    ld.reset(count, context3, FILENAME2, List2, passedVar);								
	   }
 });
		 
		 //Button 4 Back button. Finishes current activity returns to Main Activity
	     Button btnSimple4 = (Button) findViewById(R.id.backbutton);
		 btnSimple4.setOnClickListener(new View.OnClickListener() {
					
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainCounter.this,Main.class);
			startActivity(intent);  
			finish();
						
						
	    }
});
		 
	    //Button delete. Deletes the counter selected currently,deletes its object CounterModel.
	    Button btnSimple5 = (Button) findViewById(R.id.delete);	
        btnSimple5.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			
			//delete CounterModel Object
			Context context3 = getApplication();	
			UpdateCount ld = new UpdateCount();
		    ld.delete(context3, FILENAME2, List2, passedVar);
		    
		    //return to Main Activity after successful delete
		    Intent intent = new Intent(MainCounter.this,Main.class);
            startActivity(intent);  
			finish();

		}
			
 });   
         
         
         //Button Summary. Navigates to the Summary Activity to display stats
	     Button btnSimple55 = (Button) findViewById(R.id.summary);	
         btnSimple55.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			
			//pass name of counter to summary page , go to summary activity
			EditText edittext2 = (EditText)findViewById(R.id.passed);
			String sentname = edittext2.getText().toString();
			Intent intent = new Intent(MainCounter.this,SummaryActivity.class);
            intent.putExtra("selected2", sentname );
            startActivity(intent);  

	   }
			
  });   

	
	  //Button edit button. Rename the currently selected Counter, dialogue box pops up for rename
      //http://www.mkyong.com/android/android-prompt-user-input-dialog-example/
      Button btnSimple7 = (Button) findViewById(R.id.button4);	
      btnSimple7.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompts, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

		// set dialog message
		alertDialogBuilder
			.setCancelable(false)
			.setPositiveButton("OK",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
				// get user input and set it to result
				// edit text
				result = userInput.getText().toString();
				
				Context context3 = getApplication();	
				UpdateCount ld233 = new UpdateCount();
			    ld233.rename(result, context3, FILENAME2, List2, passedVar);
			    Intent intent = new Intent(MainCounter.this,Main.class);
		        startActivity(intent);  
				finish();
			    }
			  })
			.setNegativeButton("Cancel",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
				dialog.cancel();
			    }
			  });

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
		
	    }
    });
}
	
	
	//On Pause finish activity
	@Override
	public void onPause() {
	    super.onPause(); 
	    finish();
        
	}
     
	
	//On starting activity load all CounterModel Objects from file, find the right one that was selected on Main, 
	//unpack the object and retrieve necessary information to display on the MainCounter Activity.
	@Override
    protected void onStart() {
            super.onStart();
            
            //load from file list of counter models
            Context context2 = getApplication();
            LoadSave ld = new LoadSave();
            List2 = ld.loadFromFile(FILENAME2,objList,context2);
            
            //loop through counter objects and get information corresponding to Counter Selected from Main
            //set info into the right fields for display
            for(int i=0;i<List2.size();i++){
            	
            	CounterModel cam2 = List2.get(i);
            	String strincheck = cam2.getText();
            	
            	//check for the right Object to access
            	if(strincheck.equals(passedVar)){
          
            		//display text
            		passedView =(TextView)findViewById(R.id.passed);
            		passedView.setText(passedVar);
            	    passedView =(TextView)findViewById(R.id.text1);
            	    
            	    //getcount display
            	    int counter = cam2.getCount();
            	    count = counter;
            		passedView.setText(""+counter);
            		
            		
            	}    	
          }
	 }	
}
	
	
	
	
	
	
	
	
	
	
	
	
