package Heuristic;

import Graph.Node;
import processing.core.PApplet;

public class Manhattan implements Heuristic {


	@Override
	public <N,E> float value(Node<N, E> start, Node<N, E> goal) {
		return PApplet.abs(goal.getLocation().x - start.getLocation().x) + PApplet.abs(goal.getLocation().y - start.getLocation().y);
	}
}