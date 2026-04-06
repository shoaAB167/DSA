import java.util.*;

public class Subset2 {
    List<List<Integer>> res;

    public List<List<Integer>> subset2(int[] nums){
        res = new ArrayList<>();
        Arrays.sort(nums);

        backtrack(nums, 0, new ArrayList<>());
        return res;
    }

    public void backtrack(int[] nums, int i, List<Integer> subset){
        if(i == nums.length){
            res.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[i]);
        backtrack(nums, i+1, subset);

        subset.remove(subset.size()-1);

        while(i+1 < nums.length && nums[i] == nums[i+1]) {
            i++;
        }

        backtrack(nums, i+1, subset);
    }

    public static void main(String args[]){
        int nums[] = new int[]{1,2,1};
        Subset2 subset2 = new Subset2();
        List<List<Integer>> res = subset2.subset2(nums);
        System.out.println(res);
    }
}