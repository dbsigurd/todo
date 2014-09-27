package com.example.dbsigurd_mytodolist;

import java.util.ArrayList;

import android.R.color;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectToDos extends Activity
{

	ToDoList toDos = ToDoList.getInstance();
	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	public ToDoItem currentToDo;
	public int choiceResult;
	public int disp = 0;
	public ArrayList<String> toShow = new ArrayList<String>();

	
	//public ArrayList<int> clicked= new ArrayList<int>();
	public ArrayList<Integer> clicked= new ArrayList<Integer>();
	
	public void populateListView()
	{

		// populateToDoList();
		// TODO Auto-generated method stub
		final ListView toShowList = (ListView) findViewById(R.id.selectListView);
		
		//ArrayAdapter<String> adapter = 
				//new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,toShow);
		ArrayAdapter<String> adapter = 
				new MyArrayAdapter();
		toShowList.setAdapter(adapter);
		
		// ArratAdapter<ToDoItem> adapter = new MyListAdapter();
		toShowList.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> arg0,View v, int position, long arg3)
			{	
				if (clicked.contains(position)){
					toShowList.setBackgroundColor(color.background_light);
					clicked.remove(position);
				}else{
					toShowList.setBackgroundColor(color.background_dark);
					
					clicked.add(position);
				}
			
				populateListView();
			}
		});
		
	}
	
	private class MyArrayAdapter extends ArrayAdapter<String>{
		public MyArrayAdapter(){
			super(SelectToDos.this,android.R.layout.simple_list_item_1,toShow);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View itemView =convertView;
			if(itemView==null){
				itemView= getLayoutInflater().inflate(android.R.layout.simple_list_item_1,
						parent, false);
			}
			
			return itemView;
			
			
		}
	}

	private void populateToShow(){
		for(int i = 0; i<toDos.size();i++){
			String add;
			if (toDos.getToDoItem(i).isDone()){
				add = toDos.getToDoItem(i).getToDo();
				add = add + "which is done and not archived.";
			}else{
				add = toDos.getToDoItem(i).getToDo();
				add = add + "which is not done and not archived.";
			}
			toShow.add(add);
		}
		for(int i = 0; i<archivedToDos.size();i++){
			String add;
			if (archivedToDos.getToDoItem(i).isDone()){
				add = archivedToDos.getToDoItem(i).getToDo();
				add = add + "which is done and archived.";
			}else{
				add = archivedToDos.getToDoItem(i).getToDo();
				add = add + "which is not done and archived.";
			}
			toShow.add(add);
			
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_to_dos);
		
		populateToShow();
		populateListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_to_dos, menu);
		return true;
	}

}
