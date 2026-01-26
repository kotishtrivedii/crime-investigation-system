public interface Interrogator {//interface for interrogation process
    String askQuestions(String question);//abstract method to be implemented by classes
    default String Warning(){
        return "This interrogation is being recorded.";
    }
    
}
//Interface defines a contract for classes to implement methods,
//  promoting abstraction and multiple inheritance in Java