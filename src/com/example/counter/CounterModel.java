/*
The CounterModel class is responsible for creating objects that store counter information
These objects can be created, stored, and modified to store individual counters.

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
import java.util.Date;
import java.util.List;

/**
*The CounterModel class is responsible for creating objects that store counter information
*These objects can be created, stored, and modified to store individual counters.
*
* @author Cameron Alexander
*
*/

public class CounterModel {
	
	//main attributes of a Counter text,count,list of dates
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
	
	public void resetDatelist(){
		datelist =  new ArrayList<Date>();
		
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
