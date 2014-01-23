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
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainCounter extends Activity {

	int count = 0;
	TextView text;
    final String FILENAME = "file4.sav";
    
    //testfilename for saving objects
    final String FILENAME2 = "counter.sav";
    
	
	//passed variable from Main
	protected String passedVar = null;
	private TextView passedView=null;
	List<Object> List2 = new ArrayList<Object>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_counter);
		
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
			finish();
			
			
				
			
		}
			
       });   
	}
	
	
	@Override
    protected void onStart() {
            // TODO Auto-generated method stub
            super.onStart();
            List<Object> tweets = (List<Object>) loadFromFile();
            List2=tweets;
            System.out.println(tweets);
    }
	
	
	
	@Override
	public void onPause() {
	    super.onPause();  // Always call the superclass method first
	    
	    setResult(RESULT_OK);
	    String text = passedVar;
        //saveInFile(text, new Date(System.currentTimeMillis()));
        
        CounterModel obj = new CounterModel(text);
        obj.setText(text);
        obj.setCount(count);
      //  obj.setTimestamp(new Date(System.currentTimeMillis()));
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        saveInFile(json);

	    
	    }
	
	
	
	private List<Object> loadFromFile() {
        List<Object> tweets = new ArrayList<Object>();
         List2 = tweets;
        //LonelyTweetModel simpleClass = null;
        try {
                FileInputStream fis = openFileInput(FILENAME2);
                //BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                Gson gson = new Gson();
                tweets.add(gson.fromJson(new InputStreamReader(fis), CounterModel.class).toString());
                tweets.add(gson.fromJson(new InputStreamReader(fis), CounterModel.class).getCount());
                
                /*
                String line = in.readLine();
                while (line != null) {
                        tweets.add(line);
                        line = in.readLine();
                }
                
                */

        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return (List<Object>) tweets;
        //return ;
}




private void saveInFile(String text) {
        try {
                FileOutputStream fos = openFileOutput(FILENAME2,
                                Context.MODE_PRIVATE);

                //fos.write(new String(date.toString() + " | " + text).getBytes());
                fos.write(text.getBytes());
                fos.close();
                //fos.close();
        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
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
