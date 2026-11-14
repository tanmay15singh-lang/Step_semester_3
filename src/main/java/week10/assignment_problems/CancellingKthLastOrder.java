package week10.assignment_problems;

public class CancellingKthLastOrder {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // Time: O(n)
    // Space: O(1)

    static Node removeKthFromEnd(Node head, int k) {

        Node dummy = new Node(0);
        dummy.next = head;

        Node lead = dummy;
        Node trail = dummy;

        // Move lead k+1 steps
        for (int i = 0; i <= k; i++) {
            lead = lead.next;
        }

        // Move both pointers
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        // Remove kth node from end
        trail.next = trail.next.next;

        return dummy.next;
    }

    static void display(Node head) {

        while (head != null) {
            System.out.print(head.data);

            if (head.next != null)
                System.out.print(" -> ");

            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);

        int k = 2;

        System.out.print("Original List: ");
        display(head);

        head = removeKthFromEnd(head, k);

        System.out.print("After Cancellation: ");
        display(head);
    }
}