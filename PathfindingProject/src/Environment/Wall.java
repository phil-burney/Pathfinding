package Environment;

import Display.Display;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Wall {
	PVector topLeft;
	PVector bottomRight;
	float width;
	PApplet app;

	public Wall(PApplet app, PVector topLeft, PVector bottomRight) {
		this.app = app;
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}
	public void render() {
		PShape s = app.createShape();
		app.fill(255, 255, 0);

		s.beginShape();
		
		app.strokeWeight(1);
		
		s.vertex(topLeft.x, topLeft.y);
		s.vertex(topLeft.x, bottomRight.y);
		s.vertex(bottomRight.x, bottomRight.y);
		s.vertex(bottomRight.x, topLeft.y);
		
		
		s.endShape();
		app.shape(s);
		
	}
	public boolean inWall(float x, float y, float buffer) {
		if (x < this.bottomRight.x + buffer && x > this.topLeft.x - buffer && y < this.bottomRight.y + buffer && y > this.topLeft.y - buffer) {
			return true;
		}
		return false;
	}

}
