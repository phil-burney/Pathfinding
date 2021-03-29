package DynamicBehaviors;

import DynamicBehaviors.weight.WeightedMovement;
import Motion.DynamicMotion;
import Motion.Motion;
import processing.core.PApplet;
import processing.core.PVector;

public class DynamicSeek extends DynamicBehavior {
	
	PVector dest;

	public DynamicSeek(PApplet app) {
		super(app);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public WeightedMovement behavior(Motion motion) {
		PVector linear;
		
		
		if(PApplet.abs(PApplet.degrees(motion.getDirectionofMovement()) - PApplet.degrees(motion.getVectorToDest(dest).heading())) 
				> 20 && motion.getVelocity().mag() != 0) {
			linear = new PVector(motion.getVelocity().x, motion.getVelocity().y).mult(-1);
			return new WeightedMovement(linear, 0);
		}
		
		linear = motion.getVectorToDest(dest).normalize().mult(DynamicMotion.ACCEL_LIM);
		return new WeightedMovement(linear, 0);

	}
	public void setDest(PVector dest) {
		this.dest = dest;
	}

}
