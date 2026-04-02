import java.util.*;

public class CombinationSum2 {
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, int target, int i, List<Integer> cur) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        if (target < 0 || i == nums.length) {
            return;
        }

        cur.add(nums[i]);
        backtrack(nums, target - nums[i], i + 1, cur);
        cur.remove(cur.size() - 1);

        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        }
        backtrack(nums, target, i + 1, cur);
    }

    public static void main(String args[]){
        CombinationSum2 combinationSum2 = new CombinationSum2();
        int num[] = new int[]{2,2,5,6,4,3};
        int target = 9;
        List<List<Integer>> res = combinationSum2.combinationSum2(num, target);
        System.out.println((res));
    }
}