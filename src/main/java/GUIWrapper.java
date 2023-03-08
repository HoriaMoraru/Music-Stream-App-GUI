public class GUIWrapper {

    private GUI gui;

    public GUIWrapper(GUI gui) {
        this.gui = gui;
    }

    public void display() {
        gui.display();
    }
}
