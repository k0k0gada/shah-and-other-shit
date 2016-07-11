package uprajneniqOtKnijkata;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class O4sTaskKvadratnaTablica {
	public static void main(String[] args) {
		char[][] tablica = new char[5][5];
		tablica = tablicaRand(tablica);
		arr2dDisplay(tablica);
		char[] table = new char[25];
		for (int i = 0; i < table.length; i++) {
			table[i] = tablica[i / 5][i % 5];
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("enter string : ");
		String str = sc.nextLine();
		if (str.length() % 2 == 1) {
			str = str.concat("a");
		}
		str = cript(str, table, tablica);
		System.out.println("kriptiraniq string");
		System.out.println(str);
		str = decrypt(str, table, tablica);
		System.out.println("dekriptiraniq string");
		System.out.println(str);
	}

	static String decrypt(String str, char[] table, char[][] tablica) {
		char[] str1 = str.toCharArray();
		for (int i = 0; i < str1.length; i += 2) {
			int temp1 = 0, temp2 = 0, temp3 = 0, temp4 = 0;
			for (int j = 0; j < table.length; j++) {
				if (str1[i] == table[j]) {
					temp1 = j / 5;
					temp2 = j % 5;
				}
				if (str1[i + 1] == table[j]) {
					temp3 = j / 5;
					temp4 = j % 5;
				}
				if (temp1 != 0 && temp2 != 0 && temp3 != 0 && temp4 != 0) {
					break;
				}
			}
			if (temp1 == temp3) {// ako sa na edin red se vzima predi6nata bukva
				str1[i] = table[((temp1 * 5 + temp4) == 0) ? 24 : (temp1 * 5 + temp4 - 1)];
				str1[i + 1] = table[((temp1 * 5 + temp2) == 0) ? 24 : (temp1 * 5 + temp2 - 1)];
			} else if (temp2 == temp4) {// ako sa v edna kolona se vzima dolnata
										// bukva
				str1[i] = table[(((temp1) * 5 + temp4) < 5) ? (((temp1 + 4) * 5 + temp4)) : ((temp1 - 1) * 5 + temp4)];
				str1[i + 1] = table[((temp3 * 5 + 5 + temp2) < 5 ? ((temp3 * 5 + 20 + temp2))
						: (temp3 * 5 - 5 + temp2))];
			} else {
				str1[i] = tablica[temp1][temp4];
				str1[i + 1] = tablica[temp3][temp2];
			}
		}
		str = Character.toString(str1[0]);
		for (int i = 1; i < str1.length; i++) {
			str = str + str1[i];
		}

		return str;
	}

	static String cript(String str, char[] table, char[][] tablica) {
		str = str.toLowerCase();
		char[] str1 = str.toCharArray();
		for (int i = 0; i < str.length(); i += 2) {
			int temp1 = 0, temp2 = 0, temp3 = 0, temp4 = 0;
			for (int j = 0; j < tablica.length; j++) {
				for (int j2 = 0; j2 < tablica[j].length; j2++) {
					if (tablica[j][j2] == str.charAt(i)) {
						temp1 = j;
						temp2 = j2;
					}
					if (tablica[j][j2] == str.charAt(i + 1)) {
						temp3 = j;
						temp4 = j2;
					}
					if (temp1 != 0 && temp2 != 0 && temp3 != 0 && temp4 != 0) {
						break;
					}
				}
			}

			if (temp1 == temp3) {// ako sa na edin red se vzima sledva6tata
									// bukva
				str1[i] = table[((temp1 * 5 + temp4 + 1) == 25) ? 0 : (temp1 * 5 + temp4 + 1)];
				str1[i + 1] = table[((temp1 * 5 + temp2 + 1) == 25) ? 0 : (temp1 * 5 + temp2 + 1)];
			} else if (temp2 == temp4) {// ako sa v edna kolona se vzima dolnata
										// bukva
				str1[i] = table[(((temp1 + 1) * 5 + temp4) > 25) ? (((temp1 + 1) * 5 + temp4) - 25)
						: ((temp1 + 1) * 5 + temp4)];
				str1[i + 1] = table[((temp3 * 5 + 5 + temp2) > 25 ? ((temp3 * 5 + 5 + temp2) - 25)
						: (temp3 * 5 + 5 + temp2))];
			} else {
				str1[i] = tablica[temp1][temp4];
				str1[i + 1] = tablica[temp3][temp2];
			}
		}
		str = Character.toString(str1[0]);
		for (int i = 1; i < str1.length; i++) {
			str = str + str1[i];
		}

		return str;
	}

	static char[][] tablicaRand(char[][] table) {
		Random rd = new Random();
		char[] workTable = new char[25];
		for (int i = 0; i < workTable.length; i++) {
			workTable[i] = (char) (97 + rd.nextInt(25));
			for (int j = 0; j <= i; j++) {
				if (workTable[i] == workTable[j] && i != j) {
					i--;
				}
			}
		}
		for (int i = 0; i < workTable.length; i++) {
			table[i / 5][i % 5] = workTable[i];
		}
		return table;
	}

	static void arr2dDisplay(char[][] arr) {
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " | ");
			}
			System.out.println();
		}
	}
}
