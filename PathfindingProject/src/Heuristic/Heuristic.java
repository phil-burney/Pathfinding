package Heuristic;

import Graph.Node;

public interface Heuristic {
	public <N,E> float  value(Node<N, E> start, Node<N, E> goal);
}