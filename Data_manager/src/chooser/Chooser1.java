package chooser;

import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import com.fileio.FileIO;
import com.fileio.PathGeter;

import bean.MyObj;
import bean.MyXml;
import filereaders.XmlReader;
import filewriters.TextWriter;

/*复制满足指定条件的图片*/
public class Chooser1 {
	public void chooseClean(String path,String path2) throws DocumentException, IOException {
		PathGeter pg = new PathGeter();
		XmlReader xr = new XmlReader();
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		StringBuilder sb4 = new StringBuilder();
		MyXml mx = null;
		ArrayList<String> xmlPaths = pg.getXmlFiles(path);
		ArrayList<String> photoPaths = pg.getImageFiles(path);
		for (int i = 0; i < xmlPaths.size(); i++) {
			mx = xr.readAll(xmlPaths.get(i));
			ArrayList<MyObj> mObjs = mx.getObj();
			boolean f1 = true;
			boolean f2 = true;
			boolean f3 = true;
			for (int j = 0; j< mObjs.size(); j++) {
				if(mObjs.get(j).difficult.equals("1") || mObjs.get(j).shadow.equals("1")|| mObjs.get(j).truncated.equals("1")||mObjs.get(j).area.equals("1")){
					f1 = false;
				    if(mObjs.get(j).difficult.equals("1")||mObjs.get(j).shadow.equals("1")||mObjs.get(j).area.equals("1")) {
				    	f2 = false;
						if(mObjs.get(j).difficult.equals("1") || mObjs.get(j).shadow.equals("1")){
							f3 = false;
						}
					}
				}
			}
			if (f1){
				FileIO.copySingleFile(photoPaths.get(i), path2+"Choos1//"+mx.getFilename());
				sb1 = addToSB(sb1,mx);
			}else if (f2){
				FileIO.copySingleFile(photoPaths.get(i), path2+"Choos2//"+mx.getFilename());
				sb2 = addToSB(sb2,mx);
			}else if (f3){
				FileIO.copySingleFile(photoPaths.get(i), path2+"Choos3//"+mx.getFilename());
				sb3 = addToSB(sb3,mx);
			}else{
				FileIO.copySingleFile(photoPaths.get(i), path2+"Choos4//"+mx.getFilename());
				sb4 = addToSB(sb4,mx);
			}
		}
		TextWriter tw = new TextWriter();
		tw.writeText(path2+"Choos1//"+"imagename.txt", sb1.toString());
		tw.writeText(path2+"Choos2//"+"imagename.txt", sb2.toString());
		tw.writeText(path2+"Choos3//"+"imagename.txt", sb3.toString());
		tw.writeText(path2+"Choos4//"+"imagename.txt", sb4.toString());
	}

	private StringBuilder addToSB(StringBuilder sb,MyXml mx) {
		sb.append(mx.getFilename().replace(".jpg", "").replace(".JPG", "").replace(".PNG", "").replace(".png",""));
		sb.append(System.lineSeparator());
		return sb;
	}
	
	public static void main(String[] args) throws DocumentException, IOException {
		Chooser1 c = new Chooser1();
		c.chooseClean("D://6666666666666666666", "D://");
	}
}
