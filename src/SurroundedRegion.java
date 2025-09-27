public class SurroundedRegion {
    int rows;
    int cols;
    char[][] matrix;

    public char[][] surroundedRegion(char[][] matrix) {
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = matrix;

        // DFS from boundary
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 'O') dfs(i, 0);
            if (matrix[i][cols - 1] == 'O') dfs(i, cols - 1);
        }

        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 'O') dfs(0, j);
            if (matrix[rows - 1][j] == 'O') dfs(rows - 1, j);
        }

        // Replace values
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 'T') {
                    matrix[i][j] = 'O';
                } else if (matrix[i][j] == 'O') {
                    matrix[i][j] = 'X';
                }
            }
        }
        return matrix;
    }

    public void dfs(int i, int j) {
        if (i < 0 || j < 0 || i == rows || j == cols || matrix[i][j] != 'O') {
            return;
        }
        matrix[i][j] = 'T';
        dfs(i + 1, j);
        dfs(i - 1, j);
        dfs(i, j + 1);
        dfs(i, j - 1);
    }

    public static void main(String[] args) {
        char[][] matrix = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };
        SurroundedRegion obj = new SurroundedRegion();
        obj.surroundedRegion(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
