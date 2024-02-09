/*
 * This program converts a hexadecimal string to its corresponding Base64 encoding.
 * It prompts the user to enter a hexadecimal string, reads the input, and performs the conversion.
 * The resulting Base64 encoded string is then displayed to the user.
 * 
 * Functions used:
 * - main(String[] args): Entry point of the program. Reads user input, converts hexadecimal string to byte array,
 *                        encodes byte array to Base64 string, and displays the result.
 * - hexStringToByteArray(String hexString): Converts a hexadecimal string to a byte array.
 * - encodeToBase64(byte[] bytes): Encodes a byte array to a Base64 string.
 */

import java.util.Scanner;

public class HexToBase64 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("21bce0596 Sagnik Mitra");
        System.out.println("Enter the hexadecimal string (press Enter twice to finish input):");
        StringBuilder input = new StringBuilder();

        String line; 
        while (!(line = scanner.nextLine()).isEmpty()) {
            input.append(line.trim());
        }

        byte[] bytes = hexStringToByteArray(input.toString());
        String base64String = encodeToBase64(bytes);

        System.out.println("Base64 encoded string is :");
        System.out.println(base64String);

        scanner.close();
    }

    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2]; 
        for (int i = 0; i < len; i += 2) {   
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    private static String encodeToBase64(byte[] bytes) {
        final String base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

        StringBuilder base64 = new StringBuilder();
        int paddingCount = (3 - bytes.length % 3) % 3;

        for (int i = 0; i < bytes.length; i += 3) { 
            int group = (bytes[i] & 0xFF) << 16 | (bytes[i + 1] & 0xFF) << 8 | (bytes[i + 2] & 0xFF);
            
            base64.append(base64Chars.charAt((group >> 18) & 63));
            base64.append(base64Chars.charAt((group >> 12) & 63));
            base64.append(base64Chars.charAt((group >> 6) & 63));
            base64.append(base64Chars.charAt(group & 63));
        }

        for (int i = 0; i < paddingCount; i++) {
            base64.setCharAt(base64.length() - 1 - i, '=');
        }

        return base64.toString();
    }
}
