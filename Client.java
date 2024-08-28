import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;
        int key = 5; 

        try (Socket socket = new Socket(hostname, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String message;
            System.out.println("Enter messages to send to the server (type 'exit' to quit):");

            while ((message = userInput.readLine()) != null) {
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
                String encryptedMessage = Scipher.encrypt(message, key);
                out.println(encryptedMessage);
                System.out.println("After Encrption :"+encryptedMessage);
                String response = in.readLine();
                String decryptedResponse = Scipher.decrypt(response, key);
                System.out.println("Server response (decrypted): " + decryptedResponse);
            }
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
