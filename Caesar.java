//Imports

public class Caesar {

    /**
     * This function is used to encrypt one character, shifting the characters ascii value left or right a certain number of places.
     * @param shift - takes in a number, representing the amount of spaces, left or right a character will be shifted
     * @param rot - this is the character that will be rotated
     * @return newRot - representing the new encrypted character
     */
    public static char rotate(int shift, char rot) {
        char newRot = rot;
        int shift1 = shift % 26;
        if (Character.isLetter(rot) == true) {
            if (Character.isLowerCase(rot) == true){
                newRot = (char)(rot + shift1);
                if (newRot >= 'a' && newRot <= 'z') {
                    return newRot;
                }
                else if (newRot < 'a'){
                    newRot = (char)(newRot + 26);
                }
                else {
                    newRot = (char)(newRot - 26);
                }
            } else {
                newRot = (char)(rot + shift1);
                if (newRot <= 'Z' && newRot >= 'A') {
                    return newRot;
                }
                else if (newRot < 'A'){
                    newRot = (char)(newRot + 26);
                }
                else {
                    newRot = (char)(newRot - 26);
                }
            }
        }

        return newRot;

    }

    /**
     * This funcion uses the previous rotate to encrypt all the letters of a giver string.
     * @param shift - takes in a number, representing the amount of spaces, left or right a character will be shifted
     * @param rot - this is the character that will be rotated
     * @return newRot - representing the new encrypted character
     */
    public static String rotate(int shift, String rot) {
        String newRot = "";
        char c = ' ';
        char newC = ' ';

        for (int i = 0; i < rot.length(); i++) {
            c = rot.charAt(i);
            newC = Caesar.rotate(shift, c);
            newRot = newRot + newC;
        }

        return newRot;

    }

    /**
     * The main part of the code takes care of the arguments limiting how many arguments a user can input and direct them to the correct function in order to succesfully encrypt a specific string.
     * @param args - the arguments taken in are a number 'shift' representing the encryption key and the string 'rot' that will be encrypted
     */
    public static void main(String args[]) {

        char quotes = '"';
        String cipher = quotes+"cipher text"+quotes;
        if (args.length > 2) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Caesar n "+cipher);
        }
        else if (args.length < 2) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Caesar n "+cipher);
        } else {
            String newRot = " ";
            int shift = Integer.parseInt(args[0]);
            String rot = args[1];
            newRot = Caesar.rotate(shift, rot);
            System.out.println(newRot);

        }

    }

}

