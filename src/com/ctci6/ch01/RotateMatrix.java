package com.ctci6.ch01;

import com.ctci6.utils.AssortedMethods;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 * 
 * @author Sunil
 */
public class RotateMatrix {

	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(3, 3, 0, 9);
		AssortedMethods.printMatrix(matrix);
		rotate(matrix);
		System.out.println("=========================");
		AssortedMethods.printMatrix(matrix);
	}

	public static boolean rotate(int[][] matrix) {
		if (matrix.length == 0 || matrix.length != matrix[0].length) return false; // Not a square
		int n = matrix.length;//3
		
		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;
			for(int i = first; i < last; i++) {
				System.out.println("<---------------->");
				int offset = i - first;
				int top = matrix[first][i]; // save top

				// left -> top
				matrix[first][i] = matrix[last-offset][first];
				System.out.println("matrix1--->");
				AssortedMethods.printMatrix(matrix);
				// bottom -> left
				matrix[last-offset][first] = matrix[last][last - offset]; 
				System.out.println("matrix2--->");
				AssortedMethods.printMatrix(matrix);
				// right -> bottom
				matrix[last][last - offset] = matrix[i][last]; 
				System.out.println("matrix3--->");
				AssortedMethods.printMatrix(matrix);
				// top -> right
				matrix[i][last] = top; // right <- saved top
				System.out.println("matrix4--->");
				AssortedMethods.printMatrix(matrix);
				System.out.println("---------------->");
			}
		}
		return true;
	}
}
