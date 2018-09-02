import java.util.Arrays;

// AMS.java
// AMS distinct elements counter
// awirth for COMP90056
// Aug 2017,8

public class AMS implements Distinct{
    private int zs[];
    private int n;  //domain and range
    private Hash hs[];
    private int range = 536870912-3;//2^29-3
    //private int c=1835; //from the formula!
    private int c=18; //NOT from the formula!
    private int k;
        
    public AMS(int n, int del1){
        //Identify the number of calculators
        k = (int) Math.ceil(c*Math.log(del1));
        this.n = n;
        
        zs = new int[k];
        hs = new Hash[k];
        for(int i=0;i<k;i++){
            // create k new hash functions
            hs[i] = new Hash();
        }
        
    }
    public void add(Object o){
        for(int i=0;i<k;i++){
            int val = Hash.h_basic(o,n);
            val = hs[i].h2u(val, range);
            int nz = zeros(val);
            // for each hash function, store the maximum number of
            // zeros seen
            if(nz > zs[i]){
                zs[i] = nz;
            }
        }
    }
    public static double getMedian(int[] data) {
        int[] copy = Arrays.copyOf(data, data.length);
        Arrays.sort(copy);
        // if the length is odd, return the middle item, else return the average of the two middle items
        return (copy.length % 2 != 0) ? copy[copy.length / 2] : (copy[copy.length / 2] + copy[(copy.length / 2) - 1]) / 2;
    }
    public static double getMean(int[] data){
        double sum=0;
        for(int i=0; i<data.length;i++){
            sum += data[i];
        }
        return sum/(data.length);
    }
    public double distinct(){
        printData(zs);
        double m = getMean(zs); // because it's monotonic
        return Math.pow(2, m+0.5);
    }
    public int zeros(int v){
        return Integer.numberOfTrailingZeros(v);
    }
    public void printData(int[] data){
        for(int i=0; i<data.length;i++){
            System.out.println(Math.pow(2,data[i]+0.5)+ " " + data[i]);
        }
    }
    public static double getTopMedium(int[] data){
        int small = data[0];
        int large = data[0];
        for(int i=1; i<data.length; i++){
            large = (data[i]>large)?data[i]:large;
            small = (data[i]<small)?data[i]:small;
        }
        int[] counter = new int[large-small+1];
        for(int i=0; i< data.length;i++){
            counter[data[i]-small]++;
        }
        
        int top = counter[0];
        int topPos = 0;
        
        for(int i=1; i<counter.length;i++){
            if(top<counter[i]){
                top=counter[i];
                topPos = i;
            }
        }
        
        int secondTop = -1;
        int secondPos = -1;
        
        for(int i=0; i<counter.length; i++){
            if((i != topPos)&&(counter[i] > secondTop)){
                secondTop = counter[i];
                secondPos = i;
            }
        }
        topPos += small;
        secondPos += small;
        
        return (double)(topPos * top + secondPos *secondTop) / (top+secondTop);
        
        
    }
    
}

