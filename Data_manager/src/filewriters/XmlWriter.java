package filewriters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import bean.MyObj;
import bean.MyXml;

public class XmlWriter {
	private String path = null;
	private MyXml mx;
	
	public XmlWriter(String path,MyXml mx){
		this.path = path;
		this.mx = mx;
	}
	
	public void createDocument() throws IOException{
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("annotation");
		root.addElement("folder").setText(mx.getFolder());
		root.addElement("filename").setText(mx.getFilename().toLowerCase());
		Element c1 = root.addElement("source");
		c1.addElement("database").setText(mx.getSodatebase());
		c1.addElement("source").setText(mx.getSosource());
		c1.addElement("image").setText("traffic video of Qingdao");
		root.addElement("owner").addElement("name").setText(mx.getOname());
		root.addElement("marker").addElement("name").setText(mx.getMname());
		Element c2 = root.addElement("size");
		c2.addElement("width").setText(mx.getSiwidth());
		c2.addElement("height").setText(mx.getSiheight());
		c2.addElement("depth").setText(mx.getSidepth());
		root.addElement("segmented").setText(mx.getSegmented());
		ArrayList<MyObj> obj = mx.getObj();
		for(int i=0; i<obj.size(); i++){
			MyObj mj = obj.get(i);
			Element c3 = root.addElement("object");
			c3.addElement("name").setText(mj.name.toLowerCase()
					//.replaceAll("^((suv)|(mpv)|(taxi)|(van)|(pickup)|(non-motor)|(unspecified))$", "car")
					);//��ΪСд
			c3.addElement("pose").setText(mj.pose);
			c3.addElement("color").setText(mj.color.toLowerCase());//��ΪСд
			c3.addElement("truncated").setText(mj.truncated);
			c3.addElement("area").setText(mj.area);
			c3.addElement("difficult").setText(mj.difficult);
			c3.addElement("shadow").setText(mj.shadow);
			Element cc1 = c3.addElement("bndbox");
			cc1.addElement("xmin").setText(mj.xmin);
			cc1.addElement("ymin").setText(mj.ymin);
			cc1.addElement("xmax").setText(mj.xmax);
			cc1.addElement("ymax").setText(mj.ymax);
		}
		
		
		  // ����XML�ĵ���ʽ
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // ����XML���뷽ʽ,������ָ���ı��뷽ʽ����XML�ĵ����ַ���(String),����Ҳ����ָ��ΪGBK����ISO8859-1  
        outputFormat.setEncoding("UTF-8");
        //outputFormat.setNewlines(true);//�����Ƿ���
        outputFormat.setSuppressDeclaration(true); //�Ƿ�����xmlͷ(true:������)
        outputFormat.setIndent(true); //�����Ƿ�����
        outputFormat.setIndent("\t"); //���ĸ��ո�ʽʵ������
        
		doc.asXML();
		XMLWriter output;
		File f= new File(path);
		if(!f.exists()) f.createNewFile();
		output = new XMLWriter(new FileOutputStream(f),outputFormat);
		output.write(doc);
		output.close();
		
		clearHeadLine();
	}
	
	public void clearHeadLine() throws IOException{
		File f = new File(path);
		FileInputStream in= new FileInputStream(f);
		ArrayList<Integer> l = new ArrayList<>();
		int t;
		while((t = in.read()) != -1){
			l.add(t);
		}
		in.close();
		FileOutputStream out= new FileOutputStream(f);
		for(int i=1; i<l.size(); i++)
		out.write(l.get(i));
		out.close();
	}
}
