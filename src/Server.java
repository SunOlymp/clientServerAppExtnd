import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final Integer localhost = 8080;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(localhost)) { //Порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
            System.out.println("Сервер стартовал");
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("New connection accepted");
                    out.println("Соединение с сервером установлено");
                    out.println("Write your name");
                    final String name = in.readLine();
                    System.out.println("Client: " + name);
                    out.println("Are you a Child? (yes/no)");
                    String answer = in.readLine();
                    if (answer != null && !answer.isEmpty()) {
                        if ("yes".equals(answer)) {
                            out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                        }
                        if ("no".equals(answer)) {
                            out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                        }
                    }
                } catch (IOException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error: " + ex);
        }
    }
}