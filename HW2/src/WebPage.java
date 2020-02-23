import tester.*;

// represents a webpage
class WebPage {
  String title;
  String url;
  ILoItem items;
  
  WebPage(String title, String url, ILoItem items) {
    this.title = title;
    this.url = url;
    this.items = items;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.title -- String
   * this.url   -- String
   * this.items -- ILoItem
   * 
   * Methods: 
   * this.totalImageSize() -- int
   * this.textLength()     -- int
   * this.images()         -- String
   * 
   * Methods of fields:
   * items.totalImageSize();
   * items.textLength();
   * items.images();
   */
  
  // computes the total image size of the website including any linked pages
  int totalImageSize() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.items.totalImageSize();
  }
  
  // computes the total text length of the website including linked pages, and titles (not urls)
  int textLength() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.title.length() + this.items.textLength();
  }
  
  // returns a string listing all the image file names with extensions, separated by comma and space
  String images() {
    // TEMPLATE: SEE CLASS TEMPLATE
    if (this.items.images().length() > 1) {
      return this.items.images().substring(2);      
    }
    else {
      return "";
    }
    /*
     * htdp.tiff appears twice because it is linked from two different pages, and one of 
     * those pages is linked to the original fundiesWP page. So it appears as a resource
     * on the HtDP page as well as the OOD page, indirectly. This duplication also occurs
     * with the other recursive/self-referential functions for the same reason, except it
     * occurs with numbers, so the sums are larger than they should be.
     */
  }
}

// represents a list of Item
interface ILoItem {
  // computes the total image size in this list of items
  int totalImageSize();
  
  // computes the total text length in this list of items
  int textLength();
  
  // returns a string listing all the image file names and types, including those on linked pages
  String images();
}

// represents an empty list of Item
class MtLoItem implements ILoItem {
  MtLoItem() {}
  
  public int totalImageSize() {
    return 0;
  }
  
  public int textLength() {
    return 0;
  }
  
  public String images() {
    return "";
  }
}

// represents a non-empty list of Item
class ConsLoItem implements ILoItem {
  IItem first;
  ILoItem rest;
  
  ConsLoItem(IItem first, ILoItem rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.first -- IItem
   * this.rest  -- ILoItem
   * 
   * Methods:
   * this.totalImageSize() -- int
   * this.textLength()     -- int
   * this.images()         -- String
   * 
   * Methods of fields:
   * this.first.imgSize()    -- int
   * this.first.textLength() -- int
   * this.first.imgString()  -- String
   */
  
  public int totalImageSize() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.first.imgSize() + this.rest.totalImageSize();
  }
  
  public int textLength() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.first.textLength() + this.rest.textLength();
  }
  
  public String images() {
    // TEMPLATE: SEE CLASS TEMPLATE
    if (this.first.imgString().length() == 0) {
      return this.rest.images();
    } 
    else {
      return ", " + this.first.imgString() + this.rest.images();
    }
  }
}

// represents a webpage Item
interface IItem {
  // computes the total image size of the item
  // if an image, the size of the image; if a link, the img size of the linked page
  // otherwise, 0
  int imgSize();
  
  // computes the total text length (displayed text only)
  int textLength();
  
  // returns a string with the image's filename/type
  // if a link, returns the same function called recursively on the linked page
  String imgString();
}

class Text implements IItem {
  String contents;
  
  Text(String contents) {
    this.contents = contents;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.contents -- String
   * 
   * Methods:
   * this.imgSize()    -- int
   * this.textLength() -- int
   * this.imgString()  -- String
   */
  
  public int imgSize() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return 0;
  }
  
  public int textLength() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.contents.length();
  }
  
  public String imgString() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return "";
  }
}

class Image implements IItem {
  String fileName;
  int size;
  String fileType;
  
  Image(String fileName, int size, String fileType) {
    this.fileName = fileName;
    this.size = size;
    this.fileType = fileType;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.fileName -- String
   * this.size     -- int
   * this.fileType -- String
   * 
   * Methods:
   * this.imgSize()    -- int
   * this.textLength() -- int
   * this.imgString()  -- String
   */
  
  public int imgSize() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.size;
  }
  
  public int textLength() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.fileName.length() + this.fileType.length();
  }
  
  public String imgString() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.fileName + "." + this.fileType;
  }
}

class Link implements IItem {
  String name;
  WebPage page;
  
