package com.example.counter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;




public class Main extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//  List view element
		ListView myListView = (ListView)findViewById(R.id.counterlist);
		
		final EditText myEditText = (EditText)findViewById(R.id.countertext);

		final ArrayList<String> noteList = new ArrayList<String>();
		
		final ArrayAdapter<String> aa;
		
		aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,noteList);
		
		myListView.setAdapter(aa);
	

		// list item click 
	   //  myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	     //               @Override
	      //              public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	                        // TODO Auto-generated method stub



	        //Intent myIntent = new Intent(this, abc.class); 
	                    
	     // here arg2 is argument of onitemclick method
	     // this will pick the same item from array list that is clicked on list view 
	      //               i.putExtra("key_name" , Id.get(arg2));
	      //               i.putExtra("key_name" , Name.get(arg2));
	      //               i.putExtra("key_name" , Gender.get(arg2));

	          //           startActivity(i);       


	            //         }

	              //   });

		
		//Button
		Button btnSimple = (Button) findViewById(R.id.button1);
		
		btnSimple.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				noteList.add(0, myEditText.getText().toString());
				aa.notifyDataSetChanged();
				myEditText.setText("");
		      }
	   });
		
			
	}

	
	
}
