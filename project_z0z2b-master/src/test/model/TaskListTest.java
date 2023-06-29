package model;

// Tests for the constructor and methods of Task and TaskList

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private TaskList tasklist;
    private Task t1, t2, t3;
    private final String name = "TaskList1";

    @BeforeEach
    public  void setUp() {
        tasklist = new TaskList(name);
        t1 = new Task(2020, 12, 14, "task1", "Do task1");
        t2 = new Task(2200, 1, 26, "task2", "Do task2");
        t3 = new Task(2320, 5, 1, "task3", "Do task3");

    }
    @Test
    void testConstructor() {
        assertTrue(t1.checkTime(2020,12,14));
        assertFalse(t1. checkTime( 1111, 1111, 1111));
        assertFalse(t1. checkTime( 2020, 1111, 1111));
        assertFalse(t1. checkTime( 1111, 1111, 14));
        assertFalse(t1. checkTime( 2020, 12, 1111));
        assertFalse(t1. checkTime( 2222, 12, 14));

        assertEquals("task1", t1.getName());
        assertEquals("Do task1", t1.getDescription());
        assertFalse(t1.getStatus());
        assertEquals(name, tasklist.getListName());

    }

    @Test
    void testGetTasks() {
        TaskList taskList1 = new TaskList("TaskList2");
        taskList1.insertTask(t1);
        tasklist.insertTask(t1);
        assertEquals(taskList1.getTasks(),tasklist.getTasks());
        tasklist.insertTask(t2);
        assertNotEquals(tasklist.getTasks(), taskList1.getTasks());
    }


    @Test
    public void testInsertTask() {
        assertEquals(0, tasklist.remainingTaskNum());
        assertTrue(tasklist.insertTask(t1));
        assertTrue(t1.checkTime(2020,12,14));
        assertTrue(tasklist.insertTask(t2));
        assertTrue(tasklist.insertTask(t3));
        assertEquals(3, tasklist.remainingTaskNum());
        assertFalse(tasklist.insertTask(t3));
    }

    @Test
    void testInsertSameNameAndTimeTask() {
        Task t1 = new Task(2020,02,20,"name","");
        Task t2 = new Task(2020,02,20,"name","");
        Task t3 = new Task(2020,02,20,"name1","");
        Task t4 = new Task(2020,12,20,"name","");
        assertTrue(tasklist.insertTask(t1));
        assertFalse(tasklist.insertTask(t1));
        assertFalse(tasklist.insertTask(t2));
        assertTrue(tasklist.insertTask(t3));
        assertTrue(tasklist.insertTask(t4));
    }


    @Test
    public void testRemainingTasksNum() {
        assertEquals(0, tasklist.remainingTaskNum());
        tasklist.insertTask(t1);
        tasklist.insertTask(t2);
        tasklist.insertTask(t3);
        assertEquals(3, tasklist.remainingTaskNum());


    }

    @Test
    public void testRemoveTasks() {

        assertEquals(0, tasklist.remainingTaskNum());
        assertFalse(tasklist.removeTask(t1.getName()));
        tasklist.insertTask(t1);
        tasklist.insertTask(t2);
        tasklist.insertTask(t3);
        assertEquals(3, tasklist.remainingTaskNum());
        assertTrue(tasklist.removeTask(t1.getName()));
        assertTrue(tasklist.removeTask(t3.getName()));
        assertEquals(1, tasklist.remainingTaskNum());
        assertFalse(tasklist.removeTask(t1.getName()));

    }

    @Test
    public void testMarkTestCompleted() {
        assertEquals(0, tasklist.remainingTaskNum());
        try {
            assertFalse(tasklist.markTaskCompleted(t1.getName()));
            assertTrue(tasklist.insertTask(t1));
            assertTrue(tasklist.insertTask(t2));
            assertFalse(t1.getStatus());
            assertTrue(tasklist.markTaskCompleted("task1"));
            assertTrue(t1.getStatus());
            assertFalse(t2.getStatus());
            assertFalse(tasklist.markTaskCompleted(t3.getName()));

        } catch (CompletedTaskException e) {
            fail();
        }



    }
    @Test
    public void testMarkTestCompletedWithException() {
        assertEquals(0, tasklist.remainingTaskNum());
        try {
            assertFalse(tasklist.markTaskCompleted(t1.getName()));
            assertTrue(tasklist.insertTask(t1));
            assertFalse(t1.getStatus());
            assertTrue(tasklist.markTaskCompleted("task1"));
            tasklist.markTaskCompleted("task1");

        } catch (CompletedTaskException e) {
            //
        }



    }


}