package crawl_index;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

public class AllTerms {
	private Map<String,Integer> allTerms;
    Integer totalNoOfDocumentInIndex;
    IndexReader indexReader;
    
    public static IndexReader GetIndexReader() throws IOException {
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File("C:/index/nn")));
        return indexReader;
    }

    // Method for Calculating total number in index //
 
    public static Integer TotalDocumentInIndex() throws IOException
    {
        Integer maxDoc = GetIndexReader().maxDoc();
        GetIndexReader().close();
        return maxDoc;
    }
    
    public AllTerms() throws IOException
    {    
        allTerms= new HashMap<String, Integer>();
        indexReader = GetIndexReader();
        totalNoOfDocumentInIndex = TotalDocumentInIndex();
    }
        
    public void initAllTerms() throws IOException
    {
        int pos = 0;
        for (int docId = 0; docId < totalNoOfDocumentInIndex; docId++) {
            Terms vector = indexReader.getTermVector(docId, "contents1");
            TermsEnum termsEnum = null;
            termsEnum = vector.iterator(termsEnum);
            BytesRef text = null;
            while ((text = termsEnum.next()) != null) {
                String term = text.utf8ToString();
                allTerms.put(term, pos++);
            }
        }               
        //Update postition
        pos = 0;
        for(Entry s: allTerms.entrySet())
        {        
            System.out.println(s.getKey());
            s.setValue(pos++);
        }
    }
    
    public Map<String,Integer> getAllTerms() {
        return allTerms;
    }
	
}
