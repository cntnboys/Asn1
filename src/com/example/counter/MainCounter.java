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
 
    
    //testfilename for saving objects
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
		
		//http://www.youtube.com/watch?v=XPKb_JqeTp8
		passedVar = getIntent().getStringExtra("selected1");
		
	    passedVar = passedVar.substring(0, passedVar.indexOf(" ")); 

		
		
		//Button increment count
		Button btnSimple2 = (Button) findViewById(R.id.button2);
		text = (TextView) findViewById(R.id.text1);
		text.setText("0");
		
		
		
		btnSimple2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count = count + 1;
				text.setText(""+count);
				
				Context context3 = getApplication();	
				UpdateCount ld = new UpdateCount();
			    ld.update(count, context3, FILENAME2, List2, passedVar);
			 
			}
		});
		
		
		
		//Button 3 reset
	     Button btnSimple3 = (Button) findViewById(R.id.button3);
		 btnSimple3.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						count = 0;
						text.setText(""+count);
						Context context3 = getApplication();	
						UpdateCount ld = new UpdateCount();
					    ld.update(count, context3, FILENAME2, List2, passedVar);
						
						
				      }
			   });
		 
		
		 
		 
		 
		//Button 4 Back button. Finishes current activity
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
		 
		 
		 
		 
		 
		 
		 //Button delete button. Finishes current activity
	     Button btnSimple5 = (Button) findViewById(R.id.delete);	
         btnSimple5.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			
			
			Context context3 = getApplication();	
			UpdateCount ld = new UpdateCount();
		    ld.delete(context3, FILENAME2, List2, passedVar);
		     
		    Intent intent = new Intent(MainCounter.this,Main.class);
            startActivity(intent);  
			finish();

		}
			
       });   
         
       //Button Summary button. Finishes current activity
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
	
	
	
	
	//Button edit button. Finishes current activity
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
				//System.out.println(result);
				
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
            // TODO Auto-generated method stub
            super.onStart();
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
            		//System.out.println("passed"+passedVar);
            		passedView =(TextView)findViewById(R.id.passed);
            		
            		passedView.setText(passedVar);
            		
            	    passedView =(TextView)findViewById(R.id.text1);
            	    
            	    int counter = cam2.getCount();
            	    count = counter;
            	    //System.out.println("thisiscounter"+counter);
            		passedView.setText(""+counter);
            		
            		
            	}
            	
            	
            	//cam2.getText().toString();
            	//System.out.println(cam2);
            	
            }
    }
	
}
	
	
	
	
	
	
	
	
	
	
	
	
