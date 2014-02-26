
public class Subset{
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        RandomizedQueue myQueue = new RandomizedQueue();
        while(!StdIn.isEmpty()){
            myQueue.enqueue(StdIn.readString());
        }
        int count = 0;
        for(Object myString: myQueue){
            if(n==0) break;
            StdOut.println(myString);
            count++;
            if(count==n) break;
        }
    }
}