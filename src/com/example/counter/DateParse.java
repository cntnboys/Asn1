/*
The DateParse class contains methods that are responsible for processing a list of dates
in different ways. The methods can process a list of dates by year,month,week,day,hr,min. 
The methods return a list that is parsed and formated according to their method name back to
the Summary Activity to be displayed. This is where the summary statistics are calculated. 

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;

/**
*The DateParse class contains methods that are responsible for processing a list of dates
*in different ways. The methods can process a list of dates by year,month,week,day,hr,min. 
*The methods return a list that is parsed and formated according to their method name back to
*the Summary Activity to be displayed. This is where the summary statistics are calculated. 
*
* @author Cameron Alexander
*
*/

public class DateParse {
	
	//Initialise variables needed
	protected List<Date> datelist33 = new ArrayList<Date>();
	protected Date datecur=null;
	String strdate=null;
	String last=null;
	List<String> list22 = new ArrayList<String>();
	List<String> listyearfinal = new ArrayList<String>();
	
	
	//method that is responsible for processing a list of dates by year 
	//find the year and then counts the number of time that year exists
	//in list returns year with a count next to it to display the number
	//of clicks that happened per year back to summary activity to be displayed
	public List<String> year(Context appcontext, List<Date> datelist2){
		
		datelist33 = datelist2;
		for(int i=0;i<datelist33.size();i++){
			
			datecur = datelist33.get(i);
		
			//date to string
			strdate = datecur.toString();
			
			//parse year
			String[] parts = strdate.split(" ");
			String lastWord = parts[parts.length - 1];
			list22.add(lastWord);
			
		}
		
		//creates a hash set using a list and counts the frequency of an item within the list
		//http://stackoverflow.com/questions/5211194/count-occurences-of-words-in-arraylist
		Set<String> unique = new HashSet<String>(list22);
		for (String key : unique){
			listyearfinal.add(key +": " + Collections.frequency(list22, key));
		}
		
		return listyearfinal;
	}
	
	
	//method responsible for for processing a list of dates by month
	//find the month and counts the number of occurrences in list
	//returns a list with counts of click in each month
	public List<String> month(Context appcontext, List<Date> datelist2){
		
		
		
		
		datelist33 = datelist2;
		for(int i=0;i<datelist33.size();i++){
			
			datecur = datelist33.get(i);
			
			//date to string
			strdate = datecur.toString();
			
			//parse date
			String[] parts = strdate.split(" ");
			
			//month
			String lastWord = parts[parts.length - 5];
			//year
			String lastWord2 = parts[parts.length - 1];
			
			//month and year combine
			String FinalWord = (lastWord+" "+lastWord2);
			list22.add(FinalWord);
		
			
		}
	
		//creates a hash set using a list and counts the frequency of an item within the list
		//http://stackoverflow.com/questions/5211194/count-occurences-of-words-in-arraylist
		Set<String> unique = new HashSet<String>(list22);
		for (String key : unique){
			listyearfinal.add(key +": " + Collections.frequency(list22, key));
			System.out.println("month"+listyearfinal);
		}
		
		return listyearfinal;
	
	}

	
	//method responsible for for processing a list of dates by week
	//finds the week number of the month and counts the number of occurrences in list
	//returns a list with counts of click in each week of each months present
	public List<String> week(Context appcontext, List<Date> datelist2){

		datelist33 = datelist2;
		String week = null;
		for(int i=0;i<datelist33.size();i++){
		
			datecur = datelist33.get(i);
			
			//date to string
			strdate = datecur.toString();
			
			//break up string
			String[] parts = strdate.split(" ");
			
			//day
			String lastWord = parts[parts.length - 4];
			
			//month
			String lastWord2 = parts[parts.length - 5];
		
			//find week number 
			int number = new Integer(lastWord);
		
			if(number<=7){
				week = "1";
			}
			if(number<=14 && 8<=number){
				week = "2";
			}
			if(15<=number && number<=21){
				week = "3";
			}
			if (22<=number && number<=28){
				week = "4";
			}
			
			if (29<=number){
				week = "5";
			}
		
			//compile information
			String FinalWord = (lastWord2+" "+"week "+week);
			list22.add(FinalWord);
		
	}
		
		//count occurrences
		Set<String> unique = new HashSet<String>(list22);
		for (String key : unique){
			listyearfinal.add(key +": " + Collections.frequency(list22, key));
	}
	
	return listyearfinal;
	
}

