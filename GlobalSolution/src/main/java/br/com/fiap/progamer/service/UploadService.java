package br.com.fiap.progamer.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.file.UploadedFile;

public abstract class UploadService {

	private static ServletContext servletContext= (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    private static String path=servletContext.getRealPath("/");
    
    private static final String DESTINATION = path;
	
    public static String write(UploadedFile file, String folder) {
    	String fileName = file.getFileName();
    	String relativePath = "\\" + folder + "\\" + fileName;

    	try {
    		InputStream in = file.getInputStream();
            OutputStream out = new FileOutputStream(new File(DESTINATION + relativePath));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("New file uploaded: " + (DESTINATION + relativePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativePath;
    }

}