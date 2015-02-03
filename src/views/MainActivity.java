package views;

import models.ArchivedToDoList;
import models.ToDoItem;
import models.ToDoList;
import Data.dataController;
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

import com.example.dbsigurd_mytodolist.R;

public class MainActivity extends Activity
{

	/*
	 * this is my main activity. it can take in to dos as needed it also
	 * displays them using a coustum list view learned from Dr brian fraser of
	 * SFU youtube videos. and a bit from the android development site.
	 * IdataManager was taught in the lab by Gauna but could not get workign =(
	 */
	public static final int RESULT_CODE_OPTIONS = 1;
	public static final String EXTRA_ANSWER = "Choice";
	public final static String EXTRA_MESSAGE = "com.example.dbsigurd_mytodolist";
	public dataController myDataController = new dataController();
	
	
	ToDoList toDos = ToDoList.getInstance();
	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	// public List<ToDoItem> toDoViewList = new ArrayList<ToDoItem>();
	// public ToDoItem toEdit;
	public ToDoItem currentToDo;
	// public int toEditPosition;
	public int choiceResult;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// load todoList
		myDataController.setContext(this.getBaseContext());
		populateListView();

	}

	@Override
	protected void onStart()
	{
		super.onStart();
	}
	// use array adapter
	public void populateListView()
	{

		// populates the list view

		ArrayAdapter<ToDoItem> adapter = new MyListAdapter();
		myDataController.loadData("unArchived");
		myDataController.loadData("Archived");
		ListView list = (ListView) findViewById(R.id.toDoListView);
		list.setAdapter(adapter);
	}

	private class MyListAdapter extends ArrayAdapter<ToDoItem>
	{

		public MyListAdapter()
		{

			super(MainActivity.this, R.layout.item_view, toDos.getToDoList());
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{

			// TODO Auto-generated method stub

			View itemView = convertView;
			if (itemView == null)
			{
				itemView = getLayoutInflater().inflate(R.layout.item_view,
						parent, false);
			}
			// Find the Todo

			ToDoItem currentToDo = toDos.getToDoItem(position);
			final int toEditPosition = position;
			CheckBox displayBox = (CheckBox) itemView
					.findViewById(R.id.toDoCheckBox);
			displayBox.setText(currentToDo.getToDo());
			displayBox.setChecked(currentToDo.isDone());
			displayBox.setOnClickListener(new OnClickListener()
			{

				public void onClick(View view)
				{

					toDos.getToDoItem(toEditPosition).isDoneFlip();
					populateListView();
				}
			});

			Button optButton = (Button) itemView
					.findViewById(R.id.optionsButton);
			optButton.setOnClickListener(new OnClickListener()
			{

				// fill list

				@Override
				public void onClick(View view)
				{

					openOptions(toEditPosition);

				}
			});

			return itemView;

		}
	}

	public void openOptions(int toEditPosition)
	{

		Intent intent = new Intent(this, Options.class);

		// original plan was to use result to determined what button was
		// pressed but ended up not needing it
		intent.putExtra(EXTRA_MESSAGE, toEditPosition);
		startActivityForResult(intent, RESULT_CODE_OPTIONS);
		populateListView();

		return;
	}

	// gets called when started activity finishes
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_CANCELED)
		{
			choiceResult = 0;
			populateListView();
			return;
		}

		populateListView();

	}

	public void sendMessage(View view)
	{

		EditText editText = (EditText) findViewById(R.id.ToDoBox);
		String message = editText.getText().toString();
		if (message.length() != 0)
		{

			ToDoItem newAdd = new ToDoItem(message, false);
			toDos.add(newAdd);
			populateListView();
			editText.setText("");
		}
	}

	public void openMassOptions(View view)
	{

		Intent massIntent = new Intent(this, MassOptions.class);
		startActivityForResult(massIntent, 2);
	}

	public void archivedLauncher(View v)
	{

		Intent archivedIntent = new Intent(this, ViewArchive.class);
		startActivity(archivedIntent);
	}

}

