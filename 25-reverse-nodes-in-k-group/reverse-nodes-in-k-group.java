class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        
        // Step 1: Check if k nodes exist
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        
        // If k nodes exist, reverse them
        if (count == k) {
            ListNode prev = reverseKGroup(curr, k); // process next groups
            
            while (count-- > 0) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            
            head = prev;
        }
        
        return head;
    }
}