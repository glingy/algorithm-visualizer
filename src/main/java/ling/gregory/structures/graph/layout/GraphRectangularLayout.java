package ling.gregory.structures.graph.layout;

import ling.gregory.structures.graph.Graph;
import ling.gregory.structures.graph.GraphEdge;
import ling.gregory.structures.graph.GraphVertex;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class GraphRectangularLayout<V extends GraphVertex<V, E>, E extends GraphEdge<V, E>> extends GraphLayout<V, E>{
  private final int cols;
  private final int total;

  public GraphRectangularLayout(int cols, int total) {
    this.cols = cols;
    this.total = total;
  }

  private Point getInitialPoint(Rectangle bounds, Graphics2D g) {
    int height = VERTEX_SPACING * (((total + (cols - 1)) / cols) - 1) + 2 * VERTEX_RADIUS;
    int width = VERTEX_SPACING * (cols - 1) + 2 * VERTEX_RADIUS;
    int x = bounds.x + ((bounds.width - width) / 2) + VERTEX_RADIUS;
    int y = bounds.y + ((bounds.height - height) / 2) + VERTEX_RADIUS;
    //g.fillRect(x, y, width, height);
    return new Point(x, y);
  }

  @Override
  public void drawGraph(Graphics2D g, Rectangle bounds, Graph<V, E> graph) {
    Point p = getInitialPoint(bounds, g);

    List<V> vertices = graph.getVertices();
    for (int i = 0; i < vertices.size(); i++) {
      vertices.get(i).setPosition(new Point(p.x + ((i % cols) * VERTEX_SPACING), p.y + ((i / cols) * VERTEX_SPACING)));
      vertices.get(i).draw(g);
    }

    List<E> edges = graph.getEdges();
    for (int i = 0; i < edges.size(); i++) {
      edges.get(i).draw(g);
    }
  }
}
