package com.example.dbsigurd_mytodolist;

public class ToEmail {
	private String body;
	private String message;
	
	public void addToDo(String toDo,boolean isDone){
		if (isDone){
			this.message = "You have " + toDo +" which is done!" ;
		}else{
			this.message = "You have " + toDo +" which is not done.";
		}
		this.body= this.body+this.message;
	}

}
