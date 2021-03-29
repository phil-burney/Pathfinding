package Environment;

import java.util.ArrayList;

import Display.Display;
import Graph.AdjacencyListGraph;
import Graph.Node;
import GraphGenerators.City;
import processing.core.PApplet;
import processing.core.PVector;

public class Environment {
	/** How far apart nodes of graph are spaced apart */
	public static final int GRAPH_DENSITY = 100;
	public static final int WALL_WIDTH = 50;

	private AdjacencyListGraph<Integer, Integer> graph;

	private ArrayList<Wall> walls;
	private PApplet app;
	private float buffer;
	public Node<Integer, Integer> start;

	public Environment(PApplet app, float buffer) {
		this.app = app;
		this.buffer = buffer;
		this.start = null;

		walls = new ArrayList<Wall>();
		// Add outer walls
		walls.add(new Wall(app, new PVector(0, 0), new PVector(WALL_WIDTH, Display.SCREEN_SIZE)));
		walls.add(new Wall(app, new PVector(0, 0), new PVector(Display.SCREEN_SIZE, WALL_WIDTH)));
		walls.add(new Wall(app, new PVector(Display.SCREEN_SIZE - WALL_WIDTH, 0),
				new PVector(Display.SCREEN_SIZE, Display.SCREEN_SIZE)));
		walls.add(new Wall(app, new PVector(Display.SCREEN_SIZE - WALL_WIDTH, 0),
				new PVector(Display.SCREEN_SIZE, Display.SCREEN_SIZE)));
		walls.add(new Wall(app, new PVector(0, Display.SCREEN_SIZE - WALL_WIDTH),
				new PVector(Display.SCREEN_SIZE, Display.SCREEN_SIZE)));
		// Add walls for rooms
		float halfWidth = WALL_WIDTH / 2;
		float wallCenterPoint = Display.SCREEN_SIZE / 2;
		float doorway = 150;
		// top
		walls.add(new Wall(app, new PVector(wallCenterPoint - halfWidth, WALL_WIDTH),
				new PVector(wallCenterPoint + halfWidth, wallCenterPoint - doorway)));
		// left
		walls.add(new Wall(app, new PVector(WALL_WIDTH, wallCenterPoint - halfWidth),
				new PVector(wallCenterPoint - doorway, wallCenterPoint + halfWidth)));
		// right
		walls.add(new Wall(app, new PVector(wallCenterPoint + doorway, wallCenterPoint - halfWidth),
				new PVector(Display.SCREEN_SIZE - WALL_WIDTH, wallCenterPoint + halfWidth)));
		// bottom
		walls.add(new Wall(app, new PVector(wallCenterPoint - halfWidth, wallCenterPoint + doorway),
				new PVector(wallCenterPoint + halfWidth, Display.SCREEN_SIZE - WALL_WIDTH)));

		walls.add(new Wall(app, new PVector(wallCenterPoint - WALL_WIDTH, wallCenterPoint - WALL_WIDTH),
				new PVector(wallCenterPoint + WALL_WIDTH, wallCenterPoint + WALL_WIDTH)));

		// Add obstacles

		walls.add(new Wall(app, new PVector(100, 200), new PVector(250, 250)));
		walls.add(new Wall(app, new PVector(100, 500), new PVector(200, 550)));
		walls.add(new Wall(app, new PVector(500, 500), new PVector(550, 600)));
		walls.add(new Wall(app, new PVector(500, 100), new PVector(550, 200)));

		// Build graph
		buildGraph();

	}

	public void render() {
		for (Wall w : walls) {
			w.render();
		}
		//graph.render(true);
	}

	private void buildGraph() {
		graph = new AdjacencyListGraph<Integer, Integer>(app);
		generateNodes();
		generateEdges();

	}
	public AdjacencyListGraph<Integer, Integer> getGraph() {
		return graph;
	}

	private void generateNodes() {
		int dx = 0;
		int dy = 0;
		int spacing = Display.SCREEN_SIZE / GRAPH_DENSITY;
		int offset = spacing / 2;
		// Generate nodes
		for (int i = 0; i < GRAPH_DENSITY; i++) {

			for (int j = 0; j < GRAPH_DENSITY; j++) {
				float x = offset + j * spacing;
				float y = offset + i * spacing;
				if (!inWalls(x, y)) {
					Node<Integer, Integer> s = graph.createNode(0, new PVector(x, y));
					if(start == null) {
						start = s;
					}
				} else {
					graph.getNodeList().add(null);
				}

			}
		}

	}

	private boolean inWalls(float x, float y) {
		for (Wall w : walls) {
			if (w.inWall(x, y, buffer)) {
				return true;
			}
		}
		return false;
	}

	private void generateEdges() {
		// Create horizontal edges to make bi-directional graph
		for (int i = 0; i < GRAPH_DENSITY - 1; i++) {
			for (int j = 0; j < GRAPH_DENSITY - 1; j++) {
				int idx = i * GRAPH_DENSITY + j;
				Node<Integer, Integer> node = graph.getNodeList().get(idx);
				Node<Integer, Integer> node1 = graph.getNodeList().get(idx + 1);

				if (node != null && node1 != null) {
					graph.createEdge(0, node1, node, 10f);
					graph.createEdge(0, node, node1, 10f);
				}
			}

		}
		// Create vertical edges to make bi-directional graph
		for (int i = 0; i < GRAPH_DENSITY - 1; i++) {
			for (int j = 0; j < GRAPH_DENSITY; j++) {
				int idx = i * GRAPH_DENSITY + j;
				Node<Integer, Integer> node = graph.getNodeList().get(idx);
				Node<Integer, Integer> node1 = graph.getNodeList().get(idx + GRAPH_DENSITY);

				if (node != null && node1 != null) {
					graph.createEdge(0, node1, node, 10f);
					graph.createEdge(0, node, node1, 10f);
				}
			}
		}
		// Create diagonal edges to make bi-directional graph
		for (int i = 0; i < GRAPH_DENSITY - 1; i++) {
			for (int j = 0; j < GRAPH_DENSITY - 1; j++) {
				int idx = i * GRAPH_DENSITY + j;
				Node<Integer, Integer> node = graph.getNodeList().get(idx);
				Node<Integer, Integer> node1 = graph.getNodeList().get(idx + GRAPH_DENSITY + 1);

				if (node != null && node1 != null) {
					graph.createEdge(0, node1, node, 10f);
					graph.createEdge(0, node, node1, 10f);
				}
			}
		}

		// Create diagonal edges to make bi-directional graph
		for (int i = 0; i < GRAPH_DENSITY - 1; i++) {
			for (int j = 0; j < GRAPH_DENSITY + 1; j++) {
				int idx = i * GRAPH_DENSITY + j;
				Node<Integer, Integer> node = graph.getNodeList().get(idx);
				Node<Integer, Integer> node1 = graph.getNodeList().get(idx + GRAPH_DENSITY - 1);

				if (node != null && node1 != null) {
					graph.createEdge(0, node1, node, 10f);
					graph.createEdge(0, node, node1, 10f);
				}
			}
		}

	}
}
