/**
 * 
 */
package com.github.celeric.supplymanager.element;

/**
 * Class modeling the product categories.
 * 
 * @author Bence
 *
 */
public class ProductCategory {
	/**
	 * Id of the category.
	 */
	private int categoryId;
	/**
	 * Name of the category.
	 */
	private String categoryName;
	/**
	 * Description of the Category.
	 */
	private String categoryDescription;
	/**
	 * Constructor for creating a {@code ProductCategory} object.
	 * @param categoryId id of the category.
	 * @param categoryName name of the category.
	 * @param categoryDescription description of the category.
	 */
	public ProductCategory(int categoryId, String categoryName,
			String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}
	/**
	 * Gets the name of the category.
	 * @return the name of the category.
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * Sets the name of the category.
	 * @param categoryName thename of the category.
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * Gets the description of the category.
	 * @return the description of the category.
	 */
	public String getCategoryDescription() {
		return categoryDescription;
	}
	/**
	 * Sets the description of the category.
	 * @param categoryDescription the desciption of the category.
	 */
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	/**
	 * Gets the id of the category. 
	 * @return the id of the category.
	 */
	public int getCategoryId() {
		return categoryId;
	}
	
	/**
	 * Generates a string for the appropriate JLabel display.
	 * @return string to display.
	 */
	public String toLabelString() {
		  String category = "Kategória";
		  return new StringBuilder().append("<HTML><p>")
		     .append(category).append(" neve: ").append(categoryName)
		     .append("</p><p>")
		     .append(category).append(" leírása: ").append(categoryDescription)
		     .append("</p></HTML>")
		     .toString();
		 }
	

}
