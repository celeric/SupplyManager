package com.github.celeric.supplymanager.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.celeric.supplymanager.util.ConnectionHelper;
/**
 * Class for customized {@code JFrame}.
 * @author Bence
 *
 */
public class MyFrame extends JFrame {
	/**
	 * Logger for logging events.
	 */
	Logger logger = LoggerFactory.getLogger(MyFrame.class);
	/**
	 * Id for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for {@code MyFrame} object.
	 * @param arg0 the title of the window.
	 * @throws HeadlessException if there is no head.
	 */
	public MyFrame(String arg0) throws HeadlessException {
		super(arg0);

		setBackground(Color.black);
		setBounds(0, 0, 800, 600);
		setMinimumSize(new Dimension(400, 200));
		WindowListener exitListener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				String opt[] = { "igen", "nem" };
				int confirm = JOptionPane.showOptionDialog(null,
						"Biztosan bezárja a programot?",
						"Kilépés megerősítése", JOptionPane.YES_NO_OPTION,

						JOptionPane.QUESTION_MESSAGE, null, opt, opt[0]);
				if (confirm == 0) {
					try {
						ConnectionHelper.getConnection().close();
						logger.info("SQL connection closed.");

					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					System.exit(0);
				}
			}
		};
		
		
		addWindowListener(exitListener);
		JMenuBar myMenuBar = new JMenuBar();
		JMenu myMenu = new JMenu("File");
		JMenuItem myMenuItem = new JMenuItem("Exit");
		myMenuItem.addActionListener(new ActionListener() {

			@Override
			/**
			 * Custom ActionEvent for the window's close button.
			 * @param arg0 the ActionEvent.
			 */
			public void actionPerformed(ActionEvent arg0) {
				logger.info("Current method: actionPerformed(ActionEvent arg0)");
				try {
					ConnectionHelper.getConnection().close();
					logger.info("SQL connection closed.");
				} catch (SQLException | IOException e1) {
					logger.trace("SQL connection could not been closed.");
					e1.printStackTrace();
				}
				logger.info("The programhas been closed.");
				System.exit(0);

			}
		});
		myMenu.add(myMenuItem);
		myMenuBar.add(myMenu);
		setJMenuBar(myMenuBar);		
	}
}
