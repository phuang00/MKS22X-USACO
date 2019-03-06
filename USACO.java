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
    String[] vars = in.nextLine().split(" ");
    char[][] pasture = new char[Integer.parseInt(vars[0])][Integer.parseInt(vars[1])];
    int time = Integer.parseInt(vars[2]);
    for (int i = 0; i < pasture.length; i++){
      String temp = in.nextLine();
      for (int j = 0; j < pasture[i].length; j++){
        pasture[i][j] = temp.charAt(j);
      }
      //System.out.println(Arrays.toString(pasture[i]));
    }
    String[] coords = in.nextLine().split(" ");
    pasture[Integer.parseInt(coords[2]) - 1][Integer.parseInt(coords[3]) - 1] = 'E';
    return paths(pasture, Integer.parseInt(coords[0]) - 1,
                 Integer.parseInt(coords[1]) - 1,
                 Integer.parseInt(coords[2]) - 1,
                 Integer.parseInt(coords[3]) - 1, time);
  }

  private static int paths(char[][] land,int row, int col, int endR, int endC, int time){
    int ans = 0;
    int[] coords = new int[] {-1,0,1,0,0,1,0,-1};
    if (time == 0 && land[row][col] == 'E') return 1;
    if (time <= 0 || land[row][col] == '*') return 0;
    if (Math.abs(row - endR) + Math.abs(col - endC) > time) return 0;
    for (int i = 0; i < 7; i+=2){
      if (!outOfBounds(row + coords[i], col + coords[i + 1],land)){
        ans += paths(land, row + coords[i], col + coords[i + 1], endR, endC ,time - 1);
      }
    }
    return ans;
  }

  private static boolean outOfBounds(int row, int col, char[][] land){
    return (row < 0 || col < 0 || row >= land.length || col >= land[0].length);
  }

  public static void main(String[] args) {
    try{
      //System.out.println(bronze("makelake.5.in"));
      System.out.println(silver("ctravel.3.in"));
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
