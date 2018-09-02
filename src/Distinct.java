// Distinct.java
// Interface for distinct elements counter
// awirth for COMP90056
// Aug 2017,8

public interface Distinct{
    int zeros(int v);// Calculate the number of zeros seen
    void add(Object o); // add a new element to the collection
    double distinct(); // return a double representing the number of
                // distinct elements
}

