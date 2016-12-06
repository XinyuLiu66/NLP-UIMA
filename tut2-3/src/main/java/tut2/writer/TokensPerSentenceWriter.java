package tut2.writer;

import java.util.*;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
//import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
//import org.apache.uima.jcas.tcas.Annotation;
//import org.apache.uima.util.Level;
import org.apache.uima.util.Level;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.teaching.general.type.BIToken;

public class TokensPerSentenceWriter extends JCasConsumer_ImplBase {

	public static final String LF = System.getProperty("line.separator");
	
//	public static StringBuilder sb = new StringBuilder();
	public void process(JCas jcas) throws AnalysisEngineProcessException {
	//	StringBuilder sb = new StringBuilder();
	//	StringBuilder sb = new StringBuilder();

		for (Sentence s: JCasUtil.select(jcas, Sentence.class))  {
			
			//	sb.append("Sentence: ");//
				System.out.print("Sentence: ");
			//	sb.append(s.getCoveredText());
				System.out.println(s.getCoveredText());
			//	sb.append(LF);
			//	sb.append("Number of tokens: ");
				System.out.print("Number of tokens: ");
            	List<BIToken> tokens = new ArrayList<BIToken>();
            	tokens = JCasUtil.selectCovered(jcas, BIToken.class, s);
            //	sb.append(tokens.size());
				System.out.println(tokens.size());
            //	sb.append(LF);
            
 
			
          //  	numOfTokens.add(tokens.size());
            	
            }

        



	//	getContext().getLogger().log(Level.INFO, sb.toString());
	//	System.out.println(sb.toString());
	}

}
