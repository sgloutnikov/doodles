package stringsubstring;


import producerconsumer.Consumer;

public class Contains {

    public static void main(String[] args) {

        ContainChecker c = new ContainChecker();
        String s1 = "Hello dear friends";
        String s2 = "dear ";
        System.out.println(c.containsCheck(s1, s2));
    }

    public static class ContainChecker {


        public boolean containsCheck(String fullString, String checkString) {
            int maxDepth = fullString.length() - checkString.length();
            int checkStringLength = checkString.length();
            boolean found = false;

            // From beginning to maxDepth at each character check if following match the substring
            mainfor: for (int i = 0; i < maxDepth; i++) {
                int counter = 0;
                int current = i;

                while (counter < checkStringLength) {
                    if(fullString.charAt(current) != checkString.charAt(counter)) {
                        continue mainfor;
                    }
                    counter++;
                    current++;
                }
                found = true;
                break;
            }

            return found;
        }
    }
}
