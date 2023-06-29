package ui;

import javax.swing.*;
import java.awt.*;

/**
 * A pop-up window with the given string
 */

public class Message extends JFrame {
    private JLabel label;


    public Message(String str) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300, 70));
        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        label = new JLabel(str);
        panel.add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


}









