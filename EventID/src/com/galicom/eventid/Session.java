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
        // La présence d'un constructeur privé supprime le constructeur public par défaut.
        // De plus, seul le singleton peut s'instancier lui même.
        super();
    }
    public final static Session getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (Session.instance == null) {
           // Le mot-clé synchronized sur ce bloc empêche toute instanciation multiple même par différents "threads".
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
