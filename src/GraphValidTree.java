//https://leetcode.com/problems/graph-valid-tree/

import java.util.*;

public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }

        List<List<Integer>> adj = new ArrayList<>();
        Set<Integer> visit = new HashSet<>();

        //added empty array list to store adjancy node for node
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int edge[] : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        if (!dfs(0, -1, visit, adj)) {
            return false;
        }
        
       return visit.size() == n;
    }

    private boolean dfs(int node, int parent, Set<Integer> visit, List<List<Integer>> adj) {
        if(visit.contains(node)){
            return false;
        }

        visit.add(node);

        List<Integer> list = adj.get(node);

        for(int ele : list){
            if(ele == parent){
                continue;
            }
            if(!dfs(ele, node, visit, adj)){
                return false;
            }
        }

        return true;
    }

    private boolean solveBfs(int n, int[][] edges) {
        if(edges.length != n-1){
            return false;
        }
        List<List<Integer>> adj = new ArrayList<>();
        Set<Integer> visit = new HashSet<>();

        //added empty array list to store adjancy node for node
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int edge[] : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Queue<int []> queue = new LinkedList<>();

        queue.offer(new int[]{0,-1});

        while(!queue.isEmpty()){
            int poll[] = queue.poll();
            int node = poll[0];
            int parent = poll[1];

            if(visit.contains(node)){
                return false;
            }

            visit.add(node);

            List<Integer> neighbours = adj.get(node);

            for(int nei : neighbours){
                if(parent == nei){
                    continue;
                }
                queue.offer(new int[]{nei, node});
            }
        }
        return visit.size() == n;        
    }

    public static void main(String[] args) {
        GraphValidTree obj = new GraphValidTree();
        System.out.println(obj.validTree(5, new int[][]{{0,1},{0,2},{0,3},{1,4}})); // true
        System.out.println(obj.validTree(5, new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}})); // false (cycle)
        System.out.println(obj.validTree(4, new int[][]{{0,1},{2,3}})); // false (disconnected)
        System.out.println(obj.solveBfs(5, new int[][]{{0,1},{0,2},{0,3},{1,4}})); // true
        System.out.println(obj.solveBfs(5, new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}})); // false (cycle)
        System.out.println(obj.solveBfs(4, new int[][]{{0,1},{2,3}})); // false (disconnected)
    }
}
