/**
 * 
 */
package net.moeding.datasketches;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.jmeter.JMeter;
import org.apache.jmeter.engine.util.NoThreadClone;
import org.apache.jmeter.reporters.AbstractListenerElement;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleListener;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.TestStateListener;
import org.slf4j.LoggerFactory;



/**
 * @author moedings
 *
 */
public class DataSketches extends AbstractListenerElement
    implements SampleListener, Serializable, NoThreadClone, TestStateListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DataSketches.class);
    
    private PrintStream out;
    private ConcurrentHashMap<String, ResponsetimeSketch> distribution = new ConcurrentHashMap<String, ResponsetimeSketch>();
    
    // Lock used to guard static mutable variables
    private static final Object LOCK = new Object();
    
    private static class JMeterLoggerOutputStream extends PrintStream {
        public JMeterLoggerOutputStream(org.slf4j.Logger log) {
            super(System.out);
        }
        
        @Override
        public void println(String msg) {
            log.info(msg);
        }
    }

    @Override
    public void testStarted() {
        testStarted("LOCAL");
    }

    @Override
    public void testStarted(String s) {
        out = JMeter.isNonGUI() ? System.out : new JMeterLoggerOutputStream(log);
    }

    @Override
    public void testEnded() {
        testEnded("LOCAL");
    }

    @Override
    public void testEnded(String s) {
        List<String> labels = new ArrayList<String>(distribution.keySet());
        Collections.sort(labels);
        
        
        for (String label : labels) {
            ResponsetimeSketch sketch = distribution.get(label);
            
            // always get min, median, max
            double[] values = sketch.getQuantiles(3);
            double minValue    = values[0];
            double medianValue = values[1];
            double maxValue    = values[2];
            
            out.println(label + ": min=" + minValue + "\tmedian=" + medianValue + "\tmax=" + maxValue);
            
            if (getQuantilesEnabled()) {
                out.println("Writing quantiles to " + getQuantilesFilename());
                values = sketch.getQuantiles(getQuantilesNum());
                out.println(label + ": quantiles=" + Arrays.toString(values));
            }
            
            if (getHistogramEnabled()) {
                double[] splitpoints = calcSplitpoints(minValue, maxValue, getHistogramBins());
                out.println("splitpoints=" + Arrays.toString(splitpoints));

                out.println("Writing histogram to " + getHistogramFilename());
                
                values = sketch.getPMF(splitpoints);
                out.println(label + ": pmf      =" + Arrays.toString(values));
            }
        }
        
        distribution = null;
    }
    
    private double[] calcSplitpoints(double minValue, double maxValue, int bins) {
        int breakpoints = bins - 1;
        double[] result = new double[breakpoints];
        double range = maxValue - minValue;
        double step  = Math.floor(range / (bins - 1));
        double start = Math.ceil(minValue / step) * step;

        for(int i=0; i<breakpoints; i++) {
            result[i] = start + (i * step);
        }
        
        return result;
    }

    @Override
    public void sampleOccurred(SampleEvent se) {
        SampleResult res = se.getResult();
        String hashkey = res.getSampleLabel();
        
        ResponsetimeSketch sketch;
        
        if (distribution.containsKey(hashkey)) {
            sketch = distribution.get(hashkey);
        }
        else {
            synchronized (LOCK) {
                // retry synchronized: this should only happen once for every label 
                if (distribution.containsKey(hashkey)) {
                    sketch = distribution.get(hashkey);
                }
                else {
                    sketch = new ResponsetimeSketch(getDatasketchSize());
                    distribution.put(hashkey, sketch);                    
                }
            }
        }
        
        //out.println("getBytesAsLong=" + res.getBytesAsLong());
        //out.println("getSampleLabel=" + res.getSampleLabel());
        //out.println("getSampleCount=" + res.getSampleCount());
        //out.println("getTime=" + res.getTime());
        //out.println("isSuccessful=" + (res.isSuccessful() ? "yes" : "no"));
        
        sketch.update(res.getThreadName(), res.getTime());
    }

    @Override
    public void sampleStarted(SampleEvent se) {
        out.println("sampleStarted");
    }

    @Override
    public void sampleStopped(SampleEvent se) {
        out.println("sampleStopped");
    }
    
    public int getDatasketchSize() {
        return getPropertyAsInt(DataSketchesElements.DATASKETCH_SIZE);
    }

    public void setDatasketchSize(int datasketchSize) {
        setProperty(DataSketchesElements.DATASKETCH_SIZE, datasketchSize);
    }

    public boolean getQuantilesEnabled() {
        return getPropertyAsBoolean(DataSketchesElements.QUANTILES_ENABLED);
    }
    
    public void setQuantilesEnabled(boolean enabled) {
        setProperty(DataSketchesElements.QUANTILES_ENABLED, enabled);
    }
    
    public int getQuantilesNum() {
        return getPropertyAsInt(DataSketchesElements.QUANTILES_NUM);
    }

    public void setQuantilesNum(int quantilesNum) {
        setProperty(DataSketchesElements.QUANTILES_NUM, quantilesNum);
    }

    public String getQuantilesFilename() {
        return getPropertyAsString(DataSketchesElements.QUANTILES_FILENAME);
    }
    
    public void setQuantilesFilename(String filename) {
        setProperty(DataSketchesElements.QUANTILES_FILENAME, filename);
    }

    public boolean getHistogramEnabled() {
        return getPropertyAsBoolean(DataSketchesElements.HISTOGRAM_ENABLED);
    }
    
    public void setHistogramEnabled(boolean enabled) {
        setProperty(DataSketchesElements.HISTOGRAM_ENABLED, enabled);
    }
    
    public int getHistogramBins() {
        return getPropertyAsInt(DataSketchesElements.HISTOGRAM_BINS);
    }

    public void setHistogramBins(int histogramBins) {
        setProperty(DataSketchesElements.HISTOGRAM_BINS, histogramBins);
    }
    
    public String getHistogramFilename() {
        return getPropertyAsString(DataSketchesElements.HISTOGRAM_FILENAME);
    }
    
    public void setHistogramFilename(String filename) {
        setProperty(DataSketchesElements.HISTOGRAM_FILENAME, filename);
    }
}
