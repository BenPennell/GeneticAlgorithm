import java.util.Random;

public class Evolution
{
    drone[] d = new drone[1000];

    Ellipse end;

    int counter = 0;
    int lifeSpan = 150;
    int gen = 1;
    double fit = 0;
    
    Random generator = new Random();

    public static void main(String[] args) {
        Evolution run = new Evolution();

        run.drawBorders();

        run.run();
    }

    void run() {
        for(int i = 0; i < d.length; i++) {
            d[i] = new drone(lifeSpan);
        }

        for(int i = 0; i < 100; i++) {
            draw();
        }
    }

    void draw() {
        background();
        
        Text generation = new Text(475,10, "Generation " + gen);
        generation.draw();
        
        Text maxF = new Text(10,10, "Previous max Fitness: " + fit);
        maxF.draw();

        end = new Ellipse(900,335,25,25);
        end.setColor(Color.GREEN);
        end.fill();

        //Rectangle opstacle = new Rectangle(490,100,30,400);
        //opstacle.draw();
        
        for(counter = 0; counter < lifeSpan; counter++) {
            for(int i = 0; i < d.length; i++) {
                d[i].update(counter);
                d[i].show();
            }
        }

        for(int o = 0; o < d.length; o++) {
            d[o].getFitness();
        }

        sort(d);
        
        fit = d[0].getMax();
        
        for(int k = 0; k < lifeSpan/10; k++) {
            for(int j = 1; j < 10; j++) {
                d[k].birth(k, j, d);
            }
        }

        for(int i = 0; i < d.length; i++) {
            d[i].respawn();
        }

        gen++;
    }

    void drawBorders() {
        // drawing borders
        Line topBorder = new Line(0, 0, 1000, 0);
        Line lSideBorder = new Line(0, 0, 0, 700);
        Line rSideBorder = new Line(1000, 0, 1000, 700);
        Line bottomBorder = new Line(0, 700, 1000, 700);

        topBorder.draw();
        lSideBorder.draw();
        rSideBorder.draw();
        bottomBorder.draw();

        // second layer of borders so it's thicker
        Line topBorder2 = new Line(1, 1, 1001, 1);
        Line lSideBorder2 = new Line(1, 1, 1, 701);
        Line rSideBorder2 = new Line(1001, 1, 1001, 701);
        Line bottomBorder2 = new Line(1, 701, 1001, 701);

        topBorder2.draw();
        lSideBorder2.draw();
        rSideBorder2.draw();
        bottomBorder2.draw();

    } // end drawBorders

    void background() {
        Rectangle background = new Rectangle(1,1,999,699);
        background.setColor(Color.WHITE);
        background.fill();
    }

    void sort(drone[] array) {
        int check = 0;
        drone first;
        drone second;

        while(check < array.length - 1) {

            for(int i = 0; i < array.length - 1; i++) {

                double f1 = array[i+1].fitness;
                double f2 = array[i].fitness;

                if(f1 <= f2) {
                    check++;
                } else {
                    first = array[i];
                    second = array[i+1];

                    array[i] = second;
                    array[i+1] = first;

                }
            }

            if(check < array.length - 1) {
                check = 0;
            } 
        }

    }
}
