package runing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import com.fileio.PathGeter;
import com.fileio.PhotoChooser2;

import bean.MyObj;
import filereaders.TextReader;

/*���������ļ��Ĳ�ͬIOU�µ��ٻ��ʲ�ɸѡ������Ҫ����ļ�*/
public class PhotoChooser2Test {
	public static void main(String[] args) {
//		PathGeter pg = new PathGeter();
//		TextReader tr = new TextReader();
//		
//		pg.init();
//		ArrayList<String> txt = pg.getTextFiles( "C:/Users/Administrator/Desktop/DataManager/EdgeBox");
//		System.out.println(txt.size());
//		for(int i=0; i<txt.size(); i++){
//			try {
//				ArrayList<MyObj> mo = tr.readBox(txt.get(i),false);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(i);
//		}
		PhotoChooser2 pc2 = new PhotoChooser2();
		try {
			/*
			 * @  ͼƬ��xml�����ļ���·��
			 * @  txt�����ļ���·��
			 * @ ����Ҫ����ļ�·��1
			 * @ ����Ҫ����ļ�·��2
			 * @  ����ļ�·��
			 * @ ����Ҫת���ļ�����chooseFile������ע����Ӧλ��
			 * */
			pc2.chooseFile("D:/6666666666666666666", "D:/6666666666666666666", "D:/pass/", "D:/fail/", "file/IOU_LIST_1.txt");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
