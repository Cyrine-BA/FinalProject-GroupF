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

    /** holds the academics score */
    static int academics_score = 50;

    /** holds the physical health score */
    static int physical_score = 50;

    /** holds the mental health score */
    static int mental_score = 50;

    /** holds the social score */
    static int social_score = 50;


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

        // set the window
        win.updateComponents(imgFile, prompt, true);

      

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
      String imgFile = current.getData().substring(current.getData().indexOf("@")+1, current.getData().indexOf("#"));

      /** holds the score update */
      String score_update = current.getData().substring(current.getData().indexOf("#")+1);

      //for debugging 
      System.out.println("mental: "+ String.valueOf(mental_score));
      System.out.println("physical: "+ String.valueOf(physical_score));
      System.out.println("social: "+ String.valueOf(social_score));
      System.out.println("academics: "+ String.valueOf(academics_score));

      //update the score
      updateScore(score_update);

      //for debugging 
      System.out.println("update:**"+ String.valueOf(score_update)+ "**");
      System.out.println("mental: "+ String.valueOf(mental_score));
      System.out.println("physical: "+ String.valueOf(physical_score));
      System.out.println("social: "+ String.valueOf(social_score));
      System.out.println("academics: "+ String.valueOf(academics_score));


      // sets the window
      win.updateComponents(imgFile, prompt, false);
    
      System.out.println(prompt);
      System.out.println(imgFile);

      // wait for 3 seconds
      try { 
        Thread.sleep(5000);               
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      // tell them they have reached the end of the day
      win.updateComponents("Pictures/quad.jpeg", ".... End of the game day...", false);
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
      // for debugging
      System.out.println("After button clicked");
    }

    /** 
     * updates the player's score
     * @param score_update
     */
    public static void updateScore (String score_update){
      if (score_update.equals("P1")){
        physical_score += 10;
      } else if (score_update.equals("P0")) {
        physical_score -= 10;
      } else if (score_update.equals("A1")) {
        academics_score += 10;
      } else if (score_update.equals("A0")) {
        academics_score -= 10;
      } else if (score_update.equals("S1")) {
        social_score += 10;
      } else if (score_update.equals("S0")) {
        social_score -= 10;
      } else if (score_update.equals("M1")) {
        mental_score += 10;
      } else{
        mental_score -= 10;
      } 
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