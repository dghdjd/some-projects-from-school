package model;

// Represents a task having a due time, name, description, and the completion status of the task

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.Set;

public class Task implements Writable {

    private int year;
    private int month;
    private int day;
    private String name;
    private String description;
    private Boolean status;
    private Set<Integer> months1;
    private Set<Integer> months2;

    // REQUIRES: year must be >= current year (2020), month must be 1 <= mm <= 12(integer)
    //           day must be 1 <= dd <= 31 for Jan, Mar, May, July, Aug, Oct, Dec, and
    //           1 <= dd <= 30 for April, June, Sep, and Nov. 1 <= dd <= 28 for Feb.
    // MODIFIES: this
    // EFFECTS: create a task with given time, name and description, and false completion status
    public Task(int yy, int mm, int dd, String name, String description) {
        this.year = yy;
        this.month = mm;
        this.day = dd;
        this.name = name;
        this.description = description;
        this.status = false;
    }

    //EFFECTS: get the description of the task
    public String getDescription() {
        return this.description;
    }

    // EFFECTS: get the name of the task
    public String getName() {
        return this.name;
    }

    // EFFECTS: get the status of the task
    public Boolean getStatus() {
        return this.status;
    }

    // REQUIRES: the task must be uncompleted (false)
    // MODIFIES: this
    // EFFECTS:  marks a task as completed
    public void markCompleted() {
        this.status = true;
    }

    public String getTime() {
        return year + "/" + month + "/" + day;
    }

    // EFFECTS: return true if a task time matches the given time
    public Boolean checkTime(int yy, int mm, int dd) {
        return this.year == yy && this.month == mm && this.day == dd;

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("year", year);
        json.put("month", month);
        json.put("day", day);
        json.put("description", description);
        json.put("status", status);
        return json;


    }
}
