// BJKST3.java
// BJKST3 distinct elements counter
// awirth for COMP90056
// Aug 2017

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BJKST2 implements Distinct{
    private Hash h;
    private Hash g;
    private int z;
    private int thresh;
    private int dom;
    private int a=3;
    private int c=576; // magic number from the paper
    private int ran;
    private ArrayList<Integer> B;
    
    public BJKST2(int n, int eps1){
        // eps1 is an integer representing 1/epsilon
        z = 0;
        dom = n;
        ran = (int) Math.ceil(a*
                Math.pow((Math.log(n)/Math.log(2)+1)*c*eps1*eps1,2));
        //System.out.format("ran %d%n", ran);
        h = new Hash();
        g = new Hash();
        thresh = 576*eps1*eps1; 
        B = new ArrayList<Integer>(thresh);
    }
    public void add(Object o){
        int val = Hash.h_basic(o,dom);// FIX??
        int hval = h.h2u(val, dom);
        int vz = zeros(hval);
    
        if(vz >= z){
            B.add(vz);
            while(B.size()>=thresh){
                z++;
                
                for(int p = 0; p<B.size(); p++){
                    if(B.get(p)<z){
                        B.remove(p);
                        p--;
                    }
                }
                System.out.println(z + " " + B.size());
            }
        }
        
    }
    public double distinct(){
        System.out.println(z);
        return B.size()*Math.pow(2, z);
    }
    public int zeros(int v){
        return Integer.numberOfTrailingZeros(v);
    }
    
}

