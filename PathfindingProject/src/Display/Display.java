package Display;


import java.util.ArrayList;
import java.util.List;

import DynamicBehaviors.DynamicArrive;
import DynamicBehaviors.DynamicBehavior;
import DynamicBehaviors.PathFollowing;
import Environment.Environment;
import Graph.AdjacencyListGraph;
import Graph.Edge;
import Graph.Node;
import Motion.DynamicMotion;

import MovingObject.MovingObject;
import Search.AStarSearch;
import Search.DijkstraSearch;
import Search.Search;
import processing.core.*;
import GraphGenerators.City;
import GraphGenerators.GraphGenerator;
import GraphGenerators.LargeGraph;
import GraphGenerators.SmallGraph;
import Heuristic.Euclidean;
import Heuristic.Manhattan;
import Heuristic.RandomNum;

public class Display extends PApplet {
	/** The frame rate per second of the window */
	public static final int FRAME_RATE = 30;
	/** The size of the window, in pixels */
	public static final int SCREEN_SIZE = 700;
	
	/** MovingObject that is to be used */
	MovingObject dynamicMovingObject;
	/** MovingObject that is to be used */
	MovingObject kinematicMovingObject;

	/** Graph to be used */
	GraphGenerator graphGenerator;
	/** Search algorithm to be used */
	Search search;
	/** Environment to be used */
	Environment env;
	
	List<Edge<Object, Object>> list = null;
	
	Breadcrumbs crumbs;

	
	DynamicBehavior dBehavior;
	/** Program for flocking behavior */

	/**
	 * Designates the location of the main class 
	 * @param args String arguments 
	 */
	public static void main(String[] args) {
		PApplet.main("Display.Display");

	}
	/**
	 * Settings for the screen size
	 */
	public void settings() {
		size(SCREEN_SIZE, SCREEN_SIZE);
		smooth(2);

	}
	/**
	 * Sets the frame rate, initalizes a moving object and initalizes each behavior that is being 
	 * graded  
	 */
	public void setup() {
		frameRate(FRAME_RATE);
		
		env = new Environment(this, 10f);
		PVector start = new PVector(env.start.getLocation().x, env.start.getLocation().y);
		PVector velocity = new PVector(0f, 0f);
		PVector accel = new PVector(0f, 0f);
		
		DynamicMotion dynmotion = new DynamicMotion(start, 90f, velocity, PApplet.radians(1), accel, PApplet.radians(1));
		dynamicMovingObject = new MovingObject(this, dynmotion, 20);
		
		
		
		//Uncomment these one at a time!
		//initGraphSearch(new SmallGraph(this), new DijkstraSearch());
		
		//initGraphSearch(new LargeGraph(this), new DijkstraSearch());
		
		//initGraphSearch(new SmallGraph(this), new AStarSearch(new Manhattan()));
		//initGraphSearch(new SmallGraph(this), new AStarSearch(new RandomNum()));
		
		//initGraphSearch(new SmallGraph(this), new AStarSearch(new Euclidean()));
		
		//initGraphSearch(new LargeGraph(this), new AStarSearch(new Manhattan()));
		
		
		
		
		
		// Initializer for part one (5pts)
		crumbs = new Breadcrumbs(this, dynamicMovingObject);
		
		
		dBehavior = new PathFollowing(this, env, new AStarSearch(new Manhattan()));
		
		
	}

	private void initGraphSearch(GraphGenerator generator, Search search) {
		graphGenerator = generator;
		this.search = search;
		
	}
	public void draw() {
		
		
		background(100, 200, 200);
		if(graphGenerator != null) {
			graphGenerator.render();
		}
		if(list != null) {
			graphGenerator.getGraph().renderEdges(list, 255,0,0);
		}
		
		envDemo();


		
	}
	private void envDemo() {
		env.render();
		DynamicMotion dynMotion = (DynamicMotion) dynamicMovingObject.getMotion();
		dynMotion.addBlendedBehaviors(dBehavior.behavior(dynMotion));
		dynamicMovingObject.getMotion().dynamicUpdate();
		dynamicMovingObject.updateObjKinematics(dynamicMovingObject.getMotion().getPosition().x, dynamicMovingObject.getMotion().getPosition().y, dynamicMovingObject.getMotion().getOrientation());
		crumbs.render();
		
		
	}


	public void mousePressed() {
		//kArrive.onMousePress(mouseX, mouseY);

		dBehavior.onMousePress(mouseX, mouseY);
		//smallDijkstraDemo(mouseX, mouseY);
		if(graphGenerator != null) {
			graphDemo(mouseX, mouseY);
		}
		
		ellipse(mouseX, mouseY, 10, 10);
		
	}
	private void graphDemo(int x, int y) {
		AdjacencyListGraph<Object, Object> graph = graphGenerator.getGraph();
		Node<Object, Object> nearest = graph.getNearest(x, y);
		list = search.shortestPath(graph, graph.getNodeList().get(0), nearest);
		
		graph.renderEdges(list, 255,0,0);
	}

}
