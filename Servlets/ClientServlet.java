package Servlets;

import java.io.*;
import java.net.*;
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
        String charset = "UTF-8";
        Path file = Paths.get(filePath);

        try{
            Socket serverSocket = new Socket(serverName, portNum);
            OutputStream outputStream = serverSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, charset));

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
