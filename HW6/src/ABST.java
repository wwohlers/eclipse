import java.util.Comparator;

import tester.*;

// to represent a binary tree
abstract class ABST<T> {
  Comparator<T> order;
  
  /*
   * FIELDS:
   * ... this.order ... -- Comparator<T>
   * METHODS:
   * ... this.insert(T) ... -- ABST<T>
   * ... this.present(T) ... -- boolean
   * ... this.getLeftmost() ... -- T
   * ... this.getLeftmostAcc(T) ... -- T
   * ... this.getRight() ... -- ABST<T>
   * ... this.sameTree(ABST<T>) ... -- boolean
   * ... this.sameLeaf(Leaf<T>) ... -- boolean
   * ... this.sameNode(Node<T>) ... -- boolean
   * ... this.sameData(ABST<T>) ... -- boolean
   * ... this.sameDataH(ABST<T>) ... -- boolean
   * ... this.buildList() ... -- IList<T>
   * METHODS OF FIELDS:
   * ... this.order.compare(T, T) ... -- int
   */

  // inserts the given object into the binary tree
  abstract ABST<T> insert(T el);

  // returns true if the given object is in the binary tree
  abstract boolean present(T el);

  // returns the leftmost item contained in the binary tree
  abstract T getLeftmost();

  // helper function for getLeftMost
  // acc: keeps track of the last element
  abstract T getLeftmostAcc(T acc);

  // returns the binary tree containing all but the leftmost item
  abstract ABST<T> getRight();

  // returns true if this binary tree has the same values/structure as the given
  // binary tree
  abstract boolean sameTree(ABST<T> other);

  // returns true if the given ABST is the same as this leaf
  boolean sameLeaf(Leaf<T> other) {
    return false;
  }

  // returns true if the given ABST is the same as this node
  boolean sameNode(Node<T> other) {
    return false;
  }

  // returns true if both this and the given binary tree contain the same data
  boolean sameData(ABST<T> other) {
    return this.sameDataH(other) && other.sameDataH(this);
  }

  // checks that everything in this tree is in the other tree
  abstract boolean sameDataH(ABST<T> other);

  // builds a list representation of this binary search tree in order
  abstract IList<T> buildList();
}

// to represent a leaf at the end of a binary tree
class Leaf<T> extends ABST<T> {
  Leaf(Comparator<T> order) {
    this.order = order;
  }
  
  /*
   * same as above plus:
   * FIELDS:
   * ... this.order ... -- Comparator<T>
   * METHODS:
   * ... this.insert(T) ... -- ABST<T>
   * ... this.present(T) ... -- boolean
   * ... this.getLeftmost() ... -- T
   * ... this.getLeftmostAcc(T) ... -- T
   * ... this.getRight() ... -- ABST<T>
   * ... this.sameTree(ABST<T>) ... -- boolean
   * ... this.sameLeaf(Leaf<T>) ... -- boolean
   * ... this.sameDataH(ABST<T>) ... -- boolean
   * ... this.buildList() ... -- IList<T>
   * METHODS OF FIELDS:
   * ... this.order.compare(T, T) ... -- int
   */
  

  // inserts the given object into the binary tree
  ABST<T> insert(T el) {
    return new Node<T>(this.order, el, new Leaf<T>(this.order), new Leaf<T>(this.order));
  }

  // returns true if the given object is in the binary tree
  boolean present(T el) {
    return false;
  }

  // returns the leftmost item contained in the binary tree
  T getLeftmost() {
    throw new RuntimeException("No leftmost item of an empty tree");
  }

  // returns the accumulator value upon reaching a leaf
  T getLeftmostAcc(T acc) {
    return acc;
  }

  // throws exception (no rightmost of a leaf by itself)
  ABST<T> getRight() {
    throw new RuntimeException("No right of an empty tree");
  }

  // returns true if this binary tree has the same values/structure as the given
  // binary tree
  boolean sameTree(ABST<T> other) {
    return other.sameLeaf(this);
  }

  // returns true if the given ABST is the same as this leaf
  boolean sameLeaf(Leaf<T> other) {
    return true;
  }

  // checks that everything in this leaf is in the given ABST
  boolean sameDataH(ABST<T> other) {
    return true;
  }

