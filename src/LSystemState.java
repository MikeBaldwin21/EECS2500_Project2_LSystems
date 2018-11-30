public class LSystemState {
	private double x;
	private double y;
	private double angle;

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAngle() {
		return angle;
	}

	public LSystemState(double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
}
