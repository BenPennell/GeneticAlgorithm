import java.util.Random;
public class Vector
{
    Random generator = new Random();
    
    double x = (generator.nextInt(150) - 75) / 3;
    double y = (generator.nextInt(150) - 75) / 3;
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void reset() {
        double x = (generator.nextInt(150) - 75) / 3;
        double y = (generator.nextInt(150) - 75) / 3;
    }
}
