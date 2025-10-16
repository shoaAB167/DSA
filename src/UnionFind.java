public class UnionFind {
  int parent[];
  int rank[];

  public int find(int x){
    if(x != parent[x]){
        parent[x] = find(parent[x]);
    }
    return parent[x];
  }
  
  public boolean union(int u, int v){
    int pu = find(u);
    int pv = find(v);

    if(pu == pv) {
        return false;
    }

    if(rank[pv] > rank[pu]){
        parent[pu] = pv;
        rank[pv] += rank[pu];
    }else{ 
        parent[pv] = pu;
        rank[pu] += rank[pv];
    }
    return true;
  }

  UnionFind(int n){
    parent = new int[n];
    rank = new int[n];
    for(int i=0; i<n; i++){
        parent[i] = i;
        rank[i] = 1;
    }
  }
}
