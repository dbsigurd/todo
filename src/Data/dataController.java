package Data;

import java.util.ArrayList;

import models.ToDoItem;
import android.content.Context;

public class dataController {
	public FileDataManager myDataManager = new FileDataManager();
	
	
	public dataController(){
		myDataManager.getInstance();
	}
	
	public void saveData(ArrayList<ToDoItem> todos, String file){
		myDataManager.saveToDos(todos, file);
		
	}
	public void loadData(String file){
		ArrayList<ToDoItem> loadedList = myDataManager.loadToDo(file);
		// should just update singleton
		return loadedList;
	}
	public void setContext(Context context){
		myDataManager.createContext(context);
	}
	

}
