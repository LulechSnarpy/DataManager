package com.fileio;

import java.io.File;
import java.util.ArrayList;


/*
 * ��ȡ����ָ��β׺���ļ���·�������ļ�·���ַ�������
 * */
public class PathGeter {
	private ArrayList<String> xmlfilelist;
	private ArrayList<String> imagefilelist;
	private ArrayList<String> textfilelist;
	
	/*��ȡ·��ǰ��Ҫ��ʼ��*/
	public void init(){
		xmlfilelist = new ArrayList<>();
		imagefilelist = new ArrayList<>();
		textfilelist = new ArrayList<>();
	}
	
	/*��ȡָ��·����β׺Ϊxml���ļ�·��*/
	public  ArrayList<String> getXmlFiles(String filePath){
	     File root = new File(filePath);
	       File[] files = root.listFiles();
	       for(File file:files){     
		        if(file.isDirectory()){
		        	getXmlFiles(file.getAbsolutePath());
		        }
		        else{
		        	
		        	String s = file.getAbsolutePath();
		        	if( s.endsWith(".xml")|| s.endsWith(".XML") )
		        	{
			        	xmlfilelist.add(file.getAbsolutePath());
		             }
		        }  
	       }
	    return xmlfilelist;
	}
	
	/*��ȡָ��·����β׺Ϊpng��PNG��jpg��JPG���ļ�·��*/
	public ArrayList<String> getImageFiles(String filePath){
	     File root = new File(filePath);
	       File[] files = root.listFiles();
	       for(File file:files){     
		        if(file.isDirectory()){
		        	getImageFiles(file.getAbsolutePath());
		        }
		        else{
		        	
		        	String s = file.getAbsolutePath();
		        	if( s.endsWith("png") || s.endsWith("PNG") || s.endsWith("jpg") || s.endsWith("JPG"))
		        	{
		        		imagefilelist.add(file.getAbsolutePath());
		             }
		        }  
	       }
	    return imagefilelist;
	}
	
	/*��ȡָ��·�����ļ�β׺Ϊtxt���ļ����ļ�·��*/
	public ArrayList<String> getTextFiles(String filePath){
	     File root = new File(filePath);
	       File[] files = root.listFiles();
	       for(File file:files){     
		        if(file.isDirectory()){
		        	getTextFiles(file.getAbsolutePath());
		        }
		        else{
		        	
		        	String s = file.getAbsolutePath();
		        	if( s.endsWith(".txt") || s.endsWith(".TXT") )
		        	{
		        		textfilelist.add(file.getAbsolutePath());
		             }
		        }  
	       }
	    return textfilelist;
	}
	
	
	/*��ȡָ��·����β׺Ϊpng,PNG���ļ�·��*/
	public ArrayList<String> getImageFiles(String filePath,boolean f){
	     File root = new File(filePath);
	       File[] files = root.listFiles();
	       for(File file:files){     
		        if(file.isDirectory()){
		        	getImageFiles(file.getAbsolutePath());
		        }
		        else{
		        	
		        	String s = file.getAbsolutePath();
		        	if( s.endsWith("png") || s.endsWith("PNG"))
		        	{
		        		imagefilelist.add(file.getAbsolutePath());
		             }
		        }  
	       }
	    return imagefilelist;
	}
}
