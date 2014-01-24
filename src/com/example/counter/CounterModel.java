package com.example.counter;

public class CounterModel {
	
	protected String text;
	protected int count;
	
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
