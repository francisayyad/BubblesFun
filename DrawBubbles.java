import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.*;
import javax.swing.Timer;

public class DrawBubbles {

    private JFrame frame;

    // The method to set up
    public DrawBubbles() {

        frame = new JFrame("Bar Graph");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // the event that triggers the end of the program
        frame.setPreferredSize(frame.getSize()); 
        frame.add(new DrawBubs(frame.getSize())); // Setting up the DrawBars public class function (getting bars and putting it in this frame)
        frame.pack();
        frame.setVisible(true);

    }

    // The main method
    public static void main(String... argv) {

        new DrawBubbles();

    }

    public static class DrawBubs extends JPanel  implements Runnable, MouseListener {

        /*
         * Declare Class Variables Here
         */
        private Thread animator;
        ArrayList<bubble> b = new ArrayList<bubble>();


        public DrawBubs(Dimension dimension) {

            setSize(dimension);
            setPreferredSize(dimension);
            addMouseListener(this);
            if (animator == null ) {
                animator = new Thread(this);
                animator.start();
            }

            setDoubleBuffered(true);

        }

        @Override

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;//g2 is the graphics object that we need to use

            //to draw things to the screen
            Dimension d = getSize();
            
            
            //create a background
            g2.setColor(Color.white);
            g2.fillRect(0, 0, d.width, d.height);
            
            // generates a bubble with random size and color
            for(int i = 0; i< b.size(); i++){
                bubble bub = b.get(i); 
                g2.setColor(bub.c);
             
                
                g2.fillOval(bub.x,bub.y,bub.w,bub.w);
            }
            /**/
            
            // initiates the program
            movebubble();
            detectSides();
             
        }
        
        // method for bubble movement
         public void movebubble(){
            for(int i = 0; i< b.size(); i++){
                bubble bub = b.get(i); 
                bub.x += bub.xSpeed;
                bub.y += bub.ySpeed;
            }
        }
            
        // method for collision detection
        public void detectSides(){
            for(int i = 0; i< b.size(); i++){
                bubble bub = b.get(i); 
                    if (bub.x > 600 || bub.x < 0) {
                        bub.xSpeed *= -1;
                        // extra feature 
                        // slows down the ball after bouncing on the edges to a min speed of 5
                            while (bub.ySpeed > 5){
                                bub.ySpeed = bub.ySpeed - 2;
                        }      
                    }

                    if (bub.y > 400 || bub.y < 0) {
                       bub.ySpeed *= -1;
                        while (bub.xSpeed > 5 ){
                       bub.xSpeed = bub.xSpeed - 2 ;
                    }
                }
                
                    
            }
        }
       

        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();
            bubble bub = new bubble (x,y);
            // adds a bubble when pressed on the window 
            b.add(bub);
            repaint();//updates the paint method

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

        }

        public void run() {
            long beforeTime, timeDiff, sleep;
            beforeTime = System.currentTimeMillis();
            int animationDelay = 40;
            long time = 
                System.currentTimeMillis();
            while (true) {//infinite loop
                // spriteManager.update();
                repaint();
                try {
                    time += animationDelay;
                    Thread.sleep(Math.max(0,time - 
                            System.currentTimeMillis()));
                }catch (InterruptedException e) {
                    System.out.println(e);
                }//end catch
            }//end while loop
        }//end of run

    }

}