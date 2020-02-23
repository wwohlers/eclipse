import tester.*;

// represents a List of Strings
// in this problem, represents either:
// - RNA, where each string is a letter in the sequence
// - protein, where each string is a codon
interface ILoString {
  ILoLoString translateH(ILoLoString acc, ILoString protein, String codon);
  
  ILoLoString translate();
  
  ILoLoString appendTo(ILoLoString loproteins);
  
  String first();
  
  ILoString rest();
  
  public ILoString appendToEnd(String str);
}

class MtLoString implements ILoString {
  /*
   * TEMPLATE
   * Methods:
   * 
   * this.translate() -- ILoLoString
   * this.translateH(ILoLoString, ILoString, String) -- ILoLoString
   * this.appendTo(ILoLoString) -- ILoLoString
   * this.first() -- String
   * this.rest() -- ILoString
   * this.appendToEnd(String) -- ILoString
   * 
   */
  
  // translates RNA sequence into list of proteins
  public ILoLoString translate() {
    return new MtLoLoString();
  }
  
  // translate helper function
  public ILoLoString translateH(ILoLoString acc, ILoString protein, String codon) {
    return protein.appendTo(acc);
  }
  
  // appends this protein to a list of proteins
  public ILoLoString appendTo(ILoLoString loproteins) {
    return loproteins;
  }
  
  // gets the first of this list
  public String first() {
    return "";
  }
  
  // gets the rest of this list
  public ILoString rest() {
    return new MtLoString();
  }
  
  // appends a string to the end of this list
  public ILoString appendToEnd(String str) {
    return new ConsLoString(str, new MtLoString());
  }
}

class ConsLoString implements ILoString {
  String first;
  ILoString rest;
  
  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.first -- String
   * this.rest -- ILoString
   * 
   * Methods:
   * this.translate() -- ILoLoString
   * this.translateH(ILoLoString, ILoString, String) -- ILoLoString
   * this.appendTo(ILoLoString) -- ILoLoString
   * this.first() -- String
   * this.rest() -- ILoString
   * this.appendToEnd(String) -- ILoString
   * 
   * Methods for fields:
   * this.first.length() -- int
   * this.rest.translate() -- ILoLoString
   * this.rest.translateH(ILoLoString, ILoString, String) -- ILoLoString
   * this.rest.appendTo(ILoLoString) -- ILoLoString
   * this.rest.first() -- String
   * this.rest.rest() -- ILoString
   * this.rest.appendToEnd(String) -- ILoString
   */
  
  // translates RNA sequence into list of proteins
  public ILoLoString translate() {
    return translateH(new MtLoLoString(), new MtLoString(), "");
  }
  
  //translate helper function
  public ILoLoString translateH(ILoLoString acc, ILoString protein, String codon) {
    codon = this.updateCodon(codon);
    if (codon.length() == 3) {
      if (codon.equals("UAG") || codon.equals("UAA") || codon.equals("UGA")) {
        // a stop codon
        ILoLoString newAcc = new ConsLoLoString(protein, acc);
        return rest.translateH(newAcc, new MtLoString(), "");
      }
      else {
        // a complete codon
        protein = protein.appendToEnd(codon);
        return rest.translateH(acc, protein, "");
      }
    }
    else {
      return rest.translateH(acc, protein, codon);
    }
  }
  
  // appends a string to the end of this list
  public ILoString appendToEnd(String str) {
    return new ConsLoString(this.first, this.rest.appendToEnd(str));
  }

  // updates the codon to include the first letter in this sequence
  String updateCodon(String codon) {
    return codon + this.first;
  }

  // appends this protein to a list of proteins
  public ILoLoString appendTo(ILoLoString loproteins) {
    return new ConsLoLoString(this, loproteins);
  }
  
  //gets the first of this list
  public String first() {
    return this.first;
  }

  // gets the rest of this list
  public ILoString rest() {
    return this.rest;
  }
}

// represents a List of List of Strings
// in this problem, represents a list of proteins 
interface ILoLoString {
  ILoString first();
  
  ILoLoString rest();
}

class MtLoLoString implements ILoLoString {
  /*
   * TEMPLATE
   * Methods:
   * this.first() -- ILoString
   * this.rest() -- ILoLoString
   */
  public ILoString first() {
    return new MtLoString();
  }
  
  public ILoLoString rest() {
    return new MtLoLoString();
  }
}

class ConsLoLoString implements ILoLoString {
  ILoString first;
  ILoLoString rest;
  
