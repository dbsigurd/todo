package Data;

import java.util.ArrayList;
import java.util.List;

import models.ToDoItem;
import android.content.Context;
import android.util.Log;

public class dataController {
	public FileDataManager myDataManager;
	private List<ToDoItem> loadList; 
	
	
	public dataController(){
		myDataManager = FileDataManager.getInstance();
	}
	
	public void saveData(List<ToDoItem> todos, String file){
		myDataManager.saveToDos(todos, file);
		
	}
	public List<ToDoItem> loadData(String file){
		loadList = myDataManager.loadToDo(file);
		return loadList;
		// should just update singleton
		
	}
	public void setContext(Context context2){
		myDataManager = FileDataManager.getInstance();
		Log.i("context", context2.toString());
		myDataManager.createContext(context2);
	}
	
}
