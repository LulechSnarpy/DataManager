package operation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.dom4j.DocumentException;

import com.fileio.FileIO;
import com.fileio.PathGeter;

import bean.MyObj;
import bean.MyXml;
import filereaders.TextReader;
import filereaders.XmlReader;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Iou_Operation3 extends  Iou_Operation{
		private String xmlPath;
		private String textPath;
		private String outPath;
		private ArrayList<String> xmls;
		private ArrayList<String>  txts;
		private MyXml mx;
		private ArrayList<MyObj> mos;
		private ArrayList<ArrayList<Double>> rrs;
		private  ArrayList<ArrayList<Integer>> matchNums;
		private ArrayList<ArrayList<BigDecimal>> avgrrs;
		private WritableWorkbook  workbook;
		private WritableSheet[] sheet;
		public Iou_Operation3(String xmlPath, String textPath, String outPath) {
			super();
			this.xmlPath = xmlPath;
			this.textPath = textPath;
			this.outPath = outPath;
		}

		public  void getIouByProNumber(ArrayList<Double> thresholds,ArrayList<Integer> proNums,boolean isEdgeBox,boolean tran) throws RowsExceededException, WriteException{
			sheet = new WritableSheet[3];
			avgrrs = new ArrayList<>();
			for(int i=0; i<thresholds.size(); i++){
				ArrayList<BigDecimal> avg = new ArrayList<>();
				for(int j=0; j<proNums.size(); j++){
					avg.add(new BigDecimal("0"));
				}
				avgrrs.add(avg);
			}
			getInputDatas();
			tryCreateXlsWriter();
		     WritableCellFormat wcf = new WritableCellFormat();  
	        wcf.setAlignment(Alignment.CENTRE);
	        wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			for(int k=0; k<3; k++){
				sheet[k].addCell(new Label(0, 0, "文件名称",wcf));
				sheet[k].mergeCells(0, 0, 0, 1);
				for(int i=0; i<thresholds.size(); i++){
					sheet[k].addCell(new Label(1+proNums.size()*i,0,"IOU:"+thresholds.get(i),wcf));
					sheet[k].mergeCells(1+proNums.size()*i,0 , proNums.size()*(i+1),0);	
					for(int j=0; j<proNums.size(); j++){
						sheet[k].addCell(new Label(j+1+(thresholds.size()-1)*i, 1, proNums.get(j)+"",wcf));
					}
				}
			}
			for(int k=0; k<xmls.size(); k++){
				tryGetInputOneData(k, tran);
				sheet[0].addCell(new Label(0, k+2,mx.getFilename()));
				sheet[1].addCell(new Label(0, k+2, mx.getFilename()));
				sheet[2].addCell(new Label(0, k+2, mx.getFilename()));
				rrs = new ArrayList<>();
				matchNums = new ArrayList<>();
				for(int i=0; i<thresholds.size(); i++){
					double thre = thresholds.get(i);
					ArrayList<MyObj>  gt = mx.getObj();
					getIOU( mos,gt, thre,isEdgeBox);
					rrs.add(getRr());
					matchNums.add(getMatchNum());
					for(int j=0; j<proNums.size(); j++){
						int num = proNums.get(j);
						int lostNum = gt.size()-matchNums.get(i).get(num-1);
						double rr = rrs.get(i).get(num-1);
						ArrayList<BigDecimal> avg = avgrrs.get(i);
						avg.set(j, avg.get(j).add(new BigDecimal(rr)));
						sheet[0].addCell(new Label(j+1+(thresholds.size()-1)*i, k+2, rr+""));
						sheet[1].addCell(new Label(j+1+(thresholds.size()-1)*i, k+2,lostNum+""));
						sheet[2].addCell(new Label(j+1+(thresholds.size()-1)*i, k+2, (double)(lostNum*1.0/gt.size())+""));
					}
				}		
			}
			sheet[0].addCell(new Label(0,xmls.size()+2,"平均值"));
			for(int i=0; i<thresholds.size(); i++){
				for(int j=0; j<proNums.size(); j++){
					BigDecimal avgrr = avgrrs.get(i).get(j).divide(BigDecimal.valueOf(xmls.size()));
					sheet[0].addCell(new Label(j+1+(thresholds.size()-1)*i, xmls.size()+2,avgrr.toString()));
				}
			}
			tryWrite();
		}
		
		private void tryWrite(){
			try {
				write();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void write() throws IOException, WriteException{
			workbook.write();
			workbook.close();
		}
		
		private void tryCreateXlsWriter(){
			try {
				createXlsWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void createXlsWriter() throws IOException{
			File file = new File(outPath);
			FileIO.checkAndCreateFile(file);
			workbook = Workbook.createWorkbook(file);
			sheet[0] = workbook.createSheet("召回率",0);
			sheet[1] = workbook.createSheet("漏检数",1);
			sheet[2] = workbook.createSheet("漏检率",2);
		}
		
		private void getInputDatas(){
			PathGeter pg = new PathGeter();
			pg.init();
			 xmls = pg.getXmlFiles(xmlPath);
			 txts= pg.getTextFiles(textPath);
		}
		
		private void tryGetInputOneData(int s,boolean tran){
			try {
				getInputOneData(s, tran);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void getInputOneData(int s,boolean isEdgeBox) throws DocumentException, FileNotFoundException{
			XmlReader xr = new XmlReader();
			TextReader tr = new TextReader();
			mx = xr.readAll(xmls.get(s));
			mos = tr.readBox(txts.get(s),isEdgeBox);
		}
}
