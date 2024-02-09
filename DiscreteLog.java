/*
 * This program computes discrete logarithms using brute force and modular exponentiation.
 * 
 * Functions used:
 * - discreteLogBruteForce(int base, int result, int modulus): Computes discrete logarithm using brute force.
 * - modularExponentiation(int base, int exponent, int modulus): Computes modular exponentiation efficiently.
 * - main(String[] args): Entry point of the program. Computes discrete logarithms for two problems
 *                        and prints the results.
 */

public class DiscreteLog {

    public static int discreteLogBruteForce(int base, int result, int modulus) {
        int x;
        for (x = 1; x < modulus; x++) {
            if (modularExponentiation(base, x, modulus) == result) {
                return x;
            }
        }
        return -1;
    }

    public static int modularExponentiation(int base, int exponent, int modulus) {
        if (modulus == 1)
            return 0;
        int result = 1;
        base = base % modulus;
        while (exponent > 0) {
            if (exponent % 2 == 1)
                result = (result * base) % modulus;
            exponent = exponent >> 1;
            base = (base * base) % modulus;
        }
        return result;
    }

    public static void main(String[] args) {
        int base1 = 10;
        int result1 = 22;
        int modulus1 = 47;
        
        System.out.println("\n21bce0596 SagnikMitra");
        int logarithm1 = discreteLogBruteForce(base1, result1, modulus1);
        if (logarithm1 != -1) {
            System.out.println("log10(" + result1 + ") mod " + modulus1 + " = " + logarithm1);
        } else {
            System.out.println("No discrete logarithm found for the first problem.");
        }

        int base2 = 627;
        int result2 = 608;
        int modulus2 = 941;

        int logarithm2 = discreteLogBruteForce(base2, result2, modulus2);
        if (logarithm2 != -1) {
            System.out.println("log627(" + result2 + ") mod " + modulus2 + " = " + logarithm2);
        } else {
            System.out.println("No discrete logarithm found for the second problem.");
        }
    }
}
