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
	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	public void loadToDo(int x) {
		
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
					archivedToDos.add(lts.get(i));
				}
			} catch (Exception e) {
				Log.i("ToDos", "Error casting");
				e.printStackTrace();
			} 

		}
	}
	
	public void saveToDos(List<ToDoItem> lts,int x) {
		Log.i("Devon","TRIED SAVING");
		if(x==0){ //save to dos
			try {
				
				FileOutputStream fos = new FileOutputStream(FILENAME);
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

