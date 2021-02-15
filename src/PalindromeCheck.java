public class PalindromeCheck {

    public static void main(String[] args){
        String test1 = "Never odd or even";
        String test2 = "Able was I ere I saw Elba";
        String test3 = "A man, a plan, a canal: Panama!";
        String test4 = "This is not a palindrome";

        System.out.println("Is '" + test1 + "' a palindrome: " + isPalindrome(test1));
        System.out.println("Is '" + test2 + "' a palindrome: " + isPalindrome(test2));
        System.out.println("Is '" + test3 + "' a palindrome: " + isPalindrome(test3));
        System.out.println("Is '" + test4 + "' a palindrome: " + isPalindrome(test4));
    }


    public static boolean isPalindrome(String text){
        //remove upper case and punctuation using regex
        String modifiedText = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String reverseModifiedText = reverse(modifiedText);

        if(modifiedText.equals(reverseModifiedText)){
            return true;
        }
        return false;
    }


    public static String reverse(String text){
        if(text == null || text.isEmpty()){
            return text;
        }
        return text.charAt(text.length() - 1) + reverse(text.substring(0, text.length() - 1));
    }
}
