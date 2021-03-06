/**
 * class that represents one game day decision tree and number
 * 
 * @version Spring 2022
 * @author Cyrine Ben Ayed and Jessica Feng
 */

public class GameDay {
    /** holds the number of the day we're in */
    int dayNum;

    /** holds the events of the day represented by the decision tree */
    DecisionTree dayTree = new DecisionTree("");
    
    /** contructor */
    public GameDay (int num){
        this.dayNum = num;
        String filename = "GameDays/Day" + String.valueOf(num) +".txt";
        this.dayTree.readFromFile(filename);
    }
}
