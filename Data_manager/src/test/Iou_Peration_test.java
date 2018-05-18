package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.DocumentException;

import bean.MyObj;
import bean.MyXml;
import filecorrector.XmlCorrector;
import filereaders.TextReader;
import filereaders.XmlReader;
import operation.Iou_Operation;

public class Iou_Peration_test {
	public static void main(String[] args) throws DocumentException, IOException {
		
		XmlReader xr = new XmlReader();
		TextReader tr = new TextReader();
		
		Iou_Operation io = new Iou_Operation();
		
		ArrayList<MyObj> proposals= tr.readBox("file/bbs.txt", false);
	
		
		MyXml mx = xr.readAll("file/203019.xml");
		
		ArrayList<MyObj> groundTruth = mx.getObj();
		
		double standard = 0.7;
		
		io.getIOU(proposals, groundTruth, standard,true);
		
		ArrayList<Double> rr = io.getRr();
		
		ArrayList<ArrayList<Double>> iou = io.getIoulist();
		
		Iterator<ArrayList<Double>> iout = iou.iterator();
		
		File f= new File("file/OutIou_203019.txt");
		if(!f.exists()) f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
		while(iout.hasNext()){
			Iterator<Double> dt = iout.next().iterator();
			StringBuilder z = new StringBuilder();
			while(dt.hasNext()){
				z.append(dt.next()).append(" : ");
			}
			z.append(">>");
			z.append(System.lineSeparator());
			bw.write(z.toString());
		}
		bw.close();
		
		f = new File("file/OutRecallrate_203019.txt");
		if(!f.exists()) f.createNewFile();
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
		for(int i=0; i<rr.size(); i++){
			StringBuilder z = new StringBuilder();
			z.append(rr.get(i)).append(System.lineSeparator());
			bw.write(z.toString());
		}
		bw.close();
		System.out.println(groundTruth.size());
	}
}
