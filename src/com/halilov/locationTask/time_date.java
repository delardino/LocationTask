package com.halilov.locationTask;

import java.util.*;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class time_date extends Activity{
	
	private static final String tag = "time_date";
	
	public Calendar cal_obj;
	public CalendarAdapter adapter;
	public Handler handler;
	public ArrayList<String> items; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_date);
		
		cal_obj = Calendar.getInstance();
		newIntent(getIntent());
		
		items = new ArrayList<String>();		
		//Log.i(tag, "Process is in time_date class!!!");// debugging
		adapter = new CalendarAdapter(this, cal_obj);
		
		GridView gView = (GridView) findViewById(R.id.gridview);
		gView.setAdapter(adapter);
		
		handler = new Handler();
		handler.post(calendarUpdater);
		
		//assigning all components
		
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_obj));
		
		TextView previous = (TextView) findViewById(R.id.previous);
		   previous.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(cal_obj.get(Calendar.MONTH)== cal_obj.getActualMinimum(Calendar.MONTH)) {				
						cal_obj.set((cal_obj.get(Calendar.YEAR)-1),cal_obj.getActualMaximum(Calendar.MONTH),1);
					} else {
						cal_obj.set(Calendar.MONTH,cal_obj.get(Calendar.MONTH)-1);
					}
					refreshCalendar();
				}
			});
		 
		TextView next = (TextView) findViewById(R.id.next);
next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(cal_obj.get(Calendar.MONTH)== cal_obj.getActualMaximum(Calendar.MONTH)) {				
					cal_obj.set((cal_obj.get(Calendar.YEAR)+1),cal_obj.getActualMinimum(Calendar.MONTH),1);
				} else {
					cal_obj.set(Calendar.MONTH,cal_obj.get(Calendar.MONTH)+1);
				}
				refreshCalendar();
				
			}
		});

gView.setOnItemClickListener(new OnItemClickListener() {
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    	TextView date = (TextView)v.findViewById(R.id.date);
        if(date instanceof TextView && !date.getText().equals("")) {
        	
        	Intent intent = new Intent();
        	String day = date.getText().toString();
        	if(day.length()==1) {
        		day = "0"+day;
        	}
        	// return chosen date as string format 
        	intent.putExtra("date", android.text.format.DateFormat.format("yyyy-MM", cal_obj)+"-"+day);
        	setResult(RESULT_OK, intent);
        	finish();
        }
        
    }
});
				
	}
	public void newIntent(Intent intent){
		String year_cal = getIntent().getExtras().get("year").toString();
		String month_cal = getIntent().getExtras().get("month").toString();
		String day_cal = getIntent().getExtras().get("day").toString();
		
		Log.i(tag, "In time_date.java.."+year_cal+"-"+month_cal+"-"+day_cal);
		// assigning present date to Calendar object
		cal_obj.set(Integer.parseInt(year_cal), Integer.parseInt(month_cal), Integer.parseInt(day_cal));
	}
	
	public void refreshCalendar()
	{
		TextView title  = (TextView) findViewById(R.id.title);
		
		adapter.refreshDays();
		adapter.notifyDataSetChanged();				
		handler.post(calendarUpdater); // generate some random calendar items				
		
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_obj));
	}
	public Runnable calendarUpdater = new Runnable() {
		
		@Override
		public void run() {
			items.clear();
			// format random values. You can implement a dedicated class to provide real values
			for(int i=0;i<31;i++) {
				Random r = new Random();
				
				if(r.nextInt(10)>6)
				{
					items.add(Integer.toString(i));
				}
			}

			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};
	
}
