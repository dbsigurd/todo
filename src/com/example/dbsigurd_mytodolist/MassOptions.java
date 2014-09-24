package com.example.dbsigurd_mytodolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MassOptions extends MainActivity {
	ToDoList toDos = ToDoList.getInstance();
	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	int toDosTotal = toDos.size(); //unarchived total
	int totalArch = archivedToDos.size();
	int toDosChecked =0;
	int archvChecked = 0;
	int toDosUnchecked;
	int archUnchecked ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mass_options);
		TextView statsText = (TextView) findViewById(R.id.stats1);
		TextView statsText2 = (TextView) findViewById(R.id.stats2);
		
		calculateCounts();
		
	    statsText.setText("You have " +toDosTotal +" unArchived to-dos, " + toDosChecked + " checked and " + toDosUnchecked + " unchecked." );
		statsText2.setText("You have " +totalArch +" Archived to-dos, " + archvChecked + " checked and " + archUnchecked + " unchecked." );
	}
	
	public void calculateCounts(){
		toDosTotal = toDos.size(); //unarchived total
		totalArch = archivedToDos.size();
		toDosChecked =0;
		archvChecked = 0;
		for (int i =0; i<toDosTotal; i++){
			boolean checked = toDos.getToDoItem(i).isDone();
			if (checked){
				toDosChecked ++;
			}
		}
		for (int i =0; i<totalArch; i++){
			boolean checked = archivedToDos.getToDoItem(i).isDone();
			if (checked){
				archvChecked ++;
			}
		}
		toDosUnchecked = toDosTotal-toDosChecked;
		archUnchecked = totalArch-archvChecked;
	}
	public void deleteAll(View view){
		toDos.deleteAll();
		archivedToDos.deleteAll();
		TextView statsText = (TextView) findViewById(R.id.stats1);
		TextView statsText2 = (TextView) findViewById(R.id.stats2);
		statsText.setText("");
		statsText2.setText("There are no to-dos! =)");
		//delete all archived 
	}
	
	public void archiveAll( View view){
		int size = toDos.size();
		for (int i = size - 1; i>=0;i--){
			ToDoItem current  = toDos.getToDoItem(i);
			toDos.remove(current);
			archivedToDos.add(current);
		calculateCounts();
		TextView statsText = (TextView) findViewById(R.id.stats1);
		TextView statsText2 = (TextView) findViewById(R.id.stats2);
		statsText.setText("You have " +0 +" unArchived to-dos, " + 0 + " checked and " + 0 + " unchecked." );
		statsText2.setText("You have " +totalArch +" Archived to-dos, " + archvChecked + " checked and " + archUnchecked + " unchecked." );
			
		}
		
		Toast.makeText(MassOptions.this,"All to-dos have been moved to archives",Toast.LENGTH_LONG).show();
	}
	public void returnToMain (View v){
		Intent returnIntent = new Intent(this,MainActivity.class);
		startActivity(returnIntent);
	}
	public void emailAll(View view){
		
	}
	
	public void selectToDo(View view){
		//Intent select_intent
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mass_options, menu);
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
