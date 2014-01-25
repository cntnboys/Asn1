package com.example.counter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;

public class DateParse {
	
	
	protected List<Date> datelist33 = new ArrayList<Date>();
	protected Date datecur=null;
	String strdate=null;
	String last=null;
	List<String> list22 = new ArrayList<String>();
	List<String> listyearfinal = new ArrayList<String>();
	
	public List<String> year(Context appcontext, List<Date> datelist2){
		
		
		
		
		datelist33 = datelist2;
		for(int i=0;i<datelist33.size();i++){
			
			datecur = datelist33.get(i);
			
			
			strdate = datecur.toString();
			
			String[] parts = strdate.split(" ");
			String lastWord = parts[parts.length - 1];
			list22.add(lastWord);
		
			
		}
	
		//http://stackoverflow.com/questions/5211194/count-occurences-of-words-in-arraylist
		Set<String> unique = new HashSet<String>(list22);
		for (String key : unique){
			listyearfinal.add(key +": " + Collections.frequency(list22, key));
			System.out.println("year"+listyearfinal);
		}
		
		return listyearfinal;
	
	}
	
public List<String> month(Context appcontext, List<Date> datelist2){
		
		
		
		
		datelist33 = datelist2;
		for(int i=0;i<datelist33.size();i++){
			
			datecur = datelist33.get(i);
			
			
			strdate = datecur.toString();
			
			String[] parts = strdate.split(" ");
			//month
			String lastWord = parts[parts.length - 5];
			//year
			String lastWord2 = parts[parts.length - 1];
			
			String FinalWord = (lastWord+" "+lastWord2);
			list22.add(FinalWord);
		
			
		}
	
		
		Set<String> unique = new HashSet<String>(list22);
		for (String key : unique){
			listyearfinal.add(key +": " + Collections.frequency(list22, key));
			System.out.println("month"+listyearfinal);
		}
		
		return listyearfinal;
	
	}


public List<String> week(Context appcontext, List<Date> datelist2){
	
	
	
	
	datelist33 = datelist2;
	String week = null;
	for(int i=0;i<datelist33.size();i++){
		
		datecur = datelist33.get(i);
		
		strdate = datecur.toString();
		
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
	
	
	Set<String> unique = new HashSet<String>(list22);
	for (String key : unique){
		listyearfinal.add(key +": " + Collections.frequency(list22, key));
		System.out.println("week"+listyearfinal);
	}
	
	return listyearfinal;
	
}


public List<String> day(Context appcontext, List<Date> datelist2){
	
	
	
	
	datelist33 = datelist2;
	for(int i=0;i<datelist33.size();i++){
		
		datecur = datelist33.get(i);
		
		
		strdate = datecur.toString();
		
		String[] parts = strdate.split(" ");
		//month
		String lastWord = parts[parts.length - 5];
		//day
		String lastWord2 = parts[parts.length - 4];
		
		String FinalWord = (lastWord+" "+lastWord2);
		list22.add(FinalWord);
	
		
	}

	
	Set<String> unique = new HashSet<String>(list22);
	for (String key : unique){
		listyearfinal.add(key +": " + Collections.frequency(list22, key));
		System.out.println("day"+listyearfinal);
	}
	
	return listyearfinal;

}

public List<String> hour(Context appcontext, List<Date> datelist2){
	
	
	
	
	datelist33 = datelist2;
	for(int i=0;i<datelist33.size();i++){
		
		datecur = datelist33.get(i);
		
		
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
		//System.out.println(cuthour);
		
		String FinalWord = (lastWord+" "+lastWord2+" "+cuthour+"hr");
		list22.add(FinalWord);
	
		
	}

	
	Set<String> unique = new HashSet<String>(list22);
	for (String key : unique){
		listyearfinal.add(key +": " + Collections.frequency(list22, key));
	}
	System.out.println("hour"+listyearfinal);
	return listyearfinal;

}

}

