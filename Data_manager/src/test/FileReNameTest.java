package test;

import java.io.File;
import java.util.ArrayList;

import com.fileio.PathGeter;

import filecorrector.ReName;

public class FileReNameTest {
	public static void main(String[] args) {
		ReName rn = new ReName();
		PathGeter pg = new PathGeter();
		pg.init();
		ArrayList<String> paths = pg.getImageFiles("D:/(201)-����ɽ��·��̩����·���_2015216_145449");//ͼƬ������ΪJPG��·��
		for(int i=0; i<paths.size(); i++){
			String path = paths.get(i);
			File f = new File(path);
			String oldname = f.getName();
			String newname = oldname.replace("jpg", "JPG");
			path = f.getParent();
			rn.renameFile(path, oldname, newname);
		}
	}
}
