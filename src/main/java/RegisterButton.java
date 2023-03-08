import javax.swing.*;
import java.util.List;

public class RegisterButton extends JButton {
    public RegisterButton(RegisterGUI frame) {

        GUIHelper.createStyledButton(GUIHelper.BUTTON_TEXT_REGISTER, this);

        setBounds(450, 310, 250, 44);
        var lambdaContext = new Object() {
            TextWithRedirect idInfo = null;
        };
        addActionListener(e -> {
            String userInfo = frame.getTypeField().getText().trim().toUpperCase().replaceAll(" ", "");
            String userName = frame.getUsernameField().getText();
            GUIUser newUser = null;
            Database data = Database.getInstance();
            JPanel panel = frame.getPanel();
            if (!userName.equals(GUIHelper.PLACEHOLDER_TEXT_USERNAME)
                    && !frame.getTypeField().getText().equals(GUIHelper.PLACEHOLDER_TEXT_TYPE)
                    && List.of("MUSICIAN", "PODCASTER", "AUDIOBOOKAUTHOR", "LISTENER").contains(userInfo)) {
                if (lambdaContext.idInfo != null) {
                    GUIHelper.removeAndRepaint(panel, lambdaContext.idInfo);
                }
                switch (userInfo) {
                    case "MUSICIAN":
                        newUser = new Streamer(userName, Streamer.streamerType.valueOf(userInfo));
                        break;
                    case "PODCASTER":
                        newUser = new Streamer(userName, Streamer.streamerType.valueOf(userInfo));
                        break;
                    case "AUDIOBOOKAUTHOR":
                        newUser = new Streamer(userName, Streamer.streamerType.valueOf(userInfo));
                        break;
                    case "LISTENER":
                        newUser = new User(userName);
                        break;
                    default:
                        new TextWithRedirect(GUIHelper.BUTON_REGISTER_PRESS_ERROR, 450, 280, frame::dispose);
                        break;
                }
                if (newUser == null) return;
                if (data.existsDB(newUser.getName())) {
                    lambdaContext.idInfo = new TextWithRedirect(GUIHelper.BUTON_REGISTER_USER_EXISTS, 450, 280, frame::dispose);
                    GUIHelper.addAndRepaint(panel, lambdaContext.idInfo);
                    return;
                }
                addNewUserToDb(newUser);
                lambdaContext.idInfo = new TextWithRedirect(GUIHelper.BUTON_REGISTER_PRESS_OK + newUser.getId(),
                        450, 280, () -> {
                    frame.dispose();
                    GUIHelper.setWrapper(new LoginGUI());
                    GUIHelper.getWrapper().display();
                });
                GUIHelper.addAndRepaint(panel, lambdaContext.idInfo);
            } else {
                if (lambdaContext.idInfo != null) {
                    GUIHelper.removeAndRepaint(panel, lambdaContext.idInfo);
                }
                lambdaContext.idInfo = new TextWithRedirect(GUIHelper.BUTON_REGISTER_PRESS_ERROR, 450, 280, () -> {
                    System.exit(0);
                });
                GUIHelper.addAndRepaint(panel, lambdaContext.idInfo);
            }
        });
    }

    private void addNewUserToDb(GUIUser newUser) {
        Database data = Database.getInstance();
        if (newUser instanceof Streamer) {
            data.addStreamer((Streamer) newUser);
        } else if (newUser instanceof User) {
            data.addUser((User) newUser);
        }
    }
}
