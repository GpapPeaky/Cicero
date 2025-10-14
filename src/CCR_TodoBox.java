import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javafx.scene.text.Font;

import java.awt.Color;

import javax.swing.BorderFactory;

public class CCR_TodoBox extends JPanel{
    /* Panel where all todo-box components exist, save positions here */
    public JTextField titleField;
    public JTextArea descriptionArea;

    public CCR_TodoBox(JFrame parentFrame, int yPosition){
        // Panel properties
        setLayout(null);
        setBounds(5, yPosition, parentFrame.getWidth() - 15, 125);
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 255), 2, true));
        setBackground(new Color(75, 75, 75, 255));
        setVisible(true);

        // Title text field
        titleField = new JTextField();
        titleField.setBounds(getWidth() / 2 - (getWidth() / 3) / 2, 5, getWidth() / 3, 19);
        titleField.setForeground(new Color(0, 0, 0, 255));
        titleField.setBackground(new Color(255, 255, 255, 0));
        titleField.setBorder(BorderFactory.createLineBorder(getBackground()));
        add(titleField);

        // Description text area
        descriptionArea = new JTextArea("• "); // start with a bullet
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBackground(new Color(45, 45, 45));
        descriptionArea.setCaretColor(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setBounds(getWidth() / 2 - (getWidth() - 20) / 2, 30, getWidth() - 20, 80);
        add(scrollPane);
    }
}
