package com.halilov.locationTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class note_loc extends Activity {
	Button done;
	//EditText note, loc;
	TextView date, time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_loc);
		
		done = (Button)findViewById(R.id.done);
		date = (TextView) findViewById(R.id.test_date);
		time = (TextView) findViewById(R.id.test_time);
		
		date.setText(getIntent().getExtras().getString("date"));
		time.setText(getIntent().getExtras().getString("time"));
		
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent i = new Intent(note_loc.this, Task.class);
			startActivity(i);
			}
		});
	}
	
}
