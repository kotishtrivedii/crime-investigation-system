public class Detective {

    private String name;

    public Detective(String name) {
        this.name = name;
    }

    public String askQuestion(String suspect) {
        return "Detective " + name + " interrogating " + suspect;
    }

    public String getIdentity() {
        return "Detective " + name;
    }
}
