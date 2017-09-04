package filereaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.AbstractDocument.ElementEdit;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bean.MyXml;
import bean.PT;
import bean.Boxes;
import bean.MyObject;

/*
 * 读取xml文件
 * */
public class XmlReader {
	private SAXReader reader = null;
	private MyXml mx;
	
	/*使用前初始化读取类*/
	public XmlReader() {
		reader = new SAXReader();
	}
	
	/*在控制台打印信息*/
	public void fail(Object obj){
		System.out.println(obj);
	}
	
	/*读取我们自己定义的xml文件返回Myxml数据作为xml信息*/
	public MyXml readAll(String path) throws DocumentException{
		mx = new MyXml();
		Document doc = reader.read(new File(path));
		mx.setFileName(doc.selectSingleNode("//filename").getText());
		@SuppressWarnings("unchecked")
		List<Element> obl = doc.selectNodes("//object"); 
		mx.setObjs(readAllMyobj(obl));
		return mx;
	}

	public ArrayList<MyObject> readAllMyobj(List<Element> obl) {
		ArrayList<MyObject> myobjs = new ArrayList<>();
		int ll = obl.size();
		for(int u=0; u<ll; ){
			ArrayList<Element> pp = new ArrayList<>();
			MyObject object = new MyObject();
			ArrayList<Boxes> boxes = new ArrayList<>();
			String className = obl.get(0).element("name").getText();
			className = className.replaceAll("\\d", "");
			object.setClassName(className);
			for(Element e:obl){
				Boxes box = new Boxes();
				String name = e.element("name").getText().replaceAll("\\d", "");
				if(name.equals(className)){
					pp.add(e);
					box.setPolygon(readPT(e.element("polygon")));
					boxes.add(box);				}
				
			}
			u+=pp.size();
			object.setBoxes(boxes);
			myobjs.add(object);
			for(Element e:pp){
				System.out.println(e.element("name").getText());
				obl.remove(e);
			}
		}
		return myobjs;
	}

	private ArrayList<PT> readPT(Element element) {
		ArrayList<PT> pts = new ArrayList<>();
		List<Element> epts = element.elements("pt");
		for(Element e:epts){
			PT pt = new PT();
			pt.x = e.element("x").getText();
			pt.y = e.element("y").getText();
			pts.add(pt);
		}
		return pts;
	}
}
