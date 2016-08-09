import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class LetterFrequency {

	public static void main(String[] args) {

		String data = null;
		try {
			data = readFile("C:\\Users\\pvuppalur\\workspace\\Day4\\src\\1.txt");
		} 
		catch (IOException e) {

			e.printStackTrace();
		}
		
		HashMap<String, ArrayList<Integer>> chars = new HashMap<>();
		chars = listOfIndices(data);

		HashMap<String, Double> charfreq = new HashMap<>();
		charfreq = letterFrequency(chars, charfreq);

	}
	
	//finding frequency of each letter
	public static HashMap<String, Double> letterFrequency(HashMap<String, ArrayList<Integer>> chars,
			HashMap<String, Double> charfreq) {

		for (java.util.Map.Entry<String, ArrayList<Integer>> ee : chars.entrySet()) {
			String key = ee.getKey();
			ArrayList<Integer> values = ee.getValue();
			if (!key.equals("\n")) {

				int diff = 0;

				for (int i = 0; i < values.size() - 1; i++)
					diff = diff + values.get(i + 1) - values.get(i);

				double freq = diff / values.size();
				System.out.println(key + " " + freq);
				charfreq.put(key, freq);
			}

		}
		return charfreq;
	}

	//finding the indices of each char
	public static HashMap<String, ArrayList<Integer>> listOfIndices(String data) {

		int i;
		int index;
		data = data.toLowerCase();
		char[] book = data.toCharArray();

		HashMap<String, ArrayList<Integer>> chars = new HashMap<>();

		for (i = 0; i < book.length; i++) {

			index = data.indexOf(book[i]);
			ArrayList<Integer> temp = new ArrayList<Integer>();

			while (index >= 0) {
				temp.add(index);
				index = data.indexOf(book[i], index + 1);

			}
			
			if (chars.containsKey(book[i]))
				chars.get(book[i]).addAll(temp);
			else
				chars.put(book[i] + "", temp);

		}
		return chars;
	}
	//printing the hashmap
	public static void printMap(HashMap<String, ArrayList<Integer>> chars) {

		for (String s : chars.keySet())
			System.out.println(s + " " + chars.get(s));
	}
	//reading the file
	public static String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
