package de.tudarmstadt.ukp.teaching.nlp4web.tutorial.tut3;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tut2.reader.WebpageReader;
import tut2.tokenizer.SentenceSplitter;
import tut2.tokenizer.TokenizerExample;
import tut2.tokenizer.WhitespaceTokenizer;
import tut2.writer.AnnotationWriter;
import tut2.writer.TokensPerSentenceWriter;


public class TestPipeline
{
    CollectionReader webpageReader;
    AnalysisEngine wsTokenizer;
    AnalysisEngine biTokenizer;
    AnalysisEngine sentenceTokenizer;
    AnalysisEngine annotationWriter;
    AnalysisEngine tpsWriter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
    @Before
    public void setUp()
        throws Exception
    {
        webpageReader = createReader(WebpageReader.class, WebpageReader.PARAM_URL,
                "https://www.ukp.tu-darmstadt.de/ukp-home/welcome/",
                WebpageReader.PARAM_LANGUAGE,"en",
                
                WebpageReader.PARAM_SELECTOR, "div#c8030 > p"
                );

        wsTokenizer = createEngine(WhitespaceTokenizer.class);
        biTokenizer = createEngine(TokenizerExample.class);
        sentenceTokenizer = createEngine(SentenceSplitter.class);
        annotationWriter = createEngine(AnnotationWriter.class);
        tpsWriter = createEngine(TokensPerSentenceWriter.class);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
    
    @Test
    public void testWsTokenizer() throws UIMAException, IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File testWsTokenizer = new File(classLoader.getResource("testWsTokenizer.txt").getFile());
        String contents = FileUtils.readFileToString(testWsTokenizer);

        SimplePipeline.runPipeline(webpageReader, 
                wsTokenizer, 
                annotationWriter 
                );
        assertEquals(contents, outContent.toString());
    }

    @Test
    public void testBiTokenizer() throws UIMAException, IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File testBiTokenizer = new File(classLoader.getResource("testBiTokenizer.txt").getFile());
        String contents = FileUtils.readFileToString(testBiTokenizer);

        SimplePipeline.runPipeline(webpageReader, 
                biTokenizer, 
                annotationWriter 
                );
        assertEquals(contents, outContent.toString());
    }

    @Test
    public void testSentenceTokenizer() throws UIMAException, IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File testSentenceTokenizer = new File(classLoader.getResource("testSentenceTokenizer.txt").getFile());
        String contents = FileUtils.readFileToString(testSentenceTokenizer);

        SimplePipeline.runPipeline(webpageReader, 
                sentenceTokenizer, 
                annotationWriter 
                );
        assertEquals(contents, outContent.toString());
    }

    @Test
    public void testTpsWriter() throws UIMAException, IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File testTpsWriter = new File(classLoader.getResource("testTpsWriter.txt").getFile());
        String contents = FileUtils.readFileToString(testTpsWriter);

        SimplePipeline.runPipeline(webpageReader, 
                wsTokenizer, 
                biTokenizer, 
                sentenceTokenizer,
                tpsWriter
                );
        assertEquals(contents, outContent.toString());
    }

}
