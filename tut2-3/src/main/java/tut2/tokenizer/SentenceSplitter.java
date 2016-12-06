package tut2.tokenizer;

import java.text.BreakIterator;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;

public class SentenceSplitter extends JCasAnnotator_ImplBase{

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		String document = jcas.getDocumentText();
        int len = document.length();
        int start = 0;
        int end = 0;
    //    BreakIterator boundary = BreakIterator.getWordInstance();
        BreakIterator boundary = BreakIterator.getSentenceInstance();
        boundary.setText(document);
         start = boundary.first();
	     for (end = boundary.next();
	          end != BreakIterator.DONE;
	          start = end, end = boundary.next()) {
	   //       System.out.println(source.substring(start,end));
	    	  //  BIToken tokenAnnotation = new BIToken(jcas);
	    	    Sentence tokenAnnotation = new Sentence(jcas);
                tokenAnnotation.setBegin(start);
                tokenAnnotation.setEnd(end);
             //   if(!tokenAnnotation.getCoveredText().equals(null))
                tokenAnnotation.addToIndexes();
	    	 
	     }
	}

}
