package net.moeding.datasketches;

import java.util.Hashtable;

import org.apache.datasketches.quantiles.DoublesSketch;
import org.apache.datasketches.quantiles.DoublesUnion;
import org.apache.datasketches.quantiles.UpdateDoublesSketch;

/**
 *
 * @author moedings
 * @see datasketches.apache.org
 */
public class ResponsetimeSketch {
    private Hashtable<String, UpdateDoublesSketch> sketches;
    private DoublesSketch result;
    
    private int maxK;
    
    public ResponsetimeSketch(final int k) {
        sketches = new Hashtable<String, UpdateDoublesSketch>();
        result = null;
        maxK = k;
    }

    public void update(String thread, long time) {
        UpdateDoublesSketch sketch;
        
        if (sketches.containsKey(thread)) {
            sketch = sketches.get(thread);
        }
        else {
            sketch = UpdateDoublesSketch.builder().setK(maxK).build();
            sketches.put(thread, sketch);                    
        }
        
        // this method is generally not thread safe, but we use a dedicated
        // Hashtable for each single thread, so nothing bad can happen here 
        sketch.update(time);
    }
    
    private void merge() {
        DoublesUnion union = DoublesUnion.builder().setMaxK(maxK).build();
        
        for (UpdateDoublesSketch sketch : sketches.values()) {
            union.update(sketch);
        }
        
        sketches.clear();      
        result = union.getResult();
    }

    public double getQuantileUpperBound(final double fraction) {
        if (result == null) merge();
        return result.getQuantileUpperBound(fraction);
    }
    
    public double getQuantileLowerBound(final double fraction) {
        if (result == null) merge();
        return result.getQuantileLowerBound(fraction);
    }

    public double[] getQuantiles(double[] fRanks) {
        if (result == null) merge();
        return result.getQuantiles(fRanks);
    }
    
    public double[] getQuantiles(final int evenlySpaced) {
        if (result == null) merge();
        return result.getQuantiles(evenlySpaced);
    }
    
    public double getRank(final double value) {
        if (result == null) merge();
        return result.getRank(value);
    }
    
    public double[] getPMF(final double[] splitPoints) {
        if (result == null) merge();
        return result.getPMF(splitPoints);
    }
    
    public double[] getCDF(final double[] splitPoints) {
        if (result == null) merge();
        return result.getCDF(splitPoints);
    }
    
    public double getMinValue() {
        if (result == null) merge();
        return result.getMinValue();
    }
    
    public double getMaxValue() {
        if (result == null) merge();
        return result.getMaxValue();
    }

    public long getN() {
        if (result == null) merge();
        return result.getN();
    }

    public double getNormalizedRankError(final boolean pmf) {
        if (result == null) merge();
        return result.getNormalizedRankError(pmf);
    }
    
}
