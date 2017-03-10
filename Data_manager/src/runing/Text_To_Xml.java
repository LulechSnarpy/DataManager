package runing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import bean.MyXml;
import filereaders.TextReader;
import filewriters.XmlWriter;
public class Text_To_Xml implements Runnable{
	private String rootpath;//Xml生成文件的文件夹
	public static void main(String[] args) {
		Text_To_Xml xtx = new Text_To_Xml("file/");
		try {
			xtx.textToXml("file/ImgCache.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Text_To_Xml(String rp) {
		this.rootpath = rp;
	}
	
	public void textToXml(String path) throws IOException{
		TextReader tr = new TextReader();
		ArrayList<MyXml> mxlist =tr.readText(path);
		Iterator<MyXml> itmx=mxlist.iterator();
		File f = new File(rootpath);
		if(!f.exists()) f.mkdirs();
		while(itmx.hasNext()){
			MyXml mx = itmx.next();
			FileOutputStream fs = new FileOutputStream("file/max.txt");
			String xpath = mx.getFilename();
			xpath = xpath.replaceAll("jpg", "xml");
			xpath = rootpath +xpath;
			XmlWriter xw = new XmlWriter(xpath, mx);
			xw.createDocument();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