  IList<T> buildList() {
    return new MtList<T>();
  }
}

// to represent a node in a binary tree
class Node<T> extends ABST<T> {
  T data;
  ABST<T> left;
  ABST<T> right;

  Node(Comparator<T> order, T data, ABST<T> left, ABST<T> right) {
    this.order = order;
    this.data = data;
    this.left = left;
    this.right = right;
  }
  
  /*
   * same as above plus:
   * FIELDS:
   * ... this.data ... -- T
   * ... this.left ... -- ABST<T>
   * ... this.right ... -- ABST<T>
   * ... this.order ... -- Comparator<T>
   * METHODS:
   * ... this.insert(T) ... -- ABST<T>
   * ... this.present(T) ... -- boolean
   * ... this.getLeftmost() ... -- boolean
   * ... this.getLeftmostAcc(T) ... -- T
   * ... this.getRight() ... -- ABST<T>
   * ... this.sameTree(ABST<T>) ... -- boolean
   * ... this.sameNode(Node<T>) ... -- boolean
   * ... this.sameDataH(ABST<T>) ... -- boolean
   * ... this.buildList() ... -- IList<T>
   * METHODS OF FIELDS:
   * ... this.order.compare(T, T) ... -- int
   * ... this.left.insert(T) ... -- ABST<T>
   * ... this.right.insert(T) ... -- ABST<T>
   * ... this.left.present(T) ... -- boolean
   * ... this.right.present(T) ... -- boolean
   * ... this.left.getLeftmost() ... -- T
   * ... this.right.getLeftmost() ... -- T
   * ... this.left.getLeftmostAcc(T) ... -- T
   * ... this.right.getLeftmostAcc(T) ... -- T
   * ... this.left.getRight() ... -- ABST<T>
   * ... this.right.getRight() ... -- ABST<T>
   * ... this.left.sameTree(ABST<T>) ... -- boolean
   * ... this.right.sameTree(ABST<T>) ... -- boolean
   * ... this.left.sameLeaf(Leaf<T>) ... -- boolean
   * ... this.right.sameLeaf(Leaf<T>) ... -- boolean
   * ... this.left.sameNode(Node<T>) ... -- boolean
   * ... this.right.sameNode(Node<T>) ... -- boolean
   * ... this.left.sameData(ABST<T>) ... -- boolean
   * ... this.right.sameData(ABST<T>) ... -- boolean
   * ... this.left.sameDataH(ABST<T>) ... -- boolean
   * ... this.right.sameDataH(ABST<T>) ... -- boolean
   * ... this.left.buildList() ... -- IList<T>
   * ... this.right.buildList() ... -- IList<T>
   * 
   */

  // inserts the given object into the binary tree
  ABST<T> insert(T el) {
    if (this.order.compare(el, this.data) < 0) {
      return new Node<T>(this.order, this.data, this.left.insert(el), this.right);
    }
    else {
      return new Node<T>(this.order, this.data, this.left, this.right.insert(el));
    }
  }

  // returns true if the given object is in the binary tree
  boolean present(T el) {
    if (this.order.compare(this.data, el) == 0) {
      return true;
    }
    else {
      if (this.order.compare(el, this.data) < 0) {
        return this.left.present(el);
      }
      else {
        return this.right.present(el);
      }
    }
  }

  // returns the leftmost item contained in the binary tree
  T getLeftmost() {
    return this.getLeftmostAcc(this.data);
  }

  // calls getLeftmost helper on left child
  T getLeftmostAcc(T acc) {
    return this.left.getLeftmostAcc(this.data);
  }

  // get the part of a tree excluding the leftmost item
  ABST<T> getRight() {
    if (this.order.compare(this.data, this.getLeftmost()) == 0) {
      return this.right;
    }
    else {
      return new Node<T>(this.order, this.data, this.left.getRight(), this.right);
    }
  }

  // returns true if this binary tree has the same values/structure as the given
  // binary tree
  boolean sameTree(ABST<T> other) {
    return other.sameNode(this);
  }

  // returns true if the given ABST is the same as this node
  boolean sameNode(Node<T> other) {
    return this.order.compare(this.data, other.data) == 0 && this.left.sameTree(other.left)
        && this.right.sameTree(other.right);
  }

