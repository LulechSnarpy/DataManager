package runing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import com.fileio.PathGeter;
import com.fileio.PhotoChooser2;

import bean.MyObj;
import filereaders.TextReader;

/*计算批量文件的不同IOU下的召回率并筛选出符合要求的文件 IOU【0.9,0.7,0.5】 筛选全部iou0.9以上是pass，0.5一下为fail*/
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
			/*
			 * @  图片，xml所在文件夹路径
			 * @  txt所在文件夹路径
			 * @ 符合要求的文件路径1
			 * @ 符合要求的文件路径2
			 * @  输出文件路径
			 * @ 不需要转移文件请在chooseFile函数中注释相应位置
			 * */
			pc2.chooseFile("G:/算法/ED数据/ED总xml", "G:/算法/ED数据/a=0.75b=0.85(8)", "D:/pass/", "D:/fail/", "file/IOU_LIST_1.txt");
	}
}
