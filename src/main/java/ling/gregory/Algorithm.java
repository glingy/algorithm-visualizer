package ling.gregory;

public abstract class Algorithm {
  protected Gui gui;

  public void setGui(Gui gui) {
    this.gui = gui;
  }

  public abstract void run() throws InterruptedException;
  public abstract void setup();
}
