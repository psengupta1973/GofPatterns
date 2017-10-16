/*
A Behavioral Pattern.

The Visitor pattern uses an external class to act on data in other classes. 
This is a useful approach to you when you have a polymorphic operation that 
cannot reside in the class hierarchy. Visitor is also a useful way to extend 
the behavior of a class hierarchy without the need to alter existing classes 
or to implement the new behavior in every subclass that requires it.
 
*/
import java.util.ArrayList;


public class VisitorTest {

	public static void main(String[] args){

		System.out.println("-------------- VISITOR ---------------");

		Auditor iso = new ISOAuditor("Aru Bag");
		Auditor sei = new SEIAuditor("Nakul Bera");
		
		ArrayList procs = new ArrayList();
		String[] deliveryDocs = new String[]{"DeliveryRequest", "DeliverySchedule", "CustomerResponse"};
		procs.add(new Delivery("SISL-Delivery-process", deliveryDocs));
		String[] purchaseDocs = new String[]{"VendorRegister", "PurchaseRegister", "ChallanRegister"};
		procs.add(new Purchase("SISL-Purchase-process", purchaseDocs));

		Company sisl = new Company("SISL", procs);
		sisl.invite(iso);
		sisl.invite(sei);
	}
}



// Abstract Visitor
abstract class Auditor {
	String name;
	public abstract void auditPurchase(CorporateProcess proc);
	public abstract void auditDelivery(CorporateProcess proc);
};

// Concrete Visitor 1
class ISOAuditor extends Auditor {
	ISOAuditor(String nm){
		name = nm;
	}
	public void auditPurchase(CorporateProcess proc){
		System.out.println(name+" is auditing "+proc.getName()+" on behalf of ISO.");
		System.out.print("Checking purchase docs [");
		String[] docs = proc.getDocs();
		for (int i=0; i<docs.length; i++)
		{
			System.out.print(docs[i]+", ");
		}
		System.out.println("]\n");
	}
	public void auditDelivery(CorporateProcess proc){
		System.out.println(name+" is auditing "+proc.getName()+" on behalf of ISO.");
		System.out.print("Checking delivery docs [");
		String[] docs = proc.getDocs();
		for (int i=0; i<docs.length; i++)
		{
			System.out.print(docs[i]+", ");
		}
		System.out.println("]\n");
	}
};

// Concrete Visitor 2
class SEIAuditor extends Auditor {
	SEIAuditor(String nm){
		name = nm;
	}
	public void auditPurchase(CorporateProcess proc){
		System.out.println(name+" is auditing "+proc.getName()+" on behalf of SEI.");
		System.out.print("Checking purchase docs [");
		String[] docs = proc.getDocs();
		for (int i=0; i<docs.length; i++)
		{
			System.out.print(docs[i]+", ");
		}
		System.out.println("]\n");
	}
	public void auditDelivery(CorporateProcess proc){
		System.out.println(name+" is auditing "+proc.getName()+" on behalf of SEI.");
		System.out.print("Checking delivery docs [");
		String[] docs = proc.getDocs();
		for (int i=0; i<docs.length; i++)
		{
			System.out.print(docs[i]+", ");
		}
		System.out.println("]\n");
	}
};



// Abstract Element
abstract class CorporateProcess {
	String name;
	String[] docs;
	public String getName(){
		return name;
	}
	public String[]	getDocs(){
		return docs;
	}
	public abstract void accept(Auditor auditor);
};

// Concrete Element 1
class Delivery extends CorporateProcess {
	Delivery(String nm, String[] arr){
		name = nm;
		docs = arr;
	}
	public void accept(Auditor auditor){
		auditor.auditDelivery(this);
	}
};

// Concrete Element 2
class Purchase extends CorporateProcess {
	Purchase(String nm, String[] arr){
		name = nm;
		docs = arr;
	}
	public void accept(Auditor auditor){
		auditor.auditPurchase(this);
	}
};



// Object Structure
class Company {
	String name;
	ArrayList procs = new ArrayList();
	
	Company(String nm, ArrayList p){
		name = nm;
		procs = p;
	}

	public String getName(){
		return name;
	}
	public void invite(Auditor auditor){
		CorporateProcess proc;
		for (int i=0; i<procs.size(); i++)
		{
			proc = (CorporateProcess)procs.get(i);
			proc.accept(auditor);
		}
	}
};
