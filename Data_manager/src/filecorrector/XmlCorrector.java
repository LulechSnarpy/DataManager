package filecorrector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/*修正之前软件产出的xml中部分重复的问题*/
public class XmlCorrector {
	private SAXReader reader = null;
	public XmlCorrector() {
		reader = new SAXReader();
	}
	
	public void tryCorrect(String path) throws DocumentException, IOException{
		Document doc = reader.read(new File(path));
		
		List<Element> list1 = doc.getRootElement().elements();
		List<Element> list2 = doc.selectNodes("//annotation/source");
		Iterator<Element> iter = list2.iterator();
		boolean clear = false;
		Element flag = null;
		Element head = (Element) doc.selectSingleNode("//filename");
		while(iter.hasNext()) flag = iter.next();
		iter = list1.iterator();
		while(iter.hasNext()){
			Element m = iter.next();
			if(m == flag) break;
			if(clear) doc.getRootElement().remove(m);
			if(m == head) clear = true;
		}
		XMLWriter output = new XMLWriter(new FileWriter(new File(path)));	
		output.write(doc);
		output.close();
	}
}
