
public class Test {

    public static void main(String[] args) {
        Integer a=3;
        Integer b=3;
        int domain = 0x0fffffff;
        System.out.println(a.hashCode()& domain);
        System.out.println(b.hashCode()& domain);

    }

}
