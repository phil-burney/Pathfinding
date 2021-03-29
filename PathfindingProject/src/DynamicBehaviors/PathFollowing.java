package DynamicBehaviors;

import java.util.ArrayList;

import DynamicBehaviors.weight.WeightedMovement;
import Environment.Environment;
import Graph.AdjacencyListGraph;
import Graph.Edge;
import Graph.Node;
import Motion.Motion;
import Search.Search;
import processing.core.PApplet;
import processing.core.PVector;

public class PathFollowing extends DynamicBehavior {

	PVector dest;
	Node<Integer, Integer> startNode;
	Environment env;
	DynamicSeek seek;
	Search search;
	ArrayList<PVector> path;
	float pathPos;

	public PathFollowing(PApplet app) {
		
		super(app);
		seek = new DynamicSeek(app);
		this.pathPos = 0;

	}

	public PathFollowing(PApplet app, Environment env, Search search) {
		super(app);
		this.env = env;
		this.startNode = env.start;
		dest = new PVector(startNode.getLocation().x, startNode.getLocation().y);
		seek = new DynamicSeek(app);
		seek.setDest(dest);
		this.search = search;

	}

	public WeightedMovement behavior(Motion motion) {
		PVector pos = getPosition(getParam(motion.getPosition(), pathPos));
		seek.setDest(pos);
		return seek.behavior(motion);

	}
	@Override
	public void onMousePress(int mouseX, int mouseY) {
		Node<Integer, Integer> n = env.getGraph().getNearest(mouseX, mouseY);
		this.path = createPath(n);
		System.out.println("clicked");
		
	}
	private PVector getPosition(float param) {
		int index = PApplet.round(param / 1.0f);
	    
	    if(index < path.size())
	      return path.get(index);
	    else
	      return path.get(path.size() - 1); 
	}
	
	float getParam(PVector position, float lastParam) {
	    int minIndex = PApplet.round((lastParam - 2) / 1);
	    minIndex = PApplet.max(minIndex, 0);
	    
	    int maxIndex = PApplet.round((lastParam + 2) / 1) + 1;
	    maxIndex = PApplet.min(maxIndex, path.size());
	    
	    float minDistance = Float.POSITIVE_INFINITY;
	    float param = 0.0f;
	    
	    for(int i=minIndex; i < maxIndex; ++i){
	       float distance = PVector.sub(path.get(i), position).mag();
	       
	       if(distance < minDistance){
	         minDistance = distance;
	         param = 1.0f * (float) i;
	       }
	    }
	    
	    return param;
	  }
	private ArrayList<PVector> createPath(Node<Integer, Integer> n){
		ArrayList<Edge<Integer, Integer>> foundPath = search.shortestPath(env.getGraph(), startNode, n);
		ArrayList<PVector> returnList = new ArrayList<PVector>();
		for(Edge<Integer, Integer> e: foundPath) {
			returnList.add(e.getEnd().getLocation());
		}
		return path;
		
	}

}
