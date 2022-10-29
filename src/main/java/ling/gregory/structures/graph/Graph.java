package ling.gregory.structures.graph;

import ling.gregory.structures.DeepCloneable;
import ling.gregory.structures.Structure;
import ling.gregory.structures.graph.layout.GraphLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Graph<V extends GraphVertex<V, E>, E extends GraphEdge<V, E>> extends Structure {
  private final GraphCanvas canvas = new GraphCanvas();
  private final ArrayList<V> vertices = new ArrayList<>();
  private final ArrayList<E> edges = new ArrayList<>();

  private GraphLayout<V, E> layout;

  public void setLayoutManager(GraphLayout<V, E> layout) {
    this.layout = layout;
  }

  class GraphCanvas extends JPanel {
    @Override
    public void paintComponent(Graphics graphics) {
      Graphics2D g = (Graphics2D) graphics;
      g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      g.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_SIZE_FIT);
      g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
      Insets insets = getInsets();
      Rectangle bounds = g.getClipBounds();
      bounds.x += insets.left;
      bounds.y += insets.top;
      bounds.width -= insets.left + insets.right;
      bounds.height -= insets.top + insets.bottom;
      if (layout != null) layout.drawGraph(g, bounds, Graph.this);
    }
  }

  public void resetColors(Color color) {
    vertices.forEach((v) -> v.setColor(color));
    edges.forEach((v) -> v.setColor(color));
  }

  public Graph(String title) {
    setBorder(canvas, title);
  }

  public void addVertex(V v) {
    vertices.add(v);
  }

  public void removeVertex(V v) {
    vertices.remove(v);
  }

  public List<V> getVertices() {
    return Collections.unmodifiableList(vertices);
  }

  public void setVertices(List<V> vertices) {
    this.vertices.clear();
    this.vertices.addAll(vertices);
  }

  @SafeVarargs
  public final void setVertices(V... vertices) {
    setVertices(Arrays.asList(vertices));
  }

  public void addEdge(E e) {
    e.getTo().addEdge(e);
    e.getFrom().addEdge(e);
    edges.add(e);
  }

  public void removeEdge(E e) {
    e.getTo().removeEdge(e);
    e.getFrom().removeEdge(e);
    edges.remove(e);
  }

  public void setEdges(List<E> edges) {
    this.edges.clear();
    for (E e : edges) {
      addEdge(e);
    }
  }

  @SafeVarargs
  public final void setEdges(E... edges) {
    setEdges(Arrays.asList(edges));
  }

  public List<E> getEdges() {
    return Collections.unmodifiableList(edges);
  }

  @Override
  public JPanel getPanel() {
    return canvas;
  }



}