  ConsLoLoString(ILoString first, ILoLoString rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.first -- ILoString
   * this.rest -- ILoLoString
   * 
   * Methods:
   * this.first() -- ILoString
   * this.rest() -- ILoLoString
   * 
   * Methods for fields:
   * this.first.translate() -- ILoLoString
   * this.first.translateH(ILoLoString, ILoString, String) -- ILoLoString
   * this.first.appendTo(ILoLoString) -- ILoLoString
   * this.first.first() -- String
   * this.first.rest() -- ILoString
   * this.first.appendToEnd(String) -- ILoString
   * this.rest.first() -- ILoString
   * this.rest.rest() -- ILoLoString
   */
  
  public ILoString first() {
    return this.first;
  }
  
  public ILoLoString rest() {
    return this.rest;
  }
}

class ExamplesRna {
  ILoString strToSeq(String str) {
    if (str.equals("")) {
      return new MtLoString();
    }
    return new ConsLoString(str.substring(0, 1), strToSeq(str.substring(1)));
  }
  
  String proteinsToString(ILoLoString proteins) {
    if (proteins instanceof MtLoLoString) {
      return "";
    }
    else {
      String protein = proteinToString(proteins.first()).substring(1);
      return " " + protein + this.proteinsToString(proteins.rest());
    }
  }
  
  String proteinToString(ILoString protein) {
    if (protein instanceof MtLoString) {
      return " ";
    }
    else {
      return "," + protein.first() + this.proteinToString(protein.rest());
    }
  }
  
  // generate example sequences
  ILoString seq1 = strToSeq("ACAGAUAG");
  ILoString seq2 = strToSeq("ACAAAGUAGUUG");
  ILoString seq3 = strToSeq("ACAGAGAAGUAGUUGGAUUAGGAU");
  ILoString seq4 = strToSeq("GU");
  ILoString seq5 = strToSeq("GUAUAGCA");
  
  // translate example sequences
  ILoLoString seq1Trans = seq1.translate();
  ILoLoString seq2Trans = seq2.translate();
  ILoLoString seq3Trans = seq3.translate();
  ILoLoString seq4Trans = seq4.translate();
  ILoLoString seq5Trans = seq5.translate();
  
  // test Translate function
  boolean testTranslate(Tester t) {
    return t.checkExpect(proteinsToString(seq1Trans), " ACA,GAU ")
        && t.checkExpect(proteinsToString(seq2Trans), " UUG  ACA,AAG ")
        && t.checkExpect(proteinsToString(seq3Trans), " GAU  UUG,GAU  ACA,GAG,AAG ")
        && t.checkExpect(proteinsToString(seq4Trans), "")
        && t.checkExpect(proteinsToString(seq5Trans), " GUA ");
  }
  
  // test translateH function
  boolean testTranslateH(Tester t) {
    ILoString test1Cons = new ConsLoString("A", new MtLoString());
    String test1 = this.proteinsToString(test1Cons.translateH(new MtLoLoString(), new MtLoString(), "GU"));
    return t.checkExpect(test1, " GUA ");
  }
  
  // test appendToEnd
  boolean testAppendToEnd(Tester t) {
    ILoString test1Cons = new ConsLoString("U", new ConsLoString("A", new MtLoString()));
    ILoString test2Cons = new ConsLoString("A", new MtLoString());
    ILoString test3Cons = new MtLoString();
    return t.checkExpect(test1Cons.appendToEnd("G").rest().rest().first(), "G")
        && t.checkExpect(test2Cons.appendToEnd("C").rest().first(), "C")
        && t.checkExpect(test3Cons.appendToEnd("A").first(), "A");
  }
  
  // test updateCodon
  boolean testUpdateCodon(Tester t) {
    ConsLoString test1Cons = new ConsLoString("U", new MtLoString());
    return t.checkExpect(test1Cons.updateCodon("AA"), "AAU");
  }
  
  // test AppendTo
  boolean testAppendTo(Tester t) {
    ILoString seq1Test = this.strToSeq("ACA");
    ILoLoString test1 = seq1Test.appendTo(seq1Trans);
    ILoString mtTest = new MtLoString();
    ILoLoString test2 = mtTest.appendTo(seq5Trans);
    return t.checkExpect(this.proteinToString(test1.first()), ",A,C,A ")
        && t.checkExpect(this.proteinToString(test2.first()), ",GUA ");
  }
}