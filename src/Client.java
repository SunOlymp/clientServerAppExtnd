import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try (Socket clientSocket = new Socket("netology.homework", Server.localhost);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(in.readLine());
            String message = in.readLine();
            System.out.println(message);
            String response = scanner.nextLine();
            out.println(response);
            System.out.println(in.readLine());
            response = scanner.nextLine();
            out.println(response);
            System.out.println(in.readLine());
        } catch (IOException ex) {
            throw new RuntimeException("Error " + ex);
        }
    }
}