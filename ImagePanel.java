import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/** 
*. class that sets an image panel to be used as a background
*. 
*. @author anonymous
*. @version Spring 2022
*/
class ImagePanel extends JPanel {
  /** holds the image */
  private Image img;

  /** 
   *. the constructor method
   *. @param the path to thte image
  */
  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }

  /** 
   *. the constructor method
   *. @param the image itself
  */
  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }

  /** 
   *. draws the image to the window
   *. @param g the graphics object
  */
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

}
