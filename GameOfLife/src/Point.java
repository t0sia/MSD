import java.util.ArrayList;
import java.util.Random;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	
	public Point() {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState() {
		//TODO: insert logic which updates according to currentState and
		//number of active neighbors
		if (this.getState() == 0){
			if (this.activeNeighbors() <= 3 && this.activeNeighbors() >= 3) {
				nextState = 1;
			}
			else nextState = 0;
		}
		else{
			if (this.activeNeighbors() >= 2 && this.activeNeighbors() <= 3) {
				nextState = 1;
			}
			else nextState = 0;
		}
	}

	public void calculateNewStateRain(){
		if(this.getState() > 0)
			nextState = currentState - 1;

		if (neighbors.size() > 0)
			if(this.getState() == 0 && neighbors.get(0).getState() > 0)
				nextState = 6;
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point
	public int activeNeighbors(){
		int sum = 0;
		for(int i = 0; i < neighbors.size(); i++)
			if(neighbors.get(i).getState() == 1)
				sum += 1;
		return sum;
	}

	public void drop(){
		Random rand = new Random();
		int prob = rand.nextInt(20);
		if( prob == 1 ) {
			currentState = 6;
		}
	}
}
