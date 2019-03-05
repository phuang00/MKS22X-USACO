import java.io.*;
import java.util.*;

public class USACO{

  public static int bronze(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner in = new Scanner(f);
    String[] vars = in.nextLine().split(" ");
    int[][] pasture = new int[Integer.parseInt(vars[0])][Integer.parseInt(vars[1])];
    for (int i = 0; i < pasture.length; i++){
      String[] tempRow = in.nextLine().split(" ");
      for (int j = 0; j < pasture[0].length; j++){
        pasture[i][j] = Integer.parseInt(tempRow[j]);
      }
      //System.out.println(Arrays.toString(pasture[i]));
    }
    while(in.hasNextLine()){
      String[] tempArgs = in.nextLine().split(" ");
      //System.out.println(tempArgs[0]);
      //System.out.println(tempArgs[1]);
      //System.out.println(tempArgs[2]);
      updateLand(pasture, Integer.parseInt(tempArgs[0]) - 1, Integer.parseInt(tempArgs[1]) - 1, Integer.parseInt(tempArgs[2]));
      /*for (int i = 0; i < pasture.length; i++){
        System.out.println(Arrays.toString(pasture[i]));
      }*/
    }
    return addElevation(pasture, Integer.parseInt(vars[2])) * 72 * 72;
  }

  private static void updateLand(int[][] land, int row, int col, int deep){
    ArrayList<Integer> eles = new ArrayList<>();
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        eles.add(land[row + i][col + j]);
      }
    }
    int target = Collections.max(eles) - deep;
    //System.out.println(target);
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        if (land[row + i][col + j] > target) land[row + i][col + j] = target;
      }
    }
  }

  public static int addElevation(int[][] land, int deep){
    int ans = 0;
    for (int i = 0; i < land.length; i++){
      for (int j = 0; j < land[i].length; j++){
        if (land[i][j] > deep) land[i][j] = 0;
        else {
          land[i][j] = deep - land[i][j];
          ans += land[i][j];
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
      System.out.println(bronze("makelake.1.in"));
    } catch (FileNotFoundException e){

    }
  }
}
