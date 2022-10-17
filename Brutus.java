//Imports
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Brutus {

    /**
     * we use english[] array of doubles to compare our result to the frequency of english letters in the enlgish language.
     */
    public static final double[] english = {
        0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
        0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
        0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    };

    /**
     * This method is used to count the amount of letters in a string, in alphabetical order. E.g. how many 'a's there are.
     * @param b - this is the string from which we will count the amount of letters
     * @return count1 - this is the array of numbers that we will be returning
     */
    public static int[] count(String b) {
        int[] count1 = new int[26];
        int count = 0;
        String brut = b.toLowerCase();
        char comp = ' ';
        for (int i = 0; i < count1.length; i++) {
                count = 0;
                for (int j = 0; j < brut.length(); j++) {
                    comp = (char)(97 + i);
                    if (comp == brut.charAt(j)) {
                        count += 1;
                    } else {
                        count = count;
                    }

                }
        count1[i] = count;

        }



    return count1;
    }

    /**
     * Similar to the previous function, this function takes that count1 array and compares each number to how many times that letter appeared in the string.
     * Or just the frequency of that letter appearing. The number of times it has appeared divided by the amount of letters in the string.
     * @param b - the string that is to be decrypted.
     * @return count3 - an array of doubles representing the frequency of each letter in that string.
     */
    public static double[] frequency(String b) {
        int[] count2 = Brutus.count(b);
        double[] count3 = new double[26];
        double count = 0;
        for (int i = 0; i < count2.length; i++) {
            count += count2[i];
        }
        for (int i = 0; i < count2.length; i++) {
            //Convert to 4 decimal places and add it to the array
            double temp1 = (double)(count2[i]/count);
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            String temp2 = df.format(temp1);
            double temp3 = Double.parseDouble(temp2);
            count3[i] = temp3;
        }

    return count3;
    }

    /**
     * This function get the chiSquared value between the english array with the average frequencies of letters in the english language and the freq[] with the frequencies of letters that we have in our string.
     * @param english - this is the array with the average frequency of letters in the english language
     * @param freq - this is the array with the frequencies of letter in our string
     * @return chiSq - the return is a chiSq representing how close our string is to the english language according to the frequencies of letters in the string compared to the english language
     */
    public static double chiSquared(double[] english, double[] freq) {

        double chiSq = 0.0;
        for (int i = 0; i < freq.length; i++) {
            double temp1 = Math.pow((freq[i] - english[i]), 2);
            double temp2 = (double)(temp1 / english[i]);
            chiSq += temp2;
        }

        return chiSq;

    }

    /**
     * The main method filters through the amount of arguments, so that the decyption runs on only one argument.
     * It then shifts the string we give it to decrpt, 26 times, comparing the chiSq value of each one so that we get the lowest chiSq value.
     * The string with the lowest chiSq value is the decrypted string
     * @param args - this is the string we give it to decrypt
     */
    public static void main(String args[]) {

        char quotes = '"';
        String cipher = quotes+"cipher text"+quotes;
        if (args.length > 1) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Brutus "+cipher);
        }
        else if (args.length < 1) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Brutus "+cipher);
        } else {
            String save = "";
            String brut = args[0];
            double lower = 10.0;
            for (int i = 0; i < 26; i++) {
                brut = Caesar.rotate(i, brut);
                double[] array = frequency(brut);
                double chiSq = chiSquared(english, array);
                if (chiSq < lower) {
                    save = brut;
                    lower = chiSq;
                }
            }
        System.out.println(save);
        }

    }
}