//https://leetcode.com/problems/course-schedule-ii/

import java.util.*;

public class CourseSchedule2 {

    /**
     * Returns an ordering of courses or empty array if there's a cycle.
     *
     * @param numCourses the number of courses available
     * @param prerequisites a 2D array where each inner array is a prerequisite
     * @return an ordering of courses or empty array if there's a cycle
     */
    public int[] courseSchedule(int numCourses, int[][] prerequisites) {
        // Map course to its prerequisites
        Map<Integer, List<Integer>> preMap = new HashMap<>();
        for (int[] edge : prerequisites) {
            int course = edge[0];
            int prereq = edge[1];
            preMap.computeIfAbsent(course, k -> new ArrayList<>()).add(prereq);
        }

        // Booleans to detect cycles
        boolean[] visiting = new boolean[numCourses]; // on recursion stack (detect cycle)
        boolean[] visited = new boolean[numCourses];  // fully processed
        LinkedList<Integer> order = new LinkedList<>(); // final ordering (append after processing node)

        // Process each course
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (!dfs(i, preMap, visiting, visited, order)) {
                    return new int[0]; // cycle detected
                }
            }
        }

        // Convert list to int[]
        int[] result = new int[order.size()];
        for (int i = 0; i < order.size(); i++) {
            result[i] = order.get(i);
        }
        return result;
    }


    /**
     * Performs a depth-first search on the given course, detecting any cycles
     * in the prerequisites graph.
     *
     * @param course the course to search for
     * @param preMap the adjacency list of prerequisites
     * @param visiting the set of courses currently on the recursion stack
     * @param visited the set of courses that have been fully processed
     * @param order the final ordering of courses
     * @return true if the course can be finished, false otherwise
     */
    private boolean dfs(int course,
                        Map<Integer, List<Integer>> preMap,
                        boolean[] visiting,
                        boolean[] visited,
                        LinkedList<Integer> order) {
        if (visiting[course]) return false; // cycle
        if (visited[course]) return true;   // already done

        visiting[course] = true;
        List<Integer> prereqs = preMap.get(course);
        if (prereqs != null) {
            for (int p : prereqs) {
                if (!dfs(p, preMap, visiting, visited, order)) {
                    return false;
                }
            }
        }

        visiting[course] = false;
        visited[course] = true;
        order.addLast(course); // add after children => prerequisites appear before course
        return true;
    }

    public static void main(String[] args) {
        CourseSchedule2 cs = new CourseSchedule2();
        int numCourses = 4;
        int[][] prerequisites = { {1,0}, {2,0}, {3,1}, {3,2} };
        int[] order = cs.courseSchedule(numCourses, prerequisites);
        System.out.println(Arrays.toString(order)); // e.g. [0, 1, 2, 3]
    }
}
