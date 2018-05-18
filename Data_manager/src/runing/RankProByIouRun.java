package runing;

import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import bean.MyBox;
import bean.MyObj;
import filereaders.TextReader;
import filereaders.XmlReader;
import filewriters.XlsWriter;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import operation.Iou_Operation2;

/*��proposal����iou����������*/
public class RankProByIouRun {
	/*
	 * @path  xls���·��
	 * @path1 xml·��
	 * @path2 txt·��
	 * @iseage �Ƿ�Ϊeagerbox��ʽ������
	 * @dotran �費��Ҫ����x��y
	 * */
	public static void main(String[] args) throws DocumentException, RowsExceededException, WriteException, IOException {
		String path="file/SEelctive/102028.xls";
		String path1="file/SEelctive/102028.xml";
		String path2="file/SEelctive/102028.txt";
		boolean iseage = false;
		boolean dotran = false;
		XmlReader xr = new XmlReader();
		ArrayList<MyObj> groundTruth= xr.readAllMyobjBox(path1);
		TextReader tr = new TextReader();
		ArrayList<MyObj>  proposals = tr.readBox(path2,dotran) ;
		Iou_Operation2 iouOp2 = new Iou_Operation2();
		ArrayList<MyBox> pro = iouOp2.getIOURank(proposals, groundTruth, iseage);
		XlsWriter xw = new XlsWriter();
		xw.WriteRandBoxes(path, pro);
	}
	
}
