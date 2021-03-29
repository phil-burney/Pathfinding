package Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class AdjacencyListGraph<N, E> {

	public static final int NODE_SIZE = 3;

	private ArrayList<Node<N, E>> nodeList;
	private ArrayList<Edge<N, E>> edgeList;

	PApplet app;

	public AdjacencyListGraph(PApplet app) {
		nodeList = new ArrayList<Node<N, E>>();
		edgeList = new ArrayList<Edge<N, E>>();
		this.app = app;
	}

	public Node<N, E> createNode(N value, PVector location) {
		Node<N, E> node = new Node<N, E>(value, location);
		nodeList.add(node);
		return node;
	}

	public Edge<N, E> createEdge(E element, Node<N, E> start, Node<N, E> end, float weight) {
		Edge<N, E> edge = new Edge<N, E>(element, start, end, weight);
		start.edgeTo(element, end, weight);
		edgeList.add(edge);
		return edge;
	}

	public List<Edge<N, E>> outgoingEdges(Node<N, E> node) {
		return node.getOutgoing();
	}

	public ArrayList<Edge<N, E>> incomingEdges(Node<N, E> node) {
		return node.getIncoming();
	}

	public ArrayList<Node<N, E>> getNodeList() {
		return nodeList;
	}

	public ArrayList<Edge<N, E>> getEdgeList() {
		return edgeList;
	}

	public Edge<N, E> getEdge(Node<N, E> n1, Node<N, E> n2) {
		Iterator<Edge<N, E>> it = edgeList.iterator();
		while (it.hasNext()) {
			Edge<N, E> current = it.next();

			if (current.getStart() == n1 && current.getEnd() == n2) {
				return current;
			}
			if (current.getEnd() == n1 && current.getStart() == n2) {
				return current;
			}
		}
		return null;
	}

	public void render(boolean includeArrowTips) {

		for (int i = 0; i < this.getNodeList().size(); i++) {

			if (this.getNodeList().get(i) != null) {
				ArrayList<Edge<N, E>> edgeList = this.getNodeList().get(i).getOutgoing();
				for (int j = 0; j < edgeList.size(); j++) {
					Edge<N, E> edge = edgeList.get(j);
					Node<N, E> end = edge.getEnd();
					Node<N, E> start = edge.getStart();
					if (start != null && end != null) {
						PVector startCoords = new PVector(start.getLocation().x, start.getLocation().y);
						PVector endCoords = new PVector(end.getLocation().x, end.getLocation().y);
						app.stroke(0);
						app.line(startCoords.x, startCoords.y, endCoords.x, endCoords.y);
						if (includeArrowTips) {
							arrowTips(endCoords, startCoords);
						}

					}

				}
			}

		}

		for (int i = 0; i < this.getNodeList().size(); i++) {
			Node<N, E> node = this.getNodeList().get(i);
			if (node != null) {
				app.fill(100f, 255f, 20f);
				app.circle(node.getLocation().x, node.getLocation().y, NODE_SIZE);
			}
		}

	}

	private void arrowTips(PVector arrowPoint, PVector notArrowPoint) {
		float dx = arrowPoint.x - notArrowPoint.x;
		float dy = arrowPoint.y - notArrowPoint.y;

		PVector slope = new PVector(dx, dy).normalize().mult(6);
		PVector reverseSlope = new PVector(dy, dx * -1).normalize().mult(3);
		PVector arrowRear = new PVector(arrowPoint.x - slope.x, arrowPoint.y - slope.y);

		PVector point1 = new PVector(arrowPoint.x, arrowPoint.y);
		PVector point2 = new PVector(arrowRear.x + reverseSlope.x, arrowRear.y + reverseSlope.y);
		PVector point3 = new PVector(arrowRear.x - reverseSlope.x, arrowRear.y - reverseSlope.y);

		app.triangle(point1.x, point1.y, point2.x, point2.y, point3.x, point3.y);

	}

	public void renderEdges(List<Edge<N, E>> edges, float c1, float c2, float c3) {

		for (Edge<N, E> edge : edges) {

			PVector start = edge.getStart().getLocation();
			PVector end = edge.getEnd().getLocation();
			app.stroke(c1, c2, c3);
			app.line(start.x, start.y, end.x, end.y);

		}
	}

	public Node<N, E> getNearest(int x, int y) {
		PVector loc = new PVector(x, y);
		Node<N, E> returnNode = null;
		float minDist = Float.MAX_VALUE;
		for (Node<N, E> n : nodeList) {
			if (n != null) {
				float dist = new PVector(PApplet.abs(x - n.getLocation().x), PApplet.abs(y - n.getLocation().y)).mag();
				if (dist < minDist) {
					minDist = dist;
					returnNode = n;
				}
			}
		}
		return returnNode;

	}

}
