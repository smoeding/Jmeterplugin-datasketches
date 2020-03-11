package net.moeding.datasketches;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import org.apache.datasketches.quantiles.DoublesSketch;

public class DataSketchesPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField datasketchesError;
    private JFormattedTextField quantilesNum;
    private JCheckBox quantilesCheckBox;
    private JCheckBox histogramCheckBox;
    private JFormattedTextField histogramBins;
    private JRadioButton radioButton128;
    private JRadioButton radioButton256;
    private JRadioButton radioButton512;
    private JRadioButton radioButton1024;
    private JTextField quantilesFilename;
    private JTextField histogramFilename;
    private JLabel histogramFilenameLabel;

    /**
     * Create the panel.
     */
    public DataSketchesPanel() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{588, 0};
        gridBagLayout.rowHeights = new int[]{50, 50, 50, 0};
        gridBagLayout.columnWeights = new double[]{200.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        
        JPanel quantilesPanel = new JPanel();
        quantilesPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Quantiles", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        GridBagConstraints gbc_quantilesPanel = new GridBagConstraints();
        gbc_quantilesPanel.anchor = GridBagConstraints.NORTH;
        gbc_quantilesPanel.fill = GridBagConstraints.HORIZONTAL;
        gbc_quantilesPanel.insets = new Insets(0, 0, 5, 0);
        gbc_quantilesPanel.gridx = 0;
        gbc_quantilesPanel.gridy = 0;
        add(quantilesPanel, gbc_quantilesPanel);
        GridBagLayout gbl_quantilesPanel = new GridBagLayout();
        gbl_quantilesPanel.columnWidths = new int[]{588, 0};
        gbl_quantilesPanel.rowHeights = new int[]{30, 0};
        gbl_quantilesPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_quantilesPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        quantilesPanel.setLayout(gbl_quantilesPanel);
        
        JPanel quantilesGridBagPanel = new JPanel();
        GridBagConstraints gbc_quantilesGridBagPanel = new GridBagConstraints();
        gbc_quantilesGridBagPanel.anchor = GridBagConstraints.NORTHWEST;
        gbc_quantilesGridBagPanel.gridx = 0;
        gbc_quantilesGridBagPanel.gridy = 0;
        quantilesPanel.add(quantilesGridBagPanel, gbc_quantilesGridBagPanel);
        quantilesGridBagPanel.setBorder(null);
        GridBagLayout gbl_quantilesGridBagPanel = new GridBagLayout();
        gbl_quantilesGridBagPanel.columnWidths = new int[] {100, 100, 100, 150};
        gbl_quantilesGridBagPanel.rowHeights = new int[] {25, 25};
        gbl_quantilesGridBagPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0};
        gbl_quantilesGridBagPanel.rowWeights = new double[]{0.0, 0.0};
        quantilesGridBagPanel.setLayout(gbl_quantilesGridBagPanel);
        
        quantilesCheckBox = new JCheckBox("Enabled");
        quantilesCheckBox.setSelected(true);
        GridBagConstraints gbc_quantilesCheckBox = new GridBagConstraints();
        gbc_quantilesCheckBox.anchor = GridBagConstraints.WEST;
        gbc_quantilesCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_quantilesCheckBox.gridx = 0;
        gbc_quantilesCheckBox.gridy = 0;
        quantilesGridBagPanel.add(quantilesCheckBox, gbc_quantilesCheckBox);
        
        JLabel quantilesLabel = new JLabel("Quantiles:");
        GridBagConstraints gbc_quantilesLabel = new GridBagConstraints();
        gbc_quantilesLabel.anchor = GridBagConstraints.WEST;
        gbc_quantilesLabel.insets = new Insets(0, 0, 5, 5);
        gbc_quantilesLabel.gridx = 1;
        gbc_quantilesLabel.gridy = 0;
        quantilesGridBagPanel.add(quantilesLabel, gbc_quantilesLabel);
        
        quantilesNum = new JFormattedTextField();
        quantilesNum.setText("10");
        quantilesLabel.setLabelFor(quantilesNum);
        quantilesNum.setColumns(10);
        GridBagConstraints gbc_quantilesNum = new GridBagConstraints();
        gbc_quantilesNum.insets = new Insets(0, 0, 5, 5);
        gbc_quantilesNum.anchor = GridBagConstraints.WEST;
        gbc_quantilesNum.gridx = 2;
        gbc_quantilesNum.gridy = 0;
        quantilesGridBagPanel.add(quantilesNum, gbc_quantilesNum);
        
        JLabel quantilesFilenameLabel = new JLabel("Filename:");
        GridBagConstraints gbc_quantilesFilenameLabel = new GridBagConstraints();
        gbc_quantilesFilenameLabel.insets = new Insets(0, 0, 0, 5);
        gbc_quantilesFilenameLabel.anchor = GridBagConstraints.WEST;
        gbc_quantilesFilenameLabel.gridx = 1;
        gbc_quantilesFilenameLabel.gridy = 1;
        quantilesGridBagPanel.add(quantilesFilenameLabel, gbc_quantilesFilenameLabel);
        
        quantilesFilename = new JTextField();
        quantilesFilenameLabel.setLabelFor(quantilesFilename);
        GridBagConstraints gbc_quantilesFilename = new GridBagConstraints();
        gbc_quantilesFilename.gridwidth = 2;
        gbc_quantilesFilename.fill = GridBagConstraints.HORIZONTAL;
        gbc_quantilesFilename.gridx = 2;
        gbc_quantilesFilename.gridy = 1;
        quantilesGridBagPanel.add(quantilesFilename, gbc_quantilesFilename);
        quantilesFilename.setColumns(10);
        
        JPanel histogramPanel = new JPanel();
        histogramPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Histogram", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        GridBagConstraints gbc_histogramPanel = new GridBagConstraints();
        gbc_histogramPanel.fill = GridBagConstraints.BOTH;
        gbc_histogramPanel.insets = new Insets(0, 0, 5, 0);
        gbc_histogramPanel.gridx = 0;
        gbc_histogramPanel.gridy = 1;
        add(histogramPanel, gbc_histogramPanel);
        GridBagLayout gbl_histogramPanel = new GridBagLayout();
        gbl_histogramPanel.columnWidths = new int[]{588, 0};
        gbl_histogramPanel.rowHeights = new int[]{30, 0};
        gbl_histogramPanel.columnWeights = new double[]{200.0, Double.MIN_VALUE};
        gbl_histogramPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        histogramPanel.setLayout(gbl_histogramPanel);
        
        JPanel histogramGridBagPanel = new JPanel();
        histogramGridBagPanel.setBorder(null);
        GridBagConstraints gbc_histogramGridBagPanel = new GridBagConstraints();
        gbc_histogramGridBagPanel.anchor = GridBagConstraints.NORTHWEST;
        gbc_histogramGridBagPanel.gridx = 0;
        gbc_histogramGridBagPanel.gridy = 0;
        histogramPanel.add(histogramGridBagPanel, gbc_histogramGridBagPanel);
        GridBagLayout gbl_histogramGridBagPanel = new GridBagLayout();
        gbl_histogramGridBagPanel.columnWidths = new int[]{100, 100, 100, 150, 0};
        gbl_histogramGridBagPanel.rowHeights = new int[]{25, 25, 0};
        gbl_histogramGridBagPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_histogramGridBagPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        histogramGridBagPanel.setLayout(gbl_histogramGridBagPanel);
        
        histogramCheckBox = new JCheckBox("Enabled");
        histogramCheckBox.setSelected(true);
        GridBagConstraints gbc_histogramCheckBox = new GridBagConstraints();
        gbc_histogramCheckBox.anchor = GridBagConstraints.WEST;
        gbc_histogramCheckBox.insets = new Insets(0, 0, 5, 5);
        gbc_histogramCheckBox.gridx = 0;
        gbc_histogramCheckBox.gridy = 0;
        histogramGridBagPanel.add(histogramCheckBox, gbc_histogramCheckBox);
        
        JLabel histogramBinsLabel = new JLabel("Histogram Bins:");
        GridBagConstraints gbc_histogramBinsLabel = new GridBagConstraints();
        gbc_histogramBinsLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_histogramBinsLabel.insets = new Insets(0, 0, 5, 5);
        gbc_histogramBinsLabel.gridx = 1;
        gbc_histogramBinsLabel.gridy = 0;
        histogramGridBagPanel.add(histogramBinsLabel, gbc_histogramBinsLabel);
        
        histogramBins = new JFormattedTextField();
        histogramBins.setText("10");
        histogramBinsLabel.setLabelFor(histogramBins);
        histogramBins.setColumns(10);
        GridBagConstraints gbc_histogramBins = new GridBagConstraints();
        gbc_histogramBins.insets = new Insets(0, 0, 5, 5);
        gbc_histogramBins.anchor = GridBagConstraints.WEST;
        gbc_histogramBins.gridx = 2;
        gbc_histogramBins.gridy = 0;
        histogramGridBagPanel.add(histogramBins, gbc_histogramBins);
        
        histogramFilenameLabel = new JLabel("Filename:");
        GridBagConstraints gbc_histogramFilenameLabel = new GridBagConstraints();
        gbc_histogramFilenameLabel.insets = new Insets(0, 0, 0, 5);
        gbc_histogramFilenameLabel.anchor = GridBagConstraints.WEST;
        gbc_histogramFilenameLabel.gridx = 1;
        gbc_histogramFilenameLabel.gridy = 1;
        histogramGridBagPanel.add(histogramFilenameLabel, gbc_histogramFilenameLabel);
        
        histogramFilename = new JTextField();
        histogramFilenameLabel.setLabelFor(histogramFilename);
        GridBagConstraints gbc_histogramFilename = new GridBagConstraints();
        gbc_histogramFilename.gridwidth = 2;
        gbc_histogramFilename.fill = GridBagConstraints.HORIZONTAL;
        gbc_histogramFilename.gridx = 2;
        gbc_histogramFilename.gridy = 1;
        histogramGridBagPanel.add(histogramFilename, gbc_histogramFilename);
        histogramFilename.setColumns(10);
        
        JPanel datasketchesPanel = new JPanel();
        datasketchesPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Datasketches", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        GridBagConstraints gbc_datasketchesPanel = new GridBagConstraints();
        gbc_datasketchesPanel.fill = GridBagConstraints.BOTH;
        gbc_datasketchesPanel.gridx = 0;
        gbc_datasketchesPanel.gridy = 2;
        add(datasketchesPanel, gbc_datasketchesPanel);
        GridBagLayout gbl_datasketchesPanel = new GridBagLayout();
        gbl_datasketchesPanel.columnWidths = new int[]{100, 60, 60, 60, 60, 500, 0};
        gbl_datasketchesPanel.rowHeights = new int[] {25, 25};
        gbl_datasketchesPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_datasketchesPanel.rowWeights = new double[]{0.0, 0.0};
        datasketchesPanel.setLayout(gbl_datasketchesPanel);
        
        JLabel datasketchesSizeLabel = new JLabel("Size:");
        GridBagConstraints gbc_datasketchesSizeLabel = new GridBagConstraints();
        gbc_datasketchesSizeLabel.anchor = GridBagConstraints.WEST;
        gbc_datasketchesSizeLabel.insets = new Insets(0, 0, 5, 5);
        gbc_datasketchesSizeLabel.gridx = 0;
        gbc_datasketchesSizeLabel.gridy = 0;
        datasketchesPanel.add(datasketchesSizeLabel, gbc_datasketchesSizeLabel);
        
        radioButton128 = new JRadioButton("128");
        radioButton128.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (radioButton128.isSelected()) {
                    double err = 100 * DoublesSketch.getNormalizedRankError(128, true);
                    datasketchesError.setText(String.format("%.2f %%", err));
                }
            }
        });
        buttonGroup.add(radioButton128);
        GridBagConstraints gbc_radioButton128 = new GridBagConstraints();
        gbc_radioButton128.anchor = GridBagConstraints.WEST;
        gbc_radioButton128.insets = new Insets(0, 0, 5, 5);
        gbc_radioButton128.gridx = 1;
        gbc_radioButton128.gridy = 0;
        datasketchesPanel.add(radioButton128, gbc_radioButton128);
        
        radioButton256 = new JRadioButton("256");
        radioButton256.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (radioButton256.isSelected()) {
                    double err = 100 * DoublesSketch.getNormalizedRankError(256, true);
                    datasketchesError.setText(String.format("%.2f %%", err));
                }
            }
        });
        buttonGroup.add(radioButton256);
        GridBagConstraints gbc_radioButton256 = new GridBagConstraints();
        gbc_radioButton256.anchor = GridBagConstraints.WEST;
        gbc_radioButton256.insets = new Insets(0, 0, 5, 5);
        gbc_radioButton256.gridx = 2;
        gbc_radioButton256.gridy = 0;
        datasketchesPanel.add(radioButton256, gbc_radioButton256);
        
        radioButton512 = new JRadioButton("512");
        radioButton512.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if (radioButton512.isSelected()) {
                    double err = 100 * DoublesSketch.getNormalizedRankError(512, true);
                    datasketchesError.setText(String.format("%.2f %%", err));
                }
            }
        });
        buttonGroup.add(radioButton512);
        GridBagConstraints gbc_radioButton512 = new GridBagConstraints();
        gbc_radioButton512.anchor = GridBagConstraints.WEST;
        gbc_radioButton512.insets = new Insets(0, 0, 5, 5);
        gbc_radioButton512.gridx = 3;
        gbc_radioButton512.gridy = 0;
        datasketchesPanel.add(radioButton512, gbc_radioButton512);
        
        radioButton1024 = new JRadioButton("1024");
        radioButton1024.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if (radioButton1024.isSelected()) {
                    double err = 100 * DoublesSketch.getNormalizedRankError(1024, true);
                    datasketchesError.setText(String.format("%.2f %%", err));
                }
            }
        });
        radioButton1024.setSelected(true);
        buttonGroup.add(radioButton1024);
        GridBagConstraints gbc_radioButton1024 = new GridBagConstraints();
        gbc_radioButton1024.anchor = GridBagConstraints.WEST;
        gbc_radioButton1024.insets = new Insets(0, 0, 5, 5);
        gbc_radioButton1024.gridx = 4;
        gbc_radioButton1024.gridy = 0;
        datasketchesPanel.add(radioButton1024, gbc_radioButton1024);
        
        JLabel datasketchesErrorLabel = new JLabel("Estimated Error:");
        GridBagConstraints gbc_datasketchesErrorLabel = new GridBagConstraints();
        gbc_datasketchesErrorLabel.anchor = GridBagConstraints.WEST;
        gbc_datasketchesErrorLabel.insets = new Insets(0, 0, 0, 5);
        gbc_datasketchesErrorLabel.gridx = 0;
        gbc_datasketchesErrorLabel.gridy = 1;
        datasketchesPanel.add(datasketchesErrorLabel, gbc_datasketchesErrorLabel);
        
        datasketchesError = new JTextField();
        datasketchesError.setBorder(null);
        datasketchesError.setEditable(false);
        GridBagConstraints gbc_datasketchesError = new GridBagConstraints();
        gbc_datasketchesError.anchor = GridBagConstraints.WEST;
        gbc_datasketchesError.insets = new Insets(0, 0, 0, 5);
        gbc_datasketchesError.gridx = 1;
        gbc_datasketchesError.gridy = 1;
        datasketchesPanel.add(datasketchesError, gbc_datasketchesError);
        datasketchesError.setColumns(6);

    }
    
    public JFormattedTextField getQuantilesNum() {
        return quantilesNum;
    }
    public JCheckBox getQuantilesCheckBox() {
        return quantilesCheckBox;
    }
    public JCheckBox getHistogramCheckBox() {
        return histogramCheckBox;
    }
    public JFormattedTextField getHistogramBins() {
        return histogramBins;
    }
    public JRadioButton getRadioButton128() {
        return radioButton128;
    }
    public JRadioButton getRadioButton256() {
        return radioButton256;
    }
    public JRadioButton getRadioButton512() {
        return radioButton512;
    }
    public JRadioButton getRadioButton1024() {
        return radioButton1024;
    }
    public JTextField getQuantilesFilename() {
        return quantilesFilename;
    }
    public JTextField getHistogramFilename() {
        return histogramFilename;
    }
}
