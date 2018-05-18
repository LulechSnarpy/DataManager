package com.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件复制
 * @version 2017_11_14
 * @author Lulech
 * */

public class FileIO {
	
	/**
	 * 复制单个文件
	 * @param 原文件路径
	 * @param 需要复制到的文件路径
	 * @return void
	 * @exception IOExecption
	 * */
	public static void copySingleFile(String oldpath,String newpath){
		File ofile = new File(oldpath);
		File nfile = new File(newpath);
		checkAndCreateFile(nfile);
		try(FileInputStream in= new FileInputStream(ofile);
				FileOutputStream out= new FileOutputStream(nfile)){
			int temp;
			byte[] data = new byte[65536];
			while((temp = in.read(data)) != -1){
				out.write(data,0,temp);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 复制文件夹及其内容
	 * @param 原文件路径
	 * @param 需要复制到的文件路径
	 * @return void
	 * @exception IOException
	 * */
	public static void copyAllFile(String oldpath,String newpath){
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
	
	/**
	 * 检查文件路径是否存在，不存在就新建一个
	 * @param 文件
	 * @return void
	 * */
	public static void checkAndCreateFile(File file){
		if(!file.exists()){
			File pfile = file.getParentFile();
			if(!pfile.exists()) pfile.mkdirs();
		}
	}
}
