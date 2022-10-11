package Servlets;

import java.io.*;
import java.net.Socket;
import java.time.format.DateTimeFormatter;

public class HttpServletResponse {
   private PrintWriter printWriter;
   private String charEncode;
   private String contentType;
   private Socket socket = null;
   private ByteArrayOutputStream bao;
   private ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
   DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM, dd, yyyy HH:mm:ss");

   public HttpServletResponse(ByteArrayOutputStream outputStream) {
      this.bao = outputStream;
      printWriter = new PrintWriter(bao);
   }

   public HttpServletResponse(Socket socket, ByteArrayOutputStream outputStream){
      this.bao = outputStream;
      this.socket = socket;
      printWriter = new PrintWriter(bao);
   }

   public void send(String page){
      try{
         OutputStream output = socket.getOutputStream();
         String html = page;

         String response = "HTTP/1.1 200 OK" + "\r\n" + "Content-Length: " + html.getBytes().length + "\r\n" + "\r\n" +
                 page + html + "\r\n" + "\r\n";

         bao.write(response.getBytes());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void createHeader() throws IOException{
      String headerString = "GET /" + "HTTP 1.1" + "\r\n" + "Date: " + "LocalDateTime.now()" + "\r\n" + "Content-Type: "
              + contentType + "\r\n" + "Character-Encoding: " + charEncode + "\r\n";
      bao.write(headerString.getBytes());
   }
   public OutputStream getOutputStream() {return bao;}

   public byte[] byteArrayCreation(){
      try{
         createHeader();
         bao.write(outputStream.toByteArray());
         bao.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return bao.toByteArray();
   }

   public ByteArrayOutputStream getByteArray(){
      return outputStream;
   }

   public PrintWriter getWriter(){
      return printWriter;
   }

   public void setCharEncode(String s){
      this.charEncode = s;
   }

   public void setContentType(String c){
      this.contentType = c;
   }
}