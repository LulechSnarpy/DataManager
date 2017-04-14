package runing;

import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import com.fileio.PathGeter;

import bean.MyXml;
import filereaders.XmlReader;
import filewriters.XmlWriter;

public class XmlFormatChangerAllTest {
	public static void main(String[] args) {
		PathGeter pg = new PathGeter();
		pg.init();
		ArrayList<String> paths = pg.getXmlFiles("D:/666666_well_done");//Â·¾¶
		for(int i=0; i<paths.size(); i++){
			MyXml mx = null;
			XmlReader xr = new XmlReader();
			try {
				mx = xr.readAll(paths.get(i));
				XmlWriter xw = new XmlWriter(paths.get(i), mx);
				xw.createDocument();
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
