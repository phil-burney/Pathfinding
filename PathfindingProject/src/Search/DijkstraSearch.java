package Search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import Graph.AdjacencyListGraph;
import Graph.Edge;
import Graph.Node;

public class DijkstraSearch implements Search {
	@Override
	public <N, E> ArrayList<Edge<N, E>> shortestPath(AdjacencyListGraph<N, E> graph, Node<N, E> start,
			Node<N, E> goal) {
		ArrayList<Edge<N, E>> returnList = new ArrayList<Edge<N, E>>();

		PriorityQueue<Node<N, E>> openSet = new PriorityQueue<Node<N, E>>(new Node<N, E>(null, null));

		HashSet<Node<N, E>> closedSet = new HashSet<Node<N, E>>();
		Map<Node<N, E>, Edge<N, E>> lowestCostEdge = new LinkedHashMap<Node<N, E>, Edge<N, E>>();
		
		// Set CFS for all nodes to infinity, except the starting one
		for (Node<N, E> n : graph.getNodeList()) {
			if (n == start) {
				n.setCostFromStart(0f);

			} else {
				
				n.setCostFromStart(Float.POSITIVE_INFINITY);
				
			}
		}
		openSet.add(start);

		// While goal is not in closed set
		while (!openSet.isEmpty() && !closedSet.contains(goal)) {
			Node<N, E> n = openSet.remove();

			for (Edge<N, E> edge : graph.outgoingEdges(n)) {
				Node<N, E> endNode = edge.getEnd();
				float r = edge.getWeight() + n.getCostFromStart();

				if (!closedSet.contains(endNode) && r < endNode.getCostFromStart()) {
					lowestCostEdge.put(edge.getEnd(), edge);
					
					endNode.setCostFromStart(r);
					
					openSet.add(endNode);

				}
				closedSet.add(n);
			}
		}

		System.out.println("" + goal.getValue() + " " + goal.getCostFromStart());
		return getEdgeList(lowestCostEdge, start, goal);

	}

	private static <N, E> ArrayList<Edge<N, E>> getEdgeList(Map<Node<N, E>, Edge<N, E>> map, Node<N, E> start,
			Node<N, E> goal) {
		ArrayList<Edge<N, E>> list = new ArrayList<Edge<N, E>>();
		Node<N, E> currNode = goal;
		
		if( map.get(currNode) == null) {
			return list;
		}
		while (currNode != start) {
			Edge<N, E> addEdge = map.get(currNode);
			list.add(addEdge);
			if (addEdge != null) {
				currNode = addEdge.getStart();
			}
		}
		return list;
	}

}
