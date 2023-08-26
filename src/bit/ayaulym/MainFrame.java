package bit.ayaulym;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JTextField textField;
    private final JTextField textField2;
    private final JComboBox<Integer> agesBox;
    private final DefaultTableModel tableModel;
    private final JTable table;

    public MainFrame() {
        Integer[] ages = new Integer[100];
        for (int i = 0; i < 100; i++) {
            ages[i] = i;
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Simple phone book application");
        setSize(500, 500);
        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel contactPanel = new JPanel(new GridBagLayout());
        contactPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Name:");
        textField = new JTextField();
        nameLabel.setLabelFor(textField);
        contactPanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        JLabel phoneLabel = new JLabel("Phone:");
        textField2 = new JTextField();
        phoneLabel.setLabelFor(textField2);
        contactPanel.add(phoneLabel, gbc);

        gbc.gridy = 2;
        JLabel ageLabel = new JLabel("Age:");
        agesBox = new JComboBox<>(ages);
        ageLabel.setLabelFor(agesBox);
        contactPanel.add(ageLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contactPanel.add(textField, gbc);

        gbc.gridy = 1;
        contactPanel.add(textField2, gbc);

        gbc.gridy = 2;
        contactPanel.add(agesBox, gbc);

        add(contactPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Name");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Age");

        table = new JTable(tableModel);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = getButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel getButtonPanel() {
        JButton addButton = getAddButton();

        JButton deleteButton = new JButton("DELETE CONTACT");
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {

                tableModel.removeRow(selectedRow);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        return buttonPanel;
    }

    private JButton getAddButton() {
        JButton addButton = new JButton("ADD CONTACT");

        addButton.addActionListener(e -> {
            String name = textField.getText();
            String phone = textField2.getText();
            Integer age = (Integer) agesBox.getSelectedItem();
            if (!name.isEmpty()) {
                tableModel.addRow(new Object[]{name, phone, age});
                textField.setText("");
                textField2.setText("");
                agesBox.setSelectedIndex(0);
            }
        });

        return addButton;
    }
}
