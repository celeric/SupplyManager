/**
 * 
 */
package com.github.celeric.supplymanager.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Bence
 *
 */
public class ConnectionHelper {
	/**
	 * Logger for logging events.
	 */
	final static Logger logger = LoggerFactory.getLogger(ConnectionHelper.class);
	/**
	 * Url for connecting to the database.
	 */
	private static final String url = "jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g";
	/**
	 * The Connection object.
	 */
	private static Connection conn;
	
	/**
	 * Constructor for the {@code ConnectionHelper} object.
	 */
	private ConnectionHelper(){}
	
	/**
	 * Returns a usable database connection.
	 * @return a database connection.
	 * @throws IOException when the connection is obstructed.
	 * @throws SQLException when the connection is obstructed.
	 */
	public static Connection getConnection() throws IOException, SQLException{
		if(conn == null){
			logger.info("New SQL connection established.");
			String user = "H_DE7FBF";
			String password = "ab890314";
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(true);
		}
		return conn;
	}
}
