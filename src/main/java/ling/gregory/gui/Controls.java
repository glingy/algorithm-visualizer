package ling.gregory.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.BorderFactory.*;

public class Controls {
  private final JPanel root = new JPanel();
  private final Button next;
  private final Button previous;

  public Controls(ControlsListener listener) {
    root.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    next = new Button("▶", KeyEvent.VK_RIGHT);
    previous = new Button("◀", KeyEvent.VK_LEFT);

    next.setForeground(new Color(0x1FA200));
    previous.setForeground(new Color(0x1FA200));

    previous.addActionListener((e) -> listener.previous());
    next.addActionListener((e) -> listener.next());
    c.fill = GridBagConstraints.BOTH;

    c.gridx = 2;
    root.add(next, c);

    c.gridx = 1;
    root.add(previous, c);

    JPanel panel = new JPanel();
    c.gridx = 1;
    c.weightx = 0;
    root.add(panel, c);
  }

  public void setEnabled(boolean enabled) {
    next.setEnabled(enabled);
    previous.setEnabled(enabled);
  }

  public JPanel getPanel() {
    return root;
  }
}
