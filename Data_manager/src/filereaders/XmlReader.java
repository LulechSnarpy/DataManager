package filereaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bean.MyXml;
import bean.MyObj;
public class XmlReader {
	private SAXReader reader = null;
	private MyXml mx;

	public XmlReader() {
		reader = new SAXReader();
	}
	
	public void fail(Object obj){
		System.out.println(obj);
	}
	
//	 private String format(int i) {
//	        String temp = "";
//	        while (i > 0) {
//	            temp += "--";
//	            i--;
//	        }
//	        return temp;
//	    }
//	
//	private void print(int i, List<Element> els) {
//        i++;
//        for (Element el : els) {
//            fail(format(i) + "##" + el.getName() + "#" + el.getTextTrim());
//            if (el.hasContent()) {
//                print(i, el.elements());
//            } 
//        }
//    }
	
	public MyXml readAll(String path) throws DocumentException{
		mx = new MyXml();
		Document doc = reader.read(new File(path));
//		print(0,doc.getRootElement().elements());
		mx.setFolder(doc.selectSingleNode("//folder").getText());
		mx.setFilename(doc.selectSingleNode("//filename").getText());
		mx.setSodatebase(doc.selectSingleNode("//source/database").getText());
		mx.setSosource(doc.selectSingleNode("//source/source").getText());
		mx.setSoimage(doc.selectSingleNode("//source/image").getText());
		mx.setMname(doc.selectSingleNode("//owner/name").getText());
		mx.setMname(doc.selectSingleNode("//marker/name").getText());
		mx.setSiwidth(doc.selectSingleNode("//size/width").getText());
		mx.setSiheight(doc.selectSingleNode("//size/height").getText());
		mx.setSidepth(doc.selectSingleNode("//size/depth").getText());
		mx.setSegmented(doc.selectSingleNode("//segmented").getText());
		@SuppressWarnings("unchecked")
		List<Element> obl = doc.selectNodes("//object"); 
		Iterator<Element> obt = obl.iterator();
		while(obt.hasNext()){
			MyObj obj = new MyObj();
			@SuppressWarnings("unchecked")
			List<Element> obz = obt.next().elements();
			obj.name = obz.get(0).getText();
			obj.pose = obz.get(1).getText();
			obj.color = obz.get(2).getText();
			obj.truncated = obz.get(3).getText();
			obj.area = obz.get(4).getText();
			obj.difficult = obz.get(5).getText();
			obj.shadow = obz.get(6).getText();
			@SuppressWarnings("unchecked")
			List<Element> obc = obz.get(7).elements();
			obj.xmin = obc.get(0).getText();
			obj.ymin = obc.get(1).getText();
			obj.xmax = obc.get(2).getText();
			obj.ymax = obc.get(3).getText();
			mx.setObj(obj);
		}
		return mx;
	}
	
	public void printAll(MyXml mx){
		System.out.println("annotation");
		System.out.println("  folder:"+mx.getFolder());
		System.out.println("  filename:"+mx.getFilename());
		System.out.println("  source");
		System.out.println("    database:"+mx.getSodatebase());
		System.out.println("    source:"+mx.getSosource());
		System.out.println("    image:"+mx.getSoimage());
		System.out.println("  owner");
		System.out.println("    name:"+mx.getOname());
		System.out.println("  marker");
		System.out.println("    name:"+mx.getMname());
		System.out.println("  size");
		System.out.println("    width:"+mx.getSiwidth());
		System.out.println("    height:"+mx.getSiheight());
		System.out.println("    depth:"+mx.getSidepth());
		System.out.println("  segmented:"+mx.getSegmented());
		printObj(mx.getObj());
	}
	
	public void printObj(ArrayList<MyObj> obl){
		Iterator<MyObj> obj = obl.iterator();
		while(obj.hasNext()){
			MyObj ob = obj.next();
			System.out.println("  object");
			System.out.println("    name:"+ob.name);
			System.out.println("    pose:"+ob.pose);
			System.out.println("    color:"+ob.color);
			System.out.println("    truncated:"+ob.truncated);
			System.out.println("    area:"+ob.area);
			System.out.println("    difficult:"+ob.difficult);
			System.out.println("    shadow:"+ob.shadow);
			System.out.println("    bndbox");
			System.out.println("      xmin:"+ob.xmin);
			System.out.println("      ymin:"+ob.ymin);
			System.out.println("      xmax:"+ob.xmax);
			System.out.println("      ymax:"+ob.ymax);
		}
	}
	
//	public static void main(String[] args) throws FileNotFoundException, DocumentException {
//		XmlReader xr = new XmlReader();
//		System.out.println("IS THIS WORKING?");
//		MyXml mx = xr.readAll("file/204044.xml");
//		ArrayList<MyObj> obl = mx.getObj();
//		for(int i=0; i<obl.size(); i++){
//			
//			System.out.println(obl.get(i).difficult);
//		}
//	}
}
