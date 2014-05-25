package com.github.celeric.supplymanager.core;

import java.awt.EventQueue;
import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.celeric.supplymanager.ui.MyFrame;
import com.github.celeric.supplymanager.ui.MyPanel;

/**
 * Main class, containing the entry point of the program.
 * 
 * @author Bence
 */
public class Main {
	/**
	 * Logger, for logging events.
	 */
	final static Logger logger = LoggerFactory.getLogger(Main.class);
	/**
	 * Connection object for the database connection.
	 */
	public static Connection conn;
	/**
	 * The object representing the main window.
	 */
	public static MyFrame myFrame;
	/**
	 * Entry point, calling the first window's setup.
	 * 
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		logger.info("The program has started, current method: main()");
		startWindow();
		
	}
	/**
	 * Setting up windows if refresh is needed.
	 */
	public static void startWindow(){
		logger.info("current method: startWindow()");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				myFrame = new MyFrame("Supply Manager v1.0");
				myFrame.add(new MyPanel());
				myFrame.setVisible(true);
				logger.info("Window has been created and shown");
			}
		});
	}
}
