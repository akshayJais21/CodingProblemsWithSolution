// https://leetcode.com/problems/add-two-numbers/
// 2. Add Two Numbers
// Solved
// Medium
// Topics
// Companies
// You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

// You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

// Example 1:


// Input: l1 = [2,4,3], l2 = [5,6,4]
// Output: [7,0,8]
// Explanation: 342 + 465 = 807.
// Example 2:

// Input: l1 = [0], l2 = [0]
// Output: [0]
// Example 3:

// Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
// Output: [8,9,9,9,0,0,0,1]
 

// Constraints:

// The number of nodes in each linked list is in the range [1, 100].
// 0 <= Node.val <= 9
// It is guaranteed that the list represents a number that does not have leading zeros.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1;
        ListNode node2 = l2;
        ListNode ans;
        ListNode newNode = new ListNode((node1.val + node2.val)%10);
        int borrow = (node1.val + node2.val)/10;
        ans = newNode;
        node1 = node1.next;
        node2 = node2.next;
        ListNode curr;

        
        while (node1 != null || node2 != null){
            int val = 0 ;

            if(node1 == null){
                val = (borrow + node2.val)%10;
                borrow = (borrow + node2.val)/10;
                node2 = node2.next;

            }else if(node2 == null){
                val = (borrow + node1.val)%10;
                borrow = (borrow + node1.val)/10;
                node1 = node1.next;
            }else
            {
                val = (borrow + (node1.val + node2.val ))%10;
                System.out.println(val);
                borrow = (borrow + (node1.val + node2.val ))/10;
                node1 = node1.next;
                node2 = node2.next;

            }

            curr = new ListNode(val);
            newNode.next = curr;
            newNode = curr;
                    
                  
        }

        if(borrow != 0 ){
         curr = new ListNode(borrow);
         newNode.next = curr;
        }

return ans;

    }
}
