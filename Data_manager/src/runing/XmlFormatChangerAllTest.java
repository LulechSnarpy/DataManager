package runing;

import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import com.fileio.PathGeter;

import bean.MyXml;
import filereaders.XmlReader;
import filewriters.XmlWriter;

/*改变之前生成的xml中部分的字符大小问题*/
public class XmlFormatChangerAllTest {

	public static void main(String[] args) {
		PathGeter pg = new PathGeter();
		ArrayList<String> paths = pg.getXmlFiles("file");//路径使用时记得修改路径
		for(int i=0; i<paths.size(); i++){
			MyXml mx = null;
			XmlReader xr = new XmlReader();
			mx = xr.readAll(paths.get(i));
			XmlWriter xw = new XmlWriter(paths.get(i), mx);
			xw.createDocument();
				
		}
	}
}
