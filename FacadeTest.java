/*
A Structural Pattern.

Separate the construction of a complex object from its representation 
so that the same construction process can create different representations.
*/

import java.util.Hashtable;
import java.util.Date;

public class FacadeTest { 

    public static void main(String[] args){
		System.out.println("-------------- FACADE ---------------");

		String[] names = {"John Herman", "Eliza Herman", "Josh Herman"};
		TravelAgent tAgent = new TravelAgent();
		tAgent.bookTravel(names, new Date("09/12/2005"), new Date("09/18/2005"), "CHG", "SFO");
	}
}


// Facade
class TravelAgent {

	AccumodationArranger acc;
	TicketMaker tic;
	TravelPlanner pln;

	TravelAgent(){
		tic = new TicketMaker();
		acc = new AccumodationArranger();
		pln = new TravelPlanner();
	}

	public void bookTravel(String[] names, Date sdt, Date edt, String org, String dest){
		tic.make(names, sdt, edt, org, dest);
		System.out.println();
		acc.arrange(names, sdt, edt, dest);
		System.out.println();
		pln.plan(sdt, edt, dest);
		System.out.println();
	}
}

// Subsystem component 1
class TicketMaker {

	public void make(String[] names, Date sdt, Date edt, String org, String dest) {
	    System.out.println("Made "+names.length+" tickets FROM "+org+" TO "+dest+" on "+sdt.toString());
	    System.out.println("Also "+names.length+" return tickets are made FROM "+dest+" TO "+org+" on "+edt.toString());
	}
}

// Subsystem component 2
class AccumodationArranger {

	Hashtable hotelsInCities = new Hashtable();
	
	AccumodationArranger(){
		hotelsInCities.put("NY", new String[]{"Hotel Reviera", "Hotel Crown", "Hotel Paradise", "Hyatt Regency", "Shreton Hotel", "Ramada Inn"});
		hotelsInCities.put("CHG", new String[]{"Homested Villege", "Extended Stay America", "Days Inn", "Hyatt Regency", "Shreton Hotel", "Ramada Inn"});
		hotelsInCities.put("SFO", new String[]{"Extended Stay America", "Days Inn", "Hyatt Regency", "Shreton Hotel", "Ramada Inn"});
	}
	
	public void arrange(String[] names, Date sdt, Date edt, String dest) {
		String[] hotels = (String[])hotelsInCities.get(dest);

		int r=(int)(Math.random()*10);
		while(r > hotels.length-1){
			r=(int)(Math.random()*10);
		}

		System.out.println( "Accumodation is arranged for the persons below in \""+hotels[r]+"\"");
		for(int i=0; i<names.length; i++){
			if(i == names.length-1){
			    System.out.print("And ");
			}
		    System.out.println(names[i]);
		}
	}
}


// Subsystem component 3
class TravelPlanner {
	
	Hashtable placesInCities = new Hashtable();
	
	TravelPlanner(){
		placesInCities.put("CHG", new String[]{"Lincon Zoo", "Art Musium", "Sea World", "Navy Piere", "Science Musium"});
		placesInCities.put("NY", new String[]{"Kenedy Park", "Empire State", "WTC", "Times Square"});
		placesInCities.put("SFO", new String[]{"Golden Gate", "Mistery Spot", "17 Miles", "Piere 9"});
	}

	public void plan(Date sdt, Date edt, String dest) {
		String[] places = (String[])placesInCities.get(dest);
		System.out.println("The trip plan in "+dest+" is as below.");
		System.out.println("Starting from "+sdt.toString());
		System.out.println("Ending on "+edt.toString());
		long duration = edt.getTime() - sdt.getTime();
		int d = 1;
		for(int i=0; i<places.length; i++){
			if(d == duration){
				break;
			}
			System.out.println("\""+places[i]+"\" on day "+(d++));
		}
	}
}
