import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;

// Panel with the todo info
public class CCR_TodoBox extends JPanel{

    // Title of the todo 
    private JTextField titleField;

    // Description of the todo
    private JTextArea descriptionArea;

    // Deletes a task
    private JButton deleteButton;

    // Priority system
    private int priority = 1; // 1 = low, 2 = medium, 3 = high
    private JLabel priorityLabel;
    private JButton increasePriorityButton;
    private JButton decreasePriorityButton;

    // Todo box constructor
    public CCR_TodoBox(JFrame parentFrame, JPanel container){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(parentFrame.getWidth() - 50, 125));
        setMaximumSize(new Dimension(parentFrame.getWidth() - 50, 125));
        setBackground(CCR_Colors.PANEL_BG);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5), // margin around each box
            BorderFactory.createLineBorder(CCR_Colors.ACCENT, 1, true)
        ));

        // Title field and task delete button
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(CCR_Colors.PANEL_BG);
        topBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        titleField = new JTextField("Task_" + (CCR_GUI.taskCount + 1));
        titleField.setForeground(CCR_Colors.TEXT_COLOR);
        titleField.setBackground(CCR_Colors.TEXT_BG);
        titleField.setCaretColor(CCR_Colors.TEXT_COLOR);
        titleField.setBorder(null);
        titleField.setFont(new Font("Consolas", Font.PLAIN, 17));

        // Priority label
        priorityLabel = new JLabel("Priority: " + priorityName(priority));
        priorityLabel.setBounds(10 + getWidth() / 3 + 5, 10, 100, 20);
        priorityLabel.setForeground(CCR_Colors.TEXT_COLOR);
        topBar.add(priorityLabel);

        // Increase priority button
        increasePriorityButton = new JButton("▲");
        increasePriorityButton.setBounds(10 + getWidth() / 3 + 110, 10, 45, 20);
        stylePriorityButton(increasePriorityButton);
        increasePriorityButton.addActionListener(e -> changePriority(1));
        topBar. add(increasePriorityButton);

        // Decrease priority button
        decreasePriorityButton = new JButton("▼");
        decreasePriorityButton.setBounds(10 + getWidth() / 3 + 160, 10, 45, 20);
        stylePriorityButton(decreasePriorityButton);
        decreasePriorityButton.addActionListener(e -> changePriority(-1));
        topBar.add(decreasePriorityButton);

        deleteButton = new JButton("");
        deleteButton.setBackground(CCR_Colors.CLS_BUTTON_BG);
        deleteButton.setForeground(CCR_Colors.TEXT_COLOR);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 1,0));
        deleteButton.setFont(new Font("Consolas", Font.BOLD, 12));
        deleteButton.setPreferredSize(new Dimension(35, 25));
        deleteButton.addActionListener(e -> {
            container.remove(this);
            container.revalidate();
            container.repaint();

            CCR_GUI.taskCount--; // Lower the task count
        });

        topBar.add(titleField, BorderLayout.CENTER);
        topBar.add(deleteButton, BorderLayout.EAST);

        // Description area
        descriptionArea = new JTextArea("");
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setForeground(CCR_Colors.TEXT_COLOR);
        descriptionArea.setBackground(CCR_Colors.TEXT_BG);
        descriptionArea.setCaretColor(CCR_Colors.TEXT_COLOR);
        descriptionArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        descriptionArea.setBorder(null);

        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setBounds(10, 40, getWidth() - 20, 75);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setBackground(CCR_Colors.TEXT_BG);
        scrollPane.getViewport().setBackground(CCR_Colors.TEXT_BG);
        
        // Add up the components
        add(topBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void stylePriorityButton(JButton btn){
        btn.setBackground(CCR_Colors.BUTTON_BG);
        btn.setForeground(CCR_Colors.TEXT_COLOR);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBorder(null);
    }

    private void changePriority(int delta){
        priority += delta;
        if (priority < 1) priority = 1;
        if (priority > 3) priority = 3;
        priorityLabel.setForeground(new Color(0x2ECC71)); // start as Low = green
        priorityLabel.setText(priorityName(priority));
    
        // Update label color
        switch(priority){
            case 1: // Low
                priorityLabel.setForeground(new Color(0x2ECC71)); // green
                break;
            case 2: // Medium
                priorityLabel.setForeground(new Color(0xE67E22)); // orange
                break;
            case 3: // High
                priorityLabel.setForeground(new Color(0xE74C3C)); // red
                break;
        }
    }


    private String priorityName(int p){
        switch(p){
            case 1: return "Low";
            case 2: return "Medium";
            case 3: return "High";
            default: return "Unknown";
        }
    }
}
