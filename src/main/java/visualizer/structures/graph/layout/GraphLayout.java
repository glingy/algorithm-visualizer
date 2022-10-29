package visualizer.structures.graph.layout;

import visualizer.structures.graph.Graph;
import visualizer.structures.graph.GraphEdge;
import visualizer.structures.graph.GraphVertex;

import java.awt.*;

public abstract class GraphLayout<V extends GraphVertex<V, E>, E extends GraphEdge<V, E>> {
  public int VERTEX_RADIUS = 20;
  public int VERTEX_GAP = 50;
  public int VERTEX_SPACING = (2*VERTEX_RADIUS) + VERTEX_GAP;

  public GraphLayout() {}

  public GraphLayout(GraphLayout<V, E> old) {
    VERTEX_RADIUS = old.VERTEX_RADIUS;
    VERTEX_GAP = old.VERTEX_GAP;
    VERTEX_SPACING = old.VERTEX_SPACING;
  }

  public abstract void drawGraph(Graphics2D g, Rectangle bounds, Graph<V, E> graph);
}