  // checks that everything in this node is in the given ABST
  boolean sameDataH(ABST<T> other) {
    return other.present(this.data) && this.left.sameDataH(other) && this.right.sameDataH(other);
  }

  IList<T> buildList() {
    return new ConsList<T>(this.getLeftmost(), this.getRight().buildList());
  }
}

// to represent a book object
class Book {
  String title;
  String author;
  int price;

  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }
}

// function object for comparing books by title
class BooksByTitle implements Comparator<Book> {
  public int compare(Book b1, Book b2) {
    return b1.title.compareTo(b2.title);
  }
}

// function object for comparing books by author
class BooksByAuthor implements Comparator<Book> {
  public int compare(Book b1, Book b2) {
    return b1.author.compareTo(b2.author);
  }
}

// function object for comparing books by price
class BooksByPrice implements Comparator<Book> {
  public int compare(Book b1, Book b2) {
    return b1.price - b2.price;
  }
}

// function object for comparing numbers
class NumOrder implements Comparator<Integer> {
  public int compare(Integer i1, Integer i2) {
    return i1 - i2;
  }
}

// function object for comparing strings
class StringOrder implements Comparator<String> {
  public int compare(String s1, String s2) {
    return s1.compareTo(s2);
  }
}

// to represent a list of objects of type T
interface IList<T> {
}

// to represent an empty list of objects of type T
class MtList<T> implements IList<T> {
  MtList() {
  }
}

// to represent a non-empty list of objects of type T
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }
}

// examples and tests
class ExamplesABST<T> {
  // Empty cases
  ABST<Integer> leaf1 = new Leaf<Integer>(new NumOrder());
  ABST<String> leaf2 = new Leaf<String>(new StringOrder());
  ABST<Book> leaf3 = new Leaf<Book>(new BooksByTitle());
  ABST<Book> leaf4 = new Leaf<Book>(new BooksByAuthor());
  ABST<Book> leaf5 = new Leaf<Book>(new BooksByPrice());

  // Valid Int
  ABST<Integer> validInt = new Node<Integer>(
      new NumOrder(), 3, new Node<Integer>(new NumOrder(), 2,
          new Node<Integer>(new NumOrder(), 1, leaf1, leaf1), leaf1),
      new Node<Integer>(new NumOrder(), 4, leaf1, leaf1));

  // Valid String
  ABST<String> validStr = new Node<String>(new StringOrder(), "Fundies",
      new Node<String>(new StringOrder(), "Discrete",
          new Node<String>(new StringOrder(), "Cybersecurity", leaf2, leaf2),
          new Node<String>(new StringOrder(), "Engineering", leaf2, leaf2)),
      leaf2);

  // Invalid Int
  ABST<Integer> invalidInt = new Node<Integer>(new NumOrder(), 3,
      new Node<Integer>(new NumOrder(), 1, leaf1, leaf1),
      new Node<Integer>(new NumOrder(), 4, new Node<Integer>(new NumOrder(), 2, leaf1, leaf1),
          new Node<Integer>(new NumOrder(), 5, leaf1, leaf1)));

  // Book examples
  Book b1 = new Book("Great Gatsby", "F Scott Fitzgerald", 10);
  Book b2 = new Book("The Bible", "God", 200);
  Book b3 = new Book("Countdown to Zero Day", "Kim Zetter", 20);
  Book b4 = new Book("Adventures of Huckleberry Finn", "Mark Twain", 8);
  Book b5 = new Book("Fahrenheit 451", "Ray Bradbury", 16);
  Book b6 = new Book("Invisible Man", "Ralph Ellison", 18);
  Book b7 = new Book("Slaughterhouse Five", "Kurt Vonnegut", 30);
  Book b8 = new Book("Ender's Game", "Orson Scott Card", 100);

  // Book example 1 (valid for titles)
  ABST<Book> books1 = new Node<Book>(new BooksByTitle(), b5,
      new Node<Book>(new BooksByTitle(), b3, new Node<Book>(new BooksByTitle(), b4, leaf3, leaf3),
          leaf3),
      new Node<Book>(new BooksByTitle(), b1, leaf3,
          new Node<Book>(new BooksByTitle(), b2, leaf3, leaf3)));

