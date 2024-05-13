import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("netology.homework", Server.localhost)) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner console = new Scanner(System.in);
            System.out.println("Подключен к серверу. Отправьте сообщение (введите 'end' для выхода):");
            while (true) {
                String response = in.readLine(); //получение сообщения от сервера
                if (response.equalsIgnoreCase("end")) {
                    break;
                }
                System.out.println("Server: " + response); //получение сообщения от сервера
                String message = console.nextLine(); //отправление сообщение серверу
                if (message.equalsIgnoreCase("end")) {
                    break;
                }
                out.println(message);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
