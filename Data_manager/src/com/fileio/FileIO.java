package com.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIO {
	
	public static void copySingleFile(String oldpath,String newpath) throws IOException{
		File ofile = new File(oldpath);
		File nfile = new File(newpath);
		checkAndCreateFile(nfile);
		FileInputStream in= new FileInputStream(ofile);
		FileOutputStream out= new FileOutputStream(nfile);
		int temp;
		while((temp = in.read()) != -1){
			out.write(temp);
		}
		in.close();
		out.close();
	}
	
	public static void copyAllFile(String oldpath,String newpath) throws IOException{
		 File root = new File(oldpath);
	     File[] files = root.listFiles();
	     for(File file:files){     
	    	 if(file.isDirectory()){
	    		 copyAllFile(file.getAbsolutePath(),newpath+"\\"+file.getName());
	    	 }
	    	 else{
	    		 String s = file.getAbsolutePath();
	    		 copySingleFile(s, newpath+"\\"+file.getName());
	    	 }  
	     }
	}
	
	public static void checkAndCreateFile(File file) throws IOException{
		if(!file.exists()){
			File pfile = file.getParentFile();
			if(!pfile.exists()) pfile.mkdirs();
			file.createNewFile();
		}
	}
}
