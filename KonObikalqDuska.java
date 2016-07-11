package uprajneniqOtKnijkata;

import java.util.Random;
import java.util.Scanner;

public class KonObikalqDuska {
	public static void main(String[] args) {
		char[][] arr = createMasRand();// arr za metoda bez povtarqne
		char[][] arrPovt = new char[arr.length][arr.length];// arr za metoda sus
															// povtarqne
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arrPovt[i][j] = arr[i][j];// kopirame originalniq masiv
			}
		}
		System.out.println("duskata :");
		arr2dDisplay(arr);
		Scanner sc = new Scanner(System.in);
		int x, y;
		do {
			System.out.println(" kude e konq ???(po X)?");
			x = sc.nextInt();
			System.out.println("kude e konq ???? (po y)?");
			y = sc.nextInt();
		} while (x < 0 || x > arr.length || y < 0 || y > arr.length);
		x--;
		y--;
		arr[x][y] = 'k';// k-mestopolojenie na konq
		arrPovt[x][y] = 'k';
		obikalqne(x, y, arr);
		System.out.println("natisni ne6to predi programta da produlji s vtoriq metod");
		char z = sc.next().charAt(0);
		obikalqnePovtor(x, y, arrPovt);
		sc.close();

	}

	static void obikalqnePovtor(int x, int y, char[][] arr) {// obikalqne s
																// povtor
		// stack overflow error :D
		arr[x][y] = 'k';// k-kude e konq
		arr2dDisplay(arr);
		arr[x][y] = 'x';// x-konq e bil tam
		if (x + 2 < arr.length && x - 2 > 0 && x - 1 > 0 && x + 1 < arr.length && y + 1 < arr.length && y - 1 > 0
				&& y + 2 < arr.length && y - 2 > 0) {// ako x i y sa v
														// granicite na duskata
			if (arr[x + 2][y + 1] == 'x' && arr[x + 2][y - 1] == 'x' && arr[x - 2][y + 1] == 'x'
					&& arr[x - 2][y - 1] == 'x' && arr[x + 1][y + 2] == 'x' && arr[x - 1][y + 2] == 'x'
					&& arr[x + 1][y - 2] == 'x'
					&& arr[x - 1][y - 2] == 'x') {/*
													 * i vsi4ki okoloni poleta
													 * sa bili obikoleni
													 */
				arr[x][y] = 'b'; // b=blocked space
			}
		}
		if (x + 2 < arr.length && y + 1 < arr.length && arr[x + 2][y + 1] != 'b') {
			obikalqnePovtor(x + 2, y + 1, arr);
		}
		if (x + 2 < arr.length && y - 1 > 0 && arr[x + 2][y - 1] != 'b') {
			obikalqnePovtor(x + 2, y - 1, arr);
		}
		if (x - 2 > 0 && y + 1 < arr.length && arr[x - 2][y + 1] != 'b') {
			obikalqnePovtor(x - 2, y + 1, arr);
		}
		if (x - 2 > 0 && y - 1 > 0 && arr[x - 2][y - 1] != 'b') {
			obikalqnePovtor(x - 2, y - 1, arr);
		}
		if (x - 1 > 0 && y - 2 > 0 && arr[x - 1][y - 2] != 'b') {
			obikalqnePovtor(x - 1, y - 2, arr);
		}
		if (x - 1 > 0 && y + 2 < arr.length && arr[x - 1][y + 2] != 'b') {
			obikalqnePovtor(x - 1, y + 2, arr);
		}
		if (x + 1 < arr.length && y - 2 > 0 && arr[x + 1][y - 2] != 'b') {
			obikalqnePovtor(x + 1, y - 2, arr);
		}
		if (x + 1 < arr.length && y + 2 < arr.length && arr[x + 1][y + 2] != 'b') {
			obikalqnePovtor(x + 1, y + 2, arr);
		} else {
			return;
		}

	}

	static void obikalqne(int x, int y, char[][] arr) {// bez povtarqne na mesta
														// kadeto konq e bil
		arr[x][y] = 'k';// k-kade e konq
		arr2dDisplay(arr);
		arr[x][y] = 'b';// b==been there
		if (x + 2 < arr.length && y + 1 < arr.length && arr[x + 2][y + 1] != 'b') {
			obikalqne(x + 2, y + 1, arr);
		}
		if (x + 2 < arr.length && y - 1 > -1 && arr[x + 2][y - 1] != 'b') {
			obikalqne(x + 2, y - 1, arr);
		}
		if (x - 2 > -1 && y + 1 < arr.length && arr[x - 2][y + 1] != 'b') {
			obikalqne(x - 2, y + 1, arr);
		}
		if (x - 2 > -1 &&  y - 1 > -1  && arr[x - 2][y - 1] != 'b') {
			obikalqne(x - 2, y - 1, arr);
		}
		if (x - 1 > -1 &&  y - 2 > -1 && arr[x - 1][y - 2] != 'b') {
			obikalqne(x - 1, y - 2, arr);
		}
		if (x - 1 > -1 && y + 2 < arr.length && arr[x - 1][y + 2] != 'b') {
			obikalqne(x - 1, y + 2, arr);
		}
		if (x + 1 < arr.length && y - 2 > -1 && arr[x + 1][y - 2] != 'b') {
			obikalqne(x + 1, y - 2, arr);
		}
		if ( x + 1 < arr.length && y + 2 < arr.length && arr[x + 1][y + 2] != 'b') {
			obikalqne(x + 1, y + 2, arr);
		}
//		obikalqne(x, y, arr);

		if (x + 2 < arr.length && x - 2 > 0 && x - 1 > 0 && x + 1 < arr.length && y + 1 < arr.length && y - 1 > 0
				&& y + 2 < arr.length && y - 2 > 0) {// ako x i y sa v
														// granicite na duskata
			if (arr[x + 2][y + 1] == 'b' && arr[x + 2][y - 1] == 'b' && arr[x - 2][y + 1] == 'b'
					&& arr[x - 2][y - 1] == 'b' && arr[x + 1][y + 2] == 'b' && arr[x - 1][y + 2] == 'b'
					&& arr[x + 1][y - 2] == 'b'
					&& arr[x - 1][y - 2] == 'b') {/*
													 * i vsi4ki okoloni poleta
													 * sa bili obikoleni
													 */
				return;
			}
		}

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
		char[][] arr = new char[n][n];
		return arr;
	}

}
