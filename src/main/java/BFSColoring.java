import ling.gregory.Algorithm;
import ling.gregory.Tuple;
import ling.gregory.Visualizer;
import ling.gregory.structures.graph.DefaultGraph;
import ling.gregory.structures.graph.DefaultGraphEdge;
import ling.gregory.structures.graph.DefaultGraphVertex;
import ling.gregory.structures.graph.layout.GraphCircularLayout;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class BFSColoring extends Algorithm {
  private final Random rand = new Random();
  private final DefaultGraph graph = GraphGenerators.random("BFS Graph", 10, rand, 0.5);
  private final Color[] colors;

  public BFSColoring(Color a, Color b) {
    this.colors = new Color[]{ a, b };
  }


  public void setup() {
    manager.add(graph, 0, 0);

    graph.setLayoutManager(new GraphCircularLayout<>(10));
  }

  public void run() throws InterruptedException {
    List<DefaultGraphVertex> vertices = graph.getVertices();

    DefaultGraphVertex root = vertices.get(0);
    root.setColor(colors[0]);

    Queue<DefaultGraphVertex> q = new LinkedList<>();
    q.add(root);

    manager.step();

    while (!q.isEmpty()) {
      DefaultGraphVertex v = q.remove();
      Color other = colors[0] == v.getColor() ? colors[1] : colors[0];

      List<Tuple<DefaultGraphVertex, DefaultGraphEdge>> neighbors = v.getNeighbors();
      for (Tuple<DefaultGraphVertex, DefaultGraphEdge> t : neighbors) {
        DefaultGraphVertex n = t.first;
        if (n.getColor() == Color.black) {
          n.setColor(other);
          t.second.setColor(Color.red);
          q.add(n);
          manager.step();
          t.second.setColor(Color.orange);
        } else if (n.getColor() == v.getColor()) {
          n.setColor(Color.red);
          System.out.println("Error: Not 2-colorable.");
          t.second.setColor(Color.red);
          manager.step();
          return;
        }
      }
    }
  }




  public static void main(String[] args) {
    Visualizer viz = new Visualizer(new BFSColoring(Color.blue, Color.green));
    viz.run();
  }
}
