package com.halilov.locationTask;

import java.sql.Date;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Task extends Activity {
    /** Called when the activity is first created. */
	private static final String tag = "Task";
	Button addTask, tasks, tasksRecord;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addTask = (Button)findViewById(R.id.add_task);
        tasks = (Button)findViewById(R.id.tasks);
        tasksRecord = (Button) findViewById(R.id.tasks_record);
        
        addTask.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				int day = c.get(Calendar.DAY_OF_MONTH);
				String year_s = Integer.toString(year);
				String month_s = Integer.toString(month);
				String day_s = Integer.toString(day);
				Log.i(tag, year_s+"-"+month_s+"-"+day_s);
				
			// passing date parameters to time_date class 
			Intent i = new Intent(Task.this, time_date.class);
			i.putExtra("year", year_s);
			i.putExtra("month", month_s);
			i.putExtra("day", day_s);
			startActivity(i);
			}
		});
        
        tasksRecord.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent i = new Intent (Task.this, task_records.class);
			startActivity(i);
			}
		});
        
    }
}