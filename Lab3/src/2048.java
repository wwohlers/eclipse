import tester.*;

interface IGamePiece {
  int getValue();
  
  IGamePiece merge(IGamePiece other);
  
  boolean isValid();
}

class BaseTile implements IGamePiece {
  int value;
  
  BaseTile(int value) {
    this.value = value;
  }
  
  public int getValue() {
    return this.value;
  }
  
  public IGamePiece merge(IGamePiece other) {
    return new MergeTile(this, other);
  }
  
  public boolean isValid() {
    return true;
  }
}

class MergeTile implements IGamePiece {
  IGamePiece piece1;
  IGamePiece piece2;
  
  MergeTile(IGamePiece p1, IGamePiece p2) {
    this.piece1 = p1;
    this.piece2 = p2;
  }
  
  public int getValue() {
    return piece1.getValue() + piece2.getValue();
  }
  
  public IGamePiece merge(IGamePiece other) {
    return new MergeTile(this, other);
  }
  
  public boolean isValid() {
    return piece1.getValue() == piece2.getValue()
        && piece1.isValid()
        && piece2.isValid();
  }
}