package models;


import java.util.ArrayList;
import java.util.List;

import Data.FileDataManager;
import Data.dataController;
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
	private dataController data = new dataController();
	public ToDoList() {	};
	private final static String file = "unArchived";// unarchived save file

	//singleton design principle 
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
	
	public void setAllToDos(List<ToDoItem> newToDos){
		ToDos = newToDos;
	}
	
	public ToDoItem getToDoItem(int x){
		return firstInstance.ToDos.get(x);
	}
	
	public List<ToDoItem> getToDoList(){
		ToDos= data.loadData(file);
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
			data.saveData(ToDos, file);
   }
	
}
