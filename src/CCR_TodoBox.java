import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class CCR_TodoBox extends JComponent{
    /* Panel where all todo-box components exist, save positions here */
    private JPanel todoPanel;

    /* Task name */
    private String taskName;
    
    /* Task description */
    private String taskDescription;

    private JPanel taskPanel;

    /* When pressed it will delete the task and remove it from the layout */
    private JButton deleteTaskButton;

    /* Constructor */
    public CCR_TodoBox(JFrame frame, int yPosition){
        // We need to find the correct position
        // in the layout
        this.setBounds(0, yPosition, 200, 200);
        this.setLayout(null);


        this.todoPanel = new JPanel();
        this.todoPanel.setLayout(null);
        todoPanel.setLayout(null);
        
        /* FIXME: Paths, problems with icon imports */
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/assets/todo_box_panel.png"));
        JLabel bgLabel = new JLabel(bgIcon);
        bgLabel.setBounds(0, 0, 200, 200);  // Same size as the panel
        
        todoPanel.add(bgLabel);
        this.todoPanel.setBounds(0, 0, 200, 200);

        this.taskName = "New Task";
        this.taskDescription = "Task description";

        this.deleteTaskButton = new JButton(new ImageIcon(getClass().getResource("/assets/delete_icon.png"))); // Add an image icon
        deleteTaskButton.setOpaque(false);     // Makes button background transparent
        deleteTaskButton.setContentAreaFilled(false); // Prevents default button fill
        deleteTaskButton.setBorderPainted(false);     // Removes the border
        deleteTaskButton.setFocusPainted(false);      // Removes focus outline when clicked
        this.deleteTaskButton.setBounds(this.todoPanel.getWidth() - 30, this.todoPanel.getY(), 20, 20);
        this.todoPanel.add(this.deleteTaskButton);

        this.add(this.todoPanel);
    }
}
