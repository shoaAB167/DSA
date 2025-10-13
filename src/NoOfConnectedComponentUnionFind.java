// Number of Connected Components In An Undirected Graph
// Solution using union and find
public class NoOfConnectedComponentUnionFind {
    private static int parent[];
    private static int rank[];

    public static void init(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++){
            rank[i] = 1;
            parent[i] = i;
        }
    }

    //to find the parent of node
    public static int find(int node) {
        int cur = node;
        while (cur != parent[cur]) {
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        return cur;
    }

    public static boolean union(int u, int v){
        int pu = find(u);
        int pv = find(v);

        //already connected because both have same parent
        if(pu == pv){
            return false;
        }
  
        if(rank[pv] > rank[pu]){
            int temp = pu;
            pu = pv;
            pv = temp;
        }

        //as it is merging so rank of pv will be added in rank of pu
        rank[pu] += rank[pv];

        return true;
    }

    public static void main(String args[]){
        int n = 5;
        int edges[][] = {{0,1}, {1,2}, {3,4}};
        init(n);
        int res = n;
        for(int edge[] : edges){
            if(union(edge[0], edge[1])){
                res--;
            }
        }
        System.out.println(res);
    }
}