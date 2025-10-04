import java.util.*;

public class NumberOfConnectedComponent {

    public int countComponents(int n, int[][] edges) {
        // make an adjancy list for inputs
        List<List<Integer>> adj = new ArrayList<>();
        boolean visit[] = new boolean[n];
        int count = 0;

        // create a visit array for each node
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int edge[] : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        // create for loop to traverse on each node
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                dfs(visit, adj, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(boolean visit[], List<List<Integer>> adj, int i) {
        visit[i] = true;
        for (int nei : adj.get(i)) {
            if (!visit[nei]) {
                dfs(visit, adj, nei);
            }
        }
    }

    // create dfs function to check connection for each node
    public static void main(String args[]) {
        NumberOfConnectedComponent obj = new NumberOfConnectedComponent();
        int n = 5;
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 3, 4 } };
        int[][] edges2 = { { 0, 1 }, { 3, 4 } };
        System.out.println(obj.countComponents(n, edges));
        System.out.println(obj.countComponents(n, edges2));
    }
}