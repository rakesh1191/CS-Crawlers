package crawl_index;

import java.util.Map;

import org.apache.commons.math.linear.OpenMapRealVector;
import org.apache.commons.math.linear.RealVectorFormat;

public class getMethod {
	public Map terms;
    public OpenMapRealVector vector;
    
    public getMethod(Map terms) {
        this.terms = terms;
        this.vector = new OpenMapRealVector(terms.size());        
    }

    public void setEntry(String term, int freq) {
        if (terms.containsKey(term)) {
        	//
            int pos = (Integer) terms.get(term);
            vector.setEntry(pos, (double) freq);
        }
    }

    public void normalize() {
        double sum = vector.getL1Norm();
        vector = (OpenMapRealVector) vector.mapDivide(sum);
    }

    @Override
    public String toString() {
        RealVectorFormat formatter = new RealVectorFormat();
        return formatter.format(vector);
    }

}
