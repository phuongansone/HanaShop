package dto;

import java.io.Serializable;

/**
 *
 * @author andtpse62827
 */
public class CartItem implements Serializable {
    private FoodDTO food;
    private int quantity;
    private boolean outOfStock;
    
    public CartItem() {
    }

    public CartItem(FoodDTO food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public CartItem(FoodDTO food, int quantity, boolean outOfStock) {
        this.food = food;
        this.quantity = quantity;
        this.outOfStock = outOfStock;
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

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }
}
