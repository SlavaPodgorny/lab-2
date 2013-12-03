public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public  int getY(){
        return y;
    }

    public double distance(Coordinates coordinates){
        return Math.sqrt(Math.pow(x - coordinates.getX(), 2) +
                Math.pow(y - coordinates.getY(), 2));
    }
}
