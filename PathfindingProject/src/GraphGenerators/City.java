package GraphGenerators;

public class City {
	public String name;
	public float x;
	public float y;
	public City(String name, float x, float y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return name;
	}
}
