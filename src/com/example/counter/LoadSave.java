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





public class LoadSave {
	
public List<CounterModel> loadFromFile(String FILENAME2, List<CounterModel> objList,Context appcontext) {
		
        try {
                FileInputStream fis = appcontext.openFileInput(FILENAME2);
                //BufferedReader in = new BufferedReader(new InputStreamReader(fis));
             
                
               
               // Gson gson = new Gson();
              //  tweets.add(gson.fromJson(new InputStreamReader(fis), CounterModel.class).getText());
                //Gson gson1 = new Gson();
                //tweets.add(gson.fromJson(new InputStreamReader(fis), CounterModel.class));
                
                //http://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(new InputStreamReader(fis)).getAsJsonArray();
                


                for(JsonElement obj : jArray )
                {
                    CounterModel cam = gson.fromJson( obj , CounterModel.class);
                    objList.add(cam);
                }
                
               // List<CounterModel> inpList = new Gson().fromJson(new InputStreamReader(fis), type);
                //System.out.println("here2"+inpList);
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
        //return (List<Object>) tweets;
        return objList;
}




public void saveInFile(String text, Context appcontext,String FILENAME2) {
        try {
                FileOutputStream fos = appcontext.openFileOutput(FILENAME2,
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
	

}
