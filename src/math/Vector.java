package math;

public class Vector {
	private double[] coords;
	public Vector(double[] a) {
		
		coords = a;
	}
	
	public Vector(double x, double y) {
		coords = new double[2];
		coords[0] = x;
		coords[1] = y;
	}
	
	public Vector(double x, double y, double z) {
		coords = new double[3];
		coords[0] = x;
		coords[1] = y;
		coords[2] = z;
	}
	
	public Vector plus(Vector that) {
		double[] result = new double[that.coords.length>coords.length?that.coords.length:coords.length];
		for(int i = 0; i < result.length; i++) {
			if(i >= that.coords.length) result[i] = coords[i];
			else if(i >= this.coords.length) result[i] = that.coords[i];
			else result[i] = that.coords[i] + coords[i];
		}
		
		return new Vector(result);
	}
	
	public Vector minus(Vector that) {
		double[] result = new double[that.coords.length>coords.length?that.coords.length:coords.length];
		for(int i = 0; i < result.length; i++) {
			if(i >= that.coords.length) result[i] = coords[i];
			else if(i >= this.coords.length) result[i] = -that.coords[i];
			else result[i] = coords[i] - that.coords[i];
		}
		
		return new Vector(result);
	}
	
	public Vector scale(double alpha) {
		double[] result = new double[coords.length];
		for(int i = 0; i < result.length; i++) result[i] = coords[i] * alpha;
		return new Vector(result);
	}
	
	public double dot(Vector that) {
		int len = that.coords.length>coords.length?that.coords.length:coords.length;
		double result = 0;
		for(int i = 0; i < len; i++) {
			if(i < that.coords.length && i < this.coords.length) result += coords[i] * that.coords[i];
		}
		
		return result;
	}
	
	public double magnitude() {
		double sum = 0;
		for(int i = 0; i < coords.length; i++) {
			sum += Math.pow(coords[i], 2);
		}
		return Math.sqrt(sum);
	}
	
	public Vector direction() {
		double[] result = new double[coords.length];
		for(int i = 0; i < result.length; i++) {
			result[i] = coords[i] / magnitude();
		}
		
		return new Vector(result);
	}
	
	public double cartesian(int i) {
		return coords[i]; 
	}
	
	public String toString() {
		String str = "(";
		for(int i = 0; i < coords.length-1; i++) {
			str = str + coords[i] + ",";
		}
		
		str = str + coords[coords.length-1] + ")";
		return str;
	}
	
	public Vector rotate(double theta) {
		return new Vector(magnitude() * Math.cos(theta), magnitude() * Math.sin(theta));
	}
}
