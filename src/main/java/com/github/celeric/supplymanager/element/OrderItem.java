package com.github.celeric.supplymanager.element;

import com.github.celeric.supplymanager.util.Sql;
/**
 * Class modeling the OrderItems.
 * @author Bence
 *
 */
public class OrderItem {
	/**
	 * Id of the product.
	 */
	private int productId;
	/**
	 * Name of the product.
	 */
	private String productName;
	/**
	 * Serial of the product.
	 */
	private String productSerial;
	/**
	 * Status of the product.
	 */
	private String status;
	/**
	 * Id of the order.
	 */
	private int orderId;
	/**
	 * Gets the id of the product.
	 * @return the id of the product.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * Gets the name of the product.
	 * @return the name of the product.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * Gets the serial of the product. 
	 * @return the serial of the product.
	 */
	public String getProductSerial() {
		return productSerial;
	}
	/**
	 * Gets the id of the order.
	 * @return the id of the order.
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * Constructor generating the {@code OrderItem} object.
	 * @param productId the id of the product.
	 * @param productName the name of the product.
	 * @param productSerial the serial of the product.
	 * @param status the status of the product.
	 * @param orderId the id of the order.
	 */
	public OrderItem(int productId, String productName, String productSerial,
			String status, int orderId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productSerial = productSerial;
		this.status = status;
		this.orderId = orderId;
	}
	/**
	 * Generates a string for the appropriate JLabel display.
	 * @return string to display.
	 */
	public String toLabelString(){
		if(orderId == 0){
		return "<HTML><p>Termék neve: "+productName+"</p><p>Termék azonosítója: "+productSerial+"</p><p>Státusz: "+status+"</p><p>Rendelés azonosító: "+orderId+"</p></HTML>";
		}else{
			Order o = Sql.getOrderById(getOrderId());
			return  o.toLabelString("<p>Termék neve: "+productName+"</p><p>Termék azonosítója: "+productSerial+"</p><p>Státusz: "+status+"</p><p>Rendelés azonosító: "+orderId+"</p>");
		}
	}
}
