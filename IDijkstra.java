import java.util.List;

public interface IDijkstra<NodeType,EdgeType extends Number> extends GraphADT<NodeType, EdgeType>{

  public CS400Graph<NodeType, EdgeType> getMinSpanningTree(NodeType start);

  public double getMinSpanningTreeCost(NodeType start);
}


