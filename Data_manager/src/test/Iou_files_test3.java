package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlList;

import org.dom4j.DocumentException;
import org.xml.sax.XMLReader;

import com.fileio.FileIO;
import com.fileio.PathGeter;
import com.fileio.PhotoChooser;

import bean.MyXml;
import filereaders.XmlReader;

public class Iou_files_test3 {
	private static String oldpath = "G://6666666666666666666//";
	private static String newpath = "E://z//";
	 
	public static void main(String[] args) {
		PhotoChooser pc = new PhotoChooser();
		try {
			pc.chooseFile(oldpath, newpath);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
