/*
 * This program performs a fixed XOR operation on two hexadecimal strings.
 * 
 * Functions used:
 * - main(String[] args): Entry point of the program. Reads two hexadecimal strings,
 *                        performs a fixed XOR operation, and prints the result.
 * - hexToBin(String hexString): Converts a hexadecimal string to a binary string.
 * - zeroPad(String binaryString, int desiredLength): Pads a binary string with leading zeros
 *                                                     to match the desired length.
 * - xor(String binaryString1, String binaryString2): Performs the XOR operation between two binary strings.
 * - binToHex(String binaryString): Converts a binary string to a hexadecimal string.
 */

import java.math.BigInteger;

public class FixedXor {
    public static void main(String[] args) {
        String hexValue1 = "1c0111001f010100061a024b53535009181c";
        String hexValue2 = "686974207468652062756c6c277320657965";

        String binValue1 = hexToBin(hexValue1);
        String binValue2 = hexToBin(hexValue2);

        int desiredLength = Math.max(binValue1.length(), binValue2.length());
        binValue1 = zeroPad(binValue1, desiredLength);
        binValue2 = zeroPad(binValue2, desiredLength);

        String stringResult = xor(binValue1, binValue2);

        String finalOutput = binToHex(stringResult);

        System.out.println("21bce0596 SagnikMitra The output is : ");
        System.out.println("\n" + finalOutput); 
    }

    public static String hexToBin(String hexString) {
        return new BigInteger(hexString, 16).toString(2);
    }

    public static String zeroPad(String binaryString, int desiredLength) {
        StringBuilder paddedString = new StringBuilder(binaryString);
        while (paddedString.length() < desiredLength) {
            paddedString.insert(0, "0");
        }
        return paddedString.toString();
    }

    public static String xor(String binaryString1, String binaryString2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binaryString1.length(); i++) {
            result.append(binaryString1.charAt(i) == binaryString2.charAt(i) ? "0" : "1");
        }
        return result.toString();
    }

    public static String binToHex(String binaryString) {
        return new BigInteger(binaryString, 2).toString(16);
    }
}
