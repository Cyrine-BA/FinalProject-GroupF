import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Image;


/** 
*. class that creates and controls the window 
*. 
*. @author Cyrine Ben Ayed and Jessica Feng
*. @version Spring 2022
*/
public class Window extends JFrame{

  /** panels containing different components of the window*/
  JPanel controls = new JPanel();
  JPanel image = new JPanel();
  JPanel choices = new JPanel();
  JLabel text = new JLabel("", null, JLabel.CENTER);
  
  /** the buttons that we'll be using*/
  JButton yesButton = new JButton("YES");
  JButton noButton = new JButton("NO");
  JButton exitButton = new JButton("Exit");
  JButton pauseButton = new JButton("Pause");
  JButton anyButton = new JButton("Invisible button");
  
  /** checks for the user's response */
  String response = "";


  /** the contructor */
  public Window(String name) {
    super(name);
  }
  /** method to set up our window components */
  public void setUp() {
    // set the buttons and image panels
    controls.setLayout(new FlowLayout());
    image.setLayout(new FlowLayout());
    choices.setLayout(new FlowLayout());


    // set the text
    text.setForeground(Color.black);
    text.setFont(new Font("SansSerif", Font.BOLD, 16));
    text.setAlignmentX(0.5f);
    text.setAlignmentY(0.5f);
    text.setVerticalAlignment(text.CENTER);
    text.setLayout(new FlowLayout());
    

    //Add exit and pause to the experiment layout
    controls.add(exitButton);
    controls.add(pauseButton);

    //Add  yes and no controls to set up the component orientation in the experiment layout
    choices.add(yesButton);
    choices.add(noButton);
    
    // add the bakcground image
    image.add(text);


    //adds all panels to the frame
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
        anyButton.setActionCommand("yes clicked");
        anyButton.doClick();
      }
    });

    //Process the No button press
    noButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String command = "Click no!!";
        //Check the selection
        System.out.println(command);
        setResponse("no");
        anyButton.setActionCommand("no clicked");
        anyButton.doClick();
      }
    });

    anyButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        System.out.println(anyButton.getActionCommand());
        if (anyButton.getActionCommand().equals("yes clicked")){
          setResponse("yes");
          System.out.println(getResponse());
        } else {
          setResponse("no");
          System.out.println(getResponse());
        }
        synchronized (anyButton) {
          anyButton.notifyAll();
        }
      }

    });


    //Process the Exit button press => still in progress
    exitButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String command = "Click Exit!!";

        //Check the selection
        System.out.println(command);

      }
    });

    //Process the Pause button press => still in progress
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
    text.setText("<html><p style=\"width:400px\">"+message+"</p></html>");
    text.setIcon(img);
    text.setVerticalTextPosition(JLabel.BOTTOM);
    text.setHorizontalTextPosition(JLabel.CENTER);

  }
  
  /** @return the response of the user */
  public String getResponse() {
    return this.response;
  }
  /** sets the response field */
  public String setResponse(String str) {
    this.response = str;
    return response;
  }

  /**
   * Create the GUI and show it.  For thread safety,
   * this method should be invoked from the
   * event dispatch thread.
   *. @param imgFile the path to the image
   */
  private  void createAndShowGUI(String imgFile, String message) {
    Dimension winsize = new Dimension(600,600);
    
    //setting for the window
    this.setResizable(false);
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


}