package string;

public class StringOperations {

    public static void main(String[] args) {
        QuestionTwo();
    }

    private static void QuestionOne() {
        String s1 = new String("interview"); // Line 1
        String s2 = s1.intern();             // Line 2
        System.out.println(s1 == s2);        // Output?
    }

    private static void QuestionTwo() {
        // Dynamically created (no "interview" literal used yet), so no object in SCP
        String s1 = new StringBuilder("inter").append("view").toString();//

        String s2 = s1.intern();// Because there is no literal name "interview" in SCP , it will update the map to use the one from general heap

        System.out.println(s1 == s2); // Output: true (Only in Java 7+)      // s1 pointing to Heap & s2 is also pointing to the same
    }


}