  Link(String name, WebPage page) {
    this.name = name;
    this.page = page;
  }
  
  /*
   * TEMPLATE
   * Fields:
   * this.name -- String
   * this.page -- WebPage
   * 
   * Methods:
   * this.imgSize()    -- int
   * this.textLength() -- int
   * this.imgString()  -- String
   * 
   * Methods of fields:
   * this.page.totalImageSize() -- int
   * this.page.textLength()     -- int
   * this.page.images()         -- String
   */
  
  public int imgSize() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.page.totalImageSize();
  }
  
  public int textLength() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.name.length() + this.page.textLength();
  }
  
  public String imgString() {
    // TEMPLATE: SEE CLASS TEMPLATE
    return this.page.images();
  }
}

class ExamplesWebPage {
  /*
   * text: "Hello!"
   * image1: name "cat", size 10, type "jpg"
   * image2: name "dog", size 15, type "png"
   * link1: name "About", page "about.html"
   * link2: name "Contact", page "contact.html"
   * link3: name "Log In", page "login.html"
   * link4: name "Sign Up", page "signup.html"
   * All of these are in a List of items on a webpage with title "My Website" and url "mysite.com"
   */
  ILoItem mt = new MtLoItem();
  WebPage about = new WebPage("About", "mysite.com/about", 
      new ConsLoItem(new Text("This is our about page"), mt));
  WebPage contact = new WebPage("Contact", "mysite.com/contact", 
      new ConsLoItem(new Text("Contact me by email: wohlers.w@husky.neu.edu"), mt));
  WebPage login = new WebPage("Login", "mysite.com/login", 
      new ConsLoItem(new Text("Login here:"), mt));
  
  IItem text1 = new Text("Hello!");
  IItem image1 = new Image("cat", 10, "jpg");
  IItem image2 = new Image("dog", 15, "png");
  IItem link1 = new Link("About", about);
  IItem link2 = new Link("Contact", contact);
  IItem link3 = new Link("Log In", login);
  IItem link4 = new Link("Sign Up", contact);
  ILoItem items1 = new ConsLoItem(text1, new ConsLoItem(image1, new ConsLoItem(image2, 
      new ConsLoItem(link1, new ConsLoItem(link2, new ConsLoItem(link3, 
          new ConsLoItem(link4, mt)))))));
  WebPage myWebsite = new WebPage("My Website", "mysite.com", items1);

  IItem htdp = new Text("How to Design Programs");
  IItem cover = new Image("htdp", 4300, "tiff");
  ILoItem items3 = new ConsLoItem(htdp, new ConsLoItem(cover, mt));
  WebPage HtDP = new WebPage("HtDP", "htdp.org", items3);

  IItem classy = new Text("Stay classy, Java");
  IItem future = new Link("Back to the Future", HtDP);
  ILoItem items4 = new ConsLoItem(classy, new ConsLoItem(future, mt));
  WebPage ood = new WebPage("OOD", "ccs.neu.edu/OOD", items4);
  
  IItem hsh = new Text("Home sweet home");
  IItem wvhLab = new Image("wvh-lab", 400, "png");
  IItem staff = new Text("The staff");
  IItem profs = new Image("profs", 240, "jpeg");
  IItem back = new Link("A Look Back", HtDP);
  IItem ahead = new Link("A Look Ahead", ood);
  ILoItem items2 = new ConsLoItem(hsh, new ConsLoItem(wvhLab, new ConsLoItem(staff,
      new ConsLoItem(profs, new ConsLoItem(back, new ConsLoItem(ahead, mt))))));
  WebPage fundiesWP = new WebPage("Fundies II", "ccs.neu.edu/Fundies2", items2);
  
  boolean testTotalImageSize(Tester t) {
    return t.checkExpect(myWebsite.totalImageSize(), 25) 
        && t.checkExpect(fundiesWP.totalImageSize(), 9240);
  }
  
  boolean testTextLength(Tester t) {
    return t.checkExpect(myWebsite.textLength(), 198) 
        && t.checkExpect(fundiesWP.textLength(), 182);
  }
  
  boolean testImages(Tester t) {
    return t.checkExpect(myWebsite.images(), "cat.jpg, dog.png") 
        && t.checkExpect(fundiesWP.images(), "wvh-lab.png, profs.jpeg, htdp.tiff, htdp.tiff");
  }
}