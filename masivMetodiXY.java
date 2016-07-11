package uprajneniqOtKnijkata;

import java.util.Arrays;
import java.util.Scanner;

public class masivMetodiXY {
	public static void main(String[] args) {
		int x, y;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("vuvedi redove :");
			x = sc.nextInt();
		} while (x < 2);
		do {
			System.out.println("vuvedi koloni");
			y = sc.nextInt();
		} while (y < 2);
		int[][] mas = createMas(x, y);
		for (int i = 0; i < mas.length; i++) {
			for (int j = 0; j < mas[i].length; j++) {
				System.out.print(mas[i][j] + " ");
			}
			System.out.println();
		}
	}

	static int[][] createMas(int rows, int col) {
		int[][] arr = new int[rows][col];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = 0;
			}
		}
		return arr;
	}
}
