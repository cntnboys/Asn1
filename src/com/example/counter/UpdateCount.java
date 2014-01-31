/*
The UpdateCount class contains methods that are responsible for reseting, updating, deleting, and renaming a counter.
Every time a counter button in clicked to increment counter the specific CounterModel objects count is updated aswell as a date recorded
and the entire List of CounterModel objects is then saved to file. Delete removes the CounterModel Object List loaded from Gson and removes it
from the list and then saves the List to file. Rename finds the correct Object to modify and changes its text, then saves the list of 
objects to file. Reset resets the attributes of the specified object, and then saves the list of objects previously loaded
from file back to file. 

Copyright 2014 Cameron Alexander
<Contact: cpalexan@ualberta.ca>

License GPLv3: GNU GPL Version 3
<http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package com.example.counter;

import java.util.List;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

/**
*The UpdateCount class contains methods that are responsible for reseting, updating, deleting, and renaming a counter.
*Every time a counter button in clicked to increment counter the specific CounterModel objects count is updated aswell as a date recorded
*and the entire List of CounterModel objects is then saved to file. Delete removes the CounterModel Object List loaded from Gson and removes it
*from the list and then saves the List to file. Rename finds the correct Object to modify and changes its text, then saves the list of 
*objects to file. Reset resets the attributes of the specified object, and then saves the list of objects previously loaded
*from file back to file.
*
* @author Cameron Alexander
*
*/

public class UpdateCount {
	
	//method responsible for updating the count of a counter when the clicker is clicked
	//this method updates object being clicked and saves all objects to file
	public void update(int count, Context appcontext, String FILENAME2, List<CounterModel> List2, String passedVar){
			for(int i=0;i<List2.size();i++){
        	
        	CounterModel counter = List2.get(i);
        	String strincheck = counter.getText();
        	
        	if(strincheck.equals(passedVar)){
        	
        		//set count and add date for when counter was clicked
        	    counter.setCount(count);
        	    counter.setDatelist();
			       
			    //put list of objects into Gson and save to file   
			    Gson gson = new Gson();
			    String json = gson.toJson(List2);
			    LoadSave ls = new LoadSave();
			    ls.saveInFile(json,appcontext,FILENAME2);	
			    
        	}
        	
		}
	
	}
	
	//method responsible for removing a Counter, Countermodel Object is removed from list passed in, returning the 
	//updated list
	public void delete(Context appcontext, String FILENAME2, List<CounterModel> List2, String passedVar){
		for(int i=0;i<List2.size();i++){
    	
    	CounterModel cam2 = List2.get(i);
    	String strincheck = cam2.getText();
    	
    	if(strincheck.equals(passedVar)){
    		
    		//remove counter wanted to delete
    		List2.remove(i);
		       
    		//new Gson and save to file new list
		    Gson gson = new Gson();
		    String json = gson.toJson(List2);
		    LoadSave ls = new LoadSave();
		    ls.saveInFile(json,appcontext,FILENAME2);	
		    
    	}
    	
	}
	
	
}
	
	
	//method responsible for renaming a counters name, Countermodel is found in list and text is set,
	//list passed in is then saved to file
	public void rename(String newname, Context appcontext, String FILENAME2, List<CounterModel> List2, String passedVar){
		for(int i=0;i<List2.size();i++){
    	
		//new counter name
		newname = newname.trim();	
    	CounterModel cam2 = List2.get(i);
    	String strincheck = cam2.getText();
    	
    	//sucess message
    	if(strincheck.equals(newname)){
    		Toast.makeText(appcontext.getApplicationContext(), "Counter name already exists",Toast.LENGTH_LONG).show();
    		return;
    	}
    	
    	
    	if(strincheck.equals(passedVar)){
    		
    		//set text new name
    		cam2.setText(newname);
		       
    		//new Gson save new list to file
		    Gson gson = new Gson();
		    String json = gson.toJson(List2);
		    LoadSave ls = new LoadSave();
		    ls.saveInFile(json,appcontext,FILENAME2);	
		    
    	}
    	
	}
	
	
}
	
	//method responsible for reseting a counter, finds CounterModel Object zeroes the count and refreshes the dates to nothing
	//saves updated list of CounterModels back to file
	public void reset(int count, Context appcontext, String FILENAME2, List<CounterModel> List2, String passedVar){
		for(int i=0;i<List2.size();i++){
    	
    	CounterModel counterreset = List2.get(i);
    	String strincheck = counterreset.getText();
    	
    	if(strincheck.equals(passedVar)){
    		
    		//resets data of CounterModel
    		counterreset.setCount(count);
    		counterreset.resetDatelist();
		        
    	    //new Gson, save list of CounterModels back to file
		    Gson gson = new Gson();
		    String json = gson.toJson(List2);
		    LoadSave ls = new LoadSave();
		    ls.saveInFile(json,appcontext,FILENAME2);	
		    
    	}
   
	 }
	
   }
	
}
