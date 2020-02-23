//to represent housing generally
interface IHousing { }

//to represent a hut, a type of housing
class Hut implements IHousing {
  int capacity;
  int population;
  
  //constructs a new hut
  Hut(int capacity, int population) {
    this.capacity = capacity;
    this.population = population;
  }
}

//to represent an Inn, a type of housing
class Inn implements IHousing {
  String name;
  int capacity;
  int population;
  int stalls;
  
  //constructs a new Inn
  Inn(String name, int capacity, int population, int stalls) {
    this.name = name;
    this.capacity = capacity;
    this.population = population;
    this.stalls = stalls;
  }
}

//to represent a castle, a type of housing
class Castle implements IHousing {
  String name;
  String familyName;
  int population;
  int carriageHouse;
  
  //constructs a new Castle
  Castle(String name, String familyName, int population, int carriageHouse) {
    this.name = name;
    this.familyName = familyName;
    this.population = population;
    this.carriageHouse = carriageHouse;
  }
}

//to represent transportation generally
interface ITransportation { }

//to represent a horse, a type of transportation
class Horse implements ITransportation {
  IHousing from;
  IHousing to;
  String name;
  String color;
  
  //constructs a new Horse
  Horse(IHousing from, IHousing to, String name, String color) {
    this.from = from;
    this.to = to;
    this.name = name;
    this.color = color;
  }
}

//to represent a carriage, a type of transportation
class Carriage implements ITransportation {
  IHousing from;
  IHousing to;
  int tonnage;
  
  //constructs a new Carriage
  Carriage(IHousing from, IHousing to, int tonnage) {
    this.from = from;
    this.to = to;
    this.tonnage = tonnage;
  }
}

//examples of Housing and Transportation
class ExamplesTravel {
  ExamplesTravel() {}
  
  IHousing hovel = new Hut(5, 1);
  IHousing winterfell = new Castle("Winterfell", "Stark", 500, 6);
  IHousing crossroads = new Inn("Inn At The Crossroads", 40, 20, 12);
  IHousing tent = new Hut(4, 0);
  IHousing holiday = new Inn("Holiday Inn", 1000, 560, 5);
  IHousing myHouse = new Castle("The Wohlers Residence", "Wohlers", 4, 5);
  
  ITransportation horse1 = new Horse(hovel, crossroads, "Billy", "red");
  ITransportation carriage1 = new Carriage(holiday, myHouse, 2);
  ITransportation horse2 = new Horse(winterfell, tent, "Bob", "green");
  ITransportation carriage2 = new Carriage(crossroads, winterfell, 1);
}