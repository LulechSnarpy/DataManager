package filereaders;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bean.MyXml;
import bean.MyObj;

/*
 * ��ȡxml�ļ�
 * */
/**
 * ��ȡxml�ļ�
 * @version 2017_11_14
 * @author Lulech
 * */
public class XmlReader {
	private SAXReader reader = null;
	private MyXml mx;
	
	/*ʹ��ǰ��ʼ����ȡ��*/
	/**
	 * ���캯��
	 * */
	public XmlReader() {
		reader = new SAXReader();
	}
	
	/*�ڿ���̨��ӡ��Ϣ*/
	/**
	 * �ڿ���̨��ӡ��Ϣ 
	 * @param obj
	 * @return void
	 * */
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
	
	/*��ȡ�����Լ������xml�ļ�����Myxml������Ϊxml��Ϣ*/
	/**
	 * ��ȡ�����Լ������xml�ļ�����Myxml������Ϊxml��Ϣ
	 * @param �ļ�·��
	 * @return MyXml����
	 * */
	public MyXml readAll(String path){
		mx = new MyXml();
		try{
			Document doc = reader.read(new File(path));
	//		print(0,doc.getRootElement().elements());
			mx.setFolder(doc.selectSingleNode("//folder").getText());
			mx.setFilename(doc.selectSingleNode("//filename").getText());
			mx.setSodatebase(doc.selectSingleNode("//source/database").getText());
	//		mx.setSosource(doc.selectSingleNode("//source/annotation").getText());//�����������Ϊ��������ʹ�õ���source������annotation
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
			mx.setAllObj(readAllMyobj(obl));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mx;
	}

	/*��ȡ�ļ��е�object�������ݷ���MyObj����*/
	/**
	 * ��ȡ�ļ��е�object�������ݷ���MyObj����
	 * @param List<Element>
	 * @return ArrayList<MyObj>
	 * */
	public ArrayList<MyObj> readAllMyobj(List<Element> obl) {
		LinkedList<MyObj> myobjs = new LinkedList<MyObj>();
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
			myobjs.add(obj);
		}
		return new ArrayList<>(myobjs);
	}
	
	/*��ȡ�ļ��е�object��λ�����ݷ���MyObj����*/
	/**
	 * ��ȡ�ļ��е�object��λ�����ݷ���MyObj����
	 * @param �ļ�·��
	 * @return ArrayList<MyObj>
	 * */
	public ArrayList<MyObj> readAllMyobjBox(String path){
		LinkedList<MyObj> myobjs = new LinkedList<MyObj>();
		try{
			Document doc = reader.read(new File(path));
			Iterator<Element> obt = doc.selectNodes("//object//bndbox").iterator();
			while(obt.hasNext()){
				MyObj obj = new MyObj();
				@SuppressWarnings("unchecked")
				Element obz = obt.next();
				List<Element> obc = obz.elements();
				obj.xmin = obc.get(0).getText();
				obj.ymin = obc.get(1).getText();
				obj.xmax = obc.get(2).getText();
				obj.ymax = obc.get(3).getText();
	//			System.out.println(obc.get(0).getName());
	//			System.out.println(obj.xmin);
	//			System.out.println(obj.ymin);
	//			System.out.println(obj.xmax);
	//			System.out.println(obj.ymax);
				myobjs.add(obj);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ArrayList<>(myobjs);
	}
	
	/*�ڿ���̨���Myxml�е�����*/
	/**
	 * �ڿ���̨���Myxml�е�����
	 * @param MyXml
	 * @return void
	 * */
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
	
	/*�ڿ���̨���MyObj�е�����*/
	/**
	 * �ڿ���̨���MyObj�е�����
	 * @param ArrayList<MyObj>
	 * @return void
	 * */
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
