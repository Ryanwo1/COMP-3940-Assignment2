import java.net.*;
import java.io.*;
public class UploadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        final int portNumber = 4999;

        try {
            serverSocket = new ServerSocket(portNumber);
            // PrintWriter toServerPrintWriter= new PrintWriter(socket.getOutputStream());

            System.out.println("Server up on port " + portNumber);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + portNumber + ".");
            System.exit(-1);
        }
        while (true) {
            // new UploadServerThread(serverSocket.accept()).start();
            Socket client = serverSocket.accept();
            // final InputStream inputStream = client.getInputStream();
            // final OutputStream outputStream = client.getOutputStream();
            UploadServerThread clientUploadServerThread = new UploadServerThread(client);
            clientUploadServerThread.start();
            System.out.println("New client");
        }
    }
}

