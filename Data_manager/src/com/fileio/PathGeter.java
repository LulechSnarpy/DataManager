package com.fileio;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * ��ȡ����ָ��β׺���ļ���·�������ļ�·���ַ�������
 * @version 2017_11_14
 * @author Lulech
 * */
public class PathGeter {
	private LinkedList<String> filelist;
	
	/*��ȡ·��ǰ��Ҫ��ʼ��*/
	private void init(){
		filelist = new LinkedList<>();
	}
	
	/*��ȡָ��·����β׺Ϊxml���ļ�·��*/
	/**
	 * ��ȡxml��ʽ���ļ�·��
	 * @param �ļ���·��
	 * @return ArrayList<String>
	 * */
	public  ArrayList<String> getXmlFiles(String filePath){
		init();
		File root = new File(filePath);
		PathGeter.FileFilerPlus ft = new PathGeter.FileFilerPlus(".xml,".split(","));
		searchFile(root, ft);
	    return new ArrayList<>(filelist);
	}
	
	/*��ȡָ��·����β׺Ϊpng��PNG��jpg��JPG���ļ�·��*/
	/**
	 * ��ȡβ׺��png��jpg���ļ�
	 * @param �ļ���·��
	 * @return ArrayList<String>
	 * */
	public ArrayList<String> getImageFiles(String filePath){
		init();
		File root = new File(filePath);
		PathGeter.FileFilerPlus ft = new PathGeter.FileFilerPlus(".jpg,.png".split(","));
		searchFile(root, ft);
	    return new ArrayList<>(filelist);
	}
	
	/*��ȡָ��·�����ļ�β׺Ϊtxt���ļ����ļ�·��*/
	/**
	 *��ȡβ׺��txt���ļ�
	 *@param �ļ���·��
	 *@return ArrayList<String>
	 * */
	public ArrayList<String> getTextFiles(String filePath){
		init();
		File root = new File(filePath);
		PathGeter.FileFilerPlus ft = new PathGeter.FileFilerPlus(".txt,".split(","));
		searchFile(root, ft);
	    return new ArrayList<>(filelist);
	}
	
	
	/*��ȡָ��·����β׺Ϊpng,PNG���ļ�·��*/
	/**
	 * ��ȡβ׺��png���ļ�
	 * @param �ļ���·��
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
	 * ��ȡ�����ļ�
	 * @param �ļ���·��
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
	 * ��ȡ�Զ���β׺�ļ�
	 * @param �ļ���·��
	 * @param �ļ�β׺СдString[]
	 * @return ArrayList<String>
	 * */
	public ArrayList<String> getDefinedFiles(String filePath,String[] ends){
		init();
		File root = new File(filePath);
		PathGeter.FileFilerPlus ft = new PathGeter.FileFilerPlus(ends);
		searchFile(root, ft);
	    return new ArrayList<>(filelist);
	}
	
	/*ʹ�ù��������������ļ�*/
	private void searchFile(File file,FileFilter ft){
		File[] dirs = file.listFiles(PathGeter.DirFilter.getOnly());
		File[] files = file.listFiles(ft);
		if(dirs==null) return;
		for(File f:dirs) searchFile(f, ft);
		for(File f: files) filelist.add(f.getAbsolutePath());
	}
	
	/**
	 * �����ļ�·��������
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
	 * �Զ����ļ�·��������
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

