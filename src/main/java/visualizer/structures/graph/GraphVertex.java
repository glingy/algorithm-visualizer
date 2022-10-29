package visualizer.structures.graph;

import visualizer.Tuple;
import visualizer.structures.graph.layout.GraphLayout;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GraphVertex<V extends GraphVertex<V, E>, E extends GraphEdge<V, E>> {
  private ArrayList<E> edges = new ArrayList<>();
  private String name;
  private Color color = Color.black;
  private Point position;

  GraphVertex(String name) {
    this.name = name;
  }

  GraphVertex(GraphVertex<V, E> v) {
    name = v.name;
    color = v.color;
    edges = new ArrayList<>(v.edges.size());
    position = null;
  }

  public void draw(Graphics2D g, GraphLayout<?,?> layout) {
    g.setColor(color);
    g.drawOval(position.x - layout.VERTEX_RADIUS, position.y - layout.VERTEX_RADIUS, layout.VERTEX_RADIUS * 2, layout.VERTEX_RADIUS * 2);
    Rectangle2D nameBounds = g.getFontMetrics().getStringBounds(name, g);
    g.drawString(name, (int) (position.x - nameBounds.getCenterX() + 1), (int) (position.y - nameBounds.getCenterY() + 1));
  }

  // Don't call this except from the Graph.
  void addEdge(E edge) {
    edges.add(edge);
  }

  public void removeEdge(E e) {
    edges.remove(e);
  }

  public List<E> getEdges() {
    return Collections.unmodifiableList(edges);
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static <V extends GraphVertex<V, ?>> List<V> getSequence(Class<V> v, int count) {
    ArrayList<V> vertices = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      try {
        vertices.add(v.getConstructor(String.class).newInstance(Integer.toString(i)));
      } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }

    return vertices;
  }

  public Point getPosition() {
    return position;
  }

  public void setPosition(Point position) {
    this.position = position;
  }

  public List<Tuple<V, E>> getNeighbors() {
    return edges.stream().map((e) -> new Tuple<>(e.getTo() == this ? e.getFrom() : e.getTo(), e)).toList();
  }
}
