/*
A Structural Pattern.

The Proxy is intended to provide you with a way to use a surrogate 
or placeholder to another object in order to control access to it.
This pattern is useful for situations where object creation is a 
time consuming process and can make an application appear sluggish. 
A proxy object can provide the client with feedback while the object 
is being created. Proxy can also be used to provide a more sophisticated 
reference to an object. For example, the proxy object can implement 
additional logic on the objects behalf (security, remote procedure calls, 
an so forth).
*/

import java.util.Hashtable;

public class ProxyTest { 

    public static void main(String[] args){
		System.out.println("-------------- PROXY ---------------");

//		new AutoReceiver("HSBC").receive();
//		new AutoReceiver("PARK HOTEL").receive();
		new AutoReceiver("ICICI").receive();
	}
}


// Subject
abstract class CallReceiver implements Runnable {
	String name;
	String company;
	boolean busy = false;

	protected abstract void receive();

	public void run(){
		if(busy){
			System.out.println(name+": BUSY...");
			try{Thread.sleep(3000);}catch(Exception e){e.printStackTrace();}
		}
		else{
			receive();
		}
	}
	public boolean isBusy() {
		return busy;
	}
}

// Real subject
class CallOfficer extends CallReceiver {

	CallOfficer(String nm, String comp){
		name = nm;
		company = comp;
		int r=(int)(Math.random()*10);
		int rem = r / 3;
		if(rem != 0){
			busy = true;
		}
	}

	public void receive() {
		System.out.println(name+": Hi! This is "+name+" from "+company+". HOW MAY I HELP YOU?\n");
	}
}


// Proxy
class AutoReceiver extends CallReceiver {
	
	String[] officers = null;

	AutoReceiver(String comp){
		company = comp;
		if(company.equals("HSBC")){officers = new String[]{"Shelly", "Pritam", "Nirmala", "Arindam", "Vimal", "Joice"};}
		else{if(company.equals("ICICI")){officers = new String[]{"Jerry", "Nihal", "Mason", "Riya", "Anupama", "Merry"};}
		else{if(company.equals("PARK HOTEL")){officers = new String[]{"Simran", "Nisha", "Priyanka", "Mohan", "Vijay"};}}}
		System.out.println("Welcome to "+company+" Automated Call Receiving System. Please wait.");
	}
	
	public void receive() {
		CallOfficer officer = new CallOfficer(officers[0], company);
		System.out.println("Transfering to call officer...("+officers[0]+")");
		(new Thread(officer)).start();

		for(int i=1; (i<officers.length && officer.isBusy()); i++){
			System.out.println("Please wait. All our call-officers are busy.");
			officer = new CallOfficer(officers[i], company);
			System.out.println("Transfering to call officer...("+officers[i]+")");
			(new Thread(officer)).start();
			if(i+1 == officers.length){
				i = -1;
			}
		}
	}
}
