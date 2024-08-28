import java.util.*;    
public class Vignere  
 {  
    public static void main(String[] args) {  
        Scanner sc = new Scanner(System.in);  
        System.out.print("Enter the plaintext: ");  
        String pt = sc.nextLine().toUpperCase();  
        System.out.print("Enter the keyword: ");  
        String keyword = sc.nextLine().toUpperCase();  
        String encryptedText = encrypt(pt, keyword);  
        String decryptedText = decrypt(encryptedText, keyword);  
        System.out.println("\nEncrypted Text: " + encryptedText);  
        System.out.println("Decrypted Text: " + decryptedText);  
    }  
    private static String encrypt(String plaintext, String keyword) {  
        StringBuilder cipher = new StringBuilder();  
        for (int i = 0, j = 0; i < plaintext.length(); i++) {  
            char currentChar = plaintext.charAt(i);  
            if (Character.isAlphabetic(currentChar)) {  
                int shift = keyword.charAt(j % keyword.length()) - 'A';  
                char encryptedChar = (char) ((currentChar + shift - 'A') % 26 + 'A');  
                cipher.append(encryptedChar);  
                j++;  
            } else {  
                cipher.append(currentChar);  
            }  
        }  
        return cipher.toString();  
    }  
    private static String decrypt(String ciphertext, String keyword) {  
        StringBuilder decryptedText = new StringBuilder();  
        for (int i = 0, j = 0; i < ciphertext.length(); i++) {  
            char currentChar = ciphertext.charAt(i);  
            if (Character.isAlphabetic(currentChar)) {  
                int shift = keyword.charAt(j % keyword.length()) - 'A';  
                char decryptedChar = (char) ((currentChar - shift - 'A' + 26) % 26 + 'A');  
                decryptedText.append(decryptedChar);  
                j++;  
            } else {   
                decryptedText.append(currentChar);  
            }  
        }  
        return decryptedText.toString();  
    }  
}  