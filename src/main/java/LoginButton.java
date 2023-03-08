import javax.swing.*;

public class LoginButton extends JButton {
    public LoginButton(LoginGUI frame) {
        GUIHelper.createStyledButton(GUIHelper.BUTTON_TEXT_LOGIN, this);
        JPanel panel = frame.panel();
        TextWithRedirect error = new TextWithRedirect(GUIHelper.BUTON_LOGIN_PRESS_ERROR, 450, 280, () -> System.exit(0));
        setBounds(450, 310, 125, 44);

        addActionListener(e -> {
            final String username = frame.getUsernameField().getText();
            final String idInfo = frame.getTypeField().getText();
            int id = -1;
            if (idInfo.matches("^\\d+$"))
                id = Integer.parseInt(frame.getTypeField().getText());
            Database data = Database.getInstance();
            GUIUser user = getGUIUserFromDb(id);

            if (!username.equals(GUIHelper.PLACEHOLDER_TEXT_USERNAME) &&
                    !idInfo.equals(GUIHelper.PLACEHOLDER_TEXT_ID) &&
                    idInfo.matches("^\\d+$") &&
                    data.checkId(id)) {
                if (user instanceof User) {
                    GUIHelper.setWrapper(new UserGUI((User) user));
                    frame.dispose();
                    GUIHelper.getWrapper().display();
                }
                else if (user instanceof Streamer) {
                    GUIHelper.setWrapper(new StreamerGUI((Streamer) user));
                    frame.dispose();
                    GUIHelper.getWrapper().display();
                }
            }
            else {
                panel.remove(error);
                panel.add(error);
                panel.revalidate();
                panel.repaint();
            }
        });
    }

    private <T extends GUIUser> T getGUIUserFromDb(int id) {
        Database data = Database.getInstance();
        if (data.getStreamers().containsKey(id)) {
            return (T) data.getStreamers().get(id);
        }
        else if (data.getUsers().containsKey(id)) {
            return (T) data.getUsers().get(id);
        }
        return null;
    }
}
