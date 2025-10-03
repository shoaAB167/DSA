// https://neetcode.io/solutions/course-schedule-iv

import java.util.*;

public class CourseSchedule4 {
    private List<Integer>[] graph;          
    private Map<Integer, Set<Integer>> memo;


    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] pre : prerequisites) {
            graph[pre[0]].add(pre[1]);
        }

        memo = new HashMap<>();

        for (int i = 0; i < n; i++) {
            dfs(i);
        }

        List<Boolean> result = new ArrayList<>();

        
        for (int[] q : queries) {
            int u = q[0], v = q[1];
            result.add(memo.get(u).contains(v));//return true or false
        }
        return result;
    }

    private Set<Integer> dfs(int course) {
        if (memo.containsKey(course)) return memo.get(course);

        Set<Integer> prereqs = new HashSet<>();
        for (int next : graph[course]) {
            prereqs.add(next);              // direct prerequisite
            prereqs.addAll(dfs(next));      // add indirect prerequisites
        }

        memo.put(course, prereqs);
        return prereqs;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 }, { 4, 3 } };
        int[][] queries = { { 0, 4 }, { 4, 0 }, { 1, 3 }, { 3, 0 } };
        CourseSchedule4 obj = new CourseSchedule4();
        System.out.println(obj.checkIfPrerequisite(n, prerequisites, queries));
    }
}
