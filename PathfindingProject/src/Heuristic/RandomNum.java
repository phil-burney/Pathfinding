package Heuristic;

import java.util.Random;

import Graph.Node;
import processing.core.PApplet;

public class RandomNum implements Heuristic {

	@Override
	public <N, E> float value(Node<N, E> start, Node<N, E> goal) {
		
		Random rand = new Random();
		
		return (float)rand.nextDouble()*100;
		
	}

}
