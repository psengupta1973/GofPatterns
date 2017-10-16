/*
A Behavioral Pattern.

The Command pattern is intended to encapsulate a request as an object. 
For example, consider a window application that needs to make requests of 
objects responsible for the user interface. The client responds to input 
from the user by creating a command object and the receiver. It then passes 
this object to an Invoker object, which then takes the appropriate action. 
This allows the client to make requests without having to know anything about 
what action will take place. In addition, you can change that action without 
affecting the client. 
*/

public class CommandTest { 
    public static void main(String[] args){
		System.out.println("-------------- COMMAND ---------------");
		FileMenu fMenu = new FileMenu();

		FileOpener opener = new FileOpener();
		Command cmd = new NewFileOpenCommand(opener);
		
		fMenu.setCommand(cmd);
		fMenu.click();
	}
}


// Command
abstract class Command {
	FileOpener opener;
	public abstract void execute();
};


// Concrete Command
class NewFileOpenCommand extends Command {
	
	FileOpener opener;

	NewFileOpenCommand(FileOpener o){
		opener = o;
	}
	public void execute(){
		System.out.println("New File Open command is being executed... ");
		opener.openFile();
	}
};


// Invoker
class FileMenu {
	
	Command command;

	public void setCommand(Command cmd){
		command = cmd;
	}
	public Command getCommand(){
		return command;
	}

	public void click(){
		System.out.println("Clicked on File menu.");
		command.execute();
	}
};


//Receiver
class FileOpener { 
	FileOpener (){
	}
	public void openFile(){
		System.out.println("Opening a new file.");
	}
};