	//method responsible for for processing a list of dates by day
	//finds the day number of the month and counts the number of occurrences in list
	//returns a list with counts of click in each day of each months present
	public List<String> day(Context appcontext, List<Date> datelist2){
		
		datelist33 = datelist2;
		for(int i=0;i<datelist33.size();i++){
		
			datecur = datelist33.get(i);
		
			//date to string
			strdate = datecur.toString();
		
			//parse date
			String[] parts = strdate.split(" ");
			
			//month
			String lastWord = parts[parts.length - 5];
			//day
			String lastWord2 = parts[parts.length - 4];
		
			String FinalWord = (lastWord+" "+lastWord2);
			list22.add(FinalWord);
	
		
		}

			//count occurrences
			Set<String> unique = new HashSet<String>(list22);
			for (String key : unique){
				listyearfinal.add(key +": " + Collections.frequency(list22, key));
			}
	
			return listyearfinal;
	}

	
	//method responsible for for processing a list of dates by hour
	//finds the hour number of the month of each day and counts the number of occurrences in list
	//returns a list with counts of click in each day of each month of each hour
	public List<String> hour(Context appcontext, List<Date> datelist2){
	
		datelist33 = datelist2;
		for(int i=0;i<datelist33.size();i++){
		
			datecur = datelist33.get(i);
		
			//date to string
			strdate = datecur.toString();
		
			String[] parts = strdate.split(" ");
			
			//month
			String lastWord = parts[parts.length - 5];
			
			//day
			String lastWord2 = parts[parts.length - 4];
			
			//time full
			String lastWord3 = parts[parts.length - 3];
			
			//time hour
			//stackoverflow.com/questions/1583940/up-to-first-n-characters
			String cuthour = lastWord3.substring(0, Math.min(lastWord3.length(), 2));
			
		
			String FinalWord = (lastWord+" "+lastWord2+" "+cuthour+"hr");
			list22.add(FinalWord);
	
		
		}

		//count occurrences
		Set<String> unique = new HashSet<String>(list22);
		for (String key : unique){
			listyearfinal.add(key +": " + Collections.frequency(list22, key));
		}
		
		return listyearfinal;

	}


	//method responsible for for processing a list of dates by minute
	//finds the minute number of each month of each day and counts the number of occurrences in list
	//returns a list with counts of click in each minute of each hour of each day of each month 
	public List<String> minute(Context appcontext, List<Date> datelist2){
	
		datelist33 = datelist2;
		for(int i=0;i<datelist33.size();i++){
		
			datecur = datelist33.get(i);
	
			//date to string
			strdate = datecur.toString();
			
			//parse date
			String[] parts = strdate.split(" ");
		
			//month
			String lastWord = parts[parts.length - 5];
		
			//day
			String lastWord2 = parts[parts.length - 4];
		
			//time full
			String lastWord3 = parts[parts.length - 3];
		
			//time hour
			//stackoverflow.com/questions/1583940/up-to-first-n-characters
			String cuthour = lastWord3.substring(0, Math.min(lastWord3.length(), 2));
		
			//time minute
			String minute =  lastWord3.substring(0, Math.min(lastWord3.length(), 5));
			minute = minute.substring(Math.max(minute.length()- 2,0));
		

			//compile data
			String FinalWord = (lastWord+" "+lastWord2+" "+cuthour+"hr"+" "+minute+"min");
			list22.add(FinalWord);
	
		
	}

		//count occurrences
		Set<String> unique = new HashSet<String>(list22);
		for (String key : unique){
			listyearfinal.add(key +": " + Collections.frequency(list22, key));
		}
		
		return listyearfinal;

	}

}

