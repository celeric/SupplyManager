package com.github.celeric.supplymanager.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class ConnectionHelperTest {

	@Test(expected=NullPointerException.class)
	public void testgetConnection() {
		try {
			Connection conn = ConnectionHelper.getConnection();
			conn = null;
			@SuppressWarnings("null")
			Statement stmt = conn.createStatement();
			stmt.executeQuery("select * from dual");
		
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	

	}


