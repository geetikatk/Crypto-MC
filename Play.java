import java.util.Scanner;

public class Play {

    private String KeyWord = new String();
    private String Key = new String();
    private char matrix_arr[][] = new char[5][5];

    public void setKey(String k) {
        String K_adjust = new String();
        boolean flag = false;
        K_adjust = K_adjust + k.charAt(0);
        for (int i = 1; i < k.length(); i++) {
            for (int j = 0; j < K_adjust.length(); j++) {
                if (k.charAt(i) == K_adjust.charAt(j)) {
                    flag = true;
                }
            }
            if (!flag) K_adjust = K_adjust + k.charAt(i);
            flag = false;
        }
        KeyWord = K_adjust;
    }

    public void KeyGen() {
        boolean flag = true;
        char current;
        Key = KeyWord;
        for (int i = 0; i < 26; i++) {
            current = (char) (i + 97);
            if (current == 'j') continue;
            for (int j = 0; j < KeyWord.length(); j++) {
                if (current == KeyWord.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) Key = Key + current;
            flag = true;
        }
        System.out.println(Key);
        matrix();
    }

    private void matrix() {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix_arr[i][j] = Key.charAt(counter);
                System.out.print(matrix_arr[i][j] + " ");
                counter++;
            }
            System.out.println();
        }
    }

    private String format(String old_text) {
        StringBuilder text = new StringBuilder();
        for (int tmp = 0; tmp < old_text.length(); tmp++) {
            if (old_text.charAt(tmp) == 'j') {
                text.append('i');
            } else {
                text.append(old_text.charAt(tmp));
            }
        }
        for (int i = 0; i < text.length(); i += 2) {
            if (text.charAt(i + 1) == text.charAt(i)) {
                text.insert(i + 1, 'x');
            }
        }
        return text.toString();
    }

    private String[] Divid2Pairs(String new_string) {
        String Original = format(new_string);
        int size = Original.length();
        if (size % 2 != 0) {
            Original = Original + 'x';
            size++;
        }
        String[] x = new String[size / 2];
        int counter = 0;
        for (int i = 0; i < size / 2; i++) {
            x[i] = Original.substring(counter, counter + 2);
            counter += 2;
        }
        return x;
    }

    public int[] GetDiminsions(char letter) {
        int[] key = new int[2];
        if (letter == 'j') letter = 'i';

        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix_arr[i][j] == letter) {
                    key[0] = i;
                    key[1] = j;
                    return key;
                }
            }
        }
        return key;
    }

    public String encryptMessage(String Source) {
        String[] src_arr = Divid2Pairs(Source);
        StringBuilder Code = new StringBuilder();
        char one;
        char two;
        int[] part1;
        int[] part2;
        for (String pair : src_arr) {
            one = pair.charAt(0);
            two = pair.charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0]) {
                part1[1] = (part1[1] + 1) % 5;
                part2[1] = (part2[1] + 1) % 5;
            } else if (part1[1] == part2[1]) {
                part1[0] = (part1[0] + 1) % 5;
                part2[0] = (part2[0] + 1) % 5;
            } else {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Code.append(matrix_arr[part1[0]][part1[1]])
                .append(matrix_arr[part2[0]][part2[1]]);
        }
        return Code.toString();
    }

    public String decryptMessage(String Code) {
        StringBuilder Original = new StringBuilder();
        String[] src_arr = Divid2Pairs(Code);
        char one;
        char two;
        int[] part1;
        int[] part2;
        for (String pair : src_arr) {
            one = pair.charAt(0);
            two = pair.charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0]) {
                part1[1] = (part1[1] - 1 + 5) % 5;
                part2[1] = (part2[1] - 1 + 5) % 5;
            } else if (part1[1] == part2[1]) {
                part1[0] = (part1[0] - 1 + 5) % 5;
                part2[0] = (part2[0] - 1 + 5) % 5;
            } else {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Original.append(matrix_arr[part1[0]][part1[1]])
                    .append(matrix_arr[part2[0]][part2[1]]);
        }
        return Original.toString();
    }

    public static void main(String[] args) {
        Play x = new Play();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a keyword:");
        String keyword = sc.next();
        x.setKey(keyword);
        x.KeyGen();
        System.out.println("Enter word to encrypt: (Make sure length of message is even)");
        String key_input = sc.next();
        if (key_input.length() % 2 == 0) {
            String encrypted = x.encryptMessage(key_input);
            System.out.println("Encryption: " + encrypted);
            String decrypted = x.decryptMessage(encrypted);
            System.out.println("Decryption: " + decrypted);
        } else {
            System.out.println("Message length should be even");
        }
        sc.close();
    }
}
