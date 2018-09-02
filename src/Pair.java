// Pair.java
// Basic implementation of a pair class
// awirth for COMP90056 (based on downloaded material)
// Aug 2017,8

public class Pair<L,R> {

  private final L left; // assumes we'd only like to assign the value once
  private final R right;

  public Pair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  public L getLeft() { return left; }
  public R getRight() { return right; }

  @Override
  public int hashCode() { return left.hashCode() ^ right.hashCode(); }
    // new method for hashCOde

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pair)) return false; // testing if a given object equals
                        // this pair
    Pair<L,R> pairo = (Pair<L,R>) o;    // the object for the pair
    return this.left.equals(pairo.getLeft()) &&  // using builtin equals method
           this.right.equals(pairo.getRight());
  }

}
