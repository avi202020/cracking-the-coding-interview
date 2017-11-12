package com.ctci6.ch02;

import java.util.Stack;

import com.ctci6.utils.LinkedListNode;

/**
 * Implement a function to check if a linked list is a palindrome.
 * 
 * @author Sunil
 *
 */
public class Palindrome {
	
	public static void main(String[] args) {
		int length = 9;
		LinkedListNode[] nodes = new LinkedListNode[length];
		for (int i = 0; i < length; i++) {
			nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
		}
		
		for (int i = 0; i < length; i++) {
			if (i < length - 1) {
				nodes[i].setNext(nodes[i + 1]);
			}
			if (i > 0) {
				nodes[i].setPrevious(nodes[i - 1]);
			}
		}
		// nodes[length - 2].data = 9; // Uncomment to ruin palindrome
		
		LinkedListNode head = nodes[0];
		System.out.println(head.printForward());
		//System.out.println(isPalindrome1(head));
		System.out.println(isPalindrome2(head));
	}
	
	//Solution #1: Reverse and Compare
	public static boolean isPalindrome1(LinkedListNode head) {
		LinkedListNode reversed = reverseAndClone(head);
		System.out.println("reversed-->"+reversed.printForward());
		return isEqual(head, reversed);
	}
		
	public static LinkedListNode reverseAndClone(LinkedListNode node) {
		LinkedListNode head = null;
		while (node != null) {
			LinkedListNode n = new LinkedListNode(node.data); // Clone
			n.next = head;
			head = n;
			node = node.next;
		}
		return head;
	}	
		
	public static boolean isEqual(LinkedListNode one, LinkedListNode two) {
		while (one != null && two != null) {
			if (one.data != two.data) {
				return false;
			}
			one = one.next;
			two = two.next;
		}
		return one == null && two == null;
	}
	
	//Solution #2: Iterative Approach
	public static boolean isPalindrome2(LinkedListNode head) {
		LinkedListNode fast = head;
		LinkedListNode slow = head;
		
		Stack<Integer> stack = new Stack<Integer>();
		
		/* Push elements from first half of linked list onto stack. When fast runner
		 * (which is moving at 2x speed) reaches the end of the linked list, then we
		 * know we're at the middle*/
		while (fast != null && fast.next != null) {
			
			stack.push(slow.data);
			slow = slow.next;
			fast = fast.next.next;	
			System.out.println("fast-->"+fast.printForward());
			//System.out.println("fast.next-->"+fast.next.printForward());
		}
		System.out.println("stack-->"+stack);
		/* Has odd number of elements, so skip the middle */
		if (fast != null) { 
			slow = slow.next;
		}
		
		while (slow != null) {
			int top = stack.pop().intValue();
			if (top != slow.data) {
				return false;
			}
			slow = slow.next;
		}
		return true;
	}
}
