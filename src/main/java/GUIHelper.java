import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUIHelper {

    private GUIHelper() {}

    public static final Font FONT_GENERAL_UI = new Font("Segoe UI", Font.PLAIN, 20);
    public static final Font FONT_EXTRAS = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_TITLE = new Font("Serif", Font.BOLD | Font.ITALIC, 28);

    public static final Font FONT_USER_TITLE = new Font("Serif", Font.BOLD | Font.ITALIC, 24);

    public static final Color COLOR_OUTLINE = new Color(103, 112, 120);
    public static final Color COLOR_BACKGROUND = new Color(37, 51, 61);
    public static final Color COLOR_INTERACTIVE = new Color(108, 216, 158);
    public static final Color OFFWHITE = new Color(255, 255, 255, 200);
    public static final Color MAGENTA = new Color(195, 0, 195);

    public static final String BUTTON_TEXT_LOGIN = "Login";
    public static final String BUTTON_TEXT_REGISTER = "Register";
    public static final String BUTTON_TEXT_CANCEL = "Cancel";
    public static final String BUTON_TEXT_ABOUT = "About me";
    public static final String BUTON_TEXT_LOGOUT = "Logout";
    public static final String BUTTON_TEXT_RUN_TESTS = "Run tests";
    public static final String BUTON_TEXT_ABOUT_PROJECT = "More about the project";

    public static final String BUTON_TEXT_ADD_STREAM = "Add stream";

    public static final String BUTON_TEXT_LIST_STREAMS = "Show me some music";

    public static final String BUTON_REGISTER_USER_EXISTS = "User already exists";
    public static final String TEXT_TITLE = "<html>" +
                                            "Henezzia, <br> People supercharing <br> muzzzzic events " +
                                            "</html>";
    public static final String BUTON_REGISTER_PRESS_OK = "Your account has been created with id: ";
    public static final String BUTON_REGISTER_PRESS_ERROR = "Please fill in all fields correctly";
    public static final String BUTON_LOGIN_PRESS_ERROR = "Login failed, please try again";

    public static final String PLACEHOLDER_TEXT_USERNAME = "What is your name?";
    public static final String PLACEHOLDER_TEXT_ID = "Your id?";
    public static final String PLACEHOLDER_TEXT_TYPE = "What`s your thing?";

    public static final int ROUNDNESS = 9;

    private static GUIWrapper wrapper;

    public static void setWrapper(GUI gui) {
        wrapper = new GUIWrapper(gui);
    }

    public static GUIWrapper getWrapper() {
        return wrapper;
    }

    public static Graphics2D get2dGraphics(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>() {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        return g2;
    }

    public static void createStyledButton(String text, JButton button) {
        final Color[] buttonColors = new Color[]{GUIHelper.COLOR_INTERACTIVE, Color.white};
        button.add(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = GUIHelper.get2dGraphics(g);
                super.paintComponent(g2);

                Insets insets = getInsets();
                int width = getWidth() - insets.left - insets.right;
                int height = getHeight() - insets.top - insets.bottom;
                g2.setColor(buttonColors[0]);
                g2.fillRoundRect(insets.left, insets.top, width, height, GUIHelper.ROUNDNESS, GUIHelper.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(GUIHelper.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(text)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(GUIHelper.FONT_GENERAL_UI);
                g2.setColor(buttonColors[1]);
                g2.drawString(text, x2, y2);
            }
        });
        button.setBounds(450, 310, 125, 44);
        button.setBackground(GUIHelper.COLOR_BACKGROUND);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(null);
    }

    public static void addAndRepaint(JPanel panel, TextWithRedirect lambdaContext) {
        panel.add(lambdaContext);
        panel.revalidate();
        panel.repaint();
    }

    public static void removeAndRepaint(JPanel panel, TextWithRedirect lambdaContext) {
        panel.remove(lambdaContext);
        panel.revalidate();
        panel.repaint();
    }
}
