package com.galicom.eventid;

public class Session {
	private Student currentStudent;
	
    public Student getCurrentStudent() {
		return currentStudent;
	}
	public void setCurrentStudent(Student currentStudent) {
		this.currentStudent = currentStudent;
	}
	
	private static volatile Session instance = null;
    private Session() {
        // La pr�sence d'un constructeur priv� supprime le constructeur public par d�faut.
        // De plus, seul le singleton peut s'instancier lui m�me.
        super();
    }
    public final static Session getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement v�rifi�" permet d'�viter un appel co�teux � synchronized, 
        //une fois que l'instanciation est faite.
        if (Session.instance == null) {
           // Le mot-cl� synchronized sur ce bloc emp�che toute instanciation multiple m�me par diff�rents "threads".
           // Il est TRES important.
           synchronized(Session.class) {
             if (Session.instance == null) {
            	 Session.instance = new Session();
             }
           }
        }
        return Session.instance;
    }


}
