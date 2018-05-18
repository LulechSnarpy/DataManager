package runing;

import java.util.ArrayList;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import operation.Iou_Operation3;

/*��ͬIOU��ͼƬ���ٻ��ʣ�����ͼƬpro����չʾ�ٻ��ʣ�©������©����*/
public class Iou_Operation3_Run {
	public static void main(String[] args) throws RowsExceededException, WriteException {
		String xmlPath = "G:/�㷨/ED����/ED��xml"; 					//xml�ļ������ļ���·��
		String textPath = "G:/�㷨/ED����/a=0.75b=0.85(8)";					//txt�ļ������ļ���·��
		String outPath = "file/rrrr5.xls";					//����ļ���·���������ļ���.xls
		Double[] thres = {0.5,0.6,0.7,0.8,0.9};						//��Ҫ�����iou��ֵ  �������ڶ��Ÿ���
		Integer[] proNum = {100,200,500,1000}; 				//��Ҫָ����pro���� �������ڶ��Ÿ���
		boolean isEdgeBox = true;		//proposal�ǹ�Ϊ���ϳ����ʽ
		boolean tran = false;				//proposal��x,y�����Ƿ���Ҫ����
		ArrayList<Double> thresholds = new ArrayList<>();
		for(double thre:thres) thresholds.add(thre);
		ArrayList<Integer> proNums = new ArrayList<>();
		for(Integer num : proNum) proNums.add(num);
		Iou_Operation3 operation3 = new Iou_Operation3(xmlPath, textPath, outPath);
		operation3.getIouByProNumber(thresholds, proNums, isEdgeBox, tran);
	}
}
