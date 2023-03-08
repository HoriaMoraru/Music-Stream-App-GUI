import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;

public interface GUI {

    void display();

    default void openLink(URI link) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(link);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    default void fit(JFrame frame) {
        frame.pack();
        frame.toFront();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    default JPanel createBackground(int width, int height, JFrame frame) {
        frame.setUndecorated(true);
        Dimension rightSize = new Dimension(width, height);
        JPanel panel = new JPanel();
        panel.setSize(rightSize);
        panel.setPreferredSize(rightSize);
        panel.setBackground(GUIHelper.COLOR_BACKGROUND);
        panel.setLayout(null);

        return panel;
    }
}
