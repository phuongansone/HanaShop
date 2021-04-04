/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * Utils for manipulating file
 * @author andtpse62827
 */
public class FileUtils {
    /** build folder that will be refreshed each time you deploy the web */
    public static final String BUILD_DIR = "build";
    
    /**
     * Generate file name with now in milliseconds
     * @param fileName
     * @return new file name
     */
    public static String generateFileNameWithMilliseconds(String fileName) {
        return System.currentTimeMillis() + fileName;
    }
    /**
     * Get file path with current time in milliseconds
     * @param applicationPath path to the application
     * @param folderName name of parent folder
     * @param fileName name of file
     * @return absolute path to the file
     * @throws java.io.IOException
     */
    public static String getFilePath(String applicationPath, String folderName, 
            String fileName) throws IOException {
        String folderPath = applicationPath + File.separator 
                + folderName + File.separator;
        Files.createDirectories(Paths.get(folderPath));
        return folderPath + fileName;
    }
    
    /**
     * Save Http part to file
     * @param inputPart http part
     * @param outputFilePath file path
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void saveHttpPartToFile(Part inputPart, String outputFilePath) 
           throws FileNotFoundException, IOException  {
        // file to be saved
        File outputFile = new File(outputFilePath);
        
        // get input and output stream
        InputStream inputStream = null;
        OutputStream outputStream = null;
        
        try {
            // get input and output stream
            inputStream = inputPart.getInputStream();
            outputStream = new FileOutputStream(outputFile);

            // number of bytes read from input stream
            int read;

            // buffer to store data read from input stream
            final byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            Logger.getLogger(FileUtils.class.getName()).log(Level.INFO, 
                    "File {0} is saved.", new Object[]{outputFilePath});
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
    
    /**
     * Get app path
     * @param request
     * @return path to the application folder
     */
    public static String getAppPath(HttpServletRequest request) {
        String deployedAppPath = request.getServletContext().getRealPath("");
        int index = deployedAppPath.lastIndexOf(BUILD_DIR);
        return deployedAppPath.substring(0, index);
    }
}
