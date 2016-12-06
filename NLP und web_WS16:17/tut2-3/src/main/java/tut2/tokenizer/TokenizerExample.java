package tut2.tokenizer;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.teaching.general.type.BIToken;
import de.tudarmstadt.ukp.teaching.general.type.WSToken;

public class TokenizerExample extends JCasAnnotator_ImplBase {

	public static void main(String[] args) {
		String s = "I saw a man.  \"I'm Sam,\" he said.";

		System.out.println("--- WhiteSpaceTokenize ---");
		WhitespaceTokenize(s);

		System.out.println("--- BreakIteratorTokenize ---");
		BreakIteratorTokenize(s);
	}

	// Problem 1: Examine and run the following tokenizer. What problems
	// do you see in the output?
	private static void WhitespaceTokenize(String document) {
		StringTokenizer tok = new StringTokenizer(document);
		while(tok.hasMoreTokens()){
			System.out.println(tok.nextElement());
		}
	}

	// Problem 2: Implement a tokenizer using java.text.BreakIterator.
	// What are the improvements over the previous approach? What issues
	// still remain?
	private static void BreakIteratorTokenize(String document) {
		BreakIterator boundary = BreakIterator.getWordInstance();
        boundary.setText(document);
        printEachForward(boundary, document);
        
	}
	 /*public static void printEachForward(BreakIterator boundary, String source) {
	     int start = boundary.first();
	     for (int end = boundary.next();
	          end != BreakIterator.DONE ;
	          start = end, end = boundary.next()) {
	          System.out.println(source.substring(start,end));
	    	 
	    	 
	     }
	 }*/
	public static void printEachForward(BreakIterator boundary, String source) {
	     int start = boundary.first();
	     for (int end = boundary.next();
	          end != BreakIterator.DONE ;
	          start = boundary.next(), end = boundary.next()) {
	          System.out.println(source.substring(start,end));
	    	 
	    	 
	     }
	 }


	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		
		 String document = jcas.getDocumentText();
	        int len = document.length();
	        int start = 0;
	        int end = 0;
	    //    BreakIterator boundary = BreakIterator.getWordInstance();
	        BreakIterator boundary = BreakIterator.getWordInstance();
	        boundary.setText(document);
	         start = boundary.first();
		     for (end = boundary.next();
		          end != BreakIterator.DONE ;
		          start = end, end = boundary.next()) {
		   //       System.out.println(source.substring(start,end));
		    	    BIToken tokenAnnotation = new BIToken(jcas);
		    	   // Sentence tokenAnnotation = new Sentence(jcas);
	                tokenAnnotation.setBegin(start);
	                tokenAnnotation.setEnd(end);
	                if(Character.isLetter(tokenAnnotation.getCoveredText().charAt(0))|Character.isDigit(tokenAnnotation.getCoveredText().charAt(0)))
	                tokenAnnotation.addToIndexes();
		    	 
		     }
	       
	        /*
	        if (start < len) {
	        	BreakIteratorTokenize( document);
                // The following code creates an annotation and adds it to
                // the index. You'll need execute it for each token you find.
            	BIToken tokenAnnotation = new BIToken(jcas);
                tokenAnnotation.setBegin(start);
                tokenAnnotation.setEnd(end);
                tokenAnnotation.addToIndexes();
            }
            */
           // start = end + 1;
	            
	        /*
	        while (start < len) {
	            while (start < len && Character.isSpaceChar(document.charAt(start))) {
	                start++;
	            }

	            for (end = start; end < len && !Character.isSpaceChar(document.charAt(end)); end++);
	            
	            if (start < len) {
	                // The following code creates an annotation and adds it to
	                // the index. You'll need execute it for each token you find.
	            	BIToken tokenAnnotation = new BIToken(jcas);
	                tokenAnnotation.setBegin(start);
	                tokenAnnotation.setEnd(end);
	                tokenAnnotation.addToIndexes();
	            }
	            start = end + 1;
	        }
	        */
	        
	    }

}

