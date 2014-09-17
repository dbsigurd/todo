package com.example.dbsigurd_mytodolist;

public class ToDoItem {
	private String toDo;
	private boolean archived;
	private boolean isDone;
	public ToDoItem(String toDo, boolean archived,boolean isDone){
		super();
		
		this.toDo = toDo;
		this.archived= archived;
		this.isDone = isDone;
	}


	public boolean isArchived() {
		return archived;
	}

	public String getToDo(){
		return toDo;
	
	}
	
	public boolean isDone(){
		return isDone;
	}
}
