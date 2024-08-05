package com.example;

import com.example.utils.RectangleMatrix;

import java.util.logging.Logger;

public class Application {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        RectangleMatrix rectangleMatrix = new RectangleMatrix(matrix);
        logger.info("Maximal rectangle area: " + rectangleMatrix.maximalRectangle());
    }
}