import tester.*;

// represents a circuit or circuit component
interface ICircuit {
  // counts the number of components in the circuit
  int countComponents();
  
  // counts the total voltage of the circuit
  double totalVoltage();
  
  // counts the total resistance of the circuit
  double totalResistance();
  
  // counts the total current of the circuit
  double totalCurrent();
  
  // returns a circuit with the voltages reversed
  ICircuit reversePolarity();
}

// represents a battery
class Battery implements ICircuit {
  String name;
  double voltage;
  double nominalResistance;
  
  Battery(String name, double voltage, double nominalResistance) {
    this.name = name;
    this.voltage = voltage;
    this.nominalResistance = nominalResistance;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.name              -- String
   * this.voltage           -- double
   * this.nominalResistance -- double
   * 
   * Methods:
   * this.countComponents() -- int
   * this.totalVoltage()    -- double
   * this.totalCurrent()    -- double
   * this.totalResistance() -- double
   * this.reversePolarity() -- ICircuit
   */
  
  public int countComponents() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return 1;
  }
  
  public double totalVoltage() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.voltage;
  }
  
  public double totalCurrent() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.voltage / this.nominalResistance;
  }
  
  public double totalResistance() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.nominalResistance;
  }
  
  public ICircuit reversePolarity() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return new Battery(this.name, 0 - this.voltage, this.nominalResistance);
  }
}

// represents a resistor
class Resistor implements ICircuit {
  String name;
  double resistance;
  
  Resistor(String name, double resistance) {
    this.name = name;
    this.resistance = resistance;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.name              -- String
   * this.resistance        -- double
   * 
   * Methods:
   * this.countComponents() -- int
   * this.totalVoltage()    -- double
   * this.totalCurrent()    -- double
   * this.totalResistance() -- double
   * this.reversePolarity() -- ICircuit
   */
  
  public int countComponents() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return 1;
  }
  
  public double totalVoltage() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return 0;
  }
  
  public double totalCurrent() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return 0;
  }
  
  public double totalResistance() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.resistance;
  }
  
  public ICircuit reversePolarity() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this;
  }
}

// represents a connection in series
class Series implements ICircuit {
  ICircuit first;
  ICircuit second;
  
  Series(ICircuit first, ICircuit second) {
    this.first = first;
    this.second = second;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.first              -- ICircuit
   * this.second             -- ICircuit
   * 
   * Methods:
   * this.countComponents() -- int
   * this.totalVoltage()    -- double
   * this.totalCurrent()    -- double
   * this.totalResistance() -- double
   * this.reversePolarity() -- ICircuit
   * 
   * Methods for fields:
   * this.first.countComponents() -- int
   * this.first.totalVoltage()    -- double
   * this.first.totalCurrent()    -- double
   * this.first.totalResistance() -- double
   * this.first.reversePolarity() -- ICircuit
   * this.second.countComponents() -- int
   * this.second.totalVoltage()    -- double
   * this.second.totalCurrent()    -- double
   * this.second.totalResistance() -- double
   * this.second.reversePolarity() -- ICircuit
   */
  
  public int countComponents() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.first.countComponents() + this.second.countComponents();
  }
  
  public double totalVoltage() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.first.totalVoltage() + this.second.totalVoltage();
  }
  
  public double totalResistance() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.first.totalResistance() + this.second.totalResistance();
  }
  
  public double totalCurrent() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.totalVoltage() / this.totalResistance();
  }

  public ICircuit reversePolarity() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return new Series(this.first.reversePolarity(), this.second.reversePolarity());
  }
}

// represents a connection in parallel
class Parallel implements ICircuit {
  ICircuit first;
  ICircuit second;
  
