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
      System.out.println(Arrays.toString(pasture[i]));
    }
    return -1;
  }

  public static int silver(String filename){
    return -1;
  }

  public static void main(String[] args) {
    try{
      bronze("makelake.1.in");
    } catch (FileNotFoundException e){

    }
  }
}
