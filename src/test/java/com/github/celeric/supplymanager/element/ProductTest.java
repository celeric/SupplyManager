package com.github.celeric.supplymanager.element;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductTest {

	@Test
	public void testProduct() {
		Product pp = new Product(1, 1, "Valami", "Valamilyen termék", 500.2, 132);
		assertNotEquals(pp.getListPrice(), 540, 1e-15);
	}

}
