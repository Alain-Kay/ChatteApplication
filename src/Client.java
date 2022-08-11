import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 7777);

            while (true){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String messsageRecu = bufferedReader.readLine();
                System.out.println("Server : "+ messsageRecu);

                if (messsageRecu.equals("bayooo")){
                    System.out.println("serveur etteint...");
                    break;
                }

                Scanner scanner = new Scanner(System.in);
                System.out.println("Moi : ");
                String messageEnvoye = scanner.nextLine();
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println(messageEnvoye);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
