
package objects;

import java.util.Date;

public class order implements java.io.Serializable,java.lang.Cloneable{
public String name;
public java.util.Date date;
public double price;

    public order(String name, Date date, double price) {
        this.name = name;
        this.date = date;
        this.price = price;
    }

}
