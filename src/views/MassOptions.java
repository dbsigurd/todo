package views;

import models.ArchivedToDoList;
import models.ToDoItem;
import models.ToDoList;

import com.example.dbsigurd_mytodolist.R;
import com.example.dbsigurd_mytodolist.R.id;
import com.example.dbsigurd_mytodolist.R.layout;
import com.example.dbsigurd_mytodolist.R.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MassOptions extends MainActivity
{

	ToDoList toDos = ToDoList.getInstance();
	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	int toDosTotal = toDos.size(); // unarchived total
	int totalArch = archivedToDos.size(); //total archive
	int toDosChecked = 0;
	int archvChecked = 0;
	int toDosUnchecked;
	int archUnchecked;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mass_options);
		TextView statsText = (TextView) findViewById(R.id.stats1);
		TextView statsText2 = (TextView) findViewById(R.id.stats2);

		calculateCounts(); // calculates stas

		statsText.setText("You have " + toDosTotal + " unArchived to-dos, "
				+ toDosChecked + " checked and " + toDosUnchecked
				+ " unchecked.");
		statsText2.setText("You have " + totalArch + " Archived to-dos, "
				+ archvChecked + " checked and " + archUnchecked
				+ " unchecked.");
	}

	public void calculateCounts()
	{

		toDosTotal = toDos.size(); // unarchived total
		totalArch = archivedToDos.size();
		toDosChecked = 0;
		archvChecked = 0;
		for (int i = 0; i < toDosTotal; i++)
		{
			boolean checked = toDos.getToDoItem(i).isDone();
			if (checked)
			{
				toDosChecked++;
			}
		}
		for (int i = 0; i < totalArch; i++)
		{
			boolean checked = archivedToDos.getToDoItem(i).isDone();
			if (checked)
			{
				archvChecked++;
			}
		}
		toDosUnchecked = toDosTotal - toDosChecked;
		archUnchecked = totalArch - archvChecked;
	}

	public void deleteAll(View view)
	{

		toDos.deleteAll();
		archivedToDos.deleteAll();
		TextView statsText = (TextView) findViewById(R.id.stats1);
		TextView statsText2 = (TextView) findViewById(R.id.stats2);
		statsText.setText("");
		statsText2.setText("There are no to-dos! =)");
		// delete all archived
	}

	public void archiveAll(View view)
	{

		int size = toDos.size();
		for (int i = size - 1; i >= 0; i--)
		{
			ToDoItem current = toDos.getToDoItem(i);
			toDos.remove(current);
			archivedToDos.add(current);
			calculateCounts();
			TextView statsText = (TextView) findViewById(R.id.stats1);
			TextView statsText2 = (TextView) findViewById(R.id.stats2);
			statsText.setText("You have " + 0 + " unArchived to-dos, " + 0
					+ " checked and " + 0 + " unchecked.");
			statsText2.setText("You have " + totalArch + " Archived to-dos, "
					+ archvChecked + " checked and " + archUnchecked
					+ " unchecked.");

		}

		Toast.makeText(MassOptions.this,
				"All to-dos have been moved to archives", Toast.LENGTH_LONG)
				.show();
	}

	public void returnToMain(View v)
	{

		Intent returnIntent = new Intent(this, MainActivity.class);
		startActivity(returnIntent);
	}

	public void emailAll(View view)
	{

		String body = "";
		String message;
		for (int i = 0; i < toDos.size(); i++)
		{
			if (toDos.getToDoItem(i).isDone())
			{
				message = "You have " + toDos.getToDoItem(i).getToDo()
						+ " which is done and not archived! ";
			} else
			{
				message = "You have " + toDos.getToDoItem(i).getToDo()
						+ " which is not done and not archived. ";
			}
			body = body + message;
		}
		for (int i = 0; i < archivedToDos.size(); i++)
		{
			if (toDos.getToDoItem(i).isDone())
			{
				message = "You have " + archivedToDos.getToDoItem(i).getToDo()
						+ " which is done and archived! ";
			} else
			{
				message = "You have " + archivedToDos.getToDoItem(i).getToDo()
						+ " which is not done and archived. ";
			}
			body = body + message;
		}
		Toast.makeText(this, body, Toast.LENGTH_SHORT).show();
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");

		i.putExtra(Intent.EXTRA_SUBJECT, "To Dos!");

		i.putExtra(Intent.EXTRA_TEXT, body);
		try
		{
			startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex)
		{
			Toast.makeText(this, "There are no email clients installed.",
					Toast.LENGTH_SHORT).show();
		}

	}

	public void selectToDo(View view)
	{

		// Intent select_intent
		Intent intent = new Intent(this, SelectToDos.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mass_options, menu);
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
