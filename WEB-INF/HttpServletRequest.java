import java.io.*;
import java.util.Map;

public class HttpServletRequest {
   private InputStream inputStream = null;

   public HttpServletRequest(InputStream inputStream) {
      this.inputStream = inputStream;
   }
   public InputStream getInputStream() {
      return inputStream;
   }
}