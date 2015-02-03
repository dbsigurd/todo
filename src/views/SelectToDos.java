package views;

import java.util.ArrayList;

import models.ArchivedToDoList;
import models.ToDoItem;
import models.ToDoList;

import com.example.dbsigurd_mytodolist.R;
import com.example.dbsigurd_mytodolist.R.id;
import com.example.dbsigurd_mytodolist.R.layout;
import com.example.dbsigurd_mytodolist.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

/*
 * used to select multiple to dos to email
 * i simply convert to do lists
 * in to custom Listview of check boxs.
 * and any one clicked i can email as a message.
 * there is a toast but the return happens so quick it is
 * missed from time to time.
 */
public class SelectToDos extends Activity
{

	ToDoList toDos = ToDoList.getInstance();
	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	public ToDoItem currentToDo;
	public int choiceResult;
	public int disp = 0;
	public ArrayList<String> toShow = new ArrayList<String>();

	public ArrayList<Integer> clicked = new ArrayList<Integer>();

	public void populateListView()
	{

		// TODO Auto-generated method stub
		ArrayAdapter<String> adapter = new MyArrayAdapter();
		ListView toShowList = (ListView) findViewById(R.id.selectListView);

		toShowList.setAdapter(adapter);

		

	}

	private class MyArrayAdapter extends ArrayAdapter<String>
	{

		public MyArrayAdapter()
		{

			super(SelectToDos.this, R.layout.selectview, toShow);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{

			View itemView = convertView;
			if (itemView == null)
			{
				itemView = getLayoutInflater().inflate(R.layout.selectview,
						parent, false);
			}
			String currentShow = toShow.get(position);

			final Integer toShowPosition = position;
			final CheckBox showText = (CheckBox) itemView
					.findViewById(R.id.selectEmail);

			showText.setText(currentShow);
			if(clicked.contains(toShowPosition))
			{
				showText.setChecked(true);
			}else
			{
				showText.setChecked(false);
			}
			showText.setOnClickListener(new OnClickListener()

			{
				public void onClick(View v)
				{

					if (clicked.contains(toShowPosition))
					{
						showText.setChecked(false);
						int pos = clicked.indexOf(toShowPosition);
						clicked.remove(pos);
					} else
					{
						showText.setChecked(true);
						clicked.add(toShowPosition);
					}

					populateListView();
				}
			});

			// TODO Auto-generated method stub

			return itemView;

		}
	}

	private void populateToShow()
	{

		for (int i = 0; i < toDos.size(); i++)
		{
			String add;
			if (toDos.getToDoItem(i).isDone())
			{
				add = toDos.getToDoItem(i).getToDo();
				add = add + "which is done and not archived. ";
			} else
			{
				add = toDos.getToDoItem(i).getToDo();
				add = add + "which is not done and not archived. ";
			}
			toShow.add(add);
		}
		for (int i = 0; i < archivedToDos.size(); i++)
		{
			String add;
			if (archivedToDos.getToDoItem(i).isDone())
			{
				add = archivedToDos.getToDoItem(i).getToDo();
				add = add + "which is done and archived. ";
			} else
			{
				add = archivedToDos.getToDoItem(i).getToDo();
				add = add + "which is not done and archived. ";
			}
			toShow.add(add);

		}
	}
	public void sendEmail(View V){
		String body="";
		for(int i=0;i<clicked.size();i++)
		{
			String add = toShow.get(clicked.get(i));
			body=body+add;
		}
		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_SUBJECT,"To Dos!");
		email.putExtra(Intent.EXTRA_TEXT, body);
		try{
			startActivity(Intent.createChooser(email, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(SelectToDos.this, "There are no email clients installed.", Toast.LENGTH_LONG).show();
		}
		Intent returnd = new Intent(this,MainActivity.class);
		startActivity(returnd);
		
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
