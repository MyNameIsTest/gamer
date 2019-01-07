package math;

public class Vec3 {
	
	
	private double[] coords;
	
	public Vec3(double x, double y, double z) {
		coords = new double[3];
		coords[0] = x;
		coords[1] = y;
		coords[2] = z;
	}
	
	
	public Vec3 plus(Vec3 that) {
		double[] result = new double[3];
		for(int i = 0; i < 3; i++) {
			result[i] = that.coords[i] + coords[i];
		}
		
		return new Vec3(result[0],result[1],result[2]);
	}
	
	public Vec3 minus(Vec3 that) {
		double[] result = new double[2];
		for(int i = 0; i < 3; i++) {
			result[i] = coords[i] - that.coords[i];
		}
		
		return new Vec3(result[0],result[1],result[2]);
	}
	
	public Vec3 scale(double alpha) {
		double[] result = new double[coords.length];
		for(int i = 0; i < 3; i++) result[i] = coords[i] * alpha;
		return new Vec3(result[0],result[1],result[2]);
	}
	
	public double dot(Vec3 that) {
		double result = 0;
		for(int i = 0; i < 3; i++) {
			result += coords[i] * that.coords[i];
		}
		
		return result;
	}
	
	public double magnitude() {
		double sum = 0;
		for(int i = 0; i < 3; i++) {
			sum += Math.pow(coords[i], 2);
		}
		return Math.sqrt(sum);
	}
	
	public Vec3 direction() {
		double[] result = new double[2];
		for(int i = 0; i < 3; i++) {
			result[i] = coords[i] / magnitude();
		}
		
		return new Vec3(result[0],result[1],result[2]);
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
	
	public double getZ() {
		return coords[2];
	}
	
	public String toString() {
		String str = "(";
		for(int i = 0; i < coords.length-1; i++) {
			str = str + coords[i] + ",";
		}
		
		str = str + coords[coords.length-1] + ")";
		return str;
	}
	
	public Vec3 cross(Vec3 v) {
		return new Vec3(this.getY() * v.getZ() - v.getY() * this.getZ(),
				this.getZ() * v.getX() - v.getZ() * this.getX(),
				this.getX() * v.getY() - v.getX() * this.getY()
				);
	}
	
	
}
