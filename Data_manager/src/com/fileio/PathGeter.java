package com.fileio;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * 获取所有指定尾缀的文件的路径返回文件路径字符串数组
 * @version 2017_11_14
 * @author Lulech
 * */
public class PathGeter {
	private LinkedList<String> filelist;
	
	/*获取路径前需要初始化*/
	private void init(){
		filelist = new LinkedList<>();
	}
	
	/*获取指定路径下尾缀为xml的文件路径*/
	/**
	 * 获取xml格式的文件路径
	 * @param 文件夹路径
	 * @return ArrayList<String>
	 * */
	public  ArrayList<String> getXmlFiles(String filePath){
		init();
		File root = new File(filePath);
		PathGeter.FileFilerPlus ft = new PathGeter.FileFilerPlus(".xml,".split(","));
		searchFile(root, ft);
	    return new ArrayList<>(filelist);
	}
	
	/*获取指定路径下尾缀为png，PNG，jpg，JPG的文件路径*/
	/**
	 * 获取尾缀是png和jpg的文件
	 * @param 文件夹路径
	 * @return ArrayList<String>
	 * */
	public ArrayList<String> getImageFiles(String filePath){
		init();
		File root = new File(filePath);
		PathGeter.FileFilerPlus ft = new PathGeter.FileFilerPlus(".jpg,.png".split(","));
		searchFile(root, ft);
	    return new ArrayList<>(filelist);
	}
	
	/*获取指定路径下文件尾缀为txt的文件的文件路径*/
	/**
	 *获取尾缀是txt的文件
	 *@param 文件夹路径
	 *@return ArrayList<String>
	 * */
	public ArrayList<String> getTextFiles(String filePath){
		init();
		File root = new File(filePath);
		PathGeter.FileFilerPlus ft = new PathGeter.FileFilerPlus(".txt,".split(","));
		searchFile(root, ft);
	    return new ArrayList<>(filelist);
	}
	
	
	/*获取指定路径下尾缀为png,PNG的文件路径*/
	/**
	 * 获取尾缀是png的文件
	 * @param 文件夹路径
	 * @return ArrayList<String>
	 * */
	public ArrayList<String> getImageFiles(String filePath,boolean f){
		init();
		File root = new File(filePath);
		PathGeter.FileFilerPlus ft = new PathGeter.FileFilerPlus(".png,".split(","));
		searchFile(root, ft);
	    return new ArrayList<>(filelist);
	}
	

	/**
	 * 获取所有文件
	 * @param 文件夹路径
	 * @return ArrayList<String>
	 * */
	public ArrayList<String> getFiles(String filePath){
		init();
		File root = new File(filePath);
		PathGeter.FileFilerPlus ft = new PathGeter.FileFilerPlus();
		searchFile(root, ft);
	    return new ArrayList<>(filelist);
	}
	
	/**
	 * 获取自定义尾缀文件
	 * @param 文件夹路径
	 * @param 文件尾缀小写String[]
	 * @return ArrayList<String>
	 * */
	public ArrayList<String> getDefinedFiles(String filePath,String[] ends){
		init();
		File root = new File(filePath);
		PathGeter.FileFilerPlus ft = new PathGeter.FileFilerPlus(ends);
		searchFile(root, ft);
	    return new ArrayList<>(filelist);
	}
	
	/*使用过滤器遍历查找文件*/
	private void searchFile(File file,FileFilter ft){
		File[] dirs = file.listFiles(PathGeter.DirFilter.getOnly());
		File[] files = file.listFiles(ft);
		if(dirs==null) return;
		for(File f:dirs) searchFile(f, ft);
		for(File f: files) filelist.add(f.getAbsolutePath());
	}
	
	/**
	 * 单例文件路径过滤器
	 * @version 2017_11_14
	 * @author Lulech
	 * */
	static class DirFilter implements FileFilter{
		private static FileFilter only = new DirFilter();
		private DirFilter(){}
		public boolean accept(File pathname) {
			return  pathname.exists()&&pathname.isDirectory();
		}
		public static FileFilter getOnly(){
			return only;
		}
	}
	
	/**
	 * 自定义文件路径过滤器
	 * @version 2017_11_14
	 * @author Lulech
	 * */
	static class FileFilerPlus implements FileFilter{
		private String[] endTypes;
		public FileFilerPlus(){}
		public FileFilerPlus(String[] endTypes){
			this.endTypes = endTypes;
		}
		@Override
		public boolean accept(File pathname) {
			if(!pathname.exists()||!pathname.isFile()) return false;
			if(endTypes==null) return true;
			for(String end: endTypes){
				if(pathname.getName().toLowerCase().endsWith(end)) return true;
			}
			return false;
		}
	}
}

