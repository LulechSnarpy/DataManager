package filewriters;

import java.io.IOException;
import java.util.List;

import bean.Boxes;
import bean.MyObject;
import bean.MyXml;
import bean.PT;

public class WriteXmlData {
	public static  void fomateAndOutput(String filePath,MyXml mx) throws IOException{
		String fileName = mx.getFileName();
		List<MyObject> objects= mx.getObjs();
		for(MyObject object:objects){
			String className = object.getClassName();
			String path = filePath + "/" + fileName + "_" + className + ".txt";
			StringBuffer sb = new StringBuffer();
			List<Boxes> boxes = object.getBoxes();
			sb.append(boxes.size()).append(System.lineSeparator());
			for(Boxes box:boxes){
				List<PT> pts = box.getPolygon();
				for(PT pt:pts){
					sb.append(pt.x).append(",").append(pt.y).append(System.lineSeparator());
				}
				sb.append(System.lineSeparator());
			}
			TextWriter.writeText(path, sb.toString());
		}
	}
}
