package com.example.dbsigurd_mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*this class is used to display and control option for a single archived to do
 it uses onclick listners and both archived to do list and unarchived to list
 to switch items as need. also is able send email to the default email client
 */
public class Archived_Options extends Activity
{

	public static final String EXTRA_CHOICE = "the Choice"; // used to pass result of activity
	public int positionChosen; //the position in the list of todo chosen
	ToDoList toDos = ToDoList.getInstance(); //singleton design to avoid duplicate class
	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	ToDoItem toEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archived__options);
		Intent intent = getIntent();
		int positionChosen = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);
		toEdit = archivedToDos.getToDoItem(positionChosen);
		TextView toDoChosen = (TextView) findViewById(R.id.clicked);
		toDoChosen.setText(archivedToDos.getToDoItem(positionChosen).getToDo());

		setupCancel(); // returns as normal
		setupDelete(); // deletes todo
		setupUnarchive(); //unarchive to do
		setupEmail(); // emails this on to do
	}

	private void setupEmail()
	{

		Button btn = (Button) findViewById(R.id.EmailButton);
		btn.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				int choiceClicked = 4;
				Intent intent = new Intent();
				intent.putExtra(EXTRA_CHOICE, choiceClicked);
				setResult(Activity.RESULT_OK, intent);
				finish();
				// TODO Auto-generated method stub

			}
		});
	}

	private void setupUnarchive()
	{

		// TODO Auto-generated method stub
		Button btn = (Button) findViewById(R.id.UnarchiveButton);
		btn.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				int choiceClicked = 3;
				Intent intent = new Intent();
				String pop = toEdit.getToDo();
				Toast.makeText(Archived_Options.this,
						pop + " has been Unarchived", Toast.LENGTH_LONG).show();
				// add to Archived list
				toDos.add(toEdit);
				archivedToDos.remove(toEdit);
				intent.putExtra(EXTRA_CHOICE, choiceClicked);
				setResult(Activity.RESULT_OK, intent);
				finish();
				// TODO Auto-generated method stub

			}
		});
	}

	public void setupDelete()
	{

		// TODO Auto-generated method stub
		Button btn = (Button) findViewById(R.id.DeleteButton);
		btn.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				int choiceClicked = 2;
				Intent intent = new Intent();
				archivedToDos.remove(toEdit);
				intent.putExtra(EXTRA_CHOICE, choiceClicked);
				setResult(Activity.RESULT_OK, intent);
				finish();
				// TODO Auto-generated method stub

			}
		});
	}

	public void Copy(View view)
	{

		int choiceClicked = 1;
		Intent intent = new Intent();

		archivedToDos.add(toEdit);
		intent.putExtra(EXTRA_CHOICE, choiceClicked);
		setResult(Activity.RESULT_OK, intent);
		finish();
		// TODO Auto-generated method stub

	}

	private void setupCancel()
	{

		Button btn = (Button) findViewById(R.id.CancelButton);
		btn.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				Intent intent = new Intent();
				setResult(Activity.RESULT_CANCELED, intent);
				finish();
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archived__options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
