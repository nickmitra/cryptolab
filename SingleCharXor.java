/*
 * This program decrypts a single-character XOR encrypted text.
 * 
 * Functions used:
 * - main(String[] args): Entry point of the program. Reads encrypted text from a file, decrypts each line,
 *                        and prints the decrypted text along with line numbers.
 * - readFromFile(String filePath): Reads text from a file and returns an array of lines.
 * - decryptSingleCharacterXOR(String encryptedHex): Decrypts a single-character XOR encrypted string.
 * - findKey(byte[] encryptedBytes): Finds the key used for single-character XOR encryption based on the
 *                                    frequency of letters and spaces in the decrypted text.
 * - hexStringToByteArray(String s): Converts a hexadecimal string to a byte array.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SingleCharXor {

    public static void main(String[] args) {
        String filePath = "sample.txt";
        String[] encryptedLines = readFromFile(filePath);
        System.out.println("21bce0596 SagnikMitra");
        for (int i = 0; i < encryptedLines.length; i++) {
            String decryptedString = decryptSingleCharacterXOR(encryptedLines[i]);
            System.out.println("Line " + (i + 1) + ": " + decryptedString);
        }
    }

    public static String[] readFromFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString().split("\n");
    }

    public static String decryptSingleCharacterXOR(String encryptedHex) {
        byte[] encryptedBytes = hexStringToByteArray(encryptedHex);
        char key = findKey(encryptedBytes);
        byte[] decryptedBytes = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            decryptedBytes[i] = (byte) (encryptedBytes[i] ^ key);
        }
        return new String(decryptedBytes);
    }

    public static char findKey(byte[] encryptedBytes) {
        char key = ' ';
        int maxSpaces = 0;

        for (char candidateKey = 0; candidateKey < 256; candidateKey++) {
            int spaces = 0;
            for (byte encryptedByte : encryptedBytes) {
                char decryptedChar = (char) (encryptedByte ^ candidateKey);
                if (Character.isLetterOrDigit(decryptedChar) || Character.isWhitespace(decryptedChar)) {
                    if (Character.isWhitespace(decryptedChar)) {
                        spaces++;
                    }
                } else {
                    spaces = 0;
                    break;
                }
            }
            if (spaces > maxSpaces) {
                maxSpaces = spaces;
                key = candidateKey;
            }
        }
        return key;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

}
