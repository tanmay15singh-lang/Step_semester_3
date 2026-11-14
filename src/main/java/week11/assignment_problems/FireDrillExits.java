package week11.assignment_problems;

import java.util.*;

public class FireDrillExits {

    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n)

    static int[][] exitDistance(char[][] plan) {

        int m = plan.length;
        int n = plan[0].length;

        int[][] distance = new int[m][n];

        Queue<int[]> queue = new LinkedList<>();

        // Initialize distances
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (plan[i][j] == '#') {

                    distance[i][j] = -1;
                }

                else {

                    distance[i][j] = -1;

                    // Add all exits to queue
                    if (plan[i][j] == 'E') {

                        distance[i][j] = 0;

                        queue.offer(new int[]{i, j});
                    }
                }
            }
        }

        // Directions: up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // BFS
        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                // Check boundaries
                if (nx < 0 || ny < 0 ||
                        nx >= m || ny >= n) {

                    continue;
                }

                // Skip walls and already visited cells
                if (plan[nx][ny] == '#' ||
                        distance[nx][ny] != -1) {

                    continue;
                }

                distance[nx][ny] = distance[x][y] + 1;

                queue.offer(new int[]{nx, ny});
            }
        }

        return distance;
    }

    static void display(int[][] distance) {

        for (int[] row : distance) {

            for (int value : row) {

                System.out.print(value + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        display(result);
    }
}