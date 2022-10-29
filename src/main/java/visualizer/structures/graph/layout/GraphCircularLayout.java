package visualizer.structures.graph.layout;

import visualizer.structures.graph.Graph;
import visualizer.structures.graph.GraphEdge;
import visualizer.structures.graph.GraphVertex;

import java.awt.*;
import java.util.List;

public class GraphCircularLayout<V extends GraphVertex<V, E>, E extends GraphEdge<V, E>> extends GraphLayout<V, E>{
  private final int total;

  public GraphCircularLayout(GraphCircularLayout<V, E> old) {
    super(old);
    total = old.total;
  }

  public GraphCircularLayout(int total) {
    this.total = total;
  }

  private double getAngle() {
    return Math.PI - ((Math.PI * (total - 2)) / total);
  }

  private double getRadius() {
    return (VERTEX_SPACING / Math.sin(getAngle() / 2)) / 2;
  }

  @Override
  public void drawGraph(Graphics2D g, Rectangle bounds, Graph<V, E> graph) {
    int x = (int) bounds.getCenterX();
    int y = (int) bounds.getCenterY();
    double angle = getAngle();
    double radius = getRadius();

    java.util.List<V> vertices = graph.getVertices();
    for (int i = 0; i < vertices.size(); i++) {
      vertices.get(i).setPosition(new Point(x + (int) (Math.sin(angle * i) * radius), y + (int) (Math.cos(angle * i) * radius)));
      vertices.get(i).draw(g, this);
    }

    List<E> edges = graph.getEdges();
    for (int i = 0; i < edges.size(); i++) {
      edges.get(i).draw(g, this);
    }
  }
}
