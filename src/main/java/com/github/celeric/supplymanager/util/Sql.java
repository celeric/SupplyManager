package com.github.celeric.supplymanager.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.celeric.supplymanager.element.Order;
import com.github.celeric.supplymanager.element.OrderItem;
import com.github.celeric.supplymanager.element.Product;
import com.github.celeric.supplymanager.element.ProductCategory;

/**
 * Class containing the SQL commands.
 * @author Bence
 *
 */
public class Sql {
	/**
	 * Logger for logging events.
	 */
	static Logger logger = LoggerFactory.getLogger(Sql.class);
	/**
	 * Standard logger message.
	 */
	private static final String sqlExceptionMsg = "An SQL exception has occurred";

	/**
	 * Gets the ProductCategories.
	 * @return the ProductCategories.
	 */
	public static List<ProductCategory> getCategories() {
		logger.info("Current method: getCategories()");
		List<ProductCategory> ll = new ArrayList<ProductCategory>();

		try {
			Connection conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("select * from H_DE7FBF.prt_category");
			while (rset.next()) {

				ll.add(new ProductCategory((rset.getInt("category_id")), rset
						.getString("category_name"), rset
						.getString("category_description")));
			}

		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}

		return ll;
	}
	/**
	 * Gets Products by categories.
	 * @param id the categoryId.
	 * @return a list of products.
	 */
	public static List<Product> getProductByCategories(int id) {
		logger.info("Current method: getProductByCategories(int id)");
		List<Product> ll = new ArrayList<Product>();

		try {
			Connection conn = ConnectionHelper.getConnection();
			logger.trace("getCategories SQL query");
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("select * from H_DE7FBF.prt_product where category_id = "
							+ id);
			while (rset.next()) {
				ll.add(new Product(rset.getInt("product_id"), rset
						.getInt("category_id"), rset.getString("product_name"),
						rset.getString("product_description"), rset
								.getDouble("list_price"), rset
								.getInt("quantity")));

			}

		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}

		return ll;
	}
	/**
	 * Gets all products.
	 * @return a list of products.
	 */
	public static List<Product> getAllProducts() {
		logger.info("Current method: getAllProducts()");
		List<Product> ll = new ArrayList<Product>();

		try {
			Connection conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("select * from H_DE7FBF.prt_product");
			while (rset.next()) {
				ll.add(new Product(rset.getInt("product_id"), rset
						.getInt("category_id"), rset.getString("product_name"),
						rset.getString("product_description"), rset
								.getDouble("list_price"), rset
								.getInt("quantity")));

			}

		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}

		return ll;
	}
	/**
	 * Gets all OrderItems.
	 * @return a list of OrderItems.
	 */
	public static List<OrderItem> getAllOrderItems() {
		logger.info("Current method: getAllOrderItems()");
		List<OrderItem> ll = new ArrayList<OrderItem>();

		try {
			Connection conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("select * from H_DE7FBF.prt_order_item");

			while (rset.next()) {

				ll.add(new OrderItem(rset.getInt("product_id"), rset
						.getString("product_name"), rset
						.getString("product_serial"), rset.getString("status"),
						rset.getInt("order_id")));

			}

		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}

		return ll;
	}
	/**
	 * Gets OrderItems by productId.
	 * @param id the productId.
	 * @return a list of OrderItem.s
	 */
	public static List<OrderItem> getOrderItemById(int id) {
		logger.info("Current method: getOrderItemById()");
		List<OrderItem> ll = new ArrayList<OrderItem>();

		try {
			Connection conn = ConnectionHelper.getConnection();
			logger.trace("getCategories SQL query");
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("select * from H_DE7FBF.prt_order_item where product_id = "
							+ id);
			while (rset.next()) {
				ll.add(new OrderItem(rset.getInt("product_id"), rset
						.getString("product_name"), rset
						.getString("product_serial"), rset.getString("status"),
						rset.getInt("order_id")));

			}

		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}

		return ll;
	}
	/**
	 * Adds a category.
	 * @param name the name of the category.
	 * @param description the description of the category.
	 */
	public static void addCategory(String name, String description) {
		logger.info("Current method: addCategoriey(String name, String description)");
		try {
			int id = 0;
			Connection conn = ConnectionHelper.getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("select max(category_id) maid from H_DE7FBF.prt_category");
			while (rs.next()) {
				id = rs.getInt("maid")+1;
			}
			logger.error("beszúrtam");
			stmt.execute(String
							.format("insert into H_DE7FBF.prt_category (category_id, category_name, category_description) values(%d, '%s', '%s')",
									id, name, description));

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Adds a product.
	 * @param name name of the product.
	 * @param description description of the product.
	 * @param price price of the product.
	 * @param categoryId the categoryId.
	 */
	public static void addProduct(String name, String description, int price, Integer categoryId){
		logger.info("Current method: addProduct()");
		try {
			int id = 0;
			Connection conn = ConnectionHelper.getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("select max(product_id) maid from H_DE7FBF.prt_product");
			while (rs.next()) {
				id = rs.getInt("maid")+1;
			}
			logger.error("beszúrtam");
			stmt.execute(String
							.format("insert into H_DE7FBF.prt_product (category_id, product_id, product_name, product_description, list_price, quantity) values(%d, %d, '%s', '%s', %d, %d)",
									categoryId, id, name, description, price, 0));

		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}
	}
	/**
	 * Adds an orderItem.
	 * @param id the id of the product.
	 * @param name the name of the product.
	 * @param serial the serial of the product.
	 * @param status the order status of the product.
	 */
	public static void addOrderItem(int id, String name, String serial, String status){
		logger.info("Current method: addOrderItem()");
		try {
			
			Connection conn = ConnectionHelper.getConnection();
			
			Statement stmt = conn.createStatement();
		
			logger.error("beszúrtam");
			stmt.execute(String
							.format("insert into H_DE7FBF.prt_order_item (product_id, product_name, product_serial, status, order_id) values(%d, '%s', '%s', '%s', %d)",
									id, name, serial, status, 0));
			stmt.execute("update H_DE7FBF.prt_product set quantity=quantity+1 where product_name = '"+name+"'");
		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}
	}
	/**
	 * Gets a ProductCategory.
	 * @param id the categoryId.
	 * @return a category.
	 */
	public static ProductCategory getCagetory(int id) {
		logger.info("Current method: getCategory()");
		try {
			Connection conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("select * from H_DE7FBF.prt_category where category_id = "
							+ id);
			while (rset.next()) {
				return new ProductCategory(id, rset.getString("category_name"),
						rset.getString("category_description"));

			}

		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Gets a product by id.
	 * @param id the product id.
	 * @return a product.
	 */
	public static Product getProduct(int id) {
		logger.info("Current method: getProduct()");
		try {
			Connection conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("select * from H_DE7FBF.prt_product where product_id = "
							+ id);
			while (rset.next()) {
				return new Product(rset.getInt("product_id"),
						rset.getInt("category_id"),
						rset.getString("product_name"),
						rset.getString("product_description"),
						rset.getDouble("list_price"), rset.getInt("quantity"));

			}

		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * Gets an order item by serial.
	 * @param serial the serial of the product.
	 * @return an OrderItem.
	 */
	public static OrderItem getOrderItem(String serial) {
		logger.info("Current method: getOrderItem()");
		try {
			Connection conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("select * from H_DE7FBF.prt_order_item where product_serial = '"
							+ serial + "'");
			while (rset.next()) {
				return new OrderItem(rset.getInt("product_id"),
						rset.getString("product_name"),
						rset.getString("product_serial"),
						rset.getString("status"), rset.getInt("order_id"));

			}

		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Deletes a category.
	 * @param id the categoryId.
	 */
	public static void deleteCategory(int id){
		logger.info("Current method: deleteCategory()");
		try {
			Connection conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from H_DE7FBF.prt_product where category_id = "+id);
			logger.error("eddig eljutottam");
				int id2 = 0;
				while(rs.next()){
					id2 = rs.getInt("product_id");
					
				}
				deleteProduct(id2);
			
			stmt
					.executeQuery("delete from H_DE7FBF.prt_category where category_id = "
							+ id );

		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}
	}
	/**
	 * Delets a product.
	 * @param id the productId.
	 */
	public static void deleteProduct(int id){
		logger.info("Current method: deleteProduct()");
		try {
			Connection conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
	
			stmt.executeQuery("delete from H_DE7FBF.prt_order where product_serial in "
					+ "(select product_serial from H_DE7FBF.prt_order_item where product_id = "+ id+")");
							
			logger.error("deleted");
			stmt
					.executeQuery("delete from H_DE7FBF.prt_product where product_id = "
							+ id );logger.error("deleted");
	stmt
	.executeQuery("delete from H_DE7FBF.prt_order_item where product_id = "
			+ id );
	logger.error("deleted");
	
		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}
		
	}
	/**
	 * Deletes an orderItem.
	 * @param serial the serial of the product.
	 */
	public static void deleteOrderItem(String serial){
		logger.info("Current method: deleteOrderItem()");
		try {
			Connection conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("update H_DE7FBF.prt_product set quantity = quantity-1 where product_id = (select product_id from H_DE7FBF.prt_order_item where product_serial = '"
					+ serial+"')" );
			stmt
					.executeQuery("delete from H_DE7FBF.prt_order_item where product_serial = '"
							+ serial+"'" );
			
		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}

}	
	/**
	 * Creates an Order.
	 * @param oi the orderItem.
	 * @param firstName the first name of the customer.
	 * @param lastName the last name of the customer.
	 * @param email the e-mail address of the customer.
	 * @param purchase the mode of the purchase.
	 * @param reception the mode of the reception
	 */
	public static void createOrder(OrderItem oi, String firstName, String lastName, String email, String purchase, String reception){
		logger.info("Current method: createOrder()");
		try {
			int id = 0;
			
			Connection conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("select max(order_id) maid from H_DE7FBF.prt_order");
			while (rs.next()) {
				id = rs.getInt("maid")+1;
			}
			stmt
					.executeQuery(String.format("insert into H_DE7FBF.prt_order (order_id, product_serial, first_name, last_name, email, purchase, reception, due)"
							+ "values(%d, '%s', '%s', '%s', '%s', '%s', '%s', to_date( '%s', 'YYYY-MM-DD') )", id, oi.getProductSerial(),
							firstName, lastName, email, purchase, reception, new java.sql.Date(new java.util.Date().getTime()+259200000)));
			stmt.executeUpdate("update H_DE7FBF.prt_order_item set order_id = "+id+", status='Eladva' where product_serial ='"+oi.getProductSerial()+"'");
		
		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}
	}

	/**
	 * Gets an Order by id.
	 * @param id the orderId.
	 * @return an Order.
	 */
	public static Order getOrderById(int id){
		logger.info("Current method: getOrderById()");
		try {
			Connection conn;
			conn = ConnectionHelper.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("select * from H_DE7FBF.prt_order where order_id = "
							+ id);
			while (rset.next()) {
				
				return new Order(rset.getInt("order_id"),
						
						rset.getString("first_name"),
						rset.getString("last_name"),
						rset.getString("email"),
						rset.getString("purchase"),
						rset.getString("reception"),
						rset.getDate("due"));
			}
		} catch (IOException | SQLException e) {
			logger.error(sqlExceptionMsg);
			e.printStackTrace();
		}
		return null;
	}
}
