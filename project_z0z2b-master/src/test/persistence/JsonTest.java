package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkTask(int year, int month, int day,
                             String name, String description, Boolean status, Task task) {
        assertTrue(task.checkTime(year,month,day));
        assertEquals(name, task.getName());
        assertEquals(status,task.getStatus());
        assertEquals(description, task.getDescription());

    }
}
