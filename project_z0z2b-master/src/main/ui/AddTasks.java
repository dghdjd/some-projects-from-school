package ui;

import model.Task;
import model.TaskList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Creates a new pop-up window that takes user's input to create a task and save it
 */

public class AddTasks extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField date;
    private JTextField name;
    private JTextField description;
    private TaskList tasklist;
    private JButton    bottom;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    private static final String JSON_STORE = "./data/TaskList.json";


    public AddTasks(TaskList taskList) {

        super("Add Task");
        this.tasklist = taskList;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(350, 300));
        setLayout(new FlowLayout());

        createPanelsAndAdd();

        createLabelsAndFields();

        bottom = new JButton("Add");
        bottom.setPreferredSize(new Dimension(100,100));
        panel4.add(bottom,BorderLayout.SOUTH);

        bottom.setActionCommand("Add");
        bottom.addActionListener(this);
        setupPanel();
    }


    //Modifies: this
    //Effects : creates labels and text fields and add them to the panels
    private void createLabelsAndFields() {
        panel1.add(label = new JLabel("due date (yyyy/mm/dd)"),BorderLayout.WEST);
        panel1.add(date = new JTextField(8),BorderLayout.EAST);
        panel2.add(label = new JLabel("Task name"),BorderLayout.WEST);
        panel2.add(name = new JTextField(8),BorderLayout.EAST);
        panel3.add(label = new JLabel("Description"),BorderLayout.WEST);
        panel3.add(description = new JTextField(15),BorderLayout.EAST);
    }

    //Effects : sets up the panel so that the elements can be displayed properly
    private void setupPanel() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //Modifies: this
    //Effects : creates panels and add them to the frame
    private void createPanelsAndAdd() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        add(panel1,BorderLayout.CENTER);
        add(panel2,BorderLayout.CENTER);
        add(panel3,BorderLayout.CENTER);
        add(panel4,BorderLayout.CENTER);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add") && checkDate(date.getText())) {
            if (name.getText().equals("")) {
                displayError("error_alert", "Empty task name");
            } else {
                try {
                    int year = Integer.parseInt(date.getText().substring(0, 4));
                    int month = Integer.parseInt(date.getText().substring(5, 7));
                    int day = Integer.parseInt(date.getText().substring(8));
                    String name = this.name.getText();
                    String description = this.description.getText();
                    Task task = new Task(year, month, day, name, description);
                    if (tasklist.insertTask(task)) {
                        displayError("alert", "The task added successfully");
                    } else {
                        displayError("error_alert", "This task is already added");
                    }
                } catch (NumberFormatException exception) {
                    displayError("error_alert", "Invalid date input");
                }
            }
        }

    }


    //Effects: checks the user input for date is valid, false if invalid
    private Boolean checkDate(String date) {
        if (date.length() != 10) {
            displayError("error_alert", "Invalid date input");
            return false;
        } else if (Integer.parseInt(date.substring(0, 4)) < 2020) {
            displayError("error_alert", "Invalid date input");
            return false;
        } else if ((Integer.parseInt(date.substring(5, 7)) > 12) || (Integer.parseInt(date.substring(5, 7)) < 1)) {
            displayError("error_alert", "Invalid date input");
            return false;
        } else if ((Integer.parseInt(date.substring(8)) > 31) || (Integer.parseInt(date.substring(8)) < 1)) {
            displayError("error_alert", "Invalid date input");
            return false;
        }
        return true;
    }


    //Effects : creates a new message window with sound effect
    private void displayError(String sound, String error) {
        if (sound.equals("alert")) {
            new Music(sound);
            new Message(error);
        } else {
            new Music(sound);
            new Message("<html><font color=\"red\">" + error + "</font></html>");

        }
    }

}
