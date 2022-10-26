package src.linkedlist.problems;

import src.core.Problem;
import src.core.TestCase;
import src.linkedlist.ListNode;
import src.utils.LinkedListUtilsKt;

class RemoveNthNodeFromListEndParams {
    ListNode<Integer> head;
    int lastNthIndexToRemove;

    public RemoveNthNodeFromListEndParams(ListNode<Integer> head, int lastNthIndexToRemove) {
        this.head = head;
        this.lastNthIndexToRemove = lastNthIndexToRemove;
    }

    @Override
    public String toString() {
        return "\nLinked List: " + LinkedListUtilsKt.stringFromListNode(head) +
                "\n" +
                "Last nth index: " + lastNthIndexToRemove;
    }
}

public class RemoveNthNodeFromListEnd extends Problem<RemoveNthNodeFromListEndParams, ListNode<Integer>> {
    public static void main(String[] args) {
        new RemoveNthNodeFromListEnd().run();
    }

    @Override
    protected TestCase<RemoveNthNodeFromListEndParams, ListNode<Integer>>[] getTestCases() {
        return new TestCase[]{
                new TestCase<>(
                        new RemoveNthNodeFromListEndParams(
                                LinkedListUtilsKt.buildLinkedListFromInt("1 2 3 4 5"),
                                2
                        ),
                        LinkedListUtilsKt.buildLinkedListFromInt("1 2 3 5")
                ),
                new TestCase<>(
                        new RemoveNthNodeFromListEndParams(
                                LinkedListUtilsKt.buildLinkedListFromInt("1"),
                                1
                        ),
                        null
                ),
                new TestCase<>(
                        new RemoveNthNodeFromListEndParams(
                                LinkedListUtilsKt.buildLinkedListFromInt("20 380 349 322 389 424 429 120 64 691 677 58 327 631 916 203 484 918 596 252 509 644 33 460"),
                                82
                        ),
                        LinkedListUtilsKt.buildLinkedListFromInt("380 349 322 389 424 429 120 64 691 677 58 327 631 916 203 484 918 596 252 509 644 33 460")
                )
        };
    }

    @Override
    public ListNode<Integer> solve(RemoveNthNodeFromListEndParams testInput) {
        return removeNthFromEnd(testInput.head, testInput.lastNthIndexToRemove);
    }

    ListNode<Integer> removeNthFromEnd(ListNode<Integer> head, int index) {
        int size = 0;
        ListNode<Integer> curr = head;
        while (curr != null) {
            curr = curr.next;
            size++;
        }

        if (index >= size) return head.next;

        int indexToRemove = size - index;
        int count = 0;
        curr = head;
        while (count != indexToRemove - 1) {
            curr = curr.next;
            count++;
        }
        curr.next = curr.next.next;
        return head;
    }
}
