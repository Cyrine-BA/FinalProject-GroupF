//class to be deleted
// /*
// *. class that controls the flow of the game (the game days)
// *.
// *.@version Spring 2022
// *.@author Cyrine Ben Ayed & Jessica Feng
// */
// public class GameScenes {

//   // runs the game
//   public static void main(String[] args) throws InterruptedException{

//     // /** creates the window */
//     Window win = new Window("O'College");
//     win.showWindow("Pictures/Day1.8.jpeg", "Welcome to Smith!!");
    
//     // wait for 3 seconds
//      try { 
//      Thread.sleep(3000);               
//     } catch(InterruptedException ex) {
//       Thread.currentThread().interrupt();
//     }

//     /** Scanner to read input from console */
//     //Scanner in = new Scanner (System.in);

//     /** tells us whether we they want to continue playing*/
//     boolean nextDay = true;

//     /** keeps track of what day we're playing*/
//     int i=0;

//     // first line
//     System.out.println("Welcome to the game");
//     System.out.println();

//     while (nextDay && i<8){
//       // increment the counter
//       i++;

//       // display the # of the game day 
//       System.out.println("Day "+String.valueOf(i)+ "\n");

//       /** creates a tree instance*/
//       DecisionTree tree = new DecisionTree("");

//       /** holds the filename*/
//       String filename = "GameDays/Day" +String.valueOf(i)+".txt";

//       // read from file
//       tree.readFromFile(filename);

//       /** holds the current position*/
//       DecisionTree current = tree;

//       // run one round of guessing
//       while (!current.isLeaf()){
//         /** holds the prompt */
//         String prompt = current.getData();

//         String imgFile = "Pictures/Day1.1.jpeg";

//         /** sets the window*/
//         win.updateComponents(imgFile, prompt);
        
//         System.out.println(prompt);
//         System.out.println(imgFile);
//         //String response = in.nextLine();
//         // check that their answer is valid
//         //while (!YesOrNo(response)){
//           //System.out.println("Please make sure you enter a valid yes or no response");
//           //response = in.nextLine();
//         //}

//         waitForEnter();

//         String response = win.getResponse();

//         System.out.println("111");
//         System.out.println(win.getResponse());
//         // if they answered yes
//         if (response.toUpperCase().equals("Y") || response.toUpperCase().equals("YES")){
//           current = current.getLeft();
//         // if they answered no
//         } else {
//           current = current.getRight();
//         }
//         win.setResponse();
//         System.out.println("222");
//         System.out.println(win.getResponse());
//       }

//       /** holds last line */
//       String prompt = current.getData();

//       /** holds the image path */
//       String imgFile = current.getData().substring(current.getData().indexOf("@")+1);

//       /** sets the window*/
//       win.updateComponents(imgFile, prompt);
      
//       System.out.println(prompt);
//       System.out.println(imgFile);

    
//       // ask if they want to play again
//       System.out.println("Next day?");
//       //String response = in.nextLine();
  
//       // check that their answer is valid
//       //while (!YesOrNo(response)){
//       //System.out.println("Please make sure you enter a valid yes or no response");
//       //response = in.nextLine();
//       //}
      
//       waitForEnter();

//       String response = win.getResponse();

//       System.out.println("111");
//       System.out.println(win.getResponse());
//       // if they answered yes
//       //if (response.toUpperCase().equals("Y") || response.toUpperCase().equals("YES")){
//         //current = current.getLeft();
//       // if they answered no
//       if (response.toUpperCase().equals("N") || response.toUpperCase().equals("NO")){
//         nextDay = false;
//       }        
//       win.setResponse();
//       System.out.println("222");
//       System.out.println(win.getResponse());
//       response = win.getResponse();

//     }

      

//       // if they answered no stop the game     

//     // close scanner 
//     //in.close();
      
//   }


//   // function that checks whether it's a yes or no input*/
//   public static boolean YesOrNo(String s){
//     return s.toUpperCase().equals("Y") || s.toUpperCase().equals("N") || s.toUpperCase().equals("YES") || s.toUpperCase().equals("NO");
//   }
  
//   public static void waitForEnter() throws InterruptedException {
//     synchronized(Window.yesButton) {
//       try { 
//         Window.yesButton.wait();
//       } catch (InterruptedException ex) {
//         System.out.println("Interrupted");
//       }
//     }
//     synchronized(Window.noButton) {
//       try { 
//         Window.yesButton.wait();
//       } catch (InterruptedException ex) {
//         System.out.println("Interrupted");
//       }
//     }

//     System.out.println("After button clicked");
//   }
  
  
// }