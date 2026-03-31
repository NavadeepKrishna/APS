import java.util.Stack;

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0; // pointer for popped
        
        for (int x : pushed) {
            stack.push(x);
            
            // Keep popping if matches popped sequence
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        
        return stack.isEmpty();
    }
}