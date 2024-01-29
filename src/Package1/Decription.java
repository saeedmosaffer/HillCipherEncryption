package Package1;

import java.util.ArrayList;

public class Decription {

	private String StringKey;
	private String cipherText;
	private String[] charcter;

	public Decription(String key, String cipherText, String[] charcter) {
		this.StringKey = key;
		this.cipherText = cipherText;
		this.charcter = charcter;
	}

	public String getPlanText() {
		int[][] key = new int[3][3];
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				key[i][j] = getIndex(StringKey.charAt(k) + "");
				k++;
			}
		}

		int[][] array = getAdjemnt(key);
		int[][] adjemnt = lastLevelOfAdjemnt(array);

		int determent = getDeterment(key);
		int InversOfDeterment = findInvers(determent);
		System.out.println("the invers: " + InversOfDeterment);
		for (int i = 0; i < adjemnt.length; i++) {
			for (int j = 0; j < adjemnt[i].length; j++) {
				adjemnt[i][j] = (adjemnt[i][j] * InversOfDeterment);

				adjemnt[i][j] = findMod(adjemnt[i][j]);
			}
		}
		System.out.println(
				"-----------------------------------------------k^-1--------------------------------------------");

		for (int i = 0; i < adjemnt.length; i++) {
			for (int j = 0; j < adjemnt[i].length; j++) {
				System.out.print(adjemnt[i][j] + "\t\t ");
			}
			System.out.println();
		}

		System.out.println(
				"-----------------------------------------------k^-1--------------------------------------------");

		String[] splitCipher = splitCipherText();
		int[] multiplay = new int[3];
		ArrayList<String> plan = new ArrayList<>();
		for (int i = 0; i < splitCipher.length; i++) {
			k = 0;
			for (int j = 0; j < 3; j++) {

				int index = getIndex(splitCipher[i].charAt(j) + "");
				multiplay[k] = index;
				k++;

			}
			for (int j = 0; j < adjemnt.length; j++) {
				int sum = 0;
				for (int f = 0; f < adjemnt[j].length; f++) {
					sum = sum + adjemnt[j][f] * multiplay[f];
				}
				sum = sum % 63;
				plan.add(charcter[sum]);

			}

		}

		String sumOfPlan = "";
		for (int i = 0; i < plan.size(); i++) {
			sumOfPlan += plan.get(i);

		}

		return sumOfPlan;
	}

	public int getIndex(String indexForKey) {
		for (int i = 0; i < charcter.length; i++) {
			if (charcter[i].equals(indexForKey)) {
				return i;
			}
		}
		return -1;
	}

	public int[][] getAdjemnt(int[][] key) {
		int[][] array = new int[3][3];
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key[i].length; j++) {
				array[j][i] = key[i][j];
			}
		}
		return array;
	}

	public int[][] lastLevelOfAdjemnt(int[][] key) {

		int[][] array = new int[3][3];
		array[0][0] = key[1][1] * key[2][2] - key[2][1] * key[1][2];
		array[0][1] = (key[1][0] * key[2][2] - key[2][0] * key[1][2]) * -1;
		array[0][2] = key[1][0] * key[2][1] - key[2][0] * key[1][1];
		array[1][0] = (key[0][1] * key[2][2] - key[2][1] * key[0][2]) * -1;
		array[1][1] = key[0][0] * key[2][2] - key[2][0] * key[0][2];
		array[1][2] = (key[0][0] * key[2][1] - key[2][0] * key[0][1]) * -1;
		array[2][0] = key[0][1] * key[1][2] - key[1][1] * key[0][2];
		array[2][1] = (key[0][0] * key[1][2] - key[1][0] * key[0][2]) * -1;
		array[2][2] = key[0][0] * key[1][1] - key[1][0] * key[0][1];

		return array;
	}

	public int getDeterment(int[][] array) {
		int sum = array[0][0] * (array[1][1] * array[2][2] - array[2][1] * array[1][2])
				- array[0][1] * (array[1][0] * array[2][2] - array[2][0] * array[1][2])
				+ array[0][2] * (array[1][0] * array[2][1] - array[2][0] * array[1][1]);
		return sum;
	}

	public static int findInvers(int d) {
		int i = 1;

		if (d > 0) {
			while (true) {
				int f = (i * d) % 63;
				if (f == 1) {
					return i;
				}

				i++;

			}
		} else {
			d = Math.abs(d);
			while (true) {
				int f = (i * d) % 63;
				if (f == 62) {
					return i;
				}

				i++;

			}
		}

	}

	public int findMod(int n) {
		if (n > 0) {
			return n % 63;
		} else {
			n = Math.abs(n);

			int mod = 63 - (n % 63);

			return mod;
		}
	}

	public String[] splitCipherText() {
		ArrayList<String> array = new ArrayList<>();

		while (cipherText.length() > 0) {
			if (cipherText.length() < 3) {

				int u = cipherText.length();
				while (u < 3) {
					cipherText = cipherText + " ";
					u++;
				}
				array.add(cipherText);
				break;
			}
			String split = cipherText.substring(0, 3);
			array.add(split);
			cipherText = cipherText.substring(3);
		}

		String[] splitPlan = new String[array.size()];
		for (int i = 0; i < splitPlan.length; i++) {
			splitPlan[i] = array.get(i);
		}
		return splitPlan;

	}

}
