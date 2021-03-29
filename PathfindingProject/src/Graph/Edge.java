package Graph;

public class Edge<N, E> {
	private E value;
	private Node<N, E> start;
	private Node<N, E> end;
	private float weight;

	public Node<N, E> getStart() {
		return start;
	}

	public void setStart(Node<N, E> start) {
		this.start = start;
	}

	public Node<N, E> getEnd() {
		return end;
	}

	public void setEnd(Node<N, E> end) {
		this.end = end;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	Edge(E element, Node<N, E> start, Node<N, E> end, float weight) {
		this.start = start;
		this.end = end;
		this.value = element;
		this.weight = weight;
		
	}

	public E getElement() {
		return value;
	}
	public void render(float c1, float c2, float c3) {

	}
	public String toString() {
		return (start.getElement() + "->" + end.getElement());
	}

	
}