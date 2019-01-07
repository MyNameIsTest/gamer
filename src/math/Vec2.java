package math;

public class Vec2 {
	private double[] coords;
	
	public Vec2(double x, double y) {
		coords = new double[2];
		coords[0] = x;
		coords[1] = y;
	}
	
	
	public Vec2 plus(Vec2 that) {
		double[] result = new double[2];
		for(int i = 0; i < 2; i++) {
			result[i] = that.coords[i] + coords[i];
		}
		
		return new Vec2(result[0],result[1]);
	}
	
	public Vec2 minus(Vec2 that) {
		double[] result = new double[2];
		for(int i = 0; i < 2; i++) {
			result[i] = coords[i] - that.coords[i];
		}
		
		return new Vec2(result[0],result[1]);
	}
	
	public Vec2 scale(double alpha) {
		double[] result = new double[coords.length];
		for(int i = 0; i < 2; i++) result[i] = coords[i] * alpha;
		return new Vec2(result[0],result[1]);
	}
	
	public double dot(Vec2 that) {
		double result = 0;
		for(int i = 0; i < 2; i++) {
			result += coords[i] * that.coords[i];
		}
		
		return result;
	}
	
	public double magnitude() {
		double sum = 0;
		for(int i = 0; i < 2; i++) {
			sum += Math.pow(coords[i], 2);
		}
		return Math.sqrt(sum);
	}
	
	public Vec2 direction() {
		double[] result = new double[2];
		for(int i = 0; i < 2; i++) {
			result[i] = coords[i] / magnitude();
		}
		
		return new Vec2(result[0],result[1]);
	}
	
	public double cartesian(int i) {
		return coords[i]; 
	}
	
	public double getX() {
		return coords[0];
	}
	
	public double getY() {
		return coords[1];
	}
	
	public String toString() {
		String str = "(";
		for(int i = 0; i < coords.length-1; i++) {
			str = str + coords[i] + ",";
		}
		
		str = str + coords[coords.length-1] + ")";
		return str;
	}
	
	public Vec2 rotate(double theta) {
		return new Vec2(magnitude() * Math.cos(theta), magnitude() * Math.sin(theta));
	}
}
