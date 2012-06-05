package com.halilov.locationTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class task_records extends Activity{
	TextView note, loc;
	Button done;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_records);
		
		Bundle bundle = this.getIntent().getExtras();
		CharSequence note_value = bundle.getCharSequence("note");
		CharSequence loc_value = bundle.getCharSequence("loc");
		
		note = (TextView) findViewById(R.id.note_print);
		loc = (TextView) findViewById(R.id.loc_print);
		done = (Button) findViewById(R.id.cool);
		
		note.setText("Task: "+note);
		loc.setText("Loc: "+loc_value);
		
		done.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent (task_records.this, Task.class);
				startActivity(i);
			}
		});
	}
}
