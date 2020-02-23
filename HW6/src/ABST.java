import java.util.Comparator;

import tester.*;

abstract class ABST<T> {
  Comparator<T> order;
  
  abstract ABST<T> insert(T el);
  
  abstract boolean present(T el);
  
  abstract ABST<T> getLeftmost();
  
  abstract boolean sameTree(ABST<T> other);
  
  boolean sameLeaf(ABST<T> other) {
    return false;
  }
  
  boolean sameNode(ABST<T> other) {
    return false;
  }
  
  boolean sameData(ABST<T> other) {
    return this.sameDataH(other) && other.sameDataH(this);
  }
  
  // checks that everything in this tree is in the other tree
  abstract boolean sameDataH(ABST<T> other);
}

class Leaf<T> extends ABST<T> {
  ABST<T> insert(T el) {
    return new Node<T>(el, new Leaf<T>(), new Leaf<T>());
  }
  
  boolean present(T el) {
    return false;
  }
  
  ABST<T> getLeftmost() {
    return this;
  }
  
  boolean sameTree(ABST<T> other) {
    return other.sameLeaf(this);
  }
  
  boolean sameLeaf(Leaf<T> other) {
    return true;
  }
  
  boolean sameDataH(ABST<T> other) {
    return true;
  }
  
  boolean sameData(ABST<T> other) {
  }
}

class Node<T> extends ABST<T> {
  T data;
  ABST<T> left;
  ABST<T> right;

  Node(T data, ABST<T> left, ABST<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }
  
  ABST<T> insert(T el) {
    if (this.order.compare(el, this.data) < 0) {
      return new Node<T>(this.data, this.left.insert(el), this.right);
    } else {
      return new Node<T>(this.data, this.left, this.right.insert(el));
    }
  }
  
  boolean present(T el) {
    if (this.order.compare(this.data, el) == 0) {
      return true;
    } else {
      if (this.order.compare(el, this.data) < 0) {
        return this.left.present(el);
      } else {
        return this.right.present(el);
      }
    }
  }
  
  ABST<T> getLeftmost() {
    return this;
  }
  
  boolean sameTree(ABST<T> other) {
    return other.sameNode(this);
  }
  
  boolean sameNode(Node<T> other) {
    return this.order.compare(this.data, other.data) == 0
        && this.left.sameTree(other.left)
        && this.right.sameTree(other.right);
  }
  
  boolean sameDataH(ABST<T> other) {
    return other.present(this.data)
        && this.left.sameDataH(other)
        && this.right.sameDataH(other);
  }
}

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

class BooksByTitle implements Comparator<Book> {
  public int compare(Book b1, Book b2) {
    return b1.title.compareTo(b2.title);
  }
}

class BooksByAuthor implements Comparator<Book> {
  public int compare(Book b1, Book b2) {
    return b1.author.compareTo(b2.author);
  }
}

class BooksByPrice implements Comparator<Book> {
  public int compare(Book b1, Book b2) {
    return b1.price - b2.price;
  }
}

class ExamplesABST<T> {
  // Valid Int
  ABST<Integer> validInt = new Node<Integer>(3,
      new Node<Integer>(2, new Node<Integer>(1, new Leaf<Integer>(), new Leaf<Integer>()), new Leaf<Integer>()),
      new Node<Integer>(4, new Leaf<Integer>(), new Leaf<Integer>()));

  // Valid String
  ABST<String> validStr = new Node<String>("Fundies",
      new Node<String>("Discrete", new Node<String>("Cybersecurity", new Leaf<String>(), new Leaf<String>()),
          new Node<String>("Engineering", new Leaf<String>(), new Leaf<String>())),
      new Leaf<String>());

  // Invalid Int
  ABST<Integer> invalidInt = new Node<Integer>(3, new Node<Integer>(1, new Leaf<Integer>(), new Leaf<Integer>()),
      new Node<Integer>(4, new Node<Integer>(2, new Leaf<Integer>(), new Leaf<Integer>()),
          new Node<Integer>(5, new Leaf<Integer>(), new Leaf<Integer>())));

  // Book examples
  ABST<Book> leaf = new Leaf<Book>();
  Book b1 = new Book("Great Gatsby", "F Scott Fitzgerald", 10);
  Book b2 = new Book("The Bible", "God", 200);
  Book b3 = new Book("Countdown to Zero Day", "Kim Zetter", 20);
  Book b4 = new Book("Adventures of Huckleberry Finn", "Mark Twain", 8);
  Book b5 = new Book("Fahrenheit 451", "Ray Bradbury", 16);

  // Book example 1 (valid for titles)
  ABST<Book> books1 = new Node<Book>(b5, new Node<Book>(b3, new Node<Book>(b4, leaf, leaf), leaf),
      new Node<Book>(b1, leaf, new Node<Book>(b2, leaf, leaf)));

  // Book example 2 (valid for authors)
  ABST<Book> books2 = new Node<Book>(b2, new Node<Book>(b1, leaf, leaf),
      new Node<Book>(b3, leaf, new Node<Book>(b4, leaf, new Node<Book>(b5, leaf, leaf))));

  // Book example 3 (valid for prices)
  ABST<Book> books3 = new Node<Book>(b2,
      new Node<Book>(b1, new Node<Book>(b4, leaf, leaf), new Node<Book>(b3, new Node<Book>(b5, leaf, leaf), leaf)),
      leaf);
}
