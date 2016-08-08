package stylometry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stylometry  
{
	private ArrayList<String> book1Lines;
	private ArrayList<String> book2Lines;
	private ArrayList<String> book3Lines;
	
	private final String book1 = "C:\\Sorcerer's Stone.txt";
	private final String book2 = "C:\\chamber of secrets.txt";
	private final String book3 = "C:\\azkaban.txt";
	
	public Stylometry() throws IOException {
		book1Lines = this.readFile(book1);
		book2Lines = this.readFile(book2);
		book3Lines = this.readFile(book3);
	}
	
	public int sentenceCount(){
		return book1Lines.size();
	}
	
	public int punctCount(){
		
		Pattern p = Pattern.compile("\\p{Punct}");
		int count = 0;
		
		for(String s:book1Lines)
		{
			Matcher m = p.matcher(s);
			while (m.find()) {
				count++;
			}
		}
		
		return count;

	}
	
	public ArrayList<String> readFile(String path) throws IOException{
		
		ArrayList<String> tempList = new ArrayList<>();
		
		BufferedReader b = new BufferedReader (new FileReader(path));
		String line;

		while((line = b.readLine()) != null){
		   tempList.add(line);
		}
		
		return tempList;
	}	
   
	
   public static void main( String[ ] args ) throws IOException
   {
	   Stylometry s = new Stylometry();
	   int totalPunc = s.punctCount();
	   int totalSent = s.sentenceCount();
	   System.out.println(totalPunc+" "+totalSent);
      
   }
}
