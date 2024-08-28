public class Chinese {
    public static int findX(int[] y, int[] m, int k) {
        int M = 1, X = 0, temp = 0;
        int[] Mi = new int[k];
        int[] Z = new int[k];

        // Calculating M
        for (int i = 0; i < k; i++) {
            M *= m[i];
        }

        // Calculating Mi
        for (int i = 0; i < k; i++) {
            Mi[i] = M / m[i];
        }

        // Calculating Zi
        for (int i = 0; i < k; i++) {
            int z = 0;
            int x = Mi[i];
            while ((z * x) % m[i] != 1) {
                z++;
            }
            Z[i] = z;
        }

        // Calculating X
        for (int i = 0; i < k; i++) {
            temp += (y[i] * Z[i] * Mi[i]);
            X = temp % M;
        }

        // Final answer
        return X;
    }

    public static void main(String[] args) {
        int[] y = {2,3,2};
        int[] m = {3,5,7};
        int k = y.length;
        int X = findX(y, m, k);
        System.out.println("The answer using CRT is: " + X);
    }
}
