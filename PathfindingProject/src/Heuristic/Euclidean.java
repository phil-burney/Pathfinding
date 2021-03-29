package Heuristic;

import java.util.ArrayList;

import Graph.Edge;
import Graph.Node;
import processing.core.PApplet;

public class Euclidean implements Heuristic {

	@Override
	public <N, E> float value(Node<N, E> start, Node<N, E> goal) {
		float x = PApplet.sq(start.getLocation().x - goal.getLocation().x);
		float y = PApplet.sq(start.getLocation().y - goal.getLocation().y);
		return PApplet.sqrt(x+y);
	}

}
