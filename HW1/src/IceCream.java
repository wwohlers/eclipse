//to represent ice cream, either a scoop or an empty scoop
interface IIceCream { }

//to represent an empty scoop, a type of IceCream
class EmptyServing implements IIceCream {
  boolean cone;
  
  //constructs an empty scoop, with cone or cup (no cone)
  EmptyServing(boolean cone) {
    this.cone = cone;
  }
}

//to represent a scoop of ice cream, a type of IceCream
class Scooped implements IIceCream {
  IIceCream more;
  String flavor;
  
  //constructs a scoop of ice cream
  Scooped(IIceCream more, String flavor) {
    this.more = more;
    this.flavor = flavor;
  }
}

//examples of ice cream
class ExamplesIceCream {
  ExamplesIceCream() {}
  
  IIceCream order1 = new Scooped(new Scooped(new Scooped(new Scooped(new EmptyServing(false), 
      "mint chip"), "coffee"), "black raspberry"), "caramel swirl");
  IIceCream order2 = new Scooped(new Scooped(new Scooped(new EmptyServing(true), 
      "chocolate"), "vanilla"), "strawberry");
}