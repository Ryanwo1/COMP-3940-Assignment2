package Servlets;

import java.io.*;
import java.util.HashMap;

public class HttpServletRequest {
   private InputStream inputStream = null;
   private String requestType = null;
   private String requestUser = "";
   private byte[] fileByteCode;
   private HashMap<String,String> multData = new HashMap<String,String>();
   public HttpServletRequest(InputStream inputStream) {
      this.inputStream = inputStream;
   }
   public InputStream getInputStream() {return inputStream;}

   public void setRequestType(String requestType){
      this.requestType = requestType;
   }

   public String getRequestType(){
      return requestType;
   }

   public void setUser(String requestUser){
      this.requestUser = requestUser;
   }

   public String getUser(){
      return requestUser;
   }

   public String getFormData(String d){
      return multData.get(d);
   }

   public byte[] getFileByteCode(){
      return fileByteCode;
   }
}