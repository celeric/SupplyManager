package com.github.celeric.supplymanager.element;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderItemTest {

	@Test
	public void testOrderItem() {
		OrderItem oi = new OrderItem(1, "Talpas félhomálymérő", "ASDFG1234", "Hiánycikk", 125);
		assertEquals(oi.getOrderId(), 125);
		
	}

	@Test
	public void testOrderItem2() {
		OrderItem oi = new OrderItem(1, "Talpas félhomálymérő", "ASDFG1234", "Hiánycikk", 125);
		assertEquals(oi.getOrderId(), 125);
		assertNotEquals(oi.getProductName(), "Talpatlan félhomálymérő");
		assertNotEquals(oi, new OrderItem(1, "Talpas félhomálymérő", "ASDFG1234", "Hiánycikk", 125));
	}
	@Test
	public void testOrderItem3() {
		OrderItem oi = new OrderItem(1, "Talpas félhomálymérő", "ASDFG1234", "Hiánycikk", 125);
		assertNotEquals(oi, new OrderItem(1, "Talpas félhomálymérő", "ASDFG1234", "Hiánycikk", 125));
	}
}
