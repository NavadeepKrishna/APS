import java.util.HashSet;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Maintain window size k
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            
            // Check duplicate
            if (set.contains(nums[i])) {
                return true;
            }
            
            set.add(nums[i]);
        }
        
        return false;
    }
}