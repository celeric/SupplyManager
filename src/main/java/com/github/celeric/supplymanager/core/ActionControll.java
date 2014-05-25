package com.github.celeric.supplymanager.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.celeric.supplymanager.element.OrderItem;
import com.github.celeric.supplymanager.element.Product;
import com.github.celeric.supplymanager.element.ProductCategory;
import com.github.celeric.supplymanager.ui.MyPanel;
import com.github.celeric.supplymanager.util.Sql;
/**
 * Class handling the main events of the GUI.
 * 
 * @author Bence
 */
public class ActionControll {
	/**
	 * Logger for logging events.
	 */
	public static Logger logger = LoggerFactory.getLogger(ActionControll.class);
	/**
	 * Refreshes the application's main window on displayed data changes.
	 */
	public static void refreshWindow() {
	logger.info("Current method: refreshWindow()");
	Main.myFrame.dispose();
	Main.startWindow();
	}

	/**
	 * ActionListener for new button.
	 */
	public ActionListener newItemOption = new ActionListener() {
		/**
		 * Custom actionPerformed event for the button.
		 * @param e user action event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("NewButton click event started");
			switch (MyPanel.focus) {
			case 0:
				String labels[] = { "Kategória neve: ", "Kategória leírása: " };
				JPanel panel = FormBuilder.Builder(labels);
				int result = JOptionPane.showConfirmDialog(null, panel, "",
						JOptionPane.OK_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					JTextField name = (JTextField) panel.getComponent(1);
					JTextField description = (JTextField) panel.getComponent(3);
					Sql.addCategory(name.getText(), description.getText());

					refreshWindow();
				}
				break;
			case 1:
				String labels2[] = { "Termék neve: ", "Termék leírása: ",
						"Listaár: " };
				JPanel panel2 = FormBuilder.Builder(labels2);
				int result2 = JOptionPane.showConfirmDialog(null, panel2, "",
						JOptionPane.OK_OPTION);
				if (result2 == JOptionPane.OK_OPTION) {
					JTextField name = (JTextField) panel2.getComponent(1);
					JTextField description = (JTextField) panel2
							.getComponent(3);
					JTextField price = (JTextField) panel2.getComponent(5);
				
					Sql.addProduct(name.getText(), description.getText(),
							Integer.parseInt(price.getText()),
							MyPanel.productCategory.getCategoryId());

					refreshWindow();
				}
				break;
			case 2:
				String labels3[] = { "Serial-no: ", "Státusz: "
						};
				JPanel panel3 = FormBuilder.Builder(labels3);
				int result3 = JOptionPane.showConfirmDialog(null, panel3, "",
						JOptionPane.OK_OPTION);
				if (result3 == JOptionPane.OK_OPTION) {
					JTextField serial = (JTextField) panel3.getComponent(1);
					JTextField status = (JTextField) panel3
							.getComponent(3);
					
					Sql.addOrderItem(MyPanel.product.getProductId(), MyPanel.product.getProductName(), serial.getText(), status.getText());

					refreshWindow();
				}
				break;
		 

			default:
				break;
			}

		}
	};

	/**
	 * ActionListener for remove button.
	 */
	public ActionListener removeItemOption = new ActionListener() {
		/**
		 * Custom actionPerformed event for the button.
		 * @param e user action event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("RemoveButton click event started");
			switch (MyPanel.focus) {
			case 0:
			    Sql.deleteCategory(MyPanel.productCategory.getCategoryId());
			    refreshWindow();
				break;
			case 1:
			    Sql.deleteProduct(MyPanel.product.getProductId());
			    refreshWindow();
				break;
			case 2:
			    Sql.deleteOrderItem(MyPanel.orderItem.getProductSerial());
			    refreshWindow();
				break;
			default:
				break;
			}

		}
	};
	/**
	 * ActionListener for details button.
	 */
	public ActionListener detailsButtonOption = new ActionListener() {
		/**
		 * Custom actionPerformed event for the button.
		 * @param e user action event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("DetailsButton click event started");
			JPanel myPanel = new JPanel();
			JLabel label = new JLabel();
			switch (MyPanel.focus) {
			case 0:
				
				ProductCategory pc = Sql.getCagetory(MyPanel.focusId);
				if(pc!=null){
				label.setText(pc.toLabelString());

				myPanel.add(label);
				JOptionPane.showConfirmDialog(null, myPanel,
						"Kategória: " + pc.getCategoryName(),
						JOptionPane.CLOSED_OPTION);}
				break;
			case 1:
				Product pp = Sql.getProduct(MyPanel.focusId);
				if(pp!=null){
				label.setText(pp.toLabelString());
				myPanel = new JPanel();
				myPanel.add(label);
				JOptionPane.showConfirmDialog(null, myPanel,
						"Kategória: " + pp.getProductName(),
						JOptionPane.CLOSED_OPTION);}
				break;
			case 2:
				
				OrderItem oi = Sql.getOrderItem(MyPanel.focusSerial);
				if(oi!=null){
				label.setText(oi.toLabelString());
				myPanel = new JPanel();
				myPanel.add(label);
				JOptionPane.showConfirmDialog(null, myPanel,
						"Kategória: " + oi.getProductName(),
						JOptionPane.CLOSED_OPTION);}
				break;
			default:
				break;
			}

		}
	};
	/**
	 * ActionListener for sell button.
	 */
	public ActionListener sellItemOption = new ActionListener() {
		/**
		 * Custom actionPerformed event for the button.
		 * @param e user action event.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
		logger.info("SellButton click event started");
		switch (MyPanel.focus) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				OrderItem oi = Sql.getOrderItem(MyPanel.focusSerial);
				String labels[] = { "Megrendelő vezetékneve: ", "Megrendelő keresztneve: ", "Megrendelő email-címe: ", "Fizetés módja: ", "Átvétel módja: " };
				JPanel panel = FormBuilder.Builder(labels);
				int result = JOptionPane.showConfirmDialog(null, panel, "",
						JOptionPane.OK_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					JTextField firstName = (JTextField) panel.getComponent(1);
					JTextField lastName = (JTextField) panel.getComponent(3);
					JTextField email = (JTextField) panel.getComponent(5);
					JTextField purchase = (JTextField) panel.getComponent(7);
					JTextField reception = (JTextField) panel.getComponent(9);
					Sql.createOrder(oi, firstName.getText(), lastName.getText(), email.getText(), purchase.getText(), reception.getText());

					refreshWindow();
				}
				
				break;
			default:
				break;
			}

		}
	};
}