  Parallel(ICircuit first, ICircuit second) {
    this.first = first;
    this.second = second;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.first              -- ICircuit
   * this.second             -- ICircuit
   * 
   * Methods:
   * this.countComponents() -- int
   * this.totalVoltage()    -- double
   * this.totalCurrent()    -- double
   * this.totalResistance() -- double
   * this.reversePolarity() -- ICircuit
   * 
   * Methods for fields:
   * this.first.countComponents() -- int
   * this.first.totalVoltage()    -- double
   * this.first.totalCurrent()    -- double
   * this.first.totalResistance() -- double
   * this.first.reversePolarity() -- ICircuit
   * this.second.countComponents() -- int
   * this.second.totalVoltage()    -- double
   * this.second.totalCurrent()    -- double
   * this.second.totalResistance() -- double
   * this.second.reversePolarity() -- ICircuit
   */
  
  public int countComponents() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.first.countComponents() + this.second.countComponents();
  }
  
  public double totalVoltage() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.first.totalVoltage();
  }
  
  public double totalResistance() { 
    // TEMPLATE: SEE CLASS TEMPLATE
    double invR1 = 1 / this.first.totalResistance();
    double invR2 = 1 / this.second.totalResistance();
    return 1 / (invR1 + invR2);
  }
  
  public double totalCurrent() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return totalVoltage() / totalResistance();
  }

  public ICircuit reversePolarity() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return new Series(this.first.reversePolarity(), this.second.reversePolarity());
  }
}

class ExamplesCircuits {
  /*
   * Two batteries, volt. 50 and 20 respectively, connected in series;
   * in turn connected in series with:
   * two resistors, resistance 40 and 30 respectively, connected in parallel.
   */
  ICircuit batt1 = new Battery("BATT1", 50, 10);
  ICircuit batt2 = new Battery("BATT2", 20, 30);
  ICircuit battSeries = new Series(batt1, batt2);
  ICircuit resist1 = new Resistor("RES1", 40);
  ICircuit resist2 = new Resistor("RES2", 30);
  ICircuit resistors = new Parallel(resist1, resist2);
  ICircuit example1 = new Series(battSeries, resistors);
  
  ICircuit batteryOne = new Battery("B 1", 10, 25);
  ICircuit resistorOne = new Resistor("R 1", 100);
  ICircuit simpleSeries = new Series(batteryOne, resistorOne);
  
  ICircuit battSeries2 = new Series(new Battery("BT 1", 10, 0), new Battery("BT 2", 20, 0));
  ICircuit rSeries = new Series(new Resistor("R 4", 200), new Resistor("R 5", 50));
  ICircuit topHalf = new Parallel(rSeries, new Resistor("R 1", 100));
  ICircuit inclR2 = new Parallel(topHalf, new Resistor("R 2", 250));
  ICircuit all = new Parallel(inclR2, new Resistor("R 3", 500));
  ICircuit complexCircuit = new Series(battSeries2, all);
  
  boolean testCountComponents(Tester t) {
    return t.checkExpect(batteryOne.countComponents(), 1) &&
        t.checkExpect(complexCircuit.countComponents(), 7);
  }
  
  boolean testTotalVoltage(Tester t) {
    return t.checkExpect(batteryOne.totalVoltage(), 10.0) &&
        t.checkExpect(complexCircuit.totalVoltage(), 30.0);    
  }
  
  boolean testTotalCurrent(Tester t) {
    return t.checkInexact(simpleSeries.totalCurrent(), 0.08, 0.0001) &&
        t.checkInexact(complexCircuit.totalCurrent(), 0.6, 0.0001);
  }
  
  // tests that this function properly reverses voltage
  boolean testReversePolarityVoltage(Tester t) {
    return t.checkExpect(simpleSeries.reversePolarity().totalVoltage(), 
        0 - simpleSeries.totalVoltage()) &&
        t.checkExpect(complexCircuit.reversePolarity().totalVoltage(), 
            0 - complexCircuit.totalVoltage());
  }
  
  // tests that calling reversePolarity does not affect the number of components in the circuit
  boolean testReversePolarityCount(Tester t) {
    return t.checkExpect(simpleSeries.reversePolarity().countComponents(), 
        simpleSeries.reversePolarity().countComponents()) &&
        t.checkExpect(complexCircuit.reversePolarity().countComponents(), 
            complexCircuit.reversePolarity().countComponents());
  }
}