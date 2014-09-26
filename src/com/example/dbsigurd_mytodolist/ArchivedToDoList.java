package com.example.dbsigurd_mytodolist;

import java.util.ArrayList;
import java.util.List;




public class ArchivedToDoList {

	private static ArchivedToDoList firstInstance = null;
	private List<ToDoItem> archivedToDos = new ArrayList<ToDoItem>();
	private ArchivedToDoList() {};
//	dataManager = new FileDataManager();
	public static ArchivedToDoList getInstance(){
		if(firstInstance == null){
			synchronized(ArchivedToDoList.class){
				if(firstInstance == null){
					firstInstance = new ArchivedToDoList();
					
				}
			}
		}
	return firstInstance;
	}
	
	public ToDoItem getToDoItem(int x){
		return firstInstance.archivedToDos.get(x);
	}
	public List<ToDoItem> getToDoList(){
		return archivedToDos;
	}
	
	public void add(ToDoItem y){
		String newString =y.getToDo();
		Boolean newBool = y.isDone();
		ToDoItem newToDo = new ToDoItem(newString,newBool);
		firstInstance.archivedToDos.add(0,newToDo);
		save();
		
	}
	public void addnew(int loc, String toDo, boolean isDone){
		firstInstance.archivedToDos.add(new ToDoItem(toDo,isDone));
		save();
	}
	public void remove(ToDoItem z){
		firstInstance.archivedToDos.remove(z);
		save();
	}
	public int size(){
		return firstInstance.archivedToDos.size();
	}
	public void deleteAll(){
		firstInstance.archivedToDos.clear();
		save();
	}
	public void save() {

		
   }
	
}