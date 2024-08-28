import java.util.Scanner;

public class Hill {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text message = ");
        String text = sc.nextLine();
        System.out.print("Enter a key = ");
        String key = sc.nextLine();
        encryption(text, key);
    }

    private static void encryption(String text, String key) {
        String text_message = text;
        text = text.toUpperCase();
        key = key.toUpperCase();
        int lenChk = 0;
        if (text.length() % 2 != 0) {
            text += "0";
            lenChk = 1;
        }

        int[][] text2D = new int[2][text.length() / 2];
        int itr1 = 0;
        int itr2 = 0;
        for (int i = 0; i < text.length(); i++) {
            if (i % 2 == 0) {
                text2D[0][itr1] = ((int) text.charAt(i)) - 65;
                itr1++;
            } else {
                text2D[1][itr2] = ((int) text.charAt(i)) - 65;
                itr2++;
            }
        }

        System.out.println("Text 2D Matrix:");
        printMatrix(text2D);

        int[][] key2D = new int[2][2];
        int itr3 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] = (int) key.charAt(itr3) - 65;
                itr3++;
            }
        }

        System.out.println("Key 2D Matrix:");
        printMatrix(key2D);

        int deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
        deter = moduloFunction(deter);
        int mulInverse = multiplicativeInverse(deter);
        if (mulInverse == -1) {
            System.out.println("Invalid key");
            System.exit(1);
        }

        String encryptText = "";
        int itrCount = text.length() / 2;
        if (lenChk == 0) {
            for (int i = 0; i < itrCount; i++) {
                int temp1 = text2D[0][i] * key2D[0][0] + text2D[1][i] * key2D[0][1];
                encryptText += (char) ((temp1 % 26) + 65);
                int temp2 = text2D[0][i] * key2D[1][0] + text2D[1][i] * key2D[1][1];
                encryptText += (char) ((temp2 % 26) + 65);
            }
        } else {
            for (int i = 0; i < itrCount - 1; i++) {
                int temp1 = text2D[0][i] * key2D[0][0] + text2D[1][i] * key2D[0][1];
                encryptText += (char) ((temp1 % 26) + 65);
                int temp2 = text2D[0][i] * key2D[1][0] + text2D[1][i] * key2D[1][1];
                encryptText += (char) ((temp2 % 26) + 65);
            }
        }

        System.out.println("Encrypted Text: " + encryptText);
        decryption(encryptText, key, text_message);
    }

    private static void decryption(String encryptText, String key, String text) {
        int lenChk = 0;
        if (encryptText.length() % 2 != 0) {
            encryptText += "0";
            lenChk = 1;
        }

        int[][] encryptText2D = new int[2][encryptText.length() / 2];
        int itr1 = 0;
        int itr2 = 0;
        for (int i = 0; i < encryptText.length(); i++) {
            if (i % 2 == 0) {
                encryptText2D[0][itr1] = ((int) encryptText.charAt(i)) - 65;
                itr1++;
            } else {
                encryptText2D[1][itr2] = ((int) encryptText.charAt(i)) - 65;
                itr2++;
            }
        }

        System.out.println("Encrypted Text 2D Matrix:");
        printMatrix(encryptText2D);

        int[][] key2D = new int[2][2];
        int itr3 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] = (int) key.charAt(itr3) - 65;
                itr3++;
            }
        }

        System.out.println("Key 2D Matrix (Before Inverse):");
        printMatrix(key2D);

        int deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
        deter = moduloFunction(deter);
        int mulInverse = multiplicativeInverse(deter);
        if (mulInverse == -1) {
            System.out.println("Invalid key");
            System.exit(1);
        }

        // Inverse of the key matrix
        int swapTemp = key2D[0][0];
        key2D[0][0] = key2D[1][1];
        key2D[1][1] = swapTemp;
        key2D[0][1] *= -1;
        key2D[1][0] *= -1;
        key2D[0][1] = moduloFunction(key2D[0][1]);
        key2D[1][0] = moduloFunction(key2D[1][0]);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] *= mulInverse;
                key2D[i][j] = moduloFunction(key2D[i][j]);
            }
        }

        System.out.println("Key 2D Matrix (After Inverse):");
        printMatrix(key2D);

        String decryptText = "";
        int itrCount = encryptText.length() / 2;
        if (lenChk == 0) {
            for (int i = 0; i < itrCount; i++) {
                int temp1 = encryptText2D[0][i] * key2D[0][0] + encryptText2D[1][i] * key2D[0][1];
                decryptText += (char) ((temp1 % 26) + 65);
                int temp2 = encryptText2D[0][i] * key2D[1][0] + encryptText2D[1][i] * key2D[1][1];
                decryptText += (char) ((temp2 % 26) + 65);
            }
        } else {
            for (int i = 0; i < itrCount - 1; i++) {
                int temp1 = encryptText2D[0][i] * key2D[0][0] + encryptText2D[1][i] * key2D[0][1];
                decryptText += (char) ((temp1 % 26) + 65);
                int temp2 = encryptText2D[0][i] * key2D[1][0] + encryptText2D[1][i] * key2D[1][1];
                decryptText += (char) ((temp2 % 26) + 65);
            }
        }

        StringBuffer decrypted = new StringBuffer(decryptText);
        for (int i = 0; i < decryptText.length(); i++) {
            if (Character.isLowerCase(text.charAt(i))) {
                decrypted.setCharAt(i, Character.toLowerCase(decryptText.charAt(i)));
            }
        }

        System.out.println("Decrypted Text: " + decrypted);
    }

    private static int moduloFunction(int a) {
        int result = a % 26;
        if (result < 0) {
            result += 26;
        }
        return result;
    }

    private static int multiplicativeInverse(int deter) {
        int mulInverse;
        for (int i = 0; i < 26; i++) {
            int tempInv = deter * i;
            if (moduloFunction(tempInv) == 1) {
                mulInverse = i;
                return mulInverse;
            }
        }
        return -1;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
