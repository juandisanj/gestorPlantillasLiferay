package com.liferay.docs.portlet.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class DownloadFilesZipUtil {
	
	public static File createFolder(String type, String path) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy_HHmmss");
		Date date = new Date();
		String dateString = sdf.format(date);
		
		String username = System.getProperty("user.name");
		
		File dir = null;
		if("ftl".equals(type)) {
			dir = new File("/" + path + "/Temp_ftl_" + dateString);
		}else if("adt".equals(type)) {
			dir = new File("/" + path + "/Temp_adt_" + dateString);
		}else if("str".equals(type)) {
			dir = new File("/" + path + "/Temp_str_" + dateString);
		}
		dir.mkdir();
		
		return dir;
	}
	
	public static void createFile(File folder, String name, String content) throws FileNotFoundException, UnsupportedEncodingException {
		
		PrintWriter writer = new PrintWriter(folder + "/" + name + ".ftl", "UTF-8");
		writer.println(content);
		writer.close();
	}
	
	public static void exportFolderToZip(File folder, String path) {
		
		File[] allFiles = folder.listFiles();
		
		byte[] buf = new byte[1024];
		try {
			String desktopPath = System.getProperty("user.home") + "/Desktop/";
			
			String target = path + "/" + folder.getName() + ".zip";
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target));
			for (int i=0; i<allFiles.length; i++) {
	            FileInputStream in = new FileInputStream(allFiles[i]);
	    
	            // Add ZIP entry to output stream.
	            out.putNextEntry(new ZipEntry(allFiles[i].getPath()));

	            // Transfer bytes from the file to the ZIP file
	            int len;
	            while ((len = in.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }

	            // Complete the entry
	            out.closeEntry();
	            in.close();
	        }

	        // Complete the ZIP file
	        out.close();
		}catch(IOException e) {
			e.getMessage();
		}
		
		// Eliminar la carpeta que se quiere convertir
		try{
			deleteFolder(folder);
		}catch(Exception e) {
			e.getMessage();
		}
		
	}
	
	private static void deleteFolder(File folder) {
		File[] allFiles = folder.listFiles();
		
		for (int i=0; i<allFiles.length; i++) {
			allFiles[i].delete();
		}
		folder.delete();
		
	}

}
