package GraphGenerators;

import Graph.AdjacencyListGraph;

public interface GraphGenerator {
	
	public void generateGraph();

	public void render();

	public <N,E> AdjacencyListGraph<N,E> getGraph();

}
