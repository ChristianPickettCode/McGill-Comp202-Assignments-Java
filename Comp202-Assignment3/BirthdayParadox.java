import java.util.Arrays;
public class BirthdayParadox {

	public static void main (String[] args) {

		int [] arr = {2,43,5,2,53,2,53};

		for (int i = 1; i < 101; i++) {
			System.out.println((i) + " " + runExperiment(i));
		}
	}

	// This method creates an array of random integers
	// Given the size and range of the array
	public static int[] generateArray (int size, int range) {
		int[] arr = new int[size]; 

		for (int i = 0; i < size; i++) {
			arr[i] = (int)(Math.random() * range);
		}

		return arr;
	}

	// This method creates a 2D array or random integers
	// Given the total iterations (# of arrays), the size and the range of the array
	public static int[][] generateAllData (int iterations, int size, int range) {
		int[][] arr = new int[iterations][size];

		for (int i = 0; i < iterations; i++) {
			arr[i] = generateArray(size, range);
		}

		return arr;
	}

	// This method counts how many times an integer occurs in a 2D array
	public static int countElement (int[][] arr, int element) {
		int total = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (element == arr[i][j]) {
					total++;
				}
			}
		}
		return total;
	}

	// This method returns the element (integer) that occurs the most in a 2D array
	public static int maxDay (int[][] arr) {
	int mode = arr[0][0];

	for (int i = 0; i < arr.length; i++) {
		for (int j = 0; j < arr[i].length; j++) {
			if(countElement(arr,mode) <= countElement(arr, arr[i][j])) {
				mode = arr[i][j];
			}
		}
	}

	return mode;

	}

	// This method returns if the array has any duplicates
	public static boolean hasDuplicates (int[] arr) {
		boolean duplicateExists = false;

		int[][] twoDArr = new int[arr.length][1];

		// Converts a single dimentional array to a 2D array
		for (int i = 0; i < arr.length; i++) {
			twoDArr[i][0] = arr[i];
		}

		// Determines if any of the items in the inputted array occur multiple times
		for (int j = 0; j < twoDArr.length; j++) {
			if (countElement(twoDArr, twoDArr[j][0]) >= 2) {
				duplicateExists = true;
			}
		}

		return duplicateExists;
	}

	// This method determines the fraction of duplicates in a random array
	public static double runExperiment (int size) {

		if (size < 1) {

			throw new IllegalArgumentException ("Input size is too small");
		
		} else {

			int[][] arr = generateAllData(200, size, 365);
			int duplicates = 0;

			for (int i = 0; i < arr.length; i++) {

				if (hasDuplicates(arr[i])) {
					duplicates++;
				}

			}

			return duplicates / 200.0;

		}

		

	}


}