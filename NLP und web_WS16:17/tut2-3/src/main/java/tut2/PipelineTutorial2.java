package tut2;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.jazzy.JazzyChecker;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
//import de.tudarmstadt.ukp.dkpro.core.tokit.WhitespaceTokenizer;
import tut2.reader.WebpageReader;
import tut2.tokenizer.SentenceSplitter;
import tut2.tokenizer.TokenizerExample;
import tut2.tokenizer.WhitespaceTokenizer;
import tut2.writer.AnnotationWriterold;
import tut2.writer.TokensPerSentenceWriter;

public class PipelineTutorial2 {

	public static void main(String[] args) throws UIMAException, IOException {
		
		//CollectionReader textReader = createReader(TextReader.class,
	   // TextReader.PARAM_DIRECTORY_NAME, "src/main/resources/simple-documents");
		
		CollectionReader webpageReader = createReader(WebpageReader.class,
				WebpageReader.PARAM_URL, "https://www.ukp.tu-darmstadt.de/ukp-home/welcome/", WebpageReader.PARAM_LANGUAGE,"en",WebpageReader.PARAM_SELECTOR, "div#c8030 > p");
		
	//	AnalysisEngine breakIterator = createEngine(BreakIteratorSegmenter.class);

	//	AnalysisEngine stanfordSegmenter = createEngine(StanfordSegmenter.class);
		AnalysisEngine WhitespaceTokenizer = createEngine(WhitespaceTokenizer.class);
		AnalysisEngine myTokenizer = createEngine(TokenizerExample.class);
		
		AnalysisEngine sentenceSplitter = createEngine(SentenceSplitter.class);
		AnalysisEngine TokensPerSentenceWriter = createEngine(TokensPerSentenceWriter.class);
		
		AnalysisEngine spellChecker = createEngine(JazzyChecker.class,
				JazzyChecker.PARAM_MODEL_LOCATION, "src/main/resources/dict/words.small");

		AnalysisEngine annotationWriter = createEngine(AnnotationWriterold.class);
		
		SimplePipeline.runPipeline(/*textReader,*/ webpageReader,sentenceSplitter,WhitespaceTokenizer,myTokenizer,/*breakIterator,*/ spellChecker, /*annotationWriter,*/TokensPerSentenceWriter);
	}
}
