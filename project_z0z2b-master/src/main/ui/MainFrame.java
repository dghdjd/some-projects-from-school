package ui;

import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.TaskList;


/**
 * A main window for the application
 */
public class MainFrame extends JFrame implements ActionListener {

    private JLabel topLabel = new JLabel();
    private TaskList tasklist;
    private JsonReader jsonreader;
    private static final String JSON_STORE = "./data/TaskList.json";
    private JButton b1;
    private JButton b2;
    private JButton b3;

    public MainFrame() {
        super("TaskList");
        tasklist = new TaskList("TaskList1");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));

        JSplitPane panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        add(panel);
        panel.setDividerLocation(300);
        JPanel top = new JPanel();
        JPanel bot = new JPanel();
        panel.setTopComponent(top);
        panel.setBottomComponent(bot);

        bot.setLayout(new FlowLayout());
        top.setLayout(new FlowLayout());

        createButtons();
        addButtonsToPanel(bot, b1, b2, b3);

        topLabel.setIcon(new ImageIcon("./data/th.jpg"));
        top.add(topLabel);


        setAndAddCommand(b1, b2, b3);

        setupPanel();
    }

    //Effects : sets up the panel so that elements will be displayed properly
    private void setupPanel() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //Modifies: this
    //Effects : Creates three buttons
    private void createButtons() {
        b1 = new JButton("View Tasks");
        b2 = new JButton("Add Tasks");
        b3 = new JButton("Load Tasks");
    }


    //Effects : takes in three buttons and add them to the panel
    private void addButtonsToPanel(JPanel bot, JButton b1, JButton b2, JButton b3) {
        bot.add(b1);
        bot.add(b2);
        bot.add(b3);
    }

    //Effects : sets the action command for the buttons
    private void setAndAddCommand(JButton b1, JButton b2, JButton b3) {
        b1.setActionCommand("View Tasks");
        b1.addActionListener(this);
        b2.setActionCommand("Add Task");
        b2.addActionListener(this);
        b3.setActionCommand("Load Tasks");
        b3.addActionListener(this);
    }

    //This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Load Tasks")) {
            try {
                jsonreader = new JsonReader(JSON_STORE);
                tasklist = jsonreader.read();

                new Music("alert");
                new Message("Tasks loaded successfully");
            } catch (IOException ex) {
                new Music("error_alert");
                new Message("Failed to load the file");

            }
        } else if (e.getActionCommand().equals("Add Task")) {
            new AddTasks(tasklist);
        } else if (e.getActionCommand().equals("View Tasks")) {
            new ViewTasks(tasklist);
        }

    }


}
