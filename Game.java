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

      //tests the buttons
      Tests.checkButton(win.choices);
    

      //wait for response 
      waitForEnter();
        
      // get the response 
        //for testing and debugging
        // System.out.println(win.getResponse());


        // if they answered yes
        if (win.getResponse().equals("yes")){
          current = current.getLeft();
        // if they answered no
        } else {
          current = current.getRight();
        }


        win.setResponse("");
        // for debugging and testing
        // System.out.println(win.getResponse());
      }

    /** holds last line */
    String prompt = current.getData().substring(0, current.getData().indexOf("@"));

    /** holds the image path */
    String imgFile = current.getData().substring(current.getData().indexOf("@")+1, current.getData().indexOf("#"));

    /** holds the score update */
    String score_update = current.getData().substring(current.getData().indexOf("#")+1);

    //for debugging 
    // System.out.println("mental: "+ String.valueOf(mental_score));
    // System.out.println("physical: "+ String.valueOf(physical_score));
    // System.out.println("social: "+ String.valueOf(social_score));
    // System.out.println("academics: "+ String.valueOf(academics_score));

    //update the score
    updateScore(score_update);

    //for debugging 
    // System.out.println("update:**"+ String.valueOf(score_update)+ "**");
    // System.out.println("mental: "+ String.valueOf(mental_score));
    // System.out.println("physical: "+ String.valueOf(physical_score));
    // System.out.println("social: "+ String.valueOf(social_score));
    // System.out.println("academics: "+ String.valueOf(academics_score));

    // System.out.println("33333333333");
    Tests.checkButton(win.choices);


    // sets the window
    win.updateComponents(imgFile, prompt, false);

    // for testing and debugging
    System.out.println(prompt);
    System.out.println(imgFile);

    // wait for 3 seconds
    try { 
      Thread.sleep(5000);        
      Tests.checkButton(win.choices);       
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
    // tell them they have reached the end of the day
    win.updateComponents("Pictures/quad.jpeg", ".... End of the game day...", false);
    // wait for 3 seconds
    try { 
      Thread.sleep(4000); 
      // for testing and debugging
      // System.out.println("666666666666");
      Tests.checkButton(win.choices);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

  }

  
  /** method that wait for the user's click on one of the buttons */
  public static void waitForEnter() throws InterruptedException {
    synchronized(win.anyButton) {
      try { 
          win.anyButton.wait();
          Tests.checkButton(win.choices);
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

  /** method that display's the player's scores and achievements */
  public static void displayScores(){

    // get each score
    String mental = "Mental Health: " + String.valueOf(mental_score);
    String physical = "Physcial Health: " + String.valueOf(physical_score);
    String academics = "Academics Performance: " + String.valueOf(academics_score);
    String social = "Social Involvment: " + String.valueOf(social_score);

    // get their acievement
    String achievement = "";

    // get maximum score
    int max_score = Math.max(mental_score, physical_score);
    max_score = Math.max(max_score, academics_score);
    max_score = Math.max(max_score, social_score);

    //update achievement 
    if (max_score == mental_score && mental_score>100){
      achievement = "Your really took care of yourself, good job!";
    } else if(max_score == physical_score && physical_score>100) {
      achievement = "You looked so fit and you're in good health, keep it up!";
    }else if(max_score == social_score && social_score>100) {
      achievement = "You're so popular now!";
    }else if(max_score == academics_score && academics_score>100) {
      achievement = "A 4.0 GPA? Dang!";
    }
    

    // display
    String message = "Game over, here's your performance: "  + mental + physical + academics + social + achievement;
    // for testing
    // System.out.println(message);
    win.updateComponents("Pictures/scores.jpeg", "<html><p style=\"width:250px\">"+message+"</p></html>", false);
    Tests.checkButton(win.choices);

    // wait for 3 seconds
    try { 
      Tests.checkButton(win.choices);   
      Thread.sleep(5000);  
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
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
    for (int i=1; i<9; i++){
      runDay(i);
    }
    Tests.checkButton(win.choices);
    

    //display scores 
    displayScores();
    Tests.checkScore(1, mental_score, physical_score, academics_score, social_score);

    //exit when done
    System.exit(0);
      
  }
    
}