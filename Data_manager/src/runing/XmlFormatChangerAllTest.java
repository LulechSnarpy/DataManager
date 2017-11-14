package runing;

import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import com.fileio.PathGeter;

import bean.MyXml;
import filereaders.XmlReader;
import filewriters.XmlWriter;

/*�ı�֮ǰ���ɵ�xml�в��ֵ��ַ���С����*/
public class XmlFormatChangerAllTest {

	public static void main(String[] args) {
		PathGeter pg = new PathGeter();
		ArrayList<String> paths = pg.getXmlFiles("file");//·��ʹ��ʱ�ǵ��޸�·��
		for(int i=0; i<paths.size(); i++){
			MyXml mx = null;
			XmlReader xr = new XmlReader();
			mx = xr.readAll(paths.get(i));
			XmlWriter xw = new XmlWriter(paths.get(i), mx);
			xw.createDocument();
				
		}
	}
}
