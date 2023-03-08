import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MyTextField extends JTextField {

    private transient Shape shape;
    private Color borderColor = GUIHelper.COLOR_INTERACTIVE;

    public MyTextField() {
        setOpaque(false);
        setBackground(GUIHelper.COLOR_BACKGROUND);
        setForeground(Color.white);
        setCaretColor(Color.white);
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setMargin(new Insets(2, 10, 2, 2));
        setHorizontalAlignment(SwingConstants.LEFT);
        setFont(GUIHelper.FONT_GENERAL_UI);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = GUIHelper.get2dGraphics(g);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, GUIHelper.ROUNDNESS, GUIHelper.ROUNDNESS);
        super.paintComponent(g2);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = GUIHelper.get2dGraphics(g);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, GUIHelper.ROUNDNESS, GUIHelper.ROUNDNESS);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, GUIHelper.ROUNDNESS, GUIHelper.ROUNDNESS);
        }
        return shape.contains(x, y);
    }

    public void setBorderColor(Color color) {
        borderColor = color;
        repaint();
    }
}
