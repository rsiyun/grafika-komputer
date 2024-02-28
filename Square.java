import java.awt.*;
import java.awt.geom.*;

public class Square extends Frame {
    Square() {
        addWindowListener(new MyFinishWindow());
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Use of antialiasing to have nicer lines.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // The lines should have a thickness of 3.0 instead of 1.0.
        BasicStroke bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);

        // The GeneralPath to decribe the car.
        GeneralPath gp = new GeneralPath();
        gp.moveTo(50, 130);
        gp.lineTo(120,130);
        gp.curveTo(120, 130, 130, 130, 130, 120);
        gp.lineTo(130,70);
        gp.curveTo(130, 70, 130, 60, 120, 60);
        gp.lineTo(50, 60);
        gp.curveTo(50, 60, 40, 60, 40, 70);
        gp.lineTo(40, 120);
        gp.curveTo(40, 120, 40, 130, 50, 130);
        g2d.draw(gp);

        g2d.setStroke(new BasicStroke(1.0f));
        // Draw a coordinate system.
        // drawSimpleCoordinateSystem(200, 150, g2d);

    }

    public static void drawSimpleCoordinateSystem(int xmax, int ymax, Graphics2D g2d) {
        int xOffset = 30;
        int yOffset = 50;
        int step = 20;
        String s;
        // Remember the actual font.
        Font fo = g2d.getFont();
        // Use a small font.
        g2d.setFont(new Font("sansserif", Font.PLAIN, 9));
        // x-axis.
        g2d.drawLine(xOffset, yOffset, xmax, yOffset);
        // Marks and labels for the x-axis.
        for (int i = xOffset + step; i <= xmax; i = i + step) {
            g2d.drawLine(i, yOffset - 2, i, yOffset + 2);
            g2d.drawString(String.valueOf(i), i - 7, yOffset - 7);
        }

        // y-axis.
        g2d.drawLine(xOffset, yOffset, xOffset, ymax);

        // Marks and labels for the y-axis.
        s = "  "; // for indention of numbers < 100
        for (int i = yOffset + step; i <= ymax; i = i + step) {
            g2d.drawLine(xOffset - 2, i, xOffset + 2, i);
            if (i > 99) {
                s = "";
            }
            g2d.drawString(s + String.valueOf(i), xOffset - 25, i + 5);
        }

        // Reset to the original font.
        g2d.setFont(fo);
    }

    public static void main(String[] argv) {
        Square f = new Square();
        f.setTitle("GeneralPath example");
        f.setSize(250, 200);
        f.setVisible(true);
    }
}
