package Model;

/**
 * Created by SUYASH on 10-02-2018.
 */

public class Item {

    int id;
    String Task;
    String Description;

    public Item(String task, String description) {
        Task = task;
        Description = description;
    }

    public Item(int id, String task, String description) {
        this.id=id;
        this.Task = task;
        this.Description = description;
    }
    public int getid(){
        return id;
    }
    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
