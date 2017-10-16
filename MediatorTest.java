/*
A Behavioral Pattern.

Define an object that encapsulates how a set of objects interact. 
Mediator promotes loose coupling by keeping objects from referring 
to each other explicitly, and it lets you vary their interaction independently. 
*/

import java.util.ArrayList;


public class MediatorTest {
    public static void main(String[] args){
		System.out.println("-------------- MEDIATOR ---------------");

		ChatRoom room = new PublicRoom("Calcutta");
		
		Chatter c1 = new UnregisteredMember("Partha");
		Chatter c2 = new UnregisteredMember("Apu");
		Chatter c3 = new RegisteredMember("Shyam", "shyam@mail.com");

		room.register(c1);
		room.register(c2);
		room.register(c3);

		c1.send("Apu", "Hi! How are you ?");
		c2.send("Shyam", "What's ur a/s/l ?");
		c3.send("Partha", "Where do u stay ?");
	}
}


// Abstract Mediator
abstract class ChatRoom {
	String name;
	ArrayList chatters = new ArrayList();

	public String getName() {
		return name;
	}
	public void setName(String nm) {
		name = nm;
	}
	public void register(Chatter chatter){
		chatter.setRoom(this);
		chatters.add(chatter);
	}
	public abstract void send(String from, String to, String message);
};


// Concrete Mediator 1
class PublicRoom extends ChatRoom { 

	PublicRoom(String nm){
		name = nm;
	}

	public void send(String from, String to, String message) {
		Chatter c;
		for(int i=0; i<chatters.size(); i++){
			c = (Chatter) chatters.get(i);
			if(c.getName().equals(to)){
				c.receive(from, message);
				break;
			}
		}
	}
};


// Concrete Mediator 2
class PrivateRoom extends ChatRoom { 

	PrivateRoom(String nm){
		name = nm;
	}

	public void send(String from, String to, String message) {
		Chatter c;
		for(int i=0; i<chatters.size(); i++){
			c = (Chatter) chatters.get(i);
			if(c.getName().equals(to)){
				c.receive(from, message);
				break;
			}
		}
	}
};


// Abstract Colleague
class Chatter {
	String name;
	ChatRoom room;
	
	Chatter(String nm){
		name = nm;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String nm) {
		name = nm;
	}
	public ChatRoom getRoom() {
		return room;
	}
	public void setRoom(ChatRoom rm) {
		room = rm;
	}

	public void send(String to, String message )  {
		room.send( name, to, message );
	}
	public void receive(String from, String message) {
		System.out.println(from+" to "+this.name+": '"+message+"'");
	}
};


// Concrete Colleague 1
class UnregisteredMember extends Chatter {

	UnregisteredMember(String nm){
		super(nm);
	}

	public void send(String to, String message )  {
		room.send( name, to, message );
	}

	public void receive(String from, String message) {
		System.out.println(from+" to "+this.name+": '"+message+"'");
	}
};


// Concrete Colleague 2
class RegisteredMember extends Chatter { 
	String email;

	RegisteredMember(String nm, String mail){
		super(nm);
		email = mail;
	}

	public void send(String to, String message )  {
		room.send( name, to, message );
	}

	public void receive(String from, String message) {
		System.out.println(from+" to "+this.name+": '"+message+"'");
	}
};
