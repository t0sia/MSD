import java.util.Random;

public class Point {

    // TODO
    private int type;
    private Point next;
    private boolean moved;
    private int speed;


    public void move() {
        // TODO
        Random rand = new Random();
        if(this.type == 1 && !this.moved) {

            int k = rand.nextInt(15);

            if (k > 12 && this.speed >= 1) this.setSpeed(this.getSpeed() - 1);
            if (k <= 12 && this.speed < 5) this.setSpeed(this.speed + 1);

            Point temp = this;
            int i = 0;

            while(temp.next.type == 0 && i < this.speed){
                i += 1;
                temp = temp.next;
            }

            this.speed = i;

            this.type = 0;
            this.moved = true;
            temp.type = 1;
            temp.moved = true;
            temp.speed = speed;
            this.speed = 0;
        }

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

    public void setNext(Point next) {
        this.next = next;
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

    public Point getNext() {
        return next;
    }
}


