//Dijkstra algorithm using priority queue

import java.util.*;

public class Dijkstra {

    public static int[] dijkstra(int[][] matrix, int src) {
        List<List<int[]>> graph = createAdjacencyList(matrix);
        int n = graph.size();

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src}); // {distance, node}

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currDist = curr[0];
            int u = curr[1];

            if (visited.contains(u)) continue;
            visited.add(u);

            for (int[] neighbour : graph.get(u)) {
                int v = neighbour[0];
                int wt = neighbour[1];

                if (!visited.contains(v) && currDist + wt < dist[v]) {
                    dist[v] = currDist + wt;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        return dist;
    }

    public static List<List<int[]>> createAdjacencyList(int[][] matrix) {
        List<List<int[]>> result = new ArrayList<>();
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    result.get(i).add(new int[]{j, matrix[i][j]}); // neighbor, weight
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {0, 2, 0, 6, 0},
            {2, 0, 0, 8, 0},
            {0, 0, 0, 9, 0},
            {7, 8, 9, 0, 0},
            {0, 0, 0, 1, 0}
        };

        int[] dist = dijkstra(matrix, 0);
        System.out.println("Shortest distances from node 0:");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("To " + i + " = " + (dist[i] == Integer.MAX_VALUE ? "∞" : dist[i]));
        }
    }
}
