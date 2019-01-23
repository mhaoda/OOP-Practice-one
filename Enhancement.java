import java.util.ArrayList;
import java.util.List;

/**
 * This is the enhancement of CS5001 Practice one.
 * 
 * @author HaodaMiao (hm212@st-andrews.ac.uk)
 */

public class Enhancement {
	public static List<String> readDic(String dictionary) {
		/**
		 * Put every word in the dictionary into a list and transfer the capital
		 * character to lower character.
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

	public static void main(String[] args) {
		String[] str = { "b", "c", "d", "f", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y",
				"z", "g" };

		if (args.length != 2) {
			System.out.println("Expected 2 command line arguments, but got " + args.length + ".\n"
					+ "Please provide the path to the dictionary file as the first argument and a sentence as the second argument.");
			System.exit(1);
		}
		String dicAddress = args[0];
		String input = args[1];
		
		List<String> dictionary = new ArrayList<>();
		dictionary = readDic(dicAddress);
		List<String> alternatives = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			for (int j = 0; j < str.length; j++) {
				StringBuffer changedBuffer = new StringBuffer(input);
				StringBuffer candidate = changedBuffer.insert(i, str[j]);
				String test = new String(candidate);
				int n = input.split(" ").length;
				if (n < 2) {
					n = 1;
				}
				int count = 0;
				for (int z = 0; z < n; z++) {
					String testWord = test.split(" ")[z].replaceAll("\\p{Punct}", "");
					if (Character.isUpperCase(testWord.charAt(0))) {
						testWord = testWord.toLowerCase();
					}
					if (dictionary.contains(testWord)) {
						count++;
					}
				}
				if (count == n) {
					alternatives.add(test);
					System.out.println(test);
				}
			}
		}
		if (alternatives.size() == 0) {
			System.out.println("Could not find any alternatives.");
		} else {
			System.out.println("Found " + alternatives.size() + " alternatives.");
		}
	}
}
