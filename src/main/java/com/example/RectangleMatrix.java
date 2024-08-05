package com.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class RectangleMatrix {
    private final char[][] matrix;

    public RectangleMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public int maximalRectangle() {
        if (matrix.length == 0) {
            return 0;
        }

        int maxArea = 0;
        int[] heights = new int[matrix[0].length];

        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (chars[j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int[] h = new int[heights.length + 1];
        System.arraycopy(heights, 0, h, 0, heights.length);

        for (int i = 0; i < h.length; i++) {
            while (!stack.isEmpty() && h[i] < h[stack.peek()]) {
                int height = h[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        RectangleMatrix rectangleMatrix = new RectangleMatrix(matrix);
        System.out.println(rectangleMatrix.maximalRectangle());
    }
}