package com.example.dbsigurd_mytodolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

public class ViewArchive extends Activity {
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_archive);
		populateListView();
	}
	public void populateListView() {
		// TODO Auto-generated method stub
		ArrayAdapter<ToDoItem> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.toDoListView);
		list.setAdapter(adapter);
	}
	public class MyListAdapter extends ArrayAdapter<ToDoItem>{
		public MyListAdapter(){
			
			super(ViewArchive.this, R.layout.item_view, archivedToDos);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// if null view
			View itemView = convertView;
			if (itemView==null){
				itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
			}
			//Find the Todo
			final ToDoItem currentToDo = toDos.get(position);
			
			//fill list
			CheckBox displayBox = (CheckBox) itemView.findViewById(R.id.toDoCheckBox);
			displayBox.setText(currentToDo.getToDo());
			displayBox.setChecked(currentToDo.isDone());
			Button optButton = (Button) itemView.findViewById(R.id.optionsButton);
			optButton.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View view){
					toEdit = currentToDo;
					openOptions(currentToDo);
					
					//Toast.makeText(MainActivity.this,currentToDo.getToDo(),Toast.LENGTH_LONG).show();
				}

				
			});
				
			
		
			//displayBox.setCheckBox(currentToDo.getToDo());
			return itemView;
	//		return super.getView(position, convertView, parent);
		}
	}*/
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
