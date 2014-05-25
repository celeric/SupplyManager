package com.github.celeric.supplymanager.element;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest {

	@Test
	public void testOrder() {
		Order order = new Order(1, "Bence", "Alexa", "bencealexa@gmail.com", "készpénz", "személyes átvétel", 
				new java.sql.Date(new java.util.Date().getTime()));
		assertEquals(order.getOrderId(), 1);
		assertNotEquals(order.getClass(), OrderItem.class);
	}
	/**
	 * This tests the behavior of toLabelString, and expects a <code>NullPointerException</code>
	 */
	@Test(expected=NullPointerException.class)
	public void DueNullParameterTest() {
		Order ord = new Order(0, null, null, null, null, null, null);
		ord.toLabelString("");
	}

	/**
	 * This tests the behavior of toLabelString, and expects it to be False against an empty String.
	 */
	@Test
	public void DueTodayDateParameterTest() {
		Order ord = new Order(0, null, null, null, null, null, new java.sql.Date(new java.util.Date().getTime()));
		
		assertFalse(ord.toLabelString("").equals(""));
	}	

}
