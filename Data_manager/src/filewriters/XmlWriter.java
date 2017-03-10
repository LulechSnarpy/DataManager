package filewriters;

import java.io.File;
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
		root.addElement("filename").setText(mx.getFilename());
		Element c1 = root.addElement("source");
		c1.addElement("database").setText(mx.getSodatebase());
		c1.addElement("annotation").setText(mx.getSosource());
		c1.addElement("image").setText(mx.getSoimage());
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
			c3.addElement("name").setText(mj.name.toLowerCase());//设为小写
			c3.addElement("pose").setText(mj.pose);
			c3.addElement("color").setText(mj.color.toLowerCase());//设为小写
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
		
		
		  // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();  
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1  
        outputFormat.setEncoding("UTF-8");
        outputFormat.setSuppressDeclaration(true); //是否生产xml头
        outputFormat.setIndent(true); //设置是否缩进
        outputFormat.setIndent("\t"); //以四个空格方式实现缩进
        outputFormat.setNewlines(true); //设置是否换行
 
		doc.asXML();
		XMLWriter output;
		File f= new File(path);
		if(!f.exists()) f.createNewFile();
		output = new XMLWriter(new FileWriter(f),outputFormat);
		output.write(doc);
		output.close();
	}
	
}
