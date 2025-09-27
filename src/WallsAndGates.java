import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    public void wallsAndGates(int grid[][]){
        Queue<int []> queue = new LinkedList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == 0){
                    queue.add(new int[]{i,j});
                }
            }
        }
        if(queue.size() == 0) return;  
        int directions[][] = {{1,0},{-1,0},{0,1},{0,-1}}; 

        while(!queue.isEmpty()){
            int curr[] = queue.poll();
            int r = curr[0];
            int c = curr[1];
            for(int d[] : directions){
                int nr = r + d[0];
                int nc = c + d[1];
                if(nr < 0 || nc < 0 || nr >= rows || nc >= cols || grid[nr][nc] != Integer.MAX_VALUE) continue;
                grid[nr][nc] = grid[r][c] + 1;
                queue.add(new int[]{nr,nc});
            }
        }

    }
    public static void main(String[] args) {
        int[][] grid = {{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}}; 
        WallsAndGates obj = new WallsAndGates();
        obj.wallsAndGates(grid);
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                System.out.print(grid[i][j] + " ");
            }
        }
    }
}