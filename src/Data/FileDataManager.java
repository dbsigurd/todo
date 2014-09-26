package Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.dbsigurd_mytodolist.ArchivedToDoList;
import com.example.dbsigurd_mytodolist.Options;
import com.example.dbsigurd_mytodolist.ToDoItem;
import com.example.dbsigurd_mytodolist.ToDoList;


public class FileDataManager implements IDataManager{
	private static final String FILENAME = "file.sav"; // for to dos
	private static final String FILENAME2 = "archfile.sav"; // for archives
	ToDoList toDos = ToDoList.getInstance();
	private Context context;

	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	@SuppressWarnings("unchecked")
	public void loadToDo(int x) {
		Log.i("Devon","TRIED LOADING");
		//ArrayList<AbstractTweet> lts = new ArrayList<AbstractTweet>();
		if (x==0){
			ArrayList<ToDoItem> lts = new ArrayList<ToDoItem>();
			
			try {
				FileInputStream fis = new FileInputStream(FILENAME);
				ObjectInputStream ois = new ObjectInputStream(fis);

				lts = (ArrayList<ToDoItem>) ois.readObject();
				for (int i=0; i<lts.size();i++){
					toDos.add(lts.get(i));
				}
			} catch (Exception e) {
				Log.i("ToDos", "Error casting");
				e.printStackTrace();
			} 

			
		}else{
			ArrayList<ToDoItem> lts = new ArrayList<ToDoItem>();
			
			try {
				FileInputStream fis = new FileInputStream(FILENAME2);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				lts = (ArrayList<ToDoItem>) ois.readObject();
				for (int i=0; i<lts.size();i++){
					Log.i("ToDos", lts.get(i).getToDo());
					archivedToDos.add(lts.get(i));
				}
			} catch (Exception e) {
				Log.i("ToDos", "Error casting");
				e.printStackTrace();
			} 

		}
	}
	
	public void saveToDos(List<ToDoItem> lts,int x) {
		x=0;
		Log.i("Devon","TRIED SAVING");
		if(x==0){ //save to dos
			try {
				
				FileOutputStream fos = context.openFileOutput(FileDataManager.FILENAME,Context.MODE_PRIVATE);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(lts);
				fos.close();
			} 
			catch (Exception e) {
				Log.i("ToDos", "Error casting");
				e.printStackTrace();
			}
		}else{ // saves archives
			try {
				FileOutputStream fos = new FileOutputStream(FILENAME2);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(lts);
				fos.close();
			} 
			catch (Exception e) {
				Log.i("ToDos", "Error casting");
				e.printStackTrace();
			}
		}
	}

}

