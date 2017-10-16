/*
A Creational Pattern.

Define an interface for creating an object, but let subclasses decide which class to instantiate. 
Factory Method lets a class defer instantiation to subclasses.
*/
import java.util.ArrayList;

public class FactoryMethodTest {
	
	public static void main(String[] args) throws Exception{
		System.out.println("-------------- FACTORY_METHOD ---------------");

		Document[] docs = new Document[ 2 ];

		// Note: constructors call Factory Method
		docs[0] = new Resume();
		docs[1] = new Report();

		// Display document pages
		for(int i=0; i<docs.length; i++){
			System.out.println("\n"+docs[i] + " Pages...");
			ArrayList pages = docs[i].getPages();
			for(int j=0; j<pages.size(); j++){
				System.out.println( " " + (Page)pages.get(j) );
			}
		}
	}
};


// "Product"
abstract class Page {
	public String toString(){
		return this.getClass().getName();
	}
}

// "ConcreteProduct 1"
class SkillsPage extends Page {
}

// "ConcreteProduct 2"
class EducationPage extends Page {
}

// "ConcreteProduct 3"
class ExperiencePage extends Page {
}

// "ConcreteProduct 4"
class IntroductionPage extends Page {
}

// "ConcreteProduct 5"
class ResultsPage extends Page {
}

// "ConcreteProduct 6"
class ConclusionPage extends Page {
}

// "ConcreteProduct 7"
class SummaryPage extends Page {
}

// "ConcreteProduct 8"
class BibliographyPage extends Page {
}


// "Creator"
abstract class Document {
	protected ArrayList pages = new ArrayList();

	Document(){
		this.createPages();
	}

	public ArrayList getPages(){
		return pages;
	}

	public String toString(){
		return this.getClass().getName();
	}

	// Factory Method
	abstract public void createPages();
}


// "ConcreteCreator 1"
class Resume extends Document {

  // Factory Method implementation
  public void createPages() {
    pages.add( new SkillsPage() );
    pages.add( new EducationPage() );
    pages.add( new ExperiencePage() );
  }
}


// "ConcreteCreator 2"
class Report extends Document {

  // Factory Method implementation
  public void createPages() {
    pages.add( new IntroductionPage() );
    pages.add( new ResultsPage() );
    pages.add( new ConclusionPage() );
    pages.add( new SummaryPage() );
    pages.add( new BibliographyPage() );
  }
}
