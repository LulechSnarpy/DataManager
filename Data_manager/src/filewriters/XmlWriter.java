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

/*写入xml文件的类*/
/**
 * 写入xml文件的类
 * @version 2017_11_14
 * @author Lulech
 * */
public class XmlWriter {
	private String path = null;
	private MyXml mx;
	
	//path写入文件的路径，mx写入的xml信息
	/**
	 * 构造函数
	 * @param 文件路径
	 * @param 写入文件的xml信息
	 * */
	public XmlWriter(String path,MyXml mx){
		this.path = path;
		this.mx = mx;
	}
	
	
	/**
	 * 创建一个xml文档对象并将它写入文件
	 * @return void
	 * */
	public void createDocument(){
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
					);//设为小写
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
        //outputFormat.setNewlines(true);//设置是否换行
        outputFormat.setSuppressDeclaration(true); //是否生产xml头(true:不产生)
        outputFormat.setIndent(true); //设置是否缩进
        outputFormat.setIndent("\t"); //以四个空格方式实现缩进
        
		doc.asXML();
		XMLWriter output = null;
		File f= new File(path);
		try{
			output = new XMLWriter(new FileOutputStream(f),outputFormat);
			output.write(doc);
			clearHeadLine();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				output.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	
	/*删除xml中多余的开头*/
	private void clearHeadLine(){
		File f = new File(path);
		ArrayList<Integer> l = new ArrayList<>();
		try(FileInputStream in= new FileInputStream(f)){
			int t;
			while((t = in.read()) != -1){
				l.add(t);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		try(FileOutputStream out= new FileOutputStream(f)){
			for(int i=1; i<l.size(); i++)
			out.write(l.get(i));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
