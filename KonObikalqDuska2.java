package uprajneniqOtKnijkata;

import java.util.Random;
import java.util.Scanner;

public class KonObikalqDuska2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert array rows and cows");
		int n = sc.nextInt();
		int m = sc.nextInt();
		char[][] arr = new char[n][m];
		System.out.println("Insert horse coordinates.");
		int startRow = sc.nextInt();
		int startCow = sc.nextInt();

		horseMovement(startRow, startCow, arr);

		print(arr);

	}

	private static void print(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}

	}

	private static void horseMovement(int i, int j, char[][] arr) {
		arr2dDisplay(arr);
		int n = arr.length;
		int m = arr[0].length;
		if (i >= n || i < 0)
			return;
		if (j >= m || j < 0)
			return;
		if (arr[i][j] == '*')
			return;
		arr[i][j] = '*';
		horseMovement(i - 2, j + 1, arr);
		horseMovement(i - 2, j - 1, arr);
		horseMovement(i + 2, j + 1, arr);
		horseMovement(i + 2, j - 1, arr);
		horseMovement(i + 1, j + 2, arr);
		horseMovement(i - 1, j + 2, arr);
		horseMovement(i + 1, j - 2, arr);
		horseMovement(i - 1, j - 2, arr);
	}

	static void arr2dDisplay(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + ",");
			}
			System.out.println();
		}
		System.out.println();
	}

	static char[][] createMasRand() {
		Random rd = new Random();
		int n = 4 + rd.nextInt(20);
		char[][] arr = new char[8][8];
		return arr;
	}

}
