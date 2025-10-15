// TODO: Add saved workspaces

public class Cicero{
    public static void main(String[] args){
        CCR_GUI gui = new CCR_GUI();
        // JFrame CCR_Window = gui.CCR_WindowCreate();

        CCR_Colors.CCR_ParseColours("Umbra");

        gui.CCR_WindowCreate();
    }
}
