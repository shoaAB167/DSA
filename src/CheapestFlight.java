// import java.util.*;

// public class CheapestFlight {

//     public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

//         // Build adjacency list
//         Map<Integer, List<int[]>> graph = new HashMap<>();
//         for (int[] edge : flights) {
//             graph.putIfAbsent(edge[0], new ArrayList<>());
//             graph.get(edge[0]).add(new int[] { edge[1], edge[2] }); // [destination, cost]
//         }

//         // Min-heap sorted by total cost so far
//         PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
//         pq.offer(new int[] { 0, src, 0 }); // {totalCost, node, stops}

//         int[] minCost = new int[n];
//         Arrays.fill(minCost, Integer.MAX_VALUE);
//         minCost[src] = 0;

//         while (!pq.isEmpty()) {
//             int[] curr = pq.poll();
//             int cost = curr[0];
//             int node = curr[1];
//             int stops = curr[2];

//             if (node == dst) return cost;
//             if (stops > k) continue;

//             if (!graph.containsKey(node)) continue;

//             for (int[] nei : graph.get(node)) {
//                 int next = nei[0];
//                 int price = nei[1];
//                 int newCost = cost + price;

//                 if (newCost < minCost[next] || stops < k) {
//                     minCost[next] = newCost;
//                     pq.offer(new int[] { newCost, next, stops + 1 });
//                 }
//             }
//         }

//         return -1;
//     }

//     public static void main(String[] args) {
//         CheapestFlight obj = new CheapestFlight();
//         int[][] flights = { { 0, 1, 100 }, { 1, 2, 100 }, { 0, 2, 500 } };
//         int n = 3;
//         int src = 0, dst = 2, k = 0;
//         System.out.println(obj.findCheapestPrice(n, flights, src, dst, k)); // Expected output: 500
//     }
// }


import java.util.*;

public class CheapestFlight {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Build adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] f : flights) {
            graph.putIfAbsent(f[0], new ArrayList<>());
            graph.get(f[0]).add(new int[]{f[1], f[2]}); // [destination, cost]
        }

        // minCost[i] = minimum cost to reach city i so far
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        // Queue for BFS: {currentCity, totalCost, stops}
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, 0, 0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int city = curr[0];
            int cost = curr[1];
            int stops = curr[2];

            // If we already used k stops, don’t go further
            if (stops > k) continue;

            // Explore neighbors
            if (!graph.containsKey(city)) continue;

            for (int[] nei : graph.get(city)) {
                int nextCity = nei[0];
                int price = nei[1];
                int newCost = cost + price;

                // Only explore this path if it’s cheaper
                if (newCost < minCost[nextCity]) {
                    minCost[nextCity] = newCost;
                    q.offer(new int[]{nextCity, newCost, stops + 1});
                }
            }
        }

        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }

    public static void main(String[] args) {
        CheapestFlight obj = new CheapestFlight();
        int[][] flights = { {0, 1, 100}, {1, 2, 100}, {0, 2, 500} };
        int n = 3, src = 0, dst = 2, k = 1;
        System.out.println(obj.findCheapestPrice(n, flights, src, dst, k)); // Output: 200
    }
}
