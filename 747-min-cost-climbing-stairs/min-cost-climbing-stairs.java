class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int prev1 = 0; // cost to reach step i-1
        int prev2 = 0; // cost to reach step i-2

        for (int i = 0; i < cost.length; i++) {
            int curr = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = curr;
        }

        return Math.min(prev1, prev2);
    }
}