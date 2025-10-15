import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

// Layout and todo box creation handling
public class CCR_GUI{
    // Tasks currently saved
    public static int taskCount = 0;

    // Frame contents
    private JPanel contentPanel;

    // Holds the todo panels
    private JPanel todoContainer;

    // Scroll panel
    private JScrollPane scrollPane;

    // To drag the window from the custom top bar
    private int mouseX, mouseY;

    // Window creation
    public JFrame CCR_WindowCreate(){
        JFrame frame = new JFrame("Cicero");
        frame.setSize(400, 650);
        frame.setUndecorated(true); // Remove the native title bar
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPanel = new JPanel(null); // Absolute positioning
        contentPanel.setBackground(CCR_Colors.BG_DARK);
        frame.setContentPane(contentPanel);

        // Custom top bar
        JPanel titleBar = new JPanel();
        titleBar.setBounds(0, 0, 400, 30);
        titleBar.setBackground(CCR_Colors.TOP_BAR_COLOR);
        titleBar.setLayout(null);

        // Dragging handle
        titleBar.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mousePressed(java.awt.event.MouseEvent evt){
                mouseX = evt.getX();
                mouseY = evt.getY();
            }
        });
        titleBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter(){
            public void mouseDragged(java.awt.event.MouseEvent evt){
                frame.setLocation(evt.getXOnScreen() - mouseX, evt.getYOnScreen() - mouseY);
            }
        });

        // Load and scale icon
        ImageIcon icon = new ImageIcon(getClass().getResource("/_assets/icon.png"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        
        // Add icon label to title bar
        JLabel iconLabel = new JLabel(scaledIcon);
        iconLabel.setBounds(5, 3, 24, 24);
        titleBar.add(iconLabel);

        // Window name
        JLabel winName = new JLabel("C i c e r o");
        winName.setBounds(45, 2, 300, 30);
        winName.setForeground(CCR_Colors.TEXT_COLOR);
        winName.setFont(new Font("Consolas", Font.ITALIC, 20));
        titleBar.add(winName);
        
        // Close button
        JButton close = new JButton("");
        close.setBounds(370, 0, 30, 30);
        close.setBorder(null);
        close.setBackground(CCR_Colors.CLS_BUTTON_BG);
        close.setForeground(Color.WHITE);
        close.addActionListener(e -> System.exit(0));
        titleBar.add(close);
        
        todoContainer = new JPanel();
        todoContainer.setLayout(new BoxLayout(todoContainer, BoxLayout.Y_AXIS));
        todoContainer.setBackground(CCR_Colors.BG_DARK);
        
        scrollPane = new JScrollPane(todoContainer);
        scrollPane.setBounds(5, 70, 375, 530);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPanel.add(scrollPane);
        
        CCR_GUIButtonsCreate(frame);
        
        contentPanel.add(titleBar); // Add the custom top bar
        
        frame.setIconImage(scaledImg);
        frame.setLayout(null);
        frame.setVisible(true);

        return frame;
    }

    // Create the basic menu buttons
    public void CCR_GUIButtonsCreate(JFrame frame){
        JButton addTaskButton = styledButton("Add task", 5, 35);
        addTaskButton.setBounds(5,35,100, 25);
        addTaskButton.addActionListener(e -> {
            CCR_TodoBox todoBox = new CCR_TodoBox(frame, todoContainer);

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

    // Stylised button constructor
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
