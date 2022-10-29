package ling.gregory;

import ling.gregory.gui.ControlsListener;
import ling.gregory.gui.LocatedStructure;
import ling.gregory.gui.StructureRenderer;
import ling.gregory.structures.Structure;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Visualizer implements ControlsListener {
  private final Gui gui = new Gui(this);
  private final Algorithm algorithm;
  private final Semaphore stepSemaphore = new Semaphore(0);
  private final Manager manager = new Manager();
  private final ArrayList<LocatedStructure> state = new ArrayList<>();
  private final ArrayList<List<LocatedStructure>> steps = new ArrayList<>();
  private int current = -1;
  private static final int STATUS_RUNNING = 0;
  private static final int STATUS_PAUSED = 1;
  private static final int STATUS_DONE = 2;
  private int status = STATUS_RUNNING;
  private Thread algoThread;

  public Visualizer(Algorithm algorithm) {
    this.algorithm = algorithm;
    algorithm.setManager(manager);
  }

  private void runAlgorithm() {
    try {
      System.out.println("Algo running");
      System.out.flush();
      manager.step();
      algorithm.run();
      status = STATUS_DONE;
      updateEnabled();
    } catch (InterruptedException ignored) {}
  }

  public void run() {
    algorithm.setup();
    gui.layoutAndShow();

    status = STATUS_RUNNING;

    algoThread = new Thread(this::runAlgorithm);
    algoThread.start();
  }

  public class Manager {
    public void step() throws InterruptedException {
      EventQueue.invokeLater(() -> {
        status = STATUS_PAUSED;
        commit();
        updateEnabled();
      });
      System.out.println("Waiting for Freeze Semaphore");
      stepSemaphore.acquire();
      System.out.println("Acquired Freeze Semaphore");
    }

    public <T extends Structure> T add(T structure, int x, int y) {
      return add(structure, x, y, 1, 1);
    }

    public <T extends Structure> T add(T structure, int x, int y, int width, int height) {
      state.add(new LocatedStructure(structure, new Rectangle(x, y, width, height)));
      return structure;
    }
  }

  public void commit() {
    steps.add(state.stream().map(LocatedStructure::new).toList());
    current++;
    restore();
  }

  public void restore() {
    gui.setState(steps.get(current));
  }

  private void updateEnabled() {
    gui.setNextEnabled(current != steps.size() - 1 || status == STATUS_PAUSED);
    gui.setPreviousEnabled(current != 0 && steps.size() != 0);
  }

  @Override
  public void next() {
    if (current == steps.size() - 1) {
      if (status == STATUS_PAUSED) {
        status = STATUS_RUNNING;
        stepSemaphore.release();
      }
      updateEnabled();
      return;
    }

    current++;
    updateEnabled();
    restore();
  }

  @Override
  public void previous() {
    if (current == 0 || steps.size() == 0) {
      updateEnabled();
      return;
    }

    current = current - 1;
    updateEnabled();
    restore();
  }
}
