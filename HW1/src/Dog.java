//to represent a Dog
class Dog {
  String name;
  String breed;
  int yob;
  String state;
  boolean hypoallergenic;
  
  //constructs a new dog
  Dog(String name, String breed, int yob, String state, boolean hypoallergenic) {
    this.name = name;
    this.breed = breed;
    this.yob = yob;
    this.state = state;
    this.hypoallergenic = hypoallergenic;
  }
}

//examples of Dog6a
class ExamplesDog {
  Dog huffle = new Dog("Hufflepuff", "Wheaten Terrier", 2012, "TX", true);
  Dog pearl = new Dog("Pearl", "Labrador Retriever", 2016, "MA", false);
  Dog elwood = new Dog("Elwood", "Labrador Retriever", 2009, "NY", false);
}