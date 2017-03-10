package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import filereaders.TextReader;
import bean.MyObj;
public class TextReaderTest {
	
	public static void main(String[] args) {
		TextReader tr = new TextReader();
		try {
			tr.readText("file/ImgCache.txt");
			showBox(tr.readBox("file/000004_boxes.txt",true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void showBox(ArrayList<MyObj> obj){
		Iterator<MyObj> obt = obj.iterator();
		int i = 1;
		while(obt.hasNext()){
			MyObj ob = obt.next();
			System.out.println(i+".  xmin:"+ob.xmin+"  ymin:"+ob.ymin+"  xmax:"+ob.xmax+"  ymax:"+ob.ymax);
			i++;
		}
	}
}
