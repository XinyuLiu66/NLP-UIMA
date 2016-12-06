package starterCode;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordParser;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;


public class DemoPipelineTutorial4
{

    public static void main(String[] args)
        throws UIMAException, IOException
    {

    	// No real reader needed for a simple demo.
    	JCas jcas =JCasFactory.createJCas();
    	
    	// Make sure that the document language is set to "en". Check your reader(s)!
    	jcas.setDocumentLanguage("en");

    	jcas.setDocumentText("I like mammals such as cats and the sillier mice. I saw a lot of mammals, including zebras. You should be nice to dogs and other mammals.");
    	
        AnalysisEngine seg = createEngine(StanfordSegmenter.class);
        
        AnalysisEngine lem = createEngine(StanfordLemmatizer.class);
        
        AnalysisEngine pos = createEngine(StanfordPosTagger.class);
        // StanfordParser is kind of heavy. You might want to increase the heap space.
        // e.g. add -Xmx1500m to the VM arguments in the Run Configurations arguments tab.
        // Also better start with not too much text to process.
    	AnalysisEngine parse = createEngine(StanfordParser.class);
    	
    	AnalysisEngine posWriter = createEngine(POSWriter.class);
    	AnalysisEngine lemmaWriter = createEngine(LemmaWriter.class);
    	AnalysisEngine chunkWriter = createEngine(ChunkWriter.class);
    	
        SimplePipeline.runPipeline(
        		jcas,
        		seg,        		
        		
        		pos,
        		lem,
        		parse,
        		posWriter,
        		lemmaWriter,
        		chunkWriter
        );
    }
}
