package ling.gregory.structures.graph.layout;

import ling.gregory.structures.graph.Graph;
import ling.gregory.structures.graph.GraphEdge;
import ling.gregory.structures.graph.GraphVertex;

import java.awt.*;

public abstract class GraphLayout<V extends GraphVertex<V, E>, E extends GraphEdge<V, E>> {
  public static final int VERTEX_RADIUS = 20;
  public static final int VERTEX_GAP = 50;
  public static final int VERTEX_SPACING = (2*VERTEX_RADIUS) + VERTEX_GAP;

  public abstract void drawGraph(Graphics2D g, Rectangle bounds, Graph<V, E> graph);
}
