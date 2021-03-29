package Search;

import java.util.ArrayList;
import java.util.List;

import Graph.AdjacencyListGraph;
import Graph.Edge;
import Graph.Node;

public interface Search {
	public default <N, E> ArrayList<Edge<N, E>> shortestPath(AdjacencyListGraph<N, E> graph, Node<N, E> start,
			Node<N, E> goal) {
				return null;
		
	}
}
