import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;


    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        }catch (IOException e){
            fermerTout(socket, bufferedReader, bufferedWriter);
        }

    }

    //Mwthode permettant d'envoyer un message
    public void envoyerMessage(){
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()){
                String messageEnvoyer = scanner.nextLine();
                bufferedWriter.write(username + messageEnvoyer);
                bufferedWriter.newLine();
                bufferedWriter.flush();

            }
        }catch (IOException e){
            fermerTout(socket,bufferedReader, bufferedWriter);

        }
    }


    public void listerDeMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgEnGroupChat;

                while (socket.isConnected()){
                    try {
                        msgEnGroupChat = bufferedReader.readLine();
                        System.out.println(msgEnGroupChat);
                    }catch (IOException e){
                        fermerTout(socket,bufferedReader,bufferedWriter);
                    }
                }
            }
        }).start();

    }

    public void fermerTout(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez votre nom d'utilisateur pour le groupe : ");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 7777);
        Client client = new Client(socket, username);
        client.listerDeMessage();
        client.envoyerMessage();

    }
}
