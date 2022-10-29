package ling.gregory.structures;

import javax.swing.*;
import java.io.Serializable;

public abstract class Structure implements DeepCloneable {
  protected void setBorder(JPanel panel, String title) {
    panel.setBorder(BorderFactory.createTitledBorder(title));
  }

  public abstract JPanel getPanel();
}