  // Book example 2 (valid for authors)
  ABST<Book> books2 = new Node<Book>(new BooksByAuthor(), b2,
      new Node<Book>(new BooksByAuthor(), b1, leaf4, leaf4),
      new Node<Book>(new BooksByAuthor(), b3, leaf4, new Node<Book>(new BooksByAuthor(), b4, leaf4,
          new Node<Book>(new BooksByAuthor(), b5, leaf4, leaf4))));

  // Book example 3 (valid for prices)
  ABST<Book> books3 = new Node<Book>(new BooksByPrice(), b2,
      new Node<Book>(new BooksByPrice(), b1, new Node<Book>(new BooksByPrice(), b4, leaf5, leaf5),
          new Node<Book>(new BooksByPrice(), b3,
              new Node<Book>(new BooksByPrice(), b5, leaf5, leaf5), leaf5)),
      leaf5);

  // Single book node for testing
  ABST<Book> books4 = new Node<Book>(new BooksByPrice(), b8, leaf5, leaf5);

  // lists of books examples
  IList<Book> mt = new MtList<Book>();
  IList<Book> byTitle = new ConsList<Book>(b4, new ConsList<Book>(b3,
      new ConsList<Book>(b5, new ConsList<Book>(b1, new ConsList<Book>(b2, mt)))));
  IList<Book> byAuthor = new ConsList<Book>(b1, new ConsList<Book>(b2,
      new ConsList<Book>(b3, new ConsList<Book>(b4, new ConsList<Book>(b5, mt)))));
  IList<Book> byPrice = new ConsList<Book>(b4, new ConsList<Book>(b1,
      new ConsList<Book>(b5, new ConsList<Book>(b3, new ConsList<Book>(b2, mt)))));
  IList<Book> randomList = new ConsList<Book>(b3, new ConsList<Book>(b5,
      new ConsList<Book>(b4, new ConsList<Book>(b7, new ConsList<Book>(b8, mt)))));

  // tests for insert
  boolean testInsert(Tester t) {
    return t.checkExpect(leaf1.insert(10), new Node<Integer>(new NumOrder(), 10, leaf1, leaf1))
        && t.checkExpect(leaf2.insert("Hello"),
            new Node<String>(new StringOrder(), "Hello", leaf2, leaf2))
        && t.checkExpect(leaf3.insert(b1), new Node<Book>(new BooksByTitle(), b1, leaf3, leaf3))
        && t.checkExpect(books1.insert(b6),
            new Node<Book>(new BooksByTitle(), b5,
                new Node<Book>(new BooksByTitle(), b3,
                    new Node<Book>(new BooksByTitle(), b4, leaf3, leaf3), leaf3),
                new Node<Book>(new BooksByTitle(), b1, leaf3,
                    new Node<Book>(new BooksByTitle(), b2,
                        new Node<Book>(new BooksByTitle(), b6, leaf3, leaf3), leaf3))))
        && t.checkExpect(books2.insert(b7),
            new Node<Book>(new BooksByAuthor(), b2,
                new Node<Book>(new BooksByAuthor(), b1, leaf4, leaf4),
                new Node<Book>(new BooksByAuthor(), b3, leaf4,
                    new Node<Book>(new BooksByAuthor(), b4,
                        new Node<Book>(new BooksByAuthor(), b7, leaf4, leaf4),
                        new Node<Book>(new BooksByAuthor(), b5, leaf4, leaf4)))))
        && t.checkExpect(books3.insert(b8),
            new Node<Book>(new BooksByPrice(), b2,
                new Node<Book>(new BooksByPrice(), b1,
                    new Node<Book>(new BooksByPrice(), b4, leaf5, leaf5),
                    new Node<Book>(new BooksByPrice(), b3,
                        new Node<Book>(new BooksByPrice(), b5, leaf5, leaf5),
                        new Node<Book>(new BooksByPrice(), b8, leaf5, leaf5))),
                leaf5));
  }

  // tests for present
  boolean testPresent(Tester t) {
    return t.checkExpect(leaf1.present(5), false) && t.checkExpect(leaf2.present("five"), false)
        && t.checkExpect(leaf3.present(b4), false) && t.checkExpect(validInt.present(3), true)
        && t.checkExpect(validStr.present("Engineering"), true)
        && t.checkExpect(books1.present(b2), true) && t.checkExpect(books2.present(b1), true)
        && t.checkExpect(books3.present(b8), false);
  }

