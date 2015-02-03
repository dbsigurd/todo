package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import models.ArchivedToDoList;
import models.ToDoItem;
import models.ToDoList;
import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/*
 * tried to implent saving as taught in the lab,
 * but could not get it to work. Gauna git hub
 * lonely twitter is where the code/style is from
 */
public class FileDataManager
{
	private String saveString = "Saving Tracker";
	private static FileDataManager INSTANCE = null;
	private Context context;
	public ArrayList<ToDoItem> toDoList;

	ToDoList toDos = ToDoList.getInstance();
	ArchivedToDoList archivedToDos = ArchivedToDoList.getInstance();
	
	
	
	/**
	 * singleton
	 * Returns the single instance that is used to manipulate the 
	 * current copy of getInstance that is currently available
	 * @return the instance to be manipulated 
	 */

	public FileDataManager(){
	}
	public void createContext (Context context1){
		this.context=context1; //main menu context
	}
	
	public static FileDataManager getInstance(){
		if (INSTANCE == null){
			INSTANCE = new FileDataManager();
		}
		return INSTANCE;
	}
	


	public ArrayList<ToDoItem> loadToDo(String choice)
	{
		try{
			File fh = new File(context.getFilesDir(), choice);
			
			Log.i("Loading", "load1");
			if (!fh.exists() ){
				Log.i("Loading","create new");
				ArrayList<ToDoItem> toDos = new ArrayList<ToDoItem>();
				return toDos;
			}
			FileInputStream fis = context.openFileInput(choice);

			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			Log.i("Loading", "load2");
			Gson gson = new Gson();
		
			Log.i("Loading", "load3");
			Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
			Log.i("Loading", "load4");
			toDoList = gson.fromJson(in, listType);
			Log.i("Loading", "load5");
			
			
			in.close();
			fis.close();
			Log.i("Loading", "Passed");
			
		} catch (FileNotFoundException e){
			Log.i("Loading", "Filenotfound");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return toDoList;
	}
	

	public void saveToDos(List<ToDoItem> toDoList, String file)
	{
		Log.i(saveString,"start");
		try {
			Log.i(saveString, "fail1");
			FileOutputStream fos =  context.openFileOutput(file,Context.MODE_PRIVATE);
			Gson gson = new Gson();
			Log.i(saveString, "fail2");
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			Log.i(saveString, "fail4");
			gson.toJson(toDoList,osw);
			Log.i(saveString, "fail5");
			osw.flush();
			Log.i(saveString, "passed");
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}

}
