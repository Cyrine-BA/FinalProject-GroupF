//This code was developed in CSC212 Taught by Prof. Crouser and Prof. Howe


import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 *  Implements a binary decision tree
 *  
 *  @author Cyrine Ben Ayed
 *  @version Spring 2022
 *
 */
public class DecisionTree extends BinaryTree<String> {
  /** leaf constructor */
  public DecisionTree(String s) {
    super(s);
  }

  /** @override */
  public void setLeft(BinaryTree<String> left) {
    if ((left==null)||(left instanceof DecisionTree)) {
      super.setLeft(left);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /** @override */
  public DecisionTree getLeft() {
    return (DecisionTree)super.getLeft();
    
  }

  /** @override */
  public void setRight(BinaryTree<String> right) {
    if ((right==null)||(right instanceof DecisionTree)) {
      super.setRight(right);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /** @override */
  public DecisionTree getRight() {
    return (DecisionTree)super.getRight();
    
  }

  /** 
   *. follows a path through a tree from the root
   *. @param s the string that tells it the path to follow
  */
  public DecisionTree followPath(String s){

    /** holds the current tree*/
    DecisionTree current = this;

  
    /** go through the tree  and return the requested node*/
    for (char C: s.toCharArray()){
      // make sure they we are not traversing a leaf
      if (current.isLeaf() && C!=s.toCharArray()[s.toCharArray().length-1]){
        throw new RuntimeException("You are trying to traverse a leaf!");
      // if we're at a branch, keep going
      } else {
        if (C=='Y'){
          current = current.getLeft();
        } else {
          current = current.getRight();
        }
      }
    }

    /** return the data in the last element */
    return current;
  }

  /** 
   *.method that generates all possible paths in a tree
   *. @return ArrayDeque with all possible paths
  */
  public ArrayDeque<String> generatePaths(){
    // the stack that will have all possible 
    ArrayDeque<String> paths = new ArrayDeque<String>();

    // add the root
    paths.addLast("");

    //add all possible paths
    for (int i=1; i<this.height(); i++){
      String path = paths.getLast();

      // if it's an empty String 
      if (path.equals("")){
        if (followPath("Y")!=null && followPath("N")!=null){
          paths.addLast("Y/N");
        } else if (followPath("N")!=null){
          paths.addLast("N");
        } else if (followPath("Y")!=null){
         paths.addLast("Y");
        }
      } else {
        String toAdd ="";
        for (String s: path.split("/")){
          if (followPath(s+"Y")!=null && followPath(s+"N")!=null){
            toAdd+= s+"Y/"+s+"N/";
          } else if (followPath(s+"N")!=null){
            toAdd+= s+"N/";
          } else if (followPath(s+"Y")!=null){
            toAdd+= s+"Y/";
          }
        }
        paths.addLast(toAdd);
      } 
    }
  
    return paths;  
  }
  
  /** 
   *. method that writes a tree to a file
   *. @param filename the name of the file to write to
  */
  public void printToFile (String filename){
    
    try {
      // set the output channel
      PrintWriter out = new PrintWriter(new FileWriter(filename));

      // loop through the file and add to doc
      ArrayDeque<String> paths = this.generatePaths();
      while (!paths.isEmpty()){
        String s = paths.removeFirst();
        for (String str: s.split("/")){
          out.println(str+ " "+ followPath(str).getData());
          
        }
      }

      //close output channel
      out.close();

    } catch (IOException e) {
      System.out.print ("An error occured please retry");
      System.exit (-1);
    } 
  }

  /** 
   *. method to read a file and make a tree
   *. @param filename the file to read from
  */
  public void readFromFile(String filename){
    try {
      // set the scanner
      Scanner reader = new Scanner(new File(filename));


      //loop through the file
      while (reader.hasNextLine()){
        String line = reader.nextLine();
        String path = line.substring(0,line.indexOf(" "));
        
        //for an empty string => create root
        if (path.equals("")){
          this.setData(line.substring(line.indexOf(" ")+1));   
        // if a one-letter long path
        } else if (path.length()==1) {
          if (path.equals("Y")){
            this.setLeft(new DecisionTree(line.substring(line.indexOf(" ")+1)));
          } else {
            this.setRight(new DecisionTree(line.substring(line.indexOf(" ")+1)));
          }
        // if more than 1 letter  
        } 
        else {
          DecisionTree parent = followPath(path.substring(0,(path.length()-1)));
          if (path.charAt(path.length()-1)=='Y'){
            parent.setLeft(new DecisionTree(line.substring(line.indexOf(" ")+1)));
          } else {
            parent.setRight(new DecisionTree(line.substring(line.indexOf(" ")+1)));
          }
        }
        
      }

      //close the scanner
      reader.close();
      
    } catch (FileNotFoundException e) {
      System.out.println("An error occured while reading the file please retry");
      System.exit (-1);
    } 
  }
}