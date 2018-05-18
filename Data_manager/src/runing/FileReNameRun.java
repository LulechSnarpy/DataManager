package runing;

import java.io.File;
import java.util.ArrayList;

import com.fileio.PathGeter;

import filecorrector.ReName;

/*��������ͼƬΪjpg��ʵ����Ҫpng��ִ�г���  ������*/
public class FileReNameRun {
	public static void main(String[] args) {
		ReName rn = new ReName();
		PathGeter pg = new PathGeter();
		ArrayList<String> paths = pg.getImageFiles("D:/666666/(100)-201511301657386884");//��Ҫ����ΪPNG��ͼƬ·��
		for(int i=0; i<paths.size(); i++){
			String path = paths.get(i);
			File f = new File(path);
			String oldname = f.getName();
			String newname = oldname.replace("JPG", "PNG");
			path = f.getParent();
			rn.renameFile(path, oldname, newname);
		}
	}
}
