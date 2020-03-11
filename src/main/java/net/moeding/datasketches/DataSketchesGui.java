/**
 * 
 */
package net.moeding.datasketches;

import java.awt.BorderLayout;

import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.visualizers.gui.AbstractListenerGui;
import org.slf4j.LoggerFactory;


/**
 * @author moedings
 *
 */
public class DataSketchesGui extends AbstractListenerGui {

    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private DataSketchesPanel configPanel;
    
    public DataSketchesGui() {
        super();

        setLayout(new BorderLayout(0, 10));
        setBorder(makeBorder());

        add(makeTitlePanel(), "North");

        configPanel = new DataSketchesPanel();
        add(configPanel);
    }
    
    @Override
    public String getStaticLabel() {
        return "Data Sketches";
    }
   
    @Override
    public String getLabelResource() {
        return getClass().getCanonicalName();
    }

    @Override
    public TestElement createTestElement() {
        TestElement te = new DataSketches();
        modifyTestElement(te);
        return te;
    }

    @Override
    public void modifyTestElement(TestElement element) {
        configureTestElement(element);
        
        element.setProperty(DataSketchesElements.QUANTILES_ENABLED, configPanel.getQuantilesCheckBox().isSelected());
        element.setProperty(DataSketchesElements.QUANTILES_NUM, configPanel.getQuantilesNum().getText());
        element.setProperty(DataSketchesElements.QUANTILES_FILENAME, configPanel.getQuantilesFilename().getText());

        element.setProperty(DataSketchesElements.HISTOGRAM_ENABLED, configPanel.getHistogramCheckBox().isSelected());
        element.setProperty(DataSketchesElements.HISTOGRAM_BINS, configPanel.getHistogramBins().getText());
        element.setProperty(DataSketchesElements.HISTOGRAM_FILENAME, configPanel.getHistogramFilename().getText());

        if (configPanel.getRadioButton128().isSelected())
            element.setProperty(DataSketchesElements.DATASKETCH_SIZE, 128);
        
        if (configPanel.getRadioButton256().isSelected())
            element.setProperty(DataSketchesElements.DATASKETCH_SIZE, 256);
        
        if (configPanel.getRadioButton512().isSelected())
            element.setProperty(DataSketchesElements.DATASKETCH_SIZE, 512);
        
        if (configPanel.getRadioButton1024().isSelected())
            element.setProperty(DataSketchesElements.DATASKETCH_SIZE, 1024);
        
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        
        configPanel.getQuantilesCheckBox().setSelected(element.getPropertyAsBoolean(DataSketchesElements.QUANTILES_ENABLED));
        configPanel.getQuantilesNum().setText(element.getPropertyAsString(DataSketchesElements.QUANTILES_NUM));
        configPanel.getQuantilesFilename().setText(element.getPropertyAsString(DataSketchesElements.QUANTILES_FILENAME));
        
        configPanel.getHistogramCheckBox().setSelected(element.getPropertyAsBoolean(DataSketchesElements.HISTOGRAM_ENABLED));
        configPanel.getHistogramBins().setText(element.getPropertyAsString(DataSketchesElements.HISTOGRAM_BINS));
        configPanel.getHistogramFilename().setText(element.getPropertyAsString(DataSketchesElements.HISTOGRAM_FILENAME));
        
        int size = element.getPropertyAsInt(DataSketchesElements.DATASKETCH_SIZE); 
        
        configPanel.getRadioButton128().setSelected(size == 128);
        configPanel.getRadioButton256().setSelected(size == 256);
        configPanel.getRadioButton512().setSelected(size == 512);
        configPanel.getRadioButton1024().setSelected(size == 1024);
    }
}
