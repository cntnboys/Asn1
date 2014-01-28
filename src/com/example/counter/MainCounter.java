package com.example.counter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainCounter extends Activity {
	
	Context context = this;
	Button button;
	String result;
	int count = 0;
	TextView text;
    final String FILENAME2 = "counter2.sav";
    List<CounterModel> objList = new ArrayList<CounterModel>();
    
	//passed variable from Main
	protected String passedVar = null;
	private TextView passedView=null;
	List<CounterModel> List2 = new ArrayList<CounterModel>();
	List<CounterModel> inpList = new ArrayList<CounterModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_counter);
		
		//get variable from Main which is equal to the text of counter the user clicked on
		//http://www.youtube.com/watch?v=XPKb_JqeTp8
		passedVar = getIntent().getStringExtra("selected1");
	    passedVar = passedVar.substring(0, passedVar.indexOf(" ")); 

		//Button increments count
		Button btnSimple2 = (Button) findViewById(R.id.button2);
		text = (TextView) findViewById(R.id.text1);
		btnSimple2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count = count + 1;
				text.setText(""+count);
				
				//after button is clicked save updated count information, save everything
				Context context3 = getApplication();	
				UpdateCount ld = new UpdateCount();
			    ld.update(count, context3, FILENAME2, List2, passedVar);
			 
			}
		});
		
		
		
		//Button 3 reset, resets CountModel object when button is pressed.
	     Button btnSimple3 = (Button) findViewById(R.id.button3);
		 btnSimple3.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
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
		 
		 //Button delete. Deletes the curently selected CounterModel object.
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
			
			EditText edittext2 = (EditText)findViewById(R.id.passed);
			String sentname = edittext2.getText().toString();
			Intent intent = new Intent(MainCounter.this,SummaryActivity.class);
            intent.putExtra("selected2", sentname );
            startActivity(intent);  

		}
			
       });   
	
	
	
	
	  //Button edit button. Rename the currently selected Counter
      //http://www.mkyong.com/android/android-prompt-user-input-dialog-example/
      Button btnSimple7 = (Button) findViewById(R.id.button4);	
      btnSimple7.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompts, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);

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
	
	
	//On Pause 
	@Override
	public void onPause() {
	    super.onPause(); 
	    finish();
        
	}
        	

	    
	@Override
    protected void onStart() {
            super.onStart();
            
            //load from file list of counter models
            Context context2 = getApplication();
            LoadSave ld = new LoadSave();
            List2 = ld.loadFromFile(FILENAME2,objList,context2);
            
            //loop through counter objects and get information corresponding to Counter Selected from Main
            //set info into the right feilds for display
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
	
	
	
	
	
	
	
	
	
	
	
	
