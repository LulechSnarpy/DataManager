package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlList;

import com.fileio.FileIO;
import com.fileio.PathGeter;

public class Iou_files_test2 {
	private static String oldpath = "G://6666666666666666666//";
	private static String newpath;
	private static final String XMLFOLDER = "/xml/";
	
	public static void movePhoto(String oldimage,String oldxml,String newpath) throws IOException{
		File image = new File(oldimage);
		File xml = new File(oldxml);
		String imagepath = newpath+"/"+image.getName();
		String xmlpath = newpath +XMLFOLDER+xml.getName();
		FileIO.copySingleFile(oldimage, newpath);
	}
	 
	public static void main(String[] args) {
		PathGeter pg = new PathGeter();
		pg.init();
		ArrayList<String> image = pg.getImageFiles(oldpath);
		ArrayList<String> xml = pg.getXmlFiles(oldpath);
		ArrayList<String> wrong = new ArrayList<>();
		System.out.println(image.size());
		System.out.println(xml.size());
		Iterator<String> imgit = image.iterator();
		Iterator<String> xmlit = xml.iterator();
		while(imgit.hasNext()&&xmlit.hasNext()){
			String img = imgit.next();
			String x = xmlit.next();
			File i = new File(img);
			File m = new File(x);
			if(!i.getName().replace(".JPG", "").replace(".jpg", "").equals(m.getName().replace("xml", "").replace(".","").replace("JPG", "").replace("jpg", "")))
			{
				
				System.out.println(i.getName() + "\t" + m.getName());
				System.out.println(img);
				System.out.println(x);
			}

		}
	}
	
}
