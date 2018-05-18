package operation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

/**
 * �����ٻ��ʡ�©������©����
 * @version 2017_11_14
 * @author Lulech
 * */
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
		private ArrayList<ArrayList<Double>> avgrrs;
		private WritableWorkbook  workbook;
		private WritableSheet[] sheet;
		/**
		 * ���캯��
		 * @param String xml�ļ�����·��
		 * @param Stirng text�ļ�����·��
		 * @param String ����ļ����·��
		 * */
		public Iou_Operation3(String xmlPath, String textPath, String outPath) {
			super();
			this.xmlPath = xmlPath;
			this.textPath = textPath;
			this.outPath = outPath;
		}
		/**
		 * ����pro���iou
		 * @param ArrayList<Double> ��ֵList
		 * @param ArrayList<Integer> Ҫ��proposal������List 
		 * @param boolean isEdgeBox �Ƿ������Ͽ�����͵�����
		 * @param boolean tran �Ƿ���Ҫ���� x,y��λ��
		 * @return void
		 * @throws WriteException 
		 * */
		public  void getIouByProNumber(ArrayList<Double> thresholds,ArrayList<Integer> proNums,boolean isEdgeBox,boolean tran) throws WriteException{
			sheet = new WritableSheet[3];
			avgrrs = new ArrayList<>();
			for(int i=0; i<thresholds.size(); i++){
				ArrayList<Double> avg = new ArrayList<>();
				for(int j=0; j<proNums.size(); j++){
					avg.add(0.0);
				}
				avgrrs.add(avg);
			}
			getInputDatas();
			tryCreateXlsWriter();
		    WritableCellFormat wcf = new WritableCellFormat();  
	        wcf.setAlignment(Alignment.CENTRE);
	        wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			for(int k=0; k<3; k++){
				sheet[k].addCell(new Label(0, 0, "�ļ�����",wcf));
				sheet[k].mergeCells(0, 0, 0, 1);
				for(int i=0; i<thresholds.size(); i++){
					sheet[k].addCell(new Label(1+proNums.size()*i,0,"IOU:"+thresholds.get(i),wcf));
					sheet[k].mergeCells(1+proNums.size()*i,0 , proNums.size()*(i+1),0);	
					for(int j=0; j<proNums.size(); j++){
						sheet[k].addCell(new Label(j+1+(thresholds.size()-1)*i, 1, proNums.get(j)+"",wcf));
					}
				}
			}
			System.out.println();
			for(int k=0; k<xmls.size(); k++){
				getInputOneData(k, tran);
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
//						if(num>matchNums.get(i).size()){	
//							System.out.println(mx.getFilename());
//							continue big;
//						}
						int lostNum = gt.size()-matchNums.get(i).get(num-1);
						double rr = rrs.get(i).get(num-1);
						ArrayList<Double> avg = avgrrs.get(i);
						avg.set(j, avg.get(j)+rr);
						sheet[0].addCell(new Label(j+1+(thresholds.size()-1)*i, k+2, rr+""));
						sheet[1].addCell(new Label(j+1+(thresholds.size()-1)*i, k+2,lostNum+""));
						sheet[2].addCell(new Label(j+1+(thresholds.size()-1)*i, k+2, (double)(lostNum*1.0/gt.size())+""));
					}
				}		
			}
			sheet[0].addCell(new Label(0,xmls.size()+2,"ƽ��ֵ"));
			for(int i=0; i<thresholds.size(); i++){
				for(int j=0; j<proNums.size(); j++){
					Double avgrr = avgrrs.get(i).get(j)/xmls.size();
					sheet[0].addCell(new Label(j+1+(thresholds.size()-1)*i, xmls.size()+2,avgrr.toString()));
				}
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
			File file = new File(outPath);
			FileIO.checkAndCreateFile(file);
			workbook = Workbook.createWorkbook(file);
			sheet[0] = workbook.createSheet("�ٻ���",0);
			sheet[1] = workbook.createSheet("©����",1);
			sheet[2] = workbook.createSheet("©����",2);
		}
		
		private void getInputDatas(){
			PathGeter pg = new PathGeter();
			 xmls = pg.getXmlFiles(xmlPath);
			 txts= pg.getTextFiles(textPath);
		}
		
		private void getInputOneData(int s,boolean tran){
			XmlReader xr = new XmlReader();
			TextReader tr = new TextReader();
			mx = xr.readAll(xmls.get(s));
			mos = tr.readBox(txts.get(s),tran);
		}
}
