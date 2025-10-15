import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Font;

// Layout and todo box creation handling
public class CCR_GUI{
    // Tasks currently saved
    private int taskCount = 0;

    // Frame contents
    private JPanel contentPanel;

    // Holds the todo panels
    private JPanel todoContainer;

    // Scroll panel
    private JScrollPane scrollPane;

    public JFrame CCR_WindowCreate(){
        JFrame frame = new JFrame("Cicero");
        frame.setSize(400, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPanel = new JPanel(null); // Absolute positioning
        contentPanel.setBackground(CCR_Colors.BG_DARK);
        frame.setContentPane(contentPanel);

        todoContainer = new JPanel();
        todoContainer.setLayout(new BoxLayout(todoContainer, BoxLayout.Y_AXIS));
        todoContainer.setBackground(CCR_Colors.BG_DARK);


        scrollPane = new JScrollPane(todoContainer);
        scrollPane.setBounds(5, 70, 375, 530);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPanel.add(scrollPane);

        CCR_GUIButtonsCreate(frame);

        frame.setLayout(null);
        frame.setVisible(true);

        return frame;
    }

    public void CCR_GUIButtonsCreate(JFrame frame){
        JButton addTaskButton = styledButton("Add task", 5, 5);
        addTaskButton.setBounds(5,5,100, 45);
        addTaskButton.addActionListener(e -> {
            CCR_TodoBox todoBox = new CCR_TodoBox(frame, taskCount, todoContainer);

            // No need, we can use an empty border for padding
            // todoContainer.add(Box.createVerticalStrut(5)); // 10px padding between each task
            
            todoContainer.add(todoBox);
            todoContainer.revalidate();
            todoContainer.repaint();

            taskCount++;
        });
        contentPanel.add(addTaskButton);

        // contentPanel.add(styledButton("Load workspace", 110, 5));
        // contentPanel.add(styledButton("Save workspace", 245, 5));

        return;
    }

    private JButton styledButton(String text, int x, int y){
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 25);
        button.setBackground(CCR_Colors.BUTTON_BG);
        button.setForeground(CCR_Colors.TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(CCR_Colors.ACCENT, 1, true));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return button;
    }
}
