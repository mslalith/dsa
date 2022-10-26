package src.linkedlist.problems;

import src.Problem;
import src.linkedlist.ListNode;
import src.utils.LinkedListUtilsKt;

public class RemoveDuplicatesFromSortedList implements Problem<ListNode<Integer>, ListNode<Integer>> {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList problem = new RemoveDuplicatesFromSortedList();
        for (ListNode<Integer> testInput : problem.getTestInputs()) {
            System.out.println("Test: " + LinkedListUtilsKt.stringFromListNode(testInput));
            System.out.println("Output: " + LinkedListUtilsKt.stringFromListNode(problem.solution(testInput)));
            System.out.println();
        }
    }

    @Override
    public ListNode<Integer>[] getTestInputs() {
        return new ListNode[]{
                LinkedListUtilsKt.buildLinkedListFromInt("1 2 2 3 3") // 1 2 3
        };
    }

    @Override
    public ListNode<Integer> solution(ListNode<Integer> testInput) {
        return deleteDuplicates(testInput);
    }

    ListNode<Integer> deleteDuplicates(ListNode<Integer> head) {
        ListNode<Integer> curr = head;

        while (curr != null) {
            ListNode<Integer> first = curr;
            while (first.next != null && first.val.equals(first.next.val)) {
                first = first.next;
            }
            curr.next = first.next;
            curr = curr.next;
        }

        return head;
    }
}
