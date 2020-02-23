import tester.*;
import java.awt.Color;
import javalib.worldimages.*; // images, like RectangleImage or OverlayImages
import javalib.funworld.*;
import javalib.worldcanvas.WorldCanvas;

interface ITree {
  WorldImage draw();

  boolean isDrooping();

  ITree addAngle(double theta);

  ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree that);
  
  double getWidth();
}

class Leaf implements ITree {
  int size; // represents the radius of the leaf
  Color color; // the color to draw it

  Leaf(int size, Color color) {
    this.size = size;
    this.color = color;
  }

  public WorldImage draw() {
    return new CircleImage(this.size, OutlineMode.SOLID, this.color);
  }

  public boolean isDrooping() {
    return false;
  }

  public ITree addAngle(double theta) {
    return this;
  }

  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree that) {
    that = that.addAngle(rightTheta);
    return new Branch(leftLength, rightLength, leftTheta, rightTheta, this, that);
  }
  
  public double getWidth() {
    return this.size;
  }
}

class Stem implements ITree {
  // How long this stick is
  int length;
  // The angle (in degrees) of this stem, relative to the +x axis
  double theta;
  // The rest of the tree
  ITree tree;

  Stem(int length, double theta, ITree tree) {
    this.length = length;
    this.theta = theta;
    this.tree = tree;
  }

  public WorldImage draw() {
    double angle = theta * Math.PI / 180;
    int X = (int) Math.round(length * Math.cos(angle));
    int Y = (int) Math.round(length * Math.sin(angle));
    WorldImage line = new LineImage(new Posn(X, 0 - Y), Color.BLACK);
    line = line.movePinhole((double) (X / 2), (double) ((0 - Y) / 2));
    WorldImage rest = this.tree.draw();
    WorldImage img = new OverlayImage(rest, line);
    img = img.movePinhole((double) (0 - X), (double) (Y));
    return img;
  }

  public boolean isDrooping() {
    return (this.theta % 360) > 180;
  }

  public ITree addAngle(double theta) {
    ITree rotated = this.tree.addAngle(theta - 90);
    ITree rotatedStem = new Stem(this.length, this.theta + theta - 90, rotated);
    return rotatedStem;
  }

  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree that) {
    ITree rotatedLeft = this.addAngle(leftTheta);
    ITree rotatedRight = that.addAngle(rightTheta);
    return new Branch(leftLength, rightLength, leftTheta, rightTheta, rotatedLeft, rotatedRight);
  }
  
  public double getWidth() {
    return this.draw().getWidth();
  }
}

class Branch implements ITree {
  // How long the left and right branches are
  int leftLength;
  int rightLength;
  // The angle (in degrees) of the two branches, relative to the +x axis,
  double leftTheta;
  double rightTheta;
  // The remaining parts of the tree
  ITree left;
  ITree right;

  Branch(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree left, ITree right) {
    this.leftLength = leftLength;
    this.rightLength = rightLength;
    this.leftTheta = leftTheta;
    this.rightTheta = rightTheta;
    this.left = left;
    this.right = right;
  }

  public WorldImage draw() {
    WorldImage lineL = this.drawLine(this.leftTheta, this.leftLength, this.left);
    WorldImage lineR = this.drawLine(this.rightTheta, this.rightLength, this.right);
    return new OverlayImage(lineL, lineR);
  }

  WorldImage drawLine(double theta, int length, ITree tree) {
    double angle = theta * Math.PI / 180;
    int X = (int) Math.round(length * Math.cos(angle));
    int Y = (int) Math.round(length * Math.sin(angle));
    WorldImage line = new LineImage(new Posn(X, 0 - Y), Color.BLACK);
    line = line.movePinhole((double) (X / 2), (double) ((0 - Y) / 2));
    WorldImage rest = tree.draw();
    WorldImage img = new OverlayImage(rest, line);
    img = img.movePinhole((double) (0 - X), (double) (Y));
    return img;
  }

  public boolean isDrooping() {
    return (this.leftTheta % 360) > 180 || (this.rightTheta % 360) > 180;
  }

  public ITree addAngle(double theta) {
    ITree rotatedBranch = new Branch(this.leftLength, this.rightLength, leftTheta + theta - 90, 
        rightTheta + theta - 90, this.left.addAngle(theta - 90), this.right.addAngle(theta - 90));
    return rotatedBranch;
  }
  
  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree that) {
    ITree rotatedLeft = this.addAngle(leftTheta);
    ITree rotatedRight = that.addAngle(rightTheta);
    return new Branch(leftLength, rightLength, leftTheta, rightTheta, rotatedLeft, rotatedRight);
  }
  
  public double getWidth() {
    return this.draw().getWidth();
  }
}

class ExamplesTree {
  ITree tree1 = new Branch(30, 30, 135, 40, new Leaf(10, Color.RED), new Leaf(15, Color.BLUE));
  ITree tree2 = new Branch(30, 30, 115, 65, new Leaf(15, Color.GREEN), new Leaf(8, Color.ORANGE));
  ITree stem1 = new Stem(40, 90, tree2);
  ITree branch1 = new Branch(40, 50, 150, 30, tree1, tree2);
  ITree branch2 = tree1.combine(40, 50, 150, 30, tree2);

  boolean testDrawTree(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    return c.drawScene(s.placeImageXY(branch1.draw(), 250, 250)) && c.show();
  }

  boolean testCombine(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    return c.drawScene(s.placeImageXY(branch2.draw(), 250, 250)) && c.show();
  }

  boolean testIsDrooping(Tester t) {
    return true;
  }
  
  boolean testGetWidth(Tester t) {
    return t.checkExpect(branch1.getWidth(), 131.5625)
        && t.checkExpect(branch2.getWidth(), 0);
  }
}