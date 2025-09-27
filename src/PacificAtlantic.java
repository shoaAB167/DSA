import java.util.*;

public class PacificAtlantic {

    int matrix[][];
    int rows;
    int cols;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        this.matrix = matrix;
        List<List<Integer>> result = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean pacific[][] = new boolean[rows][cols];
        boolean atlantic[][] = new boolean[rows][cols];

        for (int c = 0; c < cols; c++) {
            dfs(matrix[0][c], 0, c, pacific);
            dfs(matrix[rows - 1][c], rows - 1, c, atlantic);
        }

        for (int r = 0; r < rows; r++) {
            dfs(matrix[r][0], r, 0, pacific);
            dfs(matrix[r][cols - 1], r, cols - 1, atlantic);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] == true && atlantic[i][j] == true)
                    result.add(Arrays.asList(i, j));
            }
        }

        return result;
    }

    public void dfs(int prev, int i, int j, boolean visited[][]) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || visited[i][j] == true
                || prev > matrix[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs(matrix[i][j], i + 1, j, visited);
        dfs(matrix[i][j], i - 1, j, visited);
        dfs(matrix[i][j], i, j + 1, visited);
        dfs(matrix[i][j], i, j - 1, visited);
    }

    public static void main(String args[]) {
        int matrix[][] = { { 4, 2, 7, 3, 4 },
                { 7, 4, 6, 4, 7 },
                { 6, 3, 5, 3, 6 }
        };
        PacificAtlantic pa = new PacificAtlantic();
        List<List<Integer>> res = pa.pacificAtlantic(matrix);
        System.out.println(res);

    }
}