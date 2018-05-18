package runing;

import java.io.File;
import java.util.ArrayList;

import com.fileio.PathGeter;

import filecorrector.ConvertImageFile;


/*png转jpg文件*/
public class ConvertImageFileRun {
	public static void main(String[] args) {
		PathGeter pg = new PathGeter();
		ArrayList<String> paths = pg.getImageFiles("D:/666666/(100)-201511301657386884",true);//需要修改的png图片的路径
		for(int i=0; i<paths.size(); i++){
			String path = paths.get(i);
			File f = new File(path);
			String oldname = f.getName();
			//String newname = oldname.replace("jpg", "JPG");
			path = f.getParent();
			ConvertImageFile.ConverImage(path, oldname);//调用png转jpg的函数
		}
	}
}
