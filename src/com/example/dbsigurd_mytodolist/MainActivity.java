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
    public ToDoItem toEdit;
    public ToDoItem currentToDo;
    public int toEditPosition;
    public int choiceResult;
	public ToDoItem milk = new ToDoItem("get milk", true);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //load todoList
        toDos.add(milk);
        populateListView();
        
        
    }


    

    
	public void populateToDoList() {
		toDoViewList.clear();
		Toast.makeText(MainActivity.this,"" + toDos.size(),Toast.LENGTH_LONG).show();
		for(int i =0; i < (toDos.size()); i++){
			ToDoItem nextOnList = toDos.getToDoItem(i);
			
			toDoViewList.add(nextOnList);
		}
		//toDoViewList.add(milk);
		}
	
	
	//use array adapter
	public void populateListView() {
		populateToDoList();
		// TODO Auto-generated method stub
		ArrayAdapter<ToDoItem> adapter = new MyListAdapter();
		//ArratAdapter<ToDoItem> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.toDoListView);
		list.setAdapter(adapter);
	}
	public class MyListAdapter extends ArrayAdapter<ToDoItem>{
		public MyListAdapter(){
			super(MainActivity.this, R.layout.item_view,toDoViewList);
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

			ToDoItem currentToDo = toDoViewList.get(position);
			toEdit = currentToDo;
			toEditPosition = position;
			CheckBox displayBox = (CheckBox) itemView.findViewById(R.id.toDoCheckBox);
			displayBox.setText(currentToDo.getToDo());
			displayBox.setChecked(currentToDo.isDone());
			Button optButton = (Button) itemView.findViewById(R.id.optionsButton);
			optButton.setOnClickListener(new OnClickListener(){
				
				//fill list
				@Override
				public void onClick(View view){
					
					Toast.makeText(MainActivity.this,toEdit.getToDo(),Toast.LENGTH_LONG).show();
					openOptions(toEdit);

					//Toast.makeText(MainActivity.this,currentToDo.getToDo(),Toast.LENGTH_LONG).show();
				}


			});
				
			
		
			
			return itemView;
	//		return super.getView(position, convertView, parent);
		}
	}



		

	public void openOptions(ToDoItem toEdit) {
		Intent intent = new Intent(this,Options.class);
		String chosenText = toEdit.getToDo();
		//Toast.makeText(MainActivity.this, chosenText, Toast.LENGTH_LONG).show();
		
		
		// from here ill return an int and edit accordingly
		intent.putExtra(EXTRA_MESSAGE, chosenText);
		startActivityForResult(intent,RESULT_CODE_OPTIONS);
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
			return;
		}
		switch(requestCode){
		case RESULT_CODE_OPTIONS:
			int answer = data.getIntExtra(Options.EXTRA_CHOICE,0);
			choiceResult = answer;
			//String pop = toEdit.getToDo();
			//Toast.makeText(MainActivity.this,pop,Toast.LENGTH_LONG).show();
			if (choiceResult==1){
				
				toDos.add(toEdit);
			}
			else if(choiceResult==2){
				toDos.remove(toEdit);
			}
			else if(choiceResult ==3){
				String pop = toEdit.getToDo();
				Toast.makeText(MainActivity.this,pop+" has been archived",Toast.LENGTH_LONG).show();
				//add to Archived list
				ArchivedToDoList.setToDoItem(toEdit);
				toDos.remove(toEdit);
			}
			else if (choiceResult == 4){
				//setupEmailOption
			}
			populateListView();
			//Toast.makeText(MainActivity.this,"" + answer,Toast.LENGTH_LONG).show();
			//String toDoCopied = currentToDo.getToDo();
			//Boolean toDoDone = currentToDo.isDone();
			//Toast.makeText(MainActivity.this,currentToDo.getToDo(),Toast.LENGTH_LONG).show();
			//toDos.add(0, new ToDoItem(toDoCopied,toDoDone));
				
			//populateListView();
				
		}
		
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
	
	
	


    
  
