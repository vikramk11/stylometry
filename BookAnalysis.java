package stylometry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

public class BookAnalysis  
{
	private String bookContents;
	private String bookName;
	
	private ArrayList<String> bookLines;
	
	private String PARAGRAPH_SPLIT_REGEX = "(?m)(?=^\\s{2})";
	private Pattern wordPattern = Pattern.compile("^[a-zA-Z]+(-|'|[...])*[a-zA-Z]*$");
	private Pattern punctuationPattern = Pattern.compile("\\p{Punct}");
	
	private int puncCount;
	private int wordCount;
	private int paragraphCount;
	private int sentenceCount;
	private Long letterCount;
	
	
	public BookAnalysis(String path, String name) throws IOException {
		bookContents   = this.readFile(path);
		puncCount      = 0;
		letterCount    = 0L;
		wordCount      = 0;
		sentenceCount  = 0;
		paragraphCount = 0;
		bookName       = name;
	}
	
	public void performStylometricAnalysis() throws IOException{
		   this.splitToSentences();
		   this.Tokenize();
		   this.paragraphCount();
	}
	
	public void splitToSentences() throws IOException {

			// Training Model for sentence split
			InputStream is = new FileInputStream("C:\\en-sent.bin");		
			SentenceModel model = new SentenceModel(is);
			SentenceDetectorME sdetector = new SentenceDetectorME(model);
		 
			//Book is split to sentences
			String sentences[] = sdetector.sentDetect(bookContents);
			bookLines = new ArrayList<String>(Arrays.asList(sentences));
			sentenceCount = bookLines.size();
			is.close();

	}
	
	public void Tokenize() throws InvalidFormatException, IOException {
		
		// Training Model for Word and Punctuation Tokenizer
		InputStream is = new FileInputStream("C:\\nl-token.bin");
		TokenizerModel model = new TokenizerModel(is);
		Tokenizer tokenizer = new TokenizerME(model);
				
		for(String s:bookLines){
			
			//Handles for Ellipsis - Bad model :(
			s = s.replace("...", ".");	
			String tokens[] = tokenizer.tokenize(s);		
			
			for (String a : tokens){
				
					if(containsPunctuation(a))
						++puncCount;
					
					if(containsWord(a)){
						++wordCount;
						letterCount+=a.length();
					}		
			}		
		}	 
		is.close();
	}
	
	public boolean containsPunctuation(String a){
		
		Matcher m = punctuationPattern.matcher(a);
		if(m.find()){
			return true;
		}
		else
			return false;
		
	}
	
	public boolean containsWord(String a){
		
		Matcher m = wordPattern.matcher(a);
		if(m.find()){
			return true;
		}
		else
			return false;
		
	}
	
	public double punctDensityPerWord(){
		return (double)puncCount/wordCount;
	}
	
	public double punctDensityPerSentence(){
		return (double)puncCount/sentenceCount;
	}
	
	public double wordDensityPerSentence(){
		return (double)wordCount/sentenceCount;
	}
	
	public double letterDensityPerSentence(){
		return (double)letterCount/sentenceCount;
	}
	
	public double letterDensityPerParagraph(){
		return (double)letterCount/(paragraphCount);
	}
	
	public double wordDensityPerParagraph(){
		return (double)wordCount/(paragraphCount);
	}
	
	public double sentenceDensityPerParagraph(){
		return (double)(bookLines.size())/(paragraphCount);
	}
	
	public void paragraphCount() {
	    String[] paragraphs = bookContents.split(PARAGRAPH_SPLIT_REGEX);
	    paragraphCount = paragraphs.length;
	}
	
	public String getBookName() {
		return this.bookName;
	}
   
	public String readFile(String path) throws IOException{		
		return new String(Files.readAllBytes(Paths.get(path)));
	}	
	
}
