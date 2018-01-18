package runing;

import java.util.ArrayList;

import operation.Iou_Operation4;

public class Iou_Operation4_test {
	public static void main(String[] args) throws Exception {
		String xmlPath = "G:/算法/EdgeBox/xml"; 					//xml文件所在文件夹路径
		String textPath = "G:/算法/EdgeBox/Edge boxes";					//txt文件所在文件夹路径
		String outPath = "file/rrrr3.xls";					//输出文件的路径，包括文件名.xls
		Double[] thres = {0.5,0.6,0.7,0.8,0.9};						//需要计算的iou阈值  大括号内逗号隔开
		Integer[] proNum = {100,200,500,1000}; 				//需要指定的pro数量 大括号内逗号隔开
		boolean isEdgeBox = true;		//proposal是够为左上长宽格式
		boolean tran = false;				//proposal的x,y坐标是否需要调换
		ArrayList<Double> thresholds = new ArrayList<>();
		for(double thre:thres) thresholds.add(thre);
		ArrayList<Integer> proNums = new ArrayList<>();
		for(Integer num : proNum) proNums.add(num);
		Iou_Operation4 operation = new Iou_Operation4(xmlPath, textPath, outPath);
		operation.getIouByProNumber(thresholds, proNums, isEdgeBox, tran);
	}
}
