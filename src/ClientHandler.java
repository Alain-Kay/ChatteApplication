import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String nomClient;

    public ClientHandler(Socket socket){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.nomClient = bufferedReader.readLine();
            clientHandlers.add(this);
            diffusionMessage( "SERVER :"+ nomClient + "s'est connecté");

        }catch (IOException e){
            fermerTout(socket, bufferedReader, bufferedWriter);
        }

    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        String messageClient;

        while (socket.isConnected()){
            try {
                messageClient = bufferedReader.readLine();
                diffusionMessage(messageClient);
            }catch (IOException e){
                 fermerTout(socket, bufferedReader, bufferedWriter);
                 break;
            }
        }

    }

    public void diffusionMessage(String messageEnvoyer){
        for (ClientHandler clientHandler : clientHandlers){
            try {
                if (!clientHandler.nomClient.equals(nomClient)){
                    clientHandler.bufferedWriter.write(messageEnvoyer);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();

                }

            }catch (IOException e){
                fermerTout(socket, bufferedReader, bufferedWriter);
            }
        }

    }

    public void effacerClient(){
        clientHandlers.remove(this);
        diffusionMessage("SERVER: " + nomClient + " a quitte le chat");
    }

    public void fermerTout(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){

        effacerClient();
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
}
