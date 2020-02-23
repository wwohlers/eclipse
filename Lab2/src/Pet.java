import tester.*;

// to represent a pet owner
class Person {
    String name;
    IPet pet;
    int age;
 
    Person(String name, IPet pet, int age) {
        this.name = name;
        this.pet = pet;
        this.age = age;
    }
    
    // is this person older than the given person?
    boolean isOlder(Person other) {
      return this.age > other.age;
    }
    
    // does the name of this person's pet match the given name?
    boolean sameNamePet(String name) {
      return this.pet.sameName(name);
    }
    
    // puts the pet down (rip).
    void perish() {
      this.pet = new NoPet();
    }
}

class ExamplesPerson {
  Dog wuff = new Dog("Wuff", "lab", true);
  Dog spots = new Dog("Spots", "terrier", false);
  Cat fluffer = new Cat("Fluffer", "black", true);
  Cat porcupine = new Cat("Porcupine", "gray", false);
  
  Person bob = new Person("Bob", wuff, 50);
  Person alicia = new Person("Alicia", fluffer, 67);
  Person bill = new Person("Bill", spots, 18);
  Person jake = new Person("Jake", porcupine, 24);
  Person rip = new Person("Rip", new NoPet(), 30);
}

// to represent a pet
interface IPet { 
  // does the name of this pet match the given name?
  public boolean sameName(String name);
}
 
// to represent a pet cat
class Cat implements IPet {
    String name;
    String kind;
    boolean longhaired;
 
    Cat(String name, String kind, boolean longhaired) {
        this.name = name;
        this.kind = kind;
        this.longhaired = longhaired;
    }
    
    public boolean sameName(String name) {
      return name == this.name;
    }
}
 
// to represent a pet dog
class Dog implements IPet {
    String name;
    String kind;
    boolean male;
 
    Dog(String name, String kind, boolean male) {
        this.name = name;
        this.kind = kind;
        this.male = male;
    }
    
    public boolean sameName(String name) {
      return name == this.name;
    }
}

// to represent no pet
class NoPet implements IPet {
  NoPet() {
    
  }
  
  public boolean sameName(String name) {
    return false;
  }
}