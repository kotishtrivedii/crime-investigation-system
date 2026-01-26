public class CrimeCase {

    private String killer = "Rohan";

    public String getCaseSummary() {
        return """
        Case ID: 112A
        Victim: Rahul Verma
        Location: Old Warehouse
        Weapon: Knife
        Time: 11:30 PM
        """;
    }

    public String analyzeSuspect(String suspect) {

        if (suspect.equalsIgnoreCase(killer)) {
            return """
            🚨 KILLER IDENTIFIED 🚨
            Name: Rohan
            Proof:
            - CCTV footage at warehouse
            - Fingerprints on knife
            - Phone location at crime scene
            """;
        } else {
            return "Suspect " + suspect + " eliminated. Alibi verified.";
        }
    }
}
