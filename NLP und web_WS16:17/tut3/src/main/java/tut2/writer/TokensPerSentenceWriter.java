package tut2.writer;

import java.util.*;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
//import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
//import org.apache.uima.jcas.tcas.Annotation;
//import org.apache.uima.util.Level;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.teaching.general.type.BIToken;

public class TokensPerSentenceWriter extends JCasConsumer_ImplBase {

	public static final String LF = System.getProperty("line.separator");
	

	List<Integer> numOfTokens = new ArrayList<Integer>();
	public void process(JCas jcas) throws AnalysisEngineProcessException {
	//	StringBuilder sb = new StringBuilder();
		for (Sentence s: JCasUtil.select(jcas, Sentence.class))  {

            	List<BIToken> tokens = new ArrayList<BIToken>();
            	tokens = JCasUtil.selectCovered(jcas, BIToken.class, s);
            	numOfTokens.add(tokens.size());
            //	sb.append(tokens.size());
            }

        
		/*for (Sentence s: JCasUtil.select(jcas, Sentence.class)) {
			Collection<BIToken> alltokens = JCasUtil.selectCovered(jcas, BIToken.class, s);
			//sb.append("[" + alltokens.size() + "] ");
			//sb.append(LF);
	}
*/


	//	getContext().getLogger().log(Level.INFO, sb.toString());
	}

}
