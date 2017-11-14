package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlList;

import com.fileio.FileIO;
import com.fileio.PathGeter;

/*找出命名错误的文件*/
public class Iou_files_test {
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
			if(!i.getName().replace(".","").replace("JPG", "").replace("jpg", "").equals(m.getName().replace("xml", "").replace(".","").replace("JPG", "").replace("jpg", "")))
			{
				System.out.println(i.getName().replace(".","").replace("JPG", "").replace("jpg", "")+"\t"+m.getName().replace("xml", "").replace(".","").replace("JPG", ""));
				 wrong.add(x);
				 x = xmlit.next();
				 m = new File(x);
			}
			System.out.println(i.getName() + "\t" + m.getName());
		}
		for(int i=0; i<wrong.size(); i++)
		System.out.println(wrong.get(i));
	}
	
}
