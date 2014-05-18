import java.util.*;

/**
 * This class computes the times required for List12 and ArrayList additions
 * @author Jimmy Li cs12sdm
*/

public class TimeList
{

/**
 * This method determines which addition to perform and calls another method
 * for calculation
*/

  public static void main(String[] args) 
  {
    List listChoice = null;
    boolean errorFound = false;
    boolean front = true;
    //If first argument is "array" then create an ArrayList
    if(args[0].equals("array")) {
      listChoice = new ArrayList();
    }
    //If first argument is "linked" then create List12 
    else if(args[0].equals("linked")) {
      listChoice = new List12();
    }
    else {
      System.out.println("Error");
      errorFound = true;
    }
    //If second argument is "front" then perform front addition
    if(args[1].equals("front")) {
      front = true;
    }
    //If second argument is "back" then perform end of list addition
    else if(args[1].equals("back")) {
      front = false;
    }
    else {
      System.out.println("Error");
      errorFound = true;
    }
    if(errorFound) {
      System.exit(1);
    }
    //Call method to perform time calculation
    timeList(listChoice,front);
  }

/**
 * This method calculates the time needed to perform the passed in operation
 * for the passed in list type and displays the results
*/

  public static void timeList(List listChoice, boolean front)
  {
  //Adds N elements to list with N starting at 2000 incrementing by 1000 to
  //N equals 50000
  for(int i = 2000; i <=50000; i+=1000) {
    int R = 100000/i;
    double sum = 0;
    double squaredSum = 0;
    //Runs the test R times for the same number of elements added to the list
    for(int r = 0; r < R; r++) {
      //clear listChoice
      listChoice.clear();
      //garbage collector
      System.gc();
      //Perform front addition and compute the time
      if(front) {
        long startTime = System.nanoTime();
        for(int added = 0; added < i; added++) {
          listChoice.add(0,null);
        }
        long endTime = System.nanoTime();
        double seconds = (endTime - startTime)/1.0e9;
        sum += seconds;
        squaredSum += seconds*seconds;
      }
      //Perform back addition and compute the time
      else {
        long startTime = System.nanoTime();
        for(int added = 0; added < i; added++) {
          listChoice.add(null);
        }
        long endTime = System.nanoTime();
        double seconds = (endTime - startTime) /1.0e9;
        sum += seconds;
        squaredSum += seconds*seconds;
      }
    }
    //Find average and standard deviation
    double average = sum/R;
    double squaredSumAvg = squaredSum/R;
    double stdDev = Math.sqrt(squaredSumAvg -(average*average));
    //Prints out the results in certain number of decimals 
    System.out.format("%d %.6f %.6f\n",i ,average, stdDev);
    }
  }
}
