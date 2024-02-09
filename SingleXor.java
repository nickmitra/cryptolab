/*
 * This program cracks an XOR encrypted message by performing frequency analysis.
 * 
 * Functions used:
 * - main(String[] args): Entry point of the program. Calls the crack() function to crack the XOR encryption
 *                        and prints the result.
 * - crack(String input): Cracks the XOR encryption by iterating over possible keys, decrypting the message,
 *                        and scoring each decryption based on frequency analysis.
 */

import java.util.HashMap;
import java.util.Map;

public class SingleXor {
    public static void main(String[] args) {
        String input = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
        Map<Character, String> resultMap = crack(input);
        System.out.println("\n 21bce0596 SagnikMitra");
        System.out.println("Key: " + resultMap.keySet().iterator().next());
        System.out.println("Message: " + resultMap.values().iterator().next());
    }

    public static Map<Character, String> crack(String input) {
        Map<Character, String> res = new HashMap<>();
        char candidateKey = '\0';
        int highestScore = 0;

        String[] freq = {" ", "e", "t", "a", "o", "i", "n", "s", "h", "r", "d", "l", "u"};
        
        char[] keys = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        for (char key : keys) {
            StringBuilder output = new StringBuilder();
            int cursor = 0;

            while (cursor < input.length()) {
                String hex = input.substring(cursor, cursor + 2);
                int xor = Integer.parseInt(hex, 16) ^ (int) key;
                output.append((char) xor);
                cursor += 2;
            }

            int score = 0;
            
            for (int i = 0; i < freq.length; i++) {
                String re = freq[i];
                long matches = output.toString().toLowerCase().chars().filter(ch -> ch == re.charAt(0)).count();
                score += matches * (12 - i);
            }

            if (score > highestScore) {
                highestScore = score;
                candidateKey = key;
                res.put(key, output.toString());
            }
        }

        Map<Character, String> result = new HashMap<>();
        result.put(candidateKey, res.get(candidateKey));
        return result;
    }
}
