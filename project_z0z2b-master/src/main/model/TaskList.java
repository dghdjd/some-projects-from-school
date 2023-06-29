package model;

//  A representation of a collection of tasks



import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList implements Writable {
    private List<Task> tasks;
    private String name;

    // EFFECTS: create an empty collection of tasks
    public TaskList(String name) {
        this.name = name;
        tasks = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given task into the TaskList, given that the task is not already in the list.
    //          return true if the insertion is successful, else return false.
    public boolean insertTask(Task task) {
        for (Task t : tasks) {

            if (t.getName().equals(task.getName()) && t.getTime().equals(task.getTime())) {
                return false;
            }
        }
        tasks.add(task);
        return true;
    }

    public String getListName() {
        return this.name;
    }





    // MODIFIES: this
    // EFFECTS : remove a task from the collection of tasks, return true if
    //           successful, else false
    public Boolean removeTask(String name) {
        for (Task t : tasks) {
            if (t.getName().equals(name)) {
                tasks.remove(t);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the number of tasks remaining
    public int remainingTaskNum() {
        return tasks.size();
    }

    // EFFECTS: returns an list of tasks in this TaskList
    public List<Task> getTasks() {
        return tasks;
    }


    // MODIFIES: this
    // EFFECTS : mark a task with given name as completed from the collection of tasks
    // , return true if successful, false if the task does not exist
    // throw CompletedTaskException if the status of the task is true already
    public Boolean markTaskCompleted(String name) throws CompletedTaskException {
        for (Task t : tasks) {
            if (t.getName().equals(name)) {
                if (t.getStatus()) {
                    throw new CompletedTaskException();
                }
                t.markCompleted();
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Tasks", tasksToJson());
        return json;
    }

    // EFFECTS: returns Tasks in this TaskList as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasks) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}






