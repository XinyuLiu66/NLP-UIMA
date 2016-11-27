package tut2.consumer;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.jazzy.JazzyChecker;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import reader.WebpageReader;
import writer.TutorialWrite;

public class Tut2_Pipline {

	public static void main(String[] args) throws UIMAException, IOException {
		CollectionReader reader = createReader(WebpageReader.class,WebpageReader.PARAM_URL,"https://en.wikipedia.org/wiki/Ubiquitous_Knowledge_Processing_Lab");

		/*
		 *  The difference between BreakIteratorSegmenter and StanfordSegmenter is that
		     for example Nr.1 is viewed as two token for BreakIteratorSegmenter, but for
		     StanfordSegmenter it's one token
		 */
	//	AnalysisEngine seg = createEngine(BreakIteratorSegmenter.class);
		AnalysisEngine seg = createEngine(StanfordSegmenter.class,StanfordSegmenter.PARAM_LANGUAGE,"en");
		
		
		AnalysisEngine jazzy = createEngine(JazzyChecker.class, JazzyChecker.PARAM_MODEL_LOCATION, "/usr/share/dict/words");
		AnalysisEngine writer = createEngine(TutorialWrite.class);

		SimplePipeline.runPipeline(reader, seg, writer);
	}
}
