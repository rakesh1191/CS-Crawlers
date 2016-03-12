package crawl_index;

import java.io.IOException;

import org.apache.lucene.store.LockObtainFailedException;

public class getIndex {	
		
		public static void main(String[] args) throws LockObtainFailedException, IOException {
			
			getCosineSimilarity();

		}
		
	
		
		
		 public static void getCosineSimilarity() throws LockObtainFailedException, IOException
		    {
		       indexerTfidf index = new indexerTfidf();
		       index.index();
		       VectorGenerator vectorGenerator = new VectorGenerator();
		       vectorGenerator.GetAllTerms();       
		       getMethod[] docVector = vectorGenerator.GetDocumentVectors(); // getting document vectors
		       for(int i = 0; i < docVector.length; i++)
		       {
		           double cosineSimilarity = calculateSilmilarity.CosineSimilarity(docVector[1], docVector[i]);
		           System.out.println("Cosine Similarity Score between document 0 and "+i+"  = " + cosineSimilarity);
		       }    
		    }

	}


