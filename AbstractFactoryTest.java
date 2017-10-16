/*
A Creational Pattern.

Provide an interface for creating families of related or dependent objects 
without specifying their concrete classes.
*/

public class AbstractFactoryTest {
	
	public static void main(String[] args) throws Exception{

		System.out.println("-------------- ABSTRACT_FACTORY ---------------");
		
		Factory factory = new IBMFactory();
		
		Computer computer = factory.createComputer();
		computer.boot();
		Software software = factory.createSoftware();
		software.start();

		factory = new AppleFactory();
		
		computer = factory.createComputer();
		computer.boot();
		software = factory.createSoftware();
		software.start();
	}

};

// Factory
abstract class Factory {
	public abstract Computer createComputer();
	public abstract Software createSoftware();
};

// factory 1
class IBMFactory extends Factory {

	public Computer createComputer(){
		return new ThinkCenter("ThinkCenter Turbo 123", "256MB", "40GB", "3 Ghz");
	}

	public Software createSoftware(){
		return new Websphere("Websphere Application Server 5.1.1", "12123-324-23434-54545");
	}
};


// Concrete factory 2
class AppleFactory extends Factory {
	
	public Computer createComputer(){
		return new Machintosh("Machintosh Paranoid 77Z", "512MB", "60GB", "3.6 Ghz");
	}

	public Software createSoftware(){
		return new MacOS("MacOS 7.2.1", "22-33-NMCNNJ-33290");
	}
};


// Product

abstract class Computer {
	String ram = null;
	String hdd = null;
	String speed = null;
	String model = null;
	public abstract void boot();
};

// Concrete product 1
class Machintosh extends Computer {

	Machintosh(String m, String r, String h, String s){
		model = m;
		ram = r;
		hdd = h;
		speed = s;
	}
	
	public void boot(){
		System.out.println("This is a Machintosh Computer by Apple ");
		System.out.println("Model: "+model);
		System.out.println("RAM: "+ram);
		System.out.println("HDD: "+hdd);
		System.out.println("CPU Speed: "+speed);
	}
};

// Concrete product 2
class ThinkCenter extends Computer { 

	ThinkCenter(String m, String r, String h, String s){
		model = m;
		ram = r;
		hdd = h;
		speed = s;
	}

	public void boot(){
		System.out.println(" This is a ThinkCenter by IBM ");
		System.out.println("Model: "+model);
		System.out.println("RAM: "+ram);
		System.out.println("HDD: "+hdd);
		System.out.println("CPU Speed: "+speed);
	}
};


abstract class Software {
	String name = null;
	String license = null;

	public abstract void start();
};

// Concrete product 3
class Websphere extends Software {

	Websphere(String n, String l){
		name = n;
		license = l;
	}

	public void start(){
		System.out.println("Websphere by IBM is starting up ...");
		System.out.println("Name: "+name);
		System.out.println("License: "+license);
	}
};

// Concrete product 4
class MacOS extends Software {

	MacOS(String n, String l){
		name = n;
		license = l;
	}
	public void start(){
		System.out.println("MacOS by Apple is starting up ...");
		System.out.println("Name: "+name);
		System.out.println("License: "+license);
	}
};