  // tests for getLeftmost
  boolean testGetLeftmost(Tester t) {
    return t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf1,
        "getLeftmost")
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf2,
            "getLeftmost")
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"), this.leaf3,
            "getLeftmost")
        && t.checkExpect(validInt.getLeftmost(), 1)
        && t.checkExpect(validStr.getLeftmost(), "Cybersecurity")
        && t.checkExpect(books1.getLeftmost(), b4) && t.checkExpect(books2.getLeftmost(), b1)
        && t.checkExpect(books3.getLeftmost(), b4) && t.checkExpect(books4.getLeftmost(), b8);
  }

  // tests for getRight
  boolean testGetRight(Tester t) {
    return t.checkException(new RuntimeException("No right of an empty tree"), this.leaf1,
        "getRight")
        && t.checkException(new RuntimeException("No right of an empty tree"), this.leaf2,
            "getRight")
        && t.checkException(new RuntimeException("No right of an empty tree"), this.leaf3,
            "getRight")
        && t.checkExpect(validInt.getRight(),
            new Node<Integer>(new NumOrder(), 3, new Node<Integer>(new NumOrder(), 2, leaf1, leaf1),
                new Node<Integer>(new NumOrder(), 4, leaf1, leaf1)))
        && t.checkExpect(validStr.getRight(),
            new Node<String>(new StringOrder(), "Fundies",
                new Node<String>(new StringOrder(), "Discrete", leaf2,
                    new Node<String>(new StringOrder(), "Engineering", leaf2, leaf2)),
                leaf2))
        && t.checkExpect(books1.getRight(),
            new Node<Book>(new BooksByTitle(), b5,
                new Node<Book>(new BooksByTitle(), b3, leaf3, leaf3),
                new Node<Book>(new BooksByTitle(), b1, leaf3,
                    new Node<Book>(new BooksByTitle(), b2, leaf3, leaf3))))
        && t.checkExpect(books2.getRight(),
            new Node<Book>(new BooksByAuthor(), b2, leaf4,
                new Node<Book>(new BooksByAuthor(), b3, leaf4,
                    new Node<Book>(new BooksByAuthor(), b4, leaf4,
                        new Node<Book>(new BooksByAuthor(), b5, leaf4, leaf4)))))
        && t.checkExpect(books3.getRight(),
            new Node<Book>(new BooksByPrice(), b2,
                new Node<Book>(new BooksByPrice(), b1, leaf5,
                    new Node<Book>(new BooksByPrice(), b3,
                        new Node<Book>(new BooksByPrice(), b5, leaf5, leaf5), leaf5)),
                leaf5))
        && t.checkExpect(books4.getRight(), leaf5);
  }

  // tests for sameTree
  boolean testSameTree(Tester t) {
    return t.checkExpect(leaf1.sameTree(leaf1), true) && t.checkExpect(leaf2.sameTree(leaf2), true)
        && t.checkExpect(leaf3.sameTree(leaf3), true)
        && t.checkExpect(validInt.sameTree(invalidInt), false)
        && t.checkExpect(validStr.sameTree(validStr), true)
        && t.checkExpect(books1.sameTree(books2), false)
        && t.checkExpect(books2.sameTree(books3), false)
        && t.checkExpect(books3.sameTree(books3), true)
        && t.checkExpect(books4.sameTree(books1), false);
  }

  // tests for sameLeaf
  boolean testSameLeaf(Tester t) {
    return t.checkExpect(leaf1.sameLeaf((Leaf<Integer>) leaf1), true)
        && t.checkExpect(leaf2.sameLeaf((Leaf<String>) leaf2), true)
        && t.checkExpect(leaf3.sameLeaf((Leaf<Book>) leaf3), true);
  }

  // tests for sameNode
  boolean testSameNode(Tester t) {
    return t.checkExpect(validInt.sameNode((Node<Integer>) invalidInt), false)
        && t.checkExpect(validStr.sameNode((Node<String>) validStr), true)
        && t.checkExpect(books1.sameNode((Node<Book>) books2), false)
        && t.checkExpect(books2.sameNode((Node<Book>) books3), false)
        && t.checkExpect(books3.sameNode((Node<Book>) books3), true);
  }

  // tests for sameData
  boolean testSameData(Tester t) {
    return t.checkExpect(leaf1.sameData(leaf1), true) && t.checkExpect(leaf2.sameData(leaf2), true)
        && t.checkExpect(leaf3.sameData(leaf3), true)
        && t.checkExpect(validInt.sameData(validInt), true)
        && t.checkExpect(validInt.sameData(invalidInt), false)
        && t.checkExpect(validInt.sameData(leaf1), false)
        && t.checkExpect(validStr.sameData(validStr), true)
        && t.checkExpect(validStr.sameData(leaf2), false)
        && t.checkExpect(books1.sameData(books1), true)
        && t.checkExpect(books1.sameData(books2), true)
        && t.checkExpect(books1.sameData(books1.insert(b6)), false)
        && t.checkExpect(books1.sameData(leaf3), false)
        && t.checkExpect(books2.sameData(books2), true)
        && t.checkExpect(books2.sameData(books3), true)
        && t.checkExpect(books2.sameData(books2.insert(b7)), false)
        && t.checkExpect(books2.sameData(leaf4), false)
        && t.checkExpect(books3.sameData(books3), true)
        && t.checkExpect(books3.sameData(books1), true)
        && t.checkExpect(books3.sameData(books3.insert(b8)), false)
        && t.checkExpect(books3.sameData(leaf5), false);
  }

  // tests for sameDataH
  boolean testSameDataH(Tester t) {
    return t.checkExpect(leaf1.sameDataH(leaf1), true)
        && t.checkExpect(leaf2.sameDataH(leaf2), true)
        && t.checkExpect(leaf3.sameDataH(leaf3), true)
        && t.checkExpect(validInt.sameDataH(validInt), true)
        && t.checkExpect(validInt.sameDataH(invalidInt), false)
        && t.checkExpect(validInt.sameDataH(leaf1), false)
        && t.checkExpect(validStr.sameDataH(validStr), true)
        && t.checkExpect(validStr.sameDataH(leaf2), false)
        && t.checkExpect(books1.sameDataH(books1), true)
        && t.checkExpect(books1.sameDataH(books2), true)
        && t.checkExpect(books1.sameDataH(books1.insert(b6)), true)
        && t.checkExpect(books1.insert(b6).sameDataH(books1), false)
        && t.checkExpect(books1.sameDataH(leaf3), false)
        && t.checkExpect(books2.sameDataH(books2), true)
        && t.checkExpect(books2.sameDataH(books3), true)
        && t.checkExpect(books2.sameDataH(books2.insert(b7)), true)
        && t.checkExpect(books2.insert(b7).sameData(books2), false)
        && t.checkExpect(books2.sameDataH(leaf4), false)
        && t.checkExpect(books3.sameDataH(books3), true)
        && t.checkExpect(books3.sameDataH(books1), true)
        && t.checkExpect(books3.sameDataH(books3.insert(b8)), true)
        && t.checkExpect(books3.insert(b8).sameDataH(books3), false)
        && t.checkExpect(books3.sameDataH(leaf5), false);
  }

  // tests for buildList
  boolean testBuildList(Tester t) {
    return t.checkExpect(leaf1.buildList(), new MtList<Integer>())
        && t.checkExpect(leaf2.buildList(), new MtList<String>())
        && t.checkExpect(leaf3.buildList(), mt)
        && t.checkExpect(validInt.buildList(),
            new ConsList<Integer>(1,
                new ConsList<Integer>(2,
                    new ConsList<Integer>(3, new ConsList<Integer>(4, new MtList<Integer>())))))
        && t.checkExpect(validStr.buildList(),
            new ConsList<String>("Cybersecurity",
                new ConsList<String>("Discrete",
                    new ConsList<String>("Engineering",
                        new ConsList<String>("Fundies", new MtList<String>())))))
        && t.checkExpect(books1.buildList(), byTitle) && t.checkExpect(books2.buildList(), byAuthor)
        && t.checkExpect(books3.buildList(), byPrice);
  }
}