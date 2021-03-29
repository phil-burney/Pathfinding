package GraphGenerators;

import java.util.ArrayList;

import Display.Display;
import Graph.AdjacencyListGraph;
import Graph.Edge;
import Graph.Node;
import processing.core.PApplet;
import processing.core.PVector;

public class SmallGraph implements GraphGenerator{
	public static final int NODE_SIZE = 5;
	public static final int SCALE = 120;
	public static final int ARROW_SIZE = 10;
	public static final int OFFSET = 180;
	AdjacencyListGraph<City, String> graph;
	City center;
	PApplet app;

	public SmallGraph(PApplet app) {
		graph = new AdjacencyListGraph<City, String>(app);
		center = new City("Raleigh", 78.63f, 35.77f);
		this.app = app;
		generateGraph();
	}
	@Override
	public void generateGraph() {
		City raleigh = center;
		City charlotte = new City("Charlotte", 80.84f, 35.22f);
		City waynesville = new City("Waynesville", 82.99f, 35.48f);
		City asheville = new City("Asheville", 82.55f, 35.59f);
		City greensboro = new City("Greensboro", 79.79f, 36.07f);
		City carolinabeach = new City("Carolina Beach", 77.89f, 34.04f);
		City fayetteville = new City("Fayetteville", 78.87f, 35.05f);
		City durham = new City("Durham", 78.89f, 35.99f);
		City cary = new City("Cary", 78.78f, 35.79f);
		City chapelhill = new City("Chapel Hill", 79.05f, 35.91f);
		City winstonsalem = new City("Winston-Salem", 80.2f, 36.1f);
		City boone = new City("Boone", 81.67f, 36.21f);
		City concord = new City("Concord", 80.58f, 35.41f);
		City gastonia = new City("Gastonia", 81.18f, 35.26f);
		City highpoint = new City("High Point", 80f, 35.95f);
		City mooresville = new City("Morresville", 80.8f, 35.58f);
		City rockymount = new City("Rocky Mount", 77.79f, 35.93f);
		City hendersonville = new City("Hendersonville", 82.46f, 35.31f);
		City forestcity = new City("Forest City", 81.86f, 35.33f);
		City statesville = new City("Statesville", 80.88f, 35.78f);

		// Create Nodes

		Node<City, String> raleighN = graph.createNode(raleigh, relativeCoords(raleigh));
		Node<City, String> charlotteN = graph.createNode(charlotte, relativeCoords(charlotte));
		Node<City, String> waynesvilleN = graph.createNode(waynesville, relativeCoords(waynesville));
		Node<City, String> ashevilleN = graph.createNode(asheville, relativeCoords(asheville));
		Node<City, String> greensboroN = graph.createNode(greensboro, relativeCoords(greensboro));
		Node<City, String> carolinaBeachN = graph.createNode(carolinabeach, relativeCoords(carolinabeach));
		Node<City, String> fayettevilleN = graph.createNode(fayetteville, relativeCoords(fayetteville));
		Node<City, String> durhamN = graph.createNode(durham, relativeCoords(durham));
		Node<City, String> caryN = graph.createNode(cary, relativeCoords(cary));
		Node<City, String> chapelhillN = graph.createNode(chapelhill, relativeCoords(chapelhill));
		Node<City, String> winstonsalemN = graph.createNode(winstonsalem, relativeCoords(winstonsalem));
		Node<City, String> booneN = graph.createNode(boone, relativeCoords(boone));
		Node<City, String> concordN = graph.createNode(concord, relativeCoords(concord));
		Node<City, String> gastoniaN = graph.createNode(gastonia, relativeCoords(gastonia));
		Node<City, String> highPointN = graph.createNode(highpoint, relativeCoords(highpoint));
		Node<City, String> mooresvilleN = graph.createNode(mooresville, relativeCoords(mooresville));
		Node<City, String> rockymountN = graph.createNode(rockymount, relativeCoords(rockymount));
		Node<City, String> hendersonvilleN = graph.createNode(hendersonville, relativeCoords(hendersonville));
		Node<City, String> forestcityN = graph.createNode(forestcity, relativeCoords(forestcity));
		Node<City, String> statesvilleN = graph.createNode(statesville, relativeCoords(statesville));

		//I-40
		
		// Create edges for raleigh (weights are in miles
		graph.createEdge("Hwy-54", raleighN, caryN, 11.6f);
		graph.createEdge("I-40", raleighN, chapelhillN, 31.3f);
		graph.createEdge("Hwy-401", raleighN, fayettevilleN, 83.3f);
		graph.createEdge("I-40", raleighN, carolinaBeachN, 141.8f);
		graph.createEdge("NC-147", raleighN, durhamN, 24.2f);
		graph.createEdge("Hwy-64", raleighN, rockymountN, 58.8f);
		// Create edges for charlotte
		graph.createEdge("US-74", charlotteN, fayettevilleN, 129.7f);
		graph.createEdge("I-85", charlotteN, gastoniaN, 23.5f);
		graph.createEdge("I-85", charlotteN, concordN, 26.4f);
		// Create edges for greensboro
		graph.createEdge("I-40", greensboroN, winstonsalemN, 31.2f);
		

		// Create edges for waynesville
		graph.createEdge("I-40", waynesvilleN, ashevilleN, 30.7f);

		// Create edges for asheville
		graph.createEdge("I-40", ashevilleN, statesvilleN, 102.8f);
		graph.createEdge("US-221", ashevilleN, booneN, 84.8f);

		// Create edges for carolina beach
		graph.createEdge("US-74", carolinaBeachN, charlotteN, 211.7f);
		graph.createEdge("I-74", carolinaBeachN, highPointN, 224.0f);

		// Create edges for fayetteville
		graph.createEdge("US-74", fayettevilleN, charlotteN, 132.5f);
		graph.createEdge("US-421", fayettevilleN, greensboroN, 94.2f);
		graph.createEdge("US-1", fayettevilleN, caryN, 72.5f);
		graph.createEdge("US-1", fayettevilleN, carolinaBeachN, 102.9f);
		graph.createEdge("US-1", fayettevilleN, concordN, 122f);

		// Create edges for durham
		graph.createEdge("US-64", durhamN, rockymountN, 82.4f);
		graph.createEdge("US-64", durhamN, greensboroN, 53.8f);

		// Create edges for cary
		graph.createEdge("I-40", caryN, chapelhillN, 22.7f);
		graph.createEdge("NC-147", caryN, durhamN, 19.2f);

		// Create edges for chapel hill
		graph.createEdge("Durham-Chapel Hill Blvd", chapelhillN, durhamN, 11.2f);
		graph.createEdge("I-40", chapelhillN, greensboroN, 50.5f);

		// Create edges for winston-salem
		graph.createEdge("I-40", winstonsalemN, greensboroN, 32.5f);
		graph.createEdge("I-74", winstonsalemN, highPointN, 20.5f);

		// Create edges for boone
		graph.createEdge("US-421", booneN, winstonsalemN, 86.2f);
		graph.createEdge("Hwy-321", booneN, statesvilleN, 68.8f);

		// Create edges for concord
		graph.createEdge("I-40", concordN, charlotteN, 26.9f);
		graph.createEdge("I-85", concordN, highPointN, 58.7f);
		graph.createEdge("I-77", concordN, statesvilleN, 51.1f);

		// Create edges for gastonia
		graph.createEdge("Hwy-74", gastoniaN, forestcityN, 41.3f);
		graph.createEdge("US-321", gastoniaN, booneN, 80.1f);

		// Create edges for mooresville
		graph.createEdge("NC-3", mooresvilleN, concordN, 21.6f);
		graph.createEdge("I-77", mooresvilleN, charlotteN, 27.3f);

		// Create edges for rocky mount
		graph.createEdge("I-95", rockymountN, fayettevilleN, 92.6f);
		graph.createEdge("I-40", rockymountN, carolinaBeachN, 153.2f);

		// Create edges for hendersonville
		graph.createEdge("I-26", hendersonvilleN, ashevilleN, 26.1f);
		graph.createEdge("I-64", hendersonvilleN, waynesvilleN, 52.7f);
		graph.createEdge("I-64", hendersonvilleN, statesvilleN, 134f);

		// Create edges for forest city
		graph.createEdge("Hwy-221", forestcityN, booneN, 82.4f);
		graph.createEdge("US-74 ALT", forestcityN, hendersonvilleN, 45.6f);
		graph.createEdge("NC-150", forestcityN, statesvilleN, 77.3f);
		graph.createEdge("NC-150", forestcityN, mooresvilleN, 70.0f);

		// Create edges for statesville
		graph.createEdge("I-40", statesvilleN, winstonsalemN, 45.4f);
		graph.createEdge("Hwy-64", statesvilleN, caryN, 133f);
		graph.createEdge("Hwy-64", statesvilleN, booneN, 70.8f);
		graph.createEdge("I-77", statesvilleN, mooresvilleN, 18.2f);
		
		// Create edges for high point
		graph.createEdge("I-85", highPointN, greensboroN, 18.7f);
		graph.createEdge("I-85", highPointN, statesvilleN, 61.5f);
		graph.createEdge("US-421", highPointN, fayettevilleN, 104.2f);

		

	}

	public void render() {
		graph.render(true);
		// Render nodes
		for (int i = 0; i < graph.getNodeList().size(); i++) {
			Node<City, String> node = graph.getNodeList().get(i);

			
			app.text(node.getElement().name, node.getLocation().x,  node.getLocation().y);

		}

	}

	private PVector relativeCoords(City city) {
		PVector centerScreen = new PVector(Display.SCREEN_SIZE / 2 + OFFSET, Display.SCREEN_SIZE / 2, NODE_SIZE);
		PVector relativeEarthCoords = new PVector(city.x - center.x, city.y - center.y).mult(SCALE);
		return new PVector(centerScreen.x - relativeEarthCoords.x, centerScreen.y - relativeEarthCoords.y);

	}
	public AdjacencyListGraph<City, String> getGraph() {
		return graph;
	}
	

}
