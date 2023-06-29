package persistence;

import model.Task;
import model.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            TaskList tl = new TaskList("My TaskList");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTaskList() {
        try {
            TaskList tl = new TaskList("My TaskList1");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTaskList.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTaskList.json");
            tl = reader.read();
            assertEquals("My TaskList1", tl.getListName());
            assertEquals(0, tl.remainingTaskNum());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTaskList() {
        try {
            TaskList tl = new TaskList("TaskList1");
            tl.insertTask(new Task(2000,12,12,"123","123"));
            tl.insertTask(new Task(2020,12,12,"1233","123"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTaskList.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTaskList.json");
            tl = reader.read();
            assertEquals("TaskList1", tl.getListName());
            List<Task> tasks = tl.getTasks();
            assertEquals(2, tasks.size());
            checkTask(2000, 12,12,"123","123", false, tasks.get(0));
            checkTask(2020,12,12, "1233","123", false, tasks.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}