/*
A Behavioral Pattern.

Client code often needs to record the current state of an object, 
without being interested in the actual data values (for example, 
supporting undo operations). To support this behavior, we can have 
the object record its internal data in a helper class called a Memento, 
and the client code can treat this object like a black box for storing 
its current state. Then, at some later point in time, the client can pass 
the Memento back into the object, to restore it to a previous state. 
The Memento provides you with a way to capture and externalize an object's 
internal state so that it can be restored at a later time. It does this by 
violating encapsulation and without making the object responsible for this 
capability itself.
 
*/

public class MementoTest {

	public static void main(String[] args){
		System.out.println("-------------- MEMENTO ---------------");

		AnyDocument doc = new AnyDocument("Partha.doc", "white", "black");
		DocumentTracker tracker = new DocumentTracker();
		
		tracker.setDocShot(doc.save());
		System.out.println(doc.getTitle()+" has been saved with '"+doc.getBackColor().toUpperCase()+"' backgound and '"+doc.getForeColor().toUpperCase()+"' foreground.");

		doc.setBackColor("yellow");
		doc.setForeColor("red");
		System.out.println(doc.getTitle()+" has been modified with '"+doc.getBackColor().toUpperCase()+"' backgound and '"+doc.getForeColor().toUpperCase()+"' foreground.");

		doc.restore(tracker.getDocShot());
		System.out.println(doc.getTitle()+" has been restored with '"+doc.getBackColor().toUpperCase()+"' backgound and '"+doc.getForeColor().toUpperCase()+"' foreground.");
	}
}



// Originator
class AnyDocument {

	String title;
	String bgColor;
	String fgColor;

	AnyDocument(String t, String bg, String fg){
		title = t;
		bgColor = bg;
		fgColor = fg;
	}
	
	public String getTitle(){
		return title;
	}
	public String getBackColor(){
		return bgColor;
	}
	public String getForeColor(){
		return fgColor;
	}
	public void setTitle(String t){
		title = t;
	}
	public void setBackColor(String bg){
		bgColor = bg;
	}
	public void setForeColor(String fg){
		fgColor = fg;
	}

	public DocumentSnapshot save(){
		return new DocumentSnapshot(title, bgColor, fgColor);
	}
	public void restore(DocumentSnapshot shot){
		title = shot.getTitle();
		bgColor = shot.getBackColor();
		fgColor = shot.getForeColor();
	}
};


// Memento
class DocumentSnapshot {

	String title;
	String bgColor;
	String fgColor;
	
	DocumentSnapshot(String t, String bg, String fg){
		title = t;
		bgColor = bg;
		fgColor = fg;
	}
	
	public String getTitle(){
		return title;
	}
	public String getBackColor(){
		return bgColor;
	}
	public String getForeColor(){
		return fgColor;
	}
	public void setTitle(String t){
		title = t;
	}
	public void setBackColor(String bg){
		bgColor = bg;
	}
	public void setForeColor(String fg){
		fgColor = fg;
	}

};


// Caretaker
class DocumentTracker {

	DocumentSnapshot docShot = null;
	
	public DocumentSnapshot getDocShot(){
		return docShot;
	}
	public void setDocShot(DocumentSnapshot shot){
		docShot = shot;
	}
};


