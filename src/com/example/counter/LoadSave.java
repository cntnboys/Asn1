/*
The LoadSave class is responsible for loading and saving a list of CounterModel objects 
into a file using Gson. A list of CounterModel objects can be saved into a file, and retrieved from a file.
This class contains methods that are called within the Counter Application when any data needs to be retrieved or stored.
The majority of this code was taken from the LonelyTwitter example from the 301 class.

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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
*The LoadSave class is responsible for loading and saving a list of CounterModel objects 
*into a file using Gson. A list of CounterModel objects can be saved into a file, and retrieved from a file.
*This class contains methods that are called within the Counter Application when any data needs to be retrieved or stored.
*
* @author Cameron Alexander
*
*/

public class LoadSave {

//Loads CounterModel objects from a file, using Gson, returns a list with CounterModel Objects
//this method requires a Filename, a List of CounterModel Objects and the context of the activity calling this method
// This code was taken from Lonely Twitter from the 301 class and modified accordingly 
public List<CounterModel> loadFromFile(String FILENAME2, List<CounterModel> objList,Context appcontext) {
		
        try {
                FileInputStream fis = appcontext.openFileInput(FILENAME2);
               
                //http://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(new InputStreamReader(fis)).getAsJsonArray();
                
                for(JsonElement obj : jArray )
                {
                    CounterModel cam = gson.fromJson( obj , CounterModel.class);
                    objList.add(cam);
                }
                
        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        
        return objList;
}



// Responsible for saving a Gson , containing list of CounterModel Objects to file
// this method requires , a List of CounterModel Objects and the context of the activity calling this method
// This code was taken from the Lonely Twitter from the 301 class
public void saveInFile(String text, Context appcontext,String FILENAME2) {
        try {
                FileOutputStream fos = appcontext.openFileOutput(FILENAME2,
                                Context.MODE_PRIVATE);
                
                fos.write(text.getBytes());
                fos.close();
              
        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
}
	

}
