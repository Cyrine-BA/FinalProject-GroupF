import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

public class Position {
  public static void main(String[] args) {
    AbstractButton jb = new JToggleButton("Press Me");
    jb.setHorizontalTextPosition(20);
    jb.setIcon(new MyIcon());
    
    
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(jb);
    f.pack();
    f.setVisible(true);
  }
}
class MyIcon implements Icon {
  public int getIconWidth() {
    return 32;
  }

  public int getIconHeight() {
    return 32;
  }

  public void paintIcon(Component c, Graphics g, int x, int y) {
    g.setColor(Color.red);
    g.fillRect(0, 0, 33, 33);
    g.drawString("java2s.com", 0, 20);
  }
}