package com.github.celeric.supplymanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
/**
 * Class customizing a {@code JPanel} object.
 * @author Bence
 *
 */
public class DefaultPanel extends JPanel{
	/**
	 * Id for serialization.
	 */
	private static final long serialVersionUID = 6017480117077808476L;
	/**
	 * List for GUI elements.
	 */
	protected JList<?> list;
	/**
	 * {@code JScrollPane} for scrolling my lists.
	 */
	protected JScrollPane sp;
	/**
	 * Constructor for the {@code DefaultPanel} object.
	 * @param array array containing the list objects.
	 */
	public DefaultPanel(Object array[]) {
		super();
		list = new JList<>(array);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sp = new JScrollPane(list);
		setBackground(Color.black);
		setLayout(new BorderLayout());
		add(sp);
	}	
}
