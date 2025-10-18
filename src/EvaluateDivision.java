import java.util.*;

public class EvaluateDivision {

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, List<Pair<String, Double>>> adj = new HashMap<>();

        // Build graph
        for (int i = 0; i < equations.length; i++) {
            String first = equations[i][0];
            String second = equations[i][1];
            double value = values[i];

            adj.putIfAbsent(first, new ArrayList<>());
            adj.putIfAbsent(second, new ArrayList<>());

            adj.get(first).add(new Pair<>(second, value));
            adj.get(second).add(new Pair<>(first, 1.0 / value));
        }

        double[] output = new double[queries.length];

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            String src = queries[i][0];
            String dst = queries[i][1];

            if (!adj.containsKey(src) || !adj.containsKey(dst)) {
                output[i] = -1.0;
            } else if (src.equals(dst)) {
                output[i] = 1.0;
            } else {
                Set<String> visited = new HashSet<>();
                double result = dfs(src, dst, adj, 1.0, visited);
                output[i] = (result == 0.0 ? -1.0 : result);
            }
        }

        return output;
    }

    private double dfs(String src, String dst, Map<String, List<Pair<String, Double>>> adj,
                       double product, Set<String> visited) {
        //when c == c return product
        if (src.equals(dst)) return product;

        visited.add(src);

        for (Pair<String, Double> neighbor : adj.get(src)) {
            if (!visited.contains(neighbor.first)) {
                double result = dfs(neighbor.first, dst, adj, product * neighbor.second, visited);
                if (result != 0.0) return result;
            }
        }

        return 0.0; // not found
    }

    public static void main(String[] args) {
        String[][] equations = { {"a", "b"}, {"b", "c"} };
        double[] values = { 2.0, 3.0 };
        String[][] queries = {
            {"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}
        };

        EvaluateDivision obj = new EvaluateDivision();
        System.out.println(Arrays.toString(obj.calcEquation(equations, values, queries)));
    }
}

class Pair<U, V> {
    public U first;
    public V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}
