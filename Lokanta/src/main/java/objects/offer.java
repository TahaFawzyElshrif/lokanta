
package objects;

public class offer  extends service implements java.io.Serializable, Cloneable{
 public double price;
 public double discount;
 public int numberOfDays;
 public String otherInfo="";
 public String photoPath="";
    public offer(){ super.totalPrice=price*(discount/100);
}
    public offer(String name,double price, double discount, int numberOfDays, String otherInfo, String photoPath) {
        this.name=name;    
        this.price = price;
        this.discount = discount;
        this.numberOfDays = numberOfDays;
        this.otherInfo = otherInfo;
        this.photoPath = photoPath;
        super.totalPrice=price*(discount/100);

    }
 
}
