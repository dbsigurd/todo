package com.example.dbsigurd_mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Options extends MainActivity {
	public static final String EXTRA_CHOICE = "the Choice";
	public int positionChosen;
	ToDoList toDos = ToDoList.getInstance();
	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	ToDoItem toEdit;
	public String emailAddress = "Void";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		   // Get the message from the intent
	    Intent intent = getIntent();
	    int positionChosen = intent.getIntExtra(MainActivity.EXTRA_MESSAGE,0);
	    toEdit = toDos.getToDoItem(positionChosen);
	    TextView toDoChosen = (TextView) findViewById(R.id.clicked);
	    toDoChosen.setText(toDos.getToDoItem(positionChosen).getToDo());
	    
	    
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
				if(emailAddress == "Void"){
					Toast.makeText(Options.this, "Please enter an email address!", Toast.LENGTH_LONG).show();
				}else{
					Intent i = new Intent(Intent.ACTION_SEND);
					i.setType("message/rfc822");
					i.putExtra(Intent.EXTRA_EMAIL  , new String[]{emailAddress});
					i.putExtra(Intent.EXTRA_SUBJECT, "To Dos!");
					i.putExtra(Intent.EXTRA_TEXT   , createBody());
					try{
						startActivity(Intent.createChooser(i, "Send mail..."));
					} catch (android.content.ActivityNotFoundException ex) {
						Toast.makeText(Options.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
					}
					int choiceClicked = 4;
					Intent intent = new Intent();
					intent.putExtra(EXTRA_CHOICE, choiceClicked);
					setResult(Activity.RESULT_OK,intent);
					finish();
						// TODO Auto-generated method stub
				}
			}
		});
	}
	
	private String createBody(){
		boolean isDone = toEdit.isDone();
		String body;
		if (isDone){
			body = "you have " + toEdit.getToDo() +" which is done!" ;
		}else{
			body = "you have " + toEdit.getToDo() +" which is not done!" ;
		}
		return body;
		
	}
	
	private void setupArchive() {
		// TODO Auto-generated method stub
		Button btn = (Button) findViewById(R.id.ArchiveButton);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int choiceClicked = 3;
				Intent intent = new Intent();
				String pop = toEdit.getToDo();
				Toast.makeText(Options.this,pop+" has been archived",Toast.LENGTH_LONG).show();
				//add to Archived list
				archivedToDos.add(toEdit);
				toDos.remove(toEdit);
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
				toDos.remove(toEdit);
				intent.putExtra(EXTRA_CHOICE, choiceClicked);
				setResult(Activity.RESULT_OK,intent);
				finish();
				// TODO Auto-generated method stub
				
			}
		});
	}


	public void Copy(View view){	
		int choiceClicked = 1;
		Intent intent = new Intent();
		
		toDos.add(toEdit);
		intent.putExtra(EXTRA_CHOICE, choiceClicked);
		setResult(Activity.RESULT_OK,intent);
		finish();
				// TODO Auto-generated method stub		
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
	
	public void addEmail(View view) {
		EditText editText = (EditText) findViewById(R.id.EmailEditText);
		String emailText = editText.getText().toString();
		if (emailText.length()!=0){
			emailAddress = emailText;
			editText.setText("");
		}
		
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
