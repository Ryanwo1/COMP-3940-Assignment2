package Servlets;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;

public class ClientServlet extends HttpServlet{
    public String serverName = "localhost";
    public int portNum = 8999;
    public String filePath;
    public String fileDate;
    public String keyWord;
    public String captions;

    public void POSTReq(){
        BufferedReader reader = null;
        Path file = Paths.get(filePath);

        try{
            Socket serverSocket = new Socket(serverName, portNum);
            OutputStream outputStream = serverSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.append("POST/HTTP/1.1\r\n");
            writer.append("Content-Type: multipart/form-data;\r\n");
            writer.append("User: Client\r\n\r\n").flush();
            writer.append("----------------------------------------\r\n");
            writer.append("Content-Disposition: form-data; name=\"Date and Time\"\\r\n");
            writer.append("Content-Type: text/plain; charset=" + "UTF-8\r\n");
            writer.append("\r\n").append(fileDate).append("\r\n").flush();
            writer.append("-----------------------------------------\r\n");
            writer.append("Content-Disposition: form-data; name=\"Keyword\"\\r\n");
            writer.append("Content-Type: text/plain; charset=" + "UTF-8\r\n");
            writer.append("\r\n").append(keyWord).append("\r\n").flush();
            writer.append("-----------------------------------------\r\n");
            writer.append("-----------------------------------------\r\n");
            writer.append("Content-Disposition: form-data; name=\"Captions\"\\r\n");
            writer.append("Content-Type: text/plain; charset=" + "UTF-8\r\n");
            writer.append("\r\n").append(captions).append("\r\n").flush();
            writer.append("-----------------------------------------\r\n");
            writer.append("Content-Disposition: form-data; name=\"File\"; filename=\"" + file.getFileName() + "\\r\n");
            writer.append("Content-Type: image/png; charset=" + "\r\n");
            writer.append("Content-Encoding: binary" + "\r\n");
            writer.append("\r\n").append(fileDate).append("\r\n").flush();
            Files.copy(file, outputStream);
            outputStream.flush();
            writer.append("\r\n").flush();
            writer.append("-----------------------------------------\r\n").flush();

            BufferedReader response = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Request Error: " + e);
            e.printStackTrace();
        }
    }

    public void getUserInput(){
        fileDate = java.time.LocalDate.now().toString();
        Scanner scanner = new Scanner(System.in);
    }

    public void doGet(HttpServletResponse res, HttpServletRequest req){};
    public void doPost(HttpServletResponse res, HttpServletRequest req){};

    public static void main(String args[]){
        ClientServlet client = new ClientServlet();
        client.getUserInput();
        client.POSTReq();
    }
}
