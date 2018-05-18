package runing;

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

/*根据识别难度、遮挡面积、阴影筛选图片，识别难度低，遮挡面积小，没有阴影的图片*/
public class Iou_files_Run3 {
	private static String oldpath = "G://6666666666666666666//";  //待筛选的文件夹路径
	private static String newpath = "E://z//";			//输出的文件夹路径
	 
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
