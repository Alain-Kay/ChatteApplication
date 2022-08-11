
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args)  {
        try {
            ServerSocket serverSocket  = new ServerSocket(7777);
            Socket socket = serverSocket.accept();
            System.out.println("client connecte");

            while (true){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Moi : ");
                String messageEnvoye = scanner.nextLine();
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

                if (messageEnvoye.equalsIgnoreCase("exit")){
                    printWriter.println("bayooo");
                    serverSocket.close();
                    break;
                }
                printWriter.println(messageEnvoye);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String messageRecu = bufferedReader.readLine();
                System.out.println("client : "+ messageRecu);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
