/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;
public class node<T> implements java.io.Serializable, Cloneable {
 node previous;
  T data;
node next;
node(){}
node(T data){this.data=data;}

    public node getPrevious() {
        return previous;
    }

    public void setPrevious(node previous) {
        this.previous = previous;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public node getNext() {
        return next;
    }

    public void setNext(node next) {
        this.next = next;
    }

}