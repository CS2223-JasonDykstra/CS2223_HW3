import java.util.ArrayList;
import java.util.HashMap;

public class GrayCodesAreFun {

    public static void main(String[] args){
        ArrayList<String> grayCodes = new ArrayList<String>();

        //Simply add names to this list of children to expand the list
        String[] children = new String[]{"Alice", "Bob", "Chris", "Dylan"};

        int numChildren = children.length;
        grayCodes = grayCodes(numChildren);


        //print gray codes
        System.out.println("gray codes for n = " + numChildren + ":");
        for(String s : grayCodes){
            System.out.println(s);
        }
        System.out.println();


        //iterate over the gray codes and see which value changed
        //whenever a value changes, print the name of the child associated with it
        System.out.println();
        System.out.println("Order of moved children: ");
        for(int i = 0; i < grayCodes.size() - 1; ++i){
            //since the index of the changed digit is in reverse order from the order
            //that the childrens' names appear in the array, subtract the index from
            //the length of the children array to get the index of the child
            int modifiedIndex = numChildren - getMovedChild(grayCodes.get(i), grayCodes.get(i + 1))[0];
            System.out.println(children[modifiedIndex - 1]);
        }


        //find the sequence of how children are moved (in or out) and associate them with a gray code
        //in a hash map
        HashMap<String, String> movedChildren = new HashMap<String, String>();
        for(int i = 0; i < grayCodes.size() - 1; ++i){
            int[] movedChild = getMovedChild(grayCodes.get(i), grayCodes.get(i + 1));
            int modifiedIndex = numChildren - movedChild[0];
            movedChildren.put(grayCodes.get(i + 1), children[modifiedIndex - 1] + (movedChild[1] == 0 ? " Out" : " In"));
        }

        //print the final table
        System.out.println();
        System.out.println("Completed table: ");
        printTable(grayCodes, children, movedChildren);
    }

    public static ArrayList<String> grayCodes(int n){
        if( n <= 0 ) return new ArrayList<String>();

        ArrayList<String> arr = new ArrayList<String>();
        arr.add("0");
        arr.add("1");

        //calculate next gray code by using bit shifting
        for(int i = 2; i < (1 << n); i = i << 1){
            for (int j = i - 1 ; j >= 0 ; --j)
                arr.add(arr.get(j));

            for(int j = 0; j < i; ++j)
                arr.set(j, "0" + arr.get(j));

            for(int j = i; j < 2*i; ++j){
                arr.set(j, "1" + arr.get(j));
            }
        }

        return arr;
    }

    //returns an array of 2 integers
    //[0] = the index in the gray code which has changed
    //[1] = the new digit of the gray code
    public static int[] getMovedChild(String s1, String s2){
        int[] answer = new int[2];
        for(int i = 0; i < s1.length(); ++i){
            if(s1.charAt(i) != s2.charAt(i)){
                answer[0] = i;
                answer[1] = Character.getNumericValue(s2.charAt(i));
                return answer;
            }
        }

        return answer;
    }

    public static String childrenInPhoto(String[] children, String grayCode){
        String answer = "";
        for(int i = 0; i < grayCode.length(); ++i){
            int digit = Integer.parseInt(grayCode.substring(i, i + 1));
            if(answer != "" && digit == 1) answer += " ";
            if(digit == 1) answer += children[children.length - i - 1];
        }
        return answer;
    }

    public static void printTable(ArrayList<String> grayCodes, String[] children, HashMap<String, String> movedChildren){
        System.out.println();
        System.out.println("Index | Gray Code | Child(ren) in Photo   | Action");

        //find maximum length of string of children in photo
        int maxLength = 0;
        for(int i = 1; i < grayCodes.size(); ++i){
            maxLength = Math.max(maxLength, childrenInPhoto(children, grayCodes.get(i)).length());
        }



        for(int i = 1; i < grayCodes.size(); ++i){
            String line = "";
            String grayCode = grayCodes.get(i);
            //index
            line += "  " + i + (i/10 == 0 ? "   " : "  ");

            //gray code
            line += "|    " + grayCode;

            //children in photo
            line += "   | " + childrenInPhoto(children, grayCode);
            int currLength = childrenInPhoto(children, grayCode).length();
            int remainingSpaces = maxLength - currLength;
            for(int j = 0; j < remainingSpaces; ++j){
                line += " ";
            }

            //action
            line += " | " + movedChildren.get(grayCode);

            System.out.println(line);
        }
    }


}
