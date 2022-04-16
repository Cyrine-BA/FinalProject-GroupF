import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/** 
*. class that creates and controls the window 
*. 
*. @author Cyrine Ben Ayed and Jessica Feng
*. @version Spring 2022
*/
public class Window extends JFrame{

  /** arranges components in a left-to-right flow*/
  JPanel controls = new JPanel();
  JPanel image = new JPanel();
  JPanel choices = new JPanel();
  JLabel text = new JLabel("", null, JLabel.CENTER);
  
  /** the buttons that we'll be using*/
  static JButton yesButton = new JButton("YES");
  static JButton noButton = new JButton("NO");
  static JButton exitButton = new JButton("Exit");
  static JButton pauseButton = new JButton("Pause");

  String response = "";


  /** the contructor */
  public Window(String name) {
    super(name);
  }

  public void setUp() {

    controls.setLayout(new FlowLayout());
    //setAlignment(FlowLayout.TRAILING)
    image.setLayout(new FlowLayout());
    choices.setLayout(new FlowLayout());

    text.setForeground(Color.black);
    text.setFont(new Font("SansSerif", Font.BOLD, 16));
    text.setAlignmentX(0.5f);
    text.setAlignmentY(0.5f);
    text.setVerticalAlignment(text.CENTER);


    //Add buttons to the experiment layout
    controls.add(exitButton);
    controls.add(pauseButton);
    //Add controls to set up the component orientation in the experiment layout
    choices.add(yesButton);
    choices.add(noButton);

    image.add(text, BorderLayout.CENTER );

    this.getContentPane().add(controls, BorderLayout.NORTH);
    this.getContentPane().add(image, BorderLayout.CENTER);
    this.getContentPane().add(choices, BorderLayout.SOUTH);
    // panel.add(message, BorderLayout.CENTER);


    //Process the Yes button press
    yesButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String command = "Click yes!!";
        //Check the selection
        System.out.println(command);
        response = "yes";
        synchronized (yesButton) {
          yesButton.notify();
        }
      }
    });

    //Process the No button press
    noButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String command = "Click no!!";
        //Check the selection
        System.out.println(command);
        response = "no";
        synchronized (noButton) {
          noButton.notify();
        }

      }
    });

    //Process the Exit button press
    exitButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String command = "Click Exit!!";
        //Check the selection
        System.out.println(command);

      }
    });

    //Process the Pause button press
    pauseButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String command = "Click Pause!!";
        //Check the selection
        System.out.println(command);
      }
    });

  }

  /**
   *. changes the background image and text
   *. @param imgFile the path of the image we want to work with
   *. @param message the text
  */
  public void updateComponents(String imgFile, String message) {
    
    ImageIcon temp = new ImageIcon(imgFile);
    Image temp2 = temp.getImage().getScaledInstance((int)600, (int)400, Image.SCALE_SMOOTH);
    ImageIcon img = new ImageIcon(temp2);
    text.setText(message);
    text.setIcon(img);

  }

  public String getResponse() {
    return response;
  }

  public String setResponse() {
    response = "";
    return response;
  }

  /**
   * Create the GUI and show it.  For thread safety,
   * this method should be invoked from the
   * event dispatch thread.
   *. @param imgFile the path to the image
   */
  private  void createAndShowGUI(String imgFile, String message) {
    Dimension winsize = new Dimension(600,400);
    
    //Create and set up the window.
    // Window frame = new Window("O' College");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Set up the content panel
    this.setUp();
    this.updateComponents(imgFile, message);
          
    this.setPreferredSize(winsize);
    this.setMinimumSize(winsize);

    this.getContentPane().setPreferredSize(new Dimension(600, 400));
    
    //Display the window.
    this.pack();
    this.setVisible(true);
  }
    
  /*
   *. creates and shows the window 
   *. @param the path to the image file 
  */
  public void showWindow(String imgFile, String message){
    /* Use an appropriate Look and Feel */
    try {
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } catch (UnsupportedLookAndFeelException ex) {
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      ex.printStackTrace();
    } catch (InstantiationException ex) {
      ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
     ex.printStackTrace();
    }
    
    /* Turn off metal's use of bold fonts */
    UIManager.put("swing.boldMetal", Boolean.FALSE);
    //Schedule a job for the event dispatchi thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI(imgFile, message);
      }
    });
    
  }



  // public void changeBackground(String imgFile, Window frame){
  //   ImagePanel newBackground = new ImagePanel(new ImageIcon(imgFile).getImage().getScaledInstance((int)600, (int)400, Image.SCALE_DEFAULT));
  //   System.out.println(newBackground);
  //   frame.add(newBackground, BorderLayout.CENTER);
  //   System.out.println(frame);
  //   frame.revalidate();
  //   frame.repaint();
  // }

}