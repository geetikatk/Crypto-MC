public class Euler {
    public static int ETF(int n) {
        int phi_n = n; 
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i; 
                }
                phi_n -= phi_n / i; 
            }
        }
        
        if (n > 1) { 
            phi_n -= phi_n / n; 
        }

        return phi_n; 
    }

    public static int modularExponentiation(int base, int exponent, int modulus) {
        int result = 1;
        base = base % modulus;,
        while (exponent > 0) {
            if ((exponent % 2) == 1) { 
                result = (result * base) % modulus;
            }
            exponent = exponent >> 1; 
            base = (base * base) % modulus; 
        }
        return result;
    }

    public static void main(String[] args) {
        int m = 77;
        int a = 20;
        int n = 62;

        int result = ETF(m);
        System.out.println("Euler's Totient Function value for " + m + " is: " + result);

        int k = n - result;
        int ans = modularExponentiation(a, k, m);
        System.out.println("The answer for " + a + "^" + k + " (mod " + m + ") is " + ans);
    }
}
