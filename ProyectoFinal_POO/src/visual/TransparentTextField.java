package visual;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JTextField;

public class TransparentTextField extends JTextField {
    private boolean hasFocus;

    public TransparentTextField(int columns) {
        super(columns);
        setOpaque(false);
        hasFocus = false;
        setForeground(Color.WHITE); // Establecer el color de texto a blanco
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Crea una copia del Graphics original para no afectar el estado original
        Graphics2D g2d = (Graphics2D) g.create();

        // Establece la opacidad según el estado del componente
        float opacity = hasFocus ? 0.0f : 0.0f;
        g2d.setComposite(AlphaComposite.SrcOver.derive(opacity));

        // Pinta el componente
        super.paintComponent(g2d);

        g2d.dispose(); // Limpia los recursos de Graphics2D
    }

    @Override
    protected void processFocusEvent(java.awt.event.FocusEvent e) {
        hasFocus = e.getID() == java.awt.event.FocusEvent.FOCUS_GAINED;
        repaint(); // Repinta el componente para actualizar la opacidad
        super.processFocusEvent(e);
    }
}
