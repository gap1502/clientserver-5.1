import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 23444);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            String msg;
            while (true) {
                System.out.println("Введите целое положительное число или \"end\" для завершения программы");
                msg = scanner.nextLine();
                out.println(msg);
                if ("end".equals(msg))
                    break;
                System.out.println("Число Фибоначчи: " + in.readLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
