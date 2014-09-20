package com.example.dbsigurd_mytodolist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	public static final int RESULT_CODE_OPTIONS = 1;
	public static final String EXTRA_ANSWER = "Choice";
	public final static String EXTRA_MESSAGE = "com.example.dbsigurd_mytodolist";
	ToDoList toDos = ToDoList.getInstance();
	public List<ToDoItem> toDoViewList = new ArrayList<ToDoItem>();
    //public ToDoItem toEdit;
    public ToDoItem currentToDo;
    //public int toEditPosition;
    public int choiceResult;
	public ToDoItem milk = new ToDoItem("get milk", true);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //load todoList
        
        
        populateListView();
        
        
    }


    
    
    
	public void populateToDoList() {
		/*toDoViewList.clear();
		Toast.makeText(MainActivity.this,"" + toDos.size(),Toast.LENGTH_LONG).show();
		for(int i =0; i < (toDos.size()); i++){
			ToDoItem nextOnList = toDos.getToDoItem(i);
			
			toDoViewList.add(nextOnList);
		}*/
		//toDoViewList.add(milk);
		//toDos.add(milk);
		}
	
	
	//use array adapter
	public void populateListView() {
		//populateToDoList();
		// TODO Auto-generated method stub
		ArrayAdapter<ToDoItem> adapter = new MyListAdapter();
		//ArratAdapter<ToDoItem> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.toDoListView);
		list.setAdapter(adapter);
	}
	private class MyListAdapter extends ArrayAdapter<ToDoItem>{
		public MyListAdapter(){
			super(MainActivity.this, R.layout.item_view,toDos.getToDoList());
		}

		@Override
		public View getView( int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// if null view
			View itemView = convertView;
			if (itemView==null){
				itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
			}
			//Find the Todo
			
			ToDoItem currentToDo = toDos.getToDoItem(position);
			//final ToDoItem toEdit = currentToDo;
			final int toEditPosition = position;
			CheckBox displayBox = (CheckBox) itemView.findViewById(R.id.toDoCheckBox);
			displayBox.setText(currentToDo.getToDo());
			displayBox.setChecked(currentToDo.isDone());
			//CheckBox chxBox = (CheckBox) itemView.findViewById(R.id.toDoCheckBox);
			displayBox.setOnClickListener(new OnClickListener(){
				public void onClick(View view){
					toDos.getToDoItem(toEditPosition).isDoneFlip();
					populateListView();
				}
			});
			
			Button optButton = (Button) itemView.findViewById(R.id.optionsButton);
			optButton.setOnClickListener(new OnClickListener(){
				
				//fill list
				
				@Override
				public void onClick(View view){
					
					
					openOptions(toEditPosition);

					//Toast.makeText(MainActivity.this,currentToDo.getToDo(),Toast.LENGTH_LONG).show();
				}


			});
				
			
		
			
			return itemView;
	//		return super.getView(position, convertView, parent);
		}
	}



		

	public void openOptions(int toEditPosition) {
		Toast.makeText(MainActivity.this,""+toEditPosition,Toast.LENGTH_SHORT).show();
		Toast.makeText(MainActivity.this,toDos.getToDoItem(toEditPosition).getToDo(),Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this,Options.class);
		
		// from here ill return an int and edit accordingly
		intent.putExtra(EXTRA_MESSAGE, toEditPosition);
		startActivityForResult(intent,RESULT_CODE_OPTIONS);
		populateListView();
		//Toast.makeText(MainActivity.this,""+choiceResult,Toast.LENGTH_LONG).show();
		
		return;
	}
	
	//gets called when started activity finishes
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode== Activity.RESULT_CANCELED){
			choiceResult = 0;
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
		
	




	public void sendMessage(View view) {
		EditText editText = (EditText) findViewById(R.id.ToDoBox);
		String message = editText.getText().toString();
		if (message.length()!=0){
			
			ToDoItem newAdd = new ToDoItem(message,false);
			toDos.add(newAdd);
			populateListView();
			editText.setText("");
		}
	}
	
}

	/*public void archivedLauncher(View v) {
		Intent intent = new Intent(this,ViewArchive.class);
		
		
		// from here ill return an int and edit accordingly
		
		startActivityForResult(intent,RESULT_CODE_OPTIONS);
		//Toast.makeText(MainActivity.this,""+choiceResult,Toast.LENGTH_LONG).show();
		
		return;
	}
*/
	
	
	


    
  
