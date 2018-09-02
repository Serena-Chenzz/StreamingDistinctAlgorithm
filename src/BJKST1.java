// BJKST1.java
// BJKST1 distinct elements counter
// awirth for COMP90056
// Aug 2017,8

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BJKST1 implements Distinct{
    
    private int t;
    private int dom;
    private int ran;
    private Hash hs[];
    private List<PriorityQueue<Integer>> pq;
    private int c = 18;
    private int k;
    private double[] results;
    //private int n;
    
    public BJKST1(int n, int eps1, int del1){
        // input 1/eps .. eps1
        this.dom = n;
        System.out.println(dom);
        //this.ran = (int)Math.pow(n, 3);
        this.ran=536870912-3;
        //this.ran = n*n*n; // ideally
        
        this.k = (int) Math.ceil(c*Math.log(del1));
        results = new double[k];
        hs = new Hash[k];
        
        for(int i=0;i<k;i++){        
            hs[i] = new Hash();
        }
        
        t = 96*eps1*eps1;   //magic number 96 from BJKST paper
        pq = new ArrayList<PriorityQueue<Integer>>(k);
        for(int m=0; m<k;m++){
            pq.add(new PriorityQueue<Integer>(t));
        }
    }
    
    public void add(Object o){
        for(int i=0;i<k;i++){
            int val = Hash.h_basic(o,dom);
            val = hs[i].h2u(val,ran);
            PriorityQueue<Integer> pqI = pq.get(i);
            
            if(pqI.size()<t){
                pqI.add(-val);
                // while filling up the PQ, add the new value
            } else{
                int m = pqI.peek();
                // if less than the largest and not already present
                if(val < -m && !pqI.contains(-val)){
                    pqI.poll();
                    pqI.add(-val);
                }
            }
        }
    }
    public double distinct(){
        // find out the largest value in the priority queue,
        // which is actually, the smallest negated
        int m = getMedian(pq);
        System.out.format("m %8d t %8d ran %8d%n",(-m),t,ran);      
        
        return (double)t*(double)ran/(double)(-m);
    }
    
    public int getMedian(List<PriorityQueue<Integer>> collection){
        int[] top = new int[k];
        for(int i=0; i< k;i++){
            top[i] = collection.get(i).peek();
        }
        int[] copy = Arrays.copyOf(top, top.length);
        Arrays.sort(copy);
        // if the length is odd, return the middle item, else return the average of the two middle items
        return (copy.length % 2 != 0) ? copy[copy.length / 2] : (copy[copy.length / 2] + copy[(copy.length / 2) - 1]) / 2;
        
    }
    
    public int zeros(int v){
        return Integer.numberOfTrailingZeros(v);
    }
    
}
