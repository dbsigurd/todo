package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.example.dbsigurd_mytodolist.ArchivedToDoList;
import com.example.dbsigurd_mytodolist.ToDoItem;
import com.example.dbsigurd_mytodolist.ToDoList;
/*
 * tried to implent saving as taught in the lab,
 * but could not get it to work. Gauna git hub
 * lonely twitter is where the code/style is from
 */
public class FileDataManager implements IDataManager
{

	private static final String FILENAME = "file.sav"; // for to dos
	private static final String FILENAME2 = "archfile.sav"; // for archives
	ToDoList toDos = ToDoList.getInstance();
	private Context context;

	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();

	@SuppressWarnings("unchecked")
	public void loadToDo(int x)
	{

		Log.i("Devon", "TRIED LOADING");
		x = 0;
		// ArrayList<AbstractTweet> lts = new ArrayList<AbstractTweet>();
		if (x == 0)
		{
			ArrayList<ToDoItem> lts = new ArrayList<ToDoItem>();

			try
			{
				File fh = new File(context.getFilesDir(), FILENAME);
				if (!fh.exists())
				{
					return;
				}
				FileInputStream fis = context.openFileInput(FILENAME);
				ObjectInputStream ois = new ObjectInputStream(fis);

				lts = (ArrayList<ToDoItem>) ois.readObject();
				Log.i("loadings", "entered load");
				for (int i = 0; i < lts.size(); i++)
				{
					toDos.add(lts.get(i));
					Log.i("loadings", lts.get(i).getToDo());

				}
				fis.close();
				ois.close();
			} catch (Exception e)
			{
				Log.i("loadings", "Error casting");
				e.printStackTrace();
			}

		} else
		{
			ArrayList<ToDoItem> lts = new ArrayList<ToDoItem>();

			try
			{
				FileInputStream fis = new FileInputStream(FILENAME2);
				ObjectInputStream ois = new ObjectInputStream(fis);

				lts = (ArrayList<ToDoItem>) ois.readObject();
				for (int i = 0; i < lts.size(); i++)
				{

					archivedToDos.add(lts.get(i));
				}
			} catch (Exception e)
			{
				Log.i("ToDos", "Error casting");
				e.printStackTrace();
			}

		}
	}

	public void saveToDos(List<ToDoItem> lts, int x)
	{

		x = 0;

		if (x == 0)
		{ // save to dos
			try
			{
				Log.i("Devon", "TRIED SAVING");
				FileOutputStream fos = context.openFileOutput(
						FileDataManager.FILENAME, Context.MODE_PRIVATE);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(lts);
				fos.close();
			} catch (Exception e)
			{
				Log.i("ToDos", "Error casting");
				e.printStackTrace();
			}
		} else
		{ // saves archives
			try
			{
				FileOutputStream fos = new FileOutputStream(FILENAME2);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(lts);
				fos.close();
			} catch (Exception e)
			{
				Log.i("ToDos", "Error casting");
				e.printStackTrace();
			}
		}
	}

}
