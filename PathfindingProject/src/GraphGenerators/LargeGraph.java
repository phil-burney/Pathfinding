package GraphGenerators;


import Display.Display;
import Graph.AdjacencyListGraph;
import Graph.Node;
import processing.core.PApplet;
import processing.core.PVector;

public class LargeGraph implements GraphGenerator{
	public static final int NODE_SIZE = 2;
	public static final int ARROW_SIZE = 10;
	AdjacencyListGraph<City, String> graph;
	PApplet app;
	public static final int GRID_WIDTH = 30;

	public LargeGraph(PApplet app) {
		graph = new AdjacencyListGraph<City, String>(app);
		this.app = app;
		generateGraph();
	}
	@Override
	public void generateGraph() {
		generateNodes();
		generateEdges();
		generateEdgesGrid();
	}
	private void generateNodes() {
		int dx = 0;
		int dy = 0;
		int spacing = Display.SCREEN_SIZE / GRID_WIDTH;
		int offset = spacing / 2;
		// Generate nodes
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_WIDTH; j++) {
				int idx = i * GRID_WIDTH  + j;
				City city = new City("" + idx, offset + j * spacing, offset + i * spacing);
				Node<City, String> node = graph.createNode(city, new PVector(city.x, city.y));

			}
		}
	}
	private void generateEdgesGrid() {
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_WIDTH - 1; j++) {
				int idx = i * GRID_WIDTH  + j;
				Node<City, String> node = graph.getNodeList().get(idx);
				Node<City, String> node1 = graph.getNodeList().get(idx + 1);
				
				graph.createEdge("o", node1, node, 10f);
				
				

			}
			

		}

		for (int i = 0; i < GRID_WIDTH - 1; i++) {
			for (int j = 0; j < GRID_WIDTH; j++) {
				int idx = i * GRID_WIDTH  + j;
				Node<City, String> node = graph.getNodeList().get(idx);
				Node<City, String> node1 = graph.getNodeList().get(idx + GRID_WIDTH);
				
				graph.createEdge("o", node1, node, 1f);
				
				

			}
			

		}
		
	}
	private void generateEdges() {
		// Generate edges for top half
		for (int i = 0; i < GRID_WIDTH - 3; i++) {
			for (int j = 0; j < GRID_WIDTH  -1; j++) {
				int idx = i * GRID_WIDTH  + j;
				Node<City, String> node = graph.getNodeList().get(idx);
				Node<City, String> node1 = graph.getNodeList().get(idx + GRID_WIDTH + 1);
				Node<City, String> node2 = graph.getNodeList().get(idx + GRID_WIDTH * 2 + 1);
				Node<City, String> node3 = graph.getNodeList().get(idx + GRID_WIDTH * 3 + 1);
				
				graph.createEdge("o", node, node1, 50f);
				graph.createEdge("o", node, node2, 10f);
				graph.createEdge("o", node, node3, 1f);

				
				

			}
			

		}
	}

	public void render() {


		// Render nodes
		graph.render(true);
		for (int i = 0; i < graph.getNodeList().size(); i++) {
			Node<City, String> node = graph.getNodeList().get(i);

			
			//app.text(node.getCostFromStart(), node.getLocation().x,  node.getLocation().y);

		}
		

	}
	public AdjacencyListGraph<City, String> getGraph() {
		return graph;
	}

	
}
