package com.fileio;

import java.io.File;
import java.util.ArrayList;


/*
 * 获取所有指定尾缀的文件的路径返回文件路径字符串数组
 * */
public class PathGeter {
	private ArrayList<String> xmlfilelist;
	private ArrayList<String> imagefilelist;
	private ArrayList<String> textfilelist;
	
	/*获取路径前需要初始化*/
	public void init(){
		xmlfilelist = new ArrayList<>();
		imagefilelist = new ArrayList<>();
		textfilelist = new ArrayList<>();
	}
	
	/*获取指定路径下尾缀为xml的文件路径*/
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
	
	/*获取指定路径下尾缀为png，PNG，jpg，JPG的文件路径*/
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
	
	/*获取指定路径下文件尾缀为txt的文件的文件路径*/
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
	
	
	/*获取指定路径下尾缀为png,PNG的文件路径*/
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
