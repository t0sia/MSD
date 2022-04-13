import java.util.ArrayList;

public class Point {

	public ArrayList<Point> neighbors;
	public static Integer []types ={0,1,2,3};
	public int type;
	public int staticField;
	public boolean isPedestrian;
	boolean blocked = false;

	public Point() {
		type=0;
		staticField = 100000;
		neighbors= new ArrayList<Point>();
	}

	public void clear() {
		staticField = 100000;
	}

	public boolean calcStaticField() {
		if(this.type == 1){
			return false;
		}
		int min = this.staticField;
		for(Point p: this.neighbors){
			if(p.staticField + 1 < min)
				min = p.staticField + 1;
		}
		if (min != this.staticField) {
			this.staticField = min;
			return true;
		}
		return false;
	}

	public void move(){
		if (this.isPedestrian && !this.blocked) {
			int min = 100001;
			Point new_pos = null;
			for (Point nei : this.neighbors) {
				if ((nei.type == 0 || nei.type == 2) && nei.staticField < min) {
					min = nei.staticField;
					new_pos = nei;
				}
			}
			if (new_pos != null) {
				this.type = 0;
				if (new_pos.type == 0) {
					new_pos.isPedestrian = true;
					new_pos.type = 3;
				}
				new_pos.blocked = true;
				this.isPedestrian = false;
			}
			this.blocked = true;
		}
	}


	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
}