import java.util.Scanner;

class Scipher {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";   
    public static final String DIGITS = "0123456789";

    public static String encrypt(String s, int k) {
        s = s.toLowerCase();
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {  
            char ch = s.charAt(i);

            if (Character.isLetter(ch)) {
                int pos = ALPHABET.indexOf(ch);
                int newPos = (pos + k) % 26;
                if (newPos < 0) {
                    newPos += 26;
                }
                encryptedText.append(ALPHABET.charAt(newPos));
            } else if (Character.isDigit(ch)) {
                int pos = DIGITS.indexOf(ch);
                int newPos = (pos + k) % 10;
                if (newPos < 0) {
                    newPos += 10;
                }
                encryptedText.append(DIGITS.charAt(newPos));
            } else {
                encryptedText.append(ch); 
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(String s, int k) {
        s = s.toLowerCase();
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {   
            char ch = s.charAt(i);

            if (Character.isLetter(ch)) {
                int pos = ALPHABET.indexOf(ch);
                int newPos = (pos - k) % 26;
                if (newPos < 0) {
                    newPos += 26;
                }
                decryptedText.append(ALPHABET.charAt(newPos));
            } else if (Character.isDigit(ch)) {
                int pos = DIGITS.indexOf(ch);
                int newPos = (pos - k) % 10;
                if (newPos < 0) {
                    newPos += 10;
                }
                decryptedText.append(DIGITS.charAt(newPos));
            } else {
                decryptedText.append(ch);  
            }
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the plain text:");
        String plainText = sc.nextLine();
        System.out.println("Enter the key:");
        int key = Integer.parseInt(sc.nextLine());
        String encrypted = encrypt(plainText, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Encrypted Data: " + encrypted);
        System.out.println("Decrypted Data: " + decrypted);
    }
}
