package com.example.counter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SummaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		
		
		
		
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

	
}
