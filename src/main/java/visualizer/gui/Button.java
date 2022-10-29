package visualizer.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.BorderFactory.*;

public class Button extends JButton {
  void release() {
    setBorder(createCompoundBorder(createRaisedBevelBorder(), createEmptyBorder(7, 10, 7, 10)));
    setBackground(new Color(0xEEEEEE));
  }

  void press() {
    setBorder(createCompoundBorder(createLoweredBevelBorder(), createEmptyBorder(7, 10, 7, 10)));
    setBackground(Color.red);
    setBackground(new Color(0xD9D9D9));
  }

  public Button(String text, int keyCode) {
    this(text);
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0, false), "pressed");
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0, true), "released");
    getActionMap().put("pressed", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!Button.this.isEnabled()) return;
        press();
      }
    });
    getActionMap().put("released", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!Button.this.isEnabled()) return;
        release();
        doClick();
      }
    });
  }

  public Button(String text) {
    super(text);
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    setFocusPainted(true);
    setOpaque(true);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (!Button.this.isEnabled()) return;
        press();
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        if (!Button.this.isEnabled()) return;
        release();
      }
    });
    release();
  }

  @Override
  public void setEnabled(boolean b) {
    super.setEnabled(b);
    if (!isEnabled()) {
      release();
    }
  }
}
