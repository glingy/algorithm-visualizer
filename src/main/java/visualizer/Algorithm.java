package visualizer;

public abstract class Algorithm {
  protected Visualizer.Manager manager;

  public void setManager(Visualizer.Manager manager) {
    this.manager = manager;
  }

  public abstract void run() throws InterruptedException;
  public abstract void setup();
}
