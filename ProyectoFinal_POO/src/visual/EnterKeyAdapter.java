package visual;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;


class EnterKeyAdapter extends KeyAdapter {
 private JTextField nextField;

 public EnterKeyAdapter(JTextField nextField) {
     this.nextField = nextField;
 }

 @Override
 public void keyPressed(KeyEvent e) {
     if (e.getKeyCode() == KeyEvent.VK_ENTER) {
         nextField.requestFocusInWindow();
     }
 }
}
