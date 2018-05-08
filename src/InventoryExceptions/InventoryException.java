package InventoryExceptions;

/**
 *
 * @author David Beck, Mark Quigley, Sergejs Sushinskis 
 */
public class InventoryException extends Exception{
    private String exceptionMessage;
    
    public InventoryException(String message){
        exceptionMessage = message;
    }
    public String print(){
        return exceptionMessage;
    }
}
    

