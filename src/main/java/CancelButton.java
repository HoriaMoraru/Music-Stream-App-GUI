import javax.swing.*;

public class CancelButton extends JButton {
    public CancelButton(LoginGUI frame) {
        GUIHelper.createStyledButton(GUIHelper.BUTTON_TEXT_CANCEL, this);

        setBounds(580, 310, 125, 44);
        addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }
}
