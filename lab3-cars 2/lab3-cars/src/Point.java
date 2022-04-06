import java.util.Random;

public class Point {

    // TODO
    private int type;
    private Point nextR;
    private Point nextL;
    private Point prevR;
    private Point prevL;
    private boolean moved;
    private int speed;


    public void move() {
        // TODO

    }

    public void clicked() {
        // TODO
        this.type = 1;
    }

    public void clear() {
        // TODO
        this.type = 0;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public void setType(int type) {
        this.type = type;
    }


    public int getType() {
        return type;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setNextL(Point nextL) {
        this.nextL = nextL;
    }

    public void setNextR(Point nextR) {
        this.nextR = nextR;
    }

    public void setPrevL(Point prevL) {
        this.prevL = prevL;
    }

    public void setPrevR(Point prevR) {
        this.prevR = prevR;
    }
}

