import java.awt.*;
import java.awt.geom.*;

import javax.swing.Timer;

/**
 * An example for a translation applied to a rectangle. The drawing is carried
 * out
 * w.r.t. standard coordinates, not w.r.t. window coordinates.
 *
 * @author Frank Klawonn
 *         Last change 07.01.2005
 */
public class Matahari extends Frame {
  private static final int SUN_RADIUS = 20;
  private static final int PLANET_RADIUS = 10;
  private static final int ORBIT_RADIUS = 200;
  private static final int FRAMES_PER_ORBIT = 365; // Frames per one orbit of the planet
  private static final int TOTAL_FRAMES = FRAMES_PER_ORBIT * 3; // Total frames for one third of the orbit
  private int frameCount = 0;

  /**
   * Constructor
   *
   * @param height The window height.
   */
  Matahari(int height) {
    // Enables the closing of the window.
    addWindowListener(new MyFinishWindow());
    Timer timer = new Timer(20, e -> {
      frameCount++;
      if (frameCount >= TOTAL_FRAMES) {
        frameCount = 0;
      }
      repaint();
    });
    timer.start();
  }

  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    int centerX = getWidth() / 2;
    int centerY = getHeight() / 2;
    g2d.translate(centerX, centerY);

    g2d.setColor(Color.YELLOW);
    g2d.fillOval(-SUN_RADIUS, -SUN_RADIUS, SUN_RADIUS * 2, SUN_RADIUS * 2);

    double orbitAngle = 2 * Math.PI * frameCount / FRAMES_PER_ORBIT;
    int planetX = (int) (Math.cos(orbitAngle) * ORBIT_RADIUS);
    int planetY = (int) (Math.sin(orbitAngle) * ORBIT_RADIUS);

    int planetCenterX = planetX - PLANET_RADIUS;
    int planetCenterY = planetY - PLANET_RADIUS;
    g2d.setColor(Color.BLUE);
    g2d.fillOval(planetCenterX, planetCenterY, PLANET_RADIUS * 2, PLANET_RADIUS * 2);

    double pointAngle = -2 * Math.PI * frameCount / FRAMES_PER_ORBIT;
    int pointX = (int) (Math.cos(pointAngle) * PLANET_RADIUS) + planetX;
    int pointY = (int) (Math.sin(pointAngle) * PLANET_RADIUS) + planetY;

    g2d.setColor(Color.RED);
    g2d.fillOval(pointX, pointY, 3, 3);

  }

  /**
   * Draws a coordinate system.
   *
   * @param xmax x-coordinate to which the x-axis should extend.
   * @param ymax y-coordinate to which the y-axis should extend.
   * @param g2d  Graphics2D object for drawing.
   */
  public static void drawSimpleCoordinateSystem(int xmax, int ymax, Graphics2D g2d) {
    int xOffset = -100;
    int yOffset = -100;
    int step = 20;
    String s;
    // Remember the actual font.
    Font fo = g2d.getFont();
    // Use a small font.
    int fontSize = 13;
    Font fontCoordSys = new Font("serif", Font.PLAIN, fontSize);
    /*
     * Unfortunately, the transformation yUp applied to the Graphics2D object
     * will cause the letters to occur upside down. Therefore, generate an
     * upside down font which will appear correctly when drawn upside down.
     */
    // To make the font upside down, a reflection w.r.t. the x-axis is needed.
    AffineTransform flip = new AffineTransform();
    flip.setToScale(1, -1);
    // Shift the font back to the baseline after reflection.
    AffineTransform lift = new AffineTransform();
    lift.setToTranslation(0, fontSize);
    flip.preConcatenate(lift);

    // Generate the font with the letters upside down.
    Font fontUpsideDown = fontCoordSys.deriveFont(flip);

    g2d.setFont(fontUpsideDown);

    // x-axis.
    g2d.drawLine(xOffset, 0, xmax, 0);
    // Marks and labels for the x-axis.
    for (int i = xOffset + step; i <= xmax; i = i + step) {
      g2d.drawLine(i, -2, i, 2);
      if (i != 0) {
        g2d.drawString(String.valueOf(i), i - 7, -30);
      }
    }

    // y-axis.
    g2d.drawLine(0, yOffset, 0, ymax);

    // Marks and labels for the y-axis.
    for (int i = yOffset + step; i <= ymax; i = i + step) {
      g2d.drawLine(-2, i, 2, i);
      // for indention of numbers
      if (Math.abs(i) > 99) {
        s = "";
      } else {
        if (Math.abs(i) > 9) {
          s = "  ";
        } else {
          s = "    ";
        }
      }
      if (i >= 0) {
        s = s + " ";
      }
      if (i != 0) {
        g2d.drawString(s + String.valueOf(i), -25, i + -20);
      }
    }

    // Reset to the original font.
    g2d.setFont(fo);
  }

  public static void main(String[] argv) {
    int height = 280;
    Matahari f = new Matahari(height);
    f.setTitle("Example: translation");
    f.setSize(360, height);
    f.setVisible(true);
  }

}
