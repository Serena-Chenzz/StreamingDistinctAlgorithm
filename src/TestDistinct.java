
import java.io.*;

public class TestDistinct{
    
    public static void main(String args[]){
        Long startTime = System.nanoTime();
        if (args.length != 1){
            System.out.println("Should be only one argument, the filename.\n");
            System.exit(1);
        }
        
        String filename = args[0]; // argument is filename.
        Distinct d = new BJKST1(0x00ffffff,3,500);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Integer o = Integer.parseInt(line);
                d.add(o);
            //System.err.println("This is the line: " + line);
            }
            System.out.println(d.distinct());
        }
        catch(Exception e){
            System.out.println("File " + filename + "not ok.\n");
            System.exit(1);
        }
        Long endTime = System.nanoTime();
        System.out.println("Running time: " + (endTime-startTime)/((double)10E9)+"s");
    

    }
}