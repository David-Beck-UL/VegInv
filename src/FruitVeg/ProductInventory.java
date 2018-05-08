/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitVeg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.ListIterator;
import InventoryExceptions.*;

/**
*
* @author Mark Quigley, David Beck, Sergejs Sushinskis
*/
public class ProductInventory {
    
   private ArrayList<FruitVegObj> productInv;
   /**
   * Initialize list of products in inventory.
   */
   public ProductInventory(){
       productInv = new ArrayList<>();
   }    
    
    

    //Adds item to inventory.
    public void addProducts(String name,double quantity,String batchNr,double cost) {
        FruitVegObj item = new FruitVegObj(name, quantity, batchNr, cost);
        productInv.add(item);
    }
    //Adds item to inventory.
    public void addProducts(String name, Calendar productDate, double quantity,String batchNr,double cost) {
        FruitVegObj item = new FruitVegObj(name, productDate, quantity, batchNr, cost);
        productInv.add(item);
    }
    
    public String checkOverallStock(String sortBy){
        if (sortBy == "Product Name"){
            Collections.sort(productInv, new FruitVeg.ProductNameComparator());
        }
        else if (sortBy == "Product Quantity"){
            Collections.sort(productInv, new FruitVeg.QuantityComparator());
        }
        else if (sortBy == "Product Expiry Date"){
            Collections.sort(productInv, new FruitVeg.ExpiryDateComparator());
        }
        return this.listProducts();
    }
    
    public ArrayList<FruitVegObj> sellProduct(String productName, double quantity) throws InventoryException{
        ArrayList<FruitVegObj> basketProducts = new ArrayList();
        double qtyInInventory = 0;
        FruitVegObj firstProduct = null;
        FruitVegObj thisProduct;
        // Sort by Product Name and Expiry Date
        Collections.sort(productInv, new FruitVeg.ProductExpiryComparator());
        ListIterator<FruitVegObj> inventoryIterator = productInv.listIterator();
        while (inventoryIterator.hasNext()){
            firstProduct = inventoryIterator.next();
            if (firstProduct.getName() == productName && firstProduct.getExpiryDate().after(Calendar.getInstance())){
                qtyInInventory = firstProduct.getQuantity();
                while (inventoryIterator.hasNext()){
                    thisProduct = inventoryIterator.next();
                    if (thisProduct.getName() != productName){
                        break;
                    }
                    else{
                        qtyInInventory += thisProduct.getQuantity();
                    }
                }
                break;
            }
        }
        if (qtyInInventory == 0){
            throw new InventoryExceptions.InventoryException("Zero quantity of this product in inventory");
        }
        else if (qtyInInventory < quantity){
            throw new InventoryExceptions.InventoryException("Not enough quantity of this product in inventory");
        }
        int startPosition = productInv.indexOf(firstProduct);
        ListIterator<FruitVegObj> productIterator = productInv.listIterator();
        while (productIterator.hasNext() && quantity > 0){
            thisProduct = productIterator.next();
            if (thisProduct.getQuantity() > quantity){
                //update quantity in inventory
                thisProduct.setQuantity(thisProduct.getQuantity() - quantity);
                productIterator.set(thisProduct);
                thisProduct.setQuantity(quantity);
                basketProducts.add(thisProduct);
                quantity = 0;
            }
            else{
                quantity -= thisProduct.getQuantity();
                basketProducts.add(thisProduct);
                productIterator.remove();
            }
        }
        return basketProducts;
    }
    
    public String listProducts(){
        String output = "";
        for (FruitVegObj item : productInv){
            output += item.toString() + "\n";
        }
        return output;
    }
}




    /**
     * 
     * Check inventory
     */
    // Check inventory.
 //   public void checkInventory () {
  //      for (String x : inv) {
  //          System.out.println(x);
  //      }
  //  }

    //Removes item from inventory.
 //   public void sellItem(String name) {
  //      for (int i = 0; i < inv.length; i++) {
  //          if (inv[i].equals(name)) {
   //             inv[i] = "";
   //         }
   //     }
   //     System.out.println("message tbc.....");
  //  }

  //  private static String inv[] = {"", "", "", "", "", "", "", "", "", ""};

//}
