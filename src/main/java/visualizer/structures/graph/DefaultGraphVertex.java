package visualizer.structures.graph;

public class DefaultGraphVertex extends GraphVertex<DefaultGraphVertex, DefaultGraphEdge> {
  public DefaultGraphVertex(String name) {
    super(name);
  }

  public DefaultGraphVertex(DefaultGraphVertex v) {
    super(v);
  }
}
