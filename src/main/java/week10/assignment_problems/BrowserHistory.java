package week10.assignment_problems;

public class BrowserHistory {

    static class Node {
        String url;
        Node prev, next;

        Node(String url) {
            this.url = url;
        }
    }

    static class History {

        Node current;

        History(String homepage) {
            current = new Node(homepage);
        }

        // Time: O(1)
        void visit(String url) {

            Node newNode = new Node(url);

            // Remove forward history
            current.next = null;

            newNode.prev = current;
            current.next = newNode;

            current = newNode;
        }

        // Time: O(steps)
        String back(int steps) {

            while (steps > 0 && current.prev != null) {
                current = current.prev;
                steps--;
            }

            return current.url;
        }

        // Time: O(steps)
        String forward(int steps) {

            while (steps > 0 && current.next != null) {
                current = current.next;
                steps--;
            }

            return current.url;
        }
    }

    public static void main(String[] args) {

        History browser = new History("srm.edu");

        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        System.out.println(browser.back(1));

        System.out.println(browser.back(1));

        System.out.println(browser.forward(1));

        browser.visit("d.com");

        System.out.println(browser.forward(2));

        System.out.println(browser.back(2));

        System.out.println(browser.back(7));
    }
}