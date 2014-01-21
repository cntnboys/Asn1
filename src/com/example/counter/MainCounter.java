package com.example.counter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainCounter extends Activity {

	int count = 0;
	TextView text;
    final String FILENAME = "file4.sav";
	
	
	
	
	//passed variable from Main
	protected String passedVar = null;
	//protected String passedVar2 = null;
	private TextView passedView=null;
	//private TextView passedView2=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_counter);
		
		//get passed variable
		//http://www.youtube.com/watch?v=XPKb_JqeTp8
		
		passedVar = getIntent().getStringExtra("selected1");
		passedView =(TextView)findViewById(R.id.passed);
		passedView.setText(passedVar);
		
		
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
		 
		//Button 5 Back button. Finishes current activity
	     Button btnSimple5 = (Button) findViewById(R.id.delete);	
         btnSimple5.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			
			delete();
			
			
				
			
		}
			
       });   
    }
	
	
	
	
	public void delete(){
		ContextWrapper c = new ContextWrapper(this);
		String delete = c.getFilesDir().getPath().toString()+"/"+ FILENAME;
		File file = new File(delete);
		
		File temp = null;
		try {
			temp = File.createTempFile("file", ".sav", file.getParentFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String charset = "UTF-8";
		
		String deletestring = passedVar;
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			for (String line; (line = reader.readLine()) != null;) {
				line = line.replace(deletestring, "");
				writer.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.close();
			file.delete();
			temp.renameTo(file);	
	}
	
	
	
	
	
	
	
}
