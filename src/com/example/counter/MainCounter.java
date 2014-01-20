package com.example.counter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainCounter extends Activity {

	int count = 0;
	TextView text;
	
	
	
	//passed variable from Main
	protected String passedVar = null;
	private TextView passedView=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_counter);
		
		//get passed variable
		//http://www.youtube.com/watch?v=XPKb_JqeTp8
		passedVar = getIntent().getStringExtra("selected1");
		passedView =(TextView)findViewById(R.id.passed);
		passedView.setText("Counter: "+ passedVar);
		
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
						
						
				      }
			   });
		 
		//Button 4 Back button. Finishes current activity
	     Button btnSimple4 = (Button) findViewById(R.id.backbutton);
		 btnSimple4.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
						
						
				      }
			   });
		
		
		
		
		
			
	}

	
	
}



