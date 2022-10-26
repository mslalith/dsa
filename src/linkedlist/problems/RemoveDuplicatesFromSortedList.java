package src.linkedlist.problems;

import src.core.Problem;
import src.core.TestCase;
import src.linkedlist.ListNode;
import src.utils.LinkedListUtilsKt;

public class RemoveDuplicatesFromSortedList extends Problem<ListNode<Integer>, ListNode<Integer>> {
    public static void main(String[] args) {
        new RemoveDuplicatesFromSortedList().run();
    }

    @Override
    protected TestCase<ListNode<Integer>, ListNode<Integer>>[] getTestCases() {
        return new TestCase[]{
                new TestCase<>(
                        LinkedListUtilsKt.buildLinkedListFromInt("1 2 2 3 3"),
                        LinkedListUtilsKt.buildLinkedListFromInt("1 2 3")
                )
        };
    }

    @Override
    public ListNode<Integer> solve(ListNode<Integer> testInput) {
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
