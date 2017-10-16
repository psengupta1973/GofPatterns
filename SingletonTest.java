/*
A Creational Pattern.

Ensure a class has only one instance and provide a global point of access to it.  
*/

public class SingletonTest { 

    public static void main(String[] args){
		
		System.out.println("-------------- SINGLETON ---------------");

		Singleton s1 = Singleton.getInstance();
		System.out.println("s1: "+s1.getName());

		Singleton s2 = Singleton.getInstance();
		System.out.println("s2: "+s2.getName());
		
		if(s1 == s2){
			System.out.println("s1 and s2 are same instances... changing String of s2 to 'Partha'");
		}
		
		s2.setName("Partha");
		System.out.println("s2: "+s2.getName());
		System.out.println("s1: "+s1.getName());
	}
}


//Singleton
class Singleton { 

    private static Singleton myInstance = null;
    private String name = null;

    private Singleton(String str) {
		name = str;
    }

    public static Singleton getInstance() {
        if (myInstance == null){
			myInstance = new Singleton("InitialSingletonString");
		}
		return myInstance;
    }

	public String getName(){
		return name;
	}
	
	public void setName(String str){
		name = str;
	}
}
