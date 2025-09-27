
//Problem : https://leetcode.com/problems/open-the-lock/description/
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class OpenTheLock {
    // BFS
    // public int openTheLock(String deadends[], String target){
    // Set<String> dead = new HashSet<>(Arrays.asList(deadends));
    // Set<String> begin = new HashSet<>();

    // if(target.equals("0000")){
    // return 0;
    // }
    // if(dead.contains("0000")){
    // return -1;
    // }

    // Queue<String> queue = new LinkedList<>();
    // queue.offer("0000");
    // visit.add("0000");
    // int steps = 0;

    // while(!queue.isEmpty()){
    // int size = queue.size();
    // for(int i=0; i<size; i++){
    // String poll = queue.poll();

    // if(poll.equals(target)){
    // return steps;
    // }

    // if(dead.contains(poll)){
    // continue;
    // }

    // for(String str : combination(poll)){
    // if(!visit.contains(str) && !dead.contains(str)){
    // queue.offer(str);
    // visit.add(str);
    // }
    // }
    // }
    // steps++;
    // }

    // return steps;
    // }
    // //Bidirectional bfs
    public int openTheLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000"))
            return -1;

        if (target.equals("0000"))
            return 0;

        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> visited = new HashSet<>();

        begin.add("0000");
        end.add(target);
        visited.add("0000");

        int steps = 0;

        while (!begin.isEmpty() && !end.isEmpty()) {
            if (begin.size() > end.size()) {
                Set<String> temp = begin;
                begin = end;
                end = temp;
            }

            Set<String> next = new HashSet<>();

            for (String cur : begin) {
                if (dead.contains(cur))
                    continue;

                if (end.contains(cur))
                    return steps; // already met

                for (String nei : combination(cur)) {
                    if (end.contains(nei))
                        return steps + 1;

                    if (!visited.contains(nei) && !dead.contains(nei)) {
                        next.add(nei);
                        visited.add(nei);
                    }
                }
            }
            begin = next;
            steps++;
        }
        return -1;
    }

    public List<String> combination(String str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char[] arr = str.toCharArray();

            // increment digit
            arr[i] = (char) (((arr[i] - '0' + 1) % 10) + '0');
            list.add(new String(arr));

            // decrement digit
            arr = str.toCharArray();
            arr[i] = (char) (((arr[i] - '0' + 9) % 10) + '0'); // fix for wrap-around
            list.add(new String(arr));
        }
        return list;
    }

    public static void main(String args[]) {
        String deadends[] = { "0201", "0101", "0102", "1212", "2002" };
        String target = "0202";
        OpenTheLock obj = new OpenTheLock();
        System.out.println(obj.openTheLock(deadends, target));
    }
}
