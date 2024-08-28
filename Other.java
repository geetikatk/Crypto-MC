import java.util.Scanner;

class Other {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String DIGITS = "0123456789";
   
    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }

    
    public static String affineEncrypt(String s, int a, int b) {
        s = s.toLowerCase();
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isLetter(ch)) {
                int pos = ALPHABET.indexOf(ch);
                int newPos = (a * pos + b) % 26;
                if (newPos < 0) {
                    newPos += 26;
                }
                encryptedText.append(ALPHABET.charAt(newPos));
            } 
            else if (Character.isDigit(ch)) {
                int pos = DIGITS.indexOf(ch);
                int newPos = (a * pos + b) % 10;
                if (newPos < 0) {
                    newPos += 10;
                }
                encryptedText.append(DIGITS.charAt(newPos));
            }
            else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

    
    public static String affineDecrypt(String s, int a, int b) {
        s = s.toLowerCase();
        StringBuilder decryptedText = new StringBuilder();
        int aInverse = modInverse(a, 26);
        
        if (aInverse == -1) {
            throw new IllegalArgumentException("No modular inverse exists for a=" + a);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isLetter(ch)) {
                int pos = ALPHABET.indexOf(ch);
                int newPos = (aInverse * (pos - b)) % 26;
                if (newPos < 0) {
                    newPos += 26;
                }
                decryptedText.append(ALPHABET.charAt(newPos));
            } 
            else if (Character.isDigit(ch)) {
                int pos = DIGITS.indexOf(ch);
                int newPos = (aInverse * (pos - b)) % 10;
                if (newPos < 0) {
                    newPos += 10;
                }
                decryptedText.append(DIGITS.charAt(newPos));
            }
            else {
                decryptedText.append(ch);
            }
        }
        return decryptedText.toString();
    }


    public static String multiplicativeEncrypt(String s, int key) {
        s = s.toLowerCase();
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isLetter(ch)) {
                int pos = ALPHABET.indexOf(ch);
                int newPos = (key * pos) % 26;
                if (newPos < 0) {
                    newPos += 26;
                }
                encryptedText.append(ALPHABET.charAt(newPos));
            } 
            else if (Character.isDigit(ch)) {
                int pos = DIGITS.indexOf(ch);
                int newPos = (key * pos) % 10;
                if (newPos < 0) {
                    newPos += 10;
                }
                encryptedText.append(DIGITS.charAt(newPos));
            }
            else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

   
    public static String multiplicativeDecrypt(String s, int key) {
        s = s.toLowerCase();
        StringBuilder decryptedText = new StringBuilder();
        int keyInverse = modInverse(key, 26);
        
        if (keyInverse == -1) {
            throw new IllegalArgumentException("No modular inverse exists for key=" + key);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isLetter(ch)) {
                int pos = ALPHABET.indexOf(ch);
                int newPos = (keyInverse * pos) % 26;
                if (newPos < 0) {
                    newPos += 26;
                }
                decryptedText.append(ALPHABET.charAt(newPos));
            } 
            else if (Character.isDigit(ch)) {
                int pos = DIGITS.indexOf(ch);
                int newPos = (keyInverse * pos) % 10;
                if (newPos < 0) {
                    newPos += 10;
                }
                decryptedText.append(DIGITS.charAt(newPos));
            }else {
                decryptedText.append(ch);
            }
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Choose cipher: 1) Affine 2) Multiplicative");
        int choice = Integer.parseInt(sc.nextLine());
        
        if (choice == 1) {
            System.out.println("Enter the plain text:");
            String plainText = sc.nextLine();
            System.out.println("Enter the key a:");
            int a = Integer.parseInt(sc.nextLine());
            System.out.println("Enter the key b:");
            int b = Integer.parseInt(sc.nextLine());
            
            String encrypted = affineEncrypt(plainText, a, b);
            String decrypted = affineDecrypt(encrypted, a, b);

            System.out.println("Encrypted Data: " + encrypted);
            System.out.println("Decrypted Data: " + decrypted);

        } else if (choice == 2) {
            System.out.println("Enter the plain text:");
            String plainText = sc.nextLine();
            System.out.println("Enter the key:");
            int key = Integer.parseInt(sc.nextLine());
            
            String encrypted = multiplicativeEncrypt(plainText, key);
            String decrypted = multiplicativeDecrypt(encrypted, key);

            System.out.println("Encrypted Data: " + encrypted);
            System.out.println("Decrypted Data: " + decrypted);
            
        } else {
            System.out.println("Invalid choice.");
        }
        
        sc.close();
    }
}
