import java.util.Random;

public class drone
{
    double x;
    double y;
    double Vx;
    double Vy;

    double w;

    double xSpeed;
    double ySpeed;

    double xAccel;
    double yAccel;

    int counter;
    int lifeSpan;

    double dist;
    double fitness;

    int frames;

    boolean done;
    
    double mutR;
    
    Ellipse circle;
    
    Vector[] dna;
    
    drone(int lifeSpan_) {
        mutR = 5;
        
        lifeSpan =  lifeSpan_;
        counter = 0;

        x = 100;
        y = 350;

        w = 4;
        
        circle = new Ellipse(x,y,w,w);

        xSpeed = 0;
        ySpeed = 0;

        xAccel = 0;
        yAccel = 0;

        dna =  new Vector[lifeSpan + 1];

        for(int i = 0; i < lifeSpan + 1; i++) {
            dna[i] = new Vector();
        }

    }

    drone(int lifeSpan_, Vector[] dna_, double mutR_) {
        lifeSpan =  lifeSpan_;
        counter = 0;
        
        mutR = mutR_;
        
        x = 100;
        y = 350;

        w = 4;
        
        circle = new Ellipse(x,y,w,w);
        
        xSpeed = 0;
        ySpeed = 0;

        xAccel = 0;
        yAccel = 0;

        dna = new Vector[lifeSpan +1];

        for(int i = 0; i < lifeSpan + 1; i++) {
            dna[i] = dna_[i];
        }
    }

    public void update(int c) {
        if(done == false) {
            Vx = dna[c].getX();
            Vy = dna[c].getY();

            this.xSpeed += Vx;
            this.ySpeed += Vy;

            this.x += this.xSpeed;
            this.y += this.ySpeed;

            edges(c);

            this.xSpeed = 0;
            this.ySpeed = 0;
        }
    }

    public void edges(int c) {
        double dX = 1000;
        double dY = 1000;

        if(x > 912.5) {
            dX = x - 912.5;
        } else {
            dX = 912.5 - x;
        }

        if(y > 347.5) {
            dY = y - 347.5;
        } else {
            dY = 347.5 - y;
        }

        dX = dX * dX;
        dY = dY * dY;
        dist = dX + dY;
        dist = Math.pow(dist, 0.5);

        if(dist <= 14.5) {
            fitness = -1;
            frames = (lifeSpan - c);
            done = true;
        }
        
        if(y > 675 || x > 975) {
            fitness = -5;
            done = true;
        }

        //if(x > 490 && x < 520 && y > 100 && y < 500) {
            //done = true;
            //fitness = -5;
        //}
    }

    public void show() {
        double tranX = this.x - circle.getX();
        double tranY = this.y - circle.getY();
        
        circle.translate(tranX, tranY);
        circle.setColor(Color.BLACK);
        circle.fill();
    }

    public void getFitness() {
        double dX = 1000;
        double dY = 1000;

        if(fitness != -1 && fitness != -5) {
            if(x > 905) {
                dX = x - 905;
            } else {
                dX = 905 - x;
            }

            if(y > 355) {
                dY = y - 355;
            } else {
                dY = 355 - y;
            }

            dX = dX * dX;
            dY = dY * dY;
            dist = dX + dY;

            dist = Math.pow(dist, 0.5);

            fitness = 1 / dist;

        } else if(fitness == -1){
            fitness = frames;
        } else {
            fitness = -5;
        }

    }

    public void birth(int k, int j, drone[] d) {
        int index = (j * 6) + k;
        
        if(frames != 0) {
            mutR = 2;
        }
        drone buffer = new drone(lifeSpan, this.dna, mutR);

        d[index] = buffer;
        d[index].mutate(d, index);
    }

    public void mutate(drone[] d, int index) {
        Random gen = new Random();

        for(int i = 0; i < dna.length; i++) {
            if(gen.nextInt(100) < mutR) {
                Vector buffer = new Vector();

                d[index].dna[i] = buffer;
            }
        }
    }

    public void respawn() {
        done = false;
        x = 100;
        y = 350;
    }
    
    public double getMax() {
        return fitness;
    }
}
