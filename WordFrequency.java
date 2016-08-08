
import java.io.*;
import java.security.KeyStore.Entry;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsFrequency {

	static int topn = 10;
	static int cot;

	public static void main(String[] args) {

	
		String filename = "C:\\Users\\pvuppalur\\workspace\\Day4\\src\\1.txt";
		HashMap<String, Integer> wordfreq = new HashMap<>();
		
		wordfreq = readFile(filename);
		wordfreq = sortByValues(wordfreq);

		printMap(wordfreq);

	}
	//for reading the file
	public static HashMap<String, Integer> readFile(String filen) {
		
		File file = new File(filen);
		HashMap<String, Integer> a = new HashMap<>();
		try {
			
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {

				String line = sc.nextLine();
				a = splitLine(a, line);
			}
			sc.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return a;
	}

	//sorting hash map in descending order using frequency of word
	private static HashMap<String, Integer> sortByValues(HashMap map) {
		List list = new LinkedList(map.entrySet());

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());

		}
		return sortedHashMap;
	}
	//printing top 10 words in each book
	public static void printMap(Map<String, Integer> wordfreq) {

		int count = 0;
		for (String s : wordfreq.keySet()) {
			if (count < topn) {
				System.out.println(s + " " + wordfreq.get(s));
				count++;
			}

		}
	}
	//entry of words and its frquency into hashmap
	public static HashMap<String, Integer> hashMapEntry(HashMap<String, Integer> a, String word) {

		if (a.containsKey(word)) {

			int count = a.get(word);
			count++;
			a.put(word, count);
		} else {

			a.put(word, 1);
		}
		return a;
	}
	//spliiting line using delimiters
	public static HashMap<String, Integer> splitLine(HashMap<String, Integer> a, String line) {

		String tokenise[] = line.split("-|\\s|'|\\?|\\!|\\,|\\(|\\)|\\.|\\-|\"|`|;|\\*");
		int i;
		for (i = 0; i < tokenise.length; i++) {

			if (tokenise[i].length() != 0) {
				a = hashMapEntry(a, tokenise[i].toLowerCase());

			}
		}
		return a;
	}

}
