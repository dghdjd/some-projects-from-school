package ui;


import model.Task;
import model.TaskList;



import javax.swing.*;
import java.awt.*;


/**
 * Creates a new pop-up window to show the specific elements of a task
 */
public class TaskDetail extends JFrame {
    private JLabel label;
    private JLabel date;
    private JLabel name;
    private JLabel description;
    private JLabel status;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private String string;



    public TaskDetail(Task task) {
        super("Add Task");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(320, 250));

        setLayout(new FlowLayout());

        createPanelsAndAdd();

        panel1.add(label = new JLabel(displayMessage("due date :")), BorderLayout.WEST);
        panel1.add(date = new JLabel(task.getTime()), BorderLayout.EAST);
        panel2.add(label = new JLabel(displayMessage("Task name :")), BorderLayout.WEST);
        panel2.add(name = new JLabel(task.getName()), BorderLayout.EAST);
        panel3.add(label = new JLabel(displayMessage("Description :")), BorderLayout.WEST);
        panel3.add(description = new JLabel(
                checkStringLength("<html>" + task.getDescription() + "</html>")), BorderLayout.EAST);
        panel4.add(label = new JLabel(displayMessage("Completed? :")), BorderLayout.WEST);
        panel4.add(status = new JLabel(task.getStatus().toString()));


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    //Effects :displays a message in red color with the given string
    private String displayMessage(String string) {
        return "<html><font color=\"blue\">" + string + "</font></html>";
    }

    //Modifies : this
    //Effects  : creates panels and add them to the frame
    private void createPanelsAndAdd() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.CENTER);
        add(panel4, BorderLayout.CENTER);
    }

    //Modifies : this
    //Effects  : makes the string goes to next row when the length hits index 35,
    //           43 includes "<html><font color=\"color">" ........ "<\font></html>"
    private String checkStringLength(String string) {
        if (string.length() >= 43) {
            this.string = string;
            for (int i = 1; i <= (string.length() / 35); i++) {
                this.string = this.string.substring(0, i * 35) + "<br>" + this.string.substring(i * 35);
            }
            return this.string;
        }
        return string;
    }

}

