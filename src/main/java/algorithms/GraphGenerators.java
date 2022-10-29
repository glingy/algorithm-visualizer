package algorithms;

import visualizer.structures.graph.DefaultGraph;
import visualizer.structures.graph.DefaultGraphEdge;
import visualizer.structures.graph.DefaultGraphVertex;
import visualizer.structures.graph.GraphVertex;

import java.util.List;
import java.util.Random;

public class GraphGenerators {
  public static DefaultGraph random(String title, int count, Random rand, double edgePercentage) {
    DefaultGraph graph = new DefaultGraph(title);
    graph.setVertices(GraphVertex.getSequence(DefaultGraphVertex.class, count));
    List<DefaultGraphVertex> vertices = graph.getVertices();
    for (int i = 0; i < vertices.size(); i++) {
      for (int j = i + 1; j < vertices.size(); j++) {
        if (rand.nextDouble() < edgePercentage) {
          graph.addEdge(new DefaultGraphEdge(vertices.get(i), vertices.get(j)));
        }
      }
    }
    return graph;
  }

  public static DefaultGraph complete(String title, int count) {
    DefaultGraph graph = new DefaultGraph(title);
    graph.setVertices(GraphVertex.getSequence(DefaultGraphVertex.class, count));
    List<DefaultGraphVertex> vertices = graph.getVertices();
    for (int i = 0; i < vertices.size(); i++) {
      for (int j = i + 1; j < vertices.size(); j++) {
        graph.addEdge(new DefaultGraphEdge(vertices.get(i), vertices.get(j)));
      }
    }
    return graph;
  }
}
