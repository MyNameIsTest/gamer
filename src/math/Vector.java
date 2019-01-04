package math;

public class Vector {
	//Initializes an array to hold the vector's values
	public int[] values;
	
	
	//Default constructor uses 2 numbers for 2 dimensions
	public Vector() {
		values = new int[2];
	}
	
	//Constructor for ease of use with 2 dimensional math
	public Vector(int x, int y) {
		values = new int[2];
		values[0] = x;
		values[1] = y;
	}
	
	//Constructor for any sized vector
	public Vector(int num) {
		values = new int[num];
	}
}
