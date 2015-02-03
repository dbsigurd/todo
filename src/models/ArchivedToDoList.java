package models;

import java.util.ArrayList;
import java.util.List;

import Data.FileDataManager;

public class ArchivedToDoList extends ToDoList{
	private List<ToDoItem> ToDos = new ArrayList<ToDoItem>();
	
	private static ArchivedToDoList firstInstance = null;

	public ArchivedToDoList() {};
	
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
	@Override
	public ToDoItem getToDoItem(int x) {
		// TODO Auto-generated method stub
		return super.getToDoItem(x);
	}

	@Override
	public List<ToDoItem> getToDoList() {
		// TODO Auto-generated method stub
		return super.getToDoList();
	}

	@Override
	public void add(ToDoItem y) {
		// TODO Auto-generated method stub
		super.add(y);
	}

	@Override
	public void addnew(int loc, String toDo, boolean isDone) {
		// TODO Auto-generated method stub
		super.addnew(loc, toDo, isDone);
	}

	@Override
	public void remove(ToDoItem z) {
		// TODO Auto-generated method stub
		super.remove(z);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return super.size();
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		super.deleteAll();
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		super.save();
	}

}
