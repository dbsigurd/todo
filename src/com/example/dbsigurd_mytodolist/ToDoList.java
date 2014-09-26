package com.example.dbsigurd_mytodolist;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;


public class ToDoList {
	private static final String DATABASEFILE = "toDoList.save";
	private Context context;

	//private static IDataManager dataManager;
	private static ToDoList firstInstance = null;
	private List<ToDoItem> ToDos = new ArrayList<ToDoItem>();
	private ToDoList(Context context) {
		this.context = context;
		this.load();
	};
	public static ToDoList getInstance(Context context){
		if(firstInstance == null){
			synchronized(ToDoList.class){
				if(firstInstance == null){
					firstInstance = new ToDoList(context);
					
				}
			}
		}
	return firstInstance;
	}
	
	
	public ToDoItem getToDoItem(int x){
		return firstInstance.ToDos.get(x);
	}
	public List<ToDoItem> getToDoList(){
		return ToDos;
	}
	
	public void add(ToDoItem y){
		String newString =y.getToDo();
		Boolean newBool = y.isDone();
		ToDoItem newToDo = new ToDoItem(newString,newBool);
		firstInstance.ToDos.add(0,newToDo);
		firstInstance.save();
		
		
	}
	public void addnew(int loc, String toDo, boolean isDone){
		firstInstance.ToDos.add(new ToDoItem(toDo,isDone));
		firstInstance.save();
	}
	public void remove(ToDoItem z){
		firstInstance.ToDos.remove(z);
		firstInstance.save();
	}
	public int size(){
		return firstInstance.ToDos.size();
	}
	public void deleteAll(){
		firstInstance.ToDos.clear();
		firstInstance.save();
	}
	
	private void save() {
		try{
			FileOutputStream fileOut = context.openFileOutput(ToDoList.DATABASEFILE, Context.MODE_PRIVATE);
			ObjectOutputStream output= new ObjectOutputStream(fileOut);
			output.writeObject(firstInstance.ToDos);
			output.close();
			fileOut.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	  }
	
	@SuppressWarnings("unchecked")
	private void load() {
		try{
			File fh = new File(context.getFilesDir(),ToDoList.DATABASEFILE);
			if (!fh.exists()){
				this.ToDos = new ArrayList<ToDoItem>();
				return;//return nothing to load here
			}
			FileInputStream fileIn = context.openFileInput(ToDoList.DATABASEFILE);
			ObjectInputStream input = new ObjectInputStream(fileIn);
			this.ToDos = (ArrayList<ToDoItem>) input.readObject();
			input.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
}

