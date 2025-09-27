class IslandPerimeter {
    int grid[][];
    boolean visited[][];
    int rows;
    int cols;   
    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        for(int i=0; i<rows;i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == 1){
                    return dfs(i,j);
                }   
            }
        }
        return 0;
    }

    public int dfs(int i, int j){
        if(i<0 || j<0 || i>=rows || j>=cols || grid[i][j] == 0){
            return 1;
        }
        if(visited[i][j] == true){
            return 0;
        }
        visited[i][j] = true;
        return dfs(i+1,j) + dfs(i-1,j) + dfs(i,j+1) + dfs(i,j-1);
    }

    public static void main(String args[]){
        int grid[][] = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        IslandPerimeter obj = new IslandPerimeter();
        System.out.println(obj.islandPerimeter(grid));
    }
}