import javax.swing.*;

public class ButtonFactory {
    public static JButton createButton(String type, JFrame frame) {
        switch (type) {
            case "CANCEL":
                return new CancelButton((LoginGUI) frame);
            case "LOGIN":
                return new LoginButton((LoginGUI) frame);
            case "REGISTER":
                return new RegisterButton((RegisterGUI) frame);
            case "LOGOUT":
                return new LogoutButton((UserGUI) frame);
            case "LOGOUTSTREAMER":
                return new LogoutButton((StreamerGUI) frame);
            case "RUNTESTS":
                return new RunTestsButton((UserGUI) frame);
            case "ADDSTREAM":
                return new AddStreamButton((StreamerGUI) frame);
            case "LIST":
                return new ListButton((UserGUI) frame);
            default:
                return null;
        }
    }
}
