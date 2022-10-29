package ling.gregory.structures.graph;



public class DefaultGraph extends Graph<DefaultGraphVertex, DefaultGraphEdge> {

  public DefaultGraph(String title) {
    super(title);
  }

  public DefaultGraph(DefaultGraph graph) {
    super(graph);
  }
}
