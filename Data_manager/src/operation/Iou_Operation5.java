package operation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fileio.FileIO;
import com.fileio.PathGeter;

import bean.MyBox;
import bean.MyObj;
import bean.MyXml;
import filereaders.TextReader;
import filereaders.XmlReader;
import filewriters.TextWriter;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * 计算Proposal的IOU
 * @version 2018_05_19
 * @author Lulech
 * */
public class Iou_Operation5 extends Iou_Operation2{
	private String xmlPath;
	private String textPath;
	private String outPath;
	private ArrayList<String> xmls;
	private ArrayList<String>  txts;
	private MyXml mx;
	private ArrayList<MyObj> mos;
	private ArrayList<MyBox> mbs;
	private WritableWorkbook  workbook;
	/**
	 * 构造函数
	 * @param String xml文件所在路径
	 * @param Stirng text文件所在路径
	 * @param String 结果文件输出的文件夹路径
	 * */
	public Iou_Operation5(String xmlPath, String textPath, String outPath) {
		super();
		this.xmlPath = xmlPath;
		this.textPath = textPath;
		this.outPath = outPath+File.separator+"proIou";
	}
	/**
	 * 算出每个候选框的IoU并写入文件
	 * @param boolean isEdgeBox 是否是左上宽高类型的数据
	 * @param boolean tran 是否需要调换 x,y的位置
	 * @return void
	 * @throws WriteException 
	 * */
	public void getProIouSaveAsFiles(boolean isEdgeBox,boolean tran) throws WriteException{
		getInputDatas();
		tryCreateXlsWriter();
		String txtOutPath = outPath + File.separator + "textdata";
		for(int i=0; i<xmls.size(); i++){
			getInputOneData(i, tran);
			mbs = getIOUP( mos,mx.getObj(), isEdgeBox);
			StringBuffer strb = new StringBuffer();
			WritableSheet sheet = workbook.createSheet(mx.getFilename(), 0);
			sheet.addCell(new Label(0, 0, "左上x坐标"));
			sheet.addCell(new Label(1, 0, "左上y坐标"));
			sheet.addCell(new Label(2, 0, "长"));
			sheet.addCell(new Label(3, 0, "宽"));
			sheet.addCell(new Label(4, 0, "IOU"));
			for(int j=0; j<mbs.size();j++){
				MyBox mb = mbs.get(j);
				sheet.addCell(new Label(0,j+1,""+mb.x1));
				sheet.addCell(new Label(1,j+1,""+mb.y1));
				sheet.addCell(new Label(2,j+1,""+(mb.x2-mb.x1)));
				sheet.addCell(new Label(3,j+1,""+(mb.y2-mb.y1)));
				sheet.addCell(new Label(4,j+1,String.format("%.6f", mb.iou)));
				strb.append(mb.x1).append("\t");
				strb.append(mb.y1).append("\t");
				strb.append(mb.x2-mb.y1).append("\t");
				strb.append(mb.y2-mb.y1).append("\t");
				strb.append(String.format("%.6f", mb.iou)).append(System.lineSeparator());
			}
			String fn= mx.getFilename();
			String path = txtOutPath+File.separator+fn.substring(0,fn.lastIndexOf("."))+".txt";
			System.out.println(path);
			TextWriter.writeText(path, strb.toString());
		}
		write();
	}
	
	private void write(){
		try{
			workbook.write();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				workbook.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	private void tryCreateXlsWriter(){
		try {
			createXlsWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createXlsWriter() throws IOException {
		File file = new File(outPath,"proIou.xls");
		FileIO.checkAndCreateFile(file);
		workbook = Workbook.createWorkbook(file);
	}
	
	private void getInputDatas(){
		PathGeter pg = new PathGeter();
		 xmls = pg.getXmlFiles(xmlPath);
		 for(String s : xmls) System.out.println(xmls);
		 txts= pg.getTextFiles(textPath);
	}
	
	private void getInputOneData(int s,boolean tran){
		XmlReader xr = new XmlReader();
		TextReader tr = new TextReader();
		mx = xr.readAll(xmls.get(s));
		mos = tr.readBox(txts.get(s),tran);
	}
}
