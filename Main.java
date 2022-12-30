package HW4;

import java.util.ArrayDeque;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

    }


    //101. Symmetric Tree (Stack)
    //Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
    // public boolean isSymmetric(TreeNode root) {
    //     if (root == null) return true;
    //     Stack<TreeNode> stack = new Stack<>();
    //     stack.push(root.right);
    //     stack.push(root.left);
    //     while (!stack.isEmpty()) {
    //         TreeNode left = stack.pop();
    //         TreeNode right = stack.pop();
    //         if (left == null && right == null) continue;
    //         if (left == null || right == null || left.val != right.val) {
    //             return false;
    //         }
    //         stack.push(right.left);
    //         stack.push(left.right);
    //         stack.push(right.right);
    //         stack.push(left.left);

    //     }
    //     return true;

    // }


    // 542. 01 Matrix (Stack)
    // Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
    public int[][] updateMatrix(int[][] mat) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int[][] steps = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curPoint = queue.poll();
            int curRow = curPoint[0];
            int curCol = curPoint[1];
            for (int[] step : steps) {
                int newRow = curRow + step[0];
                int newCol = curCol + step[1];
                if (newRow >= 0 && newRow < mat.length &&
                        newCol >= 0 && newCol < mat[0].length &&
                        mat[newRow][newCol] >= mat[curRow][curCol] + 1) {
                    queue.add(new int[]{newRow, newCol});
                    mat[newRow][newCol] = mat[curRow][curCol] + 1;
                }
            }
        }
        return mat;
    }


    // 71. Simplify Path (Stack)
    //Given a string path, which is an absolute path (starting with a slash '/')
    //to a file or directory in a Unix-style file system,
    //convert it to the simplified canonical path.

    public String simplifyPath(String path) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        String[] split = path.split("/");

        for (String s : split) {
            if (s.length() == 0 || s.equals(".")) {
                continue;
            }
            if (!s.equals("..")) {
                stack.push(s);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }

        StringBuilder res = new StringBuilder();

        while (!stack.isEmpty()) {
            res.insert(0, "/" + stack.pop());
        }

        if (res.toString().equals("")) {
            res = new StringBuilder("/");
        }
        return res.toString();
    }


    // 695. Max Area of Island (Stack)
    // You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
    // connected 4-directionally (horizontal or vertical.)
    // You may assume all four edges of the grid are surrounded by water.

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        int[][] steps = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int lendLength = 0;
                if (grid[i][j] == 1) {
                    stack.push(new int[]{i, j});

                    while (!stack.isEmpty()) {
                        int[] curPoint = stack.poll();
                        int curRow = curPoint[0];
                        int curCol = curPoint[1];
                        if (grid[curRow][curCol] != 1) continue;
                        grid[curRow][curCol] = 2;
                        lendLength++;

                        for (int[] step : steps) {
                            int newRow = curRow + step[0];
                            int newCol = curCol + step[1];
                            if (newRow >= 0 && newRow < grid.length &&
                                    newCol >= 0 && newCol < grid[0].length &&
                                    grid[newRow][newCol] == 1) {
                                stack.push(new int[]{newRow, newCol});
                            }
                        }
                    }
                }
                if (lendLength > max) max = lendLength;
            }
        }
        return max;
    }

}
