package uprajneniqOtKnijkata;

import java.util.Arrays;
import java.util.Random;

public class selectioSortDemo {
	public static void main(String[] args) {
		int[] arr = { 2, 3, 4, 5, 2, 1, 34, 2, 53, 2, -3 };
		Random rd = new Random();
		int[] arr1 = generateRandArr();
		selectionSort(arr);
		System.out.println(Arrays.toString(arr));
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
		selectionSort(arr1);
		System.out.println(Arrays.toString(arr1));

	}

	static int[] generateRandArr() {
		Random rd = new Random();
		int mas[] = new int[rd.nextInt(20)];
		for (int i = 0; i < mas.length; i++) {
			mas[i] = (int) ((rd.nextDouble() - 0.5) * 30);
		}
		return mas;
	}

	static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minInd = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[minInd] > arr[j]) {
					minInd = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[minInd];
			arr[minInd] = temp;
		}

	}
}
