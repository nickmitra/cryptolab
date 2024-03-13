import java.math.BigInteger;
import java.util.Scanner;

public class TrudyDecrypt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Receive public key (e, n) from Bob
        System.out.println("21BCE0596 SAGNIK MITRA");
        System.out.println("Step 1: Enter the value of e:");
        BigInteger e = null;
        while (e == null) {
            try {
                e = scanner.nextBigInteger();
            } catch (Exception ex) {
                System.out.println("Invalid input. Please enter a valid integer for e:");
                scanner.nextLine(); // Clear the input buffer
            }
        }
        
        System.out.println("Enter the value of n:");
        BigInteger n = null;
        while (n == null) {
            try {
                n = scanner.nextBigInteger();
            } catch (Exception ex) {
                System.out.println("Invalid input. Please enter a valid integer for n:");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        // Step 2: Factor n to find p and q
        BigInteger[] factors = factorize(n);
        BigInteger p = factors[0];
        BigInteger q = factors[1];
        System.out.println(" p ="+p+" "+"q ="+q);

        // Step 3: Compute z = (p-1)*(q-1)
      BigInteger z = null;
      if (p != null && q != null) {
          z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
          System.out.println("z ="+z);
        } else {
    // Handle the case where p or q is null
       System.out.println("Error: Unable to compute z. p or q is null.");
        }

        // Step 4: Compute decryption exponent d
        BigInteger d = computeDecryptionExponent(e, z);

        // Step 5: Wait to receive ciphertext message c from Alice
        System.out.println("Step 5: Enter the ciphertext message c:");
        BigInteger c = null;
        while (c == null) {
            try {
                c = scanner.nextBigInteger();
            } catch (Exception ex) {
                System.out.println("Invalid input. Please enter a valid integer for c:");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        // Step 6: Decrypt the ciphertext message
        BigInteger m = c.modPow(d, n);

        // Print original message
        System.out.println("Original message (m): " + m);

        scanner.close();
    }

    // Function to factorize n
    private static BigInteger[] factorize(BigInteger n) {
        BigInteger[] factors = new BigInteger[2];
        BigInteger i = BigInteger.valueOf(2);
        while (i.multiply(i).compareTo(n) <= 0) {
            if (n.mod(i).equals(BigInteger.ZERO)) {
                factors[0] = i;
                factors[1] = n.divide(i);
                break;
            }
            i = i.add(BigInteger.ONE);
        }
        return factors;
    }

    // Function to compute decryption exponent d
    private static BigInteger computeDecryptionExponent(BigInteger e, BigInteger z) {
        BigInteger d = BigInteger.ONE;
        while (e.multiply(d).mod(z).compareTo(BigInteger.ONE) != 0) {
            d = d.add(BigInteger.ONE);
        }
        return d;
    }
}
