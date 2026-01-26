public class Person {
    protected String name;//protected access modifier
    protected String role;
    public Person(String name, String role) {
        this.name = name;
        this.role = role;
    }
    public String getIdentity() {
        return role + ": "+ name;
    }
}
