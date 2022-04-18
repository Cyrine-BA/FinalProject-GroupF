/**
 * class that controls the flow of the game 
 * 
 * @author Cyrine Ben Ayed and Jessica Fend
 * @version Spring 2022
*/

public class Game {
    /** holds the window */
    static Window win = new Window("O'College");

    /** holds one game day  */
    static GameDay oneDay;


    /** runs one day */
    public static void runDay(int dayNum) throws InterruptedException {

      // creates the decision tree
      oneDay = new GameDay(dayNum);
      DecisionTree dayTree = oneDay.dayTree;

      /**holds the current position of the decison tree */
      DecisionTree current = dayTree;


      while (!current.isLeaf()){
        /** holds the prompt */
        String prompt = current.getData().substring(0, current.getData().indexOf("@"));

        /** holds the image file */
        String imgFile = current.getData().substring(current.getData().indexOf("@")+1);;

        // print statements for debugging 
        System.out.println(prompt);
        System.out.println(imgFile);

        /** sets the window*/
        win.updateComponents(imgFile, prompt);

      

        //wait for response 
        waitForEnter();
          
        // get the response 
          
          System.out.println("111");
          System.out.println(win.getResponse());


          // if they answered yes
          if (win.getResponse().equals("yes")){
            current = current.getLeft();
          // if they answered no
          } else {
            current = current.getRight();
          }


          win.setResponse("");
          System.out.println("222");
          System.out.println(win.getResponse());
        }

      /** holds last line */
      String prompt = current.getData().substring(0, current.getData().indexOf("@"));

      /** holds the image path */
      String imgFile = current.getData().substring(current.getData().indexOf("@")+1);

      /** sets the window*/
      win.updateComponents(imgFile, prompt);
    
      System.out.println(prompt);
      System.out.println(imgFile);

      // wait for 3 seconds
      try { 
        Thread.sleep(5000);               
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      // tell them they have reached the end of the day
      win.updateComponents("Pictures/quad.jpeg", ".... End of the game day...");
      // wait for 3 seconds
      try { 
        Thread.sleep(4000);               
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }

    }

    
    /** method that wait for the user's click on one of the buttons */
    public static void waitForEnter() throws InterruptedException {
        synchronized(win.anyButton) {
          try { 
              win.anyButton.wait();
           } catch (InterruptedException ex) {
             System.out.println("Interrupted");
           }
        }
        
        System.out.println("After button clicked");
    }
    
    /** the main class to run our program
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main (String[] args) throws InterruptedException{
      // show the window 
      win.showWindow("Pictures/Welcome.jpeg", "Welcome to Smith!!");


      // wait for 3 seconds
      try { 
        Thread.sleep(3000);               
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }

      //loop through each day
      for (int i=1; i<7; i++){
        runDay(i);
        
      }
      //exit when done
      System.exit(0);
        
    }
    
 }