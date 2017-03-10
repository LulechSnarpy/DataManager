package test;

import java.io.File;
import java.util.ArrayList;

import com.fileio.PathGeter;

import filecorrector.ConvertImageFile;

public class ConvertImageFileTest {
	public static void main(String[] args) {
		PathGeter pg = new PathGeter();
		pg.init();
		ArrayList<String> paths = pg.getImageFiles("",true);//需要修改的png图片的路径
		for(int i=0; i<paths.size(); i++){
			String path = paths.get(i);
			File f = new File(path);
			String oldname = f.getName();
			//String newname = oldname.replace("jpg", "JPG");
			path = f.getParent();
			ConvertImageFile.ConverImage(path, oldname);
		}
	}
}
