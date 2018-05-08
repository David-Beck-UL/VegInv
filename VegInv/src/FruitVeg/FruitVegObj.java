package FruitVeg;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

/**
 *
 * @author David Beck, Mark Quigley, Sergejs Sushinskis 
 */
public class FruitVegObj {
    /**
     * Array storing the list of products we sell.
     * {"Apples", "Oranges", "Strawberries", "Bananas", "Kiwi", "Potatoes", "Carrots", "Turnip", "Broccoli", "Lettuce"}
     */
    public static final String[] PRODUCTS = {"Apples", "Oranges", "Strawberries", "Bananas", "Kiwi", "Potatoes", "Carrots", "Turnip", "Broccoli", "Lettuce"};
    /**
     * Array storing the unit of each of the products.
     */
    public static final String[] UNITS = {"each", "each", "kg", "kg", "each", "kg", "kg", "kg", "kg", "each"};
    /**
     * Array storing the prices of each product.
     */
    public static final Double[] PRICES = {0.50, 0.60, 5.00, 1.20, 0.20, 1.00, 2.00, 0.75, 3.50, 1.50};
    /**
     * Array storing the disposal cost of each product.
     */
    public static final Double[] DISPOSAL = {0.02, 0.02, 0.25, 0.03, 0.01, 0.25, 0.25, 0.10, 0.10, 0.05};
    /**
     * Array storing the shelf life of each product.
     */
    public static final Integer[] SHELFLIFE = {20, 20, 2, 2, 5, 10, 10, 20, 10, 2};
    
    private String name;
    private Calendar expiryDate;
    private double quantity;
    private String unit;
    private String batchNumber;
    private double unitCost;
    private double unitPrice;
    private double unitDisposalCost;
    
    
    /**
     * This constructor will create a new product to be added to the inventory.
     * 
     * @param name Name of the product to be created
     * @param quantity Quantity of product in the batch
     * @param batchNumber Batch Number for this order
     * @param unitCost Cost per unit the product was bought for
     */
    public FruitVegObj(String name, double quantity, String batchNumber, double unitCost) {
        Calendar today = Calendar.getInstance();
        int productsIndex = findProduct(name);
        
        today.add(Calendar.DAY_OF_MONTH, SHELFLIFE[productsIndex]);
        this.name = name;
        this.expiryDate = today;
        this.quantity = quantity;
        this.unit = UNITS[productsIndex];
        this.batchNumber = batchNumber;
        this.unitCost = unitCost;
        this.unitPrice = PRICES[productsIndex];
        this.unitDisposalCost = DISPOSAL[productsIndex];
    }

    /**
     * This constructor will create a new product to be added to the inventory.  It will
     * be used for creating test data.
     * 
     * @param name Name of the product to be created
     * @param productDate The purchase date of the product
     * @param quantity Quantity of product in the batch
     * @param batchNumber Batch Number for this order
     * @param unitCost Cost per unit the product was bought for
     */
    public FruitVegObj(String name, Calendar productDate, double quantity, String batchNumber, double unitCost) {
        int productsIndex = findProduct(name);
        
        productDate.add(Calendar.DAY_OF_MONTH, SHELFLIFE[productsIndex]);
        this.name = name;
        this.expiryDate = productDate;
        this.quantity = quantity;
        this.unit = UNITS[productsIndex];
        this.batchNumber = batchNumber;
        this.unitCost = unitCost;
        this.unitPrice = PRICES[productsIndex];
        this.unitDisposalCost = DISPOSAL[productsIndex];
    }
    
    /**
     * Accessor method for name of the product
     * @return Product Name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method for the Expiry Date of the product
     * @return Expiry Date
     */
    public Calendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Accessor method for the Quantity of the product
     * @return The quantity of product in this batch
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Accessor method for the unit of the product
     * @return The product unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Accessor method for the Batch Number of the product
     * @return Batch Number
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * Accessor method for the unit cost of the product
     * @return Cost per unit of the product
     */
    public double getUnitCost() {
        return unitCost;
    }

    /**
     * Accessor method for the Total Cost of this batch of product
     * @return Total Cost of the batch
     */
    public double getTotalCost() {
        return (unitCost * quantity);
    }

    /**
     * Accessor method for the Unit Price of the product
     * @return Price per unit
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Accessor method for the Total Disposal Cost of the batch
     * @return Total Disposal Cost
     */
    public double getTotalDisposalCost() {
        return (unitDisposalCost * quantity);
    }
    
    private int findProduct(String productName){
        int result = -1;
        for (int i = 0; i < PRODUCTS.length; i ++){
            if (PRODUCTS[i] == productName){
                result = i;
                break;
            }
        }
        return result;
    }
    
    /**
     * Sorting Comparator - Sort by product name
     */
    class ProductNameComparator implements Comparator<FruitVegObj> {
        @Override
        /**
         * Overrides comparator compare method to compare the Name attribute of the two products
         * @param product1 The first product to be compared
         * @param product2 The second product to be compared
         */
        public int compare(FruitVegObj product1, FruitVegObj product2){
            return product1.getName().compareToIgnoreCase(product2.getName());
        }
    }
    /**
     * Sorting Comparator - Sort by expiry date
     */
    class ExpiryDateComparator implements Comparator<FruitVegObj> {
        @Override
        /**
         * Overrides comparator compare method to compare the Expiry Date attribute of the two products
         * @param product1 The first product to be compared
         * @param product2 The second product to be compared
         */
        public int compare(FruitVegObj product1, FruitVegObj product2){
            return product1.getExpiryDate().compareTo(product2.getExpiryDate());
        }
    }
    /**
     * Sorting Comparator - Sort by expiry date
     */
    class ProductExpiryComparator implements Comparator<FruitVegObj> {
        @Override
        /**
         * Overrides comparator compare method to compare the Product Name and 
         * Expiry Date attributes of the two products.
         * This comparator will allow for sorting multiple entries for the same product by expiry date
         * @param product1 The first product to be compared
         * @param product2 The second product to be compared
         */
        public int compare(FruitVegObj product1, FruitVegObj product2){
            int comparison;
            // compare the product names first
            comparison = product1.getName().compareToIgnoreCase(product2.getName());
            // if the product names are the same
            if (comparison == 0){
                // compare on expiry date
                return product1.getExpiryDate().compareTo(product2.getExpiryDate());
            }
            else{
                // return the result of the product comparison
                return comparison;
            }
        }
    }
}
