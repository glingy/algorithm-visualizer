package visualizer.structures.graph;

public class DefaultGraphEdge extends GraphEdge<DefaultGraphVertex, DefaultGraphEdge> {
  public DefaultGraphEdge(DefaultGraphVertex from, DefaultGraphVertex to) {
    super(from, to);
  }

  public DefaultGraphEdge(DefaultGraphEdge old, DefaultGraphVertex from, DefaultGraphVertex to) {
    super(old, from, to);
  }
}
