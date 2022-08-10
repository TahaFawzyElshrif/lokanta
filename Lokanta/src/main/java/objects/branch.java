
package objects;

import java.util.ArrayList;

public class branch implements java.io.Serializable, Cloneable {
   public String name="Branch";
   public long code=1000000000;
   public ArrayList<meal> meals=new ArrayList();
   public linkedList<offer> offers=new linkedList();//this changed to a linked list because i have add previous,next methods that
   //truly gooad at showOffers
   public String branchManagerName="manager";
   public ArrayList<order> orders=new ArrayList<>();
    public branch() {
    }
    
    public branch(String name,long code) {
        this.name=name;
        this.code=code;
    }

}
