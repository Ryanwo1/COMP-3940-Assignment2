import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Client {
    public String filePath;
    public String dateData = "";
    public String captionData = "";
/*    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        try(Socket socket = new Socket("localhost", 8999)){
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while(true){
                System.out.print("Input> ");
                String message = scanner.nextLine();
                dataOutputStream.writeUTF(message);
                if(message.equalsIgnoreCase("exit()"))
                    break;
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }*/

    public void POSTreq(){
        BufferedReader reader = null;
        Path file = Paths.get(filePath);

        try{
            Socket socket = new Socket("localhost", 8999);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.append("POST/HTTP/1.1\r\n");
            writer.append("Content-Disposition: multipart/form-data; name=\"File\"; filename=\"" + file.getFileName() + "\"\r\n");
            writer.append("Content-Type: image\r\n");
            writer.append("Content-Transfer-Encoding: binary\r\n");
            writer.append("\r\n").flush();
            Files.copy(file, outputStream);
            outputStream.flush();
            writer.append("\r\n").flush();
            BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socket.close();
        }catch(Exception e){
            System.out.println("error: " + e);
        }
    }

    public static void main(String args[]){
        Client client = new Client();
        client.POSTreq();
    }
}
