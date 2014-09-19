package com.example.dbsigurd_mytodolist;

import java.util.ArrayList;
import java.util.List;


public class ArchivedToDoList {
	private static ArchivedToDoList firstInstance = null;
	private List<ToDoItem> archivedToDos = new ArrayList<ToDoItem>();
	private ArchivedToDoList() {};
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
	
	public static void setToDoItem(ToDoItem y){
		firstInstance.archivedToDos.add(y);
		
	}
	public void setNewToDoItem(String toDo, boolean isDone){
		firstInstance.archivedToDos.add(new ToDoItem(toDo,isDone));
		
	}
	public void deleteToDoItem(ToDoItem z){
		firstInstance.archivedToDos.remove(z);
	}
	
}
