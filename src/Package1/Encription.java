package Package1;

import java.util.ArrayList;

public class Encription {

	private String[] character;
	private String StringKey;
	private String planText;

	public Encription(String[] character, String key, String planText) {
		this.character = character;
		this.StringKey = key;
		this.planText = planText;
	}

	public String cipherText() {
		long[][] key = new long[3][3];
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				key[i][j] = getIndex(StringKey.charAt(k) + "");
				k++;
			}
		}

		System.out.println(
				"--------------------------------------------Key---------------------------------------------\n");
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key[i].length; j++) {
				System.out.print(key[i][j] + "\t\t\t\t ");
			}
			System.out.println("\n");
		}

		System.out.println(
				"--------------------------------------------Key---------------------------------------------\n");

		String[] array = splitPlanText();
		long[] mutplay = new long[3];
		ArrayList<String> cipher = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			k = 0;

			for (int j = 0; j < array[i].length(); j++) {

				long index = getIndex(array[i].charAt(j) + "");
				mutplay[k] = index;
				k++;

			}

			for (int j = 0; j < key.length; j++) {
				long sum = 0;
				for (int f = 0; f < key[j].length; f++) {
					sum = sum + key[j][f] * mutplay[f];
				}
				sum = sum % 63;

				cipher.add(character[(int) sum]);

			}

		}
		String finalCipher = "";
		for (int i = 0; i < cipher.size(); i++) {
			finalCipher = finalCipher + cipher.get(i);
		}

		return finalCipher;
	}

	public long getIndex(String indexForKey) {
		for (int i = 0; i < character.length; i++) {
			if (character[i].equals(indexForKey)) {
				return i;
			}
		}
		return -1;
	}

	public String[] splitPlanText() {
		ArrayList<String> array = new ArrayList<>();

		while (planText.length() > 0) {
			if (planText.length() < 3) {

				int u = planText.length();
				while (u < 3) {
					planText = planText + " ";
					u++;
				}
				array.add(planText);
				break;
			}
			String split = planText.substring(0, 3);
			array.add(split);
			planText = planText.substring(3);
		}

		String[] splitPlan = new String[array.size()];
		for (int i = 0; i < splitPlan.length; i++) {
			splitPlan[i] = array.get(i);
		}
		return splitPlan;

	}
}
