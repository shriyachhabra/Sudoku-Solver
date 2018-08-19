import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
    
        Scanner scn= new Scanner(System.in);
        int[][] matrix= new int[9][9];
        for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					matrix[i][j]=scn.nextInt();
				}
				
			}
        
      sudoku(matrix,1);
    }
    public static void sudoku(int[][] matrix, int cell) {

		if (cell > 81) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
            return;
		}

		int r = (cell - 1) / matrix.length;
		int c = (cell - 1) % matrix.length;

		if (matrix[r][c] == 0) {
			for (int digit = 1; digit <= 9; digit++) {
				if (safeToPlace(matrix, r, c, digit)) {
					matrix[r][c] = digit;
					sudoku(matrix, cell + 1);
					matrix[r][c] = 0;
				}
			}
		}else{
            sudoku(matrix,cell+1);
        }

	}

	private static boolean safeToPlace(int[][] matrix, int r, int c, int digit) {
		
		// check in col
		for (int col = 0; col < matrix.length; col++) {
			if (matrix[r][col] == digit) {
				return false;
			}
		}
		// check in row
		for (int row = 0; row < matrix.length; row++) {
			if (matrix[row][c] == digit) {
				return false;
			}
		}

		// check in submatrix
		int startr = 3 * (r / 3);
		int startc = 3 * (c / 3);
		int endr = startr + 3;
		int endc = startc + 3;
		for (int row = startr; row < endr; row++) {
			for (int col = startc; col < endc; col++) {
				if (matrix[row][col] == digit) {
					return false;
				}
			}
		}

		return true;
	}
}
