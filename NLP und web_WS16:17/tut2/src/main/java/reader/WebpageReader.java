package reader;

import java.io.IOException;

import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebpageReader extends JCasCollectionReader_ImplBase {

//	private boolean read;
//	private String title;
	
	/*
     public static final String PARAM_TEXT_ENCODING = "TextEncoding";
	@ConfigurationParameter(
	name = PARAM_TEXT_ENCODING,
	description = "Sets the file's text encoding for I/O",
	mandatory = true , defaultValue = "utf-8")
	private String textEncoding	;
	*/
	
	// Second Configuration
	/*
	public static final String PARAM_LANGUAGE = "en";
	@ConfigurationParameter(
			name = PARAM_LANGUAGE,
			description = "default language for the text", 
			mandatory = true
		)
	private String language;
	*/
	
	public static final String PARAM_URL= "https://en.wikipedia.org/wiki/Ubiquitous_Knowledge_Processing_Lab";
	@ConfigurationParameter(
			name = PARAM_URL,
			description = "default URL for the text", 
			mandatory = true
		)
	private String typeReading;
	
	Document document;
	Elements docElements;
	private int idx = 0;
	
	
	@Override
	public void initialize(UimaContext context){
	//	Document doc = null;
		
		try {
			super.initialize(context);
		} catch (ResourceInitializationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		//	read = true;
			document = Jsoup.connect("https://en.wikipedia.org/wiki/Ubiquitous_Knowledge_Processing_Lab").get();
			docElements = document.select("body");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		 title = doc.title();
	}
	@Override
	public Progress[] getProgress() {
		// TODO Auto-generated method stub
		return new Progress[] {new ProgressImpl(idx+1,docElements.size(), Progress.ENTITIES)};
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		// TODO Auto-generated method stub
		return  idx < docElements.size();
	}

	@Override
	public void getNext(JCas j) throws IOException, CollectionException {
		// TODO Auto-generated method stub
	//	j.setDocumentText(title);
		String s = docElements.get(idx).text();		
		j.setDocumentText(s);
		idx++;
	//	j.setDocumentLanguage(language);
	//	read = false
	}

}
