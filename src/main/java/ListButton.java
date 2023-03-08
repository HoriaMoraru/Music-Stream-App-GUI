import javax.swing.*;

public class ListButton extends JButton {
    public ListButton(UserGUI frame) {
        GUIHelper.createStyledButton(GUIHelper.BUTON_TEXT_LIST_STREAMS, this);
        setBounds(500, 250, 200, 40);
        addActionListener(e -> {
            frame.getScrollPane().setVisible(true);
        });
    }
}
