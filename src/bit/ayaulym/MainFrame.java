package bit.ayaulym;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton button;
    private JLabel label;
    private JTextField textField;
    private JTextField textField2;
    private JComboBox agesBox;
    private Integer[] ages = new Integer[100];
    private JTextArea area;

    public MainFrame() {
        for (Integer i = 0; i < 100; i++) {
            ages[i] = i;
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BITLAB Application");
        setSize(500, 500);
        setLayout(null);

        label = new JLabel("Name:");
        label.setSize(300, 30);
        label.setLocation(100, 75);
        add(label);

        textField = new JTextField();
        textField.setSize(200, 30);
        textField.setLocation(170, 75);
        add(textField);

        label = new JLabel("Phone:");
        label.setSize(300, 30);
        label.setLocation(100, 120);
        add(label);

        textField2 = new JTextField();
        textField2.setSize(200, 30);
        textField2.setLocation(170, 120);
        add(textField2);

        label = new JLabel("Age:");
        label.setSize(300, 30);
        label.setLocation(100, 175);
        add(label);

        agesBox = new JComboBox(ages);
        agesBox.setSize(200, 30);
        agesBox.setLocation(170, 175);
        add(agesBox);

        area = new JTextArea();
        area.setSize(300, 200);
        area.setLocation(100, 300);
        area.setEditable(false);
        add(area);
        button = new JButton("ADD CONTACT");
        button.setSize(300, 30);
        button.setLocation(100, 250);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                String text2 = textField2.getText();
                Integer age = (Integer) agesBox.getSelectedItem();
                if (!text.equals("")) {
                    area.append(text + " - " + text2 + " - " + age + " years" + "\n");
                    textField.setText("");
                    textField2.setText("");
                    agesBox.setSelectedIndex(0);
                }
            }
        });
        add(button);
    }
}