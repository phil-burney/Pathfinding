package Graph;

import java.util.ArrayList;
import java.util.Comparator;

import processing.core.PVector;

public class Node<N, E> implements Comparator<Node<N,E>>{
	private N value;
	private ArrayList<Edge<N, E>> incoming;
	private ArrayList<Edge<N, E>> outgoing;
	private PVector location;
	private float costFromStart;

	public Node(N value, PVector location) {
		this.value = value;
		this.incoming = new ArrayList<Edge<N, E>>();
		this.outgoing = new ArrayList<Edge<N, E>>();
		this.setLocation(location);
	}

	public void edgeTo(E element, Node<N, E> end, float weight) {
		Edge<N, E> edge = new Edge<N, E>(element, this, end, weight);
		this.getOutgoing().add(edge);
		end.getIncoming().add(edge);
	}

	public N getElement() {
		return value;
	}
	public ArrayList<Edge<N, E>> getIncoming() {
		return incoming;
	}
	public ArrayList<Edge<N, E>> getOutgoing() {
		return outgoing;
	}

	public N getValue() {
		return value;
	}

	public PVector getLocation() {
		return location;
	}

	public void setLocation(PVector location) {
		this.location = location;
	}

	public float getCostFromStart() {
		return costFromStart;
	}

	public void setCostFromStart(float costFromStart) {
		this.costFromStart = costFromStart;
	}

	@Override
	public int compare(Node<N, E> node1, Node<N, E> node2) {
		if (node1.costFromStart < node2.costFromStart)
            return -1;
		else 
			return 1;
	}
	public String toString() {
		return value.toString();
	}




}