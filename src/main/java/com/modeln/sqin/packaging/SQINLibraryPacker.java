package com.modeln.sqin.packaging;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Packages the SQIN library into a zip file,
 * which is the format expected by the SQINLibraryLoader
 * client class
 */
public class SQINLibraryPacker {

	static final int BUFFER_SIZE = 4096;

    /**
     * @param args - args[0] - the path to the directory where the sqin library files are located
     *               args[1] - the path to where the zip file will be created
     *               args[2] - debug flag. If true, servlet will print debug statements
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

    	/**
    	 * validates usage arguments have been provided
    	 */
       if(args.length < 3){
          System.out.println("******************** Invalid Arguments *************************************");
          System.out.println("* First argument is path to directory where sqin library files are located *");
          System.out.println("* Second argument is path (full qualified) where zip file will be saved    *");
          System.out.println("* Third argument is debug flag to be passed to servlet                     *");
          System.out.println("****************************************************************************");

       }else{

    	  FileOutputStream fos = null;
    	  ZipOutputStream zos = null;

 	      //reads call arguments
 	      String sqinLibPath = args[0];
 	      String zipFilePath = args[1];
 	      String debug = args[2];

          try {

     		 File sqinLibDir = new File(sqinLibPath);
     		 //checks if sqin library path is in fact a directory
     		 if(sqinLibDir.isDirectory()){
        	    fos = new FileOutputStream(zipFilePath);
    		    zos = new ZipOutputStream(fos);

                File[] directoryListing = sqinLibDir.listFiles();
    		    if (directoryListing != null) {
     		        for (File currFile : directoryListing) {
     		           if (currFile.isDirectory()) {
     		              addFolderToZip(currFile, currFile.getName(), zos);
     		           } else {
     		              addFileToZip(currFile, zos);
     		           }
     		        }
    		    }

    		    zos.close();
    		    fos.close();
    		 }

          } catch (FileNotFoundException e) {
    				e.printStackTrace();
          } catch (IOException e) {
    				e.printStackTrace();
    	  }finally{
    		  try{
 		         if(zos!=null){
     		        zos.close();
 		         }
 		         if(fos != null){
 		            fos.close();
 		         }
     		  }catch(IOException ioe){
     			 ioe.printStackTrace();
     		  }
     	   }

       }
    }


    public static void addFileToZip(File file, ZipOutputStream zos) throws FileNotFoundException, IOException {

       System.out.println("Writing '" + file + "' to zip file");

       FileInputStream fis = new FileInputStream(file);
       ZipEntry zipEntry = new ZipEntry(file.getName());
       zos.putNextEntry(zipEntry);

       byte[] bytes = new byte[1024];
       int length;
       while ((length = fis.read(bytes)) >= 0) {
          zos.write(bytes, 0, length);
       }

       zos.closeEntry();
       fis.close();
    }


    public static void addFolderToZip(File folder, String parentFolder, ZipOutputStream zos) throws FileNotFoundException, IOException {

    	for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                addFolderToZip(file, parentFolder + "/" + file.getName(), zos); //reverting to forward slash
                continue;
            }

            zos.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

            long bytesRead = 0;
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;

            while ((read = bis.read(bytesIn)) != -1) {
                zos.write(bytesIn, 0, read);
                bytesRead += read;
            }

            zos.closeEntry();

        }
    }


}