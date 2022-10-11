package Servlets;

import java.net.*;
import java.io.*;

public class UploadServerThread extends Thread {
   private Socket socket = null;
   public UploadServerThread(Socket socket) {
      //super("DirServerThread");
      this.socket = socket;
   }

   public void run() {
      System.out.println("Running thread");
   }
}
