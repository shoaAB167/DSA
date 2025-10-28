import java.util.*;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                                       Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k});
        Set<Integer> visit = new HashSet<>();

        while (!pq.isEmpty()) {
            int edge[] = pq.poll();
            int time = edge[0];
            int node = edge[1];

            if (visit.contains(node)) continue;
            visit.add(node);

            if (visit.size() == n) {
                return time;
            }

            for (int[] nei : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visit.contains(nei[0])) {
                    pq.offer(new int[]{time + nei[1], nei[0]});
                }   
            }
        }

        return -1;
    }
    public static void main(String args[]){
        NetworkDelayTime obj = new NetworkDelayTime();
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4;
        int k = 2;
        System.out.println(obj.networkDelayTime(times, n, k));
    }
}
