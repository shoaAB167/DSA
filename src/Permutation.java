import java.util.ArrayList;
import java.util.List;

public class Permutation {
    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        backtrack(new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    public void backtrack(List<Integer> perm, int nums[], boolean[] pick){
        if(perm.size() == nums.length){
            res.add(new ArrayList<>(perm));
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(!pick[i]){
                perm.add(nums[i]);
                pick[i] = true;
                backtrack(perm, nums, pick);
                perm.remove(perm.size()-1);
                pick[i] = false;
            }
        }
    }
    public static void main(String args[]){
        Permutation p = new Permutation();
        int[] nums = {1,2,3};
        List<List<Integer>> result = p.permute(nums);
        System.out.println(result);
    }
}
