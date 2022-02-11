import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//используем блокирующий ввод - вывод, т.к. у нас только одна операция для пользователя
//параллельность операций не требуется

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket servSocket = new ServerSocket(23444);
        while (true) {
            try (Socket socket = servSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                String line;
                while ((line = in.readLine()) != null) {
                   long n = Long.parseLong(line);
                    if (n == 0) {
                        out.println(0);
                    } else {
                        long a = 0;
                        long b = 1;
                        for (long i = 2; i <= n; ++i) {
                            long next = a + b;
                            a = b;
                            b = next;
                        }
                        out.println(b);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
