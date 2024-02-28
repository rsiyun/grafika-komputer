import java.awt.*;
import java.awt.geom.*;

/**
* The letter D is defined by a GeneralPath and the letter and the 
* control points are drawn.
*
* @author Frank Klawonn
* Last change 01.02.2005
*/
public class Initials extends Frame
{

  //Constructor
  public Initials()
  {
    //Enables the closing of the window.
    addWindowListener(new MyFinishWindow());
  }


  public void paint(Graphics g)
  {

    Graphics2D g2d = (Graphics2D) g;

    //Use of antialiasing to have nicer lines.
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    //The lines should have a thickness of 3.0 instead of 1.0.
    BasicStroke bs = new BasicStroke(3.0f);
    g2d.setStroke(bs);


    //The control points for defining the letter.
    int xd1 =  50;
    int yd1 =  50;

    int xd2 =  50;
    int yd2 = 250;

    int xd3 = 250;
    int yd3 = 130;

    int xd4 =  50;
    int yd4 = 450;

    int xd5 =  50;
    int yd5 = 250;


    //Definition and drawing of the two curves that define the letter.
    QuadCurve2D.Double d1 = new QuadCurve2D.Double(xd1,yd1,xd3,yd3,xd2,yd2);
    g2d.draw(d1);

    QuadCurve2D.Double d2 = new QuadCurve2D.Double(xd1,yd1,xd5,yd5,xd4,yd4);
    g2d.draw(d2);

    QuadCurve2D.Double d3 = new QuadCurve2D.Double(xd2,yd2,xd2,yd5,150,yd4);
    g2d.draw(d3);

  }


  /**
  * Draws a small square around the centre (x,y).
  *
  * @param x        x-coordinate of the centre
  * @param y        y-coordinate of the centre
  * @param g2d      Graphics2D object for drawing
  */
  public static void drawSmallRect(int x, int y, Graphics2D g2d)
  {
    Rectangle rect = new Rectangle(x-4,y-3,8,8);
    g2d.fill(rect);
  }


  public static void main(String[] argv)
  {
    Initials f = new Initials();
    f.setTitle("The letter D");

    f.setSize(420,500);
    f.setVisible(true);
  }


}
