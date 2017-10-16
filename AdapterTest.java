/*
A Structural Pattern.

The Adapter is intended to provide a way for a client 
to use an object whose interface is different from the 
one expected by the client, without having to modify either. 
This pattern is suitable for solving issues that arise, 
for example, when: 1) you want to replace one class with another 
and the interfaces do not match, and 2) you want to create a class 
that can interact with other classes without knowing their interfaces at design time.
*/

public class AdapterTest { 
    public static void main(String[] args){
		System.out.println("-------------- ADAPTER ---------------");
		PowerSupplier ps = new USVoltageAdapter();
		ps.supply();
	}
}

// target
interface PowerSupplier {
	public void supply();
};


//adapter
class USVoltageAdapter implements PowerSupplier { 
	
	AsianPowerSocket asian = new AsianPowerSocket();

	public void supply(){
		asian.getPower();
		System.out.println("And is being stepped down to 120 ~ 110");
	}
};


// adaptee
class AsianPowerSocket {
	public void getPower(){
		System.out.println("240 ~ 220 is being supplied...");
	}
};
