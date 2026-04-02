import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Subset {

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> subset = new ArrayList<>();
            dfs(nums, 0, subset, res);
            return res;
        }


        private void dfs(int[] nums, int i, List<Integer> subset, List<List<Integer>> res) {
            if (i >= nums.length) {
                res.add(new ArrayList<>(subset));
                return;
            }
            subset.add(nums[i]);
            dfs(nums, i + 1, subset, res);
            subset.remove(subset.size() - 1);
            dfs(nums, i + 1, subset, res);
        }

        public static void main(String args[]){
            int num[] = new int[]{1,2,3};
            Subset subset = new Subset();
            List<List<Integer>> list = subset.subsets(num);
            System.out.println(list);
        }
}
