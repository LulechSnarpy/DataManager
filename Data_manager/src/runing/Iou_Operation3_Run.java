package runing;

import java.util.ArrayList;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import operation.Iou_Operation3;

/*不同IOU下图片的召回率，根据图片pro数量展示召回率，漏检数，漏检率*/
public class Iou_Operation3_Run {
	public static void main(String[] args) throws RowsExceededException, WriteException {
		String xmlPath = "G:/算法/ED数据/ED总xml"; 					//xml文件所在文件夹路径
		String textPath = "G:/算法/ED数据/a=0.75b=0.85(8)";					//txt文件所在文件夹路径
		String outPath = "file/rrrr5.xls";					//输出文件的路径，包括文件名.xls
		Double[] thres = {0.5,0.6,0.7,0.8,0.9};						//需要计算的iou阈值  大括号内逗号隔开
		Integer[] proNum = {100,200,500,1000}; 				//需要指定的pro数量 大括号内逗号隔开
		boolean isEdgeBox = true;		//proposal是够为左上长宽格式
		boolean tran = false;				//proposal的x,y坐标是否需要调换
		ArrayList<Double> thresholds = new ArrayList<>();
		for(double thre:thres) thresholds.add(thre);
		ArrayList<Integer> proNums = new ArrayList<>();
		for(Integer num : proNum) proNums.add(num);
		Iou_Operation3 operation3 = new Iou_Operation3(xmlPath, textPath, outPath);
		operation3.getIouByProNumber(thresholds, proNums, isEdgeBox, tran);
	}
}
