/**
 * 
 */
package com.github.celeric.supplymanager.element;

/**
 * Class modeling the products. 
 * @author Bence
 *
 */
public class Product {
	/**
	 * Id of the product.
	 */
	private int productId;
	/**
	 * Id of the category.
	 */
	private int categoryId;
	/**
	 * Name of the product.
	 */
	private String productName;
	/**
	 * Description of the product.
	 */
	private String productDescription;
	/**
	 * List price of the product.
	 */
	private double listPrice;
	/**
	 * Quantity of the product.
	 */
	private int quantity;
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
	 * Sets the name of the product.
	 * @param productName the name of the product.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * Gets the description of the product.
	 * @return the description of the product.
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * Sets the description of the product.
	 * @param productDescription the description of the product.
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	/**
	 * Gets the list price of the product.
	 * @return the list price of the product.
	 */
	public double getListPrice() {
		return listPrice;
	}
	/**
	 * Sets the list price of the product.
	 * @param listPrice ths list price of the product.
	 */
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	/**
	 * Gets the quantity of the product.
	 * @return the quantity of the product.
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * Sets the quantity of the product.
	 * @param quantity the quantity of the product.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * Gets the id of the product related category.
	 * @return the product related category id.
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * Constructor for creating {@code Product} object.
	 * @param productId the id of the product.
	 * @param categoryId the id of the category related to the product.
	 * @param productName the name of the product.
	 * @param productDescription the description of the product.
	 * @param listPrice the list price of the product.
	 * @param quantity the quantity of the product.
	 */
	public Product(int productId, int categoryId, String productName,
			String productDescription, double listPrice, int quantity) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.listPrice = listPrice;
		this.quantity = quantity;
	}
	/**
	 * Generates a string for the appropriate JLabel display.
	 * @return string to display.
	 */
	public String toLabelString(){
		return "<HTML><p>Termék neve: "+productName+"</p><p>Termék leírása: "+productDescription+"</p><p>Listaár: "+listPrice+"Ft.</p><p>Raktárkészlet: "+quantity+"db.</p></HTML>";
	}
	
}
