import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Container;
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
  FlowLayout experimentLayout = new FlowLayout();

  /** the buttons that we'll be using*/
  JButton yesButton = new JButton("YES");
  JButton noButton = new JButton("NO");
  JButton exitButton = new JButton("Exit");
  JButton pauseButton = new JButton("Pause");


  /** the contructor */
  public Window(String name) {
    super(name);
  }



  /**
   *. adds components to the the window
   *. @param panel to work with
   *. @param ImgFile the path of the image we want to work with
  */
  public void addComponentsToPanel(final Container panel, String Imgfile) {
    final JPanel compsToExperiment = new JPanel();
    compsToExperiment.setLayout(experimentLayout);
    experimentLayout.setAlignment(FlowLayout.TRAILING);
    JPanel controls = new JPanel();
    controls.setLayout(new FlowLayout());
       
    //Add buttons to the experiment layout
    compsToExperiment.add(exitButton);
    compsToExperiment.add(pauseButton);

    // adds the background
    ImagePanel background = new ImagePanel(new ImageIcon(Imgfile).getImage().getScaledInstance((int)600, (int)400, Image.SCALE_DEFAULT));
    background.setLayout(new FlowLayout());
    
    //Add controls to set up the component orientation in the experiment layout
   
    controls.add(yesButton);
    controls.add(noButton);
        
    //Process the YES button press
    yesButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String command = "Click yes!!";
        //Check the selection
        System.out.println(command);
        //update the experiment layout
        compsToExperiment.validate();
        compsToExperiment.repaint();
      }
    });

    //Process the NO button press
    noButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String command = "Click no!!";
        //Check the selection
        System.out.println(command);
        //update the experiment layout
        compsToExperiment.validate();
        compsToExperiment.repaint();
      }
    });

    //Process the Exit button press
    exitButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String command = "Click Exit!!";
        //Check the selection
        System.out.println(command);
        //update the experiment layout
        compsToExperiment.validate();
        compsToExperiment.repaint();
      }
    });

    //Process the Pause button press
    pauseButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String command = "Click Pause!!";
        //Check the selection
        System.out.println(command);
        //update the experiment layout
        compsToExperiment.validate();
        compsToExperiment.repaint();
      }
    });

    panel.add(compsToExperiment, BorderLayout.NORTH);
    panel.add(background, BorderLayout.CENTER);
    panel.add(controls, BorderLayout.SOUTH); ;
    
  }

  /**
   * Create the GUI and show it.  For thread safety,
   * this method should be invoked from the
   * event dispatch thread.
   *. @param imgFile the path to the image
   */
  private void createAndShowGUI(String imgFile) {
    Dimension winsize = new Dimension(600,400);

    //Create and set up the window.
    // Window frame = new Window("O' College");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Set up the content panel
    this.addComponentsToPanel(this.getContentPane(), imgFile);
          
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
  public void showWindow(String imgFile){
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
        createAndShowGUI(imgFile);
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