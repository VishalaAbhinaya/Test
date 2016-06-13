package com.modeln.sqin.packaging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
 
/**
 * Acts as a client to the SQINInstallation servlet to
 * upload the SQIN Library Archive to the server
 */
public class SQINLibraryLoader {

	static final int BUFFER_SIZE = 4096;
 
    /**
     * @param args - args[0] - the local path of the file to be uploaded
     *               args[1] - the URL of the SQINInstallation servlet
     *               args[2] - debug flag. If true, servlet will print debug statements
     *               
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
    	/**
    	 * validates usage arguments have been provided
    	 */
       if(args.length < 3){
          System.out.println("******************** Invalid Arguments ****************************");
          System.out.println("* First argument is Local path of archive to be uploaded          *");
          System.out.println("* Second argument is SQINInstallation servlet URL                 *");
          System.out.println("* Third argument is debug flag to be passed to servlet            *");
          System.out.println("*******************************************************************");

       }else{
    	
	      // takes file path from first program's argument
	      String filePath = args[0];
	      final String UPLOAD_URL = args[1];
	      String debug = args[2];
	      
	      File uploadFile = new File(filePath);
	 
	      System.out.println("File to upload: " + filePath);
	 
	      // creates a HTTP connection
	      URL url = new URL(UPLOAD_URL);
	      HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	      httpConn.setUseCaches(false);
	      httpConn.setDoOutput(true);
	      httpConn.setRequestMethod("POST");
	      
	      /**
	       *  sets HTTP Header attributes 
	       *  a) file name
	       *  b) Servlet debug flag
	       */
	      httpConn.setRequestProperty("fileName", uploadFile.getName());
	      httpConn.setRequestProperty("debug", debug);

	      
	      // opens output stream of the HTTP connection for writing data
	      OutputStream outputStream = httpConn.getOutputStream();
	 
	      // Opens input stream of the file for reading data
	      FileInputStream inputStream = new FileInputStream(uploadFile);
	 
	      byte[] buffer = new byte[BUFFER_SIZE];
	      int bytesRead = -1;
	 
	      System.out.println("Start writing data...");
	 
	      while ((bytesRead = inputStream.read(buffer)) != -1) {
	         outputStream.write(buffer, 0, bytesRead);
	      }
	 
	      System.out.println("Data was written.");
	      outputStream.close();
	      inputStream.close();
	 
	      // always check HTTP response code from server
	      int responseCode = httpConn.getResponseCode();
	      BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
	      String response = reader.readLine();	      
	      if (responseCode == HttpURLConnection.HTTP_OK) {
	         System.out.println("Upload of SQIN Library was successful - Server's response: " + response);
	      } else {
	         System.out.println("Upload of SQIN Library was not successfull - Server returned non-OK responseCode: " + responseCode);
	         System.out.println("response was : " + response);
	      }
	        
       }
   
    }

}