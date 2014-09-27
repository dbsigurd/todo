package com.example.dbsigurd_mytodolist;


import java.util.ArrayList;
import java.util.List;

import Data.FileDataManager;
import Data.IDataManager;
/*
 * inverse of archived to do list. 
 * saving doesnt work =(
 * but everything else does
 */

public class ToDoList {
	private static IDataManager dataManager;
	private static ToDoList firstInstance = null;
	private List<ToDoItem> ToDos = new ArrayList<ToDoItem>();
	private ToDoList() {};
	public static ToDoList getInstance(){
		if(firstInstance == null){
			synchronized(ToDoList.class){
				if(firstInstance == null){
					firstInstance = new ToDoList();
					
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
		save();
		
		
	}
	public void addnew(int loc, String toDo, boolean isDone){
		firstInstance.ToDos.add(new ToDoItem(toDo,isDone));
		save();
	}
	public void remove(ToDoItem z){
		firstInstance.ToDos.remove(z);
		save();
	}
	public int size(){
		return firstInstance.ToDos.size();
	}
	public void deleteAll(){
		firstInstance.ToDos.clear();
		save();
	}
	
	public void save() {
			dataManager = new FileDataManager();

			dataManager.saveToDos(ToDos,0);
			dataManager.saveToDos(ToDos,1);
			
	   }
	
}
