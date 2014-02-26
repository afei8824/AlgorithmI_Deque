import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int N = 0;
    public RandomizedQueue(){
//construct an empty randomized queue
        items = (Item[]) new Object[1];
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void enqueue(Item item){
        if (null == item) 
            throw new NullPointerException();
        if( N == items.length) {
            resize(2*items.length);
        }
        items[N++] = item;
    }
    public Item dequeue(){
        if(this.size()==0)
            throw new NoSuchElementException();        
        int index = StdRandom.uniform(N);
        Item item = items[index];
        items[index] = items[--N];
        items[N] = null;
        if(N > 0 && N == items.length/4) resize(items.length/2);
        return item;
    }
    
    public Item sample(){
        if(this.size()==0)
            throw new NoSuchElementException();        
        int index = StdRandom.uniform(N);
        return items[index];       
    }
    private void resize(int capacity){
        Item[] copy = (Item[]) new Object[capacity];
        for (int i=0; i < N; i++)
            copy[i] = items[i];
        items = copy;
    }
    
    public Iterator<Item> iterator(){        
// return an iterator over iterms in order from front to end
        
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>{
        private int[] seq;
        private int currentIndex = N;
        public RandomizedQueueIterator(){
            seq = new int[N];
            for (int i = 0; i < N; i++){
                seq[i] = i;
            }
            StdRandom.shuffle(seq);
        }
        public boolean hasNext() {return currentIndex > 0;}
        public void remove() {throw new UnsupportedOperationException();}
        public Item next(){
            if (currentIndex <= 0 | currentIndex >N)
                throw new NoSuchElementException();
            Item value = items[seq[--currentIndex]];
            return value;
        }
    }
    
    public static void main(String[] args){
        RandomizedQueue myQueue = new RandomizedQueue();
    }
}