/*
A Structural Pattern.

Ordinarily, an object inherits behavior from its subclasses. 
The decorator pattern is intended to give you a way to extend 
the behavior of an object, and you can also dynamically compose 
an object's behavior. Decorator allows you to do so without 
the need to create new subclasses.
*/


public class DecoratorTest { 

    public static void main(String[] args){
		System.out.println("-------------- DECORATOR ---------------");

		Phone phone = new Mobile("Nokia", "3310", "GSM");
		phone.call("9830006243");
		// add Airtel connection
		ConnectedPhone cPhone = new AirtelPhone();
		cPhone.setPhone(phone);
		cPhone.call("9830006243");
		// add Hutch connection
		cPhone = new HutchPhone();
		cPhone.setPhone(phone);
		cPhone.call("9830006243");
	}
}


// Component
abstract class Phone {
	
	String make;
	String model;

	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}

	public abstract void call(String no);
}

// Concrete component
class Mobile extends Phone {

	String technology;
	
	Mobile(String mk, String md, String tch){
		make = mk;
		model = md;
		technology = tch;
	}
	
	public void call(String no) {
		System.out.println("Trying to call "+no+" from \""+make+" "+model+"\" mobile with "+technology+" technology.");
		System.out.println("<------------Can not call without connection.----------> :(");
	}
}


// Decorator
class ConnectedPhone extends Phone {
	Phone phone;
	
	public Phone getPhone(){
		return phone;
	}
	public void setPhone(Phone phn){
		phone = phn;
	}
	
	public void call(String no) {
		phone.call(no);
	}
}

// Concrete decorator 1
class AirtelPhone extends ConnectedPhone {
	public void call(String no){
		System.out.println("Calling "+no+" from \""+phone.getMake()+" "+phone.getModel()+"\" mobile...");
		System.out.println("Connected using Airtel :)");
	}
}

// Concrete decorator 2
class HutchPhone extends ConnectedPhone {
	public void call(String no){
		System.out.println("Calling "+no+" from \""+phone.getMake()+" "+phone.getModel()+"\" mobile...");
		System.out.println("Connected using Hutch :)");
	}
}
