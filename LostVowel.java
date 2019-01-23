import java.util.ArrayList;
import java.util.List;

/**
 * This is the coursework of CS5001, Practice one.
 * Lost vowel
 * @author HaodaMiao (hm212@st-andrews.ac.uk)
 */

public class LostVowel{
	/**
	 * This is a class to achieve the lost consonant game.
	 */
	public static List<String> readDic(String dictionary) {
		/**
		 * Put every word in the dictionary into a list and transfer the capital character to lower character.
		 * There is one param in this method which is the dictionary direct address.
		 */
		ArrayList<String> dic;
		ArrayList<String> dicLower = new ArrayList<>();
		dic = FileUtil.readLines(dictionary);
		for (int i = 0; i < dic.size(); i++) {
			dicLower.add(dic.get(i).toLowerCase());
		}
		if (dicLower.isEmpty()) {
			System.out.println("Invalid dictionary, aborting.");
			System.exit(1);
		}
		return dicLower;
	}

	public static List<String> getCandidate(String[] str, String input) {
		/**
		 * Get a List of Candidate String.
		 * There are two params in this method, the first one is the array of all consonants, 
		 * the second param is the input word.
		 */
		List<String> newList = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			for (int j = 0; j < str.length; j++) {
				if (str[j].equals(input.substring(i, i + 1))) {
					String changed = input.substring(i).replaceFirst(input.substring(i, i + 1), "");
					String original = input.substring(0, i);
					String newS = original + changed;
					newList.add(newS);
				}
			}
		}
		return newList;
	}

	public static List<String> getAlternatives(String input, List<String> candidate, List<String> dictionary) {
		/**
		 * This method is used to get alternatives.
		 * There are three params, the input word, the candidate list and the dictionary list.
		 */
		
		// count the number of words in the sentence.
		int n = input.split(" ").length;
		if (n < 2) {
			n = 1;
		}
		List<String> finList = new ArrayList<>();
		for (int i = 0; i < candidate.size(); i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				String test = candidate.get(i).split(" ")[j].replaceAll("\\p{Punct}", "");
				if (Character.isUpperCase(test.charAt(0))) {
					test = test.toLowerCase();
				}
				if (dictionary.contains(test)) {
					count++;
				}
			}
			if (count == n) {
				finList.add(candidate.get(i));
				System.out.println(candidate.get(i));
			}
		}
		return finList;
	}
	
	public static void main(String[] args) {
		String[] str = { "a","e","i","o","u","A","E","I","O","U" };
		/**
		 * Get two arguments, dictionary address and a word or a sentence. if there are
		 * any input mistakes, print the alert.
		 */
		if (args.length != 2) {
			System.out.println("Expected 2 command line arguments, but got " + args.length + ".\n"
					+ "Please provide the path to the dictionary file as the first argument and a sentence as the second argument.");
			System.exit(1);
		}
		String dicAddress = args[0];
		String input = args[1];
		List<String> dictionary = new ArrayList<>();
		dictionary = readDic(dicAddress);
		List<String> candidate = new ArrayList<>();
		candidate = getCandidate(str, input);
		/**
		 * Print out alternatives.
		 */
		List<String> alternatives = new ArrayList<>();
		alternatives = getAlternatives(input, candidate, dictionary);
		if (alternatives.size() == 0) {
			System.out.println("Could not find any alternatives.");
		} else {
			System.out.println("Found " + alternatives.size() + " alternatives.");
		}
	}
}


