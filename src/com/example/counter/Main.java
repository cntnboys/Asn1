package com.example.counter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;




public class Main extends Activity {

	public final static String Id_Extra="com.example.counter._ID";
	
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
		
		myListView.setOnItemClickListener(onListClick);
		
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
	
	private AdapterView.OnItemClickListener onListClick = new Adapterview.OnItemClickListener(){
		public void onItemClick(AdapterView<?> parent, View view, int position, long id){
			Intent i=new Intent(Main,this, Avtivity2.class);
			
		}
	}
	
}
