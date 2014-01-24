package com.example.counter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CounterModel {
	
	protected String text;
	protected int count;
	protected Date timestamp;
	protected List<Date> datelist = new ArrayList<Date>();
	
	

	public List<Date> getDatelist() {
		return datelist;
	}

	public void setDatelist() {
		timestamp = new Date(System.currentTimeMillis());
		datelist.add(timestamp);
	}

	public CounterModel(String text) {
       setText(text);
        
    }

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	
	public String toString(){
        return getText();
    }
	

}
