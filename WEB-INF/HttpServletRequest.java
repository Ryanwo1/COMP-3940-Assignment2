import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpServletRequest {
   private InputStream inputStream = null;

   public HttpServletRequest(InputStream inputStream) throws IOException {
      this.inputStream = inputStream;
      System.out.println("This is the inputStream " + inputStream.toString());
     BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
      String firstLine = bufferedReader.readLine(); // GET / HTTP/1.1
      System.out.println("This is the first line of the stream: " + firstLine);

      String httpMethod = firstLine.split(" ")[0]; //Host: localhost:4999
       System.out.println(Arrays.toString(firstLine.split(" ")));
       System.out.println("This is the first line (and http method) of the stream " + httpMethod);

      String url = firstLine.split(" ")[1];
      System.out.println("This is the second line (and url) of the stream " + url);

      /* final Map<String, String> headers = new HashMap<>();
      String headerLine;
      while ((headerLine = bufferedReader.readLine()) != null) {
         if (headerLine.trim().isEmpty()) {
            break;
         }
      }
      /* String key = headerLine.split("\\s")[0];
      System.out.println("This is the key: " + key);

      String value = headerLine.split("\\s")[1];

     headers.put(key, value);
     */

   }
   public InputStream getInputStream() {

      return inputStream;
   }
}