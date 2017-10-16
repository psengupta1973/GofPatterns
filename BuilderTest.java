/*
A Creational Pattern.

Separate the construction of a complex object from its representation 
so that the same construction process can create different representations.
*/

import java.util.Hashtable;

public class BuilderTest { 

    public static void main(String[] args){
	
		System.out.println("-------------- BUILDER ---------------");
		// Create shop and vehicle builders
		Shop shop = new Shop();
		VehicleBuilder b1 = new ScooterBuilder();
		VehicleBuilder b2 = new CarBuilder();
		VehicleBuilder b3 = new MotorCycleBuilder();

		// Construct and display vehicles
		shop.construct( b1 );
		b1.getVehicle().show();

		shop.construct( b2 );
		b2.getVehicle().show();

		shop.construct( b3 );
		b3.getVehicle().show();
	}
}


// Director
class Shop {
	Shop() {
	}
	public void construct(VehicleBuilder vehicleBuilder){
		vehicleBuilder.buildFrame();
		vehicleBuilder.buildEngine();
		vehicleBuilder.buildWheels();
		vehicleBuilder.buildDoors();
	}
}


// Builder
abstract class VehicleBuilder {

	protected Vehicle vehicle;

	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	abstract public void buildFrame();
	abstract public void buildEngine();
	abstract public void buildWheels();
	abstract public void buildDoors();
}


// "ConcreteBuilder1"
class MotorCycleBuilder extends VehicleBuilder {

  public void buildFrame() {
    vehicle = new Vehicle( "MotorCycle" );
    vehicle.setFrame("MotorCycle Frame");
  }

  public void buildEngine() {
    vehicle.setEngine("500 cc");
  }

  public void buildWheels() {
    vehicle.setNumOfWheels(2);
  }

  public void buildDoors() {
    vehicle.setNumOfDoors(0);
  }
}


// "ConcreteBuilder2"
class CarBuilder extends VehicleBuilder {

  public void buildFrame() {
    vehicle = new Vehicle( "Car" );
    vehicle.setFrame("Car Frame");
  }

  public void buildEngine() {
    vehicle.setEngine("2500 cc");
  }

  public void buildWheels() {
    vehicle.setNumOfWheels(4);
  }

  public void buildDoors() {
	vehicle.setNumOfDoors(4);
  }
}


// "ConcreteBuilder3"
class ScooterBuilder extends VehicleBuilder {

	public void buildFrame() {
		vehicle = new Vehicle( "Scooter" );
		vehicle.setFrame("Scooter Frame");
	}

	public void buildEngine() {
		vehicle.setEngine("300 cc");
	}

	public void buildWheels() {
		vehicle.setNumOfWheels(2);
	}

	public void buildDoors() {
		vehicle.setNumOfDoors(0);
	}
}


// "Product"
class Vehicle {

	String type = "";
	String frame = "";
	String engine = "";
	int numOfWheels = 0;
	int numOfDoors = 0;

	Vehicle( String type ) {
		this.type = type;
	}

	public String getFrame() {
		return frame;
	}
	public void setFrame(String f){
		frame = f;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String e){
		engine = e;
	}
	public int getNumOfWheels() {
		return numOfWheels;
	}
	public void setNumOfWheels(int w){
		numOfWheels = w;
	}
	public int getNumOfDoors() {
		return numOfWheels;
	}
	public void setNumOfDoors(int d){
		numOfDoors = d;
	}

	public void show() {
		System.out.println( "---------------------------");
		System.out.println( "Vehicle Type: "+ type );
		System.out.println( " Frame : " + frame );
		System.out.println( " Engine : "+ engine);
		System.out.println( " #Wheels: "+ numOfWheels);
		System.out.println( " #Doors : "+ numOfDoors);
	}
}
