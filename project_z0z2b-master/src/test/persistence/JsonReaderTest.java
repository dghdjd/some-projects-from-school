package persistence;


import model.Task;
import model.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {
    @BeforeEach
    void createReaderTestFiles() {

        try {
            TaskList tl = new TaskList("TaskList1");
            tl.insertTask(new Task(2000, 1, 13, "123", "123"));
            tl.insertTask(new Task(2020, 8, 31, "1233", "123"));
            tl.getTasks().get(0).markCompleted();
            TaskList emptyList = new TaskList("TaskList2");
            JsonWriter writer2 = new JsonWriter("./data/testReaderEmptyTaskList.json");
            writer2.open();
            writer2.write(emptyList);
            writer2.close();
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralTaskList.json");
            writer.open();
            writer.write(tl);
            writer.close();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TaskList tl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTaskList() {


        JsonReader reader = new JsonReader("./data/testReaderEmptyTaskList.json");
        try {
            TaskList tl = reader.read();
            assertEquals("TaskList2", tl.getListName());
            assertEquals(0, tl.remainingTaskNum());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTaskList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTaskList.json");
        try {
            TaskList tl = reader.read();
            assertEquals("TaskList1", tl.getListName());
            List<Task> tasks = tl.getTasks();
            assertEquals(2, tasks.size());
            checkTask(2000, 1,13,"123","123", true, tasks.get(0));
            checkTask(2020,8,31, "1233","123", false, tasks.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}