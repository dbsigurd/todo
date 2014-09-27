package Data;

import java.util.List;

import com.example.dbsigurd_mytodolist.ToDoItem;

public interface IDataManager
{

	public void loadToDo(int x);

	public void saveToDos(List<ToDoItem> lts, int x);

}
