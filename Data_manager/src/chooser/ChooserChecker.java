package chooser;

import java.util.ArrayList;

import com.fileio.PathGeter;


/*�����ҵ��˶��ٸ�ͼƬ�ļ�*/
public class ChooserChecker {
	public static void main(String[] args) {
		PathGeter pg = new PathGeter();
		ArrayList<String> photoPaths1 = pg.getImageFiles("D:/6666666666666666666");
		System.out.println(photoPaths1.size());
		PathGeter pg1 = new PathGeter();
		ArrayList<String> photoPaths2 = pg1.getImageFiles("D:/classification");
		System.out.println(photoPaths2.size());
	}
}
