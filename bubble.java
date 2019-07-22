import java.awt.Color;

public class bubble
{
    int x;
    int y;
    Color c;
    int w;
    int xSpeed;
    int ySpeed;

    
    public bubble(int x, int y)
    {
        // variabels used in the drawbubbles class
       this.x = x; 
       this.y = y;
       int c1 = (int) (Math.random() * 255);
       int c2 = (int) (Math.random() * 255);
       int c3 = (int) (Math.random() * 255);
       c = new Color(c1,c2,c3);
       int randW = (int)(Math.random() * 70);
       w = randW; 
       int max = 30;
       int min =5;
       int randspeedX = (int)(Math.random() * (max-min)+1) +min;
       int randspeedY = (int)(Math.random() * (max-min)+1) +min;
       xSpeed = randspeedX;
       ySpeed = randspeedY; 
       
    }

    
}
