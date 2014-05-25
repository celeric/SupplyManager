package com.github.celeric.supplymanager.ui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.celeric.supplymanager.element.OrderItem;
import com.github.celeric.supplymanager.element.Product;
import com.github.celeric.supplymanager.element.ProductCategory;
import com.github.celeric.supplymanager.util.Sql;
/**
 * Class representing a custom {@code JPanel}.
 * @author Bence
 *
 */
public class MyPanel extends JPanel {
	/**
	 * Logger for logging events.
	 */
	static Logger logger = LoggerFactory.getLogger(MyPanel.class);
	/**
	 * Id for serialization.
	 */
	private static final long serialVersionUID = 7793488749660277501L;

	/**
	 * Category panel.
	 */
	public static DefaultPanel categories;
	/**
	 * Product panel.
	 */
	public static DefaultPanel products;
	/**
	 * OrderItem panel.
	 */
	public static DefaultPanel orderItems;
	/**
	 * Actual focus of the lists.
	 */
	public static int focus; 
	/**
	 * Actual focus id.
	 */
	public static int focusId;
	/**
	 * Actual focus serial.
	 */
	public static String focusSerial;
	/**
	 * Actual Product.
	 */
	public static Product product;
	/**
	 * Actual ProductCategory.
	 */
	public static ProductCategory productCategory;
	/**
	 * Actual OrderItem.
	 */
	public static OrderItem orderItem;
	/**
	 * FocusListener for the category panel.
	 */
	final FocusListener catf = new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent arg0) {
		
			
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			focus = 0;
			
		}
	};
	/**
	 * FocusListener for the orderitem panel.
	 */
	final FocusListener orderf = new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent arg0) {
	
			
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			focus = 2;
			
		}
	};
	/**
	 * FocusListener for the product panel.
	 */
	final FocusListener prodf = new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent arg0) {
			
			
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			focus = 1;
			
		}
	};
	/**
	 * ListSelectionListener for orderItem panel.
	 */
	final ListSelectionListener oilistener = new ListSelectionListener() {
	
	
		@Override
		public void valueChanged(ListSelectionEvent e) {
			focus = 2;

			if (e.getValueIsAdjusting()) {

				for (OrderItem oi : Sql.getAllOrderItems()) {
					if ((oi.getProductSerial() + " - " + oi
							.getProductName()).equals(orderItems.list
							.getSelectedValue().toString())) {
						orderItem = oi;
						focusSerial = oi.getProductSerial();
					}
				}
				categories.list.addFocusListener(catf);
				products.list.addFocusListener(prodf);
				orderItems.list.addFocusListener(orderf);

			}

		}
	};
	/**
	 * ListSelectionListener for product panel.
	 */
	final ListSelectionListener productListener = new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent e) {

			focus = 1;
			if (e.getValueIsAdjusting()) {
				String name = (String) products.list.getSelectedValue()
						.toString();

				for (Product pp : Sql.getAllProducts()) {
					if (pp.getProductName().equals(name)) {
						product = pp;
						orderItems.removeAll();
						orderItems.list = new JList<Object>(
								getOrderItemById(pp.getProductId()));
						focusId = pp.getProductId();
						orderItems.list
								.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						orderItems.sp = new JScrollPane(orderItems.list);
						orderItems.add(orderItems.sp);

						orderItems.revalidate();
						orderItems.list
								.addListSelectionListener(oilistener);
					}
				}
				categories.list.addFocusListener(catf);
				products.list.addFocusListener(prodf);
				orderItems.list.addFocusListener(orderf);
			}

		}
	};
	/**
	 * ListSelectionListener for categories panel.
	 */
	final ListSelectionListener categListener = new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			focus = 0;

			if (e.getValueIsAdjusting()) {
				String name = (String) categories.list.getSelectedValue()
						.toString();
				
				
				for (ProductCategory pc : Sql.getCategories()) {
					if (pc.getCategoryName().equals(name)) {
						productCategory = pc;
						
						products.removeAll();
						products.list = new JList<Object>(
								getProdutctsById(pc.getCategoryId()));
						focusId = pc.getCategoryId();
						products.list
								.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

						products.sp = new JScrollPane(products.list);
						products.add(products.sp);
						products.list
								.addListSelectionListener(productListener);

						products.revalidate();

						orderItems.removeAll();
						orderItems.list = new JList<Object>(
								getOrderItemById(0));

						orderItems.list
								.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						orderItems.sp = new JScrollPane(orderItems.list);
						orderItems.add(orderItems.sp);

						orderItems.revalidate();
						categories.list.addFocusListener(catf);
						products.list.addFocusListener(prodf);
						orderItems.list.addFocusListener(orderf);
					}
				}
			}

		}
	};
	
	/**
	 * Constructor for {@code MyPanel} object.
	 */
	public MyPanel() {
		logger.info("Current method: MyPanel()");
		
		categories = new DefaultPanel(getCategories());
		if(!Sql.getCategories().isEmpty()){
		productCategory = Sql.getCategories().get(0);
		products = new DefaultPanel(getProdutctsById(productCategory.getCategoryId()));
		}else{
		products = new DefaultPanel(getProdutctsById(0));}
		orderItems = new DefaultPanel(getOrderItemById(0));
		setLayout(new MigLayout("", "[fill][fill][grow][fill]",
				"[fill][grow][grow][grow][fill]"));
		setBackground(Color.black);
		categories.list.addFocusListener(catf);
		products.list.addFocusListener(prodf);
		
		add(new ButtonPanel(), "cell 1 1 1 2,grow");

	
		categories.list.setSelectedIndex(0);
		focusId = Sql.getCategories().get(0).getCategoryId();
		categories.list.addListSelectionListener(categListener);
		products.list.addListSelectionListener(productListener);
		orderItems.list.addListSelectionListener(oilistener);

		add(categories, "cell 2 1,grow");
		add(products, "cell 2 2,grow");
		add(orderItems, "cell 2 3, grow");

	}
	/**
	 * Getting all categories.
	 * @return the list of categories.
	 */
	private static Object[] getCategories() {
		List<String> list2 = new ArrayList<>();
		for (ProductCategory pc : Sql.getCategories()) {
			list2.add(pc.getCategoryName());
		}
		return list2.toArray();
	}
	/**
	 * Gets a product by category id.
	 * @param id the category id of the product.
	 * @return a product.
	 */
	private static Object[] getProdutctsById(int id) {
		List<String> list2 = new ArrayList<>();
		for (Product pp : Sql.getProductByCategories(id)) {
			list2.add(pp.getProductName());
		}
		return list2.toArray();
	}
	/**
	 * Gets an orderItem by productId.
	 * @param id the productId.
	 * @return an orderItem.
	 */
	private static Object[] getOrderItemById(int id) {
		List<String> list2 = new ArrayList<>();
		for (OrderItem oi : Sql.getOrderItemById(id)) {
			list2.add(oi.getProductSerial() + " - " + oi.getProductName());
		}
		return list2.toArray();
	}
}
