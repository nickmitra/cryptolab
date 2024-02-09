/*
 * This program performs repeating-key XOR encryption.
 * 
 * Functions used:
 * - main(String[] args): Entry point of the program. Encrypts the plaintext using repeating-key XOR
 *                        and prints the encrypted text in hexadecimal format.
 * - repeatedKeyXor(byte[] plainText, byte[] key): Encrypts the plaintext using repeating-key XOR.
 * - bytesToHex(byte[] bytes): Converts a byte array to a hexadecimal string.
 */

public class RepeatingKey {
    public static void main(String[] args) {
        byte[] plainText = "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal".getBytes();
        byte[] key = "ICE".getBytes();
        
        byte[] encrypted = repeatedKeyXor(plainText, key);
        System.out.println("\n21bce0596 SagnikMitra");
        System.out.println("Encrypted as: " + bytesToHex(encrypted));
    }

    public static byte[] repeatedKeyXor(byte[] plainText, byte[] key) {
        byte[] encoded = new byte[plainText.length];
        int lenKey = key.length;

        for (int i = 0; i < plainText.length; i++) {
            encoded[i] = (byte) (plainText[i] ^ key[i % lenKey]);
        }
        return encoded;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
