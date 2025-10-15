import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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

        titleField = new JTextField("Task #" + (CCR_GUI.taskCount + 1));
        titleField.setForeground(CCR_Colors.TEXT_COLOR);
        titleField.setBackground(CCR_Colors.TEXT_BG);
        titleField.setCaretColor(CCR_Colors.TEXT_COLOR);
        titleField.setBorder(null);
        titleField.setFont(new Font("Consolas", Font.PLAIN, 17));

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
}
