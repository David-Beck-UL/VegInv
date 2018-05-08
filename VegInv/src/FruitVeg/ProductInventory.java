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
import java.util.Scanner;

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
