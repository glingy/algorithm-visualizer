package ling.gregory;

import ling.gregory.gui.Controls;
import ling.gregory.gui.ControlsListener;
import ling.gregory.structures.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

class GuiElement {
  Structure structure;
  Rectangle pos;

  public GuiElement(Structure structure, Rectangle rect) {
    this.structure = structure;
    this.pos = rect;
  }
}

public class Gui {
  private final ArrayList<GuiElement> elements = new ArrayList<>();
  private final JFrame window = new JFrame("Algorithm Visualizer");
  private final Controls controls = new Controls(new ControlsListener() {
    @Override
    public void next() {
      controls.setEnabled(false);
      freezeSem.release();
    }

    @Override
    public void previous() {
      System.out.println("Previous");
    }
  });
  private final Algorithm algorithm;
  private final Semaphore freezeSem = new Semaphore(0);
  private Thread algoThread;

  public Gui(Algorithm algorithm) {
    this.algorithm = algorithm;
    algorithm.setGui(this);

    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void drawComponents(Container pane) {
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    for (GuiElement e : elements) {
      c.gridx = e.pos.x;
      c.gridy = e.pos.y;
      c.gridwidth = e.pos.width;
      c.gridheight = e.pos.height;
      c.weighty = e.pos.height;
      c.weightx = e.pos.width;
      pane.add(e.structure.getPanel(), c);
    }
  }

  private void layoutAndShow() {
    Container pane = window.getContentPane();
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;

    JPanel componentPanel = new JPanel();
    drawComponents(componentPanel);
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    pane.add(componentPanel, c);

    c.gridy = 1;
    c.weighty = 0;
    pane.add(controls.getPanel(), c);

    //Display the window.
    window.pack();
    window.setSize(1000, 800);
    window.setVisible(true);
  }

  private void runAlgorithm() {
    try {
      System.out.println("Algo running");
      System.out.flush();
      algorithm.run();
      System.out.println("Algo done");
    } catch (InterruptedException e) {}
  }

  public void run() {
    algorithm.setup();
    layoutAndShow();

    algoThread = new Thread(this::runAlgorithm);
    algoThread.start();
  }

  public void step() throws InterruptedException {
    EventQueue.invokeLater(() -> {
      window.repaint();
      controls.setEnabled(true);
    });
    System.out.println("Waiting for Freeze Semaphore");
    freezeSem.acquire();
    System.out.println("Acquired Freeze Semaphore");
  }

  public <T extends Structure> T add(T structure, int x, int y) {
    return add(structure, x, y, 1, 1);
  }

  public <T extends Structure> T add(T structure, int x, int y, int width, int height) {
    elements.add(new GuiElement(structure, new Rectangle(x, y, width, height)));
    return structure;
  }
}
