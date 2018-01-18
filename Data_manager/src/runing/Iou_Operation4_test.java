package runing;

import java.util.ArrayList;

import operation.Iou_Operation4;

public class Iou_Operation4_test {
	public static void main(String[] args) throws Exception {
		String xmlPath = "G:/�㷨/EdgeBox/xml"; 					//xml�ļ������ļ���·��
		String textPath = "G:/�㷨/EdgeBox/Edge boxes";					//txt�ļ������ļ���·��
		String outPath = "file/rrrr3.xls";					//����ļ���·���������ļ���.xls
		Double[] thres = {0.5,0.6,0.7,0.8,0.9};						//��Ҫ�����iou��ֵ  �������ڶ��Ÿ���
		Integer[] proNum = {100,200,500,1000}; 				//��Ҫָ����pro���� �������ڶ��Ÿ���
		boolean isEdgeBox = true;		//proposal�ǹ�Ϊ���ϳ����ʽ
		boolean tran = false;				//proposal��x,y�����Ƿ���Ҫ����
		ArrayList<Double> thresholds = new ArrayList<>();
		for(double thre:thres) thresholds.add(thre);
		ArrayList<Integer> proNums = new ArrayList<>();
		for(Integer num : proNum) proNums.add(num);
		Iou_Operation4 operation = new Iou_Operation4(xmlPath, textPath, outPath);
		operation.getIouByProNumber(thresholds, proNums, isEdgeBox, tran);
	}
}
