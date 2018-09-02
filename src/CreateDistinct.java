import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

// CreateDistinct.java
// Creates some test data
// COMP90056 2018s2 Assignment A

public class CreateDistinct{ 
    public static void main(String args[]){
        PrintWriter writer;
        try {
            writer = new PrintWriter("number-file.txt", "UTF-8");
            for(int i=0;i < 10; i++){
                for(int j = 0; j< 1000000; j++){
                    writer.println(j);
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        
        
    }
    
}