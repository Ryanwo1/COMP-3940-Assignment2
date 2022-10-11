import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public abstract class HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response) {
      return;
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(request.getInputStream()));

      String firstLine = bufferedReader.readLine(); // GET / HTTP/1.1
      String httpMethod = firstLine.split("\\s+")[0]; //Host: localhost:4999

      String url = firstLine.split("\\s+")[1];
      final Map<String, String> headers = new HashMap<>();
      String headerLine;
      while ((headerLine = bufferedReader.readLine()) != null) {
            if (headerLine.trim().isEmpty()) {
               break;
            }
      }
      String key = headerLine.split("\\s")[0];
      String value = headerLine.split("\\s")[1];

      headers.put(key, value);

      return;
   };
}