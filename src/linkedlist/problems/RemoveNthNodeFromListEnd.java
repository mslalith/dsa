package src.linkedlist.problems;

import src.Problem;
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
        return "Linked List: " + LinkedListUtilsKt.stringFromListNode(head) +
                "\n" +
                "Last nth index: " + lastNthIndexToRemove;
    }
}

public class RemoveNthNodeFromListEnd implements Problem<RemoveNthNodeFromListEndParams, ListNode<Integer>> {
    public static void main(String[] args) {
        RemoveNthNodeFromListEnd problem = new RemoveNthNodeFromListEnd();
        for (RemoveNthNodeFromListEndParams testInput : problem.getTestInputs()) {
            System.out.println(testInput);
            System.out.println("Output: " + LinkedListUtilsKt.stringFromListNode(problem.solution(testInput)));
            System.out.println();
        }
    }

    @Override
    public RemoveNthNodeFromListEndParams[] getTestInputs() {
        return new RemoveNthNodeFromListEndParams[]{
                new RemoveNthNodeFromListEndParams(
                        LinkedListUtilsKt.buildLinkedListFromInt("1 2 3 4 5"),
                        2
                ), // 1 2 3 5
                new RemoveNthNodeFromListEndParams(
                        LinkedListUtilsKt.buildLinkedListFromInt("1"),
                        1
                ), // 1
                new RemoveNthNodeFromListEndParams(
                        LinkedListUtilsKt.buildLinkedListFromInt("20 380 349 322 389 424 429 120 64 691 677 58 327 631 916 203 484 918 596 252 509 644 33 460"),
                        82
                ) // 380 349 322 389 424 429 120 64 691 677 58 327 631 916 203 484 918 596 252 509 644 33 460
        };
    }

    @Override
    public ListNode<Integer> solution(RemoveNthNodeFromListEndParams testInput) {
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
