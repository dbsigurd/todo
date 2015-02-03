package models;
/*
 * custom to do item
 * has to do string and bool
 * also flip method
 */
public class ToDoItem {
	// used to represent task
	private String toDo;
	// for checking box
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
		isDone = !isDone;
	}
}
