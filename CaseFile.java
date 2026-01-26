
abstract  class CaseFile {//abstract class shows only the essential details of a case
    private String caseId;
    private String location;
    private String weapon;
    private String victim;
    private String time;
    //constructor to initialize case details
    public CaseFile(String caseId, String location, String weapon, String victim, String time){//constructor with parameters to initialize properties
        this.caseId=caseId;//this keyword is used to refer to the current object's properties
        this.location=location;
        this.weapon=weapon;
        this.victim=victim;
        this.time=time;
    }
//encapsulation
public String getCaseId(){//getter method to access private properties 
    return caseId;}
public String getLocation(){
    return location;}
public String getWeapon(){
    return weapon;}
public String getVictim(){
    return victim;}
public String getTime(){
    return time;}
//abstract runtime polymorphism
    public abstract  String getCaseSummary();//abstract method to be implemented by subclasses and its public
}

//Private variables are hidden from outside classes, but 
//  this keyword accesses them within the same class to initialize the object’s state.”