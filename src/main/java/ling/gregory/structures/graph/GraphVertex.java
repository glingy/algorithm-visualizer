package ling.gregory.structures.graph;

import ling.gregory.structures.graph.layout.GraphLayout;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphVertex<V extends GraphVertex<V, E>, E extends GraphEdge<V, E>> {
  private ArrayList<E> edges = new ArrayList<>();
  private String name;
  private Color color = Color.black;
  private Point position;

  GraphVertex(String name) {
    this.name = name;
  }

  public void draw(Graphics2D g) {
    g.setColor(color);
    g.drawOval(position.x - GraphLayout.VERTEX_RADIUS, position.y - GraphLayout.VERTEX_RADIUS, GraphLayout.VERTEX_RADIUS * 2, GraphLayout.VERTEX_RADIUS * 2);
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
}
