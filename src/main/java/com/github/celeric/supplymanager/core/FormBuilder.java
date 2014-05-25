package com.github.celeric.supplymanager.core;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for building custom forms.
 * 
 * @author Bence
 * 
 */
public class FormBuilder {
	/**
	 * Logger for logging events.
	 */
	static Logger logger = LoggerFactory.getLogger(FormBuilder.class);

	/**
	 * Builds a custom form.
	 * 
	 * @param labels labels of the custom form.
	 * @return a new custom panel.
	 */
	public static JPanel Builder(String labels[]) {
		logger.info("Current method: Builder(String labels[])");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < labels.length; i++) {
			sb.append("[20]");
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[fill][grow][grow][fill]", "[fill]"
				+ sb.toString() + "[fill]"));
		for (int i = 0; i < labels.length; i++) {
			JLabel label = new JLabel(labels[i], JLabel.TRAILING);
			panel.add(label, "cell 1 " + i);
			JTextField textField = new JTextField(10);
			label.setLabelFor(textField);
			panel.add(textField, "cell 2 " + i);
		}
		panel.setOpaque(true);
		logger.info("Panel has been established and returned.");
		return panel;
	}

}
