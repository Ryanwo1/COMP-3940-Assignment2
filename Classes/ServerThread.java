import java.net.*;
import java.io.*;
import java.time.Clock;

public class ServerThread extends Thread{
    private Socket socket = null;
    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
         Inputstream in = socket.getInputStream();
         HttpServletRequest request = new HttpServletRequest(in);
         OutputStream baos = new ByteArrayOutputStream();
         HttpServletResponse response = new HttpServletResponse(socket, baos);

        }
    }
}
