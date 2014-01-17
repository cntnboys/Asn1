package com.example.counter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class MainCounter extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		
		//Button
		Button btnSimple = (Button) findViewById(R.id.button1);
		
		btnSimple.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		      }
	   });
		
			
	}

	
	
}



