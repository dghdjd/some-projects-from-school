//package ui;
//
//// The console user interface for reminders application
//
//import model.CompletedTaskException;
//import model.Task;
//import model.TaskList;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.sql.SQLOutput;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//// Reminders application
//public class Reminders {
//    private static final String JSON_STORE = "./data/TaskList.json";
//    private static final String file_name  = "TaskList1";
//    private Scanner input;
//    private TaskList tasklist;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//
//    //EFFECTS: runs the reminders application
//    public Reminders() throws FileNotFoundException {
//        runReminders();
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runReminders() {
//        boolean keepGoing = true;
//        String command = null;
//
//        init();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//
//
//            }
//        }
//
//
//        System.out.println("\nGoodbye!");
//    }
//
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\ta -> Add a task");
//        System.out.println("\td -> Delete a task");
//        System.out.println("\tm -> Mark a task as completed");
//        System.out.println("\tc -> Check the number of remaining tasks");
//        System.out.println("\ts -> Save the task list");
//        System.out.println("\tl -> load the task list");
//        System.out.println("\tq -> quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("a")) {
//            addTask();
//        } else if (command.equals("d")) {
//            deleteTask();
//        } else if (command.equals("m")) {
//            markTaskCompleted();
//        } else if (command.equals("c")) {
//            checkRemainingTaskNum();
//        } else if (command.equals("s")) {
//            saveTaskList();
//        } else if (command.equals("l")) {
//            loadTaskList();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes TaskList
//    private void init() {
//        tasklist = new TaskList("TaskList1");
//        input = new Scanner(System.in);
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        try {
//            new MainFrame();
//        } catch (IOException e) {
//            //
//        }
//    }
//
//
//
//    // MODIFIES: this
//    // EFFECTS: creates and adds a new task
//    private void addTask() {
//        try {
//            System.out.println("\nPlease enter the due time (year) of the task");
//            int input1 = input.nextInt();
//            System.out.println("\nPlease enter the due time (month) of the task");
//            int input2 = input.nextInt();
//            System.out.println("\nPlease enter the due time (day) of the task");
//            int input3 = input.nextInt();
//            System.out.println("\nPlease enter the name of the task");
//            String name = input.next();
//            System.out.println("\nPlease enter the description of the task");
//            String des = input.next();
//            Task task = new Task(input1, input2, input3, name, des);
//            if (tasklist.insertTask(task)) {
//                System.out.println("\nThe task is added successfully");
//            } else {
//                System.out.println("This task is already added");
//                addTask();
//            }
//        } catch (InputMismatchException i) {
//            System.out.println("Invalid Input");
//            input.next();
//            addTask();
//        }
//
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  delete the task with given name
//    private void deleteTask() {
//        System.out.println("\nPlease enter the name of the task you want to delete");
//        String input1 = input.next();
//        if (tasklist.removeTask(input1)) {
//            System.out.println("\nThe task is deleted");
//        } else {
//            System.out.println("\nTask does not exist");
//        }
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  change the task status as completed
//    private void markTaskCompleted() {
//        System.out.println("\nPlease enter the name of the task you want to mark as completed");
//        String input1 = input.next();
//        try {
//            if (tasklist.markTaskCompleted(input1)) {
//                System.out.println("\nThe task has been completed");
//            } else {
//                System.out.println("\nTask does not exist");
//            }
//        } catch (CompletedTaskException e) {
//
//            System.out.println("\nTask is completed already!");
//        }
//    }
//
//
//
//
//    // EFFECTS: Prints the number of remaining tasks
//    private void checkRemainingTaskNum() {
//        System.out.printf("Number of remaining tasks: %d\n", tasklist.remainingTaskNum());
//    }
//
//
//    // EFFECTS: saves the task list to file
//    private void saveTaskList() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(tasklist);
//            jsonWriter.close();
//            System.out.println("Saved " + tasklist.getListName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads task list from file
//    private void loadTaskList() {
//        try {
//            tasklist = jsonReader.read();
//            System.out.println("Loaded " + tasklist.getListName() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
