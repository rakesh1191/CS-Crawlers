package crawl_index;

public class calculateSilmilarity {

	public static double CosineSimilarity(getMethod d1,getMethod d2) {
        double cosinesimilarity;
        try {
            cosinesimilarity = (d1.vector.dotProduct(d2.vector))
                    / (d1.vector.getNorm() * d2.vector.getNorm());
        } catch (Exception e) {
            return 0.0;
        }
        return cosinesimilarity;
    }

}
