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

  public static int silver(String filename){
    return -1;
  }

  public static void main(String[] args) {
    try{
      System.out.println(bronze("makelake.5.in"));
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
