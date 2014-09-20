package com.example.dbsigurd_mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

public class ViewArchive extends Activity {
	public static final int RESULT_CODE_OPTIONS = 1;
	public static final String EXTRA_ANSWER = "Choice";
	public final static String EXTRA_MESSAGE = "com.example.dbsigurd_mytodolist";
	ToDoList toDos = ToDoList.getInstance();
	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	public ToDoItem currentToDo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_archive);
		populateListView();
	}
	public void populateListView(){
		ArrayAdapter<ToDoItem> adapter = new MyListAdapter();
		//ArratAdapter<ToDoItem> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.archivedListView);
		list.setAdapter(adapter);
	}
	private class MyListAdapter extends ArrayAdapter<ToDoItem>{
		public MyListAdapter(){
			super(ViewArchive.this, R.layout.item_view,archivedToDos.getToDoList());
		}
		public View getView( int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// if null view
			View itemView = convertView;
			if (itemView==null){
				itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
			}
			ToDoItem currentToDo = archivedToDos.getToDoItem(position);
			//final ToDoItem toEdit = currentToDo;
			final int toEditPosition = position;
			CheckBox displayBox = (CheckBox) itemView.findViewById(R.id.toDoCheckBox);
			displayBox.setText(currentToDo.getToDo());
			displayBox.setChecked(currentToDo.isDone());
			//CheckBox chxBox = (CheckBox) itemView.findViewById(R.id.toDoCheckBox);
			displayBox.setOnClickListener(new OnClickListener(){
				public void onClick(View view){
					archivedToDos.getToDoItem(toEditPosition).isDoneFlip();
					populateListView();
				}
			});
			Button optButton = (Button) itemView.findViewById(R.id.optionsButton);
			optButton.setOnClickListener(new OnClickListener(){
				
				//fill list
				
				@Override
				public void onClick(View view){
					
					
					openArchivedOptions(toEditPosition);

					//Toast.makeText(MainActivity.this,currentToDo.getToDo(),Toast.LENGTH_LONG).show();
				}


			});
			return itemView;
		}
	}
			//Find the Todo
	public void openArchivedOptions(int toEditPosition) {
			
		Intent intent = new Intent(this,Archived_Options.class);
		
		// from here ill return an int and edit accordingly
		intent.putExtra(EXTRA_MESSAGE, toEditPosition);
		startActivityForResult(intent,RESULT_CODE_OPTIONS);
		populateListView();
		//Toast.makeText(MainActivity.this,""+choiceResult,Toast.LENGTH_LONG).show();
		
		return;
	}		
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode== Activity.RESULT_CANCELED){
			
			populateListView();
			return;
		}
		
			//Toast.makeText(MainActivity.this,"" + answer,Toast.LENGTH_LONG).show();
			//String toDoCopied = currentToDo.getToDo();
			//Boolean toDoDone = currentToDo.isDone();
			//Toast.makeText(MainActivity.this,currentToDo.getToDo(),Toast.LENGTH_LONG).show();
			//toDos.add(0, new ToDoItem(toDoCopied,toDoDone));
				
		populateListView();
				
	}
	public void goBack(View view){
		Intent returnIntent = new Intent(this,MainActivity.class);
		startActivity(returnIntent);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_archive, menu);
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
