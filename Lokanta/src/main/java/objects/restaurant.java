package objects;

public class restaurant implements java.io.Serializable, Cloneable {

    public String name = new String("Restaurant");
    public long code = 1000000000;
    linkedList<branch> branches=new linkedList<>();
    public String managerName="manager";
    public restaurant() {
    }

    public restaurant(String name, long code) {
        this.name = name;
        this.code = code;
    }
     public restaurant(String name, long code,linkedList<branch> branches) {
        this.name = name;
        this.code = code;
        this.branches=branches;
    }
    public void addBranch(branch b){
        branches.add(b);
    }
    public linkedList<branch> getBranches(){
        return branches;
    }
    public static void main (String [] args){
        
    }
}
