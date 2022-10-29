package visualizer.structures.graph;

import visualizer.structures.graph.layout.GraphLayout;

import java.awt.*;

public class GraphEdge<V extends GraphVertex<V, E>, E extends GraphEdge<V, E>> {
  private final V from;
  private final V to;
  private Color color = Color.black;

  GraphEdge(V from, V to) {
    this.from = from;
    this.to = to;
  }

  GraphEdge(GraphEdge<V, E> old, V from, V to) {
    this.from = from;
    this.to = to;
    color = old.color;
  }

  public V getFrom() {
    return from;
  }

  public V getTo() {
    return to;
  }

  public void draw(Graphics2D g, GraphLayout<?, ?> layout) {
    g.setColor(color);
    Point f = from.getPosition();
    Point t = to.getPosition();
    double scalar = f.distance(t) / layout.VERTEX_RADIUS;
    int fx = (int) (f.x + ((t.x - f.x) / scalar));
    int fy = (int) (f.y + ((t.y - f.y) / scalar));
    int tx = (int) (t.x + ((f.x - t.x) / scalar));
    int ty = (int) (t.y + ((f.y - t.y) / scalar));

    g.drawLine(fx, fy, tx, ty);
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}
