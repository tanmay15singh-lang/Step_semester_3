package week10.assignment_problems;

public class PolynomialCalculator {

    static class Node {
        int coefficient;
        int exponent;
        Node next;

        Node(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }

    // Time: O(m+n)
    // Space: O(m+n) for result list

    static Node addPolynomials(Node p, Node q) {

        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {

            if (p.exponent > q.exponent) {

                tail.next = new Node(
                    p.coefficient,
                    p.exponent
                );

                p = p.next;
                tail = tail.next;
            }

            else if (q.exponent > p.exponent) {

                tail.next = new Node(
                    q.coefficient,
                    q.exponent
                );

                q = q.next;
                tail = tail.next;
            }

            else {

                int sum = p.coefficient + q.coefficient;

                if (sum != 0) {

                    tail.next = new Node(
                        sum,
                        p.exponent
                    );

                    tail = tail.next;
                }

                p = p.next;
                q = q.next;
            }
        }

        // Remaining terms of first polynomial
        while (p != null) {

            tail.next = new Node(
                p.coefficient,
                p.exponent
            );

            tail = tail.next;
            p = p.next;
        }

        // Remaining terms of second polynomial
        while (q != null) {

            tail.next = new Node(
                q.coefficient,
                q.exponent
            );

            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    static void display(Node head) {

        boolean first = true;

        while (head != null) {

            int c = head.coefficient;
            int e = head.exponent;

            if (!first) {
                if (c > 0)
                    System.out.print(" + ");
                else
                    System.out.print(" - ");
            } else if (c < 0) {
                System.out.print("-");
            }

            int abs = Math.abs(c);

            if (e == 0) {
                System.out.print(abs);
            } else {

                if (abs != 1)
                    System.out.print(abs);

                System.out.print("x");

                if (e != 1)
                    System.out.print("^" + e);
            }

            first = false;
            head = head.next;
        }

        if (first)
            System.out.print("0");

        System.out.println();
    }

    public static void main(String[] args) {

        // P = 5x^3 + 4x^2 + 2
        Node p = new Node(5, 3);
        p.next = new Node(4, 2);
        p.next.next = new Node(2, 0);

        // Q = 5x^2 - 3x + 1
        Node q = new Node(5, 2);
        q.next = new Node(-3, 1);
        q.next.next = new Node(1, 0);

        System.out.print("Polynomial P: ");
        display(p);

        System.out.print("Polynomial Q: ");
        display(q);

        Node result = addPolynomials(p, q);

        System.out.print("Sum: ");
        display(result);
    }
}