package models;


import java.util.ArrayList;
import java.util.List;

import Data.FileDataManager;
/*
 *Class is used to represent a to do list
 *uses a singleton design principle
 *it is the super class of Archived To Do List
 *
 */

public class ToDoList {
	//array of ToDoItems
	private List<ToDoItem> ToDos = new ArrayList<ToDoItem>();
	private static ToDoList firstInstance = null;
	
	public ToDoList() {	};
	//singleton design principle 
	public static ToDoList getInstance(){
		if(firstInstance == null){
			synchronized(ToDoList.class){
				if(firstInstance == null){
					firstInstance = new ToDoList();
					FileDataManager dataManager = new FileDataManager();
					
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
		firstInstance.ToDos.add(0,y);
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
			
			
	   }
	
}
