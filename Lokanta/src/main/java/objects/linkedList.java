
package objects;
public class linkedList<T> implements java.io.Serializable, Cloneable  {
    private node <T>head,tail;
    public void add(T b){
        node <T> n=new node<>(b);
        if( head==null){
            head=new node();
            tail=new node();
        head.next=tail.previous=n;
        }else{
         tail.previous.next=n;
         n.previous=tail.previous;
         tail.previous=n;
         
        }
    }
    
    
  
    /*  public static void main(String[] args){
    linkedList<String> l=new linkedList<>();
    l.add("a");
    l.add("b");
    l.add("c");
    node m=l.head.next;
    while(m!=null){
    System.out.print(m.data);
    m=m.next;
    }
    System.out.println();
    node mo=l.tail.previous;
    while(mo!=null){
    System.out.print(mo.data);
    mo=mo.previous;
    }
    }*/
    public node<T> getHead() {
        return head;
    }

    public void setHead(node<T> head) {
        this.head = head;
    }

    public node<T> getTail() {
        return tail;
    }

    public void setTail(node<T> tail) {
        this.tail = tail;
    }
}
