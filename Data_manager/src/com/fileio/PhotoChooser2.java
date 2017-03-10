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
import filereaders.TextReader;
import filereaders.XmlReader;
import filewriters.TextWriter;
import operation.Iou_Operation;

public class PhotoChooser2 {
//	private  String oldpath = "G://6666666666666666666//";
//	private  String newpath = "E://z//";
	private static final String XMLFOLDER = "xml//";
	private StringBuilder sb;
	
	public void movePhoto(String oldimage,String oldxml,String newpath) throws IOException{
		File image = new File(oldimage);
		File xml = new File(oldxml);
		String imagepath = newpath +image.getName();
		String xmlpath = newpath +XMLFOLDER+xml.getName();
		FileIO.copySingleFile(oldimage, imagepath);
		FileIO.copySingleFile(oldxml, xmlpath);
	}
	 
	public void chooseFile(String oldpath,String textpath,String newpath1,String newpath2,String output) throws DocumentException, IOException{
		PathGeter pg = new PathGeter();
		XmlReader xr = new XmlReader();
		TextReader tr = new TextReader();
		sb = new StringBuilder();
		sb.append("文件名").append("\t\t");
		sb.append("0.9下召回率").append("\t");
		sb.append("0.7下召回率").append("\t");
		sb.append("0.5下召回率").append(System.lineSeparator());
		pg.init();
		
		ArrayList<String> image = pg.getImageFiles(oldpath);
		ArrayList<String> xml = pg.getXmlFiles(oldpath);
		ArrayList<String> txt = pg.getTextFiles(textpath);
		
		for(int i=0; i<xml.size(); i++){
			MyXml mx = xr.readAll(xml.get(i));
			ArrayList<MyObj> mo = tr.readBox(txt.get(i),true);//读入groundTruth boolean false 不改变 x,y 
			int f = check(mx,mo);
			System.out.println(i);
			if(f == 1){
				movePhoto(image.get(i), xml.get(i), newpath1);
			}else if(f == -1){
				movePhoto(image.get(i), xml.get(i), newpath2);
			}
		}
		TextWriter.writeText(output, sb.toString());
	}
	
	public int check(MyXml mx,ArrayList<MyObj> proposals){
		int f = 0;
		Iou_Operation iou = new Iou_Operation();
		ArrayList<MyObj> groundTruth = mx.getObj();
		ArrayList<Double> rr = null;
		Double r1 = null;
		Double r2 = null;
		Double r3 = null;
		iou.getIOU(proposals, groundTruth, 0.9, false);//boolean true 左上角，宽，高      false 左上角，右下角
		rr = iou.getRr();
		r1 = rr.get(rr.size()-1);
		if(r1>0.9) f = 1;
		iou.getIOU(proposals, groundTruth, 0.7, false);
		rr = iou.getRr();
		r2 = rr.get(rr.size()-1);
		iou.getIOU(proposals, groundTruth, 0.5, false);
		rr = iou.getRr();
		r3 = rr.get(rr.size()-1);
		if(r3<0.8) f = -1;
		sb.append(mx.getFilename()).append("\t");
		sb.append(String.format("%.6f", r1)).append("\t");
		sb.append(String.format("%.6f", r2)).append("\t");
		sb.append(String.format("%.6f", r3)).append(System.lineSeparator());
		return f;
	}
}
