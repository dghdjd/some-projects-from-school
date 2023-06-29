package ui;

import model.Task;
import model.TaskList;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import persistence.JsonWriter;

/**
 * Creates a new pop-up window to show tasks and some buttons
 */

public class ViewTasks extends JFrame implements ActionListener {

    private TaskList taskList;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/TaskList.json";
    private JList list;
    private DefaultListModel listModel;


    public ViewTasks(TaskList taskList) {
        super("Add Task");
        initiate(taskList);
        List<Task> tasks = this.taskList.getTasks();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        JSplitPane panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        add(panel);

        panel.setDividerLocation(400);

        JScrollPane top = new JScrollPane();
        JPanel bot = new JPanel();
        panel.setTopComponent(top);
        panel.setBottomComponent(bot);
        bot.setLayout(new FlowLayout());



        listModel = new DefaultListModel();
        for (Task t : tasks) {
            listModel.addElement(t.getTime() + "     " + t.getName());
        }
        list = new JList(listModel);
        list.setVisibleRowCount(-1);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        top.setViewportView(list);


        buttonSetup(bot);


        panelSetup();


    }

    //Effects: initiate taskList and jsonWriter
    private void initiate(TaskList taskList) {
        this.taskList = taskList;
        jsonWriter  = new JsonWriter(JSON_STORE);
    }

    //Effects: create buttons, add them to the panel, add and set their action command.

    private void buttonSetup(JPanel bot) {
        JButton b = new JButton("Delete");
        JButton b2 = new JButton("View");
        JButton b3 = new JButton("Save");
        bot.add(b2);
        bot.add(b);
        bot.add(b3);
        b.setActionCommand("Delete");
        b.addActionListener(this);
        b2.setActionCommand("View");
        b2.addActionListener(this);
        b3.setActionCommand("Save");
        b3.addActionListener(this);
    }

    //Effects: set up panels so that the elements can be seen

    private void panelSetup() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Delete")) {
            if (list.getSelectedIndex() != -1) {
                int selected = list.getSelectedIndex();
                listModel.remove(selected);
                this.taskList.getTasks().remove(selected);
                displayError("alert","The task deleted successfully");
            }
        } else if (e.getActionCommand().equals("View")) {
            if (list.getSelectedIndex() != -1) {
                new TaskDetail(taskList.getTasks().get(list.getSelectedIndex()));
                getToolkit().beep();
            }
        } else if (e.getActionCommand().equals("Save")) {
            saveTaskList();
            displayError("alert", "Tasks saved successfully");

        }
    }

    //Modifies: this
    //Effects : save the task list
    //          throws FileNotFoundException if can't find the file in the given path
    private void saveTaskList() {
        try {
            jsonWriter.open();
            jsonWriter.write(taskList);
            jsonWriter.close();
            System.out.println("Saved " + taskList.getListName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
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

    public static void main(String[] arg) {
        new ViewTasks(new TaskList("abc"));

    }
}
