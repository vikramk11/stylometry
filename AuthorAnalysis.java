package stylometry;

import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;

public class AuthorAnalysis
{
	
    private int width = 1600; /* Width of the image */
    private int height = 1200; /* Height of the image */ 
    
    final String bookName1;
    final String bookName2;
    final String bookName3;

    final String letterDensity = "Letter Density";
    final String wordDensity = "Word Density";
    final String sentenceDensity = "Sentence Density";
    final String puncDensity = "Punctuation Density";
    
    BookAnalysis b1;
    BookAnalysis b2;
    BookAnalysis b3;
    
    private DefaultCategoryDataset dataset;
	
	public AuthorAnalysis(BookAnalysis book1,BookAnalysis book2, BookAnalysis book3) throws IOException{	
		b1 = book1;
		b2 = book2;
		b3 = book3;
		b1.performStylometricAnalysis();
		b2.performStylometricAnalysis();
		b3.performStylometricAnalysis();
		bookName1 = b1.getBookName();
		bookName2 = b2.getBookName();
		bookName3 = b3.getBookName();	
	}
	
	public void compareParagraphs() throws IOException {
		
		  dataset = new DefaultCategoryDataset( );
   
	      dataset.addValue( b1.letterDensityPerParagraph() , bookName1 , letterDensity );
	      dataset.addValue( b1.wordDensityPerParagraph(), bookName1 , wordDensity );
	      dataset.addValue( b1.sentenceDensityPerParagraph(), bookName1 , sentenceDensity );
	     
	      
	      dataset.addValue( b2.letterDensityPerParagraph() , bookName2 , letterDensity );
	      dataset.addValue( b2.wordDensityPerParagraph() , bookName2 , wordDensity );
	      dataset.addValue( b2.sentenceDensityPerParagraph() , bookName2 , sentenceDensity );
	      
	      dataset.addValue( b3.letterDensityPerParagraph() , bookName3 , letterDensity );
	      dataset.addValue( b3.wordDensityPerParagraph() , bookName3 , wordDensity );
	      dataset.addValue( b3.sentenceDensityPerParagraph() , bookName3 , sentenceDensity );
	      
	      JFreeChart barChart = ChartFactory.createBarChart(
	         "AUTHOR ANALYSIS", 
	         "Type", "Per Paragraph", 
	         dataset,PlotOrientation.VERTICAL, 
	         true, true, false);
	         

	      File BarChart = new File( "C:\\ParagraphAnalysis.jpeg" ); 
	      ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
		
	}
	
	public static void main( String[ ] args )throws Exception 
	{
      
	  BookAnalysis b1 = new BookAnalysis("C:\\Sorcerer's Stone.txt","Sorcerer's Stone");   
      BookAnalysis b2 = new BookAnalysis("C:\\hamlet.txt","Hamlet");   
      BookAnalysis b3 = new BookAnalysis("C:\\Azkaban.txt","Azkaban"); 
      
      AuthorAnalysis al = new AuthorAnalysis(b1,b2,b3);
      al.compareParagraphs();
      al.compareSentences();
      
         
   }

	private void compareSentences() throws IOException {

			  dataset = new DefaultCategoryDataset( );
			   
		      dataset.addValue( b1.letterDensityPerSentence() , bookName1 , letterDensity );
		      dataset.addValue( b1.wordDensityPerSentence(), bookName1 , wordDensity );
		      dataset.addValue( b1.punctDensityPerSentence(), bookName1 , puncDensity );
		      
		      
		      dataset.addValue( b2.letterDensityPerSentence() , bookName2 , letterDensity );
		      dataset.addValue( b2.wordDensityPerSentence() , bookName2 , wordDensity );
		      dataset.addValue( b2.punctDensityPerSentence() , bookName2 , puncDensity );
		      
		      dataset.addValue( b3.letterDensityPerSentence() , bookName3 , letterDensity );
		      dataset.addValue( b3.wordDensityPerSentence() , bookName3 , wordDensity );
		      dataset.addValue( b3.punctDensityPerSentence() , bookName3 , puncDensity );
		      
		      JFreeChart barChart = ChartFactory.createBarChart(
		         "AUTHOR ANALYSIS", 
		         "Type", "Per Sentence", 
		         dataset,PlotOrientation.VERTICAL, 
		         true, true, false);
		         

		      File BarChart = new File( "C:\\SentenceAnalysis.jpeg" ); 
		      ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
			
	}
}
