package com.example.dbsigurd_mytodolist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends Activity {
	
    private List<ToDoItem> toDos = new ArrayList<ToDoItem>();
 
  

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     
        populateToDoList();
        populateListView();
        
    }


    


	private void populateToDoList() {
		toDos.add(new ToDoItem("get milk",false,true));
		toDos.add(new ToDoItem("Drink the milk", true, false));
		
	}
	
	//use array adapter
	private void populateListView() {
		// TODO Auto-generated method stub
		ArrayAdapter<ToDoItem> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.toDoListView);
		list.setAdapter(adapter);
	}
	private class MyListAdapter extends ArrayAdapter<ToDoItem>{
		public MyListAdapter(){
			super(MainActivity.this, R.layout.item_view, toDos);
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
			ToDoItem currentToDo = toDos.get(position);
			
			//fill list
			CheckBox displayBox = (CheckBox) itemView.findViewById(R.id.toDoCheckBox);
			displayBox.setText(currentToDo.getToDo());
			displayBox.setChecked(currentToDo.isDone());
			//displayBox.setCheckBox(currentToDo.getToDo());
			
	//		return super.getView(position, convertView, parent);
			return itemView;
		}
	}
	public void sendMessage(View view) {
		EditText editText = (EditText) findViewById(R.id.ToDoBox);
		String message = editText.getText().toString();
		if (message.length()!=0){
			
		
			toDos.add(0,new ToDoItem(message, false, false));
			populateListView();
			editText.setText("");
		}
	}
	
}

    
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        return super.onOptionsItemSelected(item);*/
    

