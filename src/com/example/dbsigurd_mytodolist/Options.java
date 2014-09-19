package com.example.dbsigurd_mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Options extends MainActivity {
	public static final String EXTRA_CHOICE = "the Choice";
	public String message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		   // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	    TextView toDoChosen = (TextView) findViewById(R.id.clicked);
	    toDoChosen.setText(message);
	    
	    setupCopy();
	    setupCancel();
	    setupDelete();
	    setupArchive();
	    setupEmail();
	}
	
	private void setupEmail() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Button btn = (Button) findViewById(R.id.EmailButton);
		btn.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				int choiceClicked = 4;
				Intent intent = new Intent();
				intent.putExtra(EXTRA_CHOICE, choiceClicked);
				setResult(Activity.RESULT_OK,intent);
				finish();
						// TODO Auto-generated method stub
						
			}
		});
	}

	private void setupArchive() {
		// TODO Auto-generated method stub
		Button btn = (Button) findViewById(R.id.ArchiveButton);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int choiceClicked = 3;
				Intent intent = new Intent();
				intent.putExtra(EXTRA_CHOICE, choiceClicked);
				setResult(Activity.RESULT_OK,intent);
				finish();
				// TODO Auto-generated method stub
				
			}
		});
	}

	public void setupDelete() {
		// TODO Auto-generated method stub
		Button btn = (Button) findViewById(R.id.DeleteButton);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int choiceClicked = 2;
				Intent intent = new Intent();
				intent.putExtra(EXTRA_CHOICE, choiceClicked);
				setResult(Activity.RESULT_OK,intent);
				finish();
				// TODO Auto-generated method stub
				
			}
		});
	}


	public void setupCopy(){
		Button btn = (Button) findViewById(R.id.CopyButton);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int choiceClicked = 1;
				Intent intent = new Intent();
				intent.putExtra(EXTRA_CHOICE, choiceClicked);
				setResult(Activity.RESULT_OK,intent);
				finish();
				// TODO Auto-generated method stub
				
			}
		});
			
		}
	private void setupCancel() {
		Button btn = (Button) findViewById(R.id.CancelButton);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(Activity.RESULT_CANCELED,intent);
				finish();
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
