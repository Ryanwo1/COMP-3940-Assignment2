import java.net.*;
import java.io.*;
public class UploadServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        final int portNumber = 4999;
        String httpRequest = "GET / HTTP/1.1";
        String hostHeader = "Host: ";
        try {
            serverSocket = new ServerSocket(portNumber);
            Socket socket = serverSocket.accept();
            PrintWriter toServerPrintWriter= new PrintWriter(socket.getOutputStream());
            toServerPrintWriter.println(httpRequest);
            toServerPrintWriter.println(hostHeader);
            toServerPrintWriter.println();
            toServerPrintWriter.flush();

            BufferedReader fromServerBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String str;
            while ((str = fromServerBufferedReader.readLine()) != null) {
                System.out.println(str);
            }
            System.out.println("Server up on port " + portNumber);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + portNumber + ".");
            System.exit(-1);
        }
        while (true) {
            new UploadServerThread(serverSocket.accept()).start();
        }
    }
}

