
package objects;

import java.util.ArrayList;

public class meal extends service implements java.io.Serializable, Cloneable {
String[] foods={};   

    public meal(String[] foods, double totalPrice) {
        this.totalPrice = totalPrice;
        this.foods=foods;
        name=java.util.Arrays.toString(this.foods);

    }

    public String[] getFoods() {
        return foods;
    }

    public void setFoods(String[] foods) {
        this.foods = foods;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
