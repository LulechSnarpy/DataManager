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

/*将proposal根据iou排序并输出结果*/
public class RankProByIouRun {
	/*
	 * @path  xls输出路径
	 * @path1 xml路径
	 * @path2 txt路径
	 * @iseage 是否为eagerbox形式的数据
	 * @dotran 需不需要调换x，y
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
