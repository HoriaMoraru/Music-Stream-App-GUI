import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextWithRedirect extends JLabel {

    public TextWithRedirect(String text, int xPos, int yPos, Runnable action) {
        super(text);
        setForeground(GUIHelper.COLOR_OUTLINE);
        setFont(GUIHelper.FONT_EXTRAS);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Dimension rightSize = getPreferredSize();
        setBounds(xPos, yPos, rightSize.width, rightSize.height);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run();
            }
        });
    }
}
