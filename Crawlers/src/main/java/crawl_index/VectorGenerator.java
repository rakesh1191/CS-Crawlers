package crawl_index;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
public class VectorGenerator {
	getMethod[] getMethod;
	    private Map allterms;
	    Integer totalNoOfDocumentInIndex;
	    IndexReader indexReader;
	    
	    public static IndexReader GetIndexReader() throws IOException {
	        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("C:/index/nn")));
	        return indexReader;
	    }
	    
	    public static Integer TotalDocumentInIndex() throws IOException
	    {
	        Integer maxDoc = GetIndexReader().maxDoc();
	        GetIndexReader().close();
	        return maxDoc;
	    }
	    
	    public VectorGenerator() throws IOException
	    {
	        allterms = new HashMap<Object, Object>();
	        indexReader = GetIndexReader();
	        totalNoOfDocumentInIndex = TotalDocumentInIndex();
	        getMethod = new getMethod[totalNoOfDocumentInIndex];
	    }
	    
	    public void GetAllTerms() throws IOException
	    {
	        AllTerms allTerms = new AllTerms();
	        allTerms.initAllTerms();
	        allterms = allTerms.getAllTerms();
	    }
	    
	    public getMethod[] GetDocumentVectors() throws IOException {
	        for (int docId = 0; docId < totalNoOfDocumentInIndex; docId++) {
	            Terms vector = indexReader.getTermVector(docId, "contents1");
	            TermsEnum termsEnum = null;
	            termsEnum = vector.iterator(termsEnum);
	            BytesRef text = null;            
	            getMethod[docId] = new getMethod(allterms);            
	            while ((text = termsEnum.next()) != null) {
	                String term = text.utf8ToString();
	                int freq = (int) termsEnum.totalTermFreq();
	                getMethod[docId].setEntry(term, freq);
	            }
	            getMethod[docId].normalize();
	        }        
	        indexReader.close();
	        return getMethod;
	    }

}
