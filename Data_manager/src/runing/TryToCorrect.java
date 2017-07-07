package runing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;

import org.dom4j.DocumentException;

import filecorrector.XmlCorrector;

/*修正之前软件产生的xml文件的错误执行程序*/
public class TryToCorrect {
	private static String r;
	private static ArrayList<String> filelist;
	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
		XmlCorrector xc = new XmlCorrector();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int value = chooser.showOpenDialog(chooser);
		if(value == JFileChooser.APPROVE_OPTION){
			r = chooser.getSelectedFile().getPath();
			if(r.endsWith("Annotations")){
				ArrayList<String> al = getFiles(r);
				Iterator<String> it = al.iterator();
				while(it.hasNext()){
					try {
						xc.tryCorrect(it.next());
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else{
				main(args);
			}
		}
	}
	/*
     * 通过递归得到某一路径下所有的目录及其文件
     */
    public static ArrayList<String> getFiles(String filePath){
	     File root = new File(filePath);
	     filelist = new ArrayList<String>();
	       File[] files = root.listFiles();
	       for(File file:files){     
		        if(file.isDirectory()){
		        	getFiles(file.getAbsolutePath());
		        }
		        else{
		        	
		        	String s = file.getAbsolutePath();
		        	if( s.endsWith(".xml") )
		        	{
			        	filelist.add(file.getAbsolutePath());
			        	System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath());
		             }
		        }  
	       }
	    return filelist;
	}
}
