import ling.gregory.Algorithm;
import ling.gregory.Visualizer;
import ling.gregory.structures.graph.DefaultGraph;
import ling.gregory.structures.graph.DefaultGraphEdge;
import ling.gregory.structures.graph.DefaultGraphVertex;
import ling.gregory.structures.graph.layout.GraphCircularLayout;
import ling.gregory.structures.graph.layout.GraphRectangularLayout;

import java.awt.*;
import java.util.List;

public class Example extends Algorithm {
  private final DefaultGraph graph1 = new DefaultGraph("Graph 1");
  private final DefaultGraph graph2 = new DefaultGraph("Graph 2");
  private final DefaultGraph graph3 = new DefaultGraph("Graph 3");

  public void setup() {
    manager.add(graph1, 0, 0);
    manager.add(graph2, 1, 0, 2, 2);
    manager.add(graph3, 0, 1);

    graph1.setLayoutManager(new GraphRectangularLayout<>(3, 10));
    graph1.setVertices(DefaultGraphVertex.getSequence(DefaultGraphVertex.class, 10));
    List<DefaultGraphVertex> vertices = graph1.getVertices();
    graph1.setEdges(
       new DefaultGraphEdge(vertices.get(0), vertices.get(5)),
       new DefaultGraphEdge(vertices.get(1), vertices.get(5)),
       new DefaultGraphEdge(vertices.get(2), vertices.get(5)),
       new DefaultGraphEdge(vertices.get(3), vertices.get(5)),
       new DefaultGraphEdge(vertices.get(4), vertices.get(5)),
       new DefaultGraphEdge(vertices.get(6), vertices.get(5))
    );

    graph2.setLayoutManager(new GraphCircularLayout<>(10));
    graph2.setVertices(DefaultGraphVertex.getSequence(DefaultGraphVertex.class, 10));
    List<DefaultGraphVertex> vertices2 = graph2.getVertices();

    for (int i = 0; i < vertices2.size(); i++) {
      for (int j = i + 1; j < vertices2.size(); j++) {
        graph2.addEdge(new DefaultGraphEdge(vertices2.get(i), vertices2.get(j)));
      }
    }
  }

  public void run() throws InterruptedException {
    List<DefaultGraphVertex> vertices = graph2.getVertices();
    for (int i = 0; i < vertices.size(); i++) {
      graph2.getVertices().get(i).setColor(Color.red);
      graph2.getVertices().get(i).getEdges().forEach((e) -> e.setColor(Color.red));
      manager.step();
      graph2.resetColors(Color.black);
    }
  }




  public static void main(String[] args) {
    Visualizer viz = new Visualizer(new Example());
    viz.run();
  }
}
