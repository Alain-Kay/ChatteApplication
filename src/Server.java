
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    // Methode permettant de demarrer le serveur
        public void demarrerServer() {

            try {
                while (!serverSocket.isClosed()) {
                    Socket socket = serverSocket.accept();
                    System.out.println(" nouveau client connecte");
                    ClientHandler clientHandler = new ClientHandler(socket);

                    Thread thread = new Thread(clientHandler);
                    thread.start();

                }

                }catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }


    //Methode permettant de fermer le serveur
            public void fermerServer() {
                try {
                    if (serverSocket != null) {
                        serverSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public static void main(String[] args) throws IOException {
                ServerSocket serverSocket  = new ServerSocket(7777);
                Server server = new Server(serverSocket);
                server.demarrerServer();

            }




    }



    //    public static void main(String[] args)  {
//        try {
//            ServerSocket serverSocket  = new ServerSocket(7777);
//            Socket clienSocket = serverSocket.accept();
//            System.out.println("client connecte");
//
//            while (true){
//                Scanner scanner = new Scanner(System.in);
//                System.out.println("Moi : ");
//                String messageEnvoye = scanner.nextLine();
//                PrintWriter printWriter = new PrintWriter(clienSocket.getOutputStream(), true);
//
//                if (messageEnvoye.equalsIgnoreCase("exit")){
//                    printWriter.println("bayooo");
//                    serverSocket.close();
//                    break;
//                }
//                printWriter.println(messageEnvoye);
//
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));
//                String messageRecu = bufferedReader.readLine();
//                System.out.println("client : "+ messageRecu);
//
//            }
//
//
//
//        }catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//
//    }
