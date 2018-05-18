package com.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * �ļ�����
 * @version 2017_11_14
 * @author Lulech
 * */

public class FileIO {
	
	/**
	 * ���Ƶ����ļ�
	 * @param ԭ�ļ�·��
	 * @param ��Ҫ���Ƶ����ļ�·��
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
	 * �����ļ��м�������
	 * @param ԭ�ļ�·��
	 * @param ��Ҫ���Ƶ����ļ�·��
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
	 * ����ļ�·���Ƿ���ڣ������ھ��½�һ��
	 * @param �ļ�
	 * @return void
	 * */
	public static void checkAndCreateFile(File file){
		if(!file.exists()){
			File pfile = file.getParentFile();
			if(!pfile.exists()) pfile.mkdirs();
		}
	}
}
