package com.github.celeric.supplymanager.element;

import java.sql.Date;
/**
 * Class modeling the orders. 
 * @author Bence
 *
 */
public class Order {
	/**
	 * Id of the order.
	 */
	private int orderId;
	/**
	 * First name of the customer.
	 */
	private String firstName;
	/**
	 * Last name of the customer.
	 */
	private String lastName;
	/**
	 * Email address of the customer.
	 */
	private String email;
	/**
	 * Mode of purchase.
	 */
	private String purchase;
	/**
	 * Mode of reception.
	 */
	private String reception;
	/**
	 * Due date of reception.
	 */
	private Date due;
	/**
	 * Gets the id of the order.
	 * @return the id of the order.
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * Constructor for {@code Order} object.
	 * @param orderId the id of the order.
	 * @param firstName the first name of the customer.
	 * @param lastName the last name of the customer.
	 * @param email the e-mail address of the customer.
	 * @param purchase the mode of the purchase
	 * @param reception the mode of the reception.
	 * @param due the due date of the reception.
	 */
	public Order(int orderId, String firstName, String lastName, String email,
			String purchase, String reception, Date due) {
		super();
		this.orderId = orderId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.purchase = purchase;
		this.reception = reception;
		this.due = due;
	}
	/**
	 * Generates a string for the appropriate JLabel display.
	 * @return string to display.
	 * @param s string object from the {@code OrderItem} object.
	 */
	@SuppressWarnings("deprecation")
	public String toLabelString(String s){
		 
			  String rr = "Rendelő";
			  return new StringBuilder().append("<HTML><p>")
					  .append(s)
					     .append("</p><p>")
			     .append(rr).append(" neve: ").append(firstName + " " + lastName)
			     .append("</p><p>")
			     .append(rr).append(" e-mail címe: ").append(email)
			     .append("</p><p>")
			     .append("Rendelés ideje: ").append(due.toLocaleString())
			     .append("</p><p>")
			     .append("Átvétel módja: ").append(reception)
			     .append("</p><p>")
			     .append("Fizetés módja: ").append(purchase)
			     .append("</p></HTML>")
			     .toString();
			 }

}
