package com.example.dbsigurd_mytodolist;

public class ToDoItem {
	private String toDo;
	
	private boolean isDone;
	public ToDoItem(String toDo,boolean isDone){
		super();
		
		this.toDo = toDo;
		
		this.isDone = isDone;
	}


	

	public String getToDo(){
		return toDo;
	
	}
	
	public boolean isDone(){
		return isDone;
	}
	public void isDoneFlip(){
		if(isDone == true){
			this.isDone = false;
		}else{
			this.isDone = true;
		}
	}
}
