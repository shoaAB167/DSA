import java.util.*;

public class MinHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (n == 1) {
            result.add(0);
            return result;
        }

        // Step 1: Build adjacency list
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new HashSet<>());

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Step 2: Initialize leaves
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1)
                leaves.add(i);
        }

        // Step 3: Trim leaves until <= 2 nodes remain
        int remainingNodes = n;

        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();

            for (int leaf : leaves) {
                // Each leaf has exactly one neighbor
                for (int neighbor : adj.get(leaf)) {
                    adj.get(neighbor).remove(leaf); // remove leaf from neighbor’s list

                    // If neighbor now has only one connection, it becomes a new leaf
                    if (adj.get(neighbor).size() == 1) {
                        newLeaves.add(neighbor);
                    }
                }
            }

            leaves = newLeaves;
        }

        return leaves;
    }

    public static void main(String[] args) {
        MinHeightTrees sol = new MinHeightTrees();
        int[][] edges = { { 1, 0 }, { 1, 2 }, { 1, 3 } };
        System.out.println(sol.findMinHeightTrees(4, edges)); // Output: [1]
    }
}
