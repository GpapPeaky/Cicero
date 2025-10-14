import javax.swing.JButton;
import javax.swing.JFrame;

public class CCR_GUI{
    public int taskCount = 0;

    public JFrame CCR_WindowCreate(){
        JFrame frame = new JFrame("Cicero");
        frame.setSize(400, 650);
        frame.setResizable(false);

        /* Add components */
        CCR_GUIButtonsCreate(frame);

        frame.setLayout(null);
        frame.setVisible(true);

        return frame;
    }

    public void CCR_GUIButtonsCreate(JFrame frame){
        JButton addTaskButton = new JButton("Add task");
        addTaskButton.setBounds(0,0,100,25);
        addTaskButton.addActionListener(e -> {
            System.out.println("Add task button pressed");
            CCR_TodoBox todoBox = new CCR_TodoBox(frame, taskCount * 130 + 30);
            
            frame.add(todoBox);
            frame.repaint();

            taskCount++;
        });
        frame.add(addTaskButton);

        JButton loadWorkspaceButton = new JButton("Load workspace");
        loadWorkspaceButton.setBounds(100,0,100,25);
        frame.add(loadWorkspaceButton);

        JButton saveWorspaceButton = new JButton("Save workspace");
        saveWorspaceButton.setBounds(200,0,100,25);
        frame.add(saveWorspaceButton);

        JButton changeDisplayButton = new JButton("Grid display");
        changeDisplayButton.setBounds(300,0,100,25);
        frame.add(changeDisplayButton);

        return;
    }
}
