package models;

import java.util.ArrayList;
import java.util.List;

import Data.dataController;

public class ArchivedToDoList {
	private List<ToDoItem> archivedToDos = new ArrayList<ToDoItem>();
	private static ArchivedToDoList firstInstance = null;
	private dataController data = new dataController();
	public ArchivedToDoList() {	};
	private final static String file = "Archived";// unarchived save file

	//singleton design principle 
	public static ArchivedToDoList getInstance(){
		if(firstInstance == null){
			synchronized(ToDoList.class){
				if(firstInstance == null){
					firstInstance = new ArchivedToDoList();
				}
			}
		}
		return firstInstance;
	}
	
	public void setAllToDos(List<ToDoItem> newToDos){
		archivedToDos = newToDos;
	}
	
	public ToDoItem getToDoItem(int x){
		return firstInstance.archivedToDos.get(x);
	}
	
	public List<ToDoItem> getToDoList(){
		archivedToDos= data.loadData(file);
		return archivedToDos;
	}
	
	public void add(ToDoItem y){
		firstInstance.archivedToDos.add(0,y);
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
			data.saveData(archivedToDos, file);
   }
}
