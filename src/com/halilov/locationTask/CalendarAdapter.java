package com.halilov.locationTask;

import java.util.ArrayList;
import java.util.Calendar;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter{
	static final int FIRST_DAY_OF_WEEK = 0; // Sunday=0  Monday=1
	private final String tag = "CalendarAdapter";
	
	private Context mContext;
	
	private java.util.Calendar cal_obj;
	private Calendar selectedDate;
	private ArrayList<String> items;
	
	// construct CalendarAdapter class
	public CalendarAdapter(Context c, Calendar cal){
	Log.i(tag, "inside CalendarAdapter constructor!!");// debugging
	    
		// debugging purpose
		//int year = cal.get(Calendar.YEAR);
		Log.i(tag, "The year is: ");
	
	cal_obj = cal;
	selectedDate = (Calendar)cal.clone();
	mContext = c;
	cal_obj.set(Calendar.DAY_OF_MONTH, 1);
	this.items = new ArrayList<String>();
	Log.i(tag, "still inside CalendarAdapter constructor!!");// debugging
	refreshDays();
	}
	
	public void setItems(ArrayList<String> items) {
    	this.items = items;
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return days.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
    // create a new view for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
    	TextView dayView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.calendar_item, null);
        	}
        dayView = (TextView)v.findViewById(R.id.date);
        
        // disable empty days from the beginning
        if(days[position].equals("")) {
        	dayView.setClickable(false);
        	dayView.setFocusable(false);
        }
        else {
        	// mark current day as focused
        	if(cal_obj.get(Calendar.YEAR)== selectedDate.get(Calendar.YEAR) &&cal_obj.get(Calendar.MONTH)== selectedDate.get(Calendar.MONTH) && days[position].equals(""+selectedDate.get(Calendar.DAY_OF_MONTH))) {
        		v.setBackgroundResource(R.drawable.item_background_focused);
        	}
        	else {
        		v.setBackgroundResource(R.drawable.list_item_background);
        	}
        }
        dayView.setText(days[position]);
        
        // create date string for comparison
        String date = days[position];
    	
        if(date.length()==1) {
    		date = "0"+date;
    	}
    	String monthStr = ""+(cal_obj.get(Calendar.MONTH)+1);
    	if(monthStr.length()==1) {
    		monthStr = "0"+monthStr;
    	}
       
        // show icon if date is not empty and it exists in the items array
        ImageView iw = (ImageView)v.findViewById(R.id.date_icon);
        if(date.length()>0 && items!=null && items.contains(date)) {        	
        	iw.setVisibility(View.VISIBLE);
        }
        else {
        	iw.setVisibility(View.INVISIBLE);
        }
        return v;
    }
	
	public void refreshDays(){
		items.clear();
		
		// gives how many days in an actual date (ex. February = 28)
		int lastDay = cal_obj.getActualMaximum(Calendar.DAY_OF_MONTH);
		// gives the first day of the week (ex. 0 - Sunday, 1 - Monday)
		int firstDay = (int)cal_obj.get(Calendar.DAY_OF_WEEK);
		
		if(firstDay == 1){
			days = new String[lastDay+(FIRST_DAY_OF_WEEK*6)]; 
			}
		else
		{
			days = new String[lastDay+firstDay-(FIRST_DAY_OF_WEEK+1)];
		}
		
		int i = FIRST_DAY_OF_WEEK;
		// create empty days first
		if(firstDay > 1){
			for(i=0;i<firstDay-FIRST_DAY_OF_WEEK;i++){
				days[i]= "";
			}
			i = FIRST_DAY_OF_WEEK*6-1;
			}
		// populate days
		int dayNumber = 1;
		for(int j=i-1;j<days.length;j++){
			days[j]=""+dayNumber;
			dayNumber++;
		}
	}
	
		String [] days;

}
