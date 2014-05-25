package com.github.celeric.supplymanager.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.celeric.supplymanager.core.ActionControll;
/**
 * Class containing the the GUI buttons.
 * @author Bence
 *
 */
public class ButtonPanel extends JPanel {
	/**
	 * Logger, for logging events.
	 */
	Logger logger = LoggerFactory.getLogger(ButtonPanel.class);
	/**
	 * Id for serialization.
	 */
	private static final long serialVersionUID = 3581743587429739726L;
	/**
	 * ActionControll object for handling the events.
	 */
	ActionControll ac = new ActionControll();
	/**
	 * Details button.
	 */
	JButton details;
	/**
	 * Add button.
	 */
	JButton add;
	/**
	 * Remove button.
	 */
	JButton remove;
	/**
	 * Sell button.
	 */
	JButton sell;

	/**
	 * Constructor for the {@code ButtonPanel} object.
	 */
	public ButtonPanel() {
		details = new JButton("Bővebben");
		details.addActionListener(ac.detailsButtonOption);
		add = new JButton("Hozzáadás");
		add.addActionListener(ac.newItemOption);
		remove = new JButton("Eltávolítás");
		remove.addActionListener(ac.removeItemOption);
		sell = new JButton("Eladás");
		sell.addActionListener(ac.sellItemOption);
		setLayout(new MigLayout("", "[0][100][0]",
				"[fill][20][20][20][20][fill]"));
		setBackground(Color.black);
		setMaximumSize(new Dimension(400, 400));
		add(details, "cell 1 1,grow");
		add(add, "cell 1 2,grow");
		add(remove, "cell 1 3,grow");
		add(sell, "cell 1 4,grow");
		setVisible(true);
		logger.info("Button panel has been created");
	}
}
