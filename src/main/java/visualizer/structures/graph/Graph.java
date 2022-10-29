package visualizer.structures.graph;

import visualizer.Util;
import visualizer.structures.Structure;
import visualizer.structures.graph.layout.GraphLayout;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

public abstract class Graph<V extends GraphVertex<V, E>, E extends GraphEdge<V, E>> extends Structure {
  private final ArrayList<V> vertices = new ArrayList<>();
  private final ArrayList<E> edges = new ArrayList<>();

  private GraphLayout<V, E> layout;

  public void setLayoutManager(GraphLayout<V, E> layout) {
    this.layout = layout;
  }

  @Override
  public void draw(Graphics2D g, Rectangle bounds) {
    if (layout != null) layout.drawGraph(g, bounds, Graph.this);
  }

  public void resetColors(Color color) {
    vertices.forEach((v) -> v.setColor(color));
    edges.forEach((v) -> v.setColor(color));
  }

  public Graph(String title) {
    super(title);
  }

  public void addVertex(V v) {
    vertices.add(v);
  }

  public void removeVertex(V v) {
    vertices.remove(v);
  }

  public List<V> getVertices() {
    return Collections.unmodifiableList(vertices);
  }

  public void setVertices(List<V> vertices) {
    this.vertices.clear();
    this.vertices.addAll(vertices);
  }

  @SafeVarargs
  public final void setVertices(V... vertices) {
    setVertices(Arrays.asList(vertices));
  }

  public void addEdge(E e) {
    e.getTo().addEdge(e);
    e.getFrom().addEdge(e);
    edges.add(e);
  }

  public void removeEdge(E e) {
    e.getTo().removeEdge(e);
    e.getFrom().removeEdge(e);
    edges.remove(e);
  }

  public void setEdges(List<E> edges) {
    this.edges.clear();
    for (E e : edges) {
      addEdge(e);
    }
  }

  @SafeVarargs
  public final void setEdges(E... edges) {
    setEdges(Arrays.asList(edges));
  }

  public List<E> getEdges() {
    return Collections.unmodifiableList(edges);
  }

  @SuppressWarnings("unchecked")
  public Graph(Graph<V, E> g) {
    super(g.getTitle());
    layout = Util.copy(g.layout);
    if (g.vertices.isEmpty()) return;

    try {
      IdentityHashMap<V, V> vertexMap = new IdentityHashMap<>();
      for (int i = 0; i < g.vertices.size(); i++) {
        V v = Util.copy(g.vertices.get(i));
        addVertex(v);
        vertexMap.put(g.vertices.get(i), v);
      }

      E e0 = g.edges.get(0);
      Class<?> eClass = e0.getClass();
      Class<?> vClass = e0.getFrom().getClass();
      Constructor<?> eCtor = eClass.getConstructor(eClass, vClass, vClass);

      for (int i = 0; i < g.edges.size(); i++) {
        E e = g.edges.get(i);
        addEdge((E) eCtor.newInstance(e, vertexMap.get(e.getFrom()), vertexMap.get(e.getTo())));
      }

    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
      throw new RuntimeException(ex);
    }
  }
}
