import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final Integer localhost = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(localhost)) {
            System.out.println("Сервер стартовал");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     Scanner console = new Scanner(System.in)) {
                    System.out.println("Введите сообщение (введите 'end' для выхода):");
                    while (true) {
                        String message = console.nextLine(); //отправление клиенту
                        if (message.equalsIgnoreCase("end")) {
                            break;
                        }
                        out.println(message); //отправление клиенту
                        String response = in.readLine(); //получение от клиента
                        if (response.equalsIgnoreCase("end")) {
                            break;
                        }
                        System.out.println("Client: " + response);
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
