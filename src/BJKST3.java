// BJKST3.java
// BJKST3 distinct elements counter
// awirth for COMP90056
// Aug 2017

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class BJKST3 implements Distinct{
    private Hash[] h;
    private Hash[] g;
    private int[] z;
    private int thresh;
    private int dom;
    private int a=3;
    private int c=576; // magic number from the paper
    private int ran;
    private List<Map<Integer,Integer>> B;
    private int c2=18; //NOT from the formula!
    private int k;
    
    public BJKST3(int n, int eps1, int del1){
        // eps1 is an integer representing 1/epsilon
        k = (int) Math.ceil(c2*Math.log(del1));
        z = new int[k];
        B = new ArrayList<Map<Integer,Integer>>();
        dom = n;
        ran = (int) Math.ceil(a*
                Math.pow((Math.log(n)/Math.log(2)+1)*c*eps1*eps1,2));
        h = new Hash[k];
        g = new Hash[k];
        
        for(int i=0;i<k;i++){
            h[i] = new Hash();
            g[i] = new Hash();
            B.add(new HashMap<Integer,Integer>());
        }
        thresh = 576*eps1*eps1; 
    }
    public void add(Object o){
        int val = Hash.h_basic(o,dom);
        
        for(int i=0;i<k;i++){
            int gval = g[i].h2u(val, ran);
            int hval = h[i].h2u(val, dom);
            int vz = zeros(hval);
        
            if(vz >= z[i]){
                B.get(i).put(gval,vz);
                Map<Integer,Integer> targetMap = B.get(i);
                while(targetMap.size()>=thresh){
                    z[i]++;
                    for(Iterator<Map.Entry<Integer,Integer>> it= targetMap.entrySet().iterator();it.hasNext();){
                        Map.Entry<Integer, Integer> entry = it.next();
                        if (entry.getValue() < z[i]) {
                             it.remove();
                        }
                    }
                }
            }
        }
        
    }
    public double distinct(){
        double meanSize = getMeanSize(B);
        double meanZ = getMeanZ(z);
        System.out.println(meanSize + " " + meanZ);
        return meanSize*Math.pow(2, meanZ);
    }
    public double getMeanSize(List<Map<Integer,Integer>> lMap){
        int sum = 0;
        for(int i=0; i<lMap.size();i++){
            sum += lMap.get(i).size();
        }
        return (double)sum/(double)lMap.size();
    }
    public double getMeanZ(int[] data){
        int sum = 0;
        for(int i=0; i<data.length; i++){
            sum += data[i];
        }
        return (double)sum/data.length;
    }
    public int zeros(int v){
        return Integer.numberOfTrailingZeros(v);
    }
    
}
