package visualizer;

import visualizer.gui.Controls;
import visualizer.gui.ControlsListener;
import visualizer.gui.LocatedStructure;
import visualizer.gui.StructureRenderer;
import ling.gregory.structures.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Gui {
  private final JFrame window = new JFrame("Algorithm Visualizer");
  private final JPanel elementPanel = new JPanel();
  private final Controls controls;

  public Gui(ControlsListener controlsListener) {
    controls = new Controls(controlsListener);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void drawComponents(List<LocatedStructure> elements) {
    elementPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    for (LocatedStructure e : elements) {
      c.gridx = e.pos.x;
      c.gridy = e.pos.y;
      c.gridwidth = e.pos.width;
      c.gridheight = e.pos.height;
      c.weighty = e.pos.height;
      c.weightx = e.pos.width;
      elementPanel.add(new StructureRenderer(e.structure), c);
    }
  }

  void layoutAndShow() {
    Container pane = window.getContentPane();
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;

    //drawComponents(initialState);
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    pane.add(elementPanel, c);

    c.gridy = 1;
    c.weighty = 0;
    pane.add(controls.getPanel(), c);

    //Display the window.
    window.pack();
    window.setSize(1000, 800);
    window.setVisible(true);
  }


  public void setNextEnabled(boolean enabled) {
    controls.setNextEnabled(enabled);
  }

  public void setPreviousEnabled(boolean enabled) {
    controls.setPreviousEnabled(enabled);
  }

  void setState(List<LocatedStructure> state) {
    System.out.println("Set State");
    elementPanel.removeAll();
    drawComponents(state);
    elementPanel.revalidate();
    elementPanel.repaint();
  }
}
