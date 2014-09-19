package com.example.dbsigurd_mytodolist;


import java.util.ArrayList;
import java.util.List;


public class ToDoList {
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
	
	public void add(ToDoItem y){
		firstInstance.ToDos.add(0,y);
		
	}
	public void addnew(int loc, String toDo, boolean isDone){
		firstInstance.ToDos.add(new ToDoItem(toDo,isDone));
	}
	public void remove(ToDoItem z){
		firstInstance.ToDos.remove(z);
	}
	public int size(){
		return firstInstance.ToDos.size();
	}
	
}
