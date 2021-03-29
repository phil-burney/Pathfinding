package Search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import Graph.AdjacencyListGraph;
import Graph.Edge;
import Graph.Node;
import Heuristic.Heuristic;

public class AStarSearch implements Search {

	Heuristic heurestic;

	public AStarSearch(Heuristic heurestic) {
		this.heurestic = heurestic;
	}

	@Override
	public <N, E> ArrayList<Edge<N, E>> shortestPath(AdjacencyListGraph<N, E> graph, Node<N, E> start,
			Node<N, E> goal) {

		PriorityQueue<Node<N, E>> openSet = new PriorityQueue<Node<N, E>>(new Node<N, E>(null, null));

		Map<Node<N, E>, Edge<N, E>> lowestCostEdge = new LinkedHashMap<Node<N, E>, Edge<N, E>>();

		Map<Node<N, E>, Float> gCFSMap = new LinkedHashMap<Node<N, E>, Float>();

		for (Node<N, E> n : graph.getNodeList()) {
			if (n != null) {
				if (n == start) {
					n.setCostFromStart(heurestic.value(start, goal));

				} else {

					n.setCostFromStart(Float.POSITIVE_INFINITY);

				}
			}
		}

		for (Node<N, E> n : graph.getNodeList()) {
			if (n == start) {
				gCFSMap.put(n, 0f);

			} else {

				gCFSMap.put(n, Float.POSITIVE_INFINITY);

			}
		}

		openSet.add(start);
		while (!openSet.isEmpty()) {
			Node<N, E> n = openSet.remove();
			if (n == goal) {
				System.out.println(
						"" + goal.getValue() + " Cost:" + gCFSMap.get(goal) + " Estimate:" + goal.getCostFromStart());
				return getEdgeList(lowestCostEdge, start, goal);
			}
			openSet.remove(n);
			for (Edge<N, E> edge : graph.outgoingEdges(n)) {
				Node<N, E> endNode = edge.getEnd();
				float r = edge.getWeight() + gCFSMap.get(n);
				if (r < gCFSMap.get(endNode)) {
					lowestCostEdge.put(endNode, edge);

					gCFSMap.put(endNode, r);
					endNode.setCostFromStart(gCFSMap.get(endNode) + heurestic.value(endNode, goal));

					if (!openSet.contains(endNode)) {
						openSet.add(endNode);
					}

				}

			}

		}

		System.out
				.println("" + goal.getValue() + " Cost:" + gCFSMap.get(goal) + " Estimate:" + goal.getCostFromStart());
		return getEdgeList(lowestCostEdge, start, goal);

	}

	private static <N, E> ArrayList<Edge<N, E>> getEdgeList(Map<Node<N, E>, Edge<N, E>> map, Node<N, E> start,
			Node<N, E> goal) {
		ArrayList<Edge<N, E>> list = new ArrayList<Edge<N, E>>();
		Node<N, E> currNode = goal;

		if (map.get(currNode) == null) {
			return list;
		}
		while (currNode != start) {
			Edge<N, E> addEdge = map.get(currNode);
			list.add(0, addEdge);
			if (addEdge != null) {
				currNode = addEdge.getStart();
			}
		}
		return list;
	}

}
