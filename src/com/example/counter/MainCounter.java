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
		passedVar = getIntent().getStringExtra("selected1");
		
		//textview
		passedView =(TextView)findViewById(R.id.passed);
		
		passedView.setText("you clicked"+ passedVar);
		
		
		
		
		
		//Button
		Button btnSimple2 = (Button) findViewById(R.id.button2);
		text = (TextView) findViewById(R.id.text1);
		btnSimple2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count = count + 1;
				text.setText(""+count);
				
				
		      }
	   });
		
			
	}

	
	
}



