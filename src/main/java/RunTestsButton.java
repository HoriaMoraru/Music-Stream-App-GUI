import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class RunTestsButton extends JButton {

    public RunTestsButton(UserGUI frame) {
        GUIHelper.createStyledButton(GUIHelper.BUTTON_TEXT_RUN_TESTS, this);

        setBounds(20, 400, 200, 40);
        addActionListener(e -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);

            System.out.flush();

            System.setOut(old);
            System.out.println(baos);
        });
    }
}
