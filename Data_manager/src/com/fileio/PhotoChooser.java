package com.fileio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlList;

import org.dom4j.DocumentException;
import org.xml.sax.XMLReader;

import com.fileio.FileIO;
import com.fileio.PathGeter;

import bean.MyObj;
import bean.MyXml;
import filereaders.XmlReader;

/*�ҵ��������������ļ���ָ����·�����÷���PhotoChooser2����*/
public class PhotoChooser {
//	private  String oldpath = "G://6666666666666666666//";
//	private  String newpath = "E://z//";
	private static final String XMLFOLDER = "xml//";
	private ArrayList<MyXml> mx;
	
	public void movePhoto(String oldimage,String oldxml,String newpath) throws IOException{
		File image = new File(oldimage);
		File xml = new File(oldxml);
		String imagepath = newpath +image.getName();
		String xmlpath = newpath +XMLFOLDER+xml.getName();
		FileIO.copySingleFile(oldimage, imagepath);
		FileIO.copySingleFile(oldxml, xmlpath);
	}
	 
	public void chooseFile(String oldpath,String newpath) throws DocumentException, IOException{
		PathGeter pg = new PathGeter();
		XmlReader xr = new XmlReader();
		ArrayList<String> image = pg.getImageFiles(oldpath);
		ArrayList<String> xml = pg.getXmlFiles(oldpath);
//		ArrayList<String> chooseImg = new ArrayList<>();
//		ArrayList<String> chooseXml = new ArrayList<>();
		mx = new ArrayList<>(xml.size());
		File n = new File(newpath);
		for(String path:xml){
			mx.add(xr.readAll(path));
		}
		for(int i=0; i<mx.size(); i++){
			if(check(mx.get(i))){
//				chooseImg.add(image.get(i));
//				chooseXml.add(xml.get(i));
				movePhoto(image.get(i), xml.get(i), newpath);
			}
		}
		
	}
	
	/*�����жϺ���*/
	public boolean check(MyXml mx){
		if(mx.getObj().size()>=10) return false;
		for(MyObj m : mx.getObj()){
			if(m.difficult.equals("1")) return false;
			if(m.shadow.equals("1")) return false;
			if(m.area.equals("1")) return false;
		}
		return true;
	}
}
