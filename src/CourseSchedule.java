import java.util.*;

public class CourseSchedule {
    Map<Integer, List<Integer>> preMap;
    Set<Integer> visiting;
    Set<Integer> done;

    /**
     * Given a number of courses and prerequisites, this function checks if
     * all courses can be finished. It runs a depth-first search for each
     * course to detect any cycles.
     *
     * @param numOfCourses the total number of courses available
     * @param prerequisites a 2D array where each inner array is a prerequisite
     * @return true if all courses can be finished, false otherwise
     */
    public boolean canFinish(int numOfCourses, int[][] prerequisites) {
        preMap = new HashMap<>();
        visiting = new HashSet<>();
        done = new HashSet<>();

        // Build adjacency list
        for (int i = 0; i < numOfCourses; i++) {
            preMap.put(i, new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            preMap.get(pre[0]).add(pre[1]);
        }

        // Run DFS for each course
        for (int i = 0; i < numOfCourses; i++) {
            if (!dfs(i)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Runs a depth-first search for a given course, detecting any cycles
     * in the prerequisites graph.
     *
     * @param course the course to search for
     * @return true if the course can be finished, false otherwise
     */
    private boolean dfs(int course) {
        if (visiting.contains(course)) {
            return false; // cycle detected
        }
        if (done.contains(course)) {
            return true; // already verified
        }

        visiting.add(course);
        for (int pre : preMap.get(course)) {
            if (!dfs(pre)) {
                return false;
            }
        }
        visiting.remove(course);

        done.add(course); // mark as processed
        return true;
    }

    public static void main(String[] args) {
        int numOfCourses = 2;
        // int[][] prerequisites = { { 1, 0 } };   // true
        int[][] prerequisites = { { 1, 0 }, { 0, 1 } }; // false
        CourseSchedule obj = new CourseSchedule();
        System.out.println(obj.canFinish(numOfCourses, prerequisites));
    }
}
