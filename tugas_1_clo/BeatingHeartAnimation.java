import java.awt.*;
import java.util.Date;

public class BeatingHeartAnimation extends Frame {
    private int x = 50;
    private int y = 250;
    private int direction = 1;
    private Double heartSize = 1.0;
    

    public BeatingHeartAnimation() {
        addWindowListener(new MyFinishWindow());
        
    }
    public void sustain(long t)
    {
        long finish = (new Date()).getTime() + t;
        while( (new Date()).getTime() < finish ){}
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        int[] xPoints = {x, x + 15, x + 30, x + 45, x + 30, x + 15};
        int[] yPoints = {y + (int) (15 * heartSize), y, y + (int) (15 * heartSize), y, y - (int) (15 * heartSize), y};
        g2d.fillPolygon(xPoints, yPoints, 6);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(50, 250, 550, 250);
    }

    public void animate() {
        while (true) {
            x += direction;
            if (x >= 530 || x <= 20) {
                direction *= -1;
            }
            double scaleIncrement = 0.02; 
            if (heartSize >= 1.2 || heartSize <= 0.8) {
                scaleIncrement *= -1;
            }
            heartSize += scaleIncrement;

            repaint();
            sustain(100);
        }
    }

    public static void main(String[] args) {
        int width = 780;
        int height = 530;
        BeatingHeartAnimation f = new BeatingHeartAnimation();
        f.setTitle("Animation based on geometric transformations");
        f.setSize(width,height);
        f.setVisible(true);
        f.animate();
        
    }
}
