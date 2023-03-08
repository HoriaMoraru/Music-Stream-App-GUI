import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URI;

public class RegisterGUI extends JFrame implements GUI {
    private JButton registerButton;
    private JLabel titleLabel;
    private MyTextField usernameField;
    private MyTextField typeField;
    private JPanel panel;

    public RegisterGUI() {
        super("Register");
        createView();
        fit(this);
    }

    public void display() {
        setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public MyTextField getUsernameField() {
        return usernameField;
    }

    public MyTextField getTypeField() {
        return typeField;
    }

    private void createView() {

        panel = createBackground(800, 400, this);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(GUIHelper.COLOR_OUTLINE);
        panel.add(separator);
        separator.setBounds(310, 80, 1, 240);

        usernameField = new MyTextField();

        usernameField.setBounds(450, 100, 255, 44);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals(GUIHelper.PLACEHOLDER_TEXT_USERNAME)) {
                    usernameField.setText("");
                }
                usernameField.setBorderColor(GUIHelper.COLOR_INTERACTIVE);
                usernameField.setForeground(Color.white);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().equals("")) {
                    usernameField.setText(GUIHelper.PLACEHOLDER_TEXT_USERNAME);
                }
                usernameField.setBorderColor(GUIHelper.COLOR_OUTLINE);
                usernameField.setForeground(GUIHelper.COLOR_OUTLINE);
            }
        });
        panel.add(usernameField);

        typeField = new MyTextField();

        typeField.setBounds(450, 180, 255, 44);
        typeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (typeField.getText().equals(GUIHelper.PLACEHOLDER_TEXT_TYPE)) {
                    typeField.setText("");
                }
                typeField.setBorderColor(GUIHelper.COLOR_INTERACTIVE);
                typeField.setForeground(Color.white);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (typeField.getText().isEmpty()) {
                    typeField.setText(GUIHelper.PLACEHOLDER_TEXT_TYPE);
                }
                typeField.setBorderColor(GUIHelper.COLOR_OUTLINE);
                typeField.setForeground(GUIHelper.COLOR_OUTLINE);
            }
        });

        panel.add(typeField);

        titleLabel = new JLabel(GUIHelper.TEXT_TITLE);
        titleLabel.setForeground(GUIHelper.MAGENTA);
        titleLabel.setFont(GUIHelper.FONT_TITLE);
        titleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        titleLabel.setBounds(15, 40, 1000, 200);

        panel.add(titleLabel);

        registerButton = ButtonFactory.createButton("REGISTER",this);

        panel.add(registerButton);

        panel.add(new TextWithRedirect(GUIHelper.BUTON_TEXT_ABOUT_PROJECT,15, 240, () -> {
            openLink(URI.create("https://github.com/ACS-POO-2CB/proiect2023-HoriaMoraru/blob/main/README.md"));
        }));

        panel.add(new TextWithRedirect(GUIHelper.BUTTON_TEXT_LOGIN, 450, 240, () -> {
            dispose();
            GUIHelper.setWrapper(new LoginGUI());
            GUIHelper.getWrapper().display();
        }));

        panel.add(new TextWithRedirect(GUIHelper.BUTON_TEXT_ABOUT, 650, 240, () -> {
            openLink(URI.create("https://www.linkedin.com/in/moraru-horia-0b6a00260/"));
        }));

        add(panel);
    }
}
