/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veginv;

import FruitVeg.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import InventoryExceptions.*;

/**
 *
 * @author David Beck, Mark Quigley, Sergejs Sushinskis
 */
public class FruitVegGUI extends JFrame {

    private JButton buyPr, sellPr, checkStockD, checkStock, remExpired;
    private JTextArea output;
    private ProductInventory inventory = new FruitVeg.ProductInventory();

    //constructor to build main menu
    public FruitVegGUI() {
        //Get screensize to fit windows on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        JPanel JP = new JPanel();
        JP.setLayout(new GridLayout(5, 1));
        //create listener that is in private class to then add to buttons

        clickBtnListener click = new clickBtnListener();

        //add action buttons         
        buyPr = new JButton("Buy Stock");
        JP.add(buyPr);
        buyPr.addActionListener(click);

        sellPr = new JButton("Sell Vegetables");
        JP.add(sellPr);
        sellPr.addActionListener(click);

        checkStockD = new JButton("Check Stock on Date");
        JP.add(checkStockD);
        checkStockD.addActionListener(click);

        checkStock = new JButton("Check Overall Stock");
        JP.add(checkStock);
        checkStock.addActionListener(click);

        remExpired = new JButton("Remove Expired Stock");
        JP.add(remExpired);
        remExpired.addActionListener(click);

        //Create additional panel for log text output
        JPanel log = new JPanel();
        log.setLayout(new BorderLayout());

        output = new JTextArea(20, 50);
        log.add(new JScrollPane(output));

        container.add(JP, BorderLayout.NORTH);
        container.add(log, BorderLayout.SOUTH);

        add(container);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //get scren position and position screen
        setSize(width / 2, height / 2);
        // center the jframe on screen
        setLocationRelativeTo(null);
        setTitle("VegInvApp");
        setVisible(true);

    }

    ;//end of main menu build
    
    private class clickBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            //  identify which button has fired the event.
            JButton source = (JButton) evt.getSource();

            if (source == buyPr) {
                addProducts();
            } else if (source == sellPr) {
                output.append("\n Sell product button was pressed\n");
                ArrayList<FruitVegObj> productsSold;
                try{
                    productsSold = inventory.sellProduct("Apples", 10);
                    for (FruitVegObj product : productsSold){
                        output.append(product.toString() + "\n");
                    }
                }
                catch (InventoryException e){
                    output.append(e.print());
                }
            } else if (source == checkStockD) {
                output.append("\n Check product to date button was pressed");
            } else if (source == checkStock) {
                output.append("\n Check total product button was pressed");
                sortProducts();
            } else if (source == remExpired) {
                output.append("\n Remove expired button was pressed");
            }

        }
    ;

    }//end of actionListener private class

/**
 * method used to prompt user for details to get parameters needed to add
 * In the event "Buy products" button is pressed on the main window this 
 * pop up will appear to prompt user for details
 * once details are provided class will create new object with parameters provided
 * output is appended to text area
 */
private void addProducts() {
//variables that are used to add product 
        String name;
        double quantity;
        String batchNr;
        double cost;

        /**
         * Start building fields for JOPtion pane dialogue box
         */
        JComboBox<String> productName = new JComboBox(FruitVegObj.PRODUCTS);
        JTextField productQty = new JTextField(6);
        JTextField batchNumber = new JTextField(6);
        JTextField unitCost = new JTextField(6);
        JPanel pp = new JPanel();
        pp.add(new JLabel("Product Name"));
        pp.add(productName);
        pp.add(new JLabel("Product Quantity"));
        pp.add(productQty);
        pp.add(new JLabel("Batch Number"));
        pp.add(batchNumber);
        pp.add(new JLabel("Unit Cost"));
        pp.add(unitCost);

//JOption pane dialogue
        int buy = JOptionPane.showConfirmDialog(null, pp,
                "Please enter details to buy", JOptionPane.OK_CANCEL_OPTION);

//get variables from JOPtion pane
        name = (String) productName.getSelectedItem();
        quantity = Double.parseDouble(productQty.getText());
        batchNr = batchNumber.getText();
        cost = Double.parseDouble(unitCost.getText());

//if user pressed ok then create new product.
        if (buy == JOptionPane.OK_OPTION) {

//FruitVegObj item1 = new FruitVegObj(name,quantity,batchNr,cost);
//output.append("\n Item 1 object was created and with the following details: "+
//        "\nItem Name: "+item1.getName()+
//        "\nItem Quantity:"+item1.getQuantity()+
//        "\nItem batch: "+ item1.getBatchNumber());
            inventory.addProducts(name, quantity, batchNr, cost);
            output.append("\n" + inventory.listProducts());
        }
    }

    ;

/**
 * Method to be used to prompt user on how to sort list and call sort method
 */
private void sortProducts() {

        /**
         * Create fields for JOPtion pane dialogue.
         */
        JRadioButton option1 = new JRadioButton("Sort by Product Name");
        option1.setActionCommand("Product Name");
        option1.setSelected(true);
        JRadioButton option2 = new JRadioButton("Sort by Product Quantity");
        option2.setActionCommand("Product Quantity");
        JRadioButton option3 = new JRadioButton("Sort by Product Expiry Date");
        option3.setActionCommand("Product Expiry Date");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);

        JPanel sort = new JPanel();
        sort.add(option1);
        sort.add(option2);
        sort.add(option3);

// display popup message
        int sortOptionDialogue = JOptionPane.showConfirmDialog(null, sort,
                "Choose sort option", JOptionPane.OK_CANCEL_OPTION);
//update output and perform action on the list
        if (sortOptionDialogue == JOptionPane.OK_OPTION) {
            output.append("\n Check total product button was pressed"
                    + " and preferred sort method was: " + buttonGroup.getSelection().getActionCommand() + "\n");
            output.append(inventory.checkOverallStock(buttonGroup.getSelection().getActionCommand()));
        }
    }
;//end of get sorted stock
};//end of VegINVGUI class

//Create list of available products.

