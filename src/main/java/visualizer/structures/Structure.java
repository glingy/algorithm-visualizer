package visualizer.structures;

import java.awt.*;

public abstract class Structure {
  private final String title;

  protected Structure(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public abstract void draw(Graphics2D g, Rectangle bounds);
}
