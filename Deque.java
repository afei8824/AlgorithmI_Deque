import java.util.*;

public class Deque<Item> implements Iterable<Item>{
    private Node first = null;
    private Node last = null;
    private class Node{
        Item value;
        Node next;
        Node previous;
    }
    private int N = 0;
    
    public Deque(){ 
//construct an empty deque
        first = new Node();
        last = new Node();
        N = 0;
    }
    public boolean isEmpty() {
//is the deque empty?
        return N==0;
    }
    
    public int size() {
       return N; 
    }
    
    public void addFirst(Item item){
        if (null == item) 
            throw new NullPointerException();
        if ( N == 0 ){
            first = new Node();
            first.value = item;
            last = first;
            N++;
            return;
        }        
        Node oldFirst = first;
        first = new Node();
        first.value = item;
        first.next = oldFirst;
        oldFirst.previous = first;
        N++;
    }
    
    public void addLast(Item item){
        if (null == item) 
            throw new NullPointerException();
        if (N ==0){
            last = new Node();
            last.value = item;
            first = last;
            N++;
            return;
        }
        Node oldLast = last;
        last = new Node();
        last.value = item;
        last.previous = oldLast;
        oldLast.next = last;
        N++;
    }
    
    public Item removeFirst(){
        if(this.size()==0)
            throw new NoSuchElementException();
        Item value = first.value;
        first = first.next;
        if (N > 1)
            first.previous = null;
        N--;
        if(N <= 1)
            last = first;
        return value;
    }
    public Item removeLast(){
        if(this.size()==0)
            throw new NoSuchElementException();
        Item value = last.value;
        last = last.previous;
        if(N > 1)
            last.next = null;
        N--;       
        if(N <= 1){
            first = last;           
        }
        return value;
    }
    
    
    public Iterator<Item> iterator(){ 
// return an iterator over iterms in order from front to end
        
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext() {return current!=null;}
        public void remove() {throw new UnsupportedOperationException();}
        public Item next(){
            if (null==current)
                throw new NoSuchElementException();
            Item value = current.value;
            current = current.next;
            return value;
        }
    }
    public static void main(String[] args){
        Deque myque = new Deque();
        myque.addFirst("pengfei ");
        myque.addLast("is ");
        System.out.println(myque.removeFirst());
        System.out.println(myque.removeLast());
        Iterator myI = myque.iterator();
        System.out.println(myI.next());
    }
}