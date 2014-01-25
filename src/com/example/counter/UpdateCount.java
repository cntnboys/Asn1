package com.example.counter;

import java.util.List;

import com.google.gson.Gson;

import android.content.Context;
import android.text.Editable;

public class UpdateCount {
	
	
	
	
	public void update(int count, Context appcontext, String FILENAME2, List<CounterModel> List2, String passedVar){
			for(int i=0;i<List2.size();i++){
        	
        	CounterModel cam2 = List2.get(i);
        	String strincheck = cam2.getText();
        	
        	System.out.println("printhere"+strincheck);
        	if(strincheck.equals(passedVar)){
        		
        		//System.out.println("printhere2"+count);
        	    cam2.setCount(count);
        	    cam2.setDatelist();
			        
			    //System.out.println("here"+objList);
			       
			    Gson gson = new Gson();
			    String json = gson.toJson(List2);
			        
			    
			    LoadSave ls = new LoadSave();
			    ls.saveInFile(json,appcontext,FILENAME2);	
			    
        	}
        	
			}
		
		
	}
	
	public void delete(Context appcontext, String FILENAME2, List<CounterModel> List2, String passedVar){
		for(int i=0;i<List2.size();i++){
    	
    	CounterModel cam2 = List2.get(i);
    	String strincheck = cam2.getText();
    	
    	System.out.println("printhere"+strincheck);
    	if(strincheck.equals(passedVar)){
    		
    		List2.remove(i);
		       
		    Gson gson = new Gson();
		    String json = gson.toJson(List2);
		        
		    
		    LoadSave ls = new LoadSave();
		    ls.saveInFile(json,appcontext,FILENAME2);	
		    
    	}
    	
	}
	
	
}
	
	
	public void rename(String newname, Context appcontext, String FILENAME2, List<CounterModel> List2, String passedVar){
		for(int i=0;i<List2.size();i++){
    	
    	CounterModel cam2 = List2.get(i);
    	String strincheck = cam2.getText();
    	
    	System.out.println("printhere"+strincheck);
    	if(strincheck.equals(passedVar)){
   
    		cam2.setText(newname);
		       
		    Gson gson = new Gson();
		    String json = gson.toJson(List2);
		        
		    
		    LoadSave ls = new LoadSave();
		    ls.saveInFile(json,appcontext,FILENAME2);	
		    
    	}
    	
	}
	
	
}
	


}
