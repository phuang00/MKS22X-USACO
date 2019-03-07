import java.io.*;
import java.util.*;

public class USACO{

  public static int bronze(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner in = new Scanner(f);
    // set up scanner
    String[] vars = in.nextLine().split(" ");
    // read in first line of file
    int[][] pasture = new int[Integer.parseInt(vars[0])][Integer.parseInt(vars[1])];
    // 2D array pasture is set to dimensions R by C
    for (int i = 0; i < pasture.length; i++){
      // for every row in pasture
      String[] tempRow = in.nextLine().split(" ");
      // read in next line
      for (int j = 0; j < pasture[0].length; j++){
        // for every column
        pasture[i][j] = Integer.parseInt(tempRow[j]);
        // add in corresponding elevation
      }
      //System.out.println(Arrays.toString(pasture[i]));
    }
    while(in.hasNextLine()){
      // while there is still another line in the file
      String[] tempArgs = in.nextLine().split(" ");
      //System.out.println(tempArgs[0]);
      //System.out.println(tempArgs[1]);
      //System.out.println(tempArgs[2]);
      updateLand(pasture, Integer.parseInt(tempArgs[0]) - 1, Integer.parseInt(tempArgs[1]) - 1, Integer.parseInt(tempArgs[2]));
      // call helper that updates the land being stomped
      /*for (int i = 0; i < pasture.length; i++){
        System.out.println(Arrays.toString(pasture[i]));
      }*/
    }
    return addElevation(pasture, Integer.parseInt(vars[2])) * 72 * 72;
    // return the aggregated depth multiplied by 72 in by 72 in (the volume)
  }

  private static void updateLand(int[][] land, int row, int col, int deep){
    ArrayList<Integer> eles = new ArrayList<>();
    // create an empty ArrayList to store the 9 elevations
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        eles.add(land[row + i][col + j]);
        // add the 9 elevations to the ArrayList
      }
    }
    int target = Collections.max(eles) - deep;
    // target value is equal to the max elevation minus deep (max the elevation can decrease by)
    //System.out.println(target);
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        // for every elevation in the 3 by 3 grid
        if (land[row + i][col + j] > target) land[row + i][col + j] = target;
        // if the elevation is greater than the target value, set it to the target
      }
    }
  }

  public static int addElevation(int[][] land, int deep){
    int ans = 0;
    // ans variable to be returned
    for (int i = 0; i < land.length; i++){
      for (int j = 0; j < land[i].length; j++){
        // for every elevation in the grid
        if (land[i][j] > deep) land[i][j] = 0;
        // if the elevation is greater than the lake's final water level, set it to zero
        else {
          // else
          land[i][j] = deep - land[i][j];
          // set elevation to the final water level minus itself (to give depth)
          ans += land[i][j];
          // and then add it to the answer
        }
      }
    }
    /*for (int i = 0; i < land.length; i++){
      System.out.println(Arrays.toString(land[i]));
    }
    System.out.println(ans);*/
    return ans;
  }

  public static int silver(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner in = new Scanner(f);
    // set up scanner
    String[] vars = in.nextLine().split(" ");
    // read in first line
    int[][] ways = new int[Integer.parseInt(vars[0])][Integer.parseInt(vars[1])];
    // create 2D int array with dimensions N by M
    int time = Integer.parseInt(vars[2]);
    // set variable time to T
    for (int i = 0; i < ways.length; i++){
      // for every row of ways
      String temp = in.nextLine();
      // set a temporary String to the next line of file
      for (int j = 0; j < ways[i].length; j++){
        // for every column of ways
        if (temp.charAt(j) == '*') ways[i][j] = -1;
        // if there is a tree at that position, set the position to -1 in ways
      }
      //System.out.println(Arrays.toString(pasture[i]));
    }
    String[] coords = in.nextLine().split(" ");
    // read in the last line and split it based on spaces
    ways[Integer.parseInt(coords[0]) - 1][Integer.parseInt(coords[1]) - 1] = 1;
    for (int i = 0; i < time; i++){
      ways = updateWays(ways);
      /*for (int j = 0; j < ways.length; j++){
        System.out.println(Arrays.toString(ways[j]));
      }
      System.out.println();*/
    }
    return ways[Integer.parseInt(coords[2]) -1][Integer.parseInt(coords[3])-1];
  }

  public static int[][] updateWays(int[][] oldWays){
    int[][] ways = new int[oldWays.length][oldWays[0].length];
    int[] coords = new int[] {0, -1, -1, 0, 0, 1, 1, 0};
    for (int i = 0; i < ways.length; i++){
      for (int j = 0; j < ways[i].length; j++){
        if (oldWays[i][j] == -1) ways[i][j] = oldWays[i][j];
        else{
          int temp = 0;
          for (int x = 0; x < 8; x+=2){
            int newR = i + coords[x];
            int newC = j + coords[x + 1];
            if (!outOfBounds(newR, newC, oldWays)){
              if (oldWays[newR][newC] != -1) temp += oldWays[newR][newC];
            }
          }
          ways[i][j] = temp;
        }
      }
    }
    return ways;
  }

  private static boolean outOfBounds(int row, int col, int[][] land){
    return (row < 0 || col < 0 || row >= land.length || col >= land[0].length);
  }

  public static void main(String[] args) {
    try{
      //System.out.println(bronze("makelake.5.in"));
      System.out.println(silver("ctravel.4.in"));
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
