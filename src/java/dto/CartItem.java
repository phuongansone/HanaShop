package dto;

import java.io.Serializable;

/**
 *
 * @author andtpse62827
 */
public class CartItem implements Serializable {
    private FoodDTO food;
    private int quantity;
    
    public CartItem() {
    }

    public CartItem(FoodDTO food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public FoodDTO getFood() {
        return food;
    }

    public void setFood(FoodDTO food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
