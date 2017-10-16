/*
A Structural Pattern.

The Composite is intended to allow you to compose tree structures 
to represent whole-part hierarchies so that clients can treat 
individual objects and compositions of objects uniformly. 
We often build tree structures where some nodes are containers of 
other nodes, and other nodes are "leaves." Instead of creating separate 
client code to manage each type of node, Composite lets a client 
work with either using the same code.
*/

import java.util.ArrayList;

public class CompositeTest { 

    public static void main(String[] args){
		System.out.println("-------------- COMPOSITE ---------------");

		EmailAddress[] emails = new EmailAddress[6];

		emails[0] = new IndividualEmail("P. Sengupta", "psengupta@siemens.com");
		emails[1] = new IndividualEmail("S. Dasgupta", "sdasgupta@siemens.com");
		emails[2] = new IndividualEmail("K. Dutta", "kdutta@siemens.com");

		emails[3] = new GroupEmail("Facilities Group");
		emails[3].add(new IndividualEmail("D. Pal", "dpal@siemens.com"));
		EmailAddress dBasak = new IndividualEmail("D. Basak", "dbasak@siemens.com");
		emails[3].add(dBasak);
		emails[3].add(new IndividualEmail("P. Mitra", "pmitra@siemens.com"));
		
		emails[4] = new IndividualEmail("A. Deb", "adeb@siemens.com");
		emails[5] = new IndividualEmail("D. Mukherjee", "dmukherjee@siemens.com");

		for(int i=0; i<emails.length; i++){
			emails[i].sendMail();
		}
		System.out.println("\n--------- After DELETING D. Basak -------------");

		emails[3].delete(dBasak);

		for(int i=0; i<emails.length; i++){
			emails[i].sendMail();
		}
	}
}

// Component
abstract class EmailAddress {
	protected String name;
	public abstract void sendMail();
	public abstract void add(EmailAddress e);
	public abstract void delete(EmailAddress e);
	public abstract EmailAddress getIndividual(int i);
};

// Composite
class GroupEmail extends EmailAddress {
	
	ArrayList emails = new ArrayList();

	GroupEmail(String n){
		name = n;
	}

	public void add(EmailAddress e){
		emails.add(e);
	}

	public void delete(EmailAddress e){
		emails.remove(e);
	}

	public EmailAddress getIndividual(int i){
		return (EmailAddress)emails.get(i);
	}

	public void sendMail(){
		System.out.println("-----   Sending mail to GROUP: "+name+"  -------");
		for(int i=0; i<emails.size(); i++){
			getIndividual(i).sendMail();
		}
		System.out.println("----------   End of GROUP  -----------");
	}
};


// Leaf
class IndividualEmail extends EmailAddress {

	String email = "";
	
	IndividualEmail(String n, String e){
		name = n;
		email = e;
	}

	public void add(EmailAddress e){}

	public void delete(EmailAddress e){}

	public EmailAddress getIndividual(int i){
		return this;
	}

	public void sendMail(){
		System.out.println("Sending mail to INDIVIDUAL: "+email);
	}
};

